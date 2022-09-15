<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="blogid">
      <el-input v-model="dataForm.blogid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="这篇博客的作者的id" prop="blogUserid">
      <el-input v-model="dataForm.blogUserid" placeholder="这篇博客的作者的id"></el-input>
    </el-form-item>
    <el-form-item label="" prop="blogTitle">
      <el-input v-model="dataForm.blogTitle" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="这篇文章的作者的名字" prop="blogUserNickname">
      <el-input v-model="dataForm.blogUserNickname" placeholder="这篇文章的作者的名字"></el-input>
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
          createTime: '',
          blogUserid: '',
          blogTitle: '',
          blogUserNickname: ''
        },
        dataRule: {
          blogid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          blogUserid: [
            { required: true, message: '这篇博客的作者的id不能为空', trigger: 'blur' }
          ],
          blogTitle: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          blogUserNickname: [
            { required: true, message: '这篇文章的作者的名字不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/whiteholeblog/fork/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.blogid = data.fork.blogid
                this.dataForm.createTime = data.fork.createTime
                this.dataForm.blogUserid = data.fork.blogUserid
                this.dataForm.blogTitle = data.fork.blogTitle
                this.dataForm.blogUserNickname = data.fork.blogUserNickname
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
              url: this.$http.adornUrl(`/whiteholeblog/fork/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'blogid': this.dataForm.blogid,
                'createTime': this.dataForm.createTime,
                'blogUserid': this.dataForm.blogUserid,
                'blogTitle': this.dataForm.blogTitle,
                'blogUserNickname': this.dataForm.blogUserNickname
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
