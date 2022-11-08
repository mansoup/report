import request from "@/utils/request";

export function supervisorList (params) {
  return request({
    url: 'supervisor/pageList',
    method: 'get',
    params,
  })
}

export function supervisorAdd (data) {
  return request({
    url: 'supervisor',
    method: 'post',
    data
  })
}

export function supervisorDeleteBatch (data) {
  return request({
    url: 'supervisor/delete/batch',
    method: 'post',
    data
  })
}

export function supervisorUpdate (data) {
  return request({
    url: 'supervisor',
    method: 'put', data,
  })
}

export function supervisorDetail (data) {
  console.log("data.accessKey"+data.accessKey);
  return request({
    url: 'supervisor/' + data.id,
    method: 'get',
    params: { accessKey: data.accessKey },
    // params:data
  })
}
export default {supervisorList,supervisorAdd,supervisorDeleteBatch,supervisorUpdate,supervisorDetail}
