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
    <div id="menu-wrapper">
      <lay-out-menu></lay-out-menu>
    </div>
    <div id="user-wrapper">
      <i class="el-icon-user-solid"></i>
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
      asideIsCollapse: false,
    };
  },

  components: { LayOutMenu },
  methods: {
    changeIsCollapse() {
      this.asideIsCollapse = !this.asideIsCollapse;
      store.commit("setAsideIsCollapse", this.asideIsCollapse);
    },
  },
  mounted() {},
  computed: {
    getAsideIsCollapse() {
      return store.state.asideIsCollapse;
    },
  },
  watch: {
    getAsideIsCollapse(newEvent) {
      this.asideIsCollapse = newEvent;
    },
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
    &:hover {
      color: #409eff;
      transition-duration: 0.2s;
      transition-timing-function: ease-in-out;
    }
  }
}
</style>