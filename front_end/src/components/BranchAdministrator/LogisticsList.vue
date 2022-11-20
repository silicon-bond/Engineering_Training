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

    <el-dialog title="编辑物流详情" :visible.sync="editDetail">

      <div id="detailBox">
        <el-form ref="detail" :model="detail" label-width="140px" :rules="rules">
          <el-form-item label="订单编号">
            <el-input v-model="detail.id" readonly></el-input>
          </el-form-item>
          <el-form-item label="寄件人" prop="sender">
            <el-input v-model="detail.sender"></el-input>
          </el-form-item>
          <el-form-item label="寄件人电话号码" prop="senderNumber">
            <el-input v-model="detail.senderNumber"></el-input>
          </el-form-item>
          <el-form-item label="寄件地址(省/市/区)">
            <el-cascader
              size="large"
              :options="jijianoptions"
              v-model="detail.senderSSQ">
            </el-cascader>
          </el-form-item>
          <el-form-item label="收件人" prop="recipient">
            <el-input v-model="detail.recipient"></el-input>
          </el-form-item>
          <el-form-item label="收件人电话号码" prop="recipientNumber">
            <el-input v-model="detail.recipientNumber"></el-input>
          </el-form-item>
            <el-form-item label="收件地址(省/市/区)">
              <el-cascader
                size="large"
                :options="shoujianoptions"
                v-model="detail.recipientSSQ">
              </el-cascader>
            </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="detail.state" style="width: 100%" disabled>
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="下单时间" prop="deliveryTime">
            <el-input v-model="detail.deliveryTime" readonly></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="editClick('detail')">确认</el-button>
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
import { regionData,  CodeToText, TextToCode} from 'element-china-area-data';
export default {
  name: "LogisticsList",
  data() {
    return {
      jijianoptions: regionData,
      shoujianoptions: regionData,
      deliverymanId:'',
      maybechange:'',
      newdata:'',
      networkId:'',
      response:'',
      editDetail: false,
      transferDetail:false,
      detail: {
        id:'',
        sender:'',
        senderNumber:'',
        senderSSQ: null,
        recipient:'',
        recipientNumber:'',
        recipientSSQ: null,
        deliveryTime:'',
        state:'',
        branch:''
      },

      networkname:[

      ],
      rules:{
        sender:[
          { required: true, message: '寄件人不能为空', trigger: 'change' },
        ],
        senderNumber:[
          { pattern:/^1[3|4|5|7|8][0-9]{9}$/,message: '请输入正确的手机号码',trigger: 'change' },
          { required: true, message: '寄件人电话号码不能为空', trigger: 'change' },
        ],
        recipient:[
          { required: true, message: '收件人不能为空', trigger: 'change' },
        ],
        recipientNumber:[
          { pattern:/^1[3|4|5|7|8][0-9]{9}$/,message: '请输入正确的手机号码',trigger: 'change' },
          { required: true, message: '收件人电话号码不能为空', trigger: 'change' },
        ],
        state:[
          { required: true, message: '物流状态不能为空', trigger: 'change' },
        ],
        branch:[
          { required: true, message: '当前所属网点不能为空', trigger: 'change' },
        ]
      },
      options: [{
        value: 0,
        label: '未揽件'
      }, {
        value: 1,
        label: '已揽件'
      }, {
        value: 2,
        label: '运输中'
      }, {
        value: 3,
        label: '待派送'
      }, {
        value: 4,
        label: '派送中'
      }, {
        value: 5,
        label: '已送达'
      }],
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
        {prop: "orderDate", label: "下单时间"}

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
            item.detailaddress=item.deliverProvince+item.deliverMunicipal+item.deliverCountry+item.deliverDetailAddress
            item.recipeaddress=item.receiptProvince+item.receiptMunicipal+item.receiptCountry+item.receiptDetailAddress

          })

        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },

    edit(row){
      this.detail.id = row.expressId
      this.detail.sender = row.deliverName
      this.detail.senderNumber = row.deliverPhoneNumber
      let  array = []
      array[0] = TextToCode[`${row.deliverProvince}`].code
      array[1] = TextToCode[`${row.deliverProvince}`][`${row.deliverMunicipal}`].code
      array[2] = TextToCode[`${row.deliverProvince}`][`${row.deliverMunicipal}`][`${row.deliverCountry}`].code
      this.detail.senderSSQ = array
      this.detail.recipient = row.receiptName
      this.detail.recipientNumber = row.receiptPhoneNumber
      let  array2 = []
      array2[0] = TextToCode[`${row.receiptProvince}`].code
      array2[1] = TextToCode[`${row.receiptProvince}`][`${row.receiptMunicipal}`].code
      array2[2] = TextToCode[`${row.receiptProvince}`][`${row.receiptMunicipal}`][`${row.receiptCountry}`].code
      this.detail.recipientSSQ = array2
      this.detail.deliveryTime = row.orderDate
      this.detail.state = row.state
      this.detail.branch = row.networkId
      this.editDetail = true
    },
    editClick(message){
      this.$refs[message].validate((valid) => {
        if (valid) {
          this.$confirm('确定提交此修改?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.editConfirm()
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消修改'
            });
          });
        } else {
          return false;
        }
      });
    },
    editConfirm(){
      let logisticsMessage = {
        expressId:this.detail.id,
        deliverName:this.detail.sender,
        deliverPhoneNumber:this.detail.senderNumber,
        deliverProvince:CodeToText[`${this.detail.senderSSQ[0]}`],
        deliverMunicipal:CodeToText[`${this.detail.senderSSQ[1]}`],
        deliverCountry:CodeToText[`${this.detail.senderSSQ[2]}`],
        receiptName:this.detail.recipient,
        receiptPhoneNumber:this.detail.recipientNumber,
        receiptProvince:CodeToText[`${this.detail.recipientSSQ[0]}`],
        receiptMunicipal:CodeToText[`${this.detail.recipientSSQ[1]}`],
        receiptCountry:CodeToText[`${this.detail.recipientSSQ[2]}`],
        state:this.detail.state
      }
      this.$axios({
        method: 'put',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(logisticsMessage),
        url: 'http://8.130.39.140:8081/express/api/express/updateById',
      }).then((response) => {          //这里使用了ES6的语法
        if (response.data.message==="success"){
          this.$message({
            message: '修改物流成功',
            type: 'success'
          });
          setTimeout(()=> {
            this.$router.go(0)
          }, 1000)

        }else {
          this.$message.error('修改物流失败');
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
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
