// pages/info/info.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    info:{
      "deliver_name":"",
      "deliver_phone_number":"",
      "deliver_provice":"福建省",
      "deliver_municipal":"福州市",
      "deliver_country":"闽侯县",
      "deliver_street":"",
      "deliver_detail_address":""
    },

    region: ['福建省', '福州市', '闽侯县'],
  },

  bindRegionChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      region: e.detail.value,
      'info.deliver_provice':e.detail.value[0],
      'info.deliver_municipal':e.detail.value[1],
      'info.deliver_country':e.detail.value[2]
    })
  },

  backToD() {
    let pages = getCurrentPages();
    pages[pages.length-2].setData({
      infoDeliver:this.data.info
    })
    wx.navigateBack({
      delta:1
    })
  },


  setInputValue: function(e){
    let value = e.detail.value;
    console.log("写入：" + value)
    if(!value) {return;}
    let isObj = e.target.dataset.isObj;
    let objName = e.target.dataset.objName;
    let fieldName = e.target.dataset.fieldName;
    if(isObj=="1"){
      let obj = this.data[`${objName}`];
      obj[fieldName] = value;
      this.setData({
        [`${objName}`]: obj
      });
    }else{
      this.setData({
        [`${fieldName}`]: value
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

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