<template>
  <div>
    <div id="personalBox">
      <h2 id="personalTitle">个人信息</h2>
      <el-form ref="form" :model="form" label-width="80px" style="float: left;width: 40%" :rules="rules">
        <el-form-item label="昵称" prop="name">
          <el-input v-model="form.name" placeholder="请输入昵称"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="telephone">
          <el-input v-model="form.telephone" placeholder="请输入联系电话"></el-input>
        </el-form-item>
        <el-form-item label="省/市/区">
          <el-cascader
            size="large"
            :options="addressoptions"
            v-model="form.address">
          </el-cascader>
        </el-form-item>
        <el-form-item label="详细地址" prop="detailAddress">
          <el-input v-model="form.detailAddress" placeholder="请输入详细地址"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="SubmitClick('form')">确认修改</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { regionData,  CodeToText, TextToCode} from 'element-china-area-data';
export default {
  name: "PersonalCenter",
  data() {
    return {
      addressoptions:regionData,
      form: {
        name: '',
        telephone:'',
        address:null,
        detailAddress:''
      },
      rules:{
        name: [
          { required: true, message: '昵称不能为空', trigger: 'blur' },
        ],
        telephone:[
          { required: true, message: '联系电话不能为空', trigger: 'blur' },
        ],
        detailAddress:[
          { required: true, message: '详细地址不能为空', trigger: 'blur' },
        ]
      }
    }
  },
  methods: {
    SubmitClick(message) {
      this.$refs[message].validate((valid) => {
        if (valid) {
          this.$confirm('确定保存此修改?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.SubmitConfirm()
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
    SubmitConfirm() {
      let user = JSON.parse(localStorage.getItem("userinfo_kuaidi"))
      let userMessage = {
        userId:user.userId,
        username:this.form.name,
        phoneNumber:this.form.telephone,
        detailAddress:this.form.detailAddress,
        province:CodeToText[`${this.form.address[0]}`],
        municipal:CodeToText[`${this.form.address[1]}`],
        country:CodeToText[`${this.form.address[2]}`],
      }
      this.$axios({
        method: 'post',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(userMessage),
        url: 'http://localhost:8081/express/user/updateUser',
      }).then((response) => {          //这里使用了ES6的语法

        if (response.data.message==="修改成功"){
          this.$message({
            message: '保存个人信息成功',
            type: 'success'
          });
          this.$router.go(0)
        }else {
          this.$message.error('保存个人信息失败');
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    }
  },
  created() {
    let user = JSON.parse(localStorage.getItem("userinfo_kuaidi"))
    console.log(user)
    this.form.name = user.username
    this.form.telephone = user.phoneNumber
    this.form.detailAddress = user.detailAddress
    let  array = []
    array[0] = TextToCode[`${user.province}`].code
    array[1] = TextToCode[`${user.province}`][`${user.municipal}`].code
    array[2] = TextToCode[`${user.province}`][`${user.municipal}`][`${user.country}`].code
    this.form.address = array
  }
}
</script>

<style scoped>
#personalBox{
  margin-left: 2%;
}
</style>

