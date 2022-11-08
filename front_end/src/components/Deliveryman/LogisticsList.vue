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
            <el-button size="mini" type="text" @click="selectClick(scope.$index,scope.row)" class="button" icon="el-icon-select">接单</el-button>
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
        {prop: "id", label: "订单编号"},
        {prop: "sender", label: "寄件人"},
        {prop: "recipient", label: "收件人"},
        {prop: "weight", label: "重量/kg"},
        {prop: "state", label: "物流状态"},
        {prop: "type", label: "物品类型"},
      ],
      tableData: [
        {
          id:1,
          sender:'小松',
          recipient:'小明',
          weight:'001',
          state:'运输中',
          type:'日用品'
        },
        {
          id:2,
          sender:'小松',
          recipient:'小明',
          weight:'001',
          state:'运输中',
          type:'日用品'
        },
        {
          id:3,
          sender:'小松',
          recipient:'小明',
          weight:'001',
          state:'运输中',
          type:'日用品'
        },
        {
          id:4,
          sender:'小松',
          recipient:'小明',
          weight:'002',
          state:'运输中',
          type:'日用品'
        },
        {
          id:5,
          sender:'小松',
          recipient:'小明',
          weight:'001',
          state:'运输中',
          type:'日用品'
        },
        {
          id:6,
          sender:'小松',
          recipient:'小明',
          weight:'003',
          state:'运输中',
          type:'日用品'
        },

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

    lookClick(index,row) {

    },
    selectClick(index,tow){

    },
  },

  created () {

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
