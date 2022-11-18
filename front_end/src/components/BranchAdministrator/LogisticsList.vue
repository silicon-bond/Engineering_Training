<template>
  <div>
    <div>
      <h3 id="searchTitle">物流查询</h3>
      <div id="search">
        <span id="logisticsState">物流状态</span>
        <el-select v-model="value" placeholder="请选择物流状态">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <div id="searchContent">
          <el-input  v-model="searchContent" placeholder="请输入快递单号"></el-input>
        </div>
        <el-button type="primary">搜索</el-button>
      </div>
    </div>
    <el-divider></el-divider>
    <h3 id="tableTitle">物流信息列表</h3>
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

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <!--            <el-button size="mini" type="text" @click="lookClick(scope.$index,scope.row)" class="button" icon="el-icon-view">查看</el-button>-->
            <!--            <el-button size="mini" type="text" @click="selectClick(scope.$index,scope.row)" class="button" icon="el-icon-select">接单</el-button>-->
            <el-button size="mini" type="text" @click="transfer()" class="button" icon="el-icon-select">转送</el-button>

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
  name: "LogisticsList",
  data() {
    return {
      options: [{
        value: '选项1',
        label: '未揽件'
      },  {
        value: '选项2',
        label: '未派送'
      }],
      value: '',
      searchContent:'',
      tableCol: [
        //{prop: "id", label: "id"},
        {prop: "expressId", label: "订单编号"},
        {prop: "sender", label: "寄件人"},
        {prop: "recipient", label: "收件人"},
        {prop: "weight", label: "重量/kg"},
        {prop: "state", label: "物流状态"},
        {prop: "type", label: "物品类型"},
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
    },

    transfer() {

    },
    selectClick(){

      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        // data: JSON.stringify(info),
        url: 'http://localhost:8080/express/api/network-administrator/getExpress?page=1&pageSize=2&networkId=1&dateStart=2022-10-21',
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data.message)
        if (response.data.code==='1') {
          // this.tableData = response.data.data.records
          // this.totalCount = response.data.data.total
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })

    },
    querySearch() {
      console.log('querySearch123');
      // let info = {
      //   pn:pageNum
      // }
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        // data: JSON.stringify(info),
        url: 'http://8.130.39.140:8081/express/api/deliveryman/expressList/?page=1&pageSize=2&networkId=1',
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data.data)
        if (response.data.code==='1') {
          this.tableData = response.data.data.records
          // this.totalCount = response.data.data.total
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },

  },

  created () {
    this.querySearch()
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
