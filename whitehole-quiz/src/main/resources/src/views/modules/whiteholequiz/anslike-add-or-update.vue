<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="ansUserid">
      <el-input v-model="dataForm.ansUserid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="ansid">
      <el-input v-model="dataForm.ansid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder=""></el-input>
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
          ansUserid: '',
          ansid: '',
          userNickname: '',
          creatTime: ''
        },
        dataRule: {
          ansUserid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ansid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userNickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/whiteholequiz/anslike/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.ansUserid = data.ansLike.ansUserid
                this.dataForm.ansid = data.ansLike.ansid
                this.dataForm.userNickname = data.ansLike.userNickname
                this.dataForm.creatTime = data.ansLike.creatTime
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
              url: this.$http.adornUrl(`/whiteholequiz/anslike/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'ansUserid': this.dataForm.ansUserid,
                'ansid': this.dataForm.ansid,
                'userNickname': this.dataForm.userNickname,
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
