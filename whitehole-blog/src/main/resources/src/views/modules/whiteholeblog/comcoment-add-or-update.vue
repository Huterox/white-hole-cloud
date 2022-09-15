<template>
  <el-dialog
    :title="!dataForm.comComentid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="userid">
      <el-input v-model="dataForm.userid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="写评论的评论的用户i" prop="commentid">
      <el-input v-model="dataForm.commentid" placeholder="写评论的评论的用户i"></el-input>
    </el-form-item>
    <el-form-item label="写评论的用户的id" prop="commentUserid">
      <el-input v-model="dataForm.commentUserid" placeholder="写评论的用户的id"></el-input>
    </el-form-item>
    <el-form-item label="写评论的那个用户的昵称" prop="commentNickname">
      <el-input v-model="dataForm.commentNickname" placeholder="写评论的那个用户的昵称"></el-input>
    </el-form-item>
    <el-form-item label="写评论的评论的用户昵称" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder="写评论的评论的用户昵称"></el-input>
    </el-form-item>
    <el-form-item label="" prop="userimg">
      <el-input v-model="dataForm.userimg" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="评论的评论的内容" prop="comment">
      <el-input v-model="dataForm.comment" placeholder="评论的评论的内容"></el-input>
    </el-form-item>
    <el-form-item label="" prop="likeNumber">
      <el-input v-model="dataForm.likeNumber" placeholder=""></el-input>
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
          commentid: '',
          commentUserid: '',
          commentNickname: '',
          userNickname: '',
          userimg: '',
          createTime: '',
          comComentid: 0,
          comment: '',
          likeNumber: ''
        },
        dataRule: {
          userid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          commentid: [
            { required: true, message: '写评论的评论的用户i不能为空', trigger: 'blur' }
          ],
          commentUserid: [
            { required: true, message: '写评论的用户的id不能为空', trigger: 'blur' }
          ],
          commentNickname: [
            { required: true, message: '写评论的那个用户的昵称不能为空', trigger: 'blur' }
          ],
          userNickname: [
            { required: true, message: '写评论的评论的用户昵称不能为空', trigger: 'blur' }
          ],
          userimg: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          comment: [
            { required: true, message: '评论的评论的内容不能为空', trigger: 'blur' }
          ],
          likeNumber: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.comComentid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.comComentid) {
            this.$http({
              url: this.$http.adornUrl(`/whiteholeblog/comcoment/info/${this.dataForm.comComentid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userid = data.comComent.userid
                this.dataForm.commentid = data.comComent.commentid
                this.dataForm.commentUserid = data.comComent.commentUserid
                this.dataForm.commentNickname = data.comComent.commentNickname
                this.dataForm.userNickname = data.comComent.userNickname
                this.dataForm.userimg = data.comComent.userimg
                this.dataForm.createTime = data.comComent.createTime
                this.dataForm.comment = data.comComent.comment
                this.dataForm.likeNumber = data.comComent.likeNumber
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
              url: this.$http.adornUrl(`/whiteholeblog/comcoment/${!this.dataForm.comComentid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid,
                'commentid': this.dataForm.commentid,
                'commentUserid': this.dataForm.commentUserid,
                'commentNickname': this.dataForm.commentNickname,
                'userNickname': this.dataForm.userNickname,
                'userimg': this.dataForm.userimg,
                'createTime': this.dataForm.createTime,
                'comComentid': this.dataForm.comComentid || undefined,
                'comment': this.dataForm.comment,
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
