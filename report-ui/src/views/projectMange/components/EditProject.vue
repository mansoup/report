<template>
  <el-dialog
    title="水利项目管理"
    width="50%"
    :close-on-click-modal="false"
    center
    :visible.sync="visib"
    :before-close="closeDialog"
  >
    <el-form
      ref="userForm"
      :model="dialogForm"
      :rules="rules"
      size="small"
      label-width="100px"
    >
      <el-row :gutter="10">
        <el-col :span="8">
          <el-form-item label="工程编码" prop="supplyWaterProjectCode">
            <el-input
              :disabled="true"
              v-model.trim="dialogForm.supplyWaterProjectCode"
              placeholder="唯一标识"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="工程名称" prop="supplyWaterProjectName">
            <el-input v-model.trim="dialogForm.supplyWaterProjectName" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="工程位置" prop="location">
<!--            <el-cascader-->
<!--              size="large"-->
<!--              :options="options"-->
<!--              v-model="dialogForm.location"-->
<!--              @change="handleChange">-->
<!--            </el-cascader>-->
            <el-input v-model.trim="dialogForm.location" />
          </el-form-item>
        </el-col>
      </el-row>


      <el-row :gutter="10" style="magrin:0 atuo">
        <el-select v-model="dialogForm.province" placeholder="省份" @change="selectProvimce" >
          <el-option
            v-for="(item,index) of provincearr"
            :key="index"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
        <el-select v-model="dialogForm.city" placeholder="市" @change="selectcity">
          <el-option v-for="(item,index) of cityarr" :key="index" :label="item.name" :value="item.id"></el-option>
        </el-select>
        <el-select placeholder="区县" v-model="dialogForm.county" @change="selectCounty">
          <el-option
            v-for="(item,index) of regionarr"
            :key="index"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-row>



      <el-row :gutter="10">
        <el-col :span="6">
          <el-form-item label="工程状态" prop="projectSituation">
            <el-select v-model="dialogForm.projectSituation" placeholder="请选择">
              <el-option value="已建">已建</el-option>
              <el-option value="在建">在建</el-option>
              <el-option value="其它">其它</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="是否竣工" prop="isFinishProject">
            <el-select v-model="dialogForm.isFinishProject" placeholder="请选择">
              <el-option value="是">是</el-option>
              <el-option value="否">否</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="工程投资总额(万)" prop="totalInvest">
            <el-input v-model.trim="dialogForm.totalInvest" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="国家总投资(万)" prop="stateInvest">
            <el-input v-model.trim="dialogForm.stateInvest" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="6">
          <el-form-item label="水源类型" prop="waterSourceType">
            <el-select v-model="dialogForm.waterSourceType" placeholder="请选择">
              <el-option value="地表水">地表水</el-option>
              <el-option value="地下水">地下水</el-option>
              <el-option value="联合水源">联合水源</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="地表水源名" prop="surfaceWaterName">
            <el-input v-model.trim="dialogForm.surfaceWaterName" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="水井个数" prop="wellNums">
            <el-input v-model.trim="dialogForm.wellNums" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="水源质量" prop="waterQuality">
            <el-select v-model="dialogForm.waterQuality" placeholder="请选择">
              <el-option value="优">优</el-option>
              <el-option value="良">良</el-option>
              <el-option value="差">差</el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="6">
          <el-form-item label="设计供水规模(m³/d)" prop="designSupplyScale">
            <el-input
              v-model.trim="dialogForm.designSupplyScale"/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="实际供水规模(m³/d)" prop="actualSupplyWaterAnnual">
            <el-input v-model.trim="dialogForm.actualSupplyWaterAnnual" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="开始供水时间" prop="startSupplyWaterTime">
