<template>
  <el-dialog
    :title="!dataForm.quizid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户的id，用户的id是uuid生成的" prop="userid">
      <el-input v-model="dataForm.userid" placeholder="用户的id，用户的id是uuid生成的"></el-input>
    </el-form-item>
    <el-form-item label="问题的具体描述" prop="quizContent">
      <el-input v-model="dataForm.quizContent" placeholder="问题的具体描述"></el-input>
    </el-form-item>
    <el-form-item label="问题的标题" prop="quizTitle">
      <el-input v-model="dataForm.quizTitle" placeholder="问题的标题"></el-input>
    </el-form-item>
    <el-form-item label="用户的昵称，提问题者的昵称" prop="userNickname">
      <el-input v-model="dataForm.userNickname" placeholder="用户的昵称，提问题者的昵称"></el-input>
    </el-form-item>
    <el-form-item label="用户的头像，当用户更换头像的时候，这个也需要更新" prop="userImg">
      <el-input v-model="dataForm.userImg" placeholder="用户的头像，当用户更换头像的时候，这个也需要更新"></el-input>
    </el-form-item>
    <el-form-item label="问题的收藏量" prop="quizCollectNumber">
      <el-input v-model="dataForm.quizCollectNumber" placeholder="问题的收藏量"></el-input>
    </el-form-item>
    <el-form-item label="问题的流量量，这个浏览量是只要你点开了这个连接就算，就算没有用户登录也算。" prop="quizViewNumber">
      <el-input v-model="dataForm.quizViewNumber" placeholder="问题的流量量，这个浏览量是只要你点开了这个连接就算，就算没有用户登录也算。"></el-input>
    </el-form-item>
    <el-form-item label="问题的点赞量" prop="quizLikeNumber">
      <el-input v-model="dataForm.quizLikeNumber" placeholder="问题的点赞量"></el-input>
    </el-form-item>
    <el-form-item label="问题回答的数量" prop="quizAnsNumber">
      <el-input v-model="dataForm.quizAnsNumber" placeholder="问题回答的数量"></el-input>
    </el-form-item>
    <el-form-item label="问题的状态，正常，审核，审核失败，下架" prop="status">
      <el-input v-model="dataForm.status" placeholder="问题的状态，正常，审核，审核失败，下架"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
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
          userid: '',
          quizid: 0,
          quizContent: '',
          quizTitle: '',
          userNickname: '',
          userImg: '',
          quizCollectNumber: '',
          quizViewNumber: '',
          quizLikeNumber: '',
          quizAnsNumber: '',
          status: '',
          createTime: ''
        },
        dataRule: {
          userid: [
            { required: true, message: '用户的id，用户的id是uuid生成的不能为空', trigger: 'blur' }
          ],
          quizContent: [
            { required: true, message: '问题的具体描述不能为空', trigger: 'blur' }
          ],
          quizTitle: [
            { required: true, message: '问题的标题不能为空', trigger: 'blur' }
          ],
          userNickname: [
            { required: true, message: '用户的昵称，提问题者的昵称不能为空', trigger: 'blur' }
          ],
          userImg: [
            { required: true, message: '用户的头像，当用户更换头像的时候，这个也需要更新不能为空', trigger: 'blur' }
          ],
          quizCollectNumber: [
            { required: true, message: '问题的收藏量不能为空', trigger: 'blur' }
          ],
          quizViewNumber: [
            { required: true, message: '问题的流量量，这个浏览量是只要你点开了这个连接就算，就算没有用户登录也算。不能为空', trigger: 'blur' }
          ],
          quizLikeNumber: [
            { required: true, message: '问题的点赞量不能为空', trigger: 'blur' }
          ],
          quizAnsNumber: [
            { required: true, message: '问题回答的数量不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '问题的状态，正常，审核，审核失败，下架不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.quizid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.quizid) {
            this.$http({
              url: this.$http.adornUrl(`/whiteholequiz/quiz/info/${this.dataForm.quizid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userid = data.quiz.userid
                this.dataForm.quizContent = data.quiz.quizContent
                this.dataForm.quizTitle = data.quiz.quizTitle
                this.dataForm.userNickname = data.quiz.userNickname
                this.dataForm.userImg = data.quiz.userImg
                this.dataForm.quizCollectNumber = data.quiz.quizCollectNumber
                this.dataForm.quizViewNumber = data.quiz.quizViewNumber
                this.dataForm.quizLikeNumber = data.quiz.quizLikeNumber
                this.dataForm.quizAnsNumber = data.quiz.quizAnsNumber
                this.dataForm.status = data.quiz.status
                this.dataForm.createTime = data.quiz.createTime
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
              url: this.$http.adornUrl(`/whiteholequiz/quiz/${!this.dataForm.quizid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userid': this.dataForm.userid,
                'quizid': this.dataForm.quizid || undefined,
                'quizContent': this.dataForm.quizContent,
                'quizTitle': this.dataForm.quizTitle,
                'userNickname': this.dataForm.userNickname,
                'userImg': this.dataForm.userImg,
                'quizCollectNumber': this.dataForm.quizCollectNumber,
                'quizViewNumber': this.dataForm.quizViewNumber,
                'quizLikeNumber': this.dataForm.quizLikeNumber,
                'quizAnsNumber': this.dataForm.quizAnsNumber,
                'status': this.dataForm.status,
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
