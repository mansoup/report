import request from "@/utils/request";

export function projectList (params) {
  return request({
    url: 'waterProject/pageList',
    method: 'GET',
    params,
  })
}

export function projectAdd (data) {
  return request({
    url: 'waterProject',
    method: 'post',
    data
  })
}

export function projectDeleteBatch (data) {
  return request({
    url: 'waterProject/delete/batch',
    method: 'post',
    data
  })
}

export function projectUpdate (data) {
  return request({
    url: 'waterProject',
    method: 'put',
    data,
  })
}

export function projectDetail (data) {
  return request({
    url: 'waterProject/' + data.id,
    method: 'get',
    params: { accessKey: data.accessKey }
  })
}
//二维码查询工程信息
export function QRprojectDetail (id,accessKey) {
  return request({
    url: 'waterProject/' + id,
    method: 'get',
    params: { accessKey: accessKey }
  })
}
export default {projectList,projectAdd,projectDeleteBatch,projectUpdate,projectDetail,QRprojectDetail}
