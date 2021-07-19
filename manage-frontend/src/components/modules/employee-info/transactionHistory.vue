<template>
  <div id="transactionHistory">
    <el-table
      :data="tableData"
      stripe
      height="100%"
      border
      style="width: 100%; font-size: 18px"
      :row-style="{ height: '80px' }"
    >
      <el-table-column prop="trade_tr_id" label="交易订单号" width="120">
      </el-table-column>
      <el-table-column prop="user_id" label="客户id" width="100">
      </el-table-column>
      <el-table-column prop="username" label="客户名"> </el-table-column>
      <el-table-column prop="product_name" label="产品名称" width="180">
      </el-table-column>
      <el-table-column prop="specification_name" label="规格" width="230">
      </el-table-column>
      <el-table-column prop="price" label="单价" width="100"> </el-table-column>
      <el-table-column prop="number" label="数量" width="100">
      </el-table-column>
      <el-table-column prop="total_price" label="实收款" width="100">
      </el-table-column>
      <el-table-column prop="transaction_time" label="交易时间" width="230">
      </el-table-column>
    </el-table>
    <div class="index">
      <i class="el-icon-caret-left page" @click="page_up()"></i>
      <span class="page">{{ pageIndex }}</span>
      <i class="el-icon-caret-right page" @click="page_down()"></i>
    </div>
  </div>
</template>

<style lang="scss" scoped>
#transactionHistory {
  height: 100%;
  width: 100%;
  background-color: aquamarine;
  .page {
    width: 80px;
    height: 80px;
    font-size: 50px;
    text-align: center;
    line-height: 80px;
  }
  .index {
    width: 250px;
    height: 80px;
    margin: 0 auto;
  }
}
</style>

<script>
import { _getTrHistory } from "../../../api/history/trHistory.ts";
export default {
  data() {
    return {
      tableData: [
        {
          trade_tr_id: "",
          user_id: "",
          username: "",
          product_name: "",
          specification_name: "",
          price: "",
          transaction_time: "",
          total_price: "",
          number: "",
        },
      ],
      pageIndex: 1,
    };
  },
  methods: {
    page_up() {
      if (this.pageIndex > 1) {
        this.pageIndex--;
        this.getTrHistory();
      }
    },
    page_down() {
      this.pageIndex++;
      this.getTrHistory();
    },
    getTrHistory() {
      console.log(this.limit);
      _getTrHistory(this.limit)
        .then((res) => {
          if (res.code === 0) {
            console.log(res.data.trHistory);
            this.tableData = res.data.trHistory;
          } else {
            console.log(res.data.trHistory);
          }
        })
        .catch((err) => {
          console.log("获取交易历史失败！");
        });
    },
  },
  mounted() {
    this.getTrHistory();
  },
  computed: {
    limit() {
      let limit = {
        page_num_start: (this.pageIndex - 1) * 10,
        page_num_end: (this.pageIndex - 1) * 10 + 9,
      };
      return limit;
    },
  },
};
</script>