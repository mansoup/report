<template>
  <anji-crud ref="listPage" :option="crudOption">
    <template v-slot:buttonLeftOnTable>
      <el-button
        type="primary"
        icon="el-icon-plus"
        @click="operateProject('add')"
        v-permission="'projectManage:insert'">新增</el-button>
    </template>
    <!--    v-permission中的projectManage由后端发送 可以先删除 后期添加-->
    <template slot="rowButton" slot-scope="props">
      <el-button
        type="text"
        @click="operateProject('edit', props)"
        v-permission="'projectManage:update'">编辑</el-button>
    </template>
    <template v-slot:pageSection>
      <EditProject
        ref="EditProject"
        :project="project"
        :visib="dialogVisibleSetProject"
        @handleClose="dialogVisibleSetProject = false"
        @refreshList="refreshList"
      />
    </template>

  </anji-crud>
</template>

<script>


import EditProject from "@/views/projectMange/components/EditProject";
import {supervisorAdd, supervisorDeleteBatch, supervisorDetail, supervisorList, supervisorUpdate} from "@/api/supervisor";

export default {
  name: "ProjectManage",
  components: {
    EditProject
  },
  data() {
    return {

      dialogVisibleSetProject: false,
      project: {},

      crudOption: {
        // 使用菜单做为页面标题
        title: "工程管理",
        // 详情页中输入框左边文字宽度
        labelWidth: "120px",
        // 查询表单条件
        queryFormFields: [
          {
            inputType: "input",
            label: "名字",
            field: "name"
          },
          //根据工程名称查找水利工程
          //暂不考虑实现
          //这里仅用来修饰
          {
            inputType: "input",
            label: "电话号码",
            field: "phoneNumber"
          },

        ],
        // 操作按钮
        buttons: {
          customButton: {
            operationWidth: 150
          },
          query: {
            api: supervisorList,
            permission: "projectManage:query",
            sort: "update_time",
            order: "DESC"
          },
          queryByPrimarykey: {
            api: supervisorDetail,
            permission: "projectManage:detail"
          },
          add: {
            api: supervisorAdd,
            permission: "projectManage:insert",
            isShow: false
          },
          delete: {
            api: supervisorDeleteBatch,
            permission: "projectManage:delete"
          },
          edit: {
            api: supervisorUpdate,
            permission: "projectManage:update",
            isShow: false
          }
        },

        // 表格列
        columns: [
          {
            //后期应该有关联表，不止就是管理者的表
            label: "id",
            field: "id",
            primaryKey: true, // 根据主键查询详情或者根据主键删除时, 主键的
            tableHide: true, // 表格中不显示
            editHide: true // 编辑弹框中不显示
          },
          {
            label: "姓名", //姓名
            placeholder: "",
            field: "name",
            editField: "name",
            inputType: "input",
            disabled: false
          },
          {
            label: "家庭住址", //家庭住址
            placeholder: "",
            field: "address",
            editField: "address",
            inputType: "input",
            disabled: false
          },
          {
            label: "电话号码", //电话号码
            placeholder: "",
            field: "phoneNumber",
            editField: "phoneNumber",
            inputType: "input",
            disabled: false
          },
          {
            label: "负责人职位", //负责人职位
            placeholder: "",
            field: "position",
            editField: "position",
            inputType: "input",
            disabled: false
          },
        ]
      }
    };
  },
  created() {},
  methods: {
    operateProject(type, prop) {
      this.dialogVisibleSetProject = true;
      if (prop) {

        console.log("点击的是编辑")
        console.log("点击的id是： "+prop.msg.id)

        this.project = prop.msg;
        // console.log("this.project"+this.project.accessKey) 皆为null
        // console.log("prop.msg"+prop.msg.accessKey)
      } else {
        console.log("点击的是新增")
        this.project = {};
      }


      this.$refs.EditProject.addOrEdit(this.project);
    },
    refreshList() {
      this.$refs.listPage.handleQueryForm("query");
    }

  }
}
</script>


