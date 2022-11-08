<template>
<!--  <el-dialog-->
<!--    :title="caseResultTitle"-->
<!--    :close-on-click-modal="false"-->
<!--    :visible.sync="visib"-->
<!--    width="70%"-->
<!--    :before-close="closeDialog"-->
<!--  >-->
<!--    <vue-json-editor-->
<!--      v-model="caseResultContent"-->
<!--      :show-btns="false"-->
<!--      :mode="'code'"-->
<!--      lang="zh"-->
<!--      class="my-editor"-->
<!--      @json-change="onJsonChange"-->
<!--      @json-save="onJsonSave"-->
<!--    />-->
<!--    <span slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="closeDialog"-->
<!--        >关闭</el-button-->
<!--        >-->
<!--      </span>-->
<!--  </el-dialog>-->
<!--  /**-->
<!--  @Author soup-->
<!--  @Date 2022/03/29-->
<!--  @Description  visible是否显示 Dialog，支持 .sync 修饰符 close-on-click-modal是否可以通过点击 modal 关闭 Dialog  before-close关闭前的回调，会暂停 Dialog 的关闭-->
<!--  **/-->
  <el-dialog align="center"
    :title="caseResultTitle"
    :close-on-click-modal="false"
    :visible.sync="visib"
      width="70%"
    :before-close="closeDialog"
  >

<!--    /**-->
<!--    @Author soup-->
<!--    @Date 2022/03/27-->
<!--    @Description 春节前修改的 	handleSelectionChange当选择项发生变化时会触发该事件-->
<!--    **/-->



    <p>{{caseResultContent}}</P>
<!--    查询表单-->
    <el-form  label-width="80px"  >
<!--      控制查询表单在一行-->
      <el-row>
        <el-col :span="6"  v-for="(col,index) in get_head()" :key="index">
          <el-form-item :label="col" ><!-- 输入框 -->
            <el-input v-model="test" @input="change()"  clearable placeholder="请输入内容"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item>
            <el-button type="primary">查询</el-button>
            <el-button type="danger">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>

    </el-form>



    <el-table
      border
      ref="multipleTable"
      :data="caseResultContent"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>

      <template v-for="(col,index) in get_head()">
          <el-table-column :label="col" :prop="col"></el-table-column>
      </template>
    </el-table>






<!--    /**-->
<!--    @Author soup-->
<!--    @Date 2022/03/29-->
<!--    @Description 原始代码-->
<!--    **/-->
<!--    <vue-json-editor-->
<!--      v-model="caseResultContent"-->
<!--      :show-btns="false"-->
<!--      :mode="'code'"-->
<!--      lang="zh"-->
<!--      class="my-editor"-->
<!--      @json-change="onJsonChange"-->
<!--      @json-save="onJsonSave"-->
<!--    />-->
<!--    <div style="margin-top: 20px">-->
<!--&lt;!&ndash;      <el-button @click="toggleSelection([tableData[1], tableData[2]])">切换第二、第三行的选中状态</el-button>&ndash;&gt;-->
<!--&lt;!&ndash;      <el-button @click="toggleSelection()">取消选择</el-button>&ndash;&gt;-->
<!--    </div>-->
<!--    <span slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="closeDialog"-->
<!--        >关闭</el-button-->
<!--        >-->
<!--    </span>-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="10"
      layout="total, sizes, prev, pager, next, jumper"
      :total="400">
    </el-pagination>


    <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="closeDialog"
        >关闭</el-button
        >
    </span>



  </el-dialog>
</template>
<script>
import vueJsonEditor from "vue-json-editor";

export default {

  name: 'Support',
  components: { vueJsonEditor },
  props: {
    visib: {
      required: true,
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      test:"hah",
      dialogCaseResult: false,
      caseResultTitle: "",
      caseResultContent: null,
      table_head: get_head(),
    }
  },
  computed:{

  },
  // 在生命周期 beforeCreate里面改变this指向
  beforeCreate: function () {},
  mounted() {},
  /**
  @Author soup
  @Date 2022/03/29
  @Description result/index.vue通过this.$refs.DataView.dataViewPreview 发送数据 组件通信 应该是全局事件总线 prop.msg.caseResult对数据进行了json处理，就是一个行数组
  **/
  methods: {
    dataViewPreview(caseResultTitle, caseResultContent){
      this.caseResultTitle = caseResultTitle
      this.caseResultContent = caseResultContent
    },
    // 关闭模态框
    closeDialog() {
      this.$emit('handleClose')
    },
    onJsonChange(value) {
    },
    onJsonSave(value) {},

    get_head(){
      // let s = eval(this.caseResultContent);
      let s = this.caseResultContent;
      let t = s[0];
      var arr = new Array();
      for(var i in t){
         arr.push(i)
      }
      //arr 存的是表头各字段名

      return arr;
    },
    handleSelectionChange(val) {
      // 选项框打勾的时候发生变化 val 是每行的数据
      console.log(val);
      this.multipleSelection = val;
    },
   test(){
     var s = eval(this.caseResultContent);
     for (let j = 0; j < s.length; j++) {
         let object = s[j];
         console.log(object);
         for(var i in object){
           console.log(i+" "+s[j][i]);
         }
         object=null;
     }
    },
    //强制刷新 不然input输入框无法输入数据
    change(){
      this.$forceUpdate();
    },
    // 页码改变
    handleCurrentChange(pageNumber) {
    },
    // 每页size改变时
    handleSizeChange(val) {

    },
  },

}
</script>
