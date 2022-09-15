<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="被评论的评论的id" prop="commentid">
      <el-input v-model="dataForm.commentid" placeholder="被评论的评论的id"></el-input>
    </el-form-item>
    <el-form-item label="被评论的评论的作者的id" prop="commentUserid">
      <el-input v-model="dataForm.commentUserid" placeholder="被评论的评论的作者的id"></el-input>
    </el-form-item>
    <el-form-item label="被评论的评论的作者的nickname" prop="commentNickname">
      <el-input v-model="dataForm.commentNickname" placeholder="被评论的评论的作者的nickname"></el-input>
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
          creatTime: '',
          commentid: '',
          commentUserid: '',
          commentNickname: ''
        },
        dataRule: {
          userNickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          creatTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          commentid: [
            { required: true, message: '被评论的评论的id不能为空', trigger: 'blur' }
          ],
          commentUserid: [
            { required: true, message: '被评论的评论的作者的id不能为空', trigger: 'blur' }
          ],
          commentNickname: [
            { required: true, message: '被评论的评论的作者的nickname不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/whiteholeblog/comcomentlike/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userNickname = data.comComentLike.userNickname
                this.dataForm.creatTime = data.comComentLike.creatTime
                this.dataForm.commentid = data.comComentLike.commentid
                this.dataForm.commentUserid = data.comComentLike.commentUserid
                this.dataForm.commentNickname = data.comComentLike.commentNickname
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
              url: this.$http.adornUrl(`/whiteholeblog/comcomentlike/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'userNickname': this.dataForm.userNickname,
                'creatTime': this.dataForm.creatTime,
                'commentid': this.dataForm.commentid,
                'commentUserid': this.dataForm.commentUserid,
                'commentNickname': this.dataForm.commentNickname
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
