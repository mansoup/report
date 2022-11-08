import request from '@/utils/request'
/**
@Author soup
@Date 2022/03/11
@Description export function 函数名 向外提供函数接口
**/
export function login (data) {
  return request({
    url: 'accessUser/login',
    method: 'post',
    data
  })
}

export function logout () {
  return request({
    url: 'accessUser/logout',
    method: 'post'
  })
}

// 登录之后   根据旧修改密码
export function reqUpdatePassword (data) {
  return request({
    url: '/accessUser/updatePassword',
    method: 'post',
    data
  })
}