<!--            <el-input v-model.trim="dialogForm.startSupplyWaterTime" />-->
            <el-date-picker
              v-model.trim="dialogForm.startSupplyWaterTime"
              type="date"
              placeholder="选择日期时间">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="6">
          <el-form-item label="总供水人数(个人)" prop="totalNumsOfPeople">
            <el-input
              v-model.trim="dialogForm.totalNumsOfPeople"/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="供水到户人数(个人)" prop="waterToHomeNums">
            <el-input v-model.trim="dialogForm.waterToHomeNums" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="供水到贫困户(个人)" prop="waterToPoorFamilyNums">
            <el-input v-model.trim="dialogForm.waterToPoorFamilyNums" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="供水到师生(个人)" prop="waterToSchoolNums">
            <el-input v-model.trim="dialogForm.waterToSchoolNums" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="8">
          <el-form-item label="用水满意度(%)" prop="useWaterSatified">
            <el-input v-model.trim="dialogForm.useWaterSatified" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="管网漏损率(%)" prop=" pipeLackRate">
            <el-input v-model.trim="dialogForm.pipeLackRate" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="供水保证率(%)" prop="supplyWaterRate">
            <el-input v-model.trim="dialogForm.supplyWaterRate" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">

        <el-col :span="8">
          <el-form-item label="负责人名字" prop="supervisorName">
            <el-input v-model.trim="dialogForm.supervisorName" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="负责人号码" prop="servicePhoneNumbers">
            <el-input v-model.trim="dialogForm.servicePhoneNumbers" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="6">
          <el-form-item label="管理部门名字" prop="manageDepartmentName">
            <el-input
              v-model.trim="dialogForm.manageDepartmentName"/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="管理单位性质" prop="departmentNature">
            <el-input v-model.trim="dialogForm.departmentNature" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="工程管理人数" prop="projectManageNums">
            <el-input v-model.trim="dialogForm.projectManageNums" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="上级主管单位" prop="upManageDepartment">
            <el-input v-model.trim="dialogForm.upManageDepartment" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="8">
          <el-form-item label="全成本水价(元/m³)" prop="fullCostWaterPrice">
            <el-input
              v-model.trim="dialogForm.fullCostWaterPrice"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="运行成本价(元/m³)" prop="operateCostWaterPrice">
            <el-input v-model.trim="dialogForm.operateCostWaterPrice" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="执行水价(元/m³)" prop="householdWaterPrice">
            <el-input v-model.trim="dialogForm.householdWaterPrice" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="8">
          <el-form-item label="计费方式" prop="chargeMethod">
            <el-select v-model="dialogForm.chargeMethod" placeholder="请选择">
              <el-option value="计量收费">计量收费</el-option>
              <el-option value="固定收费">固定收费</el-option>
              <el-option value="不收费">不收费</el-option>
              <el-option value="其他">其他</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="收支情况" prop="incomeExpenditureSituation">
            <el-select v-model="dialogForm.incomeExpenditureSituation" placeholder="请选择">
              <el-option value="收支平衡">收支平衡</el-option>
              <el-option value="收不抵支">收不抵支</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="水费回收率(%)" prop="waterChargeRecoveryRate">
            <el-input v-model.trim="dialogForm.waterChargeRecoveryRate" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="8">
          <el-form-item label="是否建立水源保护" prop="isProtectArea">
            <el-select v-model="dialogForm.isProtectArea" placeholder="请选择">
              <el-option value="有">有</el-option>
              <el-option value="无">无</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="取水许可证" prop="isWaterIntakeLegal">
            <el-select v-model="dialogForm.isWaterIntakeLegal" placeholder="请选择">
              <el-option value="有">有</el-option>
              <el-option value="无">无</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="卫生许可证" prop="isLegalInHygiene">
            <el-select v-model="dialogForm.isLegalInHygiene" placeholder="请选择">
              <el-option value="有">有</el-option>
              <el-option value="无">无</el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="6">
          <el-form-item label="有无净化" prop="isPurified">
            <el-select v-model="dialogForm.isPurified" placeholder="请选择">
              <el-option value="有">有</el-option>
              <el-option value="无">无</el-option>
            </el-select>
<!--            <el-input-->
<!--              v-model.trim="dialogForm.isPurified"/>-->
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="有无消毒" prop="isDisinfect">
<!--            <select v-model="dialogForm.isDisinfect">-->
<!--              <option value="有">有</option>-->
<!--              <option value="无">无</option>-->
<!--            </select>-->
            <el-select v-model="dialogForm.isDisinfect" placeholder="请选择">
              <el-option value="有">有</el-option>
              <el-option value="无">无</el-option>
            </el-select>
