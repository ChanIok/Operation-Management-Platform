<template>
  <div id="merchandiseControler">
    <div class="block">
      <span class="demonstration">选择商品分类：</span>
      <el-cascader
        placeholder="也可以打字搜索哦"
        :options="options"
        @change="handleChange"
        filterable
      ></el-cascader>
      <div id="margin"></div>
      <el-form
        :model="dataForm"
        ref="dataForm"
        label-width="110px"
        class="demo-ruleForm"
      >
        <el-form-item label="商品名称：" prop="name">
          <el-input
            type="textarea"
            v-model="dataForm.name"
            class="introduction"
          ></el-input>
        </el-form-item>

        <el-form-item label="商品简介：" prop="introduction">
          <el-input
            type="textarea"
            v-model="dataForm.introduction"
            class="introduction"
          ></el-input>
        </el-form-item>

        <el-form-item
          label="单价   (元/年)："
          prop="price"
          class="price"
          :rules="[
            { required: true, message: '单价不能为空' },
            { type: 'number', message: '单价必须为数字值' },
          ]"
        >
          <el-input
            type="price"
            v-model.number="dataForm.price"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm()">提交</el-button>
          <el-button @click="resetForm('dataForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style lang="scss" scoped>
#merchandiseControler {
  height: 100%;
  width: 100%;
  #margin {
    width: 100%;
    height: 30px;
  }
  .price {
    width: 300px;
  }
  .introduction {
    width: 300px;
  }
}
</style>

<script>
export default {
  data() {
    return {
      dataForm: {
        product_id: 1,
        specification_id: 1,
        price: "",
        introduction: "",
        name: "",
      },

      props: { expandTrigger: "hover" },
      options: [
        {
          value: "1",
          label: "弹性计算",
          children: [
            {
              value: "1",
              label: "云服务器 ECS",
            },
            {
              value: "2",
              label: "弹性裸金属服务器",
            },
            {
              value: "3",
              label: "GPU 云服务器",
            },
          ],
        },
        {
          value: "2",
          label: "存储",
          children: [
            {
              value: "1",
              label: "块存储 EBS",
            },
            {
              value: "2",
              label: "对象存储 OSS",
            },
            {
              value: "3",
              label: "文件存储 NAS",
            },
          ],
        },
        {
          value: "3",
          label: "数据库",
          children: [
            {
              value: "1",
              label: "云数据库 RDS MySQL 版",
            },
            {
              value: "2",
              label: "云数据库 Redis 版",
            },
          ],
        },
      ],
    };
  },
  methods: {
    handleChange(value) {
      console.log(value);
      this.dataForm.product_id = value[0];
      this.dataForm.specifiction_id = value[1];
    },
    submitForm() {
      console.log(this.dataForm);
    },
  },
  computed: {
    classification() {
      let classification = value;
      return classification;
    },
  },
};
</script>