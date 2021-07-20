<template>
  <div id="lay-out-header">
    <div id="aside-controller">
      <i
        class="el-icon-s-fold"
        v-if="!asideIsCollapse"
        @click="changeIsCollapse"
      ></i>
      <i
        class="el-icon-s-unfold"
        v-if="asideIsCollapse"
        @click="changeIsCollapse"
      ></i>
    </div>
    <div id="title">云业务综合运营管理平台</div>
    <div id="user-wrapper" @click="clickUserButton" ref="userWrapper">
      <span id="user">
        <span v-if="!logined"> 登录 </span>
      </span>
      <i class="el-icon-user-solid"></i>
      <div id="exit-wrapper" v-if="isExitWrapper" @click="exitLogin">退出</div>
    </div>
  </div>
</template>

<script>
import store from "../../store";

export default {
  name: "LayOutHeader",
  data() {
    return {
      logined: false,
      isExitWrapper: false,
      asideIsCollapse: false,
    };
  },

  components: {},
  methods: {
    changeIsCollapse() {
      this.asideIsCollapse = !this.asideIsCollapse;
      store.commit("setAsideIsCollapse", this.asideIsCollapse);
    },
    clickUserButton() {
      if (!this.logined) {
        this.$router.push({
          path: "/",
        });
      } else {
        this.isExitWrapper = !this.isExitWrapper;
      }
    },
    checkLogin() {
      if (localStorage.getItem("token") !== null) {
        this.logined = true;
      } else {
        this.logined = false;
      }
    },
    exitLogin() {
      localStorage.clear();
      this.isExitWrapper = false;
      this.$router.push({
        path: "/",
      });
    },
    handleClick(e) {
      if (!document.getElementById("user-wrapper").contains(e.target))
        this.isExitWrapper = false;
    },
  },
  mounted() {
    this.checkLogin();
    document.addEventListener("click", this.handleClick);
  },
  computed: {
    getAsideIsCollapse() {
      return store.state.asideIsCollapse;
    },
  },
  watch: {
    getAsideIsCollapse(newEvent) {
      this.asideIsCollapse = newEvent;
    },
    $route() {
      this.checkLogin();
    },
  },
  unmounted() {
    document.removeEventListener("click", this.handleClick);
  },
};
</script>

<style  lang="scss" scoped>
#lay-out-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  line-height: 60px;

  #aside-controller {
    cursor: pointer;
    font-size: 26px;
    &:hover {
      color: #409eff;
      transition-duration: 0.3s;
      transition-timing-function: ease-in-out;
    }
  }
  #title {
    font-weight: 800;
    font-size: 20px;
    @media screen and (max-width: 380px) {
      display: none;
    }
  }
  #user-wrapper {
    font-size: 26px;
    cursor: pointer;
    position: relative;
    #user {
      position: relative;
      span {
        font-size: 14px;
        display: inline-block;
        width: 30px;
        right: 0;
        position: absolute;
        @media screen and (max-width: 720px) {
          display: none;
        }
        &:hover {
          color: #409eff;
          transition-duration: 0.2s;
          transition-timing-function: ease-in-out;
        }
      }
    }
    i {
      &:hover {
        color: #409eff;
        transition-duration: 0.2s;
        transition-timing-function: ease-in-out;
      }
    }

    #exit-wrapper {
      position: absolute;
      top: 55px;
      right: -20px;
      width: 80px;
      font-size: 18px;
      background-color: #fff;
      text-align: center;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
      &:hover {
        color: #409eff;
        transition-duration: 0.2s;
        transition-timing-function: ease-in-out;
      }
    }
  }
}
</style>