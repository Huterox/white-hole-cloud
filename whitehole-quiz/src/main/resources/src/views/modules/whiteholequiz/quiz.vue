<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('whiteholequiz:quiz:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('whiteholequiz:quiz:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="userid"
        header-align="center"
        align="center"
        label="用户的id，用户的id是uuid生成的">
      </el-table-column>
      <el-table-column
        prop="quizid"
        header-align="center"
        align="center"
        label="问题的主键，除了user其他的都是使用id自增的策略">
      </el-table-column>
      <el-table-column
        prop="quizContent"
        header-align="center"
        align="center"
        label="问题的具体描述">
      </el-table-column>
      <el-table-column
        prop="quizTitle"
        header-align="center"
        align="center"
        label="问题的标题">
      </el-table-column>
      <el-table-column
        prop="userNickname"
        header-align="center"
        align="center"
        label="用户的昵称，提问题者的昵称">
      </el-table-column>
      <el-table-column
        prop="userImg"
        header-align="center"
        align="center"
        label="用户的头像，当用户更换头像的时候，这个也需要更新">
      </el-table-column>
      <el-table-column
        prop="quizCollectNumber"
        header-align="center"
        align="center"
        label="问题的收藏量">
      </el-table-column>
      <el-table-column
        prop="quizViewNumber"
        header-align="center"
        align="center"
        label="问题的流量量，这个浏览量是只要你点开了这个连接就算，就算没有用户登录也算。">
      </el-table-column>
      <el-table-column
        prop="quizLikeNumber"
        header-align="center"
        align="center"
        label="问题的点赞量">
      </el-table-column>
      <el-table-column
        prop="quizAnsNumber"
        header-align="center"
        align="center"
        label="问题回答的数量">
      </el-table-column>
      <el-table-column
        prop="status"
        header-align="center"
        align="center"
        label="问题的状态，正常，审核，审核失败，下架">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="创建时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.quizid)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.quizid)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './quiz-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/whiteholequiz/quiz/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.quizid
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/whiteholequiz/quiz/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>
