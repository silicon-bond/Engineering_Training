<template>
  <div>
    <div id="messagebox">
      <p id="logintopic">登录</p>
      <el-form :model="information" :rules="rules" ref="information">
        <el-form-item prop="account">
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
          {required: true, message: '密码不能为空',trigger: ['blur', 'change']}
        ]
      },
    }
  },
  methods: {
    login(message){
      let userMessage = {
        email: '123456@qq.com',
        password:'123456'
      }
      this.$refs[message].validate((valid) => {
        if (valid) {
          this.$axios({
            method: 'post',
            headers: {
              'Content-type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(userMessage),
            url: 'http://8.130.39.140:8080/express/api/login',
          }).then((response) => {          //这里使用了ES6的语法
            alert(response)
            // if (response.data.code === '200'){
            //   alert('登录成功！');
            //   localStorage.setItem('id',response.data.data.id)
            //   localStorage.setItem('account',response.data.data.name)
            //   this.$router.push('/user')
            // } else {
            //   alert('用户名或密码错误!');
            // }
          }).catch((error) => {
            console.log(error)       //请求失败返回的数据
          })
        } else {
          console.log('error login!!');
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
  font-size: 40px;
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
