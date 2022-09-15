<template>
  <el-dialog
    :title="!dataForm.communityid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="1-创建 2-删除 3-下架" prop="action">
      <el-input v-model="dataForm.action" placeholder="1-创建 2-删除 3-下架"></el-input>
    </el-form-item>
    <el-form-item label="哪个用户的" prop="userid">
      <el-input v-model="dataForm.userid" placeholder="哪个用户的"></el-input>
    </el-form-item>
    <el-form-item label="" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder=""></el-input>
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
          communityid: 0,
          createTime: '',
          action: '',
          userid: '',
          userNickname: ''
        },
        dataRule: {
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          action: [
            { required: true, message: '1-创建 2-删除 3-下架不能为空', trigger: 'blur' }
          ],
          userid: [
            { required: true, message: '哪个用户的不能为空', trigger: 'blur' }
          ],
          userNickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.communityid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.communityid) {
            this.$http({
              url: this.$http.adornUrl(`/whiteholecommunity/log/info/${this.dataForm.communityid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.createTime = data.log.createTime
                this.dataForm.action = data.log.action
                this.dataForm.userid = data.log.userid
                this.dataForm.userNickname = data.log.userNickname
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
              url: this.$http.adornUrl(`/whiteholecommunity/log/${!this.dataForm.communityid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'communityid': this.dataForm.communityid || undefined,
                'createTime': this.dataForm.createTime,
                'action': this.dataForm.action,
                'userid': this.dataForm.userid,
                'userNickname': this.dataForm.userNickname
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
