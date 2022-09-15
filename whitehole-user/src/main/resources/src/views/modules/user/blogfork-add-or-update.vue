<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="articleid">
      <el-input v-model="dataForm.articleid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="articleTitle">
      <el-input v-model="dataForm.articleTitle" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="文章作者的id" prop="articleUserid">
      <el-input v-model="dataForm.articleUserid" placeholder="文章作者的id"></el-input>
    </el-form-item>
    <el-form-item label="文章作者的昵称" prop="articleUserNickname">
      <el-input v-model="dataForm.articleUserNickname" placeholder="文章作者的昵称"></el-input>
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
          articleid: '',
          creatTime: '',
          articleTitle: '',
          articleUserid: '',
          articleUserNickname: ''
        },
        dataRule: {
          articleid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          creatTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          articleTitle: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          articleUserid: [
            { required: true, message: '文章作者的id不能为空', trigger: 'blur' }
          ],
          articleUserNickname: [
            { required: true, message: '文章作者的昵称不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/user/blogfork/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.articleid = data.blogFork.articleid
                this.dataForm.creatTime = data.blogFork.creatTime
                this.dataForm.articleTitle = data.blogFork.articleTitle
                this.dataForm.articleUserid = data.blogFork.articleUserid
                this.dataForm.articleUserNickname = data.blogFork.articleUserNickname
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
              url: this.$http.adornUrl(`/user/blogfork/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'articleid': this.dataForm.articleid,
                'creatTime': this.dataForm.creatTime,
                'articleTitle': this.dataForm.articleTitle,
                'articleUserid': this.dataForm.articleUserid,
                'articleUserNickname': this.dataForm.articleUserNickname
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
