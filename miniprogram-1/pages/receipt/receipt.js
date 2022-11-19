// pages/receipt/receipt.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    searchid:"",
    userInfo:{},
    pageD:2,
    pageR:2,
    stateForRD:true,
    state:['未揽件','已揽件','运输中','待派送','派送中','已送达'],
    expressListForDelivery:[
      {
        "expressId":3,
        "receiptMunicipal":"福州市",
        "deliverName":"小明",
        "state":"派送中",
        "receiptMunicipal":"漳州市",
        "receiptName":"晓东"
      },
      {
        "expressId":3,
        "receiptMunicipal":"福州市",
        "deliverName":"小明",
        "state":"派送中",
        "receiptMunicipal":"漳州市",
        "receiptName":"晓东"
      }
    ],
    expressListForRecive:[
      {
        "expressId":3,
        "receiptMunicipal":"福州市",
        "deliverName":"小明",
        "state":"派送中",
        "receiptMunicipal":"漳州市",
        "receiptName":"晓东"
      },
      {
        "expressId":3,
        "receiptMunicipal":"福州市",
        "deliverName":"小明",
        "state":"派送中",
        "receiptMunicipal":"漳州市",
        "receiptName":"晓东"
      }
    ]
  },

  searchIdInput:function(e){
    this.setData({
      searchid: e.detail.value
    })
  },

  searchById(){
    var that = this
    if(this.data.searchid==""){
     
    }else{
      if(this.data.stateForRD){
      wx.request({
        url: 'http://8.130.39.140:8081/express/api/express/deliverPhoneNumber/id?phoneNumber='+that.data.userInfo['phoneNumber']+'&id='+that.data.searchid,
        method: 'GET',
        success: function (res) {
          console.log(res);
          that.setData({
            expressListForDelivery:res.data.data.records
          });
        },
        fail: function (res) {
          console.log(res.data);
          console.log('is failed')
        }
      })
    }else{
      wx.request({
        url: 'http://8.130.39.140:8081/express/api/express/receiptPhoneNumber/id?phoneNumber='+that.data.userInfo['phoneNumber']+'&id='+that.data.searchid,
        method: 'GET',
        success: function (res) {
          console.log(res);
          that.setData({
            expressListForRecive:res.data.data.records
          });
        },
        fail: function (res) {
          console.log(res.data);
          console.log('is failed')
        }
      })
    }
    }
    
  },
  navigateToDetail(e){
    console.log(e.currentTarget.dataset.id);
    wx.navigateTo({
      url: '../../pages/receipt/content?expressId='+e.currentTarget.dataset.id,
    })
  },

  showD() {
    console.log(123);
    this.setData({
      stateForRD:true
    })
  },
  showR() {
    console.log(456);
    this.setData({
      stateForRD:false
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
        wx.request({
          url: 'http://8.130.39.140:8081/express/api/express/deliverPhoneNumber?page=1&pageSize=4&phoneNumber='+that.data.userInfo['phoneNumber'],
          method: 'GET',
          success: function (res) {
            console.log(res);
            that.setData({
              expressListForDelivery:res.data.data.records
            });
          },
          fail: function (res) {
            console.log(res.data);
            console.log('is failed')
          }
        })
        wx.request({
          url: 'http://8.130.39.140:8081/express/api/express/receiptPhoneNumber?page=1&pageSize=4&phoneNumber='+that.data.userInfo['phoneNumber'],
          method: 'GET',
          success: function (res) {
            console.log(res);
            that.setData({
              expressListForRecive:res.data.data.records
            });
          },
          fail: function (res) {
            console.log(res.data);
            console.log('is failed')
          }
        })
        console.log(res);
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
    var that = this
    if(that.data.stateForRD){
      wx.request({
      url: 'http://8.130.39.140:8081/express/api/express/deliverPhoneNumber?page=1&pageSize=4&phoneNumber='+that.data.userInfo['phoneNumber'],
      method: 'GET',
      success: function (res) {
        console.log(res);
        that.setData({
          expressListForDelivery:res.data.data.records,
          pageD:2
        });
      },
      fail: function (res) {
        console.log(res.data);
        console.log('is failed')
      }
    })
    }else{
      wx.request({
      url: 'http://8.130.39.140:8081/express/api/express/receiptPhoneNumber?page=1&pageSize=4&phoneNumber='+that.data.userInfo['phoneNumber'],
      method: 'GET',
      success: function (res) {
        console.log(res);
        that.setData({
          expressListForRecive:res.data.data.records,
          pageR:2
        });
      },
      fail: function (res) {
        console.log(res.data);
        console.log('is failed')
      }
    })
    }

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {
    var that = this
    if(this.data.stateForRD){
      wx.request({
        url: 'http://8.130.39.140:8081/express/api/express/deliverPhoneNumber?page='+that.data.pageD+'&pageSize=4&phoneNumber='+that.data.userInfo['phoneNumber'],
        method: 'GET',
        success: function (res) {
          console.log(res);
          that.setData({
            expressListForDelivery:that.data.expressListForDelivery.concat(res.data.data.records),
            pageD:that.data.pageD+1
          });
        },
        fail: function (res) {
          console.log(res.data);
          console.log('is failed')
        }
      })
    }else{
      wx.request({
        url: 'http://8.130.39.140:8081/express/api/express/receiptPhoneNumber?page='+that.data.pageR+'&pageSize=4&phoneNumber='+that.data.userInfo['phoneNumber'],
        method: 'GET',
        success: function (res) {
          console.log(res);
          that.setData({
            expressListForRecive:that.data.expressListForRecive.concat(res.data.data.records),
            pageR:that.data.pageR+1
          });
        },
        fail: function (res) {
          console.log(res.data);
          console.log('is failed')
        }
      })
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})