<template>
  <div id="product-retrieve">
    <div id="table-wrapper">
      <el-table
        :data="products"
        stripe
        height="100%"
        border
        style="width: 100%; font-size: 16px"
        :row-style="{ height: '80px' }"
      >
        <el-table-column prop="product_id" label="产品ID" width="100">
        </el-table-column>
        <el-table-column prop="product_name" label="产品名称" width="150">
        </el-table-column>
        <el-table-column prop="product_type" label="产品类型" width="100">
        </el-table-column>
        <el-table-column prop="introduction" label="简介"> </el-table-column>
        <el-table-column prop="img_url" label="图片路径"> </el-table-column>
      </el-table>
    </div>
    <div id="index-wrapper">
      <div class="index">
        <i class="el-icon-caret-left page" @click="page_up()"></i>
        <span class="page">{{ pageIndex }}</span>
        <i class="el-icon-caret-right page" @click="page_down()"></i>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
#product-retrieve {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  #table-wrapper {
    width: 100%;
    height: calc(100% - 65px);
    overflow: auto;
    .page {
      width: 80px;
      height: 80px;
      font-size: 50px;
      text-align: center;
      line-height: 80px;
    }
  }
  #index-wrapper {
    flex-grow: 1;
    flex-shrink: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    .index {
      font-size: 36px;
    }
    .page {
      margin: 0 10px 0 10px;
    }
    i {
      cursor: pointer;
      &:hover {
        color: #409eff;
        transition-duration: 0.2s;
        transition-timing-function: ease-in-out;
      }
    }
  }
}
</style>

<script>
import { _getProducts } from "../../../api/product/product";
export default {
  data() {
    return {
      products: [],
      tableData: [
        {
          trade_ex_id: "",
          user_id: 1,
          user_name: "",
          produce_name: "",
          specification_name: "",
          price: "",
          num: "",
          total_price: "",
          experience_time: "",
        },
      ],
      pageIndex: 1,
    };
  },
  methods: {
    page_up() {
      if (this.pageIndex > 1) {
        this.pageIndex--;
        this.getProducts();
      }
    },
    page_down() {
      this.pageIndex++;
      this.getProducts();
    },
    getProducts() {
      console.log(this.limit);
      _getProducts(this.limit)
        .then((res) => {
          if (res.code === 0) {
            this.products = res.data.products;
          } else {
            console.log(res.data.message);
          }
        })
        .catch((err) => {
          console.log("获取交易历史失败！");
        });
    },
  },
  mounted() {
    this.getProducts();
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