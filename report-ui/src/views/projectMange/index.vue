<!--
 * @Descripttion: 报表设计--工程管理
 * @Author: liuwei
 * @Date: 2022-05-15 22:15:36
-->
<template >

  <anji-crud ref="listPage" :option="crudOption" >
    <template v-slot:buttonLeftOnTable>
        <el-button
          type="primary"
          icon="el-icon-plus"
          @click="operateProject('add')"
          v-permission="'projectManage:insert'">新增</el-button>
    </template>
<!--    <template v-slot:test1>-->
<!--      <router-link to="/report/projects/projectDetail">-->
<!--      <el-button-->
<!--        type="primary"-->
<!--        icon="el-icon-plus">饮水工程表</el-button>-->
<!--      </router-link>-->
<!--    </template>-->
<!--    点击完审核工程就去刷新表-->
<!--    <template v-slot:test2>-->
<!--      <router-link to="/report/projects/projectApproval">-->
<!--      <el-button type="primary" v-permission="'projectManage:approval'" >审核工程-->
<!--      </el-button>-->
<!--      </router-link>-->
<!--    </template>-->
    <template v-slot:test3>
      <column-select
        ref="ColumnSelect"
        :columns="crudOption.columns"
        :visib="dialogVisibleColumns"
        @handleClose="dialogVisibleColumns = false"
      />
      <el-button
        type="primary"
        icon="el-icon-plus" @click="dialogVisibleColumns = true">列选择</el-button>
    </template>

    <template slot="rowButton" slot-scope="props">
      <el-button
        type="text"
        @click="operateProject('edit', props)"
        v-permission="'projectManage:update'">编辑</el-button>
    </template>
    <template slot="Approval" slot-scope="props">
      <Approval
        ref="Approval"
        :Approvalrow="currentRow"
        :visib="dialogVisibleApproval"
        @handleClose="dialogVisibleApproval = false"
      />
      <el-button v-if="props.msg.approval==1" v-permission="'projectManage:approval'" type="text" @click="createApproval(props)">审核</el-button>
    </template>
    <template slot="QRcode" slot-scope="props">
      <QRcode
        ref="QRcode"
        :crow="currentRow"
        :visib="dialogVisibleQRcode"
        @handleClose="dialogVisibleQRcode = false"
      />
      <el-button  type="text" @click="createQRcode(props)">生成二维码</el-button>
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
import ColumnSelect from "@/views/projectMange/components/ColumnSelect";
import QRcode from "@/views/projectMange/components/QRcode";
import Approval from "@/views/projectMange/components/ApprovalConfirm"
import {projectAdd, projectDeleteBatch, projectDetail, projectList, projectUpdate} from "@/api/projectManage";

