<template>
  <div id="settlement">
    <div id="head">
      <el-page-header
        icon="el-icon-arrow-left"
        content="确认订单"
      ></el-page-header>
    </div>
    <div id="body">
      <el-table
        :data="tableData"
        stripe
        height="100%"
        border
        style="width: 100%; font-size: 18px"
        :row-style="{ height: '80px' }"
      >
        <el-table-column type="index" :index="indexMethod"> </el-table-column>
        <el-table-column prop="produce_name" label="产品名称">
        </el-table-column>
        <el-table-column prop="specification_name" label="规格">
        </el-table-column>
        <el-table-column prop="price" label="单价" width="180">
        </el-table-column>
        <el-table-column prop="num" label="数量" width="180"> </el-table-column>
        <el-table-column prop="total_price" label="总价" width="180">
        </el-table-column>
      </el-table>
    </div>
    <div id="foot">
      <div id="sum">共{{ count }}件， 合计：￥{{ priceSum }}</div>
      <el-button type="success" round id="pay_button">确认支付</el-button>
    </div>
  </div>
  <router-view />
</template>
<script>
import banner from "../../../assets/images/modules/mall/banner.jpg";
export default {
  data() {
    return {
      banner,
      tableData: [
        {
          produce_name: "云服务器ECS",
          specification_name: "突发性能实例 t6",
          price: 200,
          num: 2,
          total_price: 400,
        },
        {
          produce_name: "云服务器ECS",
          specification_name: "突发性能实例 t6",
          price: 200,
          num: 2,
          total_price: 400,
        },
        {
          produce_name: "云服务器ECS",
          specification_name: "突发性能实例 t6",
          price: 200,
          num: 2,
          total_price: 400,
        },
        {
          produce_name: "云服务器ECS",
          specification_name: "突发性能实例 t5",
          price: 200,
          num: 2,
          total_price: 400,
        },
        {
          produce_name: "云服务器ECS",
          specification_name: "共享型实例s6",
          price: 500,
          num: 1,
          total_price: 500,
        },
        {
          produce_name: "云服务器ECS",
          specification_name: "共享型实例s6",
          price: 500,
          num: 1,
          total_price: 500,
        },
        {
          produce_name: "云服务器ECS",
          specification_name: "共享型实例s6",
          price: 500,
          num: 1,
          total_price: 500,
        },
      ],
      count: 7,
      priceSum: 2300,
    };
  },
  methods: {
    goBack() {
      console.log("go back");
    },
    indexMethod(index) {
      return index + 1;
    },
  },
};
</script>
<style  lang="scss" scoped>
.my_margin {
  margin: 0 auto;
}
#settlement {
  height: 100%;
  #head {
    background-color: cadetblue;
    display: flex;
    color: white;
    .el-icon-arrow-left {
      width: 50px;
      height: 50px;
      font-size: 50px;
      margin: 8px;
    }
    div {
      line-height: 70px;
      font-size: 26px;
    }
  }
  #body {
    margin: 0 auto;
    width: 90%;
    height: 76%;
  }
  #foot {
    width: 100%;
    height: 14%;
    background-color: cadetblue;
    white-space: pre;
    display: flex;
    #sum {
      line-height: 100px;
      font-size: 24px;
      margin-left: 950px;
      color: white;
    }
    #pay_button {
      height: 50%;
      margin: auto;
      font-size: 20px;
    }
  }
}
</style>
 