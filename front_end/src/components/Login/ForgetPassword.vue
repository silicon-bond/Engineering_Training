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
            style="width: 65%;float: left;border-radius: 4px 0 0 4px"
            id="valicodebox"
            prefix-icon="el-icon-lock"
            size="large"
            placeholder="请输入验证码"
            required
            v-model="information.valicode"
          ></el-input>
          <el-button :disabled="isdisable" @click="sendyzm" id="sendyzm" type="primary" style="font-size: 8px; padding-left: 2px; padding-right: 2px">
            {{yanzhenma}}
          </el-button>
          <div style="clear:both;"></div>
        </el-form-item>
        <el-form-item style="margin-bottom: 3px" prop="newPwd">
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
      <el-button type="text" id="toLoginbtn" @click="toLogin">已有账号</el-button>
      <div style="clear:both;"></div>
      <el-button type="primary" id="editConfirmbtn" @click="editConfirm('information')">确认修改</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ForgetPassword",
  data(){
    return{
      clock: null,
      isdisable: false,
      yanzhenma:'获取验证码',
      information:{
        email:'',
        valicode: '',
        newPwd:'',
      },
      time: 30,
      rules:{
        email:[
          {required: true, message: '邮箱不能为空', trigger: ['blur', 'change']}
        ],
        valicode:[
          {required: true, message: '验证码不能为空', trigger: ['blur', 'change']}
        ],
        newPwd:[
          {required: true, message: '新密码不能为空', trigger: 'blur'},
          {pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/, message: '新密码必须同时包含数字与字母,且长度为 8-20位' }
        ],
      },
    }
  },
  mounted() {
    if (localStorage.getItem('yanzhen1')) {
      this.isdisable = true;
      this.clock = setInterval(this.doLoop, 1000);
    }
  },
  methods:{
    toLogin(){
      this.$router.push('/login/login')
    },

    doLoop(){
      this.time--;
      if(this.time > 0){
        this.yanzhenma = this.time + 's后获取';
      } else{
        clearInterval(this.clock); //清除js定时器
        this.isdisable = false;
        this.yanzhenma = '获取验证码';
        this.time = 30; //重置时间
        localStorage.removeItem('yanzhen1')
      }
    },

    sendyzm(){//发送验证码
      let RegEmail = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
      if(RegEmail.test(this.information.email)) {
        this.$axios({
          method: 'get',
          headers: {
            'Content-type': 'application/json;charset=UTF-8'
          },
          url: 'http://8.130.39.140:8081/express/api/user/Captcha/'+this.information.email,
        }).then((response) => {          //这里使用了ES6的语法
          if (response.data.message === '发送成功') {
            this.$message({
              showClose: true,
              message: '验证码发送成功',
              type: 'success'
            });
            this.isdisable = true;
            localStorage.setItem('yanzhen1', 1)
            this.clock = setInterval(this.doLoop, 1000);
          } else {
            this.$message({
              showClose: true,
              message: '验证码发送失败',
              type: 'error'
            });
          }
        }).catch((error) => {
          console.log(error)       //请求失败返回的数据
          this.$message({
              showClose: true,
              message: '服务器出了点问题',
              type: 'error'
          });
        })
      } else {
        this.$message({
          showClose: true,
          message: '请输入正确的邮箱格式',
          type: 'warning'
        });
      }
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
            url: 'http://8.130.39.140:8081/express/user/Forgetpassword',
          }).then((response) => {          //这里使用了ES6的语法
            if (response.data.message==='修改成功') {
               this.$message({
                  showClose: true,
                  message: '修改密码成功！跳转至登陆页面...',
                  type: 'success',
                  duration: 2000
              });
              setTimeout(()=> {
                  this.$router.push('/login/login')
              }, 2000)
            } else {
              this.$message({
                  showClose: true,
                  message: '修改密码失败！',
                  type: 'error',
              });
            }
          }).catch((error) => {
            console.log(error)       //请求失败返回的数据
            this.$message({
                showClose: true,
                message: '服务器出了点问题',
                type: 'error'
            });
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
#editConfirmbtn {
  width: 100%;
}

#sendyzm{
  float: right;
  width: 30%;
}


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
