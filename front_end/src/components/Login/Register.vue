<template>
  <div>
    <div id="messagebox">
      <p id="logintopic">注册</p>
      <div id="charactorBox">
        <el-radio v-model="charactor" label="1">用户</el-radio>
        <el-radio v-model="charactor" label="2">快递员</el-radio>
      </div>
      <el-form :model="information" :rules="Rules" ref="information">
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
        <el-form-item prop="phone">
          <el-input
            id="phone"
            prefix-icon="el-icon-user"
            size="large"
            placeholder="请输入手机号"
            clearable
            required
            v-model="information.phone"
          ></el-input>
        </el-form-item>
        <el-form-item prop="yzm">
          <el-input
            id="yzm"
            style="width: 65%;float: left;border-radius: 4px 0 0 4px"
            prefix-icon="el-icon-user"
            size="large"
            placeholder="请输入验证码"
            clearable
            required
            v-model="information.yzm"
          ></el-input>
          <el-button type="primary" @click="sendyzm" style=" float: right; width: 30%;font-size: 8px; padding-left: 2px; padding-right: 2px">
            发送验证码
          </el-button>
          <div style="clear:both;"></div>
        </el-form-item>

        <el-form-item prop="branch" v-if="charactor==='2'">
          <el-select v-model="information.branch" placeholder="请选择所属网点" style="width: 100%">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
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
      options: [{
        value: '1',
        label: '福州网点'
      }, {
        value: '2',
        label: '厦门网点'
      }, {
        value: '3',
        label: '莆田网点'
      }, {
        value: '4',
        label: '漳州网点'
      }, {
        value: '5',
        label: '泉州网点'
      }],

      charactor:'1',
      information:{
        email:'',
        phone: '',
        pwd:'',
        twicePwd:'',
        branch:'',
        yzm:'',
      },
      Rules:{
        email:[
          {required: true, message: '邮箱不能为空', trigger: ['blur', 'change']}
        ],
        phone:[
          {required: true, message: '手机号不能为空', trigger: ['blur', 'change']}
        ],
        pwd:[
          {required: true, message: '密码不能为空', trigger: 'blur'},
          { pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/, message: '密码必须同时包含数字与字母,且长度为 8-20位' }
        ],
        twicePwd: [
          {validator: validatePass2, trigger: 'blur'}
        ],
        branch:[
          {required: true, message: '所属网点不能为空', trigger: ['blur', 'change']}
        ]
      },
    }
  },
  mounted() {

  },
  methods:{
    toLogin(){
      this.$router.push('/login/login')
    },
    sendyzm(){
      let RegEmail = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
      if(RegEmail.test(this.information.email)) {
        this.$axios({
          method: 'get',
          headers: {
            'Content-type': 'application/json;charset=UTF-8'
          },
          url: 'http://8.130.39.140:8081/express/api/user/Captcha/'+this.information.email,
        }).then((response) => {          //这里使用了ES6的语法
          this.$message({
            showClose: true,
            message: '验证码发送成功',
            type: 'success'
          });
        }).catch((error) => {
          console.log(error)       //请求失败返回的数据
          this.$message({
              showClose: true,
              message: '验证码发送失败',
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
    register(message){
      const that = this
      if (this.charactor==='1'){//用户注册
        let userMessage = {
          phoneNum:this.information.phone.toString(),
          email:this.information.email.toString(),
          password:this.information.pwd.toString(),
          captcha:this.information.yzm.toString()
        }
        this.$refs[message].validate((valid) => {
          if (valid) {
            this.$axios({
              method: 'post',
              headers: {
                'Content-type': 'application/json;charset=UTF-8'
              },
              data: JSON.stringify(userMessage),
              url: 'http://8.130.39.140:8081/express/user/Register',
            }).then((response) => {          //这里使用了ES6的语法
              console.log(response)
              if (response.data.message === '用户已注册'){
                this.$message({
                    showClose: true,
                    message: '邮箱已被注册！',
                    type: 'error'
                });
                // this.$router.push('/login/login')
              }else if(response.data.message === '验证码错误') {
                this.$message({
                    showClose: true,
                    message: '验证码错误！',
                    type: 'error'
                });
              }  else {
                this.$message({
                    showClose: true,
                    message: '注册成功！正在跳转',
                    type: 'success'
                });
              }
            }).catch((error) => {
              console.log(error)       //请求失败返回的数据
              this.$message({
                  showClose: true,
                  message: '网络出了点问题',
                  type: 'error'
              });
            })
          } else {
            console.log('error !!');
            return false;
          }
        });
      }else {//快递员注册
        let deliverymanMessage = {
          phoneNumber:this.information.phone.toString(),
          email:this.information.email.toString(),
          password:this.information.pwd.toString(),
          networkId:this.information.branch.toString(),
          captcha:this.information.yzm.toString()
        }
        this.$refs[message].validate((valid) => {
          if (valid) {
            this.$axios({
              method: 'post',
              headers: {
                'Content-type': 'application/json;charset=UTF-8'
              },
              data: JSON.stringify(deliverymanMessage),
              url: 'http://8.130.39.140:8081/express/api/deliveryman/register',
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

    },
  }
}
</script>

<style scoped>
#charactorBox{
  margin-left: 25%;
  margin-bottom: 10px;
}
#messagebox{
  position: absolute;
  top: 7%;
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
  margin-top: 20px;
  width: 100%
}
#toLoginbtn{
  float: right;
}

.el-form {
  height: 49%;
  overflow: auto;
}

.el-form-item {
  margin-bottom: 20px;
}

</style>
