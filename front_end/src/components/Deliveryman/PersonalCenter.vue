<!--<template>-->
<!--  <div>-->
<!--    快递员端4-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "PersonalCenter"-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->
<template>
  <div>
    <div id="personalBox">
      <h2 id="personalTitle">个人信息</h2>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" style="float: left;width: 40%">
        <el-form-item label="姓名">
          <el-input v-model="form.name"  placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="telephone">
          <el-input v-model="form.telephone" placeholder="请输入联系电话"></el-input>
        </el-form-item>
        <el-form-item label="工号">
          <el-input v-model="form.deliverymanId" readonly placeholder="请输入工号"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" readonly placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item label="所属网点">
          <el-input v-model="form.network" readonly placeholder="请输入地址"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmitclick('form')">修改信息</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "PersonalCenter",
  data() {
    return {
      form: {
        name: '',
        telephone:'',
        deliverymanId:'',
        email:'',
        network:'',

      },
      rules: {
        telephone: [
          { pattern:/^1[3|4|5|7|8][0-9]{9}$/,message: '请输入正确的手机号码',trigger: 'change' },
          { required: true, message: '电话号码不能为空', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    // onSubmitclick(){
    //   this.$confirm('确定修改个人信息?', '提示', {
    //     confirmButtonText: '确定',
    //     cancelButtonText: '取消',
    //     type: 'warning'
    //   }).then(() => {
    //     onSubmit()
    //   }).catch(() => {
    //     this.$message({
    //       type: 'info',
    //       message: '已取消修改'
    //     });
    //   });
    // },
    onSubmitclick(message){
      this.$refs[message].validate((valid) => {
        if (valid) {
          this.$confirm('确定修改个人信息?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.onSubmit()
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消修改'
            });
          });
        } else {
          return false;
        }
      });
    },
    onSubmit() {
      let user = JSON.parse(localStorage.getItem("userinfo_kuaidi"))
      user.username=this.form.name
      user.Phonenumber=this.form.telephone
      console.log(user)
      this.$axios({
        method: 'put',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(user),
        url: 'http://8.130.39.140:8081/express/api/deliveryman',
      }).then((response) => {          //这里使用了ES6的语法
        if (response.data.message==="success"){
          this.$message({
            message: '保存个人信息成功',
            type: 'success'
          });
          localStorage.setItem('userinfo_kuaidi',JSON.stringify(user))
          setTimeout(()=> {
            this.$router.go(0)
          }, 1000)

        }else {
          this.$message.error('保存个人信息失败');
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },
  },
  created() {
    let user = JSON.parse(localStorage.getItem("userinfo_kuaidi"))
    this.form.name=user.username
    this.form.telephone=user.phoneNumber
    this.form.deliverymanId=user.deliverymanId
    this.form.email=user.email
    this.form.network=user.network.municipal+user.network.networkName

  }
}
</script>

<style scoped>
#personalBox{
  margin-left: 2%;
}
</style>

