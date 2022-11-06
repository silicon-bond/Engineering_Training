<template>
  <div id="main">
    <div id="content">
      <div id="loginleft">
        <p id="welcome">Welcome</p>
        <p id="topic1">社区交流平台</p>
      </div>
      <div id="loginbox">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginBox"
}
</script>

<style scoped>
#main{
  width: 100%;
  height: 100%;
}
#content{
  position: absolute;
  top: 0;
  bottom: 0;
  width: 100%;
  background: url("../../assets/login.jpg") no-repeat;
  background-size:cover;
}
#loginleft{
  position: absolute;
  top: 50%;
  right: 50%;
  width: 30%;
  height:60%;
  background-color:rgba(255,255,255,0.3);
  transform: translate(0, -50%);
  box-shadow: 2px 2px 5px #909090;
}
#welcome{
  text-align: center;
  font-size: 40px;
  padding-top: 20%;
  margin-bottom: 0;
  opacity: 0.7;
  color: #ffffff;
  text-shadow: 5px 5px 5px #7090af;
}
#topic1{
  text-align: center;
  font-size: 40px;
  margin-bottom: 0;
  margin-top: 10px;
  opacity: 0.7;
  color: #ffffff;
  text-shadow: 5px 5px 5px #7090af;
}
#loginbox{
  position: absolute;
  top: 50%;
  left: 50%;
  width: 30%;
  height:60%;
  transform: translate(0, -50%);
  background-color: #f1f1f1;
  box-shadow: 2px 2px 5px #909090;
}

</style>
