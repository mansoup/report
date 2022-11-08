<!--
 * @Author: qianlishi
 * @Date: 2020-07-13 11:04:24
 * @Last Modified by:   qianlishi
 * @Last Modified time: 2020-07-13 11:04:24
 !-->
<template>
  <div class="login_container">
    <!-- 顶部logo -->
    <div class="login_title">
      <img src="@/assets/images/home-logo1.png" alt="logo" />
    </div>
    <div class="login_contant">
      <img src="@/assets/images/login.jpg" alt="image" class="login_img" />
<!--      @soup 2022/1/6  增加注释 <el-form>登陆界面那个表单  rules用于表单验证 autocomplete自动补全：就是之前输入框输入过密码下次会自动补全 label-position密码和用户名对齐位置
    @keyup.enter.native 点击enter就跳转到提交密码-->
      <el-form
        ref="loginForm"
        :model="loginForm"
        :rules="loginRules"
        class="login_form"
        autocomplete="on"
        label-position="left"
        @keyup.enter.native="handleLogin"
      >
        <div class="title_container">
          <h3 class="title">
            <p class="title_name">辽宁省水利厅在线大屏管理系统</p>
          </h3>
        </div>
        <div class="form_fields">
          <!-- 黄色条条 -->
          <i :style="{ top: activeTop + '%' }" />
          <!-- 中间条条 -->
          <b />
<!--          @focus获取输入框的焦点 一般是更新下拉框,每次打开会请求后端方法 @change:就是你要联动的方法-->
          <div>
            <p>用户名</p>
            <el-form-item prop="loginName">
              <el-input
                ref="loginName"
                v-model="loginForm.loginName"
                placeholder="用户名"
                name="loginName"
                type="text"
                tabindex="1"
                autocomplete="on"
                @focus="setTop('0')"
                @change="getPsw"
              />
            </el-form-item>
          </div>
          <div>
            <p>密码</p>
            <input
              name="password"
              type="password"
              autocomplete="off"
              class="take"
            />
            <!--
            @Author soup
            @Date 2022/03/14
            @Description <el-tooltip>是对输入框进行提示的，比如说下面是对密码输入提示大小写 当caps lock is on ，manual 手动控制模式，设置为 true 后，mouseenter 和 mouseleave 事件将不会生效，
            也就是说 鼠标聚焦到input输入框不会提示caps lock is on 只有手动打开大写才会提示
            -->
            <el-tooltip
              v-model="capsTooltip"
              content="Caps lock is On"
              placement="right"
              manual
            >
              <el-form-item prop="password">
                <el-input
                  :key="passwordType"
                  ref="password"
                  v-model="loginForm.password"
                  :type="passwordType"
                  placeholder="用户密码"
                  name="password"
                  tabindex="2"
                  autocomplete="on"
                  @blur="capsTooltip = false"
                  @focus="setTop('50')"
                  @keyup.native="checkCapslock"
                />
<!--             @soup 2022/1/8   登录界面眼睛的图标-->
                <span class="show_pwd" @click="showPwd">
                  <i class="el-icon-view" />
                </span>
              </el-form-item>
            </el-tooltip>
          </div>
        </div>
        <div class="control">
          <div class="remember">
            <input v-model="rememberPsw" type="checkbox" />
            <p>记住密码</p>
          </div>
        </div>
<!--        当一个 form 元素中只有一个输入框时，在该输入框中按下回车应提交该表单。如果希望阻止这一默认行为，可以在 <el-form> 标签上添加 @submit.native.prevent  :loading 登录过程那个转圈圈的-->
        <el-button
          :loading="loading"
          type="primary"
          class="login_btn"
          @click.native.prevent="handleLogin"
          >登录</el-button
        >
      </el-form>
    </div>
    <!--  验证码  -->
    <Verify
      v-if="needCaptcha"
      ref="verify"
      :captcha-type="'blockPuzzle'"
      :img-size="{ width: '400px', height: '200px' }"
      @success="verifylogin"
    />
  </div>
</template>

