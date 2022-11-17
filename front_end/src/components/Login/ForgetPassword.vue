<!--<template>-->
<!--<div>-->
<!--  忘记密码-->
<!--</div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "ForgetPassword"-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->
<template>
  <div>
    <div id="messagebox">
      <p id="logintopic">找回密码</p>
      <el-form :model="information" :rules="rules" ref="information">

        <el-form-item prop="email">
          <el-input
            id="email"
            prefix-icon="el-icon-lock"
            size="large"
            placeholder="请输入邮箱"
            show-password
            required
            v-model="information.email"
          ></el-input>
        </el-form-item>

      </el-form>
      <el-button @click="toValidate" id="registerbtn" type="primary" >发送验证码</el-button>
      <el-button type="text" id="toLoginbtn" @click="toLogin">返回上一级</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ForgetPassword",
  data(){
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮箱'));
      } else if (value !== this.information.email) {
        callback(new Error('输入邮箱与认证邮箱不一致!'));
      }
        else {
        callback();
      }
    };
    return{
      information:{
        email:'',
      },
      rules:{
        email:[
          {required: true, message: '邮箱不能为空',validator: validatePass2, trigger: ['blur', 'change']}
        ],
        // newpwd:[
        //   {required: true, message: '密码不能为空', trigger: 'blur'},
        //   { pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/, message: '密码必须同时包含数字与字母,且长度为 8-20位' }
        // ],
      },
    }
  },
  methods:{
    toLogin(){
      this.$router.push('/login/login')
    },
    toValidate(){
      this.$router.push('/login/validate')
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
