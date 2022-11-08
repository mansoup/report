import Vue from 'vue'
/*@soup 2022/1/9 增加注释 项目的入口文件*/
// element-ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import zhLocale from 'element-ui/lib/locale/lang/zh-CN'
import 'normalize.css/normalize.css'// A modern alternative to CSS resets @soup 2022/1/6  增加注释 让css代码在不同浏览器表现一致
import '@/assets/styles/common.css'
import '@/assets/styles/index.scss'// custome global css

// app router vuex filter mixins
import App from './App'
import router from './router'
import store from './store'
import * as filter from './filter'
import mixins from '@/mixins'
import echarts from 'echarts';
// 全局定义echarts
import ECharts from 'vue-echarts'
import 'echarts/lib/chart/bar'
import 'echarts/lib/component/tooltip'
//import 'echarts-liquidfill'
// import 'echarts-gl'
Vue.component('v-chart', ECharts)
  /**
  @Author soup
  @Date 2022/06/16
  @Description  下面两行是全局注册echarts 不知道有没有和上面冲突
  **/
// import echarts from 'echarts'
// Vue.prototype.$echarts = echarts
// anji component
import anjiCrud from '@/components/AnjiPlus/anji-crud/anji-crud'
import anjiSelect from '@/components/AnjiPlus/anji-select'
import anjiUpload from '@/components/AnjiPlus/anji-upload'
Vue.component('anji-upload', anjiUpload)
Vue.component('anji-crud', anjiCrud)
Vue.component('anji-select', anjiSelect)

// permission control
import '@/permission'
// 按钮权限的指令
import permission from '@/components/Permission/index'
Vue.use(permission)

import Avue from '@smallwei/avue';
import '@smallwei/avue/lib/index.css';
Vue.use(Avue);

// import {TWEEN} from "@/views/threeD_Bookstore/js/Tween.js"
// Vue.use(TWEEN);

// enable element zh-cn
Vue.use(ElementUI, { zhLocale })

// register global filter.
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key])
})

// register global mixins.
Vue.mixin(mixins)
//引入省市级联
// 分页的全局size配置;
Vue.prototype.$pageSizeAll = [10,30, 50, 100, 200, 500]

Vue.config.productionTip = false

// create the app instance.
/* @soup 2022/1/6 增加注释  h 函数的本质是createElement 函数，这个函数的作用就是生成一个 VNode节点，render 函数得到这个 VNode 节点之后，返回给 Vue.js 的 mount 函数，渲染成真实 DOM 节点，并挂载到根节点上
  render: h => h(App)相当于
  render: function (createElement) {
  return createElement(App);
}*/
// @soup 2022/1/9 增加注释 render函数 将App组件放入容器
new Vue({ el: '#app', router, store, render: h => h(App) })
