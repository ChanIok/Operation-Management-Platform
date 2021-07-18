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
    <div id="user-wrapper">
      <i class="el-icon-user-solid"></i>
    </div>
  </div>
</template>

<script>
import store from "../../store";
export default {
  name: "LayOutHeader",
  data() {
    return { asideIsCollapse: false };
  },

  components: {},
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
    &:hover {
      color: #409eff;
      transition-duration: 0.2s;
      transition-timing-function: ease-in-out;
    }
  }
}
</style>