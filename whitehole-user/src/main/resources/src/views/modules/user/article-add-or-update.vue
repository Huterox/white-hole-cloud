<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="文章的id，除了UserID之外的话，我们所有的ID使用自增ID，未来可以使用ID大小表示稀有度" prop="articleid">
      <el-input v-model="dataForm.articleid" placeholder="文章的id，除了UserID之外的话，我们所有的ID使用自增ID，未来可以使用ID大小表示稀有度"></el-input>
    </el-form-item>
    <el-form-item label="文章的标题，虽然设置为128但是，实际上只是为了安全，多做了一点。
" prop="articleTitle">
      <el-input v-model="dataForm.articleTitle" placeholder="文章的标题，虽然设置为128但是，实际上只是为了安全，多做了一点。
"></el-input>
    </el-form-item>
    <el-form-item label="文章的大致信息，这边在文章创建的时候都会一起在用户的地方保存一下，为了减少微服务之间的一个调用，所以这里做一个妥协，牺牲数据冗余，来提高系统的性能。
" prop="articleInfo">
      <el-input v-model="dataForm.articleInfo" placeholder="文章的大致信息，这边在文章创建的时候都会一起在用户的地方保存一下，为了减少微服务之间的一个调用，所以这里做一个妥协，牺牲数据冗余，来提高系统的性能。
"></el-input>
    </el-form-item>
    <el-form-item label="文章的封面，在用户修改的时候也是需要修改的，多查询，少修改，因此这边是可以接受的" prop="articleImg">
      <el-input v-model="dataForm.articleImg" placeholder="文章的封面，在用户修改的时候也是需要修改的，多查询，少修改，因此这边是可以接受的"></el-input>
    </el-form-item>
    <el-form-item label="文章的创建时间
" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="文章的创建时间
"></el-input>
    </el-form-item>
    <el-form-item label="文章的修改时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="文章的修改时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          userid: 0,
          articleid: '',
          articleTitle: '',
          articleInfo: '',
          articleImg: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          articleid: [
            { required: true, message: '文章的id，除了UserID之外的话，我们所有的ID使用自增ID，未来可以使用ID大小表示稀有度不能为空', trigger: 'blur' }
          ],
          articleTitle: [
            { required: true, message: '文章的标题，虽然设置为128但是，实际上只是为了安全，多做了一点。
不能为空', trigger: 'blur' }
          ],
          articleInfo: [
            { required: true, message: '文章的大致信息，这边在文章创建的时候都会一起在用户的地方保存一下，为了减少微服务之间的一个调用，所以这里做一个妥协，牺牲数据冗余，来提高系统的性能。
不能为空', trigger: 'blur' }
          ],
          articleImg: [
            { required: true, message: '文章的封面，在用户修改的时候也是需要修改的，多查询，少修改，因此这边是可以接受的不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '文章的创建时间
不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '文章的修改时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.userid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.userid) {
            this.$http({
              url: this.$http.adornUrl(`/user/article/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.articleid = data.article.articleid
                this.dataForm.articleTitle = data.article.articleTitle
                this.dataForm.articleInfo = data.article.articleInfo
                this.dataForm.articleImg = data.article.articleImg
                this.dataForm.createTime = data.article.createTime
                this.dataForm.updateTime = data.article.updateTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/user/article/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'articleid': this.dataForm.articleid,
                'articleTitle': this.dataForm.articleTitle,
                'articleInfo': this.dataForm.articleInfo,
                'articleImg': this.dataForm.articleImg,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
