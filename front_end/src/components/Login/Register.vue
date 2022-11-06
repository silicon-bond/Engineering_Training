<template>
  <div>
    <div id="messagebox">
      <p id="logintopic">注册</p>
      <el-form :model="information" :rules="rules" ref="information">
        <el-form-item prop="account">
          <el-input
            id="account"
            prefix-icon="el-icon-user"
            size="large"
            placeholder="请输入用户名"
            clearable
            required
            v-model="information.account"
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
        <el-form-item prop="twicePwd">
          <el-input
            id="twicePwd"
            prefix-icon="el-icon-lock"
            size="large"
            placeholder="请输入确认密码"
            show-password
            required
            v-model="information.twicePwd"
          ></el-input>
        </el-form-item>
      </el-form>
      <el-button @click="register('information')" id="registerbtn" type="primary" >注册</el-button>
      <el-button type="text" id="toLoginbtn" @click="toLogin">已有账号</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data(){
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.information.pwd) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return{
      information:{
        account: '',
        pwd:'',
        twicePwd:'',
      },
      rules:{
        account:[
          {required: true, message: '账号不能为空', trigger: ['blur', 'change']}
        ],
        pwd:[
          {required: true, message: '密码不能为空', trigger: 'blur'},
          { pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/, message: '密码必须同时包含数字与字母,且长度为 8-20位' }
        ],
        twicePwd: [
          {validator: validatePass2, trigger: 'blur'}
        ]
      },
    }
  },
  methods:{
    toLogin(){
      this.$router.push('/login/login')
    },

    register(message){
      let userMessage = {
        name:this.information.account,
        pwd:this.information.pwd
      }
      this.$refs[message].validate((valid) => {
        if (valid) {
          this.$axios({
            method: 'post',
            headers: {
              'Content-type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(userMessage),
            url: 'http://localhost:8081/javaee1_war_exploded/register/user',
          }).then((response) => {          //这里使用了ES6的语法
            console.log(response.data.data)
            if (response.data.code === '200'){
              alert("注册成功")
              this.$router.push('/login/login')
            }else {
              alert("用户名已被占用")
              this.$router.go(0)
            }
          }).catch((error) => {
            console.log(error)       //请求失败返回的数据
          })
        } else {
          console.log('error !!');
          return false;
        }
      });
    },
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
  margin-top: 0;
  margin-bottom: 20px;
}
#pwd{
  padding-top: 20px;
}
#registerbtn{
  width: 100%
}
#toLoginbtn{
  float: right;
}
</style>
