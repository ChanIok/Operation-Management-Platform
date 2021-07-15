<template>
  <div id="result-wrapper">
    <div class="success" v-if="result && !waitting">
      <el-result icon="success" title="成功提示" :subTitle="message">
        <template #extra>
          <el-button type="primary" size="medium" @click="goPage(0)"
            >查看个人产品</el-button
          >
        </template>
      </el-result>
    </div>
    <div class="err" v-if="!result && !waitting">
      <el-result icon="error" title="错误提示" :subTitle="message">
        <template #extra>
          <el-button type="primary" size="medium" @click="goPage(1)"
            >返回业务超市</el-button
          >
        </template>
      </el-result>
    </div>
  </div>
  <router-view />
</template>
<script>
import { _getSettlement } from "../../../api/mall/mall";
export default {
  data() {
    return {
      result: true,
      waitting: true,
      message: "",
      tableData: [
        {
          product_name: "",
          specification_name: "",
          price: 0,
          buy_count: 0,
          total_price: 0,
        },
      ],
    };
  },
  methods: {
    goPage(res) {
      if (!res) {
        this.$router.push({
          path: "/user",
          query: { anchor: "products" },
        });
      } else {
        this.$router.push({
          path: "/mall",
        });
      }
    },
    openFullScreen() {
      const loading = this.$loading({
        lock: true,
        text: "支付确认中",
        spinner: "el-icon-loading",
        background: "rgba(255, 255, 255, 0.5)",
        customClass: "pay-loading",
      });
      this.doSettlement(loading);
    },
    doSettlement(loading) {
      _getSettlement()
        .then((res) => {
          this.message = res.data.message;
          if (res.code === 0) {
            this.result = true;
          } else {
            this.result = false;
          }
        })
        .catch((err) => {
           this.result = false;
          console.log("结算失败！");
        })
        .finally(() => {
          setTimeout(() => {
            loading.close();
            this.waitting = false;
          }, 1000);
        });
    },
  },
  mounted() {
    this.openFullScreen();
  },
  computed: {},
};
</script>
<style lang="scss">
.pay-loading {
  font-size: 64px;
}
</style>
<style  lang="scss" scoped>
#result-wrapper {
   height: 100%;
   display: flex;
   align-items: center;
   justify-content:center;
}
</style>
 