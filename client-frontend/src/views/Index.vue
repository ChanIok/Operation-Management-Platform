<template>
  <el-container id="container">
    <el-header id="header"> <lay-out-header></lay-out-header> </el-header>
    <el-container>
      <el-aside
        id="sider"
        :width="asideWidth"
        :class="{ asideFloat: isAsideFloatActive }"
      >
        <lay-out-menu></lay-out-menu>
      </el-aside>
      <div
        id="mask"
        v-if="isAsideFloatActive && !asideIsCollapse"
        @click="hideAside"
      ></div>
      <el-main id="main"> <router-view></router-view></el-main>
    </el-container>
  </el-container>
</template>

<script lang='ts'>
import LayOutMenu from "../components/main/LayOutMenu.vue";
import LayOutHeader from "../components/main/LayOutHeader.vue";
import LayOutMain from "../components/main/LayOutMain.vue";
import store from "../store";
export default {
  name: "Index",
  data() {
    return {
      asideIsCollapse: false,
      asideWidth: "250px",
      resizeRun: true,
      isAsideFloatActive: false,
    };
  },
  components: { LayOutMenu, LayOutHeader, LayOutMain },
  methods: {
    resizeEvent() {
      if (this.resizeRun) {
        store.commit("setWidth", window.innerWidth);
        if (window.innerWidth < 1080) {
          this.isAsideFloatActive = true;
          this.asideWidth = "0";
          store.commit("setAsideIsCollapse", true);
        } else {
          this.isAsideFloatActive = false;
          store.commit("setAsideIsCollapse", false);
        }

        this.resizeRun = false;
        setTimeout(() => {
          this.resizeRun = true;
        }, 100);
      }
    },
    hideAside() {
      store.commit("setAsideIsCollapse", true);
    },
  },
  mounted() {
    window.addEventListener("resize", this.resizeEvent);
    this.resizeEvent();
  },
  computed: {
    getAsideIsCollapse() {
      return store.state.asideIsCollapse;
    },
  },
  watch: {
    getAsideIsCollapse(newEvent) {
      this.asideIsCollapse = newEvent;
      if (this.asideIsCollapse) {
        this.asideWidth = "0";
      } else {
        if (this.isAsideFloatActive) {
        }
        this.asideWidth = "250px";
      }
    },
  },
  unmounted() {
    window.removeEventListener("resize", this.resizeEvent);
  },
};
</script>

<style  lang="scss" scoped>
#container {
  width: 100%;
  height: 100%;

  #header {
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);

    z-index: 999;
  }
  #sider {
    height: 100%;
    @media screen and (min-width: 1080px) {
      display: none;

      overflow-x: hidden;
      overflow-y: auto;
      height: calc(100vh - 60px);
      transition-duration: 0.25s;
      transition-timing-function: linear;
    }

    z-index: 998;
  }
  #mask {
    z-index: 997;
    background-color: rgba(192, 192, 192, 0.5);
    width: 100%;
    height: 100%;
    position: absolute;
  }
  #main {
    padding: 0;
    height: calc(100vh - 60px);
    overflow: auto;
  }
  .asideFloat {
    position: absolute;
  }
  #main {
    background-color: rgb(250, 250, 250);
  }
}
</style>