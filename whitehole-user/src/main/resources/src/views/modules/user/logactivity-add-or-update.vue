<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户参加的活动的id" prop="activityid">
      <el-input v-model="dataForm.activityid" placeholder="用户参加的活动的id"></el-input>
    </el-form-item>
    <el-form-item label="" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="参加的活动的名称" prop="activityTitle">
      <el-input v-model="dataForm.activityTitle" placeholder="参加的活动的名称"></el-input>
    </el-form-item>
    <el-form-item label="活动的大致情况" prop="activityInfo">
      <el-input v-model="dataForm.activityInfo" placeholder="活动的大致情况"></el-input>
    </el-form-item>
    <el-form-item label="活动创建的时间" prop="activityStartTime">
      <el-input v-model="dataForm.activityStartTime" placeholder="活动创建的时间"></el-input>
    </el-form-item>
    <el-form-item label="活动的结束时间" prop="activityEndTime">
      <el-input v-model="dataForm.activityEndTime" placeholder="活动的结束时间"></el-input>
    </el-form-item>
    <el-form-item label="用户在活动当中的情况" prop="activityAction">
      <el-input v-model="dataForm.activityAction" placeholder="用户在活动当中的情况"></el-input>
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
          activityid: '',
          creatTime: '',
          activityTitle: '',
          activityInfo: '',
          activityStartTime: '',
          activityEndTime: '',
          activityAction: ''
        },
        dataRule: {
          activityid: [
            { required: true, message: '用户参加的活动的id不能为空', trigger: 'blur' }
          ],
          creatTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          activityTitle: [
            { required: true, message: '参加的活动的名称不能为空', trigger: 'blur' }
          ],
          activityInfo: [
            { required: true, message: '活动的大致情况不能为空', trigger: 'blur' }
          ],
          activityStartTime: [
            { required: true, message: '活动创建的时间不能为空', trigger: 'blur' }
          ],
          activityEndTime: [
            { required: true, message: '活动的结束时间不能为空', trigger: 'blur' }
          ],
          activityAction: [
            { required: true, message: '用户在活动当中的情况不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/user/logactivity/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.activityid = data.logActivity.activityid
                this.dataForm.creatTime = data.logActivity.creatTime
                this.dataForm.activityTitle = data.logActivity.activityTitle
                this.dataForm.activityInfo = data.logActivity.activityInfo
                this.dataForm.activityStartTime = data.logActivity.activityStartTime
                this.dataForm.activityEndTime = data.logActivity.activityEndTime
                this.dataForm.activityAction = data.logActivity.activityAction
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
              url: this.$http.adornUrl(`/user/logactivity/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid || undefined,
                'activityid': this.dataForm.activityid,
                'creatTime': this.dataForm.creatTime,
                'activityTitle': this.dataForm.activityTitle,
                'activityInfo': this.dataForm.activityInfo,
                'activityStartTime': this.dataForm.activityStartTime,
                'activityEndTime': this.dataForm.activityEndTime,
                'activityAction': this.dataForm.activityAction
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
