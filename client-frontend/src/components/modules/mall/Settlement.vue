<template>
  <div id="settlement">
    <div id="header">
      <el-page-header
        icon="el-icon-arrow-left"
        content="确认订单"
        title="返回"
        @back="goBack"
      ></el-page-header>
    </div>
    <div id="container">
      <el-table
        :data="tableData"
        stripe
        height="calc(100% - 60px)"
        style="width: 100%; font-size: 16px"
        :row-style="{ height: '80px' }"
      >
        <el-table-column type="index" :index="indexMethod"> </el-table-column>
        <el-table-column prop="product_name" label="产品名称">
        </el-table-column>
        <el-table-column prop="specification_name" label="规格">
        </el-table-column>
        <el-table-column prop="price" label="单价"> </el-table-column>
        <el-table-column prop="buy_count" label="数量"> </el-table-column>
        <el-table-column prop="total_price" label="总价"> </el-table-column>
      </el-table>
    </div>
    <div id="footer">
      <div id="sum">
        <span class="counts">共 {{ count }} 件，</span> 合计：
        <span class="price">￥ {{ priceSum }} </span>
      </div>
      <div id="pay-wrapper">
        <el-button type="primary" id="pay-button" @click="doSettlement"
          >确认支付</el-button
        >
      </div>
    </div>
  </div>
  <router-view />
</template>
<script>
import { _getSettlement, _getShoppingCart } from "../../../api/mall/mall";
export default {
  data() {
    return {
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
    goBack() {
      console.log("go back");
      this.$router.push(this.$route.query.return);
    },
    indexMethod(index) {
      return index + 1;
    },
    getTrolley() {
      _getShoppingCart()
        .then((res) => {
          if (res.code === 0) {
            console.log(res.data.message);
            this.tableData = res.data.trolley;
            for (let item of this.tableData) {
              item.total_price = item.price * item.buy_count;
            }
          } else {
            console.log(res.data.message);
          }
        })
        .catch((err) => {
          console.log("获取购物车失败！");
        });
    },
    doSettlement() {
      this.$router.push({
        path: "/payment-result",
      });
    },
  },
  mounted() {
    this.getTrolley();
  },
  computed: {
    count() {
      return this.tableData.length;
    },

    priceSum() {
      let sum = 0;
      for (let item of this.tableData) {
        sum += item.total_price;
      }
      return sum;
    },
  },
};
</script>
<style  lang="scss" scoped>
#settlement {
  height: 100%;
  #header {
    background-color: rgb(250, 250, 250);

    padding-left: 20px;
    border-bottom: 1px solid rgb(235, 235, 235);
    box-sizing: border-box;

    div {
      line-height: 60px;
      font-size: 32px !important;
      color: #000;
    }
  }
  #container {
    background-color: rgb(250, 250, 250);
    margin: 0 auto;
    padding: 0 10% 0 10%;
    height: calc(100% - 80px);
    overflow: auto;

    @media screen and (max-width: 1080px) {
      width: 100%;
      padding: 0;
    }
    .el-table {
      box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.03);
    }
  }
  #footer {
    position: fixed;
    bottom: 0;
    width: 100%;
    height: 80px;
    z-index: 996;
    line-height: 80px;
    background-color: rgb(255, 255, 255);
    display: flex;
    justify-content: flex-end;
    align-items: center;
    box-shadow: 0 -2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
    #sum {
      font-size: 18px;
      .counts {
        @media screen and (max-width: 480px) {
          display: none;
        }
      }
      .price {
        color: #ff6a00;
        font-size: 24px;
      }
    }
    #pay-wrapper {
      padding: 0 20px 0 20px;
      #pay-button {
        height: 50%;
        margin: auto;
        font-size: 18px;
      }
    }
  }
}
</style>
 