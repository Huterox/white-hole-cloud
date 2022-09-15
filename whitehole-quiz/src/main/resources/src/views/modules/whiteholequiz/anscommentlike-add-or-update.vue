<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="点赞者的昵称，这个评论只有回复和点赞" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder="点赞者的昵称，这个评论只有回复和点赞"></el-input>
    </el-form-item>
    <el-form-item label="评论的id" prop="commentid">
      <el-input v-model="dataForm.commentid" placeholder="评论的id"></el-input>
    </el-form-item>
    <el-form-item label="哪个问题的id，是在哪个问题下面的" prop="ansid">
      <el-input v-model="dataForm.ansid" placeholder="哪个问题的id，是在哪个问题下面的"></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="是谁写的评论" prop="commentUserid">
      <el-input v-model="dataForm.commentUserid" placeholder="是谁写的评论"></el-input>
    </el-form-item>
    <el-form-item label="写这个评论的用户昵称" prop="commentUserNickname">
      <el-input v-model="dataForm.commentUserNickname" placeholder="写这个评论的用户昵称"></el-input>
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
          userNickname: '',
          commentid: '',
          ansid: '',
          createTime: '',
          commentUserid: '',
          commentUserNickname: ''
        },
        dataRule: {
          userNickname: [
            { required: true, message: '点赞者的昵称，这个评论只有回复和点赞不能为空', trigger: 'blur' }
          ],
          commentid: [
            { required: true, message: '评论的id不能为空', trigger: 'blur' }
          ],
          ansid: [
            { required: true, message: '哪个问题的id，是在哪个问题下面的不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          commentUserid: [
            { required: true, message: '是谁写的评论不能为空', trigger: 'blur' }
          ],
          commentUserNickname: [
            { required: true, message: '写这个评论的用户昵称不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/whiteholequiz/anscommentlike/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userNickname = data.ansCommentLike.userNickname
                this.dataForm.commentid = data.ansCommentLike.commentid
                this.dataForm.ansid = data.ansCommentLike.ansid
                this.dataForm.createTime = data.ansCommentLike.createTime
                this.dataForm.commentUserid = data.ansCommentLike.commentUserid
                this.dataForm.commentUserNickname = data.ansCommentLike.commentUserNickname
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
              url: this.$http.adornUrl(`/whiteholequiz/anscommentlike/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'userNickname': this.dataForm.userNickname,
                'commentid': this.dataForm.commentid,
                'ansid': this.dataForm.ansid,
                'createTime': this.dataForm.createTime,
                'commentUserid': this.dataForm.commentUserid,
                'commentUserNickname': this.dataForm.commentUserNickname
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
