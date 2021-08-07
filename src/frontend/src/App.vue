<template>
  <el-container>

    <el-header>
      <el-row justify="space-between">
        <el-col :span="6">
          <div class="logo">
            <h4>LOGO</h4>
          </div>
        </el-col>
        <el-col :span="4">
          <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
            <el-menu-item index="1">Home</el-menu-item>
            <el-menu-item index="2">Logout</el-menu-item>
          </el-menu>
        </el-col>
      </el-row>
    </el-header>

    <el-main>
      <div class="main-container">

        <h2>Add People</h2>
        <el-divider></el-divider>
        <el-row justify="center">

          <el-col :span="12">

            <el-form :model="form" :rules="rules" ref="formAdd" label-width="120px">
              <el-form-item :required="true" label="First Name" prop="firstName">
                <el-input v-model="form.firstName"></el-input>
              </el-form-item>
              <el-form-item :required="true" label="Last Name" prop="lastName">
                <el-input v-model="form.lastName"></el-input>
              </el-form-item>
              <el-form-item :required="true" label="Birth Date" prop="dob">
                <el-date-picker v-model="form.dob" class="cdate-picker" placeholder="Pick a date"
                                type="date" value-format="YYYY-MM-DD"></el-date-picker>
              </el-form-item>
              <el-form-item :required="true" label="Gender" prop="gender">
                <el-radio-group v-model="form.gender">
                  <el-radio label="Male"></el-radio>
                  <el-radio label="Female"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item :required="true" label="Upload Image">
                <el-upload :auto-upload="false" :file-list="fileList" :limit="1" :multiple="false"
                           :on-change="handleUploadChange" action="#">
                  <template #trigger>
                    <el-button size="small">Select File</el-button>
                  </template>
                  <b></b>
                </el-upload>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit">Add</el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </div>
    </el-main>

    <el-row align="middle" justify="center" tag="footer">
      <el-col :span="24">

          <span>
            &copy; 2021. All rights reserved.
          </span>

      </el-col>
    </el-row>
  </el-container>
</template>

<script>

import {defineComponent, reactive, ref} from 'vue'
import CustomerService from "./services/customer"
import {ElNotification} from 'element-plus';

export default defineComponent({
  setup() {
    const activeIndex = ref('1')
    const formAdd = ref(null)
    let fileList = reactive([])
    let form = reactive({
      firstName: '',
      lastName: '',
      gender: '',
      dob: '',
      profilePhoto: ''
    })
    let rules = reactive({
      firstName: [{required: true, message: 'Invalid input', trigger: 'blur'}],
      lastName: [{required: true, message: 'Invalid input', trigger: 'blur'}],
      gender: [{required: true, message: 'Invalid input', trigger: 'change'}],
      dob: [{required: true, message: 'Invalid input', trigger: 'change'}],
      // profilePhoto: [{validator: checkProfilePhoto, trigger: 'change'}]
    })

    const handleSelect = (key, keyPath) => {
      console.log(key, keyPath);
    }
    const handleUploadChange = (file, listFile) => {
      fileList = listFile.slice(-1)
    }
    const onSubmit = () => {
      formAdd.value.validate((valid) => {
        if (valid) {
          if (fileList.length === 0){
            ElNotification({title: "Error", message: "Select an image", type: "error"})
            return false
          }
          const formData = new FormData()
          formData.append('firstName', form.firstName)
          formData.append('lastName', form.lastName)
          formData.append('gender', form.gender)
          formData.append('dob', form.dob)
          formData.append('profilePhoto', fileList[0].raw)

          CustomerService.addCustomer(formData)
              .then((res) => {
                if (res.status === 200) {
                  ElNotification({title: "Success", message: res.data, type: "success"})
                } else {
                  ElNotification({title: "Error", message: res.data, type: "error"})
                }
              })
              .catch((err) => {
                ElNotification({title: "Error", message: err.response.data.error, type: "error"})
              })
        } else {
          return false
        }
      })
    }

    return {
      form,
      fileList,
      rules,
      formAdd,
      activeIndex,
      handleUploadChange,
      handleSelect,
      onSubmit
    }
  }
})
</script>

<style>
header {
  border-bottom: solid 1px #e6e6e6;
}

body {
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.main-container {
  max-width: 1200px;
  margin: auto;
}

.cdate-picker.el-date-editor.el-input, .cdate-picker.el-date-editor.el-input__inner {
  width: 100%;
}

footer {
  border-top: solid 1px #e6e6e6;
  text-align: center;
  padding: 1.5em 1em;
}
</style>
