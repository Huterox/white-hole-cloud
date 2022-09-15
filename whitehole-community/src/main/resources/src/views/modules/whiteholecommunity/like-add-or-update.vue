<template>
  <el-dialog
    :title="!dataForm.communityid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="userid">
      <el-input v-model="dataForm.userid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="communityName">
      <el-input v-model="dataForm.communityName" placeholder=""></el-input>
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
          userid: '',
          userNickname: '',
          createTime: '',
          communityName: ''
        },
        dataRule: {
          userid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userNickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          communityName: [
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
              url: this.$http.adornUrl(`/whiteholecommunity/like/info/${this.dataForm.communityid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userid = data.like.userid
                this.dataForm.userNickname = data.like.userNickname
                this.dataForm.createTime = data.like.createTime
                this.dataForm.communityName = data.like.communityName
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
              url: this.$http.adornUrl(`/whiteholecommunity/like/${!this.dataForm.communityid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'communityid': this.dataForm.communityid || undefined,
                'userid': this.dataForm.userid,
                'userNickname': this.dataForm.userNickname,
                'createTime': this.dataForm.createTime,
                'communityName': this.dataForm.communityName
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