<script>
import Verify from "@/components/verifition/Verify";
import cookies from "js-cookie";
import { Decrypt, Encrypt } from "@/utils/index";
import { login } from "@/api/login";
import { transPsw } from "@/utils/encrypted";
import { setToken, setAccessUser } from "@/utils/auth";
export default {
  name: "Login",
  components: {
    Verify
  },
  data() {
    return {
      activeTop: "-50%", // 色条滑块控制
      rememberPsw: false, // 记住密码选择框
      loginForm: {
        loginName: "",
        password: "",
        verifyCode: ""
      }, // 登录表单
      loginRules: {
        loginName: [{ required: true, message: "用户名必填", trigger: "blur" }],//@soup 2022/1/6 增加注释 鼠标焦点不在输入框提示警告信息
        password: [{ required: true, message: "用户密码必填", trigger: "blur" }]
      }, // 登录表单验证
      passwordType: "password", // 用来控制查看密码操作时的输入框类型
      capsTooltip: false, // 键盘大写是否打开
      loading: false, // 登录loding
      redirect: undefined, // 记录重定向地址
      otherQuery: {}, // 记录重定向地址中的参数
      needCaptcha: false
    };
  },
  watch: {
    $route: {
      // 监听路由获取上个路由（from）的地址和参数
      handler: function(route) {
        const query = route.query;
        if (query) {
          this.redirect = query.redirect;
          this.otherQuery = this.getOtherQuery(query);
        }
      },
      immediate: true
    }
  },
  mounted() {
    // 获取焦点
    if (this.loginForm.loginName === "") {
      this.$refs.loginName.focus();
    } else if (this.loginForm.password === "") {
      this.$refs.password.focus();
    }
  },
  methods: {
    // 获取存储的密码并解密
    getPsw() {
      const cookVal = cookies.get(`u_${this.loginForm.loginName}`);
      this.loginForm.password = cookVal && Decrypt(cookVal);
    },
    // 滑动条块的top控制
    setTop(val) {
      // console.log(val) 0和50来回跳转
      this.activeTop = val;
    },
    // 检测大写锁定键是否开启
    checkCapslock(e) {
      const { key } = e;
      this.capsTooltip = key && key.length === 1 && key >= "A" && key <= "Z";
    },
    // 查看密码
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
    /**
    @Author soup
    @Date 2022/03/14
    @Description  滑动验证码 经测试好像没有完成
     **/
    useVerify() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.$refs.verify.show();
        } else {
          return false;
        }
      });
    },
    // 验证成功的回调
    verifylogin(params) {
      this.loginForm.verifyCode = params.captchaVerification;
      if (this.loginForm.verifyCode) {
        this.loginApi();
      }
    },
    /**
    @Author soup
    @Date 2022/3/14
    @Description this.$refs.loginForm调用loginForm的方法 validete 是element ui 提供的表单验证的方法
    **/
    // 登录操作
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          // 登录失败次数过多需要展示滑动验证码
          if (this.needCaptcha) {//captcha是验证码意思
            this.useVerify();
            return;
          }
          // console.log(this.loginApi());//promise对象
          // console.log(valid);//只有ture 和false 是表单验证的就是有没有填写正确格式
          this.loginApi();
        } else {
          return false;
        }
      });
    },
    /**
    @Author soup
    @Date 2022/03/14
    @Description transPsw 加密密码
    **/
    async loginApi() {
      let obj = {
        loginName: this.loginForm.loginName,
        password: transPsw(this.loginForm.password),//将密码加密
        verifyCode: ""
      };
      // console.log(obj);
      const { code, data } = await login(obj);
      this.loading = false;
      // console.log("code"+code); //失败 login.error
      if (code != "200") return;
      setToken(data.token);
      setAccessUser(data);
      // 选中记住密码时 把密码存到cookie里,时效15天
      this.rememberPsw &&
        cookies.set(
          `u_${this.loginForm.loginName}`,
          Encrypt(this.loginForm.password),
          { expires: 15 }
        );
      /**
      @Author soup
      @Date 2022/03/14
      @Description 后端返回的数据
       GaeaUserDto(loginName=admin, password=null, realName=管理员, token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1dWlkIjoiYTg2NjFmZTEyYjU4NDdmOGI0MDIzMGZkZTJjNDE0YzIiLCJ1c2VybmFtZSI6ImFkbWluIn0.-fDxCFgt7t8zh4taqezy4Y_6SjU2WLMMceQ26fda9GI,
       authorities=[datasourceManage:query, resultsetManage:query, reportManage:query, bigScreenManage:share, bigScreenManage:view, datasourceManage:insert, datasourceManage:update, datasourceManage:delete, resultsetManage:insert, resultsetManage:update,
       resultsetManage:delete, reportManage:insert, reportManage:update, reportManage:delete, bigScreenManage:design, fileManage:query, fileManage:upload, fileManage:update, fileManage:delete, dictManage:query, dictManage:insert, dictManage:update, dictManage:delete,
       dictManage:fresh, dictItemManage:query, dictItemManage:insert, dictItemManage:update, dictItemManage:delete, authorityManage:insert, authorityManage:update, authorityManage:delete, authorityManage:query, roleManage:insert, roleManage:update, roleManage:delete,
       roleManage:query, roleManage:grantAuthority, userManage:insert, userManage:update, userManage:delete, userManage:query, userManage:resetPassword, userManage:grantRole, bigScreenManage:export, bigScreenManage:import])
      **/

      if (data && data.captcha) {
        this.needCaptcha = true;
      } else {
        this.needCaptcha = false;
        //实现路由跳转
        this.$router.push({
          path: this.redirect || "/index",
          query: this.otherQuery
        });
      }
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== "redirect") {
          acc[cur] = query[cur];
        }
        return acc;
      }, {});
    }
  }
};
</script>

