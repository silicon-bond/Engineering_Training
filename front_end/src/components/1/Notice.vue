<template>
  <div>

    <el-dialog title="通知详情" :visible.sync="dialogDetail">

      <div>
        <p id="detailtitle">{{ detail.title }}</p>
        <p id="detailcontent">{{ detail.content }}</p>
        <p id="detailissuer">{{ detail.publisher}}</p>
        <p id="detailreleasedAt">{{ detail.releasedAt }}</p>
      </div>

    </el-dialog>

    <div id="table">
      <el-table :data="tableData"
                stripe
                style="width: 100%"
                :header-cell-style="headeRowClass">
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
            <el-button size="mini" type="text" @click="lookClick(scope.$index,scope.row)" class="button" icon="el-icon-view">查看</el-button>
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

  </div>
</template>

<script>
export default {
  name: "Notice",
  data() {
    return {
      detail: {
        notificationName: '',
        content: '',
        publisher:'',
        releasedAt:''
      },
      dialogFormVisible: false,
      dialogDetail: false,

      form: {
        title:'',
        content:'',
      },
      formInline: {
        user: '',
        region: ''
      },
      tableCol: [
        //{prop: "id", label: "id"},
        {prop: "notificationName", label: "通知名称"},
        {prop: "content", label: "通知内容"},
        {prop: "publisher", label: "发布人"},
        {prop: "releasedAt", label: "发布时间"},

      ],

      tableData: [],

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
      this.detail.title = row.notificationName
      this.detail.content = row.content
      this.detail.publisher = row.publisher
      this.detail.releasedAt = row.releasedAt
      this.dialogDetail = true

    },

    headeRowClass({row, column, rowIndex, columnIndex}){
      //表头的背景颜色
      if(rowIndex === 0){
        return 'background:#e8e8e8; color: black';
      }
    },

    querySearch(pageNum) {
      let info = {
        pn:pageNum
      }
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(info),
        url: 'http://localhost:8081/javaee1_war_exploded/notice/all?pn='+pageNum,
      }).then((response) => {          //这里使用了ES6的语法
        if (response.data.code==='200') {
          this.tableData = response.data.data.list
          this.totalCount = response.data.data.total
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },
  },

  created () {
    this.querySearch(this.currentPage);
  }
}
</script>

<style scoped>
#deliverbtn{
  margin-top: 20px;
  margin-bottom: 20px;
  margin-left: 20px;
}
#detailtitle{
  text-align: center;
  font-size: 22px;
  margin-top: 0;
  font-weight: bold;
}
#detailcontent{
  font-size: 15px;
  text-indent: 2em;
}
#detailissuer{
  text-align: right;
  margin-right: 50px;
  font-size: 16px;
}
#detailreleasedAt{
  font-size: 16px;
  margin-right: 50px;
  text-align: right;
}
</style>
