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
<!--          <el-input  v-model="searchContent" placeholder="请输入快递单号"></el-input>-->
          <el-button type="primary" @click="searchClick">搜索</el-button>

        </div>
<!--        <el-button type="primary" >搜索</el-button>-->
      </div>
    </div>
    <el-divider></el-divider>
    <h3 id="tableTitle">物流信息列表</h3>
    <el-divider></el-divider>

    <el-dialog title="物流详情" :visible.sync="dialogDetail">

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
          <el-form-item label="寄件地址">
            <el-input v-model="detail.deliveryAddress" readonly></el-input>
          </el-form-item>
          <el-form-item label="收货地址">
            <el-input v-model="detail.recipentAddress" readonly></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>


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

        <el-table-column label="物流状态" align="center">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.state===0">未揽件</el-tag>
            <el-tag v-if="scope.row.state===1">已揽件</el-tag>
            <el-tag v-if="scope.row.state===2">运输中</el-tag>
            <el-tag v-if="scope.row.state===3">待派送</el-tag>
            <el-tag v-if="scope.row.state===4">派送中</el-tag>
            <el-tag v-if="scope.row.state===5">已送达</el-tag>
          </template>
        </el-table-column>


        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="lookClick(scope.$index,scope.row)" class="button" icon="el-icon-view">查看</el-button>
<!--            <el-button size="mini" type="text" @click="selectClick(scope.$index,scope.row)" class="button" icon="el-icon-select">接单</el-button>-->
            <el-button size="mini" type="text" v-if="scope.row.state === 0||scope.row.state === 3" @click="selectClick(scope.$index,scope.row)" class="button" icon="el-icon-select">接单</el-button>

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
      deliverymanId:'',
      networkId:'',
      dialogDetail: false,
      detail: {
        id:'',
        sender:'',
        recipient:'',
        state:'',
        deliveryAddress:'',
        recipentAddress:''
      },
      options: [{
        value: '0',
        label: '未揽件'
      },
        {
          value: '3',
          label: '待派送'
        }],
      value: '',
      searchContent:'',
      tableCol: [
        //{prop: "id", label: "id"},
        {prop: "expressId", label: "订单编号"},
        // {prop: "deliverName", label: "寄件人"},
        // {prop: "receiptName", label: "收件人"},
        {prop: "detailaddress", label: "寄件地址"},
        {prop: "recipeaddress", label: "收货地址"},
      ],
      tableData: [

      ],
      resultStatus:'1',

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
      if (this.resultStatus === '1'){
        this.querySearch(this.currentPage);
      }
      else{
        this.searchByfactor(this.currentPage);
      }
      }
    ,
    searchClick(){
      this.resultStatus='2'
      this.currentPage=1
      this.searchByfactor(this.currentPage)
    },

    searchByfactor(pageNum){
      this.resultStatus='2'
      this.currentPage=1
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        // data: JSON.stringify(info),
        url: 'http://8.130.39.140:8081/express/api/deliveryman/expressState/?state='+this.value+'&networkId='+this.networkId+'&page='+pageNum+'&pageSize='+this.pagesize,
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data.data)
        if (response.data.code==='1') {
          this.tableData=response.data.data.records
          // console.log(this.tableData)
          this.totalCount = response.data.data.total
          this.tableData.forEach((item,index)=>{
            item.detailaddress=item.deliverProvince+item.deliverMunicipal+item.deliverCountry+item.deliverStreet+item.deliverDetailAddress
            item.recipeaddress=item.receiptProvince+item.receiptMunicipal+item.receiptCountry+item.receiptStreet+item.receiptDetailAddress

          })
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },

    lookClick(index,row) {
      this.detail.id = row.expressId
      this.detail.sender = row.deliverName
      this.detail.recipient = row.receiptName
      // this.detail.arrivalTime = row.arrivalTime
      // this.detail.deliveryTime = row.deliveryTime
      if (row.state===0){
        this.detail.state = '未揽件'
      }
      else {
        this.detail.state = '待派送'
      }      this.detail.deliveryAddress=row.detailaddress
      this.detail.recipentAddress=row.recipeaddress
      this.dialogDetail = true

    },
    selectClick(index,row){
      let info=JSON.parse(localStorage.getItem('userinfo_kuaidi'))
      if(info.username===''){
        alert('请完善个人信息')
        this.$router.push('/deliveryman/personalCenter')
      }
      else {
      this.$confirm('确定接单此物流?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.selectConfirm(row)

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消接单'
        });
      });
    }
      },
    selectConfirm(row) {
      this.$axios({
          method: 'get',
          headers: {
            'Content-type': 'application/json;charset=UTF-8'
          },
          // data: JSON.stringify(info),
          url: 'http://8.130.39.140:8081/express/api/deliveryman/orderReceiving/?expressId='+row.expressId+'&deliverymanId='+this.deliverymanId,
        }).then((response) => {          //这里使用了ES6的语法
          console.log(response.data)
          if (response.data.code==='1') {
              this.querySearch(this.currentPage)
            // this.totalCount = response.data.data.total
          }
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
        url: 'http://8.130.39.140:8081/express/api/deliveryman/expressList/?networkId='+this.networkId+'&page='+pageNum+'&pageSize='+this.pagesize,
      }).then((response) => {          //这里使用了ES6的语法
        // console.log(response.data.data)
        if (response.data.code==='1') {
          this.tableData = response.data.data.records
          this.totalCount = response.data.data.total
          // this.tableData = this.tableData.filter((item) => {
          //   return item.state ===0||item.state===3
          // });
          this.tableData.forEach((item,index)=>{
            item.detailaddress=item.deliverProvince+item.deliverMunicipal+item.deliverCountry+item.deliverDetailAddress
            item.recipeaddress=item.receiptProvince+item.receiptMunicipal+item.receiptCountry+item.receiptDetailAddress

          })
          // this.totalCount = response.data.data.total
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },

  },

  created () {
    let info=JSON.parse(localStorage.getItem('userinfo_kuaidi'))
    this.deliverymanId=info.deliverymanId
    this.networkId=info.networkId
    this.querySearch(this.currentPage)
    // console.log(this.networkId)
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
