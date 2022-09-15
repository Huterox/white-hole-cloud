<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="friendid">
      <el-input v-model="dataForm.friendid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="friendNickname">
      <el-input v-model="dataForm.friendNickname" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="1-添加 2-删除" prop="action">
      <el-input v-model="dataForm.action" placeholder="1-添加 2-删除"></el-input>
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
          friendid: '',
          creatTime: '',
          friendNickname: '',
          action: ''
        },
        dataRule: {
          friendid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          creatTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          friendNickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          action: [
            { required: true, message: '1-添加 2-删除不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/user/logfriend/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.friendid = data.logFriend.friendid
                this.dataForm.creatTime = data.logFriend.creatTime
                this.dataForm.friendNickname = data.logFriend.friendNickname
                this.dataForm.action = data.logFriend.action
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
              url: this.$http.adornUrl(`/user/logfriend/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'friendid': this.dataForm.friendid,
                'creatTime': this.dataForm.creatTime,
                'friendNickname': this.dataForm.friendNickname,
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
