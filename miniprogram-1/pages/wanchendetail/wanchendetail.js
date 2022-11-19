// pages/receipt/content.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isShow: true,
    userInfo:{},
    expressInfo:{},
    network:{},
    state:['未揽件','已揽件','运输中','待派送','派送中','已送达'],
  },

  submit() {
    const that = this
    wx.showModal({
      title: '提示',
      content: '是否确定送达',
      success (res) {
        if (res.confirm) {
          console.log('sss');
          wx.request({
            url: `http://8.130.39.140:8081/express/api/deliveryman/completeDelivery?expressId=${that.data.expressInfo.expressId}`,
            method: 'GET',
            success: function (res) {
              wx.showToast({
                title: '成功送达',
                icon: 'success',
                duration: 2000
              })
              setTimeout(()=> {
                wx.redirectTo({
                  url: '../deliveryManjiedan/deliveryManjiedan',
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
    this.setData({
      isShow: options.stateForRD=='true'?true:false
    })
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