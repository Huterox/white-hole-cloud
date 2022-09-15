<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="ansid">
      <el-input v-model="dataForm.ansid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="quizid">
      <el-input v-model="dataForm.quizid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="quizTitle">
      <el-input v-model="dataForm.quizTitle" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="1-回答 2-回答删除 3-回答修改" prop="action">
      <el-input v-model="dataForm.action" placeholder="1-回答 2-回答删除 3-回答修改"></el-input>
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
          ansid: '',
          quizid: '',
          quizTitle: '',
          creatTime: '',
          action: ''
        },
        dataRule: {
          ansid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          quizid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          quizTitle: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          creatTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          action: [
            { required: true, message: '1-回答 2-回答删除 3-回答修改不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/user/logans/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.ansid = data.logAns.ansid
                this.dataForm.quizid = data.logAns.quizid
                this.dataForm.quizTitle = data.logAns.quizTitle
                this.dataForm.creatTime = data.logAns.creatTime
                this.dataForm.action = data.logAns.action
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
              url: this.$http.adornUrl(`/user/logans/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'ansid': this.dataForm.ansid,
                'quizid': this.dataForm.quizid,
                'quizTitle': this.dataForm.quizTitle,
                'creatTime': this.dataForm.creatTime,
                'action': this.dataForm.action
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
