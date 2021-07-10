<template>
  <div id="menu-wrapper">
    <el-menu
      default-active="1"
      :collapse="asideIsCollapse"
      @select="handleSelect"
      :mode="menuMode"
      id="menu"
    >
      <el-menu-item index="1">
        <template #title>首页</template>
      </el-menu-item>
      <el-menu-item index="2">
        <i class="el-icon-s-shop"></i>
        <template #title>业务超市</template>
      </el-menu-item>
      <el-menu-item index="3">
        <i class="el-icon-s-home"></i>
        <template #title>体验中心</template>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-s-custom"></i>
        <template #title>我的产品</template>
      </el-menu-item>
      <el-menu-item index="5">
        <i class="el-icon-phone"></i>
        <template #title>帮助支持</template>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import store from "../../store";
export default {
  name: "LayOutMenu",
  data() {
    return {
      menuMode: "horizontal",
      asideIsCollapse: false,
      isAdmin: true,
      isHR: true,
      isFinance: true,
      isStaff: true,
      mapper: {
        1: "/index/presentation",
        2: "/index/employee-info/personal-data",
        5:"/index/supports",
      },
    };
  },
  components: {},

  methods: {
    handleSelect(key, keyPath) {
      if (this.mapper[key] !== undefined) {
        this.$router.push(this.mapper[key]);
      }
    },
    setMode(width) {
      if (width < 1080) {
        this.menuMode = "vertical";
      } else {
        this.menuMode = "horizontal";
      }
    },
  },
  mounted() {
    this.setMode(window.innerWidth);
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
      this.setMode(width);
    },
  },
};
</script>

<style  lang="scss" scoped>
#menu-wrapper {
  height: 100%;

  #menu {
    border: 0;
    height: 100%;
  }
}
</style>