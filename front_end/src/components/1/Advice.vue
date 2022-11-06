<template>
  <div>
    <div id="messageBox">
      <div id="mesghead">
        <span id="headtitle">发表意见</span>
      </div>
      <div id="message">
        <el-form :model="ruleForm" :label-position="labelPosition" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="意见标题" prop="title">
            <el-input v-model="ruleForm.title"></el-input>
          </el-form-item>
          <el-form-item label="意见内容" prop="content">
            <el-input v-model="ruleForm.content" type="textarea" :rows="6"></el-input>
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
      ruleForm: {
        title:'',
        content:'',
      },
      rules: {
        title: [
          { required: true, message: '请输入意见标题', trigger: 'blur' },
        ],
        content: [
          { required: true, message: '请输入意见内容', trigger: 'blur' }
        ],

      }
    }
  },
  methods:{
    submitForm(message){
      this.$refs[message].validate((valid) => {
        if (valid) {
          let adviceMessage = {
            account:localStorage.getItem('account'),
            title: this.ruleForm.title,
            content:this.ruleForm.content
          }
          this.$axios({
            method: 'post',
            headers: {
              'Content-type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(adviceMessage),
            url: 'http://localhost:8081/javaee1_war_exploded/advice/insert',
          }).then((response) => {          //这里使用了ES6的语法
            if (response.data.code === '200'){
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
  }
}
</script>

<style scoped>
#messageBox{
  border-radius: 20px;
  box-shadow: 2px 6px 10px #d9d9f5;
  margin-top: 20px;
  margin-left: 7%;
  margin-right: 7%;
}
#mesghead{
  width: 100%;
  height: 50px;
  background-color: #eeeeee;
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
