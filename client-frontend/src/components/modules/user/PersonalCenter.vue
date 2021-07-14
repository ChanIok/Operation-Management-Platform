<template>
  <div id="personal-data">
    <div id="header">
      <div id="title">个人信息</div>

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
            <span>{{ info.user_id }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="姓名">
            <span>{{ info.username }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="权限">
            <span>{{ info.permission }}</span>
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-card>

    <el-card class="box-card" id="other-info">
      <template #header>
        <div class="card-header">
          <span>联系方式</span>
        </div>
      </template>
      <div>
        <el-descriptions title="" :column="columnSize" border>
          <el-descriptions-item label="联系电话">
            <span>{{ info.phone_num }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="邮箱">
            <span>{{ info.email }}</span>
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
          <el-input v-model="info.phone_num"></el-input>
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
import { ElMessage } from "element-plus";
import { _getUserInfo } from "../../../api/user/user";
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
        user_id: "2018080198207",
        username: "陈文轩",
        permission: "9",
        email: "998@qq.com",
        phone_num: "13700000000",
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
    getUserInfo() {
      if (localStorage.getItem("token") === null) {
        this.$router.push({
          path: "/login",
          query: { return: "user" },
        });
        return;
      }
      _getUserInfo()
        .then((res) => {
          if (res.code === 0) {
            console.log("获取个人信息成功！");
            this.info = res.data.userInfo;
          } else {
            ElMessage.error("获取个人信息失败！");
          }
        })
        .catch((err) => {
          ElMessage.error("获取个人信息失败！");
        });
    },

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
    this.getUserInfo();
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