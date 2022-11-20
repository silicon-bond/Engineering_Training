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
    //已完成
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
    //未完成
    expressListForRecive:[
      {
        "expressId":3,
        "receiptMunicipal":"福州市",
        "deliverName":"小明",
        "state":"派送中",
        "receiptMunicipal":"漳州市",
        "receiptName":"晓东"
      },
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
      if(this.data.stateForRD) {
        wx.request({
          url: `http://8.130.39.140:8081/express/api/deliveryman/myUnCompletedExpressList?deliverymanId=${that.data.userInfo.deliverymanId}&page=1&pageSize=5`,
          method: 'GET',
          success: function (res) {
            console.log(111);
            console.log(res);
            that.setData({
              expressListForDelivery:res.data.data.records
            });
          },
          fail: function (res) {
            console.log(res);
            wx.showToast({
              title: '获取信息失败',
              icon: 'error',
              duration: 2000
            })
          }
        })
      } else {
        wx.request({
          url: `http://8.130.39.140:8081/express/api/deliveryman/myCompletedExpressList?deliverymanId=${that.data.userInfo.deliverymanId}&page=1&pageSize=5`,
          method: 'GET',
          success: function (res) {
            console.log(222);
            console.log(res);
            that.setData({
              expressListForRecive:res.data.data.records
            });
          },
          fail: function (res) {
            console.log(res);
            wx.showToast({
              title: '获取信息失败',
              icon: 'error',
              duration: 2000
            })
          }
        })
      }
    }else{
      if(this.data.stateForRD){
        wx.request({
          url: `http://8.130.39.140:8081/express/api/deliveryman/myUnCompletedExpressList?expressId=${that.data.searchid}&deliverymanId=${that.data.userInfo.deliverymanId}&page=1&pageSize=5`,
          method: 'GET',
          success: function (res) {
            console.log(res);
            if (res.data.data == null) {
              that.setData({
                expressListForDelivery: []
              });
            } else {
              that.setData({
                expressListForDelivery: res.data.data.records
              });
            }
          },
          fail: function (res) {
            console.log(res.data);
            wx.showToast({
              title: '查询信息失败',
              icon: 'error',
              duration: 2000
            })
          }
        })
    }else{
      wx.request({
        url: `http://8.130.39.140:8081/express/api/deliveryman/myCompletedExpressList?expressId=${that.data.searchid}&deliverymanId=${that.data.userInfo.deliverymanId}&page=1&pageSize=5`,
        method: 'GET',
        success: function (res) {
          console.log(res);
          if (res.data.data == null) {
            that.setData({
              expressListForRecive: []
            });
          } else {
            that.setData({
              expressListForRecive: res.data.data.records
            });
          }
        },
        fail: function (res) {
          console.log(res.data);
          wx.showToast({
            title: '查询信息失败',
            icon: 'error',
            duration: 2000
          })
        }
      })
    }
    }
    
  },
  navigateToDetail(e){
    console.log(e.currentTarget.dataset.id);
    wx.navigateTo({
      url: '../../pages/wanchendetail/wanchendetail?stateForRD='+this.data.stateForRD+'&expressId='+e.currentTarget.dataset.id,
    })
  },

  showD() {
    this.setData({
      stateForRD:true
    })
  },
  showR() {
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
        console.log(res);
        that.setData({
          userInfo:res.data
        })
        wx.request({
          url: `http://8.130.39.140:8081/express/api/deliveryman/myUnCompletedExpressList?deliverymanId=${res.data.deliverymanId}&page=1&pageSize=5`,
          method: 'GET',
          success: function (res) {
            console.log(111);
            console.log(res);
            that.setData({
              expressListForDelivery:res.data.data.records
            });
          },
          fail: function (res) {
            console.log(res);
            wx.showToast({
              title: '获取信息失败',
              icon: 'error',
              duration: 2000
            })
          }
        })
        wx.request({
          url: `http://8.130.39.140:8081/express/api/deliveryman/myCompletedExpressList?deliverymanId=${res.data.deliverymanId}&page=1&pageSize=5`,
          method: 'GET',
          success: function (res) {
            console.log(222);
            console.log(res);
            that.setData({
              expressListForRecive:res.data.data.records
            });
          },
          fail: function (res) {
            console.log(res);
            wx.showToast({
              title: '获取信息失败',
              icon: 'error',
              duration: 2000
            })
          }
        })
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
        url: `http://8.130.39.140:8081/express/api/deliveryman/myUnCompletedExpressList?deliverymanId=${that.data.userInfo.deliverymanId}&page=1&pageSize=5`,
        method: 'GET',
        success: function (res) {
          console.log('下拉111');
          that.setData({
            expressListForDelivery:res.data.data.records,
            pageD:2
          });
        },
        fail: function (res) {
          console.log(res);
          wx.showToast({
            title: '获取信息失败',
            icon: 'error',
            duration: 2000
          })
        }
      })
    }else{
      wx.request({
        url: `http://8.130.39.140:8081/express/api/deliveryman/myCompletedExpressList?deliverymanId=${that.data.userInfo.deliverymanId}&page=1&pageSize=5`,
        method: 'GET',
        success: function (res) {
          console.log('下拉222');
          that.setData({
            expressListForRecive:res.data.data.records,
            pageR:2
          });
        },
        fail: function (res) {
          console.log(res);
          wx.showToast({
            title: '获取信息失败',
            icon: 'error',
            duration: 2000
          })
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
        url: `http://8.130.39.140:8081/express/api/deliveryman/myUnCompletedExpressList?deliverymanId=${that.data.userInfo.deliverymanId}&page=${that.data.pageD}&pageSize=5`,
        method: 'GET',
        success: function (res) {
          console.log('底部111');
          that.setData({
            expressListForDelivery:that.data.expressListForDelivery.concat(res.data.data.records),
            pageD:that.data.pageD+1
          });
        },
        fail: function (res) {
          console.log(res);
          wx.showToast({
            title: '获取信息失败',
            icon: 'error',
            duration: 2000
          })
        }
      })
    }else{
      wx.request({
        url: `http://8.130.39.140:8081/express/api/deliveryman/myCompletedExpressList?deliverymanId=${that.data.userInfo.deliverymanId}&page=${that.data.pageR}&pageSize=5`,
        method: 'GET',
        success: function (res) {
          console.log('底部222');
          that.setData({
            expressListForRecive:that.data.expressListForRecive.concat(res.data.data.records),
            pageR:that.data.pageR+1
          });
        },
        fail: function (res) {
          console.log(res);
          wx.showToast({
            title: '获取信息失败',
            icon: 'error',
            duration: 2000
          })
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