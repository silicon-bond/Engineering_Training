<!--<template>-->
<!--  <div>-->
<!--    <div>-->
<!--      <h3 id="searchTitle">物流查询</h3>-->
<!--      <div id="search">-->
<!--        <span id="logisticsState">物流状态</span>-->
<!--        <el-select v-model="value" placeholder="请选择物流状态">-->
<!--          <el-option-->
<!--            v-for="item in options"-->
<!--            :key="item.value"-->
<!--            :label="item.label"-->
<!--            :value="item.value">-->
<!--          </el-option>-->
<!--        </el-select>-->
<!--        <div id="searchContent">-->
<!--          <el-input  v-model="searchContent" placeholder="请输入快递单号"></el-input>-->
<!--        </div>-->
<!--        <el-button type="primary">搜索</el-button>-->
<!--      </div>-->
<!--    </div>-->
<!--    <el-divider></el-divider>-->
<!--    <h3 id="tableTitle">物流信息列表</h3>-->
<!--    <el-divider></el-divider>-->
<!--    <div id="table">-->
<!--      <el-table :data="tableData"-->
<!--                stripe-->
<!--                style="width: 100%"-->
<!--      >-->
<!--        <el-table-column-->
<!--          v-for="(item,i) in tableCol"-->
<!--          :key="i"-->
<!--          :prop="item.prop"-->
<!--          :label="item.label"-->
<!--          :width="item.width"-->
<!--          align="center"-->
<!--          show-overflow-tooltip>-->
<!--        </el-table-column>-->

<!--        <el-table-column label="操作" align="center">-->
<!--          <template slot-scope="scope">-->
<!--            &lt;!&ndash;            <el-button size="mini" type="text" @click="lookClick(scope.$index,scope.row)" class="button" icon="el-icon-view">查看</el-button>&ndash;&gt;-->
<!--            &lt;!&ndash;            <el-button size="mini" type="text" @click="selectClick(scope.$index,scope.row)" class="button" icon="el-icon-select">接单</el-button>&ndash;&gt;-->
<!--            <el-button size="mini" type="text" @click="transfer()" class="button" icon="el-icon-select">转送</el-button>-->

<!--          </template>-->
<!--        </el-table-column>-->
<!--      </el-table>-->
<!--    </div>-->
<!--    <el-divider></el-divider>-->

