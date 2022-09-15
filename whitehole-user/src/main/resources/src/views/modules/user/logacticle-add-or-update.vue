<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="articleid">
      <el-input v-model="dataForm.articleid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="1-增 2-删除 3-查 4-改   一般情况下，查我们是不会去记录的" prop="action">
      <el-input v-model="dataForm.action" placeholder="1-增 2-删除 3-查 4-改   一般情况下，查我们是不会去记录的"></el-input>
    </el-form-item>
    <el-form-item label="" prop="creteTime">
      <el-input v-model="dataForm.creteTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="articleTitle">
      <el-input v-model="dataForm.articleTitle" placeholder=""></el-input>
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
          action: '',
          creteTime: '',
          articleTitle: ''
        },
        dataRule: {
          articleid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          action: [
            { required: true, message: '1-增 2-删除 3-查 4-改   一般情况下，查我们是不会去记录的不能为空', trigger: 'blur' }
          ],
          creteTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          articleTitle: [
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
              url: this.$http.adornUrl(`/user/logacticle/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.articleid = data.logActicle.articleid
                this.dataForm.action = data.logActicle.action
                this.dataForm.creteTime = data.logActicle.creteTime
                this.dataForm.articleTitle = data.logActicle.articleTitle
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
              url: this.$http.adornUrl(`/user/logacticle/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'articleid': this.dataForm.articleid,
                'action': this.dataForm.action,
                'creteTime': this.dataForm.creteTime,
                'articleTitle': this.dataForm.articleTitle
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
