<template>
  <div class="regist-form">
    <!-- 登录表单头部 -->
    <header class="regist-title">
      <div class="regist-title-word">注册</div>
    </header>
    <!-- 登录表单内容 -->
    <article>
      <el-form
        status-icon
        :model="RegistFormData"
        :rules="rules"
        label-position="top"
        label-width="80px"
        class="regist-form-body"
        ref="RegistFormData"
      >
        <el-form-item
          v-for="item in RegistForm"
          :key="item.title"
          :label="item.title"
          :prop="item.name"
          :class="item.name"
        >
          <el-input
            v-model="RegistFormData[item.name]"
            :maxlength="item.meta.max"
            :type="item.meta.type ? item.meta.type : ''"
          ></el-input>
        </el-form-item>

        <el-form-item prop="protocolCheck">
          <el-checkbox-group v-model="RegistFormData.protocolCheck">
            <el-checkbox> 我已经阅读并同意 </el-checkbox>
            <span @click="protocolVisible = true" class="protocol-check"
              >《相关协议》</span
            >
          </el-checkbox-group>
        </el-form-item>
      </el-form>

      <div class="auth-button">
        <el-button type="primary" @click="regist('RegistFormData')"
          >注册</el-button
        >
      </div>
      <div class="login-wapper">
        <span class="login-back" @click="changeEvent('login')">已有帐号？</span>
      </div>
    </article>
    <!-- 登录表单底部 -->
    <footer></footer>

    <!-- 协议对话框 -->
    <el-dialog title="协议" v-model="protocolVisible"> hello </el-dialog>
  </div>
</template>

<script  >
import store from "../../store";
import { _regist } from "../../api/auth/auth";
import { ElMessage } from "element-plus";
export default {
  name: "RegistForm",
  data() {
    let validateName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入用户名"));
      }
      callback();
    };
    let validatePass = (rule, value, callback) => {
      if (value === "") {
        return callback(new Error("请输入密码"));
      } else {
        if (this.RegistFormData.passwordCheck !== "") {
          this.$refs.RegistFormData.validateField("passwordCheck");
        }
        callback();
      }
    };
    let validatePassCheck = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请再次输入密码!!!"));
      } else if (value !== this.RegistFormData.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      protocolVisible: false,
      RegistFormData: {
        username: "",
        password: "",
        passwordCheck: "",
        protocolCheck: [],
      },
      RegistForm: [
        {
          title: "用户名",
          name: "username",
          value: "",
          meta: {
            max: 15,
          },
        },
        {
          title: "登录密码",
          name: "password",
          value: "",
          meta: {
            max: 30,
            type: "password",
          },
        },
        {
          title: "确认密码",
          name: "passwordCheck",
          value: "",
          meta: {
            max: 30,
            type: "password",
          },
        },
      ],
      rules: {
        username: [
          {
            validator: validateName,
            trigger: "blur",
          },
        ],
        password: [
          {
            validator: validatePass,
            trigger: "blur",
          },
        ],
        passwordCheck: [
          {
            validator: validatePassCheck,
            trigger: "blur",
          },
        ],
        protocolCheck: [
          {
            type: "array",
            required: true,
            message: "请同意用户协议",
            trigger: "change",
          },
        ],
      },
    };
  },
  methods: {
    regist(RegistFormData) {
      this.$refs[RegistFormData].validate((valid) => {
        if (valid) {
          console.log("submit!");
          let dataSubmit = {
            username: this.RegistFormData.username,
            password: this.RegistFormData.password,
          };

          _regist(dataSubmit)
            .then((res) => {
              if (res.code === 0) {
                ElMessage.success({
                  message: "恭喜你，注册成功",
                  type: "success",
                });
                store.commit("setEvent", "login");
              }
            })
            .catch((err) => {
              ElMessage.error("请求注册失败", err);
            });
        } else {
          console.log("error submit!!");
        }
      });
    },
    changeEvent(newEvent) {
      store.commit("setEvent", newEvent);
    },
  },
};
</script>

<style lang="scss" scoped>
.regist-form {
  .regist-title {
    .regist-title-word {
      font-size: 34px;
    }
  }
  article {
    padding-top: 30px;
    box-sizing: border-box;
    .regist-form-body {
      .username,
      .password {
        box-sizing: border-box;
        width: 100%;
      }
    }
    .protocol-check {
      color: #426ab3;
      display: inline-block;
      width: 100px;
      font-size: 14px;
    }
    .protocol-check:hover {
      color: #2e3049;
      cursor: pointer;
    }
    .auth-button {
      text-align: right;
      display: flex;

      padding-bottom: 10px;
      box-sizing: border-box;
      button {
        width: 100%;
      }
    }
    .login-wapper {
      font-size: 14px;
      color: rgba($color: gray, $alpha: 0.7);
      padding-top: 20px;

      text-align: right;
      .login-back {
        color: #426ab3;
      }
      .login-back:hover {
        color: #393b63;
        cursor: pointer;
      }
    }
  }
}
</style>