<!--    <el-pagination-->
<!--      id="pagination"-->
<!--      @current-change="handleCurrentChange"-->
<!--      :current-page="currentPage"-->
<!--      :page-size="pagesize"-->
<!--      layout="prev, pager, next"-->
<!--      :total="totalCount">-->
<!--    </el-pagination>-->
<!--  </div>-->
<!--</template>-->
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
          <div class="block">
            <span class="demonstration">开始日期</span>
            <el-date-picker
              v-model="begindate"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </div>
        </div>

        <div id="searchContent">
          <div class="block">
            <span class="demonstration">截止日期</span>
            <el-date-picker
              v-model="endate"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </div>
        </div>

        <el-button type="primary" @click="searchClick">搜索</el-button>

      </div>
    </div>
    <el-divider></el-divider>
    <h3 id="tableTitle">物流信息列表</h3>
    <el-divider></el-divider>

    <el-dialog title="编辑物流详情" :visible.sync="dialogDetail">

      <div id="detailBox">
        <el-form ref="detail" :model="detail" label-width="80px">
          <el-form-item label="订单编号">
            <el-input v-model="detail.id" readonly></el-input>
          </el-form-item>
          <el-form-item label="寄件人" prop="sender">
            <el-input v-model="detail.sender"></el-input>
          </el-form-item>
          <el-form-item label="寄件人电话号码" prop="senderNumber">
            <el-input v-model="detail.senderNumber"></el-input>
          </el-form-item>
          <el-form-item label="收件人" prop="recipient">
            <el-input v-model="detail.recipient"></el-input>
          </el-form-item>
          <el-form-item label="收件人电话号码" prop="recipientNumber">
            <el-input v-model="detail.recipientNumber"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-input v-model="detail.state" readonly></el-input>
          </el-form-item>
          <el-form-item label="寄件地址">
            <el-input v-model="detail.deliveryAddress"></el-input>
          </el-form-item>
          <el-form-item label="收货地址">
            <el-input v-model="detail.recipentAddress"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>


    <el-dialog title="转发网点查询" :visible.sync="transferDetail">
    <div id="detailBox">
      <el-select v-model="nextnet" placeholder="请选择转发的网点">
        <el-option
          v-for="item in networkname"
          :key="item.networkId"
          :label="item.networkName"
          :value="item.networkId">
        </el-option>
      </el-select>
      <el-button type="primary" @click="transfer">确认转发</el-button>
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
            <el-button size="mini" type="text" @click="edit(scope.row)" class="button">编辑</el-button>
            <!--            <el-button size="mini" type="text" @click="selectClick(scope.$index,scope.row)" class="button" icon="el-icon-select">接单</el-button>-->
            <el-button size="mini" type="text" v-if="scope.row.state === 2" @click="transferclick(scope.$index,scope.row)" class="button" icon="el-icon-select">转发</el-button>

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
      maybechange:'',
      newdata:'',
      networkId:'',
      response:'',
      dialogDetail: false,
      transferDetail:false,
      detail: {
        id:'',
        sender:'',
        recipient:'',
        state:'',
        deliveryAddress:'',
        recipentAddress:''
      },

      networkname:[

      ],

      options: [{
        value: '0',
        label: '未揽件'
      },
        {
          value: '3',
          label: '待派送'
        },{
        value: '1',
          label:'已揽件'
        },
        {
          value: '2',
          label:'运输中'
        },
        {
          value: '4',
          label: '派送中'
        },
        {
          value: '5',
          label: '已送达'
        }
        ],
      value: '',
      nextnet:'',
      searchContent:'',
      begindate:'',
      endate:'',
      tableCol: [
        //{prop: "id", label: "id"},
        {prop: "expressId", label: "订单编号"},
        // {prop: "deliverName", label: "寄件人"},
        // {prop: "receiptName", label: "收件人"},
        {prop: "detailaddress", label: "寄件地址"},
        {prop: "recipeaddress", label: "收货地址"},
        {prop: "orderDate", label: "订单时间"}

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
    },
    searchClick(){
      this.resultStatus='2'
      this.currentPage=1
      this.searchByfactor(this.currentPage)
    },

    searchByfactor(pageNum){
      this.resultStatus='2'
      this.currentPage=1
      console.log(this.begindate)
      console.log(this.endate)
      let bd =''
      let ed=''
      if(this.begindate!==''&&this.begindate!==null){
      const yy = this.begindate.getFullYear()
      const MM = (this.begindate.getMonth() + 1) < 10 ? '0' + (this.begindate.getMonth() + 1) : (this.begindate.getMonth() + 1)
      const dd = this.begindate.getDate() < 10 ? '0' + this.begindate.getDate() : this.begindate.getDate()
      bd=yy + '-' + MM + '-' + dd
    }
      else{bd=''}

      if(this.endate!==''&&this.endate!==null){
        const yye = this.endate.getFullYear()
        const MMe = (this.endate.getMonth() + 1) < 10 ? '0' + (this.endate.getMonth() + 1) : (this.endate.getMonth() + 1)
        const dde = this.endate.getDate() < 10 ? '0' + this.endate.getDate() : this.endate.getDate()
        ed=yye + '-' + MMe + '-' + dde
      }
      else{ed=''}

      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        // data: JSON.stringify(info),
        url: 'http://8.130.39.140:8081/express/api/network-administrator/getExpressByStateAndTime?networkId='+this.networkId+'&page='+pageNum+'&pageSize='+this.pagesize+'&state='+this.value+'&dateStart='+bd+'&dateOver='+ed,
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
      if (row.state===1){
        this.detail.state = '已揽件'
      }
      else if(row.state===2) {
        this.detail.state = '运输中'
      }
      else if(row.state===4) {
        this.detail.state = '派送中'
      }
      else if (row.state===0){
        this.detail.state = '未揽件'
      }
      else if (row.state===3){
        this.detail.state = '待派送'
      }
      else {
        this.detail.state = '已送达'
      }
      this.detail.deliveryAddress=row.detailaddress
      this.detail.recipentAddress=row.recipeaddress
      this.dialogDetail = true

    },
    edit(row){

    },
    editClick(){

    },
    editConfirm(){

    },
    transferclick(index,row) {
      this.transferDetail = true
      this.maybechange=row.expressId
    },

    transfer(){
      this.tableData.forEach((item,index)=>{
        if(item.expressId===this.maybechange){
          if(this.nextnet===0){
            item.state= 3
            this.newdata=item

          }
          else{
            item.networkId=this.nextnet
            this.newdata=item
          }
        }
      })
      // console.log(this.newdata)
      this.$axios({
        method: 'put',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(this.newdata),
        url: 'http://8.130.39.140:8081/express/api/express/updateById',
      }).then((response) => {          //这里使用了ES6的语法
        // console.log(response.data.data)
        if (response.data.code==='1') {
          this.querySearch(this.currentPage)
          this.transferDetail = false

          // this.tableData=response.data.data.records
          // // console.log(this.tableData)
          // this.totalCount = response.data.data.total
          // this.tableData.forEach((item,index)=>{
          //   item.detailaddress=item.deliverProvince+item.deliverMunicipal+item.deliverCountry+item.deliverStreet+item.deliverDetailAddress
          //   item.recipeaddress=item.receiptProvince+item.receiptMunicipal+item.receiptCountry+item.receiptStreet+item.receiptDetailAddress
          //
          // })
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },


    getnetwork(){
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        // data: JSON.stringify(this.tableData),
        url: 'http://8.130.39.140:8081/express/api/allNetworks',
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data)
        if (response.data.code==='1') {
          this.networkname = response.data.data
          let opt=this.networkname.filter((item) => {
            return item.networkId !== this.networkId
          })
          this.networkname=opt
          this.networkname.forEach((item,index)=>{
            item.networkName=item.municipal+item.networkName
          })
          this.networkname.push({
            networkId:0,
            networkName:'目的地'
          })
          // this.response=response
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
        // data: JSON.stringify(info),
        url: 'http://8.130.39.140:8081//express/api/network-administrator/getExpress/?networkId='+this.networkId+'&page='+pageNum+'&pageSize='+this.pagesize,
      }).then((response) => {
//这里使用了ES6的语法
//         console.log(response)
        if (response.data.code==='1') {
          this.tableData = response.data.data.records
          // this.tableData = this.tableData.filter((item) => {
          //   return item.state ===1||item.state===4
          // });
          console.log(this.tableData)
          this.tableData.forEach((item,index)=>{
            item.detailaddress=item.deliverProvince+item.deliverMunicipal+item.deliverCountry+item.deliverDetailAddress
            item.recipeaddress=item.receiptProvince+item.receiptMunicipal+item.receiptCountry+item.receiptDetailAddress

          })
          this.totalCount = response.data.data.total
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },

  },

  created () {
    let info=JSON.parse(localStorage.getItem('userinfo_kuaidi'))
    this.networkId=info.networkId
    this.querySearch(this.currentPage)
    this.getnetwork()
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
#detailBox{
  margin-left: 6%;
  margin-right: 9%;
}
</style>
