
//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    motto: '快递服务系统',
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
          wx.showToast({
            title: '邮箱或密码错误',
            icon: 'error',
            duration: 2000
          })
        } else{
          wx.showToast({
            title: '登陆成功',
            icon: 'success',
            duration: 2000
          })
          wx.setStorage({
            key:"userInfo",
            data:res.data.data
          });
          setTimeout(()=> {
            if (res.data.data.userId != null) {
              wx.switchTab({
                url: '../home/home'
              })
            } else {
              wx.redirectTo({
                url: '../deliveryMan/deliveryMan',
              })
            }
          }, 2000)
          
          // wx.switchTab({
          //   url: '../home/home'
          // })
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