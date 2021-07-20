<template>
  <div id="menu-wrapper">
    <el-menu
      default-active="1"
      :collapse="asideIsCollapse"
      @select="handleSelect"
      id="menu"
    >
      <el-menu-item index="1">
        <i class="el-icon-user"></i>
        <template #title>交易流水管理</template>
      </el-menu-item>
      <el-menu-item index="2">
        <i class="el-icon-document-checked"></i>
        <template #title>体验记录管理</template>
      </el-menu-item>

      <el-submenu index="3">
        <template #title>
          <i class="el-icon-coordinate"></i
          ><span v-if="!asideIsCollapse">业务商品管理</span>
        </template>
        <el-menu-item index="3-1">商品审查</el-menu-item>
        <el-menu-item index="3-2">商品管理</el-menu-item>
        <el-menu-item index="3-3">配置审查</el-menu-item>
        <el-menu-item index="3-4">配置管理</el-menu-item>
      </el-submenu>

      <el-menu-item index="4">
        <i class="el-icon-document"></i>
        <template #title>平台用户管理</template>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import store from "../../store";
export default {
  name: "LayOutAside",
  data() {
    return {
      asideIsCollapse: false,
      isAdmin: true,
      isHR: true,
      isFinance: true,
      isStaff: true,
      mapper: {
        1: "/index/trade-history",
        2: "/index/trial-history",
        3: "/index/merchandise-controler",
        "3-1": "/index/products/list",
        "3-2": "/index/products/manage",
        "3-3": "/index/products/specification/list",
        "3-4": "/index/products/specification/manage",
        4: "/index/user-management",
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
#menu-wrapper {
  height: 100%;

  #menu {
    border: 0;
    height: 100%;
  }
}
</style>