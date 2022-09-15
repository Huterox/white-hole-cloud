<template>
  <el-dialog
    :title="!dataForm.userid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="这个是用户名，相当于私钥" prop="username">
      <el-input v-model="dataForm.username" placeholder="这个是用户名，相当于私钥"></el-input>
    </el-form-item>
    <el-form-item label="这个是密码，后面会使用md5加密" prop="password">
      <el-input v-model="dataForm.password" placeholder="这个是密码，后面会使用md5加密"></el-input>
    </el-form-item>
    <el-form-item label="用户昵称，可重复，默认会把username作为昵称，可修改" prop="nickname">
      <el-input v-model="dataForm.nickname" placeholder="用户昵称，可重复，默认会把username作为昵称，可修改"></el-input>
    </el-form-item>
    <el-form-item label="用户年龄" prop="age">
      <el-input v-model="dataForm.age" placeholder="用户年龄"></el-input>
    </el-form-item>
    <el-form-item label="用户电话号码，用于后期密码找回" prop="phone">
      <el-input v-model="dataForm.phone" placeholder="用户电话号码，用于后期密码找回"></el-input>
    </el-form-item>
    <el-form-item label="用户邮箱，用于用户注册验证，密码找回，如果用户在本平台注册的话，是必须要的
" prop="email">
      <el-input v-model="dataForm.email" placeholder="用户邮箱，用于用户注册验证，密码找回，如果用户在本平台注册的话，是必须要的
"></el-input>
    </el-form-item>
    <el-form-item label="用户对自己的简介描述，相当于个性签名" prop="describe">
      <el-input v-model="dataForm.describe" placeholder="用户对自己的简介描述，相当于个性签名"></el-input>
    </el-form-item>
    <el-form-item label="用户等级，1-普通用户 2-捐赠，高级用户" prop="level">
      <el-input v-model="dataForm.level" placeholder="用户等级，1-普通用户 2-捐赠，高级用户"></el-input>
    </el-form-item>
    <el-form-item label="白洞值" prop="holeValue">
      <el-input v-model="dataForm.holeValue" placeholder="白洞值"></el-input>
    </el-form-item>
    <el-form-item label="用户地址" prop="home">
      <el-input v-model="dataForm.home" placeholder="用户地址"></el-input>
    </el-form-item>
    <el-form-item label="用户状态，1-正常 2-警告 3-封禁" prop="status">
      <el-input v-model="dataForm.status" placeholder="用户状态，1-正常 2-警告 3-封禁"></el-input>
    </el-form-item>
    <el-form-item label="如果用户选择第三方登录，那么会将第三方的token保存到数据当中，然后自动创建userid,其余信息需要用户自己填写" prop="tokenThere">
      <el-input v-model="dataForm.tokenThere" placeholder="如果用户选择第三方登录，那么会将第三方的token保存到数据当中，然后自动创建userid,其余信息需要用户自己填写"></el-input>
    </el-form-item>
    <el-form-item label="用户加入社区的时间，格式为2022-9-12" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder="用户加入社区的时间，格式为2022-9-12"></el-input>
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
          username: '',
          password: '',
          userid: 0,
          nickname: '',
          age: '',
          phone: '',
          email: '',
          describe: '',
          level: '',
          holeValue: '',
          home: '',
          status: '',
          tokenThere: '',
          creatTime: ''
        },
        dataRule: {
          username: [
            { required: true, message: '这个是用户名，相当于私钥不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '这个是密码，后面会使用md5加密不能为空', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '用户昵称，可重复，默认会把username作为昵称，可修改不能为空', trigger: 'blur' }
          ],
          age: [
            { required: true, message: '用户年龄不能为空', trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '用户电话号码，用于后期密码找回不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '用户邮箱，用于用户注册验证，密码找回，如果用户在本平台注册的话，是必须要的
不能为空', trigger: 'blur' }
          ],
          describe: [
            { required: true, message: '用户对自己的简介描述，相当于个性签名不能为空', trigger: 'blur' }
          ],
          level: [
            { required: true, message: '用户等级，1-普通用户 2-捐赠，高级用户不能为空', trigger: 'blur' }
          ],
          holeValue: [
            { required: true, message: '白洞值不能为空', trigger: 'blur' }
          ],
          home: [
            { required: true, message: '用户地址不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '用户状态，1-正常 2-警告 3-封禁不能为空', trigger: 'blur' }
          ],
          tokenThere: [
            { required: true, message: '如果用户选择第三方登录，那么会将第三方的token保存到数据当中，然后自动创建userid,其余信息需要用户自己填写不能为空', trigger: 'blur' }
          ],
          creatTime: [
            { required: true, message: '用户加入社区的时间，格式为2022-9-12不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/user/user/info/${this.dataForm.userid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.username = data.user.username
                this.dataForm.password = data.user.password
                this.dataForm.nickname = data.user.nickname
                this.dataForm.age = data.user.age
                this.dataForm.phone = data.user.phone
                this.dataForm.email = data.user.email
                this.dataForm.describe = data.user.describe
                this.dataForm.level = data.user.level
                this.dataForm.holeValue = data.user.holeValue
                this.dataForm.home = data.user.home
                this.dataForm.status = data.user.status
                this.dataForm.tokenThere = data.user.tokenThere
                this.dataForm.creatTime = data.user.creatTime
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
              url: this.$http.adornUrl(`/user/user/${!this.dataForm.userid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'username': this.dataForm.username,
                'password': this.dataForm.password,
                'userid': this.dataForm.userid || undefined,
                'nickname': this.dataForm.nickname,
                'age': this.dataForm.age,
                'phone': this.dataForm.phone,
                'email': this.dataForm.email,
                'describe': this.dataForm.describe,
                'level': this.dataForm.level,
                'holeValue': this.dataForm.holeValue,
                'home': this.dataForm.home,
                'status': this.dataForm.status,
                'tokenThere': this.dataForm.tokenThere,
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
