<template>
  <div id="giftHistory">
    <el-table
      :data="tableData"
      stripe
      height="100%"
      border
      style="width: 100%; font-size: 18px"
      :row-style="{ height: '80px' }"
    >
      <el-table-column type="index" :index="indexMethod"> </el-table-column>
      <el-table-column prop="trade_tr_id" label="体验订单号" width="120">
      </el-table-column>
      <el-table-column prop="user_id" label="客户id" width="100">
      </el-table-column>
      <el-table-column prop="user_name" label="客户名"> </el-table-column>
      <el-table-column prop="produce_name" label="产品名称"> </el-table-column>
      <el-table-column prop="specification_name" label="规格">
      </el-table-column>
      <el-table-column prop="num" label="数量" width="100"> </el-table-column>
      <el-table-column prop="tramsaction_time" label="赠送时间" width="180">
      </el-table-column>
    </el-table>
  </div>
</template>

<style lang="scss" scoped>
#giftHistory {
  height: 100%;
  width: 100%;
}
</style>

<script>

export default {
  data() {
    return {
      tableData: [
        {
          trade_tr_id: "00000001",
          user_id: 1,
          user_name: "啊伟子",
          produce_name: "云服务器ECS",
          specification_name: "突发性能实例 t6",
          price: 200,
          num: 2,
          total_price: 400,
          tramsaction_time: "2021-01-01",
        },
        {
          trade_tr_id: "00000002",
          user_id: 1,
          user_name: "啊伟子",
          produce_name: "云服务器ECS",
          specification_name: "突发性能实例 t6",
          price: 200,
          num: 2,
          total_price: 400,
          tramsaction_time: "2021-01-01",
        },
        {
          trade_tr_id: "00000003",
          user_id: 1,
          user_name: "啊伟子",
          produce_name: "云服务器ECS",
          specification_name: "突发性能实例 t6",
          price: 200,
          num: 2,
          total_price: 400,
          tramsaction_time: "2021-01-01",
        },
        {
          trade_tr_id: "00000004",
          user_id: 1,
          user_name: "啊伟子",
          produce_name: "云服务器ECS",
          specification_name: "突发性能实例 t5",
          price: 200,
          num: 2,
          total_price: 400,
          tramsaction_time: "2021-01-01",
        },
        {
          trade_tr_id: "00000005",
          user_id: 1,
          user_name: "啊伟子",
          produce_name: "云服务器ECS",
          specification_name: "共享型实例s6",
          price: 500,
          num: 1,
          total_price: 500,
          tramsaction_time: "2021-01-01",
        },
        {
          trade_tr_id: "00000006",
          user_id: 1,
          user_name: "啊伟子",
          produce_name: "云服务器ECS",
          specification_name: "共享型实例s6",
          price: 500,
          num: 1,
          total_price: 500,
          tramsaction_time: "2021-01-01",
        },
        {
          trade_tr_id: "00000007",
          user_id: 1,
          user_name: "啊伟子",
          produce_name: "云服务器ECS",
          specification_name: "共享型实例s6",
          price: 500,
          num: 1,
          total_price: 500,
          tramsaction_time: "2021-01-01",
        },
        {
          trade_tr_id: "00000008",
          user_id: 1,
          user_name: "啊伟子",
          produce_name: "云服务器ECS",
          specification_name: "共享型实例s6",
          price: 500,
          num: 1,
          total_price: 500,
          tramsaction_time: "2021-01-01",
        },
      ],
    };
  },
  methods: {
    indexMethod(index) {
      return index + 1;
    },
  },
};
</script>