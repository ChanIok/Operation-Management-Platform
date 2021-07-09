<template>
  <el-container id="container">
    <el-header id="header"> <lay-out-header></lay-out-header> </el-header>
    <el-container>
      <el-aside
        id="sider"
        :width="asideWidth"
        :class="{ asideFloat: isAsideFloatActive }"
      >
        <lay-out-aside></lay-out-aside>
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
import LayOutAside from "../components/main/LayOutAside.vue";
import LayOutHeader from "../components/main/LayOutHeader.vue";
import LayOutMain from "../components/main/LayOutMain.vue";
import store from "../store";
export default {
  name: "Main",
  data() {
    return {
      asideIsCollapse: false,
      asideWidth: "250px",
      resizeRun: true,
      isAsideFloatActive: false,
    };
  },
  components: { LayOutAside, LayOutHeader, LayOutMain },
  methods: {
    resizeEvent() {
      if (this.resizeRun) {
        store.commit("setWidth", window.innerWidth);
        if (window.innerWidth < 720) {
          this.isAsideFloatActive = true;
          this.asideWidth = "0";
        } else {
          this.isAsideFloatActive = false;
        }
        if (window.innerWidth < 1080) {
          store.commit("setAsideIsCollapse", true);
        } else {
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
        if (this.isAsideFloatActive) {
          this.asideWidth = "0";
        } else {
          this.asideWidth = "64px";
        }
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
    overflow-x: hidden;
    overflow-y: auto;
    height: calc(100vh - 60px);

    @media screen and (min-width: 720px) {
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