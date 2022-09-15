<template>
  <el-dialog
    :title="!dataForm.communityid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="blogid">
      <el-input v-model="dataForm.blogid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="blogTitle">
      <el-input v-model="dataForm.blogTitle" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="blogInfo">
      <el-input v-model="dataForm.blogInfo" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="blogImg">
      <el-input v-model="dataForm.blogImg" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="博客对应的用户（写入到社区的博客）" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="博客对应的用户（写入到社区的博客）"></el-input>
    </el-form-item>
    <el-form-item label="" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userImg">
      <el-input v-model="dataForm.userImg" placeholder=""></el-input>
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
          communityid: 0,
          blogid: '',
          blogTitle: '',
          blogInfo: '',
          blogImg: '',
          userId: '',
          userNickname: '',
          userImg: '',
          createTime: ''
        },
        dataRule: {
          blogid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          blogTitle: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          blogInfo: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          blogImg: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userId: [
            { required: true, message: '博客对应的用户（写入到社区的博客）不能为空', trigger: 'blur' }
          ],
          userNickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userImg: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
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
              url: this.$http.adornUrl(`/whiteholecommunity/blogs/info/${this.dataForm.communityid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.blogid = data.blogs.blogid
                this.dataForm.blogTitle = data.blogs.blogTitle
                this.dataForm.blogInfo = data.blogs.blogInfo
                this.dataForm.blogImg = data.blogs.blogImg
                this.dataForm.userId = data.blogs.userId
                this.dataForm.userNickname = data.blogs.userNickname
                this.dataForm.userImg = data.blogs.userImg
                this.dataForm.createTime = data.blogs.createTime
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
              url: this.$http.adornUrl(`/whiteholecommunity/blogs/${!this.dataForm.communityid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'communityid': this.dataForm.communityid || undefined,
                'blogid': this.dataForm.blogid,
                'blogTitle': this.dataForm.blogTitle,
                'blogInfo': this.dataForm.blogInfo,
                'blogImg': this.dataForm.blogImg,
                'userId': this.dataForm.userId,
                'userNickname': this.dataForm.userNickname,
                'userImg': this.dataForm.userImg,
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
