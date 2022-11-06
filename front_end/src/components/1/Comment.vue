<template>
  <div>
    <div id="head">
      <div id="searchHead">
        <el-input placeholder="请输入关键字" v-model="searchcontent">
          <el-button slot="append" icon="el-icon-search" @click="SearchByTitle(searchcontent,currentPage)">搜索</el-button>
        </el-input>
      </div>

      <div id="btnBox">
        <el-button type="primary" @click="FindMyList">仅查看自己</el-button>
        <el-button type="primary" @click="FindAllList">查看所有</el-button>
        <el-button type="primary"  size="large" id="deliverbtn" @click="dialogFormVisible = true">发表话题</el-button>
        <el-dialog title="发表话题" :visible.sync="dialogFormVisible">
          <el-form :model="form" :rules="rules" ref="form">

            <el-form-item label="标题" prop="title">
              <el-input v-model="form.title" placeholder="请输入话题标题"></el-input>
            </el-form-item>

            <el-form-item label="内容" prop="content">
              <el-input type="textarea" v-model="form.content" rows="4" placeholder="请输入话题内容" resize="none"></el-input>
            </el-form-item>

          </el-form>

          <div slot="footer" class="deliver-footer">
            <el-button @click="delivergoback">取 消</el-button>
            <el-button type="primary" @click="deliverConfirm('form')">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
    <div v-for="item in result" id="searchResultBox">
      <el-divider id="divider"></el-divider>
      <p id="resultTitle">{{ item.title }}</p>
      <p id="resultContent" @click="ToDetail(item)">{{ item.content }}</p>
      <el-button v-if="item.account === userName" id="resultDelbtn" type="primary" size="mini" @click="deleteClick(item)">删除</el-button>
      <span id="resultRelease">{{ item.releasedAt }}</span>
      <span id="resultPublisher">{{ item.account }}</span>
    </div>
    <div id="resultFoot">
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

  </div>
</template>

<script>
export default {
  name: "Comment",
  data(){
    return{
      dialogFormVisible: false,

      form: {
        title:'',
        content:'',
      },
      searchcontent:'',
      result:[
      ],
      rules:{
        title:[
          {required: true, message: '标题不能为空', trigger: ['blur', 'change']}
        ],
        content:[
          {required: true, message: '内容不能为空',trigger: ['blur', 'change']}
        ]
      },
      userName:localStorage.getItem('account'),

      resultStatus:'1',
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
    ToDetail(item){
      localStorage.setItem('result-id',item.id)
      localStorage.setItem('result-title',item.title)
      localStorage.setItem('result-content',item.content)
      localStorage.setItem('result-account',item.account)
      localStorage.setItem('result-release',item.releasedAt)
      this.$router.push('/user/commentdetail')
    },

    handleCurrentChange: function(val) {
      this.currentPage = val;
      if (this.resultStatus === '1'){
        this.querySearch(this.currentPage);
      }else {
        this.SearchByName(localStorage.getItem("account"),this.currentPage);
      }
    },

    delivergoback(){
      this.dialogFormVisible = false
      this.form.title = ''
      this.form.content = ''
    },

    querySearch(pageNum) {

      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },

        url: 'http://localhost:8081/javaee1_war_exploded/topic/all?pn='+pageNum,
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data.data.list)
        if (response.data.code==='200') {
          this.result = response.data.data.list
          this.totalCount = response.data.data.total
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },

    SearchByTitle(title,pageNum){
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },

        url: 'http://localhost:8081/javaee1_war_exploded/topic/search?title='+title+'&pn='+pageNum,
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data.data.list)
        if (response.data.code==='200') {
          this.result = response.data.data.list
          this.totalCount = response.data.data.total
          this.searchcontent = ''
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },

    FindMyList(){
      this.resultStatus = '2'
      this.currentPage = '1'
      this.SearchByName(localStorage.getItem("account"),this.currentPage)
    },

    FindAllList(){
      this.resultStatus = '1'
      this.currentPage = '1'
      this.querySearch(this.currentPage)
    },

    SearchByName(name,pageNum){
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },

        url: 'http://localhost:8081/javaee1_war_exploded/topic/my?name='+name+'&pn='+pageNum,
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data.data.list)
        if (response.data.code==='200') {
          this.result = response.data.data.list
          this.totalCount = response.data.data.total
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },

    deleteClick(row){
      this.$confirm('确定删除此讨论?', '提示', {
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

    deleteConfirm(row) {

      let notice = {
        id : row.id
      }
      this.$axios({
        method: 'post',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(notice),
        url: 'http://localhost:8081/javaee1_war_exploded/topic/delete',
      }).then((response) => {          //这里使用了ES6的语法
        if (response.data.code === '200'){
          alert("删除成功")
          this.$router.push('/user/comment')
          this.$router.go(0)
        }else {
          alert("删除失败")
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })

    },

    deliverConfirm(message){
      let topic = {
        title: this.form.title,
        content: this.form.content,
        account: localStorage.getItem('account')
      }
      this.$refs[message].validate((valid) => {
        if (valid) {
          this.$axios({
            method: 'post',
            headers: {
              'Content-type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(topic),
            url: 'http://localhost:8081/javaee1_war_exploded/topic/insert',
          }).then((response) => {          //这里使用了ES6的语法
            if (response.data.code === '200'){
              alert('发表成功')
              this.$router.push('/user/comment')
              this.$router.go(0)
            }else {
              alert('发表失败')
            }
          }).catch((error) => {
            console.log(error)       //请求失败返回的数据
          })
        } else {
          console.log('error login!!');
          return false;
        }
      });
    },
  },
  created() {
    this.querySearch(this.currentPage)
  }
}
</script>

<style scoped>
#searchHead{
  width: 50%;
}
#head{
  margin-top: 20px;
  margin-left: 10%;
  width: 90%;
}
#searchResultBox{
  width: 90%;
  margin-left: 5%;
}
#resultTitle{
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-top: 10px;
  font-size: 18px;
  font-weight: 500;
}
#resultContent{
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 15px;
  color: #616161;
  cursor: pointer;
}
#resultPublisher{
  font-size: 13px;
  color: #8d8b8b;
  float: right;
  margin-right: 15px;
}
#resultRelease{
  font-size: 13px;
  color: #8d8b8b;
  float: right;

}
#divider{
  margin-top: 30px;
  margin-bottom: 0;
}
p{
  margin-top: 5px;
  margin-bottom: 5px;
}
#resultFoot{
  width: 90%;
  margin-left: 5%;
}
#btnBox{
  width: 400px;
  position: absolute;
  left: 60%;
  top: 20px;
}
#resultDelbtn{
  float: right;
  margin-top:-60px;
}
</style>
