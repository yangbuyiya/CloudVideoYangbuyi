<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div style="margin: 20px 20px">
    <el-tree
      ref="tree"
      :data="data"
      show-checkbox
      node-key="id"
      highlight-current
      :props="defaultProps"
    />
    <el-button :disabled="saveBtnDisabled" type="primary" @click="save">保存</el-button>
  </div>
</template>
<script>
import menu from '@/api/authority/menu'
export default {
  data() {
    return {
      saveBtnDisabled: false,
      data: [],
      defaultProps: {
        children: 'childMenuList',
        label: 'name'
      },
      roleId: ''

    }
  },
  // 监听器
  watch: {
    $route(to, from) {
      console.log('路由变化......')
      console.log(to)
      console.log(from)
      this.init()
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      if (this.$route.params && this.$route.params.id) {
        this.roleId = this.$route.params.id
        this.fetchDataById(this.roleId)
      }
    },
    fetchDataById(roleId) {
      menu.getMenuWithRoleId(roleId).then(response => {
        this.data = response.data.rows
        const jsonList = JSON.parse(JSON.stringify(this.data))
        const list = []
        this.getJsonToList(list, jsonList[0]['childMenuList'])
        // 设置默认选择
        this.setCheckedKeys(list)
      })
    },
    // 把json数据转成string再转成对象，根据Key获取value数据
    getJsonToList(list, jsonList) {
      // 遍历这个集合对象，获取key的值
      for (let i = 0; i < jsonList.length; i++) {
        if (jsonList[i]['select'] === true && jsonList[i]['level'] === 4) {
          list.push(jsonList[i]['id'])
        }
        if (jsonList[i]['childMenuList'] != null) {
          this.getJsonToList(list, jsonList[i]['childMenuList'])
        }
      }
    },

    getCheckedNodes() {
      console.log(this.$refs.tree.getCheckedNodes())
    },
    getCheckedKeys() {
      console.log(this.$refs.tree.getCheckedKeys())
    },

    setCheckedKeys(list) {
      this.$refs.tree.setCheckedKeys(list)
    },

    save() {
      this.saveBtnDisabled = true
      const ids = this.$refs.tree.getCheckedKeys().join(',')
      console.log(ids)
      menu.doAssignRoleAuth(this.roleId, ids).then(response => {
        if (response.success) {
          this.$message({
            type: 'success',
            message: '保存成功'
          })
          this.$router.push({ path: '/authority/role/list' })
        }
      })
    }
  }
}
</script>
