import { hasPermission } from '@/utils/auth'
export default {
  /**
  @Author soup
  @Date 2022/03/25
  @Description  el是html元素  binding是v-permission与绑定dom元素关系 两个都是对象
  **/
  inserted(el, binding, vnode) {
    const { value } = binding // v-permission 绑定的权限值 类似这种字符串 authorityManage:query
    var checkFlag = hasPermission(value)
    if(checkFlag == false && el.parentNode ){
      el.parentNode.removeChild(el)
    }
  },
}