<style lang="scss">
.login_container .el-input input {
  color: #000;
  background: #fff;
}

/* reset element-ui css */
.login_container {
  .el-input {
    display: inline-block;
    width: 100%;
    input {
      -webkit-appearance: none;
      caret-color: rgba($color: #000000, $alpha: 0.3);
      border: 1px solid #fff;
      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px #eee inset !important;
        -webkit-text-fill-color: #666 !important;
      }
    }
  }

  .el-form-item {
    border-radius: 5px;
    color: #454545;
  }
}
.verifybox {
  position: absolute;
  left: auto;
  right: 30%;
  transform: translate(50%, -50%);
}
</style>

<style lang="scss" scoped>
.take {
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
}
.login_container {
  height: 100%;
  width: 100%;
  overflow: hidden;
  background: #91D5D5;
  .login_title {
    width: 100%;
    height: 60px;
    padding: 10px 60px;
    display: flex;
    align-items: center;
    img {
      width: 10%;
      display: block;
    }
  }
  .login_contant {
    position: relative;
    width: 100%;
    height: calc(100% - 60px);
    // height: 100%;
    .login_img {
      display: block;
      width: 100%;
      height: 100%;
    }
    .login_form {
      position: absolute;
      top: 50%;
      right: 30%;
      transform: translate(50%, -50%);
      min-width: 400px;
      width: 22%;
      height: 460px;
      background-color: #ffffff;
      border-radius: 11px;
      padding: 30px;
      overflow: hidden;
      .title_container {
        position: relative;
        .title {
          font-size: 24px;
          color: #1a1a1a;
          .title_name {
            margin: 0;
            font-size: 18px;
          }
        }
        .set_language {
          color: #fff;
          position: absolute;
          top: 3px;
          font-size: 18px;
          right: 0px;
          cursor: pointer;
        }
      }
      .form_fields {
        position: relative;
        width: 100%;
        overflow: hidden;
        padding: 5px 16px;
        background: #ffffff;
        border: 1px solid #e0e0e0;
        box-shadow: 0 0 14px 4px rgba(230, 229, 229, 0.5);
        border-radius: 4px 10px 10px 4px;
        i {
          position: absolute;
          top: -50%;
          left: 0;
          width: 4px;
          height: 50%;
          transition: top 0.2s;
          background: #f5ab1b;
          border-radius: 14px;
        }
        b {
          position: absolute;
          top: 50%;
          left: 0;
          width: 100%;
          height: 1px;
          background: #e0e0e0;
          border-radius: 2px;
          margin-top: -0.5px;
        }
        p {
          margin: 0;
          padding: 0;
          line-height: 32px;
          height: 32px;
          font-size: 12px;
          color: #666;
        }
        .show_pwd {
          position: absolute;
          right: 10px;
          top: 0;
          font-size: 16px;
          color: #889aa4;
          cursor: pointer;
          user-select: none;
        }
      }
      .control {
        width: 100%;
        height: 70px;
        display: flex;
        align-items: center;
        justify-content: space-between;
        font-size: 14px;
        color: #919191;
        .remember {
          width: 36%;
          display: flex;
          align-items: center;
          p {
            padding-left: 8px;
          }
          // justify-content: space-between;
          & > input {
            position: relative;
            width: 14px;
            height: 14px;
          }
          & > input:checked::before {
            content: "\2713";
            background-color: #f5ab1b;
            position: absolute;
            top: 0;
            left: 0px;
            padding-left: 1.5px;
            width: 100%;
            height: 100%;
            border: 1px solid #f5ab1b;
            border-radius: 2px;
            font-size: 12px;
            color: white;
            font-weight: bold;
          }
        }
      }
      .login_btn {
        width: 130px;
        height: 40px;
        background: #409eff;
        border: none;
        border-radius: 10px;
        font-size: 16px;
        color: #ffffff;
        text-align: center;
      }
    }
  }
}
</style>
