// pages/deliveryMangr/deliveryMangr.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    region: ['福建省', '福州市', '闽侯县'],
    name:'cjx',
    array: [],
    userInfo: '',
    index: 0
  },

  bindRegionChange: function (e) {
    let tmp = this.data.userInfo
    tmp.province = e.detail.value[0]
    tmp.municipal = e.detail.value[1]
    tmp.country = e.detail.value[2]
    this.setData({
      region: e.detail.value,
      userInfo: tmp
    })
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
        let regionTmp = [`${res.data.province}`, `${res.data.municipal}`, `${res.data.country}`]
        that.setData({
          region:regionTmp
        })
        console.log(res);
      }
    })
  },

  back() {
    wx.switchTab({
      url: '../home/home',
    })
  },

  bindNameInput(e) {
    let tmp = this.data.userInfo
    tmp.username = e.detail.value
    this.setData({
      userInfo: tmp
    })
  },

  bindAddrInput(e) {
    let tmp = this.data.userInfo
    tmp.detailAddress = e.detail.value
    this.setData({
      userInfo: tmp
    })
  },


  submit() {
    console.log(this.data.userInfo);
    const that = this
    if (this.data.userInfo.detailAddress.trim() == "" || this.data.userInfo.username.trim() == "" ) {
      wx.showToast({
        title: '信息不能为空',
        icon: 'error',
        duration: 2000
      })
      return
    }
    wx.request({
      url: 'http://8.130.39.140:8081/express/user/updateUser',
      data: JSON.stringify(this.data.userInfo),
      method: 'POST',
      success: function (res) {
        console.log(res);
        if (res.data.message == '修改成功') {
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
            wx.switchTab({
              url: '../home/home',
            })
          }, 2000)
        } else {
          console.log(res);
          wx.showToast({
            title: '修改失败',
            icon: 'error',
            duration: 2000
          })
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