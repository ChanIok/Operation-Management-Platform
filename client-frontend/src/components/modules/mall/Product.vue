<template>
  <div id="product-wrapper" style="width: 100%; height: 800px">
    <trolley
      
      :productInCart="productInCart"
    ></trolley>

    <div
      id="banner"
      :style="{ backgroundImage: 'url(' + details.info.img_url + ')' }"
    >
      <div id="content">
        <div id="title">{{ details.info.product_name }}</div>
        <div id="second-title">{{ details.info.introduction }}</div>
        <div id="banner-button">
          <button class="button" @click="goAnchor('#par')">
            <span>立即购买</span>
          </button>
        </div>
      </div>
    </div>

    <div id="container-main">
      <el-affix target="#container-main" :offset="80" id="side-menu-wrapper">
        <div id="side-menu">
          <div class="menu-item" @click="goAnchor('#par')">
            <div class="product-descript">产品规格</div>
          </div>
          <div class="menu-item" @click="goAnchor('#adv')">
            <div class="product-descript">产品优势</div>
          </div>
          <div class="menu-item" @click="goAnchor('#apply')">
            <div class="product-descript">应用场景</div>
          </div>
          <div class="menu-item" @click="goAnchor('#docs')">
            <div class="product-descript">技术文档</div>
          </div>
        </div>
      </el-affix>

      <div id="details-wrapper">
        <div class="specification details-item">
          <div class="anchor" id="par"></div>
          <div class="title">产品规格</div>

          <div class="list-wrapper">
            <div
              class="list-item"
              v-for="(item, index) in details.specification"
              :key="index"
            >
              <el-card class="box-card" shadow="hover">
                <template #header>
                  <div class="card-header">
                    <span>{{ item.name }}</span>
                  </div>
                </template>
                <div class="card-content">
                  {{ item.description }}
                  <el-divider style="margin: 15px 0 15px 0"></el-divider>
                  <div class="price">
                    <span>￥{{ item.price }}</span> / 年
                  </div>
                  <div class="card-button-wrapper">
                    <el-button type="primary" @click="addToCart(item)"
                      >加入购物车</el-button
                    >
                  </div>
                </div>
              </el-card>
            </div>
          </div>
        </div>

        <div class="advantage details-item">
          <div class="anchor" id="adv"></div>
          <div class="title">产品优势</div>
          <div class="list-wrapper">
            <div
              class="list-item"
              v-for="(item, index) in details.advantage"
              :key="index"
            >
              <div class="adv-img-wrapper">
                <img :src="item.img_url" class="adv-img" />
              </div>
              <div class="title">
                {{ item.title }}
              </div>
              <div class="content">
                {{ item.content }}
              </div>
            </div>
          </div>
        </div>

        <div class="application details-item">
          <div class="anchor" id="apply"></div>
          <div class="title">应用场景</div>
          <div class="application-wrapper">
            <div class="application-menu">
              <div
                :class="{
                  menuItem: true,
                  active: applicationIndex == indexMenu,
                }"
                v-for="(itemMenu, indexMenu) in details.application"
                :key="indexMenu"
                @click="clickApplicationMenu(indexMenu)"
              >
                {{ itemMenu.title }}
              </div>
            </div>
            <div class="application-body">
              <div class="application-img">
                <img :src="applicationInfo.img_url" alt="..." />
              </div>
              <div class="application-main">
                <div class="title">
                  {{ applicationInfo.title }}
                </div>
                <div class="content">
                  {{ applicationInfo.content }}
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="document details-item">
          <div class="anchor" id="docs"></div>
          <div class="title">技术文档</div>
          <div class="list-wrapper">
            <div class="list-item">
              <div class="card">
                <div class="title">快速入门</div>
                <div class="content">快速使用并搭建</div>
              </div>
            </div>
            <div class="list-item">
              <div class="card">
                <div class="title">产品文档</div>
                <div class="content">查看产品所有文档</div>
              </div>
            </div>
            <div class="list-item">
              <div class="card">
                <div class="title">常见问题</div>
                <div class="content">常见使用问题</div>
              </div>
            </div>
            <div class="list-item">
              <div class="card">
                <div class="title">操作指南</div>
                <div class="content">详细的应用搭建操作指导</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import trolley from "./Trolley.vue";

