import permission from './permission'
//@soup 设置自定义命令 定义不需要设置v开头
const install = function (Vue) {
  Vue.directive('permission', permission)
}
//Vue.use()是用来使用插件的
if (window.Vue) {
  window['permission'] = permission
  Vue.use(install) // eslint-disable-line
}

permission.install = install
export default permission
