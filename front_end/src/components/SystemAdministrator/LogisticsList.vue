<template>
  <div>
    <div>
      <h2 id="searchTitle">物流查询</h2>
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
    <el-dialog title="物流详情" :visible.sync="lookDetail">

      <div id="detailBox">
        <el-form ref="detail" :model="detail" label-width="80px">
          <el-form-item label="订单编号">
            <el-input v-model="detail.id" readonly></el-input>
          </el-form-item>
          <el-form-item label="寄件人">
            <el-input v-model="detail.sender" readonly></el-input>
          </el-form-item>
          <el-form-item label="收件人">
            <el-input v-model="detail.recipient" readonly></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-input v-model="detail.state" readonly></el-input>
          </el-form-item>
          <el-form-item label="发货时间">
            <el-input v-model="detail.deliveryTime" readonly></el-input>
          </el-form-item>
          <el-form-item label="送达时间">
            <el-input v-model="detail.arrivalTime" readonly></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>

    <el-dialog title="编辑物流信息" :visible.sync="editDetail">

      <div id="editBox">
        <el-form ref="detail" :model="detail" label-width="80px">
          <el-form-item label="订单编号">
            <el-input v-model="detail.id" readonly></el-input>
          </el-form-item>
          <el-form-item label="寄件人">
            <el-input v-model="detail.sender"></el-input>
          </el-form-item>
          <el-form-item label="收件人">
            <el-input v-model="detail.recipient"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-input v-model="detail.state"></el-input>
          </el-form-item>
          <el-form-item label="发货时间">
            <el-input v-model="detail.deliveryTime"></el-input>
          </el-form-item>
          <el-form-item label="送达时间">
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
  name: "LogisticsList",
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
      lookDetail: false,
      editDetail: false,
      options: [{
        value: '选项1',
        label: '未揽件'
      }, {
        value: '选项2',
        label: '已揽件'
      }, {
        value: '选项3',
        label: '运输中'
      }, {
        value: '选项4',
        label: '未派送'
      }, {
        value: '选项5',
        label: '已送达'
      }],
      value: '',
      searchContent:'',
      tableCol: [
        //{prop: "id", label: "id"},
        {prop: "id", label: "订单编号"},
        {prop: "sender", label: "寄件人"},
        {prop: "recipient", label: "收件人"},
        {prop: "deliveryTime", label: "发货时间"},
        {prop: "state", label: "物流状态"},
        {prop: "arrivalTime", label: "预计到达时间"},
      ],

      tableData: [
        {
          id:1,
          sender:'小松',
          recipient:'小明',
          deliveryTime:'2022.11.06 16:21',
          state:'运输中',
          arrivalTime:'2022.11.09 16:21'
        },
        {
          id:2,
          sender:'小松',
          recipient:'小明',
          deliveryTime:'2022.11.06 16:21',
          state:'运输中',
          arrivalTime:'2022.11.09 16:21'
        },
        {
          id:3,
          sender:'小松',
          recipient:'小明',
          deliveryTime:'2022.11.06 16:21',
          state:'运输中',
          arrivalTime:'2022.11.09 16:21'
        },
        {
          id:4,
          sender:'小松',
          recipient:'小明',
          deliveryTime:'2022.11.06 16:21',
          state:'运输中',
          arrivalTime:'2022.11.09 16:21'
        },
        {
          id:5,
          sender:'小松',
          recipient:'小明',
          deliveryTime:'2022.11.06 16:21',
          state:'运输中',
          arrivalTime:'2022.11.09 16:21'
        },
        {
          id:6,
          sender:'小松',
          recipient:'小明',
          deliveryTime:'2022.11.06 16:21',
          state:'运输中',
          arrivalTime:'2022.11.09 16:21'
        },

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

    }
  }
}
</script>

<style scoped>
#searchTitle{
  margin-left: 2%;
}
#logisticsState{
  font-weight: 600;
  margin-right: 1%;
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
  margin-left: 4%;
  margin-right: 1%;
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
