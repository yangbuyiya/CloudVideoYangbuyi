<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div class="app-container">
    <el-checkbox
      v-model="checkAll"
      :indeterminate="isIndeterminate"
      @change="handleCheckAllChange"
    >全选
    </el-checkbox>

    <div style="margin: 15px 0;" />
    <el-checkbox-group v-model="checkedRoles" @change="handlecheckedRolesChange">
      <el-checkbox
        v-for="role in roles"
        :key="role.id"
        :label="role.id"
      >
        {{ role.roleName }}
      </el-checkbox>
    </el-checkbox-group>
    <br>
    <el-button :disabled="saveBtnDisabled" type="primary" @click="update">保存</el-button>
  </div>
</template>

<script>

import userApi from '@/api/authority/user'

export default {
  data() {
    return {
      checkAll: false, // 是否选中先
      checkedRoles: [], // 已选中
      roles: [], // 所有的
      isIndeterminate: true,
      userId: '',
      saveBtnDisabled: false // 保存按钮是否禁用,
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      if (this.$route.params && this.$route.params.id) {
        /** 获取用户id*/
        this.userId = this.$route.params.id
        /** 查询用户*/
        this.getById(this.userId)
      }
    },
    getById(userId) {
      userApi.getAssign(userId).then(response => {
        // 获取用户角色
        var jsonObj = response.data.assignRoles
        // 设置角色选中状态
        this.checkedRoles = this.getJsonToList(jsonObj, 'id')
        this.roles = response.data.allRolesList
      })
    },
    // 把json数据转成string再转成对象，根据Key获取value数据
    getJsonToList(json, key) {
      // 把JSON字符串转成对象
      var list = JSON.parse(JSON.stringify(json))
      var strText = []
      // 遍历这个集合对象，获取key的值
      for (var i = 0; i < list.length; i++) {
        strText.push(list[i][key])
      }
      return strText
    },
    handleCheckAllChange(val) { // 全选
      this.checkedRoles = val ? this.roles : []
      this.isIndeterminate = false
    },
    handlecheckedRolesChange(value) { // 切换角色
      const checkedCount = value.length
      this.checkAll = checkedCount === this.roles.length
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.roles.length
    },
    update() { // 更新提交
      this.saveBtnDisabled = true // 防止表单重复提交
      var ids = this.checkedRoles.join(',')
      console.log(ids)
      // 修改权限
      userApi.saveAssign(this.userId, ids).then(response => {
        if (response.success) {
          this.$message({
            type: 'success',
            message: '保存成功'
          })
          this.$router.push({ path: '/authority/user/list' })
        }
      })
    }
  }
}
</script>
