<template>
  <div>
    <div>
      <h2 id="searchTitle">快递员查询</h2>
      <div id="search">
        <div id="searchContent">
          <el-input  v-model="searchContent" placeholder="请输入快递员编号"></el-input>
        </div>
        <el-button type="primary" @click="searchClick">搜索</el-button>
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

    <el-dialog title="编辑快递员信息" :visible.sync="editDetail">

      <div id="editBox">
        <el-form ref="detail" :model="detail" label-width="125px"  :rules="editRules">
          <el-form-item label="快递员编号">
            <el-input v-model="detail.id" readonly></el-input>
          </el-form-item>
          <el-form-item label="快递员姓名" prop="username">
            <el-input v-model="detail.username"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="detail.email" readonly></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="phone">
            <el-input v-model="detail.phone"></el-input>
          </el-form-item>
          <el-form-item label="注册时间">
            <el-input v-model="detail.registerTime" readonly></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="detail.password"></el-input>
          </el-form-item>
          <el-form-item label="所属网点" prop="branch">
            <el-select filterable v-model="detail.branch" placeholder="请选择网点" style="width: 100%">
              <el-option
                v-for="item in options"
                :key="item.networkId"
                :label="item.country+item.networkName"
                :value="item.networkId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="editClick('detail')">确认</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "DeliverymanList",
  data() {
    return {
      detail: {
        id:'',
        username:'',
        email:'',
        phone:'',
        registerTime:'',
        password:'',
        branch:''
      },
      options: [],
      dialogFormVisible: false,
      editDetail: false,

      searchContent:'',
      tableCol: [
        //{prop: "id", label: "id"},
        {prop: "deliverymanId", label: "快递员编号"},
        {prop: "username", label: "快递员姓名"},
        {prop: "email", label: "邮箱"},
        {prop: "phoneNumber", label: "联系电话"},
        {prop: "registerDate", label: "注册时间"},
      ],

      tableData: [
      ],
      editRules:{
        username:[
          { required: true, message: '姓名不能为空', trigger: 'change' },
        ],
        phone:[
          { pattern:/^1[3|4|5|7|8][0-9]{9}$/,message: '请输入正确的手机号码',trigger: 'change' },
          { required: true, message: '联系电话不能为空', trigger: 'change' },
        ],
        password:[
          { required: true, message: '密码不能为空', trigger: 'change' },
        ],
        branch:[
          { required: true, message: '所属网点不能为空', trigger: 'change' },
        ]
      },
      resultStatus:'1',

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

    edit(row){
      this.detail.id = row.deliverymanId
      this.detail.email = row.email
      this.detail.username = row.username
      this.detail.phone = row.phoneNumber
      this.detail.registerTime = row.registerDate
      this.detail.password = row.password
      this.detail.branch = row.networkId
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
    editConfirm(){
      let deliverymanMessage = {
        deliverymanId:this.detail.id,
        email:this.detail.email,
        phoneNumber:this.detail.phone,
        password:this.detail.password,
        username:this.detail.username,
        networkId:this.detail.branch
      }
      this.$axios({
        method: 'put',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(deliverymanMessage),
        url: 'http://8.130.39.140:8081/express/api/system-administrator/person-management/update/2',
      }).then((response) => {          //这里使用了ES6的语法
        if (response.data.message==="快递员修改成功"){
          this.$message({
            message: '修改快递员信息成功',
            type: 'success'
          });
          setTimeout(()=> {
            this.$router.go(0)
          }, 1000)

        }else {
          this.$message.error('修改快递员信息失败');
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },
    deleteClick(row){
      this.$confirm('确定删除此快递员?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteConfirm(row)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    deleteConfirm(row){
      this.$axios({
        method: 'delete',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        url: 'http://8.130.39.140:8081/express/api/system-administrator/deleteByIdAndRole?id='+row.deliverymanId+'&role=2',
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data)
        if (response.data.message==="success"){
          this.$message({
            message: '删除快递员成功',
            type: 'success'
          });
          setTimeout(()=> {
            this.$router.go(0)
          }, 1000)

        }else {
          this.$message.error('删除快递员失败');
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },
    searchClick(){
      this.resultStatus='2'
      this.currentPage=1
      this.searchById(this.currentPage)
    },
    searchById(pageNum){
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        url: 'http://8.130.39.140:8081/express/api/system-administrator/getPeopleByIdAndRole?id='+this.searchContent+'&role=2',
      }).then((response) => {          //这里使用了ES6的语法
        this.tableData = response.data.data.records
        this.totalCount = response.data.data.total
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },
    querySearch(pageNum) {
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        url: 'http://8.130.39.140:8081/express/api/system-administrator/person-management/getDeliverymanByPage?page='+pageNum+'&pageSize='+this.pagesize,
      }).then((response) => {          //这里使用了ES6的语法
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
        console.log(this.options)
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },
  },
  created() {
    this.querySearch(this.currentPage)
    this.getAllBranch()
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
</style>
