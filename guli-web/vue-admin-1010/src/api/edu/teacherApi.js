import request from '@/utils/request'

export default {
  getTeacherListPage(page, size, teacherQuery) {
    return request({
      // url:'/serviceedu/teacher/pageTeacherCondition/'+page+"/"+size',
      url: `/serviceedu/teacher/pageTeacherCondition/${page}/${size}`,
      method: 'post',
      // teacherQuery 查询条件对象 后端使用RequestBody获取数据
      // data表示吧对象转换json进行传递到接口里面
      data: teacherQuery
    })
  },
  deleteTeacherById(id) {
    return request({
      url: `/serviceedu/teacher/${id}`,
      method: 'delete'
    })
  },
  saveOrUpdate(teacher) {
    return request({
      url: `/serviceedu/teacher/addTeacher`,
      method: 'post',
      data: teacher
    })
  }
}

