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
    <div id="title"></div>
    <div id="menu-wrapper" v-if="isShow">
      <lay-out-menu></lay-out-menu>
    </div>
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
import LayOutMenu from "./LayOutMenu.vue";
import store from "../../store";
export default {
  name: "LayOutHeader",
  data() {
    return {
      logined: false,
      asideIsCollapse: false,
      isShow: false,
      isExitWrapper: false,
    };
  },

  components: { LayOutMenu },
  methods: {
    changeIsCollapse() {
      this.asideIsCollapse = !this.asideIsCollapse;
      store.commit("setAsideIsCollapse", this.asideIsCollapse);
    },
    clickUserButton() {
      if (!this.logined) {
        let currentPath = this.$route.path;
        console.log(currentPath);
        this.$router.push({
         path: "/login",
         query: { return: currentPath },
        });
      } else {
        this.isExitWrapper = !this.isExitWrapper;
      }
    },
    setShow(width) {
      if (width < 1080) {
        this.isShow = false;
      } else {
        this.isShow = true;
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
      localStorage.removeItem("token");
      this.isExitWrapper = false;
      this.$router.go(0);
    },
  },
  mounted() {
    this.setShow(window.innerWidth);
    this.checkLogin();
    document.addEventListener("click", (e) => {
      if (!document.getElementById("user-wrapper").contains(e.target))
        this.isExitWrapper = false;
    });
  },
  computed: {
    getAsideIsCollapse() {
      return store.state.asideIsCollapse;
    },
    getWidth() {
      return store.state.width;
    },
  },
  watch: {
    getAsideIsCollapse(newEvent) {
      this.asideIsCollapse = newEvent;
    },
    getWidth(width) {
      this.setShow(width);
    },
    $route() {
      this.checkLogin();
    },
  },
};
</script>

<style  lang="scss" scoped>
#lay-out-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  line-height: 55px;
  background-color: #fff;
  #aside-controller {
    cursor: pointer;
    font-size: 26px;
    &:hover {
      color: #409eff;
      transition-duration: 0.3s;
      transition-timing-function: ease-in-out;
    }
    @media screen and (min-width: 1080px) {
      display: none;
    }
  }
  #title {
    font-weight: 800;
    font-size: 20px;
    @media screen and (max-width: 380px) {
      display: none;
    }
  }
  #menu-wrapper {
    @media screen and (max-width: 1080px) {
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