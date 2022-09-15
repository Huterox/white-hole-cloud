<template>
  <el-dialog
    :title="!dataForm.communityid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="userid">
      <el-input v-model="dataForm.userid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="communityTitle">
      <el-input v-model="dataForm.communityTitle" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userimg">
      <el-input v-model="dataForm.userimg" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="communityImg">
      <el-input v-model="dataForm.communityImg" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="communityInfo">
      <el-input v-model="dataForm.communityInfo" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="memberNumber">
      <el-input v-model="dataForm.memberNumber" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="blogNumber">
      <el-input v-model="dataForm.blogNumber" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="1-正常 2-审核 3-下架" prop="status">
      <el-input v-model="dataForm.status" placeholder="1-正常 2-审核 3-下架"></el-input>
    </el-form-item>
    <el-form-item label="" prop="communityLike">
      <el-input v-model="dataForm.communityLike" placeholder=""></el-input>
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
          communityTitle: '',
          createTime: '',
          userimg: '',
          userNickname: '',
          communityImg: '',
          communityInfo: '',
          memberNumber: '',
          blogNumber: '',
          status: '',
          communityLike: ''
        },
        dataRule: {
          userid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          communityTitle: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userimg: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userNickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          communityImg: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          communityInfo: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          memberNumber: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          blogNumber: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '1-正常 2-审核 3-下架不能为空', trigger: 'blur' }
          ],
          communityLike: [
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
              url: this.$http.adornUrl(`/whiteholecommunity/community/info/${this.dataForm.communityid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userid = data.community.userid
                this.dataForm.communityTitle = data.community.communityTitle
                this.dataForm.createTime = data.community.createTime
                this.dataForm.userimg = data.community.userimg
                this.dataForm.userNickname = data.community.userNickname
                this.dataForm.communityImg = data.community.communityImg
                this.dataForm.communityInfo = data.community.communityInfo
                this.dataForm.memberNumber = data.community.memberNumber
                this.dataForm.blogNumber = data.community.blogNumber
                this.dataForm.status = data.community.status
                this.dataForm.communityLike = data.community.communityLike
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
              url: this.$http.adornUrl(`/whiteholecommunity/community/${!this.dataForm.communityid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'communityid': this.dataForm.communityid || undefined,
                'userid': this.dataForm.userid,
                'communityTitle': this.dataForm.communityTitle,
                'createTime': this.dataForm.createTime,
                'userimg': this.dataForm.userimg,
                'userNickname': this.dataForm.userNickname,
                'communityImg': this.dataForm.communityImg,
                'communityInfo': this.dataForm.communityInfo,
                'memberNumber': this.dataForm.memberNumber,
                'blogNumber': this.dataForm.blogNumber,
                'status': this.dataForm.status,
                'communityLike': this.dataForm.communityLike
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
