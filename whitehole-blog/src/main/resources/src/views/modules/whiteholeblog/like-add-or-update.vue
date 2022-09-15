<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="blogid">
      <el-input v-model="dataForm.blogid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="blogUserid">
      <el-input v-model="dataForm.blogUserid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="blogUsernickname">
      <el-input v-model="dataForm.blogUsernickname" placeholder=""></el-input>
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
          creatTime: '',
          userNickname: '',
          blogUserid: '',
          blogUsernickname: ''
        },
        dataRule: {
          blogid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          creatTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userNickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          blogUserid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          blogUsernickname: [
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
              url: this.$http.adornUrl(`/whiteholeblog/like/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.blogid = data.like.blogid
                this.dataForm.creatTime = data.like.creatTime
                this.dataForm.userNickname = data.like.userNickname
                this.dataForm.blogUserid = data.like.blogUserid
                this.dataForm.blogUsernickname = data.like.blogUsernickname
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
              url: this.$http.adornUrl(`/whiteholeblog/like/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'blogid': this.dataForm.blogid,
                'creatTime': this.dataForm.creatTime,
                'userNickname': this.dataForm.userNickname,
                'blogUserid': this.dataForm.blogUserid,
                'blogUsernickname': this.dataForm.blogUsernickname
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
