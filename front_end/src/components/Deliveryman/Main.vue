<template>
  <div>
    <div id="head">
      <span id="title">快递员端</span>
      <el-button id="exit" @click="logout">注销</el-button>
    </div>
    <div id="main">
      <div id="sidebar">
        <el-menu
          style="border: 0"
          :default-active="this.$route.path"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-menu-item index="/deliveryman/logistics">
            <i class="el-icon-chat-line-square"></i>
            <span slot="title">网点物流信息</span>
          </el-menu-item>
          <el-menu-item index="/deliveryman/receivedLogistics">
            <i class="el-icon-chat-line-square"></i>
            <span slot="title">已接单物流信息</span>
          </el-menu-item>
          <el-menu-item index="/deliveryman/advice" >
            <i class="el-icon-reading"></i>
            <span slot="title">意见箱</span>
          </el-menu-item>
          <el-menu-item index="/deliveryman/personalCenter">
            <i class="el-icon-message"></i>
            <span slot="title">个人中心</span>
          </el-menu-item>
        </el-menu>
      </div>
      <div id="rightBox">
        <div id="right">
          <div>
            <router-view></router-view>
          </div>
        </div>
      </div>

    </div>

  </div>
</template>

<script>
export default {
  name: "Main",
  data(){
    return{
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleSelect(key, keyPath){
      console.log(key, keyPath);
      if (key === "/deliveryman/logistics") this.$router.push('/deliveryman/logistics')
      if (key === "/deliveryman/receivedLogistics") this.$router.push('/deliveryman/receivedLogistics')
      if (key === "/deliveryman/advice") this.$router.push('/deliveryman/advice')
      if (key === "/deliveryman/personalCenter") this.$router.push('/deliveryman/personalCenter')
    },
    logout(){
      localStorage.clear();
      this.$router.push('/')
    },

  },
  created() {
  },
}
</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

#head{
  position: absolute;
  width: 100%;
  height: 70px;
  background-color: #40454b;

}
#title{
  font-size: 30px;
  color: white;
  margin-left: 2%;
  line-height: 67px;
}
#main{
  position: absolute;
  height: calc(100% - 70px);
  width: 100%;
  top: 70px;
  bottom: 0;
}
#sidebar{
  position: absolute;
  width: 18%;
  height: 100%;
  overflow: hidden;
  background-color: #545c64;
}
#rightBox{
  width: 100%;
  height: 100%;
  background-color: #e2e2e2;
}
#right{
  position: absolute;
  width: 80%;
  top: 3%;
  bottom: 3%;
  right: 1%;
  background-color: white;
  overflow: auto;
}
#exit{
  float: right;
  margin-right: 20px;
  margin-top: 15px;
}
</style>
