<template>
  <div>
    <h2 id="mailTitle">寄快递</h2>

    <div id="senderMessageBox">
      <div id="senderMessageHead">
        <span id="headTitle">寄件人信息</span>
      </div>
      <div id="senderMessage">
        <el-form :model="Form" :label-position="labelPosition" :rules="Rules" ref="Rules1" label-width="100px" class="demo-ruleForm">
          <el-form-item label="姓名" prop="senderName">
            <el-input v-model="Form.senderName" placeholder="请输入寄件人姓名"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="senderTelephone">
            <el-input v-model="Form.senderTelephone" placeholder="请输入寄件人联系电话"></el-input>
          </el-form-item>
          <el-form-item label="普通地址">
            <el-cascader
            size="large"
            :options="jijianoptions"
            v-model="Form.senderSSQ"
            @change="jijianhandleChange">
            </el-cascader>
          </el-form-item>

          <el-form-item label="详细地址" prop="senderAddress">
            <el-input v-model="Form.senderAddress" placeholder="请输入寄件人地址"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="note">
            <el-input v-model="Form.note" placeholder="请输入备注信息"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <el-divider></el-divider>
    </div>

    <div id="recipientMessageBox">
      <div id="recipientMessageHead">
        <span id="recipientHeadTitle">收件人信息</span>
      </div>
      <div id="recipientMessage">
        <el-form :model="Form" :label-position="labelPosition" :rules="Rules" ref="Rules2" label-width="100px" class="demo-ruleForm">
          <el-form-item label="姓名" prop="recipientName">
            <el-input v-model="Form.recipientName" placeholder="请输入收件人姓名"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="recipientTelephone">
            <el-input v-model="Form.recipientTelephone" placeholder="请输入收件人联系电话"></el-input>
          </el-form-item>
          <el-form-item label="普通地址">
            <el-cascader
            size="large"
            :options="shoujianoptions"
            v-model="Form.recipientSSQ"
            @change="shoujianhandleChange">
            </el-cascader>
          </el-form-item>
          <el-form-item label="详细地址" prop="recipientAddress">
            <el-input v-model="Form.recipientAddress" placeholder="请输入收件人地址"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <el-divider></el-divider>
    </div>

    <div id="submitBox">
      <el-form :inline="true" :model="Form" :label-position="labelPosition" :rules="Rules" ref="Rules3" label-width="100px" class="demo-ruleForm">
        <el-form-item prop="value">
          <el-select filterable v-model="Form.value" placeholder="请选择网点">
            <el-option
              v-for="item in options"
              :key="item.networkId"
              :label="item.country+item.networkName"
              :value="item.networkId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit" id="submibtn">确认</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
 import { regionData,  CodeToText, TextToCode} from 'element-china-area-data';
