<template>
  <el-dialog
    :title="!dataForm.ansid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="userid">
      <el-input v-model="dataForm.userid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="quizid">
      <el-input v-model="dataForm.quizid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="context">
      <el-input v-model="dataForm.context" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="ansViewNumber">
      <el-input v-model="dataForm.ansViewNumber" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="ansLikeNumber">
      <el-input v-model="dataForm.ansLikeNumber" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="只要点击进入查看的回答的具体的页面，那么就+1" prop="ansCollectNumber">
      <el-input v-model="dataForm.ansCollectNumber" placeholder="只要点击进入查看的回答的具体的页面，那么就+1"></el-input>
    </el-form-item>
    <el-form-item label="问题回答者的昵称" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder="问题回答者的昵称"></el-input>
    </el-form-item>
    <el-form-item label="这个是问题的回答者的头像" prop="userImg">
      <el-input v-model="dataForm.userImg" placeholder="这个是问题的回答者的头像"></el-input>
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
          quizid: '',
          creatTime: '',
          context: '',
          ansViewNumber: '',
          ansLikeNumber: '',
          ansCollectNumber: '',
          userNickname: '',
          userImg: '',
          ansid: 0,
        },
        dataRule: {
          userid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          quizid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          creatTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          context: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ansViewNumber: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ansLikeNumber: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ansCollectNumber: [
            { required: true, message: '只要点击进入查看的回答的具体的页面，那么就+1不能为空', trigger: 'blur' }
          ],
          userNickname: [
            { required: true, message: '问题回答者的昵称不能为空', trigger: 'blur' }
          ],
          userImg: [
            { required: true, message: '这个是问题的回答者的头像不能为空', trigger: 'blur' }
          ],
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.ansid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.ansid) {
            this.$http({
              url: this.$http.adornUrl(`/whiteholequiz/ans/info/${this.dataForm.ansid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userid = data.ans.userid
                this.dataForm.quizid = data.ans.quizid
                this.dataForm.creatTime = data.ans.creatTime
                this.dataForm.context = data.ans.context
                this.dataForm.ansViewNumber = data.ans.ansViewNumber
                this.dataForm.ansLikeNumber = data.ans.ansLikeNumber
                this.dataForm.ansCollectNumber = data.ans.ansCollectNumber
                this.dataForm.userNickname = data.ans.userNickname
                this.dataForm.userImg = data.ans.userImg
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
              url: this.$http.adornUrl(`/whiteholequiz/ans/${!this.dataForm.ansid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid,
                'quizid': this.dataForm.quizid,
                'creatTime': this.dataForm.creatTime,
                'context': this.dataForm.context,
                'ansViewNumber': this.dataForm.ansViewNumber,
                'ansLikeNumber': this.dataForm.ansLikeNumber,
                'ansCollectNumber': this.dataForm.ansCollectNumber,
                'userNickname': this.dataForm.userNickname,
                'userImg': this.dataForm.userImg,
                'ansid': this.dataForm.ansid || undefined,
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
