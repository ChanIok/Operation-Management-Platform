<template>
  <div class="auth">
    <!-- 背景图片 -->
    <div class="auth-img-wrapper">
      <div
        id="auth-img-background"
        :style="{ backgroundImage: 'url(' + auth_background + ')' }"
      ></div>

      <div class="auth-img-mask"></div>
    </div>
    <!-- 登录表 -->
    <div class="auth-card">
      <login-form
        class="login-form"
        v-if="currentState === 'login'"
      ></login-form>
      <regist-form
        class="regist-form"
        v-else-if="currentState === 'regist'"
      ></regist-form>
      <alter-form
        class="alter-form"
        v-else-if="currentState === 'alter'"
      ></alter-form>
    </div>
  </div>
</template>

<script lang="ts">
import LoginForm from "../components/auth/LoginForm.vue";
import RegistForm from "../components/auth/RegistForm.vue";
import AlterForm from "../components/auth/AlterForm.vue";
import auth_background from "../assets/images/auth/auth_background.jpg";
import store from "../store";

export default {
  components: { LoginForm, RegistForm, AlterForm },
  setup() {},
  data() {
    return {
      auth_background,
      currentState: "login",
    };
  },
  computed: {
    getEvent() {
      return store.state.event;
    },
  },
  watch: {
    getEvent(newEvent: string) {
      this.currentState = newEvent;
    },
  },
};
</script>

<style lang="scss">
.auth {
  position: absolute;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  .auth-img-wrapper {
    width: 100%;
    height: 100%;
    position: absolute;
    left: 0;
    top: 0;
    #auth-img-background {
      width: 100%;
      height: 100%;
      background-repeat: no-repeat;
      background-size: cover;
    }
    .auth-img-mask {
      position: absolute;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      background-color: rgba($color: #000000, $alpha: 0.05);
    }
  }
  .auth-card {
    width: 50%;
    max-width: 450px;
    min-width: 350px;
    position: absolute;
    display: flex;

    .login-form {
      width: 100%;
      top: calc(50% - 300px);
      background-color: #fff;
      padding: 60px;
      box-sizing: border-box;
      box-shadow: 0px 0px 10px 10px rgba(0, 0, 0, 0.05);
      border-radius: 5px;
    }
    .regist-form {
      width: 100%;
      top: calc(50% - 350px);
      background-color: #fff;
      padding: 60px;
      box-sizing: border-box;
      box-shadow: 0px 0px 10px 10px rgba(0, 0, 0, 0.05);
      border-radius: 5px;
    }
  }
}
</style>