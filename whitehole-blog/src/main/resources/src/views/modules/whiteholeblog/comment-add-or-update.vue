<template>
  <el-dialog
    :title="!dataForm.commentid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="博客的评论者的id" prop="userid">
      <el-input v-model="dataForm.userid" placeholder="博客的评论者的id"></el-input>
    </el-form-item>
    <el-form-item label="博客的评论者的昵称" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder="博客的评论者的昵称"></el-input>
    </el-form-item>
    <el-form-item label="博客的评论者的头像" prop="userImg">
      <el-input v-model="dataForm.userImg" placeholder="博客的评论者的头像"></el-input>
    </el-form-item>
    <el-form-item label="博客的评论者的评论" prop="comment">
      <el-input v-model="dataForm.comment" placeholder="博客的评论者的评论"></el-input>
    </el-form-item>
    <el-form-item label="被评论的博客的id" prop="blogid">
      <el-input v-model="dataForm.blogid" placeholder="被评论的博客的id"></el-input>
    </el-form-item>
    <el-form-item label="评论的时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="评论的时间"></el-input>
    </el-form-item>
    <el-form-item label="点赞的个数，这里的话只要是评论都只有点赞" prop="likeNumber">
      <el-input v-model="dataForm.likeNumber" placeholder="点赞的个数，这里的话只要是评论都只有点赞"></el-input>
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
          userid: '',
          userNickname: '',
          userImg: '',
          comment: '',
          commentid: 0,
          blogid: '',
          createTime: '',
          likeNumber: ''
        },
        dataRule: {
          userid: [
            { required: true, message: '博客的评论者的id不能为空', trigger: 'blur' }
          ],
          userNickname: [
            { required: true, message: '博客的评论者的昵称不能为空', trigger: 'blur' }
          ],
          userImg: [
            { required: true, message: '博客的评论者的头像不能为空', trigger: 'blur' }
          ],
          comment: [
            { required: true, message: '博客的评论者的评论不能为空', trigger: 'blur' }
          ],
          blogid: [
            { required: true, message: '被评论的博客的id不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '评论的时间不能为空', trigger: 'blur' }
          ],
          likeNumber: [
            { required: true, message: '点赞的个数，这里的话只要是评论都只有点赞不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.commentid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.commentid) {
            this.$http({
              url: this.$http.adornUrl(`/whiteholeblog/comment/info/${this.dataForm.commentid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userid = data.comment.userid
                this.dataForm.userNickname = data.comment.userNickname
                this.dataForm.userImg = data.comment.userImg
                this.dataForm.comment = data.comment.comment
                this.dataForm.blogid = data.comment.blogid
                this.dataForm.createTime = data.comment.createTime
                this.dataForm.likeNumber = data.comment.likeNumber
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
              url: this.$http.adornUrl(`/whiteholeblog/comment/${!this.dataForm.commentid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid,
                'userNickname': this.dataForm.userNickname,
                'userImg': this.dataForm.userImg,
                'comment': this.dataForm.comment,
                'commentid': this.dataForm.commentid || undefined,
                'blogid': this.dataForm.blogid,
                'createTime': this.dataForm.createTime,
                'likeNumber': this.dataForm.likeNumber
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
