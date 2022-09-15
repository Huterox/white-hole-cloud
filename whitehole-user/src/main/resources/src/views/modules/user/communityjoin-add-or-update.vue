<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="communityid">
      <el-input v-model="dataForm.communityid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="创建该社区的是谁" prop="communityUserid">
      <el-input v-model="dataForm.communityUserid" placeholder="创建该社区的是谁"></el-input>
    </el-form-item>
    <el-form-item label="社区的封面" prop="communityImg">
      <el-input v-model="dataForm.communityImg" placeholder="社区的封面"></el-input>
    </el-form-item>
    <el-form-item label="" prop="communityInfo">
      <el-input v-model="dataForm.communityInfo" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="communityName">
      <el-input v-model="dataForm.communityName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="communityUserimg">
      <el-input v-model="dataForm.communityUserimg" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="社区的创建者的姓名" prop="communityUserNickname">
      <el-input v-model="dataForm.communityUserNickname" placeholder="社区的创建者的姓名"></el-input>
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
          communityUserid: '',
          communityImg: '',
          communityInfo: '',
          communityName: '',
          communityUserimg: '',
          communityUserNickname: ''
        },
        dataRule: {
          communityid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          communityUserid: [
            { required: true, message: '创建该社区的是谁不能为空', trigger: 'blur' }
          ],
          communityImg: [
            { required: true, message: '社区的封面不能为空', trigger: 'blur' }
          ],
          communityInfo: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          communityName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          communityUserimg: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          communityUserNickname: [
            { required: true, message: '社区的创建者的姓名不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/user/communityjoin/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.communityid = data.communityJoin.communityid
                this.dataForm.communityUserid = data.communityJoin.communityUserid
                this.dataForm.communityImg = data.communityJoin.communityImg
                this.dataForm.communityInfo = data.communityJoin.communityInfo
                this.dataForm.communityName = data.communityJoin.communityName
                this.dataForm.communityUserimg = data.communityJoin.communityUserimg
                this.dataForm.communityUserNickname = data.communityJoin.communityUserNickname
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
              url: this.$http.adornUrl(`/user/communityjoin/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'communityid': this.dataForm.communityid,
                'communityUserid': this.dataForm.communityUserid,
                'communityImg': this.dataForm.communityImg,
                'communityInfo': this.dataForm.communityInfo,
                'communityName': this.dataForm.communityName,
                'communityUserimg': this.dataForm.communityUserimg,
                'communityUserNickname': this.dataForm.communityUserNickname
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
