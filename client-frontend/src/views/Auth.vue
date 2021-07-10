<template>
  <div class="auth">
    <!-- 背景图片 -->
    <div class="auth-img-wrapper">
      <div
        id="auth-img-background"
        :style="{ backgroundImage: 'url(' + auth_background + ')' }"
      ></div>
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
    <div id="content">
      <div id="title">移动云</div>
      <div id="second-title">5G时代，你身边的智慧云</div>
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
  // position: absolute;
  width: 100%;
  height: 100%;
  .auth-img-wrapper {
    width: 100%;
    height: 100%;
    left: 0;
    bottom: 0;
    #auth-img-background {
      width: 100%;
      height: 100%;
      background-repeat: no-repeat;
      background-size: cover;
      background-position: 50% 50%;
    }
  }
  .auth-card {
    width: 450px;
    top: calc(50% - 250px);
    right: 15%;
    position: absolute;
    display: flex;
    box-sizing: border-box;
    @media screen and(max-width:1080px) {
      right: calc(50% - 275px);
    }
    @media screen and(max-width:720px ) {
      width: 100%;
      height: 100%;
      max-width: 100%;
      top: 0;
      right: 0;
      
    }
    @media screen and(max-height:720px ) {
      width: 100%;
      height: 100%;
      max-width: 100%;
      top: 0;
      right: 0;
     
    }
    .login-form {
      width: 100%;
      background-color: #fff;
      padding: 60px;
      box-sizing: border-box;
      box-shadow: 0px 0px 10px 10px rgba(0, 0, 0, 0.05);
      border-radius: 5px;
      overflow: auto;
       @media screen and(max-width:720px) {
         padding-top: 80px;
      }
    }
    .regist-form {
      @extend .login-form;
      @media screen and(min-width:720px) {
        position: relative;
        top: -50px;
      }
    }
  }
  #content {
    position: absolute;
    top: 50px;
    left: 20px;
    font-size: 28px;
    @media screen and(max-width:1080px) {
      display: none;
    }
    @media screen and(max-height:720px) {
      display: none;
    }
    #title {
      font-weight: 800;
      font-size: 52px;
      padding-bottom: 10px;
      @media screen and (max-width: 720px) {
        font-size: 28px;
      }
    }
    padding-top: 100px;
    padding-left: 100px;
    @media screen and (max-width: 720px) {
      padding-top: 50px;
      padding-left: 40px;
      padding-right: 20px;
    }
  }
}
</style>