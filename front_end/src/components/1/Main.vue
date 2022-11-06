<template>
  <div>
    <div id="head">
      <span id="title">社区交流平台</span>
      <el-button id="exit" @click="logout">注销</el-button>
    </div>
    <div id="main">
      <div id="sidebar">
        <el-menu
          default-active="/user/notice"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-menu-item index="/user/notice">
            <i class="el-icon-chat-line-square"></i>
            <span slot="title">通知消息</span>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-document"></i>
              <span>房产信息</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/user/houserent" >房屋出租信息</el-menu-item>
              <el-menu-item index="/user/housesell">房屋出售信息</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-menu-item index="/user/comment" >
            <i class="el-icon-reading"></i>
            <span slot="title">社区论坛</span>
          </el-menu-item>
          <el-menu-item index="/user/advice">
            <i class="el-icon-message"></i>
            <span slot="title">意见箱</span>
          </el-menu-item>
        </el-menu>
      </div>
      <div id="right">
        <div>
          <router-view></router-view>
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
      if (key === "/user/notice") this.$router.push('/user/notice')
      if (key === "/user/houserent") this.$router.push('/user/houserent')
      if (key === "/user/housesell") this.$router.push('/user/housesell')
      if (key === "/user/comment") this.$router.push('/user/comment')
      if (key === "/user/advice") this.$router.push('/user/advice')
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
}
#exit{
  float: right;
  margin-right: 20px;
  margin-top: 15px;
}
</style>
