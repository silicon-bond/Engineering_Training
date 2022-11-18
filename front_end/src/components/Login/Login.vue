<template>
  <div>
    <div id="messagebox">
      <p id="logintopic">登录</p>
      <el-form :model="information" :rules="rules" ref="information">
        <el-form-item prop="email">
          <el-input
            id="email"
            prefix-icon="el-icon-user"
            size="large"
            placeholder="请输入邮箱"
            clearable
            required
            v-model="information.email"
          ></el-input>
        </el-form-item>
        <el-form-item prop="pwd">
          <el-input
            id="pwd"
            prefix-icon="el-icon-lock"
            size="large"
            placeholder="请输入密码"
            show-password
            required
            v-model="information.pwd"
          ></el-input>
        </el-form-item>
      </el-form>
      <el-button @click="login('information')" id="loginbtn" type="primary" >登录</el-button>
      <el-button type="text" id="toForgetbtn" @click="toForget">忘记密码</el-button>
      <el-button type="text" id="toRegisterbtn" @click="toRegister">我要注册</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      information:{
        email: '',
        pwd:''
      },
      rules:{
        email:[
          {required: true, message: '邮箱不能为空', trigger: ['blur', 'change']}
        ],
        pwd:[
          {required: true, message: '密码不能为空', trigger: ['blur', 'change']}
        ]
      },
    }
  },
  methods: {
    login(message){
      let userMessage = {
        email: this.information.email,
        password:this.information.pwd
      }
      this.$refs[message].validate((valid) => {
        if (valid) {
          this.$axios({
            method: 'post',
            headers: {
              'Content-type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(userMessage),
            url: 'http://8.130.39.140:8081/express/api/login',
          }).then((response) => {          //这里使用了ES6的语法
            console.log(response.data)
            if (response.data.message === "success"){
              this.$message({
                showClose: true,
                message: '登陆成功，跳转中...',
                type: 'success'
              });

              localStorage.setItem('userinfo_kuaidi',JSON.stringify(response.data.data))

              setTimeout(() => {
                if (response.data.data.userId != undefined) {
                  this.$router.push('/user')
                } else if (response.data.data.deliverymanId != undefined) {
                  this.$router.push('/deliveryman')
                } else if (response.data.data.networkAdministratorId != undefined) {
                  this.$router.push('/branch')
                } else {
                  this.$router.push('/system')
                }
              }, 2000)

              // if (response.data.data.userId)

              // var user = JSON.parse(localStorage.getItem("userinfo_kuaidi"))
              // this.$router.push('/user')
            } else {
              this.$message({
                showClose: true,
                message: '用户名密码错误',
                type: 'error'
              });
            }
          }).catch((error) => {
            console.log(error)
            this.$message({
                showClose: true,
                message: '网络出了点问题',
                type: 'error'
            });
                 //请求失败返回的数据
          })
        } else {
          console.log('error login!!');
          // this.$message({
          //       showClose: true,
          //       message: '有信息未填写',
          //       type: 'error'
          // });
          return false;
        }
      });
    },

    toRegister(){
      this.$router.push('/login/register')
    },
    toForget(){
      this.$router.push('/login/forgetPassword')
    }
  }
}
</script>

<style scoped>
#messagebox{
  position: absolute;
  top: 15%;
  bottom: 10%;
  left: 20%;
  right: 20%;
  background-color: #f1f1f1;
}
#logintopic{
  text-align: center;
  font-size: 30px;
  color: #616161;
  margin-top: 20px;
  margin-bottom: 20px;
}
#pwd{
  padding-top: 20px;
}
#loginbtn{
  width: 100%
}
#toRegisterbtn{
  float: right;
}
</style>
