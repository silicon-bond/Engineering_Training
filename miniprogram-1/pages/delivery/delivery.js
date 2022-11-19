// pages/delivery/delivery.js
var util = require('../../utils/util.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{},
    infoDeliver:{
      "deliver_name":"请输入姓名",
      "deliver_phone_number":"请输入手机号码",
      "deliver_provice":"",
      "deliver_municipal":"",
      "deliver_country":"",
      "deliver_detail_address":""
    },
    infoReceipt:{
      "receipt_name":"请输入姓名",
      "receipt_phone_number":"请输入手机号码",
      "receipt_provice":"",
      "receipt_municipal":"",
      "receipt_country":"",
      "receipt_detail_address":""
    },
    "description":"",
    "checkedNetwork":{},
    networks:[],
    InfoNull:true,
  },

  sendExpress(){
    var that = this
    const keyD = Object.keys(this.data.infoDeliver)
    const keyR = Object.keys(this.data.infoReceipt)
    keyD.forEach(function name(item,key) {
      if(that.data.infoDeliver[item]==""){
        that.setData({
          InfoNull:false
        })
      }
    })
    keyR.forEach(function name(item,key) {
      if(that.data.infoReceipt[item]==""){
        that.setData({
          InfoNull:false
        })
      }
    })
    if(that.data.InfoNull){
      wx.showModal({
        title: '提示',
        content: '确定要进行下单吗',
        success: function (res) {
          if (res.confirm) { //这里是点击了确定以后
            console.log('用户点击确定')
            let localDate = util.formatTime(new Date())
            wx.request({
              url: 'http://8.130.39.140:8081/express/api/express',
              data:{
                orderDate:localDate,
                state:0,
                networkId:that.data.checkedNetwork['networkId'],
                deliverName:that.data.infoDeliver['deliver_name'],
                deliverProvince:that.data.infoDeliver['deliver_provice'],
                deliverMunicipal:that.data.infoDeliver['deliver_municipal'],
                deliverCountry:that.data.infoDeliver['deliver_country'],
                deliverDetailAddress:that.data.infoDeliver['deliver_detail_address'],
                deliverPhoneNumber:that.data.infoDeliver['deliver_phone_number'],
                receiptName:that.data.infoReceipt['receipt_name'],
                receiptProvince:that.data.infoReceipt['receipt_provice'],
                receiptMunicipal:that.data.infoReceipt['receipt_municipal'],
                receiptCountry:that.data.infoReceipt['receipt_country'],
                receiptDetailAddress:that.data.infoReceipt['receipt_detail_address'],
                receiptPhoneNumber:that.data.infoReceipt['receipt_phone_number'],
                description:that.data.description,
                ordererId:that.data.userInfo['userId']
              },
              method: 'POST',
              success: function (res) {
                console.log(res);
                wx.redirectTo({
                  url: '../receipt/receipt',
                })
              },
              fail: function (res) {
                console.log(res.data);
                console.log('is failed')
              }
            })
          } else { //这里是点击了取消以后
            console.log('用户点击取消')
          }
        }
      })
    }else{
      that.setData({
        InfoNull:true
      })
      wx.showModal({
        title: '提示',
        content: '请填写必要信息',
        success: function (res) {
          if (res.confirm) { //这里是点击了确定以后
            console.log('用户点击确定')
          } else { //这里是点击了取消以后
            console.log('用户点击取消')
          }
        }
      })
    }
  },

  bindPickerChange: function(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      checkedNetwork: this.data.networks[e.detail.value]
    })
  },

  toInfo(){
    wx.navigateTo({
      url: '../info/info',
    })
  },
  toInfoTwo(){
    wx.navigateTo({
      url: '../info/infoTwo',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var that = this
    wx.getStorage({
      key:"userInfo",
      success(res){
        that.setData({
          userInfo:res.data
        })
        console.log(res);
      }
    })
    var that = this
    wx.request({
      url: 'http://8.130.39.140:8081/express/api/allNetworks',
      method: 'GET',
      success: function (res) {
        console.log(res);
        that.setData({
          networks:res.data.data,
          checkedNetwork:res.data.data[0]
        });
      },
      fail: function (res) {
        console.log(res.data);
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