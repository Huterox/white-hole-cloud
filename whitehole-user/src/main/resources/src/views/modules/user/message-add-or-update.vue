<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="消息的类型" prop="type">
      <el-input v-model="dataForm.type" placeholder="消息的类型"></el-input>
    </el-form-item>
    <el-form-item label="消息的id" prop="messageid">
      <el-input v-model="dataForm.messageid" placeholder="消息的id"></el-input>
    </el-form-item>
    <el-form-item label="" prop="messageTitle">
      <el-input v-model="dataForm.messageTitle" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="messageInfo">
      <el-input v-model="dataForm.messageInfo" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="from">
      <el-input v-model="dataForm.from" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="to">
      <el-input v-model="dataForm.to" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="源于那个服务产生的，也就是咱们具体的一服务的地址" prop="source">
      <el-input v-model="dataForm.source" placeholder="源于那个服务产生的，也就是咱们具体的一服务的地址"></el-input>
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
          type: '',
          messageid: '',
          messageTitle: '',
          messageInfo: '',
          from: '',
          to: '',
          createTime: '',
          source: ''
        },
        dataRule: {
          type: [
            { required: true, message: '消息的类型不能为空', trigger: 'blur' }
          ],
          messageid: [
            { required: true, message: '消息的id不能为空', trigger: 'blur' }
          ],
          messageTitle: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          messageInfo: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          from: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          to: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          source: [
            { required: true, message: '源于那个服务产生的，也就是咱们具体的一服务的地址不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/user/message/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.type = data.message.type
                this.dataForm.messageid = data.message.messageid
                this.dataForm.messageTitle = data.message.messageTitle
                this.dataForm.messageInfo = data.message.messageInfo
                this.dataForm.from = data.message.from
                this.dataForm.to = data.message.to
                this.dataForm.createTime = data.message.createTime
                this.dataForm.source = data.message.source
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
              url: this.$http.adornUrl(`/user/message/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'type': this.dataForm.type,
                'messageid': this.dataForm.messageid,
                'messageTitle': this.dataForm.messageTitle,
                'messageInfo': this.dataForm.messageInfo,
                'from': this.dataForm.from,
                'to': this.dataForm.to,
                'createTime': this.dataForm.createTime,
                'source': this.dataForm.source
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
