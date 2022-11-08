<template>
  <el-dialog
    title="请选择不展示的列"
    width="50%"
    :close-on-click-modal="false"
    center
    :visible.sync="visib"
    :before-close="closeDialog"
  >
    <template>
      <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange" >全选</el-checkbox>
      <div style="margin: 15px 0;"></div>
      <el-checkbox-group v-model="checkedColumns" @change="handleCheckedCitiesChange" >
<!--        <el-checkbox v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox>-->
        <el-checkbox v-for="(column,index) in columns" :label="column.label" :key="index" checked @change="column.tableHide=!column.tableHide">
          {{column.label}}
        </el-checkbox>
<!--        <p>{{checkedCities}}</p>-->
      </el-checkbox-group>
    </template>
    <el-tag
      v-for="tag in this.checkedColumns"
      :key="tag.name"
      :type="tag.type">
      {{tag}}
    </el-tag>
    <div slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取消</el-button>
      <el-button type="primary" @click="test"
      >确定</el-button
      >
    </div>
  </el-dialog>
</template>
<script>


import Dictionary from "@/components/Dictionary/index";
const ColumnsOptions = ['id', '工程编码', '工程名称', '工程位置', '省', '市', '县区', '状态', '是否竣工', '工程投资总额', '国家总投资','水源类型', '地表水源名', '水井个数', '水源质量',
   '开始供水时间','设计供水规模','实际供水规模','供水保证率','总供水人数','供水到户人数','供水到贫困户人数','供水到师生人数','解决的饮水型氟超标人数','工程负责人','联系方式','管理部门名字' ,
  '工程上级主管单位名称','管理单位性质','供水工程管理人数','管网漏损率','供水满意度','全成本水价','运行成本水价','居民生活执行水价','计费方式','收支情况','水费回收率','是否建立水质保护区'
,'取水许可证','卫生许可证','有无净化','有无消毒','净化运行情况','消毒运行情况','水质化实验室建立与否','实验室是否工作','出厂水日检指标','水质检测结果','有计算机监控','有视频监控','计算机监控运行与否',
'视频监控运行与否','经度','纬度'];
export default {
  name: "Support",
  components: { Dictionary },
  props: {
    visib: {
      required: true,
      type: Boolean,
      default: false
    },
    columns: {},
  },
  data() {
    return {
      checkAll: false,
      checkedColumns: [],
      cities: ColumnsOptions,
      isIndeterminate: true
    };
  },
  // 在生命周期 beforeCreate里面改变this指向
  beforeCreate: function() {},
  mounted() {
    console.log("this.checkedCities"+this.checkedCities);
    this.test();
    //默认全选
  },
  created() {
  },
  methods: {
    handleCheckAllChange(val) {
      this.checkedColumns = val ? ColumnsOptions : [];
      for (let column of this.columns) {
        column.tableHide=true;
        // console.log("column"+column.tableHide);
      }
      this.isIndeterminate = false;
    },
    handleCheckedCitiesChange(value) {
      console.log("value"+value)
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.cities.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
    },
    test(){
      this.$emit("handleClose");
      console.log("this.columns"+this.columns[1].tableHide);
    },

    // 关闭模态框
    closeDialog() {
      this.$emit("handleClose");
    },

  },
  computed:{

  },
};
</script>
