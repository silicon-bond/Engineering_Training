<template>
  <div>
    <div id="personalBox">
      <h2 id="personalTitle">个人信息</h2>
      <el-form ref="form" :model="form" label-width="80px" style="float: left;width: 40%">
        <el-form-item label="昵称">
          <el-input v-model="form.name" placeholder="请输入昵称"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="form.gender" placeholder="请选择性别">
            <el-option label="男" value="male"></el-option>
            <el-option label="女" value="female"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="form.telephone" placeholder="请输入联系电话"></el-input>
        </el-form-item>
        <el-form-item label="地址信息">
          <el-input v-model="form.address" placeholder="请输入地址"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确认修改</el-button>
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
        gender:'',
        telephone:'',
        address:'',
      }
    }
  },
  methods: {
    onSubmit() {

    }
  }
}
</script>

<style scoped>
#personalBox{
  margin-left: 2%;
}
</style>
