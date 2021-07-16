<template>
  <div id="menu-wrapper" v-if="ifMenu">
    <el-menu
      :default-active="activeIndex"
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
      ifMenu: true,
      activeIndex: "1",
      menuMode: "horizontal",
      asideIsCollapse: false,
      isAdmin: true,
      isHR: true,
      isFinance: true,
      isStaff: true,

      mapper: {
        1: "/presentation",
        2: "/mall",
        3: "/trial",
        4: "/user",
        5: "/supports",
      },
    };
  },
  components: {},

  methods: {
    handleSelect(key) {
      if (this.mapper[key] !== undefined) {
        this.$router.push(this.mapper[key]);
        if (window.innerWidth < 1080) {
          store.commit("setAsideIsCollapse", true);
        }
      }
    },
    setMode(width) {
      if (width < 1080) {
        this.menuMode = "vertical";
      } else {
        this.menuMode = "horizontal";
      }
    },
    setIndexActive() {
      if (this.$route.path.indexOf("mall") !== -1) {
        this.ifMenu = false;
        this.$nextTick(() => {
          this.ifMenu = true;
          this.activeIndex = "2";
        });
      } else if (this.$route.path.indexOf("user") !== -1) {
        this.ifMenu = false;
        this.$nextTick(() => {
          this.ifMenu = true;
          this.activeIndex = "4";
        });
      } else if (this.$route.path.indexOf("supports") !== -1) {
        this.ifMenu = false;
        this.$nextTick(() => {
          this.ifMenu = true;
          this.activeIndex = "5";
        });
      }
    },
  },
  mounted() {
    this.setMode(window.innerWidth);
    this.setIndexActive();  
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
    $route() {
      this.setIndexActive();
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