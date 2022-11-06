<template>
  <div>
    <el-button @click="goback">返回</el-button>
    <div id="messageBox">
      <div id="mesghead">
        <span id="headtitle">房产出租信息</span>
      </div>
      <div id="mesgtitle">
        {{ information.title }}
      </div>
      <el-divider></el-divider>
      <div id="message">
        <p>城市：{{ information.city }}</p>
        <p>地址：{{information.address}}</p>
        <p>户型：{{information.size}}</p>
        <p>面积：{{information.area}}㎡</p>
        <p>装修：{{information.decoration}}</p>
        <p>租金：{{information.price}}元/月</p>
        <p>配套：{{information.equipment}}</p>
        <p>联系人：{{information.owner}}</p>
        <p>联系电话：{{information.telephone}}</p>
        <p>发布时间：{{information.releasedAt}}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RentDetail",
  data(){
    return{
      information: {
        title:'',
        size:'',
        area:'',
        price:'',
        releasedAt:'',
        address:'',
        city:'',
        type:'',
        decoration:'',
        equipment:'',
        telephone:'',
        owner:''
      },
    }
  },
  methods:{
    goback(){
      this.$router.push('/user/houserent')
    }
  },
  created() {
    this.information.title = localStorage.getItem('r-title')
    this.information.size = localStorage.getItem('r-size')
    this.information.area = localStorage.getItem('r-area')
    this.information.price = localStorage.getItem('r-price')
    this.information.releasedAt = localStorage.getItem('r-releasedAt')
    this.information.address = localStorage.getItem('r-address')
    this.information.city = localStorage.getItem('r-city')
    this.information.type = localStorage.getItem('r-type')
    this.information.decoration = localStorage.getItem('r-decoration')
    this.information.equipment = localStorage.getItem('r-equipment')
    this.information.telephone = localStorage.getItem('r-telephone')
    this.information.owner = localStorage.getItem('r-owner')
  }
}
</script>

<style scoped>
p{
  font-size: 16px;
  margin-left: 30px;
}
#messageBox{
  border-radius: 20px;
  box-shadow: 2px 6px 10px #d9d9f5;
  height: 560px;
  margin-left: 7%;
  margin-right: 7%;
}
#mesghead{
  width: 100%;
  height: 50px;
  background-color: #eeeeee;
  border-radius: 20px 20px 0px 0px;
}
#headtitle{
  font-size: 17px;
  font-weight: bold;
  margin-left: 3%;
  line-height: 300%;
}
#mesgtitle{
  color: red;
  margin-left: 30px;
  height: 50px;
  line-height: 300%;
  font-size: 22px;
}
</style>
