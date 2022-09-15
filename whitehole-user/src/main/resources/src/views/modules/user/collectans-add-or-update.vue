<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="ansid">
      <el-input v-model="dataForm.ansid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="ansInfo">
      <el-input v-model="dataForm.ansInfo" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="quizTitle">
      <el-input v-model="dataForm.quizTitle" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="quizid">
      <el-input v-model="dataForm.quizid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="问题的回答者名字" prop="ansActor">
      <el-input v-model="dataForm.ansActor" placeholder="问题的回答者名字"></el-input>
    </el-form-item>
    <el-form-item label="提问者名字" prop="quizActor">
      <el-input v-model="dataForm.quizActor" placeholder="提问者名字"></el-input>
    </el-form-item>
    <el-form-item label="提问者的id" prop="quizActorId">
      <el-input v-model="dataForm.quizActorId" placeholder="提问者的id"></el-input>
    </el-form-item>
    <el-form-item label="问题回答者的id" prop="ansActorId">
      <el-input v-model="dataForm.ansActorId" placeholder="问题回答者的id"></el-input>
    </el-form-item>
    <el-form-item label="" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="ansStatus">
      <el-input v-model="dataForm.ansStatus" placeholder=""></el-input>
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
          ansInfo: '',
          quizTitle: '',
          quizid: '',
          ansActor: '',
          quizActor: '',
          quizActorId: '',
          ansActorId: '',
          creatTime: '',
          ansStatus: ''
        },
        dataRule: {
          ansid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ansInfo: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          quizTitle: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          quizid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ansActor: [
            { required: true, message: '问题的回答者名字不能为空', trigger: 'blur' }
          ],
          quizActor: [
            { required: true, message: '提问者名字不能为空', trigger: 'blur' }
          ],
          quizActorId: [
            { required: true, message: '提问者的id不能为空', trigger: 'blur' }
          ],
          ansActorId: [
            { required: true, message: '问题回答者的id不能为空', trigger: 'blur' }
          ],
          creatTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ansStatus: [
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
              url: this.$http.adornUrl(`/user/collectans/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.ansid = data.collectAns.ansid
                this.dataForm.ansInfo = data.collectAns.ansInfo
                this.dataForm.quizTitle = data.collectAns.quizTitle
                this.dataForm.quizid = data.collectAns.quizid
                this.dataForm.ansActor = data.collectAns.ansActor
                this.dataForm.quizActor = data.collectAns.quizActor
                this.dataForm.quizActorId = data.collectAns.quizActorId
                this.dataForm.ansActorId = data.collectAns.ansActorId
                this.dataForm.creatTime = data.collectAns.creatTime
                this.dataForm.ansStatus = data.collectAns.ansStatus
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
              url: this.$http.adornUrl(`/user/collectans/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'ansid': this.dataForm.ansid,
                'ansInfo': this.dataForm.ansInfo,
                'quizTitle': this.dataForm.quizTitle,
                'quizid': this.dataForm.quizid,
                'ansActor': this.dataForm.ansActor,
                'quizActor': this.dataForm.quizActor,
                'quizActorId': this.dataForm.quizActorId,
                'ansActorId': this.dataForm.ansActorId,
                'creatTime': this.dataForm.creatTime,
                'ansStatus': this.dataForm.ansStatus
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
