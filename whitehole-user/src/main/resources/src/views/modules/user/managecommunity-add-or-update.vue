<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="communityid">
      <el-input v-model="dataForm.communityid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="communityName">
      <el-input v-model="dataForm.communityName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="communityInfo">
      <el-input v-model="dataForm.communityInfo" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="communityImg">
      <el-input v-model="dataForm.communityImg" placeholder=""></el-input>
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
          communityid: '',
          createTime: '',
          communityName: '',
          communityInfo: '',
          communityImg: ''
        },
        dataRule: {
          communityid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          communityName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          communityInfo: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          communityImg: [
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
              url: this.$http.adornUrl(`/user/managecommunity/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.communityid = data.manageCommunity.communityid
                this.dataForm.createTime = data.manageCommunity.createTime
                this.dataForm.communityName = data.manageCommunity.communityName
                this.dataForm.communityInfo = data.manageCommunity.communityInfo
                this.dataForm.communityImg = data.manageCommunity.communityImg
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
              url: this.$http.adornUrl(`/user/managecommunity/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'communityid': this.dataForm.communityid,
                'createTime': this.dataForm.createTime,
                'communityName': this.dataForm.communityName,
                'communityInfo': this.dataForm.communityInfo,
                'communityImg': this.dataForm.communityImg
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
