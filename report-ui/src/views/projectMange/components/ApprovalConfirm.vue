<template>
  <el-dialog
    title="工程审核"
    width="50%"
    :close-on-click-modal="false"
    center
    :visible.sync="visib"
    :append-to-body='true'
    :before-close="closeDialog"
  >
    <div>
      <el-descriptions class="margin-top"  title="   " label-class-name="mybg" :column="3"  border>
        <el-descriptions-item label-class-name="mybg">
          <template slot="label">
            <i class="el-icon-user"></i>
            工程名字
          </template>
          {{this.Approvalrow.supplyWaterProjectName}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            服务电话
          </template>
          {{this.Approvalrow.servicePhoneNumbers}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-location-outline"></i>
            工程地址
          </template>
          {{this.Approvalrow.location}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-location-outline"></i>
            填报年份
          </template>
          {{this.Approvalrow.writeTime}}
        </el-descriptions-item>
        <el-descriptions-item v-if="this.Approvalrow.projectSituation=='已建'">
          <template slot="label">
            <i class="el-icon-tickets"></i>
            工程状态
          </template>
          <el-tag size="small">{{this.Approvalrow.projectSituation}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item v-else-if="this.Approvalrow.projectSituation=='在建'">
          <template slot="label">
            <i class="el-icon-tickets"></i>
            工程状态
          </template>
          <el-tag size="small" type="warning">{{this.Approvalrow.projectSituation}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item v-else="this.Approvalrow.projectSituation">
          <template slot="label">
            <i class="el-icon-tickets"></i>
            工程状态
          </template>
          <el-tag size="small" type="danger">{{this.Approvalrow.projectSituation}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            地表水源名
          </template>
          {{this.Approvalrow.surfaceWaterName}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            设计供水量
          </template>
          {{this.Approvalrow.designSupplyScale}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            年实际供水量
          </template>
          {{this.Approvalrow.actualSupplyWaterAnnual}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            管网漏损率
          </template>
          {{this.Approvalrow.pipeLackRate}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            供水保证率(%)
          </template>
          {{this.Approvalrow.supplyWaterRate}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            工程总投资(万)
          </template>
          {{this.Approvalrow.totalInvest}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            国家投资(万)
          </template>
          {{this.Approvalrow.stateInvest}}
        </el-descriptions-item>
        <el-descriptions-item v-if="this.Approvalrow.startSupplyWaterTime">
          <template slot="label">
            <i class="el-icon-tickets"></i>
            开始供水时间
          </template>
          {{this.Approvalrow.startSupplyWaterTime.substring(0,10)}}
        </el-descriptions-item>
        <el-descriptions-item v-else>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            开始供水时间
          </template>
          {{this.Approvalrow.startSupplyWaterTime}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            是否竣工
          </template>
          {{this.Approvalrow.isFinishProject}}
        </el-descriptions-item>
      </el-descriptions>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取消</el-button>
      <el-button @click="confirm">通过</el-button>
<!--      <el-button type="primary" @click="show">展示二维码</el-button>-->
    </div>
  </el-dialog>
</template>

<script>
import {projectAdd, projectDetail, projectUpdate} from "@/api/projectManage";

export default {
  name: "Approval",
  props: {
    visib: {
      required: true,
      type: Boolean,
      default: false
    },
    Approvalrow:{},
  },
  data() {
    return {

    };
  },
  // 在生命周期 beforeCreate里面改变this指向
  beforeCreate: function() {},

  beforeMount() {

  },
  mounted() {

    //默认全选
  },
  created() {

  },
  methods: {


    // 关闭模态框
    closeDialog() {
      this.$emit("handleClose");
    },
    //点击确定表明审核通过，就去修改该spproval变量的值
    async confirm(){
      if(this.Approvalrow.id){
        this.Approvalrow.approval = 0;
        const { code, data } = await projectUpdate(this.Approvalrow);
        if (code != "200"){
          alert("该工程未通过审核");
          return
        };
        alert("该工程已经通过审核");
      }
    }


    // 清除二维码
  },
  computed:{

  },
}
</script>

<style scoped>

</style>
