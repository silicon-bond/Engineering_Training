<template>
  <div>
    <el-button type="primary" plain size="large" id="deliverbtn" @click="deliver">我有房源</el-button>
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
  name: "HouseMessage",
  data(){
    return{

      tableCol: [
        //{prop: "id", label: "id"},
        {prop: "subject", label: "标题"},
        {prop: "size", label: "户型"},
        {prop: "area", label: "面积(㎡)"},
        {prop: "price", label: "租金(元/月)"},
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
    }
  },
  methods:{
    handleCurrentChange: function(val) {
      this.currentPage = val;
      this.querySearch(this.currentPage);
    },

    lookClick(index,row) {

      localStorage.setItem('r-title',row.subject)
      localStorage.setItem('r-size',row.size)
      localStorage.setItem('r-area',row.area)
      localStorage.setItem('r-price',row.price)
      localStorage.setItem('r-releasedAt',row.releasedAt)
      localStorage.setItem('r-address',row.address)
      localStorage.setItem('r-city',row.city)
      localStorage.setItem('r-type',row.type)
      localStorage.setItem('r-decoration',row.decoration)
      localStorage.setItem('r-equipment',row.equipment)
      localStorage.setItem('r-telephone',row.telephone)
      localStorage.setItem('r-owner',row.owner)
      this.$router.push({
        path:'/user/rentdetail'
      })

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
        url: 'http://localhost:8081/javaee1_war_exploded/rent/all?pn='+pageNum,
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data.data.list)
        if (response.data.code==='200') {
          this.tableData = response.data.data.list
          this.totalCount = response.data.data.total
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },

    deliver(){
      this.$router.push('/user/rentadd')
    }


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
</style>
