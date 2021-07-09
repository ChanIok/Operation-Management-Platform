<template>
  <div id="personal-data">
    <el-card class="box-card" id="base-info">
      <template #header>
        <div class="card-header">
          <span>基本信息</span>
          <div class="edit-button" @click="editCard('baseInfo')">
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

    <el-card class="box-card" id="position-info">
      <template #header>
        <div class="card-header">
          <span>职位信息</span>
          <div class="edit-button" @click="editCard('positionInfo')">
            <i class="el-icon-edit-outline" v-if="!edit.positionInfo"></i>
            <i class="el-icon-finished" v-if="edit.positionInfo"></i>
          </div>
        </div>
      </template>
      <div>
        <el-descriptions title="" :column="columnSize" border>
          <el-descriptions-item label="职位">
            <span v-if="!edit.positionInfo">{{ info.position }}</span>
            <el-input
              v-model="info.position"
              :placeholder="info.position"
              v-if="edit.positionInfo"
            ></el-input>
          </el-descriptions-item>
          <el-descriptions-item label="所属院系">
            <span v-if="!edit.positionInfo">{{ info.faculty }}</span>
            <el-input
              v-model="info.faculty"
              :placeholder="info.faculty"
              v-if="edit.positionInfo"
            ></el-input>
          </el-descriptions-item>
          <el-descriptions-item label="所属单位">
            <span v-if="!edit.positionInfo">{{ info.unit }}</span>
            <el-input
              v-model="info.unit"
              :placeholder="info.unit"
              v-if="edit.positionInfo"
            ></el-input
          ></el-descriptions-item>
          <el-descriptions-item label="入职年份">
            <span v-if="!edit.positionInfo">{{ info.induction_year }}</span>
            <el-input
              v-model="info.induction_year"
              :placeholder="info.induction_year"
              v-if="edit.positionInfo"
            ></el-input
          ></el-descriptions-item>
        </el-descriptions>
      </div>
    </el-card>

    <el-card class="box-card" id="other-info">
      <template #header>
        <div class="card-header">
          <span>其它信息</span>
          <div class="edit-button" @click="editCard('otherInfo')">
            <i class="el-icon-edit-outline" v-if="!edit.otherInfo"></i>
            <i class="el-icon-finished" v-if="edit.otherInfo"></i>
          </div>
        </div>
      </template>
      <div>
        <el-descriptions title="" :column="columnSize" border>
          <el-descriptions-item label="联系电话">
            <span v-if="!edit.otherInfo">{{ info.phone }}</span>
            <el-input
              v-model="info.phone"
              :placeholder="info.phone"
              v-if="edit.otherInfo"
            ></el-input>
          </el-descriptions-item>
          <el-descriptions-item label="家庭住址">
            <span v-if="!edit.otherInfo">{{ info.adderss }}</span>
            <el-input
              v-model="info.adderss"
              :placeholder="info.adderss"
              v-if="edit.otherInfo"
            ></el-input>
          </el-descriptions-item>
          <el-descriptions-item label="学历">
            <span v-if="!edit.otherInfo">{{ info.education_background }}</span>
            <el-input
              v-model="info.education_background"
              :placeholder="info.education_background"
              v-if="edit.otherInfo"
            ></el-input
          ></el-descriptions-item>
          <el-descriptions-item label="毕业院校">
            <span v-if="!edit.otherInfo">{{ info.graduate_institutions }}</span>
            <el-input
              v-model="info.graduate_institutions"
              :placeholder="info.graduate_institutions"
              v-if="edit.otherInfo"
            ></el-input
          ></el-descriptions-item>
          <el-descriptions-item label="政治面貌">
            <span v-if="!edit.otherInfo">{{ info.political_status }}</span>
            <el-input
              v-model="info.political_status"
              :placeholder="info.political_status"
              v-if="edit.otherInfo"
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
        positionInfo: true,
        otherInfo: true,
      },
      columnSize: 1,
      info: {
        id: "2018080198207",
        name: "陈文轩",
        age: "21",
        sex: "男",
        position: "人事管理",
        faculty: "计算机学院",
        unit: "教务处",
        induction_year: "2018",
        phone: "13700000000",
        adderss: "广东佛山",
        political_status: "群众",
        education_background: "本科",
        graduate_institutions: "中山学院",
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
    editCard(index) {
      this.edit[index] = !this.edit[index];
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