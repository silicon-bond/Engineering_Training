// pages/receipt/content.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{},
    expressInfo:{},
    network:{},
    state:['未揽件','已揽件','运输中','待派送','派送中','已送达'],
  },

  submit() {
    const that = this
    console.log('sub');
    wx.showModal({
      title: '提示',
      content: '是否确定接单',
      success (res) {
        if (res.confirm) {
          wx.request({
            url: `http://8.130.39.140:8081/express/api/deliveryman/orderReceiving?expressId=${that.data.expressInfo.expressId}&deliverymanId=${that.data.userInfo.deliverymanId}`,
            method: 'GET',
            success: function (res) {
              console.log(res)
              wx.showToast({
                title: '接单成功',
                icon: 'success',
                duration: 2000
              })
              setTimeout(()=> {
                wx.redirectTo({
                  url: '../deliveryMan/deliveryMan',
                })
              }, 2000)
            },
            fail: function (res) {
              console.log(res);
              console.log('is failed')
            }
          })

        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },


  back() {
    wx.navigateBack({
      delta: 0,
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    wx.getStorage({
      key:"userInfo",
      success(res){
        that.setData({
          userInfo:res.data
        })
      }
    })
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
            let tmp = {}
            for (let i =0; i<res.data.data.length; i++) {
                if(res.data.data[i].networkId == that.data.expressInfo.networkId) {
                  tmp = res.data.data[i]
                }
            }
            console.log(tmp);
            that.setData({
              network:tmp,
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