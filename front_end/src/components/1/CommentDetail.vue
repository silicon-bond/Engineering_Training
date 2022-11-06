<template>
  <div>
    <div id="commDetailHead">
      <p id="commDetailTitle">{{ detail.title }}</p>
      <p id="commDetailContent">{{ detail.content }}</p>
      <span id="commDetailPublisher">{{ detail.account }}</span>
      <span id="commDetailRelease">{{ detail.released_at }}</span>
    </div>

    <div id="CommentDeliver">
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入内容"
        v-model="deliverContent">
      </el-input>
      <el-button id="deliverbtn" size="mini" type="primary" @click="deliverConfirm()">评论</el-button>
    </div>
    <div id="comment" v-for="item in comment">
      <el-divider id="divider"></el-divider>
      <p id="commentContent">{{ item.content }}</p>
      <span id="commentPublisher">{{ item.account }}</span>
      <span id="commentRelease">{{ item.releasedAt }}</span>

      <el-button v-if="item.account === userName" id="commentDelbtn" type="primary" size="mini" @click="deleteClick(item)">删除</el-button>
    </div>

    <div id="commentFoot">
      <el-divider id="footdivider"></el-divider>
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
  name: "CommentDetail",
  data(){
    return{
      detail:{
        id:'',
        title:'',
        content:'',
        account:'',
        released_at:'',
      },
      comment:[
      ],

      userName: localStorage.getItem('account'),
      deliverContent:'',
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
      this.detailSearch(this.detail.id,this.currentPage);
    },

    detailSearch(id,pageNum){
      this.$axios({
        method: 'get',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },

        url: 'http://localhost:8081/javaee1_war_exploded/comment/all?tid='+id+'&pn='+pageNum,
      }).then((response) => {          //这里使用了ES6的语法
        console.log(response.data.data.list)
        if (response.data.code==='200') {
          this.comment = response.data.data.list
          this.totalCount = response.data.data.total
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })
    },

    deliverConfirm(){
      if (this.deliverContent === ''){
        alert("评论内容不能为空")
      }
      else {
        let comment = {
          tid:this.detail.id,
          account: localStorage.getItem("account"),
          content: this.deliverContent
        }
        this.$axios({
          method: 'post',
          headers: {
            'Content-type': 'application/json;charset=UTF-8'
          },
          data: JSON.stringify(comment),
          url: 'http://localhost:8081/javaee1_war_exploded/comment/insert',
        }).then((response) => {          //这里使用了ES6的语法
          if (response.data.code === '200'){
            alert('发布成功')
            this.currentPage = '1'
            this.detailSearch(this.detail.id,this.currentPage)
            this.$router.go(0)
          }else {
            alert('发布失败')
          }
        }).catch((error) => {
          console.log(error)       //请求失败返回的数据
        })
      }
    },

    deleteClick(item){
      this.$confirm('确定删除此评论?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteConfirm(item)

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
        url: 'http://localhost:8081/javaee1_war_exploded/comment/delete',
      }).then((response) => {          //这里使用了ES6的语法
        if (response.data.code === '200'){
          alert("删除成功")
          this.$router.push('/user/commentdetail')
          this.$router.go(0)
        }
      }).catch((error) => {
        console.log(error)       //请求失败返回的数据
      })

    }

  },
  created() {
    this.detail.id = localStorage.getItem('result-id')
    this.detail.title = localStorage.getItem('result-title')
    this.detail.content = localStorage.getItem('result-content')
    this.detail.account = localStorage.getItem('result-account')
    this.detail.released_at = localStorage.getItem('result-release')
    this.detailSearch(this.detail.id,this.currentPage)
  }
}
</script>

<style scoped>
#commDetailHead{
  width: 90%;
  background-color: #fafafa;
  box-shadow: 2px 2px 2px #e2e2e2;
  margin-left: 3%;
  padding-left: 2%;
  padding-right: 2%;
  padding-bottom: 1%;
  border-radius: 16px;
  border: 1px solid #ccc;
}
#CommentDeliver{
  width: 92%;
  margin-left: 4%;
  margin-top: 1%;
}
#commDetailTitle{
  margin-top: 10px;
  font-size: 18px;
  font-weight: 500;
}
#commDetailContent{
  font-size: 15px;
  color: #616161;
}
#commDetailPublisher{
  font-size: 13px;
  color: #8d8b8b;
  margin-right: 15px;
}
#commDetailRelease{
  font-size: 13px;
  color: #8d8b8b;

}
#comment{
  width: 92%;
  margin-left: 4%;
}
#commentFoot{
  width: 92%;
  margin-left: 4%;
}
#divider{
  margin-top: 10px;
  margin-bottom: 0;
}
#footdivider{
  margin-top: 10px;
  margin-bottom: 0;
}
#commentContent{
  font-size: 15px;
  color: #616161;
}
#commentPublisher{
  font-size: 13px;
  color: #8d8b8b;
  margin-right: 15px;
}
#commentRelease{
  font-size: 13px;
  color: #8d8b8b;
}
#deliverbtn{
  margin-top: 5px;
}
#commentDelbtn{
  float: right;
  margin-top:-40px;
}
</style>
