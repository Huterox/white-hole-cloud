<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户登录时间" prop="loginTime">
      <el-input v-model="dataForm.loginTime" placeholder="用户登录时间"></el-input>
    </el-form-item>
    <el-form-item label="用户退出时间" prop="outTime">
      <el-input v-model="dataForm.outTime" placeholder="用户退出时间"></el-input>
    </el-form-item>
    <el-form-item label="用户的一些操作描述,由系统自动记录的一些信息" prop="systemAction">
      <el-input v-model="dataForm.systemAction" placeholder="用户的一些操作描述,由系统自动记录的一些信息"></el-input>
    </el-form-item>
    <el-form-item label="系统对用户支付信息的记录" prop="rechargeAction">
      <el-input v-model="dataForm.rechargeAction" placeholder="系统对用户支付信息的记录"></el-input>
    </el-form-item>
    <el-form-item label="" prop="rechargeTime">
      <el-input v-model="dataForm.rechargeTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="对用户的警告，用户的一些非法操作" prop="warnAction">
      <el-input v-model="dataForm.warnAction" placeholder="对用户的警告，用户的一些非法操作"></el-input>
    </el-form-item>
    <el-form-item label="" prop="warnTime">
      <el-input v-model="dataForm.warnTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="系统错误的记录" prop="errorAction">
      <el-input v-model="dataForm.errorAction" placeholder="系统错误的记录"></el-input>
    </el-form-item>
    <el-form-item label="" prop="errorTime">
      <el-input v-model="dataForm.errorTime" placeholder=""></el-input>
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
          loginTime: '',
          outTime: '',
          systemAction: '',
          rechargeAction: '',
          rechargeTime: '',
          warnAction: '',
          warnTime: '',
          errorAction: '',
          errorTime: ''
        },
        dataRule: {
          loginTime: [
            { required: true, message: '用户登录时间不能为空', trigger: 'blur' }
          ],
          outTime: [
            { required: true, message: '用户退出时间不能为空', trigger: 'blur' }
          ],
          systemAction: [
            { required: true, message: '用户的一些操作描述,由系统自动记录的一些信息不能为空', trigger: 'blur' }
          ],
          rechargeAction: [
            { required: true, message: '系统对用户支付信息的记录不能为空', trigger: 'blur' }
          ],
          rechargeTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          warnAction: [
            { required: true, message: '对用户的警告，用户的一些非法操作不能为空', trigger: 'blur' }
          ],
          warnTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          errorAction: [
            { required: true, message: '系统错误的记录不能为空', trigger: 'blur' }
          ],
          errorTime: [
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
              url: this.$http.adornUrl(`/user/logbase/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.loginTime = data.logBase.loginTime
                this.dataForm.outTime = data.logBase.outTime
                this.dataForm.systemAction = data.logBase.systemAction
                this.dataForm.rechargeAction = data.logBase.rechargeAction
                this.dataForm.rechargeTime = data.logBase.rechargeTime
                this.dataForm.warnAction = data.logBase.warnAction
                this.dataForm.warnTime = data.logBase.warnTime
                this.dataForm.errorAction = data.logBase.errorAction
                this.dataForm.errorTime = data.logBase.errorTime
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
              url: this.$http.adornUrl(`/user/logbase/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'loginTime': this.dataForm.loginTime,
                'outTime': this.dataForm.outTime,
                'systemAction': this.dataForm.systemAction,
                'rechargeAction': this.dataForm.rechargeAction,
                'rechargeTime': this.dataForm.rechargeTime,
                'warnAction': this.dataForm.warnAction,
                'warnTime': this.dataForm.warnTime,
                'errorAction': this.dataForm.errorAction,
                'errorTime': this.dataForm.errorTime
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
