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
    <el-form-item label="pull的用户名称" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder="pull的用户名称"></el-input>
    </el-form-item>
    <el-form-item label="博客作者的id" prop="blogUserid">
      <el-input v-model="dataForm.blogUserid" placeholder="博客作者的id"></el-input>
    </el-form-item>
    <el-form-item label="博客作者的名字" prop="blogUserNickname">
      <el-input v-model="dataForm.blogUserNickname" placeholder="博客作者的名字"></el-input>
    </el-form-item>
    <el-form-item label="提交者的用户头像" prop="userimg">
      <el-input v-model="dataForm.userimg" placeholder="提交者的用户头像"></el-input>
    </el-form-item>
    <el-form-item label="" prop="contentid">
      <el-input v-model="dataForm.contentid" placeholder=""></el-input>
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
          userNickname: '',
          blogUserid: '',
          blogUserNickname: '',
          userimg: '',
          contentid: ''
        },
        dataRule: {
          blogid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userNickname: [
            { required: true, message: 'pull的用户名称不能为空', trigger: 'blur' }
          ],
          blogUserid: [
            { required: true, message: '博客作者的id不能为空', trigger: 'blur' }
          ],
          blogUserNickname: [
            { required: true, message: '博客作者的名字不能为空', trigger: 'blur' }
          ],
          userimg: [
            { required: true, message: '提交者的用户头像不能为空', trigger: 'blur' }
          ],
          contentid: [
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
              url: this.$http.adornUrl(`/whiteholeblog/pull/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.blogid = data.pull.blogid
                this.dataForm.createTime = data.pull.createTime
                this.dataForm.userNickname = data.pull.userNickname
                this.dataForm.blogUserid = data.pull.blogUserid
                this.dataForm.blogUserNickname = data.pull.blogUserNickname
                this.dataForm.userimg = data.pull.userimg
                this.dataForm.contentid = data.pull.contentid
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
              url: this.$http.adornUrl(`/whiteholeblog/pull/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'blogid': this.dataForm.blogid,
                'createTime': this.dataForm.createTime,
                'userNickname': this.dataForm.userNickname,
                'blogUserid': this.dataForm.blogUserid,
                'blogUserNickname': this.dataForm.blogUserNickname,
                'userimg': this.dataForm.userimg,
                'contentid': this.dataForm.contentid
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
