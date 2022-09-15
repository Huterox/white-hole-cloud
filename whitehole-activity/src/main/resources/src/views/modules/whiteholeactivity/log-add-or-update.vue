<template>
  <el-dialog
    :title="!dataForm.actorid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="1-修改 2-提交" prop="action">
      <el-input v-model="dataForm.action" placeholder="1-修改 2-提交"></el-input>
    </el-form-item>
    <el-form-item label="" prop="activityid">
      <el-input v-model="dataForm.activityid" placeholder=""></el-input>
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
          actorid: 0,
          action: '',
          activityid: '',
          createTime: ''
        },
        dataRule: {
          action: [
            { required: true, message: '1-修改 2-提交不能为空', trigger: 'blur' }
          ],
          activityid: [
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
        this.dataForm.actorid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.actorid) {
            this.$http({
              url: this.$http.adornUrl(`/whiteholeactivity/log/info/${this.dataForm.actorid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.action = data.log.action
                this.dataForm.activityid = data.log.activityid
                this.dataForm.createTime = data.log.createTime
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
              url: this.$http.adornUrl(`/whiteholeactivity/log/${!this.dataForm.actorid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'actorid': this.dataForm.actorid || undefined,
                'action': this.dataForm.action,
                'activityid': this.dataForm.activityid,
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