<!--            <el-input v-model.trim="dialogForm.isDisinfect" />-->
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="净化运行情况" prop="purifiedSituation">
            <el-select v-model="dialogForm.purifiedSituation" placeholder="请选择">
              <el-option value="正常">正常</el-option>
              <el-option value="异常">异常</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="消毒运行情况" prop="disinfectSituation">
            <el-select v-model="dialogForm.disinfectSituation" placeholder="请选择">
              <el-option value="正常">正常</el-option>
              <el-option value="异常">异常</el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="6">
          <el-form-item label="水质化实验室" prop="isBuildLab">
            <el-select v-model="dialogForm.isBuildLab" placeholder="请选择">
              <el-option value="有">有</el-option>
              <el-option value="无">无</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="是否工作" prop="isWork">
            <el-select v-model="dialogForm.isWork" placeholder="请选择">
              <el-option value="是">是</el-option>
              <el-option value="否">否</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="出厂日检指标" prop="waterIndexOutFactory">
            <el-input v-model.trim="dialogForm.waterIndexOutFactory" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="水质检测结果" prop="waterQualityResult">
            <el-input v-model.trim="dialogForm.waterQualityResult" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="6">
          <el-form-item label="有计算机监控" prop="haveComputerMonitor">
            <el-select v-model="dialogForm.haveComputerMonitor" placeholder="请选择">
              <el-option value="有">有</el-option>
              <el-option value="无">无</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="计算机运行与否" prop="computerIsWork">
            <el-select v-model="dialogForm.computerIsWork" placeholder="请选择">
              <el-option value="运行">运行</el-option>
              <el-option value="故障">故障</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="有视频监控" prop="haveVideoMonitor">
            <el-select v-model="dialogForm.haveVideoMonitor" placeholder="请选择">
              <el-option value="有">有</el-option>
              <el-option value="无">无</el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="6">
          <el-form-item label="监控运行与否" prop="videoIsWork">
            <el-select v-model="dialogForm.videoIsWork" placeholder="请选择">
              <el-option value="运行">运行</el-option>
              <el-option value="故障">故障</el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-upload  class="avatar-uploader"
                      ref="upload"
                      :action="uploadUrl"
                      :headers="headers"
                      :data="{rowid:rowId}"
                      :on-success="handleUpload"
                      :on-error="handleError"
                      :show-file-list="false"
                      :limit="1">
            <el-image style="width: 300px; height: 300px" :src="imgurl" >
              </el-image>
            <el-button
              type="primary"
              icon="el-icon"
              v-permission="'fileManage:upload'"
            >文件上传<i class="el-icon-upload el-icon--right"></i></el-button
            >
          </el-upload>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="6">
        <el-button @click="test"  type="danger">点击获取经纬度</el-button>
        <!--        <el-dialog ref="baiduDialog" :visible="badiudialogVisible"></el-dialog>-->
        <baidu-dialog  :visible.sync="badiudialogVisible" @func="getLocation"></baidu-dialog>
        </el-col>
        <el-col :span="6">
          <el-form-item label="经度" prop="longitude">
            <el-input
              v-model.trim="dialogForm.longitude"/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="纬度" prop="latitude">
            <el-input v-model.trim="dialogForm.latitude" />
          </el-form-item>
        </el-col>
      </el-row>

    </el-form>

    <div slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取消</el-button>
      <el-button type="primary" @click="Submit('userForm')">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>

