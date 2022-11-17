
//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    motto: 'Express 简单快递小程序',
    userInfo: {},
    userEmail:'',
    userPassword:'',
    responseData:{},
  },
  userNameInput:function(e){
    this.setData({
      userEmail: e.detail.value
    })
  },
  userPasswordInput:function(e){
    this.setData({
      userPassword: e.detail.value
    })
    console.log(e.detail.value)
  },
  logIn:function(){
    var that = this
    wx.request({
      url: 'http://8.130.39.140:8081/express/api/login',
      data:{
        email:this.data.userEmail,
        password:this.data.userPassword
      },
      method: 'POST',
      success: function (res) {
        console.log(res);
        // that.setData({
        //   responseData:res.data.result[0].body
        // });
        if(res.data.data==null){
          wx.showModal({
            title: '提示',
            content: '用户邮箱或密码错误',
            success: function (res) {
              if (res.confirm) { //这里是点击了确定以后
                console.log('用户点击确定')
              } else { //这里是点击了取消以后
                console.log('用户点击取消')
              }
            }
          })
        }else{
          wx.setStorage({
          key:"userInfo",
          data:res.data.data
        });
        wx.redirectTo({
          url: '../home/home'
        })
        }  
      },
      fail: function (res) {
        console.log(res.data);
        console.log('is failed')
      }
    })
  },
  onLoad: function () {
    
  },
  onShow: function(){
      
  },
  onReady:function(){
  },
  onHide:function(){
  },
  onUnload:function(){
  },

})