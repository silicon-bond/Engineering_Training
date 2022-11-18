<template>
  <div>
    <div>
      <h2 id="searchTitle">网点管理员查询</h2>
      <div id="search">
        <div id="searchContent">
          <el-input  v-model="searchContent" placeholder="请输入网点管理员编号"></el-input>
        </div>
        <el-button type="primary">搜索</el-button>
        <el-button id="addUserbtn" type="primary" @click="add">添加网点管理员</el-button>
      </div>
    </div>
    <el-divider></el-divider>
    <div id="table">
      <el-table :data="tableData"
                stripe
                style="width: 100%">
        <el-table-column
          v-for="(item,i) in tableCol"
          :key="i"
          :prop="item.prop"
          :label="item.label"
          :width="item.width"
          align="center"
          show-overflow-tooltip>
        </el-table-column>

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="edit(scope.row)" class="button">编辑</el-button>
            <el-button size="mini" type="text" @click="deleteClick(scope.row)" class="button">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-divider></el-divider>

    <el-pagination
      id="pagination"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="pagesize"
      layout="prev, pager, next"
      :total="totalCount">
    </el-pagination>

    <el-dialog title="编辑网点管理员信息" :visible.sync="editDetail">

      <div id="editBox">
        <el-form ref="detail" :model="detail" label-width="125px" :rules="editRules">
          <el-form-item label="网点管理员编号">
            <el-input v-model="detail.id" readonly></el-input>
          </el-form-item>
          <el-form-item label="账号" prop="account">
            <el-input v-model="detail.account"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="phone">
            <el-input v-model="detail.phone"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="detail.password"></el-input>
          </el-form-item>
          <el-form-item label="所属网点" prop="branch">
            <el-input v-model="detail.branch"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="editClick">确认</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>

    <el-dialog title="新增网点管理员信息" :visible.sync="addDetail">

      <div id="addBox">
        <el-form ref="addMessage" :model="addMessage" label-width="125px" :rules="addRules">
          <el-form-item label="账号" prop="account">
            <el-input v-model="addMessage.account"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="phone">
            <el-input v-model="addMessage.phone"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="addMessage.password"></el-input>
          </el-form-item>
          <el-form-item label="所属网点" prop="branch">
            <el-select v-model="addMessage.branch" style="width: 100%">
              <el-option
                v-for="item in options"
                :key="item.networkId"
                :label="item.networkName"
                :value="item.networkId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="addClick('addMessage')">确认</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "branchAdminList",
  data() {
    return {
      detail: {
        id:'',
        account:'',
        phone:'',
        password:'',
        branch:''
      },
      addMessage:{
        account:'',
        phone:'',
        password:'',
        branch:''
      },
      dialogFormVisible: false,
      editDetail: false,
      addDetail:false,
      searchContent:'',
      tableCol: [
        //{prop: "id", label: "id"},
        {prop: "networkAdministratorId", label: "系统管理员编号"},
        {prop: "account", label: "账号"},
        {prop: "password", label: "密码"},
        {prop: "phoneNumber", label: "联系电话"},
        {prop: "branch", label: "所属网点"},
      ],
      options: [],
      value: '',
      tableData: [
      ],
      addRules:{
        account:[
          { required: true, message: '账号不能为空', trigger: 'change' },
        ],
        phone:[
          { required: true, message: '联系电话不能为空', trigger: 'change' },
        ],
        password:[
          { required: true, message: '密码不能为空', trigger: 'change' },
        ],
        branch:[
          { required: true, message: '所属网点不能为空', trigger: 'change' },
        ]
      },
      editRules:{
        account:[
          { required: true, message: '账号不能为空', trigger: 'change' },
        ],
        phone:[
          { required: true, message: '联系电话不能为空', trigger: 'change' },
        ],
        password:[
          { required: true, message: '密码不能为空', trigger: 'change' },
        ],
        // branch:[
        //   { required: true, message: '所属网点不能为空', trigger: 'change' },
        // ]
      },
      nId: '1',
      nname: '',
      content: '',
      pagesize: 5,
      //当前页码
      currentPage: 1,
      //查询的页码
      start: 1,
      //默认数据总数
      totalCount: 1,
      //搜索条件
      criteria: '',
    };
  },
  methods: {
    handleCurrentChange: function(val) {
      this.currentPage = val;
      this.querySearch(this.currentPage);
    },
    add(){
      this.getAllBranch()
      this.addDetail = true
    },
    addClick(message){
      this.$refs[message].validate((valid) => {
        if (valid) {
          this.$confirm('确定提交此新增?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.addConfirm()
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
    addConfirm(){//未实现

    },
    edit(row){
      this.detail.id = row.networkAdministratorId
      this.detail.account = row.account
      this.detail.password = row.password
      this.detail.phone = row.phoneNumber
      this.detail.branch = row.branch
      this.editDetail = true
    },
    editClick(message){
      this.$refs[message].validate((valid) => {
        if (valid) {
          this.$confirm('确定提交此修改?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.editConfirm()
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
    editConfirm(){//未实现
      let userMessage = {
        userId:this.detail.id,
        email:this.detail.email,
        detailAddress:this.detail.address,
        phoneNumber:this.detail.phone,
        password:this.detail.password
      }
      this.$axios({
        method: 'put',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(userMessage),
        url: 'http://8.130.39.140:8081/express/api/system-administrator/person-management/update/1',
      }).then((response) => {          //这里使用了ES6的语法
        if (response.data.message==="用户修改成功"){
          this.$message({
            message: '修改用户成功',
            type: 'success'
          });
          this.$router.go(0)
        }else {
          this.$message.error('修改用户失败');
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },
    deleteClick(){

    },

    querySearch(pageNum) {
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        url: 'http://8.130.39.140:8081/express/api/system-administrator/person-management/getNetworkAdministratorByPage?page='+pageNum+'&pageSize='+this.pagesize,
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data)
        this.tableData = response.data.data.records
        this.totalCount = response.data.data.total
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
    this.querySearch(this.currentPage)
  }
}
</script>

<style scoped>
#searchTitle{
  margin-left: 2%;
}
#search{
  margin-left: 3%;
}
#tableTitle{
  margin-left: 3%;
}
#searchContent{
  display:inline-block;
  width: 25%;
  margin-left: 2%;
  margin-right: 1%;
}
#addUserbtn{
  float: right;
  margin-right: 3%;
}
#detailBox{
  margin-left: 6%;
  margin-right: 9%;
}
#editBox{
  margin-left: 6%;
  margin-right: 9%;
}
#addBox{
  margin-left: 6%;
  margin-right: 9%;
}
</style>
