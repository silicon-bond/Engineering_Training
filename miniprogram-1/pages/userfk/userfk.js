// pages/deliveryManfk/deliveryManfk.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: '',
    network:[],
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
    wx.switchTab({
      url: '../home/home',
    })
  },

  bindPickerChange: function(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index: e.detail.value
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
    let id = null
    let nekwordName = this.data.array[this.data.index]
    for (let i = 0 ;i< this.data.network.length; i++) {
      console.log(nekwordName)
      let name = `${this.data.network[i].country}${this.data.network[i].networkName}`
      if(nekwordName==name) {
        id = this.data.network[i].networkId
      }
    }
    wx.request({
      url: 'http://8.130.39.140:8081/express/user/FeedBack?title='+this.data.title+'&description='+this.data.info+'&phoneNum='+this.data.userInfo.phoneNumber+'&networkId='+id,
      method: 'GET',
      success: function (res) {
        console.log(res);
        if (res.data.message == '反馈成功') {
          wx.showToast({
            title: '反馈成功',
            icon: 'success',
            duration: 2000
          })
          setTimeout(()=> {
            wx.switchTab({
              url: '../home/home',
            })
          }, 2000)
        } else {
          wx.showToast({
            title: '反馈失败',
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
        console.log(res);
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
    wx.request({
      url: 'http://8.130.39.140:8081/express/api/allNetworks',
      method: 'GET',
      success: function (res) {
        console.log(res);
        that.setData({
          network: res.data.data
        })
        let tmp = []
        for (let i = 0; i<res.data.data.length;i++) {
          tmp.push(`${res.data.data[i].country}${res.data.data[i].networkName}`)
        }
        that.setData({
          array: tmp
        })
      },
      fail: function (res) {
        console.log(res);
        wx.showToast({
          title: '获取数据失败',
          icon: 'error',
          duration: 2000
        })
      }
    })
   

    wx.getStorage({
      key:"userInfo",
      success(res){
        that.setData({
          userInfo:res.data
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