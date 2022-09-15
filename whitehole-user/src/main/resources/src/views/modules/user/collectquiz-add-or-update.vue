<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="quizid">
      <el-input v-model="dataForm.quizid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="quizTitle">
      <el-input v-model="dataForm.quizTitle" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="quizInfo">
      <el-input v-model="dataForm.quizInfo" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="quizActor">
      <el-input v-model="dataForm.quizActor" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="quizStatus">
      <el-input v-model="dataForm.quizStatus" placeholder=""></el-input>
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
          quizTitle: '',
          quizInfo: '',
          quizActor: '',
          createTime: '',
          quizStatus: ''
        },
        dataRule: {
          quizid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          quizTitle: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          quizInfo: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          quizActor: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          quizStatus: [
            { required: true, message: '不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/user/collectquiz/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.quizid = data.collectQuiz.quizid
                this.dataForm.quizTitle = data.collectQuiz.quizTitle
                this.dataForm.quizInfo = data.collectQuiz.quizInfo
                this.dataForm.quizActor = data.collectQuiz.quizActor
                this.dataForm.createTime = data.collectQuiz.createTime
                this.dataForm.quizStatus = data.collectQuiz.quizStatus
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
              url: this.$http.adornUrl(`/user/collectquiz/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'quizid': this.dataForm.quizid,
                'quizTitle': this.dataForm.quizTitle,
                'quizInfo': this.dataForm.quizInfo,
                'quizActor': this.dataForm.quizActor,
                'createTime': this.dataForm.createTime,
                'quizStatus': this.dataForm.quizStatus
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
