// pages/deliveryMan/deliveryMan.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  toView(e) {
    const {id} = e.currentTarget.dataset
    let url = ''
    switch(+id) {
      case 1:
        url = '../deliveryManwuliu/deliveryManwuliu'
        break
      case 2:
        url = '../deliveryManjiedan/deliveryManjiedan'
        break
      case 3:
        url = '../deliveryManfk/deliveryManfk'
        break
      case 4:
        url = '../deliveryMangr/deliveryMangr'
        break
    }
    wx.navigateTo({
      url,
    })
  },

  leave() {
    wx.clearStorage()
    wx.redirectTo({
      url: '../login/login',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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