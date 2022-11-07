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
          <el-form-item label="详细地址" prop="senderAddress">
            <el-input v-model="Form.senderAddress" placeholder="请输入寄件人地址"></el-input>
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
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('Rules')" id="submibtn">提交</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Mail",
  data(){
    return{
      labelPosition:'left',
      options: [{
        value: '选项1',
        label: '福州网点'
      }, {
        value: '选项2',
        label: '厦门网点'
      }, {
        value: '选项3',
        label: '莆田网点'
      }, {
        value: '选项4',
        label: '泉州网点'
      }, {
        value: '选项5',
        label: '漳州网点'
      }],
      Form: {
        senderName:'',
        senderTelephone:'',
        senderAddress:'',
        recipientName:'',
        recipientTelephone:'',
        recipientAddress:'',
        value:'',
      },

      Rules: {
        senderName: [
          { required: true, message: '请输入寄件人姓名', trigger: 'blur' },
        ],
        senderTelephone: [
          { required: true, message: '请输入寄件人联系电话', trigger: 'blur' }
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
          { required: true, message: '请输入收件人地址', trigger: 'blur' }
        ]
      },
    }
  },
  methods:{
    submitForm(message){
      this.$refs['Rules2'].validate((valid) => {
        if (valid) {
          alert('aaaa')
        } else {
          return false;
        }
      });
    }
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
