
var app = getApp();

Page({

  /**
   * 页面初始化数据
   */

  data: {
    coverContentHidden: true,
    animationData: {},
    array: ['北京', '广州', '上海', '保定'],
    index: 0 ,
    proMessage:"",

    first: '', 
    firstcolor: '', 
    second:'',
    secondcolor:'',
    third:'',
    thirdcolor:'',
    fourth:'',
    fourthcolor:'', 

    map: {
      lat: '',
      lng: '',
      markers: [],
      clickMarker(e) {
        console.log(e.id)
      },
      hasMarkers: false//解决方案
    }

  },


  //选择城市后变化云麻点
  bindPickerChange: function (e) {
    var that = this;
    var cityint = e.detail.value;
    console.log('picker发送选择改变，携带值为', e.detail.value)
    wx.request({
      url: 'http://47.105.114.70/getcityLocation.do',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded' // 默认值
      },
      method: 'POST',
      data: { 'citycode': e.detail.value},
      //调用成功后标记地图气泡
      success: function (res) {
        that.setData({
          'map.lat': that.citylocationsList(res)[0].lat,
          'map.lng': that.citylocationsList(res)[0].lng, 
          'map.markers' : that.locationsList(res),
          index: e.detail.value
        })
      },

    })
  },
  actionSheetTap: function () {

  },
  regionchange(e) {
    console.log(e.type)
  },
  //点击云点时展示变化信息
  markertap(e) {
    var markerId = e.markerId;
    var that = this;
    wx.request({
      url: 'http://47.105.114.70/getProMessage.do',
      method: 'POST',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded' // 默认值
      },
      data:{
        "markerId": markerId
      },
      //调用成功后标记地图气泡
      success: function (res) {
        console.log(res)
        that.setData({
          proMessage:res.data.message
        })
      },
    })
    var animation = wx.createAnimation({
      transformOrigin: "50% 50%",
      duration: 1000,
      timingFunction: "ease-in",
      delay: 0
    })
    this.setData({
      coverContentHidden: !this.data.coverContentHidden
    })
  },
  controltap(e) {
    console.log(e.controlId)
  },
 


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    var that = this;
    that.loadInfo();
    wx.request({
      url: 'http://47.105.114.70/getAllLocation.do',
      header: {
        'content-type': 'application/json' // 默认值
      },
      method: 'POST',
      //调用成功后标记地图气泡
      success: function (res) {
          that.setData({          
            'map.markers': that.locationsList(res)
          })
      },

    })
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          mapHeight: res.windowHeight - 70
        });
      }
    });
  },
  //遍历json后得到经纬度信息
  locationsList: function (res) {
    var maker_new = [];
    for (var key in res.data) {
      maker_new.push({
        id: res.data[key].id,
        latitude: res.data[key].latitude,
        longitude: res.data[key].longitude,
        width: 50,
        height: 50
      })
    }
      return maker_new;
  },

  //遍历后得到当前城市位置
  citylocationsList: function (res) {
    var city_location = [];
    for (var key in res.data) {
      city_location.push({
        lat: res.data[key].cityLat,
        lng: res.data[key].cityLng
      })
     }
    return city_location;
  },

  //获取当前位置的经纬度
  loadInfo: function () {
    var that = this;
    wx.getLocation({
      type: 'wgs84', //返回可以用于wx.openLocation的经纬度
      success: function (res) {
        var latitude = res.latitude//维度
        var longitude = res.longitude//经度
        that.setData({
          'map.lat':latitude,
          'map.lng':longitude
        })
      }
    })
  },

  //点击工程状态后过滤工程
  fileterProject: function(event){
    var that = this;
    if (event.currentTarget.dataset.id == 1) {
      this.setData({
        first: '#eaeaea',
        firstcolor: 'purple',
        second:'#fafafa',
        secondcolor:'#000',
        third:'#fafafa',
        thirdcolor:'#000',
        fourth:'#fafafa',
        fourthcolor:'#000' 
            }) 
          }; 
    if(event.currentTarget.dataset.id == 2){ 
      this.setData({ 
        first: '#fafafa', 
        firstcolor:'#000', 
        second: '#eaeaea', 
        secondcolor: 'purple', 
        third: '#fafafa', 
        thirdcolor: '#000', 
        fourth: '#fafafa', 
        fourthcolor: '#000' 
      }) 
    }; 

    if (event.currentTarget.dataset.id == 3) {
      this.setData({
        first: '#fafafa',
        firstcolor: '#000',
        second: '#fafafa',
        secondcolor: '#000',
        third: '#eaeaea',
        thirdcolor: 'purple',
        fourth: '#fafafa',
        fourthcolor: '#000'
      })
    }; 

    if (event.currentTarget.dataset.id == 4) {
      this.setData({
        first: '#fafafa',
        firstcolor: '#000',
        second: '#fafafa',
        secondcolor: '#000',
        third: '#fafafa',
        thirdcolor: '#000',
        fourth: '#eaeaea',
        fourthcolor: 'purple'
      })
    }; 
    wx.request({
      url: 'http://47.105.114.70/getProjiect.do',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded' // 默认值
      },
      method: 'POST',
      data: {'prostatus': event.currentTarget.dataset.id},
      //调用成功后标记地图气泡
      success: function (res){
        that.setData({
          'map.markers': that.locationsList(res),
          isChecked: true
        })
      },
      
    })

  },

})