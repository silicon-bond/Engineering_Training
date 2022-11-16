<template>
  <div>
    <div id="head">
      <span id="title">系统管理员端</span>
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
          <el-menu-item index="/system/logistics">
            <i class="el-icon-chat-line-square"></i>
            <span slot="title">物流信息管理</span>
          </el-menu-item>
          <el-menu-item index="/system/personList">
            <i class="el-icon-chat-line-square"></i>
            <span slot="title">人员管理</span>
          </el-menu-item>
          <el-menu-item index="/system/branchList">
            <i class="el-icon-chat-line-square"></i>
            <span slot="title">网点管理</span>
          </el-menu-item>
          <el-menu-item index="/system/adviceList" >
            <i class="el-icon-reading"></i>
            <span slot="title">意见箱管理</span>
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
      if (key === "/system/logistics") this.$router.push('/system/logistics')
      if (key === "/system/personList") this.$router.push('/system/personList')
      if (key === "/system/branchList") this.$router.push('/system/branchList')
      if (key === "/system/adviceList") this.$router.push('/system/adviceList')
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
  width: 100%;
  top: 70px;
  bottom: 0;
}
#sidebar{
  position: absolute;
  top: 0;
  bottom: 0;
  width: 18%;
  overflow: hidden;
  background-color: #545c64;
}
#right{
  position: absolute;
  width: 80%;
  top: 15px;
  bottom: 0;
  right: 1%;
  background-color: white;
}
#rightBox{
  width: 100%;
  height: 100%;
  background-color: #e2e2e2;
  /*background-color: #95b695;*/
}
#exit{
  float: right;
  margin-right: 20px;
  margin-top: 15px;
}
</style>