import { _getProductDetails } from "../../../api/mall/mall";
export default {
  data() {
    return {
      productInCart: {},
      details: {
        specification: [
          {
            name: "",
            specification_id: "",
            description: "",
            price: "",
          },
        ],
        advantage: [
          {
            title: "",
            content: "",
            img_url: "",
          },
        ],
        application: [
          {
            title: "",
            content: "",
            img_url: "",
          },
        ],
        info: {
          img_url: "",
          product_name: "",
          introduction: "",
        },
      },

      applicationIndex: 0,

 
      price: 469.2,
    };
  },
  components: {
    trolley,
  },
  methods: {
    clickApplicationMenu(val) {
      this.applicationIndex = val;
    },

    goAnchor(val) {
      document.querySelector(`${val}`).scrollIntoView(true);
    },
    scrollToTop() {
      document.getElementById("product-wrapper").scrollIntoView();
    },
    getProductDetails() {
      _getProductDetails(this.$route.params.id)
        .then((res) => {
          if (res.code === 0) {
            console.log("获取产品详情成功！");
            this.details = res.data.details;
          } else {
            ElMessage.error("获取产品详情失败！");
          }
        })
        .catch((err) => {
          ElMessage.error("获取产品详情失败！");
        });
    },
    addToCart(item) {
      this.productInCart = {
        product_id: this.$route.params.id,
        specification_id: item.specification_id,
        product_name: this.details.info.product_name,
        specification_name: item.name,
        add_number: 1,
        price: item.price,
      };
    },
  },
  mounted() {
    this.scrollToTop();
    this.getProductDetails();
  },
  computed: {
    applicationInfo() {
      return this.details.application[this.applicationIndex];
    },
  },
};
</script>


