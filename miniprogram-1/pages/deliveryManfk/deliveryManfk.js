// pages/deliveryManfk/deliveryManfk.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: [],
    title: '',
    info: '',
    userInfo:'',
    index: 0
  },

  bindTextInput: function(e) {
    this.setData({
      info: e.detail.value
    })
  },
  bindKeyInput: function (e) {
    this.setData({
      title: e.detail.value
    })
  },

  back() {
    wx.redirectTo({
      url: '../deliveryMan/deliveryMan',
    })
  },

  submit() {
    if (this.data.title.trim() == '' || this.data.info.trim() == '') {
      wx.showToast({
        title: '信息不能为空',
        icon: 'error',
        duration: 2000
      })
      return
    }
    console.log(this.data.title);
    console.log(this.data.info);
    wx.request({
      url: 'http://8.130.39.140:8081/express/api/deliveryman/feedback',
      data:{
        networkId: this.data.userInfo.networkId,
        title: this.data.title,
        description:this.data.info,
        providerPhoneNumber:this.data.userInfo.phoneNumber
      },
      method: 'POST',
      success: function (res) {
        console.log(res);
        if (res.data.message == 'success') {
          wx.showToast({
            title: '提交成功',
            icon: 'success',
            duration: 2000
          })
          setTimeout(()=> {
            wx.redirectTo({
              url: '../deliveryMan/deliveryMan',
            })
          }, 2000)
        } else {
          wx.showToast({
            title: '提交失败',
            icon: 'error',
            duration: 2000
          })
          this.setData({
            info: '',
            title: ''
          })
        }
      },
      fail: function (res) {
        console.log(res.data);
        wx.showToast({
          title: '提交失败',
          icon: 'error',
          duration: 2000
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const that = this

    wx.getStorage({
      key:"userInfo",
      success(res){

        that.setData({
          userInfo:res.data
        })
        that.setData({
          array:[`${res.data.network.country}${res.data.network.networkName}`]
        })
      }
    })
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