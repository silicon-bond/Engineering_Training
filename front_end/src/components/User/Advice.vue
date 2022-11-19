<template>
  <div>
    <div id="messageBox">
      <div id="mesghead">
        <span id="headtitle">发表意见</span>
      </div>
      <div id="message">
        <el-form :model="ruleForm" :label-position="labelPosition" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="意见标题" prop="title">
            <el-input v-model="ruleForm.title" placeholder="请输入意见标题"></el-input>
          </el-form-item>
          <el-form-item label="意见内容" prop="content">
            <el-input v-model="ruleForm.content" type="textarea" :rows="6" placeholder="请输入意见内容"></el-input>
          </el-form-item>
          <el-form-item label="所属网点" prop="branch">
            <el-select v-model="ruleForm.branch" placeholder="请选择网点">
              <el-option
                v-for="item in options"
                :key="item.networkId"
                :label="item.municipal+item.networkName"
                :value="item.networkId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-divider></el-divider>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')" id="submibtn">提交</el-button>
          </el-form-item>
        </el-form>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Advice",
  data(){
    return{
      labelPosition:'left',
      options: [],
      ruleForm: {
        title:'',
        content:'',
        branch:''
      },
      rules: {
        title: [
          { required: true, message: '请输入意见标题', trigger: 'blur' },
        ],
        content: [
          { required: true, message: '请输入意见内容', trigger: 'blur' }
        ],
        branch: [
          { required: true, message: '请选择所属网点', trigger: 'change' }
        ]
      }
    }
  },
  methods:{
    submitForm(message){
      let user = JSON.parse(localStorage.getItem("userinfo_kuaidi"))
      this.$refs[message].validate((valid) => {
        if (valid) {
          this.$axios({
            method: 'get',
            headers: {
              'Content-type': 'application/json;charset=UTF-8'
            },
            url: 'http://localhost:8081/express/user/FeedBack?title='+this.ruleForm.title+'&description='+this.ruleForm.content+'&phoneNum='+user.phoneNumber+'&networkId='+this.ruleForm.branch,
          }).then((response) => {          //这里使用了ES6的语法
            alert('发表意见成功')
            this.$router.go(0)
          }).catch((error) => {
            console.log(error)       //请求失败返回的数据
          })
        } else {
          return false;
        }
      });
    },
    getAllBranch(){
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        url: 'http://8.130.39.140:8081/express/api/allNetworks',
      }).then((response) => {          //这里使用了ES6的语法
        this.options = response.data.data
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },
  },
  created() {
    this.getAllBranch()
  }
}
</script>

<style scoped>
#messageBox{
  border-radius: 20px;
  box-shadow: 2px 6px 10px #d9d9f5;
  margin-top: 50px;
  margin-left: 7%;
  margin-right: 7%;
}
#mesghead{
  width: 100%;
  height: 50px;
  background-color: #efefef;
  border-radius: 20px 20px 0px 0px;
}
#headtitle{
  font-size: 17px;
  font-weight: bold;
  margin-left: 3%;
  line-height: 300%;
}
#message{
  width: 80%;
  margin-top: 3%;
  margin-left: 5%;
}
#submibtn{
  margin-left: 40%;
  margin-bottom: 20px;
}
</style>
