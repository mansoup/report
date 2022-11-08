<template>
  <el-dialog
    title="工程二维码"
    width="50%"
    :close-on-click-modal="false"
    center
    :visible.sync="visib"
    :append-to-body='true'
    :before-close="closeDialog"
  >
    <div ref="qrcode" style="width:100px; height:100px; margin-top:15px;margin: 0 auto" ></div>
    <div slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取消</el-button>
      <el-button @click="clear">清除二维码</el-button>
      <el-button type="primary" @click="show"
      >展示二维码</el-button
      >
    </div>
  </el-dialog>
</template>
<script>


import Dictionary from "@/components/Dictionary/index";
//引入二维码
import QRCode from 'qrcodejs2'

export default {
  name: "Support",
  components: { Dictionary },
  props: {
    visib: {
      required: true,
      type: Boolean,
      default: false
    },
    crow:{},
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

    MakeQrcode(){
      new QRCode(this.$refs.qrcode, {
        text: "http://101.35.226.248:9528/#/report/projects/QReditable/"+this.crow.id+"/"+this.crow.accessKey, // 扫码后页面地址http://127.0.0.1:9095/back/waterProject/17?accessKey=324ccb00a90bfd05f5bb70dcc28766e6
        // text:"http://192.168.10.3:9528/#/login",
        width: 128, // 二维码宽度
        height: 128, // 二维码高度
        colorDark: "#000000", // 二维码颜色
        colorLight: "#ffffff", // 背景颜色
        correctLevel: QRCode.CorrectLevel.H, // 校正水准
      });

    },
    show() {
      if(this.$refs.qrcode.innerHTML==""){
        this.$nextTick(() => {
          this.MakeQrcode();
        });
      }


     },
    // 清除二维码
    clear() {
      this.$refs.qrcode.innerHTML = "";
    },
    },
  computed:{

  },
};
</script>
