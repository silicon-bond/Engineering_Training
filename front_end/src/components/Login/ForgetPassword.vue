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
            required
            v-model="information.email"
          ></el-input>
        </el-form-item>
        <el-form-item prop="valicode">
          <el-input
            id="valicode"
            prefix-icon="el-icon-lock"
            size="large"
            placeholder="请输入验证码"
            required
            v-model="information.valicode"
          ></el-input>
        </el-form-item>
        <el-button @click="sendyzm" id="sendyzm" type="primary" >发送验证码</el-button>
        <el-form-item prop="newPwd">
          <el-input
            id="newPwd"
            prefix-icon="el-icon-lock"
            size="large"
            placeholder="请输入新密码"
            show-password
            required
            v-model="information.newPwd"
          ></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" id="editConfirmbtn" @click="editConfirm('information')">确认修改</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ForgetPassword",
  data(){
    return{
      information:{
        email:'',
        valicode: '',
        newPwd:'',
      },
      rules:{
        email:[
          {required: true, message: '邮箱不能为空', trigger: ['blur', 'change']}
        ],
        valicode:[
          {required: true, message: '验证码不能为空', trigger: ['blur', 'change']}
        ],
        newpwd:[
          {required: true, message: '新密码不能为空', trigger: 'blur'},
          { pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/, message: '新密码必须同时包含数字与字母,且长度为 8-20位' }
        ],
      },
    }
  },
  methods:{
    toLogin(){
      this.$router.push('/login/login')
    },

    sendyzm(){
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        url: 'http://localhost:8081/express/api/user/Captcha/'+this.information.email,
      }).then((response) => {          //这里使用了ES6的语法
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
    },
    editConfirm(message){
      let userMessage = {
        email:this.information.email.toString(),
        password:this.information.newPwd.toString(),
        captcha:this.information.valicode.toString()
      }
      this.$refs[message].validate((valid) => {
        if (valid) {
          this.$axios({
            method: 'post',
            headers: {
              'Content-type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(userMessage),
            url: 'http://localhost:8081/express/user/Forgetpassword',
          }).then((response) => {          //这里使用了ES6的语法
            console.log(response.data.data)
            // if (response.data.code === '200'){
            //   alert("注册成功")
            //   this.$router.push('/login/login')
            // }else {
            //   alert("用户名已被占用")
            //   this.$router.go(0)
            // }
          }).catch((error) => {
            console.log(error)       //请求失败返回的数据
          })
        } else {
          console.log('error !!');
          return false;
        }
      });
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
