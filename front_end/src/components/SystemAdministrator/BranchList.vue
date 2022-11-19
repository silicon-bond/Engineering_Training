<template>
  <div>
    <div>
      <h2 id="searchTitle">网点查询</h2>
      <div id="search">
        <div id="searchContent">
          <el-input  v-model="searchContent" placeholder="请输入网点编号"></el-input>
        </div>
        <el-button type="primary">搜索</el-button>
        <el-button id="addUserbtn" type="primary" @click="add">添加网点</el-button>
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
        <el-table-column prop="province,country,municipal" label="网点所在地址" align="center">
          <template slot-scope="scope">
            {{ scope.row.province}}{{ scope.row.municipal }}{{ scope.row.country }}
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="edit(scope.row)" class="button">编辑</el-button>
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

    <el-dialog title="编辑网点信息" :visible.sync="editDetail">

      <div id="editBox">
        <el-form ref="detail" :model="detail" label-width="125px" :rules="editRules">
          <el-form-item label="网点编号">
            <el-input v-model="detail.networkId" readonly></el-input>
          </el-form-item>
          <el-form-item label="网点名称" prop="networkName">
            <el-input v-model="detail.networkName"></el-input>
          </el-form-item>
          <el-form-item label="网点地址">
            <el-cascader
              size="large"
              :options="addressoptions"
              v-model="detail.address">
            </el-cascader>
          </el-form-item>
          <el-form-item label="注册时间">
            <el-input v-model="detail.registerDate" readonly></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="editClick('detail')">确认</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>
    <el-dialog title="新增网点信息" :visible.sync="addDetail">

      <div id="addBox">
        <el-form ref="addMessage" :model="addMessage" label-width="125px" :rules="addRules">
          <el-form-item label="网点名称" prop="networkName">
            <el-input v-model="addMessage.networkName"></el-input>
          </el-form-item>
          <el-form-item label="网点地址">
            <el-cascader
              size="large"
              :options="addressoptions"
              v-model="addMessage.address">
            </el-cascader>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="addClick('addMessage')">确认</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { regionData,  CodeToText, TextToCode} from 'element-china-area-data';
export default {
  name: "branchList",
  data() {
    return {
      addressoptions: regionData,
      detail: {
        networkId:'',
        networkName:'',
        address: null,
        registerDate:'',
      },
      addMessage:{
        networkName:'',
        address: null,
      },
      dialogFormVisible: false,
      lookDetail: false,
      editDetail: false,
      addDetail:false,
      searchContent:'',
      tableCol: [
        //{prop: "id", label: "id"},
        {prop: "networkId", label: "网点编号"},
        {prop: "networkName", label: "网点名称"},
        {prop: "registerDate", label: "注册时间"},
      ],
      editRules:{
        networkName:[
          { required: true, message: '网点名称不能为空', trigger: 'change' },
        ]
      },
      addRules:{
        networkName:[
          { required: true, message: '网点名称不能为空', trigger: 'change' },
        ]
      },
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

    add(){
      this.addDetail = true
    },
    addClick(message){
      this.$refs[message].validate((valid) => {
        if (valid) {
          this.$confirm('确定新增此网点?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.addConfirm()
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
    addConfirm(){
      let branchMessage = {
        networkName:this.addMessage.name,
        province:CodeToText[`${this.addMessage.address[0]}`],
        municipal:CodeToText[`${this.addMessage.address[1]}`],
        country:CodeToText[`${this.addMessage.address[2]}`],
      }
      this.$axios({
        method: 'post',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(branchMessage),
        url: 'http://8.130.39.140:8081/express/api/system-administrator/network-management/addOneNetwork',
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data)
        if (response.data.message==="网点增加成功"){
          this.$message({
            message: '此网点新增成功',
            type: 'success'
          });
          this.$router.go(0)
        }else {
          this.$message.error('此网点新增失败');
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },
    edit(row){
      this.detail.networkId = row.networkId
      this.detail.networkName = row.networkName
      let  array = []
      array[0] = TextToCode[`${row.province}`].code
      array[1] = TextToCode[`${row.province}`][`${row.municipal}`].code
      array[2] = TextToCode[`${row.province}`][`${row.municipal}`][`${row.country}`].code
      this.detail.address = array
      this.detail.registerDate = row.registerDate
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
    editConfirm(){//
      let branchMessage = {
        networkId:this.detail.networkId,
        networkName:this.detail.networkName,
        province:CodeToText[`${this.detail.address[0]}`],
        municipal:CodeToText[`${this.detail.address[1]}`],
        country:CodeToText[`${this.detail.address[2]}`],
        registerDate:this.detail.registerDate,
      }
      this.$axios({
        method: 'put',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(branchMessage),
        url: 'http://8.130.39.140:8081/express/api/system-administrator/network-management/updateNetwork',
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data)
        // if (response.data.message==="success"){
        //   this.$message({
        //     message: '修改物流成功',
        //     type: 'success'
        //   });
        //   this.$router.go(0)
        // }else {
        //   this.$message.error('修改物流失败');
        // }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },
    deleteClick(row){
      this.$confirm('确定删除此网点?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteConfirm(row)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    deleteConfirm(row){
      this.$axios({
        method: 'delete',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        url: 'http://8.130.39.140:8081/express/api/system-administrator/network-management/deleteNetworkById/'+row.networkId,
      }).then((response) => {          //这里使用了ES6的语法
        if (response.data.message==="网点删除成功"){
          this.$message({
            message: '删除网点成功',
            type: 'success'
          });
          this.$router.go(0)
        }else {
          this.$message.error('删除网点失败');
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
        url: 'http://8.130.39.140:8081/express/api/system-administrator/network-management/getNetworkByPage?page='+pageNum+'&pageSize='+this.pagesize,
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data)
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
#addUserbtn{
  float: right;
  margin-right: 3%;
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
