// pages/deliveryMangr/deliveryMangr.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name:'cjx',
    array: [],
    userInfo: '',
    index: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const that =this
    wx.getStorage({
      key:"userInfo",
      success(res){
        that.setData({
          userInfo:res.data
        })
        that.setData({
          array:[`${res.data.network.country}${res.data.network.networkName}`]
        })
        console.log(res);
      }
    })
  },

  back() {
    wx.redirectTo({
      url: '../deliveryMan/deliveryMan',
    })
  },

  bindNameInput(e) {
    let tmp = this.data.userInfo
    tmp.username = e.detail.value
    this.setData({
      userInfo: tmp
    })
  },

  bindTelInput(e) {
    let tmp = this.data.userInfo
    tmp.phoneNumber = e.detail.value
    this.setData({
      userInfo: tmp
    })
  },

  submit() {
    const that = this
    var reg='^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-9])|(18[0-9])|166|198|199|191|(147))\\d{8}$';
  //创建正则表达式对象
    var regExp=new RegExp(reg);
  //使用test()函数验证数据是否匹配正则表达式，匹配返回true,否则返回false
    if(!regExp.test(this.data.userInfo.phoneNumber)) {
      wx.showToast({
        title: '电话号码不符合规范',
        icon: 'error',
        duration: 2000
      })
      return
    }
    wx.request({
      url: 'http://8.130.39.140:8081/express/api/deliveryman',
      data: this.data.userInfo,
      method: 'PUT',
      success: function (res) {
        console.log(res);
        if (res.data.message == 'success') {
          wx.setStorage({
            key:"userInfo",
            data:that.data.userInfo
          });
          wx.showToast({
            title: '修改成功',
            icon: 'success',
            duration: 2000
          })
          setTimeout(()=> {
            wx.redirectTo({
              url: '../deliveryMan/deliveryMan',
            })
          }, 2000)
        }
      },
      fail: function (res) {
        console.log(res.data);
        wx.showToast({
          title: '修改失败',
          icon: 'error',
          duration: 2000
        })
      }
    })
    console.log(this.data.userInfo);
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})