<template>
  <div id="mall">
    <div id="banner" :style="{ backgroundImage: 'url(' + banner + ')' }">
      <div id="content">
        <div id="title">业务中心</div>
        <div id="second-title">云与企业深度融合，创造数字化未来</div>
      </div>
    </div>
    <div class="big-title">了解所有产品和业务</div>
    <div id="container-main">
      <el-affix target="#container-main" :offset="80" id="side-menu-wrapper">
        <div id="side-menu">
          <div class="menu-item" @click="goAnchor(1)">
            <i class="el-icon-s-grid"></i>
            <div class="product-descript">弹性计算</div>
          </div>
          <div class="menu-item" @click="goAnchor(2)">
            <i class="el-icon-s-data"></i>
            <div class="product-descript">存储</div>
          </div>
          <div class="menu-item" @click="goAnchor(3)">
            <i class="el-icon-s-management"></i>
            <div class="product-descript">数据库</div>
          </div>
          <div class="menu-item" @click="goAnchor(4)">
            <i class="el-icon-s-claim"></i>
            <div class="product-descript">安全</div>
          </div>
          <div class="menu-item" @click="goAnchor(5)">
            <i class="el-icon-s-marketing"></i>
            <div class="product-descript">大数据</div>
          </div>
          <div class="menu-item" @click="goAnchor(6)">
            <i class="el-icon-share"></i>
            <div class="product-descript">人工智能</div>
          </div>
        </div>
      </el-affix>

      <div id="product-list-wrapper">
        <div
          class="product-list-group"
          v-for="(item, index) in products"
          :key="index"
        >
          <div class="anchor" :id="`item${index + 1}`"></div>
          <div class="title-type">{{ item.type }}</div>
          <div
            class="product-list-item"
            v-for="(pItem, pIndex) in item.list"
            :key="pIndex"
            @click="clickProduct(pItem.product_id)"
          >
            <el-card class="box-card">
              <template #header>
                <div class="card-header">
                  <span>{{ pItem.product_name }}</span>
                </div>
              </template>
              <div class="card-content">
                {{ pItem.introduction }}
              </div>
            </el-card>
          </div>
        </div>
      </div>
    </div>
  </div>
  <router-view />
</template>
<script>
import { ElMessage } from "element-plus";
import { _getProducts } from "../../../api/mall/mall";
import banner from "../../../assets/images/modules/mall/banner.jpg";
export default {
  data() {
    return {
      banner,
      products: [],
    };
  },

  methods: {
    goAnchor(index) {
      let anchor = document.getElementById(`item${index}`);
      if (anchor !== null) anchor.scrollIntoView();
    },
    clickProduct(index) {
      this.$router.push(`/product/${index}`);
    },
    getProducts() {
      _getProducts()
        .then((res) => {
          if (res.code === 0) {
            console.log("获取所有产品成功！");
            this.products = res.data.products;
            this.$nextTick(() => {
              this.goAnchor(this.$route.params.id);
            });
          } else {
            ElMessage.error("获取所有产品失败！");
          }
        })
        .catch((err) => {
          ElMessage.error("获取所有产品失败！");
        });
    },
  },
  mounted() {
    this.getProducts();
  },
};
</script>
<style  lang="scss" scoped>
#mall {
  height: 100%;
  #banner {
    height: 350px;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: 50% 50%;
    @media screen and (max-width: 720px) {
      height: 200px;
    }
    #content {
      #title {
        font-weight: 800;
        font-size: 38px;
        padding-bottom: 10px;
        @media screen and (max-width: 720px) {
          font-size: 28px;
        }
      }
      padding-top: 100px;
      padding-left: 100px;
      @media screen and (max-width: 720px) {
        padding-top: 50px;
        padding-left: 40px;
        padding-right: 20px;
      }
    }
    #second-title {
      line-height: 30px;
    }
  }
  .big-title {
    padding: 40px 15% 20px 15%;
    font-size: 32px;
    margin-bottom: 20px;
    @media screen and(max-width:720px) {
      padding: 40px 20px 40px 40px;
    }
  }
  #container-main {
    padding: 0px 15% 40px 15%;
    box-sizing: border-box;
    display: flex;
    @media screen and(max-width:720px) {
      padding: 0px 20px 40px 20px;
    }
    #side-menu-wrapper {
      width: 300px;
      flex-grow: 0;
      flex-shrink: 0;
      height: 360px;
      @media screen and(max-width:1080px) {
        width: 150px;
      }
      @media screen and(max-width:720px) {
        width: 50px;
      }
      #side-menu {
        width: 100%;
        .menu-item {
          height: 60px;
          background-color: rgb(250, 250, 250);
          display: flex;
          align-items: center;
          cursor: pointer;
          @media screen and(max-width:720px) {
            justify-content: center;
          }

          i {
            font-size: 32px;
            transition-duration: 0.2s;
            transition-timing-function: ease-in-out;
          }
          .product-descript {
            padding-left: 20px;
            transition-duration: 0.2s;
            transition-timing-function: ease-in-out;
            @media screen and(max-width:720px) {
              display: none;
            }
          }
          &:hover {
            color: #409eff;
          }
          &:hover .product-descript {
            padding-left: 28px;
          }
        }
      }
    }
    #product-list-wrapper {
      flex-grow: 1;
      .product-list-group {
        display: flex;
        flex-wrap: wrap;
        margin-top: 20px;
        margin-bottom: 40px;
        .anchor {
          position: relative;
          top: -30px;
        }
        .title-type {
          width: 100%;
          height: 30px;
          line-height: 30px;
          font-size: 30px;

          margin-left: 15px;
          margin-bottom: 15px;
        }
        .product-list-item {
          padding: 10px;
          cursor: pointer;
          box-sizing: border-box;
          width: 50%;
          &:hover .card-header {
            color: #409eff;
            transition-duration: 0.2s;
            transition-timing-function: ease-in-out;
          }
          @media screen and(max-width:1080px) {
            width: 100%;
          }
          .card-header {
            font-weight: bold;
          }
          .card-content {
            font-size: 15px;
            color: #555555;
            line-height: 23px;
          }
        }
      }
    }
  }
}
</style>
 