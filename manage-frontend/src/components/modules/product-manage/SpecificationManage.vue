<template>
  <div id="product-edit-wrapper">
    <div id="container">
      <div class="card-wrapper">
        <el-card shadow="hover" @click="dialogCreateVisible = true">
          <i class="el-icon-plus"></i>
          <div class="card-content">添加配置</div>
        </el-card>
      </div>
      <div class="card-wrapper">
        <el-card shadow="hover" @click="dialogEditVisible = true">
          <i class="el-icon-edit"></i>
          <div class="card-content">修改配置</div>
        </el-card>
      </div>
      <div class="card-wrapper">
        <el-card shadow="hover" @click="dialogDeleteVisible = true">
          <i class="el-icon-delete"></i>
          <div class="card-content">删除配置</div>
        </el-card>
      </div>
    </div>
  </div>
  <div id="dialog-wrapper">
    <el-dialog
      title="修改配置"
      v-model="dialogEditVisible"
      :width="dialogWidth"
      :fullscreen="dialogFull"
    >
      <el-form :model="dataForm" ref="dataForm" label-width="110px">
        <el-form-item label="产品ID：" prop="product_id">
          <el-input type="textarea" v-model="dataForm.product_id"></el-input>
        </el-form-item>

        <el-form-item label="配置ID：" prop="specification_id">
          <el-input
            type="textarea"
            v-model="dataForm.specification_id"
          ></el-input>
        </el-form-item>

        <el-form-item label="配置名称：" prop="specification_name">
          <el-input
            type="textarea"
            v-model="dataForm.specification_name"
          ></el-input>
        </el-form-item>

        <el-form-item label="配置类型：" prop="specification_type">
          <el-input
            type="textarea"
            v-model="dataForm.specification_type"
          ></el-input>
        </el-form-item>

        <el-form-item label="配置价格：" prop="price">
          <el-input type="textarea" v-model="dataForm.price"></el-input>
        </el-form-item>

        <el-form-item label="配置描述：" prop="description">
          <el-input type="textarea" v-model="dataForm.description"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="doEdit">提交</el-button>
          <el-button @click="resetForm('dataForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
      title="增加配置"
      v-model="dialogCreateVisible"
      :width="dialogWidth"
      :fullscreen="dialogFull"
    >
      <el-form :model="dataForm" ref="dataForm" label-width="110px">
        <el-form-item label="产品ID：" prop="product_id">
          <el-input type="textarea" v-model="dataForm.product_id"></el-input>
        </el-form-item>

        <el-form-item label="配置ID：" prop="specification_id">
          <el-input
            type="textarea"
            v-model="dataForm.specification_id"
          ></el-input>
        </el-form-item>

        <el-form-item label="配置名称：" prop="specification_name">
          <el-input
            type="textarea"
            v-model="dataForm.specification_name"
          ></el-input>
        </el-form-item>

        <el-form-item label="配置类型：" prop="specification_type">
          <el-input
            type="textarea"
            v-model="dataForm.specification_type"
          ></el-input>
        </el-form-item>

        <el-form-item label="配置价格：" prop="price">
          <el-input type="textarea" v-model="dataForm.price"></el-input>
        </el-form-item>

        <el-form-item label="配置描述：" prop="description">
          <el-input type="textarea" v-model="dataForm.description"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="doCreate">提交</el-button>
          <el-button @click="resetForm('dataForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
      title="删除配置"
      v-model="dialogDeleteVisible"
      :width="dialogWidth"
      :fullscreen="dialogFull"
    >
      <el-form :model="dataForm" ref="dataForm" label-width="110px">
        <el-form-item label="产品ID：" prop="product_id">
          <el-input type="textarea" v-model="dataForm.product_id"></el-input>
        </el-form-item>

        <el-form-item label="配置ID：" prop="specification_id">
          <el-input
            type="textarea"
            v-model="dataForm.specification_id"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="doDelete">提交</el-button>
          <el-button @click="resetForm('dataForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
#product-edit-wrapper {
  height: 100%;
  width: 100%;
  box-sizing: border-box;
  padding: 40px;
  #container {
    display: flex;
    flex-wrap: wrap;
    .card-wrapper {
      padding: 10px;
      box-sizing: border-box;
      cursor: pointer;
      flex-basis: 33%;
      @media screen and (max-width: 1080px) {
        flex-basis: 100%;
      }
      i {
        font-size: 72px;
      }
      .card-content {
        text-align: right;
        font-size: 24px;
        margin-top: 10px;
      }
    }
  }
}
</style>

<script>
import {
  _updateSpecification,
  _createSpecification,
  _deleteSpecification,
} from "../../../api/product/specification.ts";
import store from "../../../store";
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      dialogEditVisible: false,
      dialogCreateVisible: false,
      dialogDeleteVisible: false,
      dialogWidth: "60%",
      dialogFull: false,
      dataForm: {
        product_id: "",
        specification_id: "",
        specification_name: "",
        specification_type: "",
        price: "",
        description: "",
      },
      props: { expandTrigger: "hover" },
    };
  },
  methods: {
    handleInt() {
      this.dataForm.product_id = parseInt(this.dataForm.product_id);
      this.dataForm.specification_id = parseInt(this.dataForm.specification_id);
      this.dataForm.price = parseInt(this.dataForm.price);
    },
    doEdit() {
      this.handleInt();
      _updateSpecification(this.dataForm)
        .then((res) => {
          if (res.code === 0) {
            ElMessage.success({
              message: res.data.message,
              offset: 60,
              type: "success",
            });
            this.dialogEditVisible=false;
          } else {
            ElMessage.error({
              message: res.data.message,
              offset: 60,
              type: "error",
            });
          }
        })
        .catch((err) => {
          ElMessage.error({
            message: res.data.message,
            offset: 60,
            type: "更新产品失败！",
          });
        });
    },

    doCreate() {
      this.handleInt();
      _createSpecification(this.dataForm)
        .then((res) => {
          if (res.code === 0) {
            ElMessage.success({
              message: res.data.message,
              offset: 60,
              type: "success",
            });
            this.dialogCreateVisible=false;
          } else {
            ElMessage.error({
              message: res.data.message,
              offset: 60,
              type: "error",
            });
          }
        })
        .catch((err) => {
          ElMessage.error({
            message: res.data.message,
            offset: 60,
            type: "添加产品失败！",
          });
        });
    },

    doDelete() {
      this.handleInt();
     _deleteSpecification(this.dataForm)
        .then((res) => {
          if (res.code === 0) {
            ElMessage.success({
              message: res.data.message,
              offset: 60,
              type: "success",
            });
            this.dialogDeleteVisible=false;
          } else {
            ElMessage.error({
              message: res.data.message,
              offset: 60,
              type: "error",
            });
          }
        })
        .catch((err) => {
          ElMessage.error({
            message: res.data.message,
            offset: 60,
            type: "删除产品失败！",
          });
        });
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    submitForm() {
      console.log(this.dataForm);
    },
    setDialogSize(width) {
      console.log(width);
      if (width < 720) {
        this.dialogFull = true;
      } else {
        this.dialogFull = false;
      }
    },
  },
  mounted() {},
  computed: {
    getWidth() {
      return store.state.width;
    },
  },
  watch: {
    getWidth(width) {
      this.setDialogSize(width);
    },
  },
};
</script>