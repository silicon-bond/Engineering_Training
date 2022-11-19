<template>
  <div>
    <div>
      <h2 id="searchTitle">意见查询</h2>
      <div id="search">
        <span id="adviceState">意见状态</span>
        <el-select v-model="value" placeholder="请选择意见状态">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <div id="searchContent">
          <el-input  v-model="searchContent" placeholder="请输入意见标题"></el-input>
        </div>
        <el-button type="primary">搜索</el-button>
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
            <el-button size="mini" type="text" @click="lookClick(scope.row)" class="button">查看</el-button>
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
    <el-dialog title="意见详情" :visible.sync="lookDetail">

      <div id="detailBox">
        <el-form ref="detail" :model="detail" label-width="125px">
          <el-form-item label="意见编号">
            <el-input v-model="detail.abnormalFeedbackId" readonly></el-input>
          </el-form-item>
          <el-form-item label="意见标题">
            <el-input v-model="detail.title" readonly></el-input>
          </el-form-item>
          <el-form-item label="意见内容">
            <el-input v-model="detail.content" readonly></el-input>
          </el-form-item>
          <el-form-item label="所属网点">
            <el-input v-model="detail.branch" readonly></el-input>
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
      options: [{
        value: '1',
        label: '未处理'
      }, {
        value: '2',
        label: '已处理'
      }],
      value: '',
      detail: {
        abnormalFeedbackId:'',
        title:'',
        description:'',
        feedbackDate:'',
        providerPhoneNumber:'',
        branch:'',
        state:'',
      },
      dialogFormVisible: false,
      lookDetail: false,
      editDetail: false,

      searchContent:'',
      tableCol: [
        //{prop: "id", label: "id"},
        {prop: "abnormalFeedbackId", label: "意见编号"},
        {prop: "title", label: "意见标题"},
        {prop: "providerPhoneNumber", label: "反馈人联系电话"},
        {prop: "deliveryTime", label: "发货时间"},
        {prop: "state", label: "意见状态"},
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
        url: 'http://8.130.39.140:8081/express/api/abnormal-feedback?page='+pageNum+'&pageSize='+this.pagesize+'&factor=',
      }).then((response) => {          //这里使用了ES6的语法
        this.tableData = response.data.data.records
        this.totalCount = response.data.data.total
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
#adviceState{
  margin-right: 1%;
  font-weight: bold;
}
#detailBox{
  margin-left: 6%;
  margin-right: 9%;
}
</style>
