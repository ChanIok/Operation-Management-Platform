<template>
  <div id="presentation">
    <div id="container"></div>
    <div id="banner" :style="{ backgroundImage: 'url(' + banner + ')' }">
      <div id="content">
        <div id="title">移动云</div>
        <div id="second-title">5G时代，你身边的智慧云</div>
      </div>
    </div>
    <div id="container-main">
      <div id="main-title">丰富、安全、稳定的产品及服务</div>
      <div id="main-check">
        <router-link to="/mall">查看全部云产品 <i>></i></router-link>
      </div>
      <div id="main-product-container">
        <div class="product" @click="clickProduct(1)">
          <i class="el-icon-s-grid"></i>
          <div class="product-descript">弹性计算</div>
        </div>
        <div class="product" @click="clickProduct(2)">
          <i class="el-icon-s-data"></i>
          <div class="product-descript">存储</div>
        </div>
        <div class="product" @click="clickProduct(3)">
          <i class="el-icon-s-management"></i>
          <div class="product-descript">数据库</div>
        </div>
        <div class="product" @click="clickProduct(4)">
          <i class="el-icon-s-claim"></i>
          <div class="product-descript">安全</div>
        </div>
        <div class="product" @click="clickProduct(5)">
          <i class="el-icon-s-marketing"></i>
          <div class="product-descript">大数据</div>
        </div>
        <div class="product" @click="clickProduct(6)">
          <i class="el-icon-share"></i>
          <div class="product-descript">人工智能</div>
        </div>
      </div>
    </div>
    <div id="footer">
      <div class="clicked">
        <span> 服务条款 </span>
        <span> 服务协议 </span>
        <span> 隐私政策 </span>
        <span> 营业执照</span>
        <span> 廉政举报 </span>
      </div>
      <div id="record">Copyright ©2008-2021 云移动 All Rights Reserved.</div>
    </div>
  </div>
</template>

<script>
import banner from "../../../assets/images/modules/presentation/banner-2.png";
export default {
  name: "Presentation",
  data() {
    return {
      banner,
    };
  },
  methods: {
    clickProduct(index) {
      this.$router.push(`/mall/${index}`);
    },
  },
  components: {},

  mounted() {},
};
</script>

<style  lang="scss" scoped>
#presentation {
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
  #container-main {
    padding: 40px;
    #main-title {
      font-size: 38px;
      text-align: center;
    }
    #main-check {
      margin-top: 50px;
      text-align: center;
      cursor: pointer;
      a {
        text-decoration: none;
        color: #2c3e50;
        &:hover {
          color: #409eff;
          transition-duration: 0.2s;
          transition-timing-function: ease-in-out;
        }
      }

      i {
        text-align: right;
        width: 20px;
        font-weight: 800;
        display: inline-block;
        transition-duration: 0.2s;
        transition-timing-function: ease-in-out;
      }

      &:hover i {
        width: 40px;
      }
    }
    #main-product-container {
      width: 80%;
      margin: auto;
      margin-top: 50px;
      display: flex;
      flex-flow: wrap;
      justify-content: space-around;
      @media screen and (max-width: 720px) {
        width: 90%;
      }
      .product {
        width: 16.6%;
        height: 120px;
        text-align: center;
        cursor: pointer;
        font-size: 72px;

        &:hover {
          color: #88c3ff;
          transition-duration: 0.2s;
          transition-timing-function: ease-in-out;
        }
        &:hover i {
          color: #409eff;
          transition-duration: 0.2s;
          transition-timing-function: ease-in-out;
          font-size: 80px;
        }
        @media screen and (max-width: 720px) {
          width: 33.3%;
        }
        .product-descript {
          font-size: 16px;
        }
      }
    }
  }
  #footer {
    font-size: 15px;
    margin-top: 10px;
    text-align: center;
    line-height: 30px;
    @media screen and (min-height: 830px) {
      position: absolute;
      bottom: 10px;
      width: 380px;
      left: calc(50% - 190px);
    }
    span {
      cursor: pointer;
      &:hover {
        color: #409eff;
        transition-duration: 0.2s;
        transition-timing-function: ease-in-out;
      }
    }
    #record {
      font-size: 13px;
      color: rgba(65, 65, 65, 0.8);
    }
  }
}
</style>