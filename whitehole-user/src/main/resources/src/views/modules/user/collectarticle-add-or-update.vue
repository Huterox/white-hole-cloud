<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="articleid">
      <el-input v-model="dataForm.articleid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="articleTitle">
      <el-input v-model="dataForm.articleTitle" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="articleActor">
      <el-input v-model="dataForm.articleActor" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="articleInfo">
      <el-input v-model="dataForm.articleInfo" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="articleImg">
      <el-input v-model="dataForm.articleImg" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="articleStatus">
      <el-input v-model="dataForm.articleStatus" placeholder=""></el-input>
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
          articleTitle: '',
          articleActor: '',
          articleInfo: '',
          articleImg: '',
          creatTime: '',
          articleStatus: ''
        },
        dataRule: {
          articleid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          articleTitle: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          articleActor: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          articleInfo: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          articleImg: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          creatTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          articleStatus: [
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
              url: this.$http.adornUrl(`/user/collectarticle/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.articleid = data.collectArticle.articleid
                this.dataForm.articleTitle = data.collectArticle.articleTitle
                this.dataForm.articleActor = data.collectArticle.articleActor
                this.dataForm.articleInfo = data.collectArticle.articleInfo
                this.dataForm.articleImg = data.collectArticle.articleImg
                this.dataForm.creatTime = data.collectArticle.creatTime
                this.dataForm.articleStatus = data.collectArticle.articleStatus
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
              url: this.$http.adornUrl(`/user/collectarticle/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'articleid': this.dataForm.articleid,
                'articleTitle': this.dataForm.articleTitle,
                'articleActor': this.dataForm.articleActor,
                'articleInfo': this.dataForm.articleInfo,
                'articleImg': this.dataForm.articleImg,
                'creatTime': this.dataForm.creatTime,
                'articleStatus': this.dataForm.articleStatus
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
