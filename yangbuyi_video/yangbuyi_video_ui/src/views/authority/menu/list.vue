<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div class="app-container">

    <!--row-key  数据当中 哪一个字段能够唯一的标识一行
    :expand-row-keys 要求传入的是一个数组
    -->
    <el-table
      ref="refTable"
      :data="menuList"
      style="width: 100%;margin-bottom: 20px;align-content: center"
      :expand-row-keys="expands"
      row-key="id"
      border
      :tree-props="{children: 'childMenuList', hasChildren: 'hasChildren'}"
      @row-click="clickTable"
    >
      <el-table-column prop="name" label="名称" sortable width="180" />
      <el-table-column prop="path" label="访问路径" sortable width="250" align="center" />
      <el-table-column prop="component" label="组件路径" sortable width="180" align="center" />
      <el-table-column prop="permissionValue" label="权限值" align="center" />
      <!--CRUD操作-->
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            @click.native.stop="() => {dialogFormVisible = true,menu.pid = scope.row.id}"
          >添加菜单</el-button>
          <el-button
            type="success"
            size="mini"
            @click.native.stop="() => getById(scope.row)"
          >修改菜单</el-button>
          <el-button
            type="success"
            size="mini"
            @click.native.stop="() => {dialogPermissionVisible = true, permission.pid = scope.row.id}"
          >添加权限
          </el-button>
          <el-button
            type="primary"
            size="mini"
            @click.native.stop="() => updateFunction(scope.row)"
          >修改权限
          </el-button>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click.native.stop="() => remove(scope.row)"
          />
        </template>
      </el-table-column>
    </el-table>
    <!--添加菜单的窗口-->
    <el-dialog :visible.sync="dialogFormVisible" :title="dialogFormValue">
      <el-form ref="menu" :model="menu" :rules="menuValidateRules" label-width="120px">
        <el-form-item label="菜单名称" prop="name">
          <el-input v-model="menu.name" />
        </el-form-item>
        <el-form-item label="访问路径" prop="path">
          <el-input v-model="menu.path" />
        </el-form-item>
        <el-form-item label="组件路径" prop="component">
          <el-input v-model="menu.component" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetData()">取 消</el-button>
        <el-button type="primary" @click="addMenuClick()">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 添加权限的窗口 -->
    <el-dialog :visible.sync="dialogPermissionVisible" title="添加功能权限">
      <el-form ref="permission" :model="permission" :rules="permissionValidateRules" label-width="120px">
        <el-form-item label="功能名称" prop="name">
          <el-input v-model="permission.name" />
        </el-form-item>
        <el-form-item label="访问路径">
          <el-input v-model="permission.path" />
        </el-form-item>
        <el-form-item label="组件路径">
          <el-input v-model="permission.component" />
        </el-form-item>
        <el-form-item label="功能权限值" prop="permissionValue">
          <el-input v-model="permission.permissionValue" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetData()">取 消</el-button>
        <el-button type="primary" @click="appendPermission()">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import menu from '@/api/authority/menu'
/** 菜单实体*/
const menuForm = {
  name: '',
  pid: 0,
  path: '',
  component: '',
  type: 1
}
/** 权限实体*/
const perForm = {
  permissionValue: '',
  name: '',
  path: '',
  component: '',
  pid: 0,
  type: 2
}
export default {
  /** 初始化相关属性*/
  data() {
    return {
      menuList: [],
      defaultProps: {
        children: 'childMenuList',
        label: 'name'
      },
      dialogFormValue: '添加菜单',
      dialogFormVisible: false,
      dialogPermissionVisible: false,
      menu: { ...menuForm },
      permission: { ...perForm },
      menuValidateRules: {
        name: [{ required: true, trigger: 'blur', message: '菜单名必须输入' }],
        path: [{ required: true, trigger: 'blur', message: '菜单路径必须输入' }],
        component: [{ required: true, trigger: 'blur', message: '组件名称必须输入' }]
      },
      permissionValidateRules: {
        name: [{ required: true, trigger: 'blur', message: '功能名称必须输入' }],
        permissionValue: [{ required: true, trigger: 'blur', message: '功能权限值必须输入' }]
      },
      // 要展开的行，数值的元素是row的key值
      expands: []
    }
  },
  created() {
    /** 获取列表数据*/
    this.fetchNodeList()
  },
  methods: {
    /** table的方法,展开/折叠 行*/
    clickTable(row, index, e) {
      // 调用,table的方法,展开/折叠 行
      this.$refs.refTable.toggleRowExpansion(row)
    },
    /** 获取列表数据*/
    fetchNodeList() {
      menu.getNestedTreeList().then(response => {
        if (response.success === true) {
          this.menuList = response.data.rows
          this.expands.push(this.menuList[0].id)
          this.resetData()
        }
      })
    },
    /** 删除菜单*/
    remove(data) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return menu.removeById(data.id)
      }).then(() => {
        this.fetchNodeList()// 刷新列表
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch((response) => { // 失败
        if (response === 'cancel') {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        } else {
          this.$message({
            type: 'error',
            message: '删除失败'
          })
        }
      })
    },
    /** 添加功能权限*/
    appendPermission() {
      this.$refs.permission.validate(valid => {
        if (valid) {
          if (this.permission.id) {
            this.update(this.permission)
          } else {
            menu.saveMenu(this.permission).then(response => {
              this.dialogPermissionVisible = false
              this.$message({
                type: 'success',
                message: '添加权限成功'
              })
              /** 刷新数据，展开菜单*/
              menu.getNestedTreeList().then(response => {
                if (response.success === true) {
                  this.menuList = response.data.rows
                  this.expands.push(this.permission.pid)
                  this.resetData()
                }
              })
            })
          }
        }
      })
    },
    /** 添加菜单点击*/
    addMenuClick() {
      this.$refs.menu.validate(valid => {
        if (valid) {
          if (!this.menu.id) { // 添加
            this.addMenu()
          } else { // 修改
            this.update(this.menu)
          }
        }
      })
    },
    /** 添加菜单*/
    addMenu() {
      menu.saveMenu(this.menu).then(response => {
        this.dialogFormVisible = false
        this.$message({
          type: 'success',
          message: '添加菜单成功'
        })
        /** 刷新数据，展开菜单*/
        menu.getNestedTreeList().then(response => {
          if (response.success === true) {
            this.menuList = response.data.rows
            /** 展开刚添加菜单的父级*/
            this.expands.push(this.menu.pid)
            this.resetData()
          }
        })
      }).catch(response => {
        this.dialogFormVisible = false
        this.$message({
          type: 'error',
          message: '添加菜单失败'
        })
      })
    },
    /** 修改菜单*/
    update(obj) {
      menu.update(obj).then(response => {
        this.dialogFormVisible = false
        this.$message({
          type: 'success',
          message: '修改成功'
        })
        // 刷新页面
        this.fetchNodeList()
        this.resetData()
      })
    },
    /** 获取菜单信息*/
    getById(data) {
      this.dialogFormVisible = true
      this.menu = data
    },
    /** 修改权限点击*/
    updateFunction(data) {
      this.dialogPermissionVisible = true
      this.permission = data
    },
    /** 重置数据*/
    resetData() {
      this.dialogPermissionVisible = false
      this.dialogFormVisible = false
      this.menu = { ...menuForm }
      this.permission = { ...perForm }
    }
  }
}
</script>
