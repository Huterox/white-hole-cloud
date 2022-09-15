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
    <el-form-item label="问题回答自动节截选的内容，通过正则表达式去截取markdown当中的一些内容" prop="ansInfo">
      <el-input v-model="dataForm.ansInfo" placeholder="问题回答自动节截选的内容，通过正则表达式去截取markdown当中的一些内容"></el-input>
    </el-form-item>
    <el-form-item label="问题回答的id" prop="ansid">
      <el-input v-model="dataForm.ansid" placeholder="问题回答的id"></el-input>
    </el-form-item>
    <el-form-item label="" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder=""></el-input>
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
          ansInfo: '',
          ansid: '',
          creatTime: ''
        },
        dataRule: {
          quizid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          quizTitle: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ansInfo: [
            { required: true, message: '问题回答自动节截选的内容，通过正则表达式去截取markdown当中的一些内容不能为空', trigger: 'blur' }
          ],
          ansid: [
            { required: true, message: '问题回答的id不能为空', trigger: 'blur' }
          ],
          creatTime: [
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
              url: this.$http.adornUrl(`/user/ans/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.quizid = data.ans.quizid
                this.dataForm.quizTitle = data.ans.quizTitle
                this.dataForm.ansInfo = data.ans.ansInfo
                this.dataForm.ansid = data.ans.ansid
                this.dataForm.creatTime = data.ans.creatTime
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
              url: this.$http.adornUrl(`/user/ans/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'quizid': this.dataForm.quizid,
                'quizTitle': this.dataForm.quizTitle,
                'ansInfo': this.dataForm.ansInfo,
                'ansid': this.dataForm.ansid,
                'creatTime': this.dataForm.creatTime
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
