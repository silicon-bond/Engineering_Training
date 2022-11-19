<template>
  <div>
    <div>
      <h3 id="searchTitle">意见查询</h3>
      <div id="search">
        <el-select v-model="value" placeholder="请选择意见状态">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <div id="searchContent">
          <el-button type="primary">搜索</el-button>
        </div>
      </div>
    </div>
    <el-divider></el-divider>
    <div id="table">
      <el-table :data="tableData"
                stripe
                style="width: 100%"
      >
        <el-table-column
          v-for="(item,i) in tableCol"
          :key="i"
          :prop="item.prop"
          :label="item.label"
          :width="item.width"
          align="center"
          show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="意见状态" align="center">
          <template slot-scope="scope">
            <el-tag type="danger" v-if="scope.row.state===0">未处理</el-tag>
            <el-tag type="success" v-if="scope.row.state===1">已处理</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="lookClick(scope.row)" class="button">查看</el-button>
            <el-button size="mini" type="text" @click="handleClick(scope.row)" v-if="scope.row.state===0" class="button">处理</el-button>
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
    <el-dialog title="意见详情" :visible.sync="dialogDetail">

      <div id="detailBox">
        <el-form ref="detail" :model="detail" label-width="115px">
          <el-form-item label="意见编号">
            <el-input v-model="detail.abnormalFeedbackId" readonly></el-input>
          </el-form-item>
          <el-form-item label="意见标题">
            <el-input v-model="detail.title" readonly></el-input>
          </el-form-item>
          <el-form-item label="意见内容">
            <el-input v-model="detail.description" readonly></el-input>
          </el-form-item>
          <el-form-item label="反馈者联系电话">
            <el-input v-model="detail.providerPhoneNumber" readonly></el-input>
          </el-form-item>
          <el-form-item label="意见状态">
            <el-select v-model="detail.state" style="width: 100%" disabled>
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="反馈时间">
            <el-input v-model="detail.feedbackDate" readonly></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "AdviceList",
  data() {
    return {
      dialogDetail:false,
      detail: {
        abnormalFeedbackId:'',
        title:'',
        description:'',
        state:'',
        providerPhoneNumber:'',
        feedbackDate:''
      },
      options: [{
        value: 0,
        label: '未处理'
      },  {
        value: 1,
        label: '已处理'
      }],
      value: '',
      searchContent:'',
      tableCol: [
        //{prop: "id", label: "id"},
        {prop: "abnormalFeedbackId", label: "意见编号"},
        {prop: "title", label: "意见标题"},
        {prop: "description", label: "意见内容"},
      ],
      tableData: [
      ],

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
    lookClick(row){
      this.detail.abnormalFeedbackId = row.abnormalFeedbackId
      this.detail.title = row.title
      this.detail.description = row.description
      this.detail.state = row.state
      this.detail.providerPhoneNumber = row.providerPhoneNumber
      this.detail.feedbackDate = row.feedbackDate
      this.dialogDetail = true
    },
    handleClick(row){
      this.$confirm('确定已处理此意见?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleConfirm(row)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    handleConfirm(row){
      let branchAdmin = JSON.parse(localStorage.getItem("userinfo_kuaidi"))
      let adviceMessage = {
        abnormalFeedbackId:row.abnormalFeedbackId,
        description:row.description,
        feedbackDate:row.feedbackDate,
        networkId:row.networkId,
        principalId:branchAdmin.networkAdministratorId,
        providerPhoneNumber:row.providerPhoneNumber,
        state:1,
        title:row.title
      }
      this.$axios({
        method: 'post',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(adviceMessage),
        url: 'http://8.130.39.140:8081/express/api/abnormal-feedback/updateById',
      }).then((response) => {          //这里使用了ES6的语法
        if (response.data.message==="success"){
          this.$message({
            message: '处理意见成功',
            type: 'success'
          });
          setTimeout(()=> {
            this.$router.go(0)
          }, 1000)

        }else {
          this.$message.error('处理意见失败');
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },
    deleteClick(row){
      this.$confirm('确定删除此意见?', '提示', {
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
        url: 'http://8.130.39.140:8081/express/api/abnormal-feedback/'+row.abnormalFeedbackId,
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data)
        if (response.data.message==="success"){
          this.$message({
            message: '删除此意见成功',
            type: 'success'
          });
          setTimeout(()=> {
            this.$router.go(0)
          }, 1000)

        }else {
          this.$message.error('删除此意见失败');
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },
    querySearch(pageNum) {
      let branchAdmin = JSON.parse(localStorage.getItem("userinfo_kuaidi"))
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        // data: JSON.stringify(info),
        url: 'http://8.130.39.140:8081/express/api/network-administrator/network_id?page='+pageNum+'&pageSize='+this.pagesize+'&network_id='+branchAdmin.networkId,
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data)
        this.tableData = response.data.data.records
        this.totalCount = response.data.data.total
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },

  },
  created () {
    this.querySearch(this.currentPage)
  }
}
</script>

<style scoped>
#searchTitle{
  margin-left: 1%;
  margin-right: 1%;
}
#logisticsState{
  font-weight: 600;
  margin-right: 1%;
}
#search{
  margin-left: 1%;
}
#tableTitle{
  margin-left: 1%;
}
#searchContent{
  display:inline-block;
  width: 25%;
  margin-left: 4%;
  margin-right: 1%;
}
</style>
