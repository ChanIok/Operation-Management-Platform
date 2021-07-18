// 员工管理

// 获取所有员工的ID，姓名
// 返回
let res = {
  code: 0,
  msg: [{
    id: 1,
    name: "lisi"
  }, {
    id: 2,
    name: "zhangsan"
  }]
}
// 获取指定员工的所有信息
let res = {
  code: 0,
  msg: {
    id: "2018080198207",
    name: "陈文轩",
    age: "21",
    sex: "男",
    position: "人事管理",
    faculty: "计算机学院",
    unit: "教务处",
    induction_year: "2018",
    phone: "13700000000",
    adderss: "广东佛山",
    political_status: "群众",
    education_background: "本科",
    graduate_institutions: "中山学院",
  }
}

// 修改指定员工的所有信息


// 查询员工考勤记录


// 返回格式
export interface IResponse {
  code: 0; //0为成功
  msg: {

  }
}
