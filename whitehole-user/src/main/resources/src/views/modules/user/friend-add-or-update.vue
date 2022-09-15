<template>
  <el-dialog
    :title="!dataForm.myUserid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="friendUserid">
      <el-input v-model="dataForm.friendUserid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="friendName">
      <el-input v-model="dataForm.friendName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="朋友的头像" prop="friendImg">
      <el-input v-model="dataForm.friendImg" placeholder="朋友的头像"></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
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
          myUserid: 0,
          friendUserid: '',
          friendName: '',
          friendImg: '',
          createTime: ''
        },
        dataRule: {
          friendUserid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          friendName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          friendImg: [
            { required: true, message: '朋友的头像不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.myUserid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.myUserid) {
            this.$http({
              url: this.$http.adornUrl(`/user/friend/info/${this.dataForm.myUserid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.friendUserid = data.friend.friendUserid
                this.dataForm.friendName = data.friend.friendName
                this.dataForm.friendImg = data.friend.friendImg
                this.dataForm.createTime = data.friend.createTime
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
              url: this.$http.adornUrl(`/user/friend/${!this.dataForm.myUserid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'myUserid': this.dataForm.myUserid || undefined,
                'friendUserid': this.dataForm.friendUserid,
                'friendName': this.dataForm.friendName,
                'friendImg': this.dataForm.friendImg,
                'createTime': this.dataForm.createTime
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
