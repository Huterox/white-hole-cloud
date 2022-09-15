<template>
  <el-dialog
    :title="!dataForm.imgid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户图片保存的地址" prop="imgpath">
      <el-input v-model="dataForm.imgpath" placeholder="用户图片保存的地址"></el-input>
    </el-form-item>
    <el-form-item label="这个是用户的id" prop="userid">
      <el-input v-model="dataForm.userid" placeholder="这个是用户的id"></el-input>
    </el-form-item>
    <el-form-item label="图片的上传时间" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder="图片的上传时间"></el-input>
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
          imgid: 0,
          imgpath: '',
          userid: '',
          creatTime: ''
        },
        dataRule: {
          imgpath: [
            { required: true, message: '用户图片保存的地址不能为空', trigger: 'blur' }
          ],
          userid: [
            { required: true, message: '这个是用户的id不能为空', trigger: 'blur' }
          ],
          creatTime: [
            { required: true, message: '图片的上传时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.imgid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.imgid) {
            this.$http({
              url: this.$http.adornUrl(`/user/headimg/info/${this.dataForm.imgid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.imgpath = data.headimg.imgpath
                this.dataForm.userid = data.headimg.userid
                this.dataForm.creatTime = data.headimg.creatTime
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
              url: this.$http.adornUrl(`/user/headimg/${!this.dataForm.imgid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'imgid': this.dataForm.imgid || undefined,
                'imgpath': this.dataForm.imgpath,
                'userid': this.dataForm.userid,
                'creatTime': this.dataForm.creatTime
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
