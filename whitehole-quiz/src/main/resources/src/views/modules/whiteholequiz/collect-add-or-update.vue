<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="quizid">
      <el-input v-model="dataForm.quizid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="收藏这个问题的用户的名字" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder="收藏这个问题的用户的名字"></el-input>
    </el-form-item>
    <el-form-item label="提问者的一个id" prop="quizUserid">
      <el-input v-model="dataForm.quizUserid" placeholder="提问者的一个id"></el-input>
    </el-form-item>
    <el-form-item label="问题的提问者的昵称" prop="quizUsernickname">
      <el-input v-model="dataForm.quizUsernickname" placeholder="问题的提问者的昵称"></el-input>
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
          quizid: '',
          creatTime: '',
          userNickname: '',
          quizUserid: '',
          quizUsernickname: ''
        },
        dataRule: {
          quizid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          creatTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userNickname: [
            { required: true, message: '收藏这个问题的用户的名字不能为空', trigger: 'blur' }
          ],
          quizUserid: [
            { required: true, message: '提问者的一个id不能为空', trigger: 'blur' }
          ],
          quizUsernickname: [
            { required: true, message: '问题的提问者的昵称不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/whiteholequiz/collect/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.quizid = data.collect.quizid
                this.dataForm.creatTime = data.collect.creatTime
                this.dataForm.userNickname = data.collect.userNickname
                this.dataForm.quizUserid = data.collect.quizUserid
                this.dataForm.quizUsernickname = data.collect.quizUsernickname
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
              url: this.$http.adornUrl(`/whiteholequiz/collect/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'quizid': this.dataForm.quizid,
                'creatTime': this.dataForm.creatTime,
                'userNickname': this.dataForm.userNickname,
                'quizUserid': this.dataForm.quizUserid,
                'quizUsernickname': this.dataForm.quizUsernickname
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
