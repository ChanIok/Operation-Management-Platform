<template>
  <div id="personal-data">
    <el-card class="box-card" id="base-info">
      <template #header>
        <div class="card-header">
          <span>基本信息</span>
          <div class="edit-button" @click="edit.baseInfo = !edit.baseInfo">
            <i class="el-icon-edit-outline" v-if="!edit.baseInfo"></i>
            <i class="el-icon-finished" v-if="edit.baseInfo"></i>
          </div>
        </div>
      </template>
      <div>
        <el-descriptions title="" :column="columnSize" border>
          <el-descriptions-item label="用户ID">
            <span v-if="!edit.baseInfo">{{ info.id }}</span>
            <el-input
              v-model="info.id"
              :placeholder="info.id"
              v-if="edit.baseInfo"
            ></el-input>
          </el-descriptions-item>
          <el-descriptions-item label="姓名">
            <span v-if="!edit.baseInfo">{{ info.name }}</span>
            <el-input
              v-model="info.name"
              :placeholder="info.name"
              v-if="edit.baseInfo"
            ></el-input>
          </el-descriptions-item>
          <el-descriptions-item label="年龄">
            <span v-if="!edit.baseInfo">{{ info.age }}</span>
            <el-input
              v-model="info.age"
              :placeholder="info.age"
              v-if="edit.baseInfo"
            ></el-input
          ></el-descriptions-item>
          <el-descriptions-item label="性别">
            <span v-if="!edit.baseInfo">{{ info.sex }}</span>
            <el-input
              v-model="info.sex"
              :placeholder="info.sex"
              v-if="edit.baseInfo"
            ></el-input
          ></el-descriptions-item>
        </el-descriptions>
      </div>
    </el-card>
  </div>
</template>

<script>
import store from "../../../store";
export default {
  name: "PersonalData",
  data() {
    return {
      edit: {
        baseInfo: true,
      },
      columnSize: 1,
      info: {
        id: "2018080198207",
        name: "陈文轩",
        age: "21",
        sex: "男",
        position: "人事管理",
      },
    };
  },
  components: {},
  methods: {
    setColumnSize(width) {
      if (width < 720) {
        this.columnSize = 1;
      } else {
        this.columnSize = 2;
      }
    },
  },
  mounted() {
    this.setColumnSize(window.innerWidth);
  },
  computed: {
    getWidth() {
      return store.state.width;
    },
  },
  watch: {
    getWidth(width) {
      this.setColumnSize(width);
    },
  },
};
</script>

<style  lang="scss" scoped>
#personal-data {
  padding: 40px;
  @media screen and (max-width: 720px) {
    padding: 20px;
  }
  @media screen and (max-width: 360px) {
    padding: 10px;
  }
  .box-card {
    margin-bottom: 20px;
    .edit-button {
      float: right;
      font-size: 18px;
      cursor: pointer;
      &:hover {
        color: #409eff;
        transition-duration: 0.1s;
        transition-timing-function: ease-in-out;
      }
    }
  }
}
</style>