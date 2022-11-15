<template>
  <div>
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
            <el-button size="mini" type="text" @click="lookClick(scope.$index,scope.row)" class="button">查看</el-button>
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
  </div>
</template>

<script>
export default {
  name: "AdviceList",
  data() {
    return {

      dialogFormVisible: false,
      dialogDetail: false,


      tableCol: [
        //{prop: "id", label: "id"},
        {prop: "nickname", label: "昵称"},
        {prop: "character", label: "角色"},
        {prop: "email", label: "邮箱"},
        {prop: "address", label: "地址信息"},

      ],

      tableData: [
        {
          nickname:'aa',
          character:'用户',
          email:'11111111@qq.com',
          address:'福州大学'
        },
        {
          nickname:'bb',
          character:'快递员',
          email:'11111111@qq.com',
          address:'福州大学'
        },
        {
          nickname:'cc',
          character:'快递员',
          email:'11111111@qq.com',
          address:'福州大学'
        },
        {
          nickname:'dd',
          character:'网点管理员',
          email:'11111111@qq.com',
          address:'福州大学'
        }
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


    headeRowClass({row, column, rowIndex, columnIndex}){
      //表头的背景颜色
      if(rowIndex === 0){
        return 'background:#95b695; color: black';
      }
    },
    lookClick(){

    },
    deleteClick(){

    }
  }
}
</script>

<style scoped>

</style>
