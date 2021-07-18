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
        <template #title>商品交易历史记录</template>
      </el-menu-item>
      <el-menu-item index="2">
        <i class="el-icon-document-checked"></i>
        <template #title>赠送体验商品历史记录</template>
      </el-menu-item>
      <el-menu-item index="3">
        <i class="el-icon-coordinate"></i>
        <template #title>收费商品管理</template>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-document"></i>
        <template #title>体验商品管理</template>
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
        1: "/index/employee-info/transactionHistory",
        2: "/index/employee-info/giftHistory",
        3: "/index/employee-info/merchandiseControler",
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