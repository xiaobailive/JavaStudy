<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" controls-position="right" min="0"/>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
          -->
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career"/>
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>
      <!-- 讲师头像：TODO -->
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import teacherApi from '@/api/edu/teacherApi'
export default {
  data() {
    return {
      teacher: {
        name: '',
        sort: 0,
        level: 1,
        career: '',
        intro: '',
        avatar: ''
      },
      saveBtnDisabled: false
    }
  },
  watch: { // 监听
    $route(to, from) { // 监听路由 发生变化会执行
      this.init()
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      // 判断路径是否有id 如果有代表修改页面
      if (this.$route.params && this.$route.params.id) {
        const id = this.$route.params.id
        this.getTeacherById(id)
      } else { // 如果不是修改跳转过来的页面 清空页面
        this.teacher = {}
      }
    },
    saveOrUpdate(teacher) {
      // 判断是修改还是添加功能
      if (!this.teacher.id) {
        // 添加
        this.saveBtnDisabled = true
        this.saveTeacher()
      } else {
        // 修改
        this.saveBtnDisabled = true
        this.updateTeacherById()
      }
    },
    saveTeacher() {
      teacherApi.saveOrUpdate(this.teacher)
        .then(response => {
          this.$message({
            type: 'success',
            message: '保存成功!'
          })
          this.$router.push({ path: '/teacher/list' })
        })
        .catch(response => {
          console.warn('添加失败')
        })
    },
    getTeacherById(id) {
      teacherApi.getTeacherById(id)
        .then(response => {
          this.teacher = response.data.rows
        })
        .catch(errors => {
          console.warn('错误!')
        })
    },
    updateTeacherById() {
      teacherApi.updateTeacherById(this.teacher)
        .then(response => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          this.$router.push({ path: '/teacher/list' })
        })
        .catch(errors => {
          console.warn('修改失败')
        })
    }
  }
}
</script>
