<template>
  <div id="personal-data">
    <div id="header">
      <div id="title">员工资料</div>
      <el-button @click="enterEdit" id="editButton">编辑</el-button>
    </div>
    <el-card class="box-card" id="base-info">
      <template #header>
        <div class="card-header">
          <span>基本信息</span>
        </div>
      </template>
      <div>
        <el-descriptions title="" :column="columnSize" border>
          <el-descriptions-item label="用户ID">
            <span>{{ info.id }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="姓名">
            <span>{{ info.name }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="年龄">
            <span>{{ info.age }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="性别">
            <span>{{ info.sex }}</span>
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-card>

    <el-card class="box-card" id="position-info">
      <template #header>
        <div class="card-header">
          <span>职位信息</span>
        </div>
      </template>
      <div>
        <el-descriptions title="" :column="columnSize" border>
          <el-descriptions-item label="职位">
            <span>{{ info.position }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="入职年份">
            <span>{{ info.induction_year }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="所属单位">
            <span>{{ info.unit }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="所属院系">
            <span>{{ info.faculty }}</span>
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-card>

    <el-card class="box-card" id="other-info">
      <template #header>
        <div class="card-header">
          <span>其它信息</span>
        </div>
      </template>
      <div>
        <el-descriptions title="" :column="columnSize" border>
          <el-descriptions-item label="联系电话">
            <span>{{ info.phone }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="政治面貌">
            <span>{{ info.political_status }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="学历">
            <span>{{ info.education_background }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="毕业院校">
            <span>{{ info.graduate_institutions }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="家庭住址">
            <span>{{ info.adderss }}</span>
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-card>
  </div>

  <div id="dialog-wrapper">
    <el-dialog
      title="修改信息"
      v-model="infoEdit"
      :width="dialogWidth"
      :fullscreen="dialogFull"
    >
      <el-form :model="info" :label-position="labelPosition">
        <el-form-item label="用户ID" :label-width="formLabelWidth">
          <el-input v-model="info.id" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="info.name" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="年龄" :label-width="formLabelWidth">
          <el-select v-model="info.age" placeholder="请选择年龄">
            <el-option
              :label="i + 17"
              :value="i + 17"
              v-for="i in 63"
              :key="i + 17"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-select v-model="info.sex" placeholder="请选择性别">
            <el-option label="男" value="m"></el-option>
            <el-option label="女" value="f"></el-option>
            <el-option label="未知" value="n"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="职位" :label-width="formLabelWidth">
          <el-select v-model="info.position" placeholder="请选择性别">
            <el-option
              :label="item.position_name"
              :value="item.position_id"
              v-for="(item, index) in table.position"
              :key="index"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所属单位" :label-width="formLabelWidth">
          <el-select v-model="info.unit" placeholder="请选择性别">
            <el-option
              :label="item.unit_name"
              :value="item.unit_id"
              v-for="(item, index) in table.unit"
              :key="index"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所属院系" :label-width="formLabelWidth">
          <el-select v-model="info.faculty" placeholder="请选择性别">
            <el-option
              :label="item.faculty_name"
              :value="item.faculty_id"
              v-for="(item, index) in table.faculty"
              :key="index"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="入职年份" :label-width="formLabelWidth">
          <el-select v-model="info.age" placeholder="请选择年龄">
            <el-option
              :label="i + 1969"
              :value="i + 1967"
              v-for="i in 53"
              :key="i + 1969"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手机" :label-width="formLabelWidth">
          <el-input v-model="info.phone"></el-input>
        </el-form-item>
      </el-form>

      <div id="dialog-footer">
        <div>
          <el-button @click="cancelEdit">取 消</el-button>
        </div>
        <div>
          <el-button type="primary" @click="infoEdit = false">确 定</el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import store from "../../../store";
export default {
  name: "PersonalData",
  data() {
    return {
      infoEdit: false,
      columnSize: 1,
      dialogWidth: "60%",
      dialogFull: false,
      labelPosition: "right",
      formLabelWidth: "80px",
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
      table: {
        unit: [
          { unit_id: 1, unit_name: "教务处" },
          { unit_id: 2, unit_name: "财务部" },
          { unit_id: 3, unit_name: "人事部" },
          { unit_id: 4, unit_name: "招生办" },
        ],
        faculty: [
          { faculty_id: 1, faculty_name: "计算机学校" },
          { faculty_id: 2, faculty_name: "电信学院" },
          { faculty_id: 3, faculty_name: "外国语学院" },
        ],
        position: [
          { position_id: 1, position_name: "校长" },
          { position_id: 2, position_name: "院长" },
          { position_id: 3, position_name: "教授" },
          { position_id: 3, position_name: "副教授" },
          { position_id: 4, position_name: "讲师" },
        ],
      },
      infoBackup: {},
    };
  },
  components: {},
  methods: {
    setColumnSize(width) {
      if (width < 720) {
        this.columnSize = 1;
        this.labelPosition = "top";
        this.dialogFull = true;
      } else {
        this.columnSize = 2;
        this.labelPosition = "right";
        this.dialogFull = false;
      }
    },
    enterEdit() {
      this.infoEdit = true;
      this.infoBackup = JSON.parse(JSON.stringify(this.info));
    },
    cancelEdit() {
      this.infoEdit = false;
      this.info = this.infoBackup;
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
  #header {
    margin-bottom: 20px;
    line-height: 40px;
    font-size: 26px;
    display: flex;
    justify-content: space-between;
  }
  clear: both;
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

#dialog-wrapper {
  #dialog-footer {
    display: flex;
    justify-content: flex-end;
    div {
      margin-left: 10px;
    }
  }
}
</style>