export default {
  name: "ProjectManage",
  components: {
    ColumnSelect: ColumnSelect,
    QRcode: QRcode,
    Approval:Approval,
    EditProject
  },
  data() {
    return {
      dialogVisibleSetProject: false,
      project: {},
      dialogVisibleColumns:false,
      dialogVisibleQRcode:false,
      dialogVisibleApproval:false,
      crudOption: {
        // 使用菜单做为页面标题
        title: "工程管理",
        // 详情页中输入框左边文字宽度
        labelWidth: "120px",
        // 查询表单条件
        queryFormFields: [
          {
            inputType: "input",
            label: "工程编码",
            field: "supplyWaterProjectCode"
          },
          //根据工程名称查找水利工程
          //暂不考虑实现
          //这里仅用来修饰
          {
            inputType: "input",
            label: "工程名称",
            field: "supplyWaterProjectName"
          },
          {
            inputType: "input",
            label: "省",
            field: "province"
          },
          {
            inputType: "input",
            label: "市",
            field: "city"
          },
          {
            inputType: "input",
            label: "区县",
            field: "county"
          },
          {
            inputType: "input",
            label: "审核与否",
            field: "approval"
          },

        ],
        // 操作按钮
        buttons: {
          customButton: {
            operationWidth: 150
          },
          query: {
            api: projectList,
            permission: "projectManage:query",
            sort: "update_time",
            order: "DESC"
          },
          queryByPrimarykey: {
            api: projectDetail,
            permission: "projectManage:detail"
          },
          add: {
            api: projectAdd,
            permission: "projectManage:insert",
            isShow: false
          },
          delete: {
            api: projectDeleteBatch,
            permission: "projectManage:delete"
          },
          edit: {
            api: projectUpdate,
            permission: "projectManage:update",
            isShow: false
          }
        },

        // 表格列
        columns: [
          {
            label: "id",
            field: "id",
            primaryKey: true, // 根据主键查询详情或者根据主键删除时, 主键的
            tableHide: true, // 表格中不显示
            editHide: true // 编辑弹框中不显示
          },
          {
            label: "工程编码", //工程编码
            placeholder: "唯一标识",
            field: "supplyWaterProjectCode",
            editField: "supplyWaterProjectCode",
            inputType: "input",
            rules: [
              { min: 1, max: 10, message: "不超过16个字符", trigger: "blur" }
            ],
            disabled: "disableOnEdit"
          },
          {
            label: "工程名称", //工程名称
            placeholder: "",
            field: "supplyWaterProjectName",
            editField: "supplyWaterProjectName",
            inputType: "input",
            disabled: false
          },
          {
            label: "工程位置", //工程位置
            placeholder: "",
            field: "location",
            editField: "location",
            inputType: "input",
            disabled: false
          },
          {
            label: "省", //工程位置
            placeholder: "",
            field: "province",
            editField: "province",
            inputType: "input",
            disabled: false
          },
          {
            label: "市", //工程位置
            placeholder: "",
            field: "city",
            editField: "city",
            inputType: "input",
            disabled: false
          },
          {
            label: "县区", //工程位置
            placeholder: "",
            field: "county",
            editField: "county",
            inputType: "input",
            disabled: false
          },
          {
            label: "状态", //工程状态
            placeholder: "",
            field: "projectSituation",
            editField: "projectSituation",
            inputType: "input",
            disabled: false
          },
          {
            label: "是否竣工", //是否是竣工工程
            placeholder: "",
            field: "isFinishProject",
            editField: "isFinishProject",
            inputType: "input",
            disabled: false
          },
          {
            label: "工程投资总额", //工程投资总额
            placeholder: "",
            field: "totalInvest",
            editField: "totalInvest",
            inputType: "input",
            disabled: false
          },
          {
            label: "国家总投资", //国家总投资
            placeholder: "",
            field: "stateInvest",
            editField: "stateInvest",
            inputType: "input",
            disabled: false
          },
          {
            label: "水源类型", //水源类型
            placeholder: "",
            field: "waterSourceType",
            editField: "waterSourceType",
            inputType: "input",
            disabled: false
          },
          {
            label: "地表水源名", //地表水源名
            placeholder: "",
            field: "surfaceWaterName",
            editField: "surfaceWaterName",
            inputType: "input",
            disabled: false
          },
          {
            label: "水井个数", //水井个数
            placeholder: "",
            field: "wellNums",
            editField: "wellNums",
            inputType: "input",
            disabled: false
          },
          {
            label: "水源质量", //水源质量
            placeholder: "",
            field: "waterQuality",
            editField: "waterQuality",
            inputType: "input",
            disabled: false
          },
          {
            label: "开始供水时间", //开始供水时间
            placeholder: "",
            field: "startSupplyWaterTime",
            editField: "startSupplyWaterTime",
            inputType: "input",
            disabled: false
          },

          {
            label: "设计供水规模", //设计供水规模
            placeholder: "",
            field: "designSupplyScale",
            editField: "designSupplyScale",
            inputType: "input",
            disabled: false
          },

          {
            label: "实际供水规模", //实际供水规模
            placeholder: "",
            field: "actualSupplyWaterAnnual",
            editField: "actualSupplyWaterAnnual",
            inputType: "input",
            disabled: false
          },
          {
            label: "供水保证率", //供水保证率
            placeholder: "",
            field: "supplyWaterRate",
            editField: "supplyWaterRate",
            inputType: "input",
            disabled: false
          },
          {
            label: "总供水人数", //总供水人数
            placeholder: "",
            field: "totalNumsOfPeople",
            editField: "totalNumsOfPeople",
            inputType: "input",
            disabled: false
          },
          {
            label: "供水到户人数", //供水到户人数
            placeholder: "",
            field: "waterToHomeNums",
            editField: "waterToHomeNums",
            inputType: "input",
            disabled: false
          },         {
            label: "供水到贫困户人数",//供水到贫困户人数
            placeholder: "",
            field: "waterToPoorFamilyNums",
            editField: "waterToPoorFamilyNums",
            inputType: "input",
            disabled: false
          },
          {
            label: "供水到师生人数", //供水到师生人数
            placeholder: "",
            field: "waterToSchoolNums",
            editField: "waterToSchoolNums",
            inputType: "input",
            disabled: false
          },
          {
            label: "解决的饮水型氟超标人数", //解决的饮水型氟超标人数
            placeholder: "",
            field: "solvedExceedFuNums",
            editField: "solvedExceedFuNums",
            inputType: "input",
            disabled: false
          },
          {
            label: "工程负责人", //工程负责人
            placeholder: "",
            field: "supervisorName",
            editField: "supervisorName",
            inputType: "input",
            disabled: false
          },
          {
            label: "联系方式", //联系方式
            placeholder: "",
            field: "servicePhoneNumbers",
            editField: "servicePhoneNumbers",
            inputType: "input",
            disabled: false
          },
          {
            label: "管理部门名字", //管理部门名字
            placeholder: "",
            field: "manageDepartmentName",
            editField: "manageDepartmentName",
            inputType: "input",
            disabled: false
          },
          {
            label: "工程上级主管单位名称", //工程上级主管单位名称
            placeholder: "",
            field: "upManageDepartment",
            editField: "upManageDepartment",
            inputType: "input",
            disabled: false
          },
          {
            label: "管理单位性质", //管理单位性质
            placeholder: "",
            field: "departmentNature",
            editField: "departmentNature",
            inputType: "input",
            disabled: false
          },
          {
            label: "供水工程管理人数", //供水工程管理人数
            placeholder: "",
            field: "projectManageNums",
            editField: "projectManageNums",
            inputType: "input",
            disabled: false
          },
          {
            label: "管网漏损率", //管网漏损率
            placeholder: "",
            field: "pipeLackRate",
            editField: "pipeLackRate",
            inputType: "input",
            disabled: false
          },
          {
            label: "供水满意度", //供水满意度
            placeholder: "",
            field: "useWaterSatified",
            editField: "useWaterSatified",
            inputType: "input",
            disabled: false
          },
          {
            label: "全成本水价", //全成本水价
            placeholder: "",
            field: "fullCostWaterPrice",
            editField: "fullCostWaterPrice",
            inputType: "input",
            disabled: false
          },
          {
            label: "运行成本水价", //运行成本水价
            placeholder: "",
            field: "operateCostWaterPrice",
            editField: "operateCostWaterPrice",
            inputType: "input",
            disabled: false
          },
          {
            label: "居民生活执行水价", //居民生活执行水价
            placeholder: "",
            field: "householdWaterPrice",
            editField: "householdWaterPrice",
            inputType: "input",
            disabled: false
          },
          {
            label: "计费方式", //计费方式
            placeholder: "",
            field: "chargeMethod",
            editField:"chargeMethod",
            inputType: "input",
            disabled: false
          },
          {
            label: "收支情况", //收支情况
            placeholder: "",
            field: "incomeExpenditureSituation",
            editField: "incomeExpenditureSituation",
            inputType: "input",
            disabled: false
          },
          {
            label: "水费回收率", //水费回收率
            placeholder: "",
            field: "waterChargeRecoveryRate",
            editField: "waterChargeRecoveryRate",
            inputType: "input",
            disabled: false
          },

          {
            label: "是否建立水质保护区", //是否建立水质保护区
            placeholder: "",
            field: "isProtectArea",
            editField: "isProtectArea",
            inputType: "input",
            disabled: false
          },
          {
            label: "取水许可证", //取水许可证
            placeholder: "",
            field: "isWaterIntakeLegal",
            editField: "isWaterIntakeLegal",
            inputType: "input",
            disabled: false
          },
          {
            label: "卫生许可证", //卫生许可证
            placeholder: "",
            field: "isLegalInHygiene",
            editField: "isLegalInHygiene",
            inputType: "input",
            disabled: false
          },
          {
            label: "有无净化", //有无净化
            placeholder: "",
            field: "isPurified",
            editField: "isPurified",
            inputType: "input",
            disabled: false
          },
          {
            label: "有无消毒", //有无消毒
            placeholder: "",
            field: "isDisinfect",
            editField: "isDisinfect",
            inputType: "input",
            disabled: false
          },
          {
            label: "净化运行情况", //净化运行情况
            placeholder: "",
            field: "purifiedSituation",
            editField: "purifiedSituation",
            inputType: "input",
            disabled: false
          },

          {
            label: "消毒运行情况", //消毒运行情况
            placeholder: "",
            field: "disinfectSituation",
            editField: "disinfectSituation",
            inputType: "input",
            disabled: false
          },
          {
            label: "水质化实验室建立与否", //水质化实验室建立与否
            placeholder: "",
            field: "isBuildLab",
            editField: "isBuildLab",
            inputType: "input",
            disabled: false
          },
          {
            label: "实验室是否工作", //实验室是否工作
            placeholder: "",
            field: "isWork",
            editField: "isWork",
            inputType: "input",
            disabled: false
          },
          {
            label: "出厂水日检指标", //出厂水日检指标
            placeholder: "",
            field: "waterIndexOutFactory",
            editField: "waterIndexOutFactory",
            inputType: "input",
            disabled: false
          },
          {
            label: "水质检测结果", //水质结果
            placeholder: "",
            field: "waterQualityResult",
            editField: "waterQualityResult",
            inputType: "input",
            disabled: false
          },
          {
            label: "有计算机监控", //有计算机监控
            placeholder: "",
            field: "haveComputerMonitor",
            editField: "haveComputerMonitor",
            inputType: "input",
            disabled: false
          },
          {
            label: "有视频监控", //有视频监控
            placeholder: "",
            field: "haveVideoMonitor",
            editField: "haveVideoMonitor",
            inputType: "input",
            disabled: false
          },
          {
            label: "计算机监控运行与否", //计算机监控运行与否
            placeholder: "",
            field: "computerIsWork",
            editField: "computerIsWork",
            inputType: "input",
            disabled: false
          },
          {
            label: "视频监控运行与否", //视频监控运行与否
            placeholder: "",
            field: "videoIsWork",
            editField: "videoIsWork",
            inputType: "input",
            disabled: false
          },
          {
            label: "经度", //经度
            placeholder: "",
            field: "longitude",
            editField: "longitude",
            inputType: "input",
            disabled: false
          },
          {
            label: "纬度", //纬度
            placeholder: "",
            field: "latitude",
            editField: "latitude",
            inputType: "input",
            disabled: false
          },
          {
            label: "审核与否", //纬度
            placeholder: "",
            field: "approval",
            editField: "approval",
            inputType: "approval",
            disabled: false
          },
        ]
      },
      currentRow:{},
    };
  },
  created() {},
  mounted() {
    // this.updateColumns();
  },
  methods: {
    operateProject(type, prop) {

      if (prop&&(prop.msg.approval==null||prop.msg.approval==0)) {
        this.dialogVisibleSetProject = true;
        console.log("点击的是编辑")
        console.log("点击的id是： "+prop.msg.id)

        this.project = prop.msg;
        // console.log("this.project"+this.project.accessKey) 皆为null
        // console.log("prop.msg"+prop.msg.accessKey)
      }
      else if(prop.msg.approval==1) {
        this.dialogVisibleSetProject = false;
        //设置让编辑弹框不要出现
        //this.dialogVisibleSetProject=false;
        //并且要有提示弹框
        alert("该工程需要先审核，请联系管理员审核该工程");
      }else {
        this.dialogVisibleSetProject = true;
        console.log("点击的是新增")
        this.project = {};
      }
      this.$refs.EditProject.addOrEdit(this.project);
    },
    refreshList() {
      this.$refs.listPage.handleQueryForm("query");
    },
    //将id发送QRcode组件并且生成唯一二维码
    createQRcode(prop){
      this.dialogVisibleQRcode=true;
      this.currentRow=prop.msg;
      console.log("你目前操作的id是"+prop.msg.id);
      //传送数据给QRcode
      //让一点击就直接生成二维码
      // this.$refs.QRcode.MakeQrcode();

    },
    // updateColumns(columns){
    //   this.crudOption.columns=columns;
    // }
    createApproval(prop){
      this.dialogVisibleApproval=true;
      this.currentRow=prop.msg;
      console.log("你目前操作的id是"+prop.msg.id);
      //传送数据给QRcode
      //让一点击就直接生成二维码
      // this.$refs.QRcode.MakeQrcode();

    }

  }

}
</script>


