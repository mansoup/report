import axios from 'axios';
import { Message, MessageBox } from 'element-ui';//是用来弹窗消息的
/**
@Author soup
@Date 2022/03/11
@Description  env.BASE_API 在config 下dev.env.js用于配置baseUrl 简便输入url
**/
axios.defaults.baseURL = process.env.BASE_API
const service = axios.create({
  withCredentials: false,//等于false表示发送的请求不允许携带cookie
  timeout: 60000,
  headers: {
    'Content-Type': 'application/json',//表示设置默认的请求头，在这里设置了一个Content-Type。axios的header默认的Content-Type是'application/json;charset=UTF-8'，在本项目中都是用json格式传输，所以修改为application/json
  }
})

service.interceptors.request.use(
  config => {
    return config
  },
  error => {
    // Do something with request error
    Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  response => {
    const res = response.data;
    if (res.code == 200) {
      return res
    }

    else if (res.code == '50014') {
      //登录超时或被登出，弹确认框，用户确认后，跳转到登录页面
      MessageBox({
        message: "当前登录已失效或异地登录，请重新登录",
        type: 'error',
        duration: 3 * 1000,
      }).then(() => {
        sessionStorage.clear();
        localStorage.clear();
        window.location.href = "/";//跳转到登录界面
      }).catch(err => {
      })
    } else if (res.code == "3100" || res.code == "3101") {
      return res;
    }
    else {
      Message({
        message: res.repMsg || res.message,
        type: 'error',
        duration: 3 * 1000
      })
      return res;
    }
  },
  error => {
    var errorStatus = error.response.code;
    var errorData = error.response.data;
    var messageTxt = "";
    if (errorStatus != 200) {
      messageTxt = "服务器内部错误，请联系管理员";
    } else {
      messageTxt = '失败原因：' + errorData.code + '--' + errorData.repMsg;
    }
    Message({
      message: messageTxt,
      type: 'error',
      duration: 5 * 1000
    })
  }
)
//在其它组件复用service这个axios
export default service
