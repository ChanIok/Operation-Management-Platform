<template>
  <div class="login-form">
    <!-- 登录表单头部 -->
    <header class="login-title">
      <div class="login-title-word">登录</div>
    </header>
    <!-- 登录表单内容 -->
    <article>
      <el-form
        status-icon
        :model="LoginFormData"
        :rules="rules"
        label-position="top"
        label-width="80px"
        class="login-form-body"
        ref="LoginFormData"
      >
        <el-form-item
          v-for="item in LoginForm"
          :key="item.title"
          :label="item.title"
          :prop="item.name"
          :class="item.name"
        >
          <el-input
            v-model="LoginFormData[item.name]"
            :maxlength="item.meta.max"
            :type="item.meta.type ? item.meta.type : ''"
          ></el-input>
        </el-form-item>
      </el-form>
      <div class="retrieve-wapper">
        <span class="retrieve-password" @click="changeEvent('alter')"
          >忘记密码？</span
        >
      </div>
      <div class="auth-button">
        <el-button type="primary" @click="login('LoginFormData')"
          >登录</el-button
        >
        <el-button type="warning" @click="changeEvent('regist')"
          >注册</el-button
        >
      </div>
    </article>
    <!-- 登录表单底部 -->
    <footer></footer>
  </div>
</template>

<script  >
import store from "../../store";
import { _login } from "../../api/auth/auth";
export default {
  name: "LoginForm",
  data() {
    let validatorName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入用户名"));
      }
      callback();
    };
    let validatorPass = (rule, value, callback) => {
      if (value === "") {
        return callback(new Error("请输入密码"));
      }
      callback();
    };
    return {
      LoginFormData: {
        username: "",
        password: "",
      },
      LoginForm: [
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
      ],
      rules: {
        username: [
          {
            validator: validatorName,
            trigger: "blur",
          },
        ],
        password: [
          {
            validator: validatorPass,
            trigger: "blur",
          },
        ],
      },
      ready: false,
    };
  },
  methods: {
    login(LoginFormData) {
      // 待拿到api
      // this.$refs[LoginFormData].validate((valid) => {
      //   if (valid) {
      //     console.log("submit!");
      //     _login(this.LoginFormData).then((res) => {
      //       if (res.msg.token && res.code === 0) {
      //         localStorage.setItem("token", res.msg.token);
      //         localStorage.setItem("userID", res.msg.userID);
      //         console.log("save token!");
      //         this.$router.push('/index')
      //       }
      //     });
      //   } else {
      //     console.log("error submit!!");
      //   }
      // });
      this.$router.push("/");
    },
    changeEvent(newEvent) {
      if (newEvent === "alter") {
        return;
      }
      store.commit("setEvent", newEvent);
    },
  },
};
</script>

<style lang="scss" scoped>
.login-form {
  .login-title {
    .login-title-word {
      font-size: 34px;
    }
  }
  article {
    padding-top: 30px;
    box-sizing: border-box;
    .login-form-body {
      .username,
      .password {
        box-sizing: border-box;
        width: 100%;
      }
    }
    .retrieve-wapper {
      font-size: 14px;
      color: rgba($color: gray, $alpha: 0.7);
      padding-top: 10px;
      padding-bottom: 10px;
      .retrieve-password {
        color: #426ab3;
      }
      .retrieve-password:hover {
        color: #393b63;
        cursor: pointer;
      }
    }
    .login-ready {
      span {
        color: #426ab3;
      }
      span:hover {
        color: #6a6da9;
        cursor: pointer;
      }
    }
    .auth-button {
      text-align: right;
      display: flex;
      padding-top: 10px;
      box-sizing: border-box;
      button {
        width: 100%;
      }
    }
  }
}
</style>