export default {
  name: "Mail",
  data(){
    return{
      jijianselectedOptions: [],
      shoujianselectedOptions: [],
      jijianoptions: regionData,
      shoujianoptions: regionData,
      labelPosition:'left',
      options: [],
      Form: {
        senderSSQ: null,
        senderName:'',
        senderTelephone:'',
        senderAddress:'',
        recipientSSQ: null,
        recipientName:'',
        recipientTelephone:'',
        recipientAddress:'',
        value:'',
        note:'',
      },

      Rules: {
        senderName: [
          { required: true, message: '请输入寄件人姓名', trigger: 'blur' },
        ],
        senderTelephone: [
          { required: true, message: '请输入寄件人联系电话', trigger: 'blur' }
        ],
        senderptAddress: [
          { required: true, message: '请选择寄件人地址', trigger: 'blur' }
        ],
        senderAddress: [
          { required: true, message: '请输入寄件人地址', trigger: 'blur' }
        ],
        recipientName: [
          { required: true, message: '请输入收件人姓名', trigger: 'blur' },
        ],
        recipientTelephone: [
          { required: true, message: '请输入收件人联系电话', trigger: 'blur' }
        ],
        recipientAddress: [
          { required: true, message: '请输入收件人地址', trigger: 'blur' }
        ],
        value:[
          { required: true, message: '请选择网点', trigger: 'blur' }
        ],
        note:[
          { required: true, message: '请输入备注信息', trigger: 'blur' }
        ],
      },
    }
  },
  methods:{
    jijianhandleChange (value) {
        console.log(value)
        console.log(CodeToText[`${this.Form.senderSSQ[0]}`]);
        console.log(CodeToText[`${this.Form.senderSSQ[1]}`]);
        console.log(CodeToText[`${this.Form.senderSSQ[2]}`]);
    },
    shoujianhandleChange (value) {
        console.log(value)
        console.log(CodeToText[`${this.Form.recipientSSQ[0]}`]);
        console.log(CodeToText[`${this.Form.recipientSSQ[1]}`]);
        console.log(CodeToText[`${this.Form.recipientSSQ[2]}`]);
    },

    submit(){
      const form1=new Promise((resolve,reject)=>{
        this.$refs['Rules1'].validate(valid => {
          if(valid) resolve()
        })
      })
      const form2=new Promise((resolve,reject)=>{
        this.$refs['Rules2'].validate(valid => {
          if(valid) resolve()
        })
      })
      const form3=new Promise((resolve,reject)=>{
        this.$refs['Rules3'].validate(valid => {
          if(valid) resolve()
        })
      })
      Promise.all([form1,form2,form3]).then(()=>{
        this.$confirm('确定提交此修改?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.submitForm()
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消修改'
          });
        });
      })
    },
    submitForm(){
      let mailMessage = {
        deliverName:this.Form.senderName,
        deliverPhoneNumber:this.Form.senderTelephone,
        deliverDetailAddress:this.Form.senderAddress,
        description:this.Form.note,
        receiptName:this.Form.recipientName,
        receiptPhoneNumber:this.Form.recipientTelephone,
        deliverProvince:CodeToText[`${this.Form.senderSSQ[0]}`],
        deliverMunicipal:CodeToText[`${this.Form.senderSSQ[1]}`],
        deliverCountry:CodeToText[`${this.Form.senderSSQ[2]}`],
        receiptProvince:CodeToText[`${this.Form.recipientSSQ[0]}`],
        receiptMunicipal:CodeToText[`${this.Form.recipientSSQ[1]}`],
        receiptCountry:CodeToText[`${this.Form.recipientSSQ[2]}`],
        receiptDetailAddress:this.Form.recipientAddress,
        networkId:this.Form.value
      }
      this.$axios({
        method: 'post',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(mailMessage),
        url: 'http://localhost:8081/express/user/addExpress',
      }).then((response) => {          //这里使用了ES6的语法
        alert("寄件成功")
        this.$router.push('/user/logistics')
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
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
#mailTitle{
  margin-left: 2%;
}
#senderMessageBox{
  box-shadow: 0px 0px 2px #d9d9f5;
  margin-top: 20px;
  margin-left: 2%;
  margin-right: 2%;
}
#senderMessageHead{
  width: 100%;
  height: 50px;
  background-color: #efefef;
}
#headTitle{
  font-size: 17px;
  font-weight: bold;
  margin-left: 2%;
  line-height: 300%;
}
#senderMessage{
  width: 80%;
  margin-top: 1%;
  margin-left: 5%;
  margin-bottom: 2%;
}
#recipientMessageBox{
  box-shadow: 0px 0px 2px #d9d9f5;
  margin-top: 20px;
  margin-left: 2%;
  margin-right: 2%;
}
#recipientMessageHead{
  width: 100%;
  height: 50px;
  background-color: #efefef;
}
#recipientHeadTitle{
  font-size: 17px;
  font-weight: bold;
  margin-left: 2%;
  line-height: 300%;
}
#recipientMessage{
  width: 80%;
  margin-top: 1%;
  margin-left: 5%;
  margin-bottom: 2%;
}
#submitBox{
  margin-right: 3%;
float: right;
}
</style>
