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
                :label="item.country+item.networkName"
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
      telephone:'',
      networkId: '',
      labelPosition:'left',
      options: [

      ],
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
      this.$refs[message].validate((valid) => {
        if (valid) {
          let adviceMessage = {
            networkId:this.networkId,
            title: this.ruleForm.title,
            description:this.ruleForm.content,
            providerPhoneNumber:this.telephone
          }
          this.$axios({
            method: 'post',
            headers: {
              'Content-type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(adviceMessage),
            url: 'http://8.130.39.140:8081/express/api/deliveryman/feedback',
          }).then((response) => {          //这里使用了ES6的语法
            if (response.data.code === '1'){
              alert('发表成功');
              this.ruleForm.title = ''
              this.ruleForm.content = ''
            } else{
              alert('发表失败');
            }
          }).catch((error) => {
            console.log(error)       //请求失败返回的数据
          })
        } else {
          return false;
        }
      });
    },
    getnetwork(){
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        // data: JSON.stringify(info),
        url: 'http://8.130.39.140:8081/express/api/allNetworks',
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data)
        if (response.data.code==='1') {
          this.options = response.data.data
          let opt=this.options.filter((item) => {
            return item.networkId === this.networkId
          })
          this.options=opt
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },

  },
  created() {
    let info=JSON.parse(localStorage.getItem('userinfo_kuaidi'))
    this.telephone=info.telephone
    this.networkId=info.networkId
    this.getnetwork()
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
