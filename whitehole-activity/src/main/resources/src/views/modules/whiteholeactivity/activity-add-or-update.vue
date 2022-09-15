<template>
  <el-dialog
    :title="!dataForm.activityid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="activityTitle">
      <el-input v-model="dataForm.activityTitle" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="activityInfo">
      <el-input v-model="dataForm.activityInfo" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="startTime">
      <el-input v-model="dataForm.startTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="endTime">
      <el-input v-model="dataForm.endTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="viewNumber">
      <el-input v-model="dataForm.viewNumber" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="我们这边统计一些活动的受欢迎程度" prop="likeNumber">
      <el-input v-model="dataForm.likeNumber" placeholder="我们这边统计一些活动的受欢迎程度"></el-input>
    </el-form-item>
    <el-form-item label="负责人1" prop="actorid1">
      <el-input v-model="dataForm.actorid1" placeholder="负责人1"></el-input>
    </el-form-item>
    <el-form-item label="活动的负责人1" prop="actor1Nickname">
      <el-input v-model="dataForm.actor1Nickname" placeholder="活动的负责人1"></el-input>
    </el-form-item>
    <el-form-item label="" prop="actor2">
      <el-input v-model="dataForm.actor2" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="actor2Nickname">
      <el-input v-model="dataForm.actor2Nickname" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="actor3">
      <el-input v-model="dataForm.actor3" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="actor3Nickname">
      <el-input v-model="dataForm.actor3Nickname" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="actor4">
      <el-input v-model="dataForm.actor4" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="actorNickname">
      <el-input v-model="dataForm.actorNickname" placeholder=""></el-input>
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
          activityid: 0,
          activityTitle: '',
          activityInfo: '',
          createTime: '',
          startTime: '',
          endTime: '',
          viewNumber: '',
          likeNumber: '',
          actorid1: '',
          actor1Nickname: '',
          actor2: '',
          actor2Nickname: '',
          actor3: '',
          actor3Nickname: '',
          actor4: '',
          actorNickname: ''
        },
        dataRule: {
          activityTitle: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          activityInfo: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          startTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          endTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          viewNumber: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          likeNumber: [
            { required: true, message: '我们这边统计一些活动的受欢迎程度不能为空', trigger: 'blur' }
          ],
          actorid1: [
            { required: true, message: '负责人1不能为空', trigger: 'blur' }
          ],
          actor1Nickname: [
            { required: true, message: '活动的负责人1不能为空', trigger: 'blur' }
          ],
          actor2: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          actor2Nickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          actor3: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          actor3Nickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          actor4: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          actorNickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.activityid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.activityid) {
            this.$http({
              url: this.$http.adornUrl(`/whiteholeactivity/activity/info/${this.dataForm.activityid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.activityTitle = data.activity.activityTitle
                this.dataForm.activityInfo = data.activity.activityInfo
                this.dataForm.createTime = data.activity.createTime
                this.dataForm.startTime = data.activity.startTime
                this.dataForm.endTime = data.activity.endTime
                this.dataForm.viewNumber = data.activity.viewNumber
                this.dataForm.likeNumber = data.activity.likeNumber
                this.dataForm.actorid1 = data.activity.actorid1
                this.dataForm.actor1Nickname = data.activity.actor1Nickname
                this.dataForm.actor2 = data.activity.actor2
                this.dataForm.actor2Nickname = data.activity.actor2Nickname
                this.dataForm.actor3 = data.activity.actor3
                this.dataForm.actor3Nickname = data.activity.actor3Nickname
                this.dataForm.actor4 = data.activity.actor4
                this.dataForm.actorNickname = data.activity.actorNickname
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
              url: this.$http.adornUrl(`/whiteholeactivity/activity/${!this.dataForm.activityid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'activityid': this.dataForm.activityid || undefined,
                'activityTitle': this.dataForm.activityTitle,
                'activityInfo': this.dataForm.activityInfo,
                'createTime': this.dataForm.createTime,
                'startTime': this.dataForm.startTime,
                'endTime': this.dataForm.endTime,
                'viewNumber': this.dataForm.viewNumber,
                'likeNumber': this.dataForm.likeNumber,
                'actorid1': this.dataForm.actorid1,
                'actor1Nickname': this.dataForm.actor1Nickname,
                'actor2': this.dataForm.actor2,
                'actor2Nickname': this.dataForm.actor2Nickname,
                'actor3': this.dataForm.actor3,
                'actor3Nickname': this.dataForm.actor3Nickname,
                'actor4': this.dataForm.actor4,
                'actorNickname': this.dataForm.actorNickname
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
