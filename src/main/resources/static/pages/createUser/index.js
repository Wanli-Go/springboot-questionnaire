onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('创建用户')

  $('#startTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd hh:mm:ss', // 显示格式
    // minView: "month", // 设置只显示到月份
    minView: 0,  //0表示可以选择小时、分钟   1只可以选择小时
    minuteStep: 1,//分钟间隔1分钟
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
  $('#endTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd hh:mm:ss', // 显示格式
    // minView: "month", // 设置只显示到月份
    minView: 0,  //0表示可以选择小时、分钟   1只可以选择小时
    minuteStep: 1,//分钟间隔1分钟
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })

  let user = $util.getPageParam('user')
  // console.log('--- 用户信息 ---');
  // console.log(user);
  if (user) {
    $('#username').val(user.username)
    $('#password').val(user.password)
    $('#startDate').val(user.startTime)
    $('#endDate').val(user.endTime)
  }
}

function formatDate(date) {
  var d = new Date(date),
      month = '' + (d.getMonth() + 1),
      day = '' + d.getDate(),
      year = d.getFullYear(),
      hour = '' + d.getHours(),
      minute = '' + d.getMinutes(),
      second = '' + d.getSeconds();

  if (month.length < 2)
    month = '0' + month;
  if (day.length < 2)
    day = '0' + day;
  if (hour.length < 2)
    hour = '0' + hour;
  if (minute.length < 2)
    minute = '0' + minute;
  if (second.length < 2)
    second = '0' + second;

  return [year, month, day].join('-') + ' ' + [hour, minute, second].join(':');
}


const handleCreateUser = () => {

  if (!$('#username').val()) return alert('账号不能为空！')
  if (!$('#password').val()) return alert('密码不能为空！')
  if (!($('#startDate').val() && new Date($('#startDate').val()).getTime())) return alert('开始时间不能为空！')
  if (!($('#endDate').val() && new Date($('#endDate').val()).getTime())) return alert('结束时间不能为空！')

  let user = $util.getPageParam('user');
  console.log('--- user ---')
  console.log(user);
  if(!user) {
    user = {};
  }
 
  user.username = $('#username').val();
  user.password = $('#password').val();
  //user.startTime = $('#startDate').val() && new Date($('#startDate').val()).getTime();
  //user.stopTime = $('#endDate').val() && new Date($('#endDate').val()).getTime();
  user.startTime = $('#startDate').val() && formatDate(new Date($('#startDate').val()));
  user.stopTime = $('#endDate').val() && formatDate(new Date($('#endDate').val()));

  // 修改
  if(user.id) {

    $.ajax({
      url: API_BASE_URL + '/admin/modifyUserInfo',
      type: 'POST',
      data: JSON.stringify(user),
      dataType: 'json',
      contentType: 'application/json',
      success(res) {
        if (res.code === "666") {
          location.href = '/pages/user/index.html'
        } else {
          alert(res.message)
        }
      }
    })

  } else {
    // 新建
    $.ajax({
      url: API_BASE_URL + '/admin/addUserInfo',
      type: 'POST',
      data: JSON.stringify(user),
      dataType: 'json',
      contentType: 'application/json',
      success(res) {
        if (res.code === "666") {
          location.href = '/pages/user/index.html'
        } else {
          alert(res.message)
        }
      }
    })
  }

  // let params = {
  //   username: $('#username').val(),
  //   password: $('#password').val(),
  //   startTime: $('#startDate').val() && new Date($('#startDate').val()).getTime(),
  //   stopTime: $('#endDate').val() && new Date($('#endDate').val()).getTime()
  // }
  // if (!params.username) return alert('账号不能为空！')
  // if (!params.password) return alert('密码不能为空！')
  // if (!params.startTime) return alert('开始时间不能为空！')
  // if (!params.stopTime) return alert('结束时间不能为空！')
  // $.ajax({
  //   url: API_BASE_URL + '/admin/addUserInfo',
  //   type: 'POST',
  //   data: JSON.stringify(params),
  //   dataType: 'json',
  //   contentType: 'application/json',
  //   success(res) {
  //     if (res.code === "666") {
  //       location.href = '/pages/user/index.html'
  //     } else {
  //       alert(res.message)
  //     }
  //   }
  // })
}