<style  lang="scss" scoped>
#product-wrapper {
  width: 100%;

  .button {
    display: inline-block;
    border-radius: 4px;
    background-color: #409eff;
    border: none;
    color: #ffffff;
    text-align: center;
    font-size: 16px;
    line-height: 16px;
    padding: 20px;
    width: 145px;
    height: 56px;
    transition: all 0.5s;
    cursor: pointer;
    margin: 5px;
  }
  #banner {
    height: 350px;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: 50% 50%;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    @media screen and (max-width: 720px) {
      height: 200px;
    }
    #content {
      #title {
        font-weight: bold;
        font-size: 38px;
        padding-bottom: 10px;
        @media screen and (max-width: 720px) {
          font-size: 28px;
        }
      }
      padding-top: 100px;
      padding-left: 100px;
      padding-right: 50px;
      @media screen and (max-width: 1080px) {
        padding-top: 60px;
        padding-left: 60px;
        padding-right: 20px;
      }
      @media screen and (max-width: 720px) {
        padding-top: 50px;
        padding-left: 40px;
        padding-right: 20px;
      }
      #second-title {
        line-height: 30px;
        margin-bottom: 30px;

        @media screen and (max-width: 720px) {
          display: none;
        }
      }
    }
  }
  #container-main {
    padding: 0px 15% 40px 15%;
    box-sizing: border-box;
    display: flex;
    @media screen and(max-width:1080px) {
      padding: 0px 20px 40px 20px;
    }
    @media screen and(max-width:720px) {
      padding: 0px 40px 40px 40px;
    }
    #side-menu-wrapper {
      flex-grow: 0;
      flex-shrink: 0;
      height: 360px;
      margin-top: 30px;
      width: 200px;
      @media screen and(max-width:1080px) {
        width: 150px;
      }
      @media screen and(max-width:720px) {
        width: 0;
      }
      #side-menu {
        width: 100%;
        .menu-item {
          height: 60px;
          background-color: rgb(250, 250, 250);
          display: flex;
          align-items: center;
          cursor: pointer;

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
    #details-wrapper {
      margin-top: 40px;
      .details-item {
        margin-bottom: 40px;
      }
      .anchor {
        position: relative;
        top: -30px;
      }
      .specification {
        width: 100%;
      }
      .title {
        font-size: 32px;
      }
      .specification {
        .list-wrapper {
          width: 100%;
          margin-top: 30px;
          display: flex;
          flex-wrap: wrap;
          .list-item {
            width: 33.3%;
            box-sizing: border-box;
            padding: 10px;
            @media screen and(max-width:1080px) {
              width: 50%;
            }
            @media screen and(max-width:480px) {
              width: 100%;
            }

            .card-content {
              line-height: 26px;
              .price span {
                font-size: 28px;
                color: orange;
              }
            }
            .card-button-wrapper {
              margin-top: 20px;
              button {
                width: 100%;
              }
            }
          }
        }
      }
      .advantage {
        .list-wrapper {
          width: 100%;
          margin-top: 30px;
          display: flex;
          flex-wrap: wrap;
          .list-item {
            width: 33.3%;
            box-sizing: border-box;
            padding: 10px 40px 10px 40px;
            text-align: center;
            @media screen and(max-width:1080px) {
              width: 50%;
            }
            @media screen and(max-width:480px) {
              width: 100%;
            }
            .title {
              margin: 10px 0 10px 0;
            }
            .content {
              line-height: 26px;
              font-size: 15px;
              color: #6e6e6e;
            }
            .adv-img-wrapper {
              .adv-img {
                height: 64px;
              }
            }
          }
        }
      }
      .application {
        .application-wrapper {
          .application-menu {
            margin-top: 20px;
            display: flex;
            .active {
              border-bottom: 5px solid #409eff;
            }
            .menuItem {
              height: 30px;
              cursor: pointer;
              flex-grow: 1;
              text-align: center;
              @media screen and(max-width:720px) {
                height: 50px;
              }
            }
          }
          .application-body {
            width: 100%;
            display: flex;
            @media screen and(max-width:1080px) {
              flex-wrap: wrap;
            }
            .application-img {
              flex: 0 0 70%;
              @media screen and(max-width:1080px) {
                flex: 0 0 100%;
              }
              img {
                width: 100%;
              }
            }
            .application-main {
              flex: 0 0 30%;
              box-sizing: border-box;
              padding: 20px;
              @media screen and(max-width:1080px) {
                flex: 0 0 100%;
              }
              .title {
                font-size: 34px;
                margin: 40px 20px 40px 20px;
                text-align: center;
                @media screen and(max-width:1080px) {
                  display: none;
                }
              }
              .content {
                line-height: 24px;
              }
            }
          }
        }
      }
      .document {
        .list-wrapper {
          display: flex;
          flex-wrap: wrap;
          margin-top: 20px;
          .list-item {
            flex-basis: 25%;
            text-align: center;
            padding: 10px;
            box-sizing: border-box;
            height: 150px;
            display: flex;
            justify-content: center;
            align-items: center;

            @media screen and(max-width:1080px) {
              flex-basis: 50%;
            }
            @media screen and(max-width:320px) {
              flex-basis: 100%;
            }
            .card {
              flex-grow: 1;
              height: 100%;
              display: flex;
              flex-wrap: wrap;
              box-sizing: border-box;
              padding: 20px;
              background-color: rgb(255, 255, 255);
              border-radius: 2px;
              cursor: pointer;
              box-shadow: 0 2px 4px rgba(0, 0, 0, 0.08),
                0 0 6px rgba(0, 0, 0, 0.04);
              &:hover {
                box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
              }
              .title {
                font-size: 20px;
                line-height: 38px;
                flex-basis: 100%;
              }
              .content {
                font-size: 15px;
                color: rgb(88, 88, 88);
                flex-basis: 100%;
              }
            }
          }
        }
      }
    }
  }
}
</style>

