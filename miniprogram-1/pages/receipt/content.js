// pages/receipt/content.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    expressInfo:{},
    network:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    console.log(options)
    var that = this
    wx.request({
      url: 'http://8.130.39.140:8081/express/api/express/'+options.expressId,
      method: 'GET',
      success: function (res) {
        console.log(res)
        that.setData({
          expressInfo:res.data.data
        })
        wx.request({
          url: 'http://8.130.39.140:8081/express/api/allNetworks',
          method: 'GET',
          success: function (res) {
            console.log(res);
            that.setData({
              network:res.data.data[that.data.expressInfo.networkId-1],
            });
          },
          fail: function (res) {
            console.log(res.data);
            console.log('is failed')
          }
        })
      },
      fail: function (res) {
        console.log(res);
        console.log('is failed')
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})