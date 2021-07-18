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

      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="参数规格" prop="name">
          <el-input v-model="ruleForm.name" style="width:200px;"></el-input>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style lang="scss" scoped>
#merchandiseControler {
  height: 100%;
  width: 100%;
}
</style>

<script>
export default {
  data() {
    return {
      value: [],
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
      ruleForm: {
        name: "",
      },
    };
  },
  methods: {
    handleChange(value) {
      console.log(value);
    },
  },
};
</script>