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

        <el-form-item prop="vcd">
          <el-input
            id="vcd"
            prefix-icon="el-icon-lock"
            size="large"
            placeholder="请输入验证码"
            show-password
            required
            v-model="information.twicePwd"
          ></el-input>
        </el-form-item>

        <el-form-item prop="newpsd">
          <el-input
            id="newpsd"
            prefix-icon="el-icon-lock"
            size="large"
            placeholder="请输入新密码"
            show-password
            required
            v-model="information.twicePwd"
          ></el-input>
        </el-form-item>

      </el-form>
      <el-button @click="toLogin" id="registerbtn" type="primary" >确定</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ForgetPassword",
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
        email:'',
        vcd:'',
        newpsd:'',

      },
      rules:{
        account:[
          {required: true, message: '用户名不能为空', trigger: ['blur', 'change']}
        ],
        vcd:[
          {required: true, message: '验证码不能为空', trigger: ['blur', 'change']}
        ],
        email:[
          {required: true, message: '邮箱不能为空', trigger: ['blur', 'change']}
        ],
        newpwd:[
          {required: true, message: '密码不能为空', trigger: 'blur'},
          { pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/, message: '密码必须同时包含数字与字母,且长度为 8-20位' }
        ],
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
