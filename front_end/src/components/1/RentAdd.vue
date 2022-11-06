<template>
  <div>
    <div id="messageBox">
      <div id="mesghead">
        <span id="headtitle">房源信息</span>
      </div>
      <div id="message">
        <el-form :model="ruleForm" :inline="true" :label-position="labelPosition" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item style="width: 40%" label="标题" prop="subject">
            <el-input v-model="ruleForm.subject"></el-input>
          </el-form-item>
          <el-form-item style="width: 40%" label="地址" prop="address">
            <el-input v-model="ruleForm.address"></el-input>
          </el-form-item>
          <el-form-item style="width: 40%" label="城市" prop="city">
            <el-input v-model="ruleForm.city"></el-input>
          </el-form-item>
          <el-form-item style="width: 40%" label="户型" prop="size">
            <el-input v-model="ruleForm.size"></el-input>
          </el-form-item>
          <el-form-item style="width: 40%" label="租金(元/月)" prop="price">
            <el-input v-model="ruleForm.price"></el-input>
          </el-form-item>
          <el-form-item style="width: 40%" label="面积(㎡)" prop="area">
            <el-input v-model="ruleForm.area"></el-input>
          </el-form-item>
          <el-form-item style="width: 40%" label="装修情况" prop="decoration">
            <el-input v-model="ruleForm.decoration"></el-input>
          </el-form-item>
          <el-form-item style="width: 40%" label="配套设施" prop="equipment">
            <el-input v-model="ruleForm.equipment"></el-input>
          </el-form-item>
          <el-form-item style="width: 40%" label="联系电话" prop="telephone">
            <el-input v-model.number="ruleForm.telephone"></el-input>
          </el-form-item>
          <el-form-item style="width: 40%" label="联系人" prop="owner">
            <el-input v-model="ruleForm.owner"></el-input>
          </el-form-item>

          <el-divider></el-divider>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
            <el-button @click="goback">取消</el-button>
          </el-form-item>
        </el-form>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RentAdd",
  data(){
    return{
      labelPosition:'left',
      ruleForm: {
        subject:'',
        address:'',
        city:'',
        size:'',
        price:'',
        decoration:'',
        equipment:'',
        telephone:'',
        owner:'',
        area:''
      },
      rules: {
        subject: [
          { required: true, message: '请输入标题', trigger: 'blur' },
        ],
        address: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
        city: [
          { required: true, message: '请输入城市', trigger: 'blur' }
        ],
        size: [
          { required: true, message: '请输入户型', trigger: 'blur' }
        ],
        price: [
          {required: true, message: '请输入租金', trigger: 'blur'},
        ],
        decoration: [
          { required: true, message: '请输入装修情况', trigger: 'blur' }
        ],
        equipment: [
          { required: true, message: '请输入配套设施情况', trigger: 'blur'}
        ],
        telephone: [
          { required: true, message: '请输入联系电话', trigger: 'blur' },
          { type: 'number', message: '联系电话必须为数字值'}
        ],
        owner: [
          { required: true, message: '请输入联系人', trigger: 'blur' }
        ],
        area: [
          { required: true, message: '请输入面积', trigger: 'blur' },
        ],
      }
    }
  },
  methods:{
    submitForm(message){
      this.$refs[message].validate((valid) => {
        if (valid) {
          this.$axios({
            method: 'post',
            headers: {
              'Content-type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(this.ruleForm),
            url: 'http://localhost:8081/javaee1_war_exploded/rent/insert',
          }).then((response) => {          //这里使用了ES6的语法
            if (response.data.code === '200'){
              alert('添加成功');
              this.$router.push('/user/houserent')
            } else{
              alert('添加失败');
            }
          }).catch((error) => {
            console.log(error)       //请求失败返回的数据
          })
        } else {
          return false;
        }
      });
    },
    goback(){
      this.$router.push('/user/houserent')
    }
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
  width: 90%;
  margin-top: 3%;
  margin-left: 5%;
}
</style>
