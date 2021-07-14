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
        <el-table-column prop="produce_name" label="产品名称">
        </el-table-column>
        <el-table-column prop="specification_name" label="规格">
        </el-table-column>
        <el-table-column prop="price" label="单价" >
        </el-table-column>
        <el-table-column prop="num" label="数量"> </el-table-column>
        <el-table-column prop="total_price" label="总价">
        </el-table-column>
      </el-table>
    </div>
    <div id="footer">
      <div id="sum">
        <span class="counts">共 {{ count }} 件，</span> 合计：
        <span class="price">￥ {{ priceSum }} </span>
      </div>
      <div id="pay-wrapper">
        <el-button type="primary" id="pay-button">确认支付</el-button>
      </div>
    </div>
  </div>
  <router-view />
</template>
<script>
export default {
  data() {
    return {
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
   
      ],
      count: 7,
      priceSum: 2300,
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
      padding:  0;
    }
    .el-table{
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
 