import {projectAdd, projectDetail, projectUpdate} from "@/api/projectManage";
import {validateEngOrNum} from "@/utils/validate";
import baiduDialog from "./baiduDialog";
import {province,city,region} from "@/api/areaData";
import { nanoid } from 'nanoid' //导入生成唯一id为工程编号
import { fileList, fileAdd, fileDel, fileUpdate, fileDetail } from "@/api/file";
import { getToken } from "@/utils/auth";
// import { provinceAndCityData, regionData, provinceAndCityDataPlus, regionDataPlus, CodeToText, TextToCode } from 'element-china-area-data'
export default {
  name: "EditProject",
  components: {
    baiduDialog
  },

  props: {
    visib: {
      required: true,
      type: Boolean,
      default: false
    },
    project: {
      required: false,
      type: Object,
      //默认接收值？
      default: () => {
        return "";
      }
    }
  },

  data() {
    return {
      dialogForm: {
        supplyWaterProjectCode: "",
        supplyWaterProjectName: "",
        location:"",
        province:"",
        city:"",
        county:"",
        projectSituation:"",
        isFinishProject:"",
        totalInvest:"",
        stateInvest:"",
        waterSourceType:"",
        surfaceWaterName:"",
        wellNums:"",
        waterQuality:"",
        designSupplyScale:"",
        actualSupplyWaterAnnual:"",
        startSupplyWaterTime:"",
        supplyWaterRate:"",
        totalNumsOfPeople:"",
        waterToHomeNums:"",
        waterToPoorFamilyNums:"",
        waterToSchoolNums:"",
        solvedExceedFuNums:"",//暂时没使用
        supervisorName:"",
        servicePhoneNumbers:"",
        manageDepartmentName:"",
        departmentNature:"",
        projectManageNums:"",
        upManageDepartment:"",
        pipeLackRate:"",
        useWaterSatified:"",
        fullCostWaterPrice:"",
        operateCostWaterPrice:"",
        householdWaterPrice:"",
        chargeMethod:"",
        incomeExpenditureSituation:"",
        waterChargeRecoveryRate:"",
        isProtectArea:"",
        isWaterIntakeLegal:"",
        isLegalInHygiene:"",
        isPurified:"",
        isDisinfect:"",
        purifiedSituation:"",
        disinfectSituation:"",
        isBuildLab:"",
        isWork:"",
        waterIndexOutFactory:"",
        waterQualityResult:"",
        haveComputerMonitor:"",
        haveVideoMonitor:"",
        computerIsWork:"",
        videoIsWork:"",
        longitude:"",
        latitude:"",
      },
      //请求的工程id为了上传图片时候修改对应的imgurl
      rowId:'',
      uploadUrl: process.env.BASE_API + "/waterProject/upload/",//上传路径
      badiudialogVisible:false,
      arr: [1, 3, 4],
      province: [],
      cityarr: [],
      regionarr: [],
      provinceValue: '',
      cityValue: '',
      RegionValue: '',
      imgurl:'',
      // options: regionData,

      rules: {
        // supplyWaterProjectCode:[
        //   { required: true, message: "工程编码必填", trigger: "blur" },
        //   { validator: validateEngOrNum, trigger: "blur" }
        // ],
        supplyWaterProjectName: [
          { required: true, message: "工程名称必填", trigger: "blur" }
        ]
      },
      updataDisabled: false,
    };
  },
  computed: {
    headers() {
      return {
        Authorization: getToken() // 直接从本地获取token就行 设置上传的请求头部
      };
    }
  },
  created() {
    this.provincearr = province;
    console.log("this.provincearr"+this.provincearr)
  },
  methods: {
    // handleChange (value) {
    //   console.log(value)
    // },
    getLocation(data){
      console.log("data"+data);
        this.dialogForm.latitude = data.mylatitude;
        this.dialogForm.longitude = data.mylongitude;
        console.log("父组件latitude值"+this.dialogForm.latitude);
        console.log("父组件latitude值"+this.dialogForm.longitude);
    },
     test(){
       console.log("前"+this.badiudialogVisible),
        this.badiudialogVisible=true;
       console.log("后"+this.badiudialogVisible)
     },
     async addOrEdit(row) {
      console.log("row"+row);//object object
      console.log("去查的id: "+row.id);
      console.log("data.accessKey"+row.accessKey);//为什么为空呢？
      if(row.id) {

        const { code, data } = await projectDetail(row);//api 根据某一id查找工程
        // //给rowid赋值
        this.rowId = data.id;
        console.log("data-----"+data)
        if (code != "200") return;
        this.dialogForm = data;
      } else {
        this.dialogForm = {
          supplyWaterProjectCode: "",
          supplyWaterProjectName: "",
          projectSituation:"",
          isFinishProject:"",
          province:"",
          city:"",
          county:"",
          totalInvest:"",
          stateInvest:"",
          waterSourceType:"",
          surfaceWaterName:"",
          wellNums:"",
          waterQuality:"",
          designSupplyScale:"",
          actualSupplyWaterAnnual:"",
          startSupplyWaterTime:"",
          supplyWaterRate:"",
          totalNumsOfPeople:"",
          waterToHomeNums:"",
          waterToPoorFamilyNums:"",
          waterToSchoolNums:"",
          solvedExceedFuNums:"",//暂时没使用
          supervisorName:"",
          phone:"",
          manageDepartmentName:"",
          departmentNature:"",
          projectManageNums:"",
          upManageDepartment:"",
          pipeLackRate:"",
          useWaterSatified:"",
          fullCostWaterPrice:"",
          operateCostWaterPrice:"",
          householdWaterPrice:"",
          chargeMethod:"",
          incomeExpenditureSituation:"",
          waterChargeRecoveryRate:"",
          isProtectArea:"",
          isWaterIntakeLegal:"",
          isLegalInHygiene:"",
          isPurified:"",
          isDisinfect:"",
          purifiedSituation:"",
          disinfectSituation:"",
          isBuildLab:"",
          isWork:"",
          waterIndexOutFactory:"",
          waterQualityResult:"",
          haveComputerMonitor:"",
          haveVideoMonitor:"",
          computerIsWork:"",
          videoIsWork:"",
          longitude:"",
          latitude:"",
          approval:"",
        };
      }
    },
    // 关闭模态框
    closeDialog() {
      this.$emit("handleClose");
    },
    // 提交
    async Submit(formName) {
      this.$refs[formName].validate(async (valid, obj) => {
        if (valid) {

          if (this.dialogForm.id == undefined) {
            const { code } = await projectAdd (this.dialogForm);//api 增加一个工程
            if (code != "200") return;
            //子组件向父组件传值，提示刷新表格
            this.$emit("refreshList");
          } else {
            //这里因为修改了工程就需要修改审核字段
            this.dialogForm.approval=1;
            const { code } = await projectUpdate(this.dialogForm);//api 更新某一工程
            if (code != "200") return;
            this.$emit("refreshList");
          }
          this.closeDialog();
        } else {
          return;
        }
      });
    },
    selectProvimce (id) {
      this.cityarr = [];
      this.regionarr = [];
      this.cityValue = '';
      this.RegionValue = '';
      for (let item of this.provincearr) {
        if (id == item.id) {
          this.dialogForm.province=item.name;
          console.log("this.dialogForm.province"+this.dialogForm.province)
          console.log(item)
          this.cityarr = item.children
        }
      }
    },
    selectcity (id) {
      this.regionarr = [];
      this.RegionValue = '';
      for (let item of this.cityarr) {
        if (id == item.id) {
          this.dialogForm.city=item.name;
          this.regionarr = item.children
        }
      }

    },
    selectCounty(id){
      for (let item of this.regionarr) {
        if (id == item.id) {
          this.dialogForm.county=item.name;
          let mynanoid = nanoid(10);//十六个够了吧
          if(this.dialogForm.supplyWaterProjectCode.substring(0,6)!=id){
            this.dialogForm.supplyWaterProjectCode=id+mynanoid;
          }
        }
      }
      console.log("countyid"+id)
    },

    // 上传成功的回调
    handleUpload(response, file, fileList) {
      console.log(response.data.urlPath);
      console.log(file);
      console.log(fileList);
      // 触发查询按钮
      // this.$refs.listPage.handleQueryForm();
      //清除el-upload组件中的文件
      this.imgurl=response.data.urlPath;
      // console.log(this.imgurl);
      this.$refs.upload.clearFiles();
    },
    handleError() {
      this.$message({
        message: "上传失败！",
        type: "error"
      });
    },
    async downloadFile(row) {
      //其实是预览，打开新标签页查看
      window.open(row.urlPath);
    },
    customButtom(val) {
      this.downloadFile(val.msg);
    },
    copyUrlPath(val) {
      this.copyToClip(val.msg.urlPath);
      this.$message({
        message: "已将url路径复制至剪切板！",
        type: "success"
      });
    },
    copyToClip(content, message) {
      var aux = document.createElement("input");
      aux.setAttribute("value", content);
      document.body.appendChild(aux);
      aux.select();
      document.execCommand("copy");
      document.body.removeChild(aux);
    }

  }

  }
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
