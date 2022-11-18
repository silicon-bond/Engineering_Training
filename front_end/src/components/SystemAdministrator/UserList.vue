<template>
  <div>
    <div>
      <h2 id="searchTitle">用户查询</h2>
      <div id="search">
        <div id="searchContent">
          <el-input  v-model="searchContent" placeholder="请输入手机号"></el-input>
        </div>
        <el-button type="primary">搜索</el-button>
        <el-button id="addUserbtn" type="primary">添加用户</el-button>
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
            <el-button size="mini" type="text" @click="editClick(scope.$index,scope.row)" class="button">编辑</el-button>
            <el-button size="mini" type="text" @click="deleteClick(scope.$index,scope.row)" class="button">删除</el-button>
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
    <el-dialog title="编辑用户信息" :visible.sync="editDetail">

      <div id="editBox">
        <el-form ref="detail" :model="detail" label-width="80px">
          <el-form-item label="用户编号">
            <el-input v-model="detail.id" readonly></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="detail.sender"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="detail.recipient"></el-input>
          </el-form-item>
          <el-form-item label="联系电话">
            <el-input v-model="detail.state"></el-input>
          </el-form-item>
          <el-form-item label="注册时间">
            <el-input v-model="detail.deliveryTime"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="detail.arrivalTime"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="editSubmit">确认</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "UserList",
  data() {
    return {
      detail: {
        id:'',
        sender:'',
        recipient:'',
        deliveryTime:'',
        state:'',
        arrivalTime:''
      },
      dialogFormVisible: false,
      editDetail: false,

      searchContent:'',
      tableCol: [
        //{prop: "id", label: "id"},
        {prop: "userId", label: "用户编号"},
        {prop: "email", label: "邮箱"},
        {prop: "detailAddress", label: "地址"},
        {prop: "phoneNumber", label: "联系电话"},
        {prop: "registerDate", label: "注册时间"},
      ],

      tableData: [
      ],

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


    lookClick(index,row) {
      this.detail.id = row.id
      this.detail.sender = row.sender
      this.detail.recipient = row.recipient
      this.detail.arrivalTime = row.arrivalTime
      this.detail.deliveryTime = row.deliveryTime
      this.detail.state = row.state
      this.lookDetail = true

    },
    editClick(index,row){
      this.detail.id = row.id
      this.detail.sender = row.sender
      this.detail.recipient = row.recipient
      this.detail.arrivalTime = row.arrivalTime
      this.detail.deliveryTime = row.deliveryTime
      this.detail.state = row.state
      this.editDetail = true
    },
    deleteClick(){

    },
    editSubmit(){

    },
    querySearch(pageNum) {
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        url: 'http://8.130.39.140:8081/express/api/system-administrator/person-management/getUserByPage?page='+pageNum+'&pageSize='+this.pagesize,
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data.data)
        this.tableData = response.data.data.records
        this.totalCount = response.data.data.total
        // if (response.data.code==='200') {
        //   this.result = response.data.data.list
        //   this.totalCount = response.data.data.total
        // }
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
</style>
