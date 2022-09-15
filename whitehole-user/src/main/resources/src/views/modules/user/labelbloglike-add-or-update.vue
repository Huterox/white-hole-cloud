<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="label1">
      <el-input v-model="dataForm.label1" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="label2">
      <el-input v-model="dataForm.label2" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="label3">
      <el-input v-model="dataForm.label3" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="label4">
      <el-input v-model="dataForm.label4" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="label5">
      <el-input v-model="dataForm.label5" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="label6">
      <el-input v-model="dataForm.label6" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="label7">
      <el-input v-model="dataForm.label7" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="label8">
      <el-input v-model="dataForm.label8" placeholder=""></el-input>
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
          label1: '',
          label2: '',
          label3: '',
          label4: '',
          label5: '',
          label6: '',
          label7: '',
          label8: ''
        },
        dataRule: {
          label1: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          label2: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          label3: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          label4: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          label5: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          label6: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          label7: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          label8: [
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
              url: this.$http.adornUrl(`/user/labelbloglike/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.label1 = data.labelBlogLike.label1
                this.dataForm.label2 = data.labelBlogLike.label2
                this.dataForm.label3 = data.labelBlogLike.label3
                this.dataForm.label4 = data.labelBlogLike.label4
                this.dataForm.label5 = data.labelBlogLike.label5
                this.dataForm.label6 = data.labelBlogLike.label6
                this.dataForm.label7 = data.labelBlogLike.label7
                this.dataForm.label8 = data.labelBlogLike.label8
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
              url: this.$http.adornUrl(`/user/labelbloglike/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'label1': this.dataForm.label1,
                'label2': this.dataForm.label2,
                'label3': this.dataForm.label3,
                'label4': this.dataForm.label4,
                'label5': this.dataForm.label5,
                'label6': this.dataForm.label6,
                'label7': this.dataForm.label7,
                'label8': this.dataForm.label8
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
