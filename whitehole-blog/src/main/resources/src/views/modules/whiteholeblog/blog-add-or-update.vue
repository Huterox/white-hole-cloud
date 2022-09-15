<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="blogid">
      <el-input v-model="dataForm.blogid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="contentid">
      <el-input v-model="dataForm.contentid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="blogTitle">
      <el-input v-model="dataForm.blogTitle" placeholder=""></el-input>
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
    <el-form-item label="" prop="viewNumber">
      <el-input v-model="dataForm.viewNumber" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="likeNumber">
      <el-input v-model="dataForm.likeNumber" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="collectNumber">
      <el-input v-model="dataForm.collectNumber" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="1-正常 2-审核 3-删除 4-下架" prop="status">
      <el-input v-model="dataForm.status" placeholder="1-正常 2-审核 3-删除 4-下架"></el-input>
    </el-form-item>
    <el-form-item label="1-公开 2-私密" prop="level">
      <el-input v-model="dataForm.level" placeholder="1-公开 2-私密"></el-input>
    </el-form-item>
    <el-form-item label="" prop="forkNumber">
      <el-input v-model="dataForm.forkNumber" placeholder=""></el-input>
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
          blogid: '',
          contentid: '',
          blogTitle: '',
          userNickname: '',
          userImg: '',
          createTime: '',
          viewNumber: '',
          likeNumber: '',
          collectNumber: '',
          status: '',
          level: '',
          forkNumber: ''
        },
        dataRule: {
          blogid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          contentid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          blogTitle: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userNickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userImg: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          viewNumber: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          likeNumber: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          collectNumber: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '1-正常 2-审核 3-删除 4-下架不能为空', trigger: 'blur' }
          ],
          level: [
            { required: true, message: '1-公开 2-私密不能为空', trigger: 'blur' }
          ],
          forkNumber: [
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
              url: this.$http.adornUrl(`/whiteholeblog/blog/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.blogid = data.blog.blogid
                this.dataForm.contentid = data.blog.contentid
                this.dataForm.blogTitle = data.blog.blogTitle
                this.dataForm.userNickname = data.blog.userNickname
                this.dataForm.userImg = data.blog.userImg
                this.dataForm.createTime = data.blog.createTime
                this.dataForm.viewNumber = data.blog.viewNumber
                this.dataForm.likeNumber = data.blog.likeNumber
                this.dataForm.collectNumber = data.blog.collectNumber
                this.dataForm.status = data.blog.status
                this.dataForm.level = data.blog.level
                this.dataForm.forkNumber = data.blog.forkNumber
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
              url: this.$http.adornUrl(`/whiteholeblog/blog/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'blogid': this.dataForm.blogid,
                'contentid': this.dataForm.contentid,
                'blogTitle': this.dataForm.blogTitle,
                'userNickname': this.dataForm.userNickname,
                'userImg': this.dataForm.userImg,
                'createTime': this.dataForm.createTime,
                'viewNumber': this.dataForm.viewNumber,
                'likeNumber': this.dataForm.likeNumber,
                'collectNumber': this.dataForm.collectNumber,
                'status': this.dataForm.status,
                'level': this.dataForm.level,
                'forkNumber': this.dataForm.forkNumber
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
