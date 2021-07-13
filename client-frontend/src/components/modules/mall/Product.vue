<template>
  <div id="product-wrapper" style="width: 100%; height: 800px">
    <div
      id="trolley"
      :style="{ backgroundImage: 'url(' + trolleyImgUrl + ')' }"
      @click="trolleyEnter()"
    ></div>
    <trolley v-if="trolleyBool"></trolley>
    <div id="exit" v-if="trolleyBool" @click="trolleyExit()">×</div>

    <div id="banner" :style="{ backgroundImage: 'url(' + bannerImg + ')' }">
      <div id="content">
        <div id="title">{{ productName }}</div>
        <div id="second-title">{{ introduction }}</div>
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

      <div id="description-wrapper">
        <div class="specification description-item">
          <div class="anchor" id="par"></div>
          <div class="title">产品规格</div>

          <div class="list-wrapper">
            <div
              class="list-item"
              v-for="(item, index) in description.specification"
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
                    <el-button type="primary">加入购物车</el-button>
                  </div>
                </div>
              </el-card>
            </div>
          </div>
        </div>

        <div class="advantage description-item">
          <div class="anchor" id="adv"></div>
          <div class="title">产品优势</div>
          <div class="list-wrapper">
            <div
              class="list-item"
              v-for="(item, index) in description.advantage"
              :key="index"
            >
              <div class="adv-img-wrapper">
                <img :src="item.imgUrl" class="adv-img" />
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

        <div class="application description-item">
          <div class="anchor" id="apply"></div>
          <div class="title">应用场景</div>
          <div class="application-wrapper">
            <div class="application-menu">
              <div
                :class="{
                  menuItem: true,
                  active: applicationIndex == indexMenu,
                }"
                v-for="(itemMenu, indexMenu) in description.application"
                :key="indexMenu"
                @click="clickApplicationMenu(indexMenu)"
              >
                {{ itemMenu.title }}
              </div>
            </div>
            <div class="application-body">
              <div class="application-img">
                <img :src="applicationInfo.imgUrl" alt="..." />
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

        <div class="document description-item">
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
export default {
  data() {
    return {
      productName: "云服务器 ECS",
      introduction:
        "云服务器 ECS是一种弹性可伸缩的计算服务，助您降低 IT 成本，提升运维效率，使您更专注于核心业务创新。",
      detailname: "突发性能实例 t6",
      note: "轻量低负载场景，20%CPU性能轻松覆盖",
      description: {
        specification: [
          {
            name: "突发性能实例 t6",
            description:
              "开发者成长计划补贴！轻量低负载场景，20%CPU性能轻松覆盖",
            price: 552.0,
          },
          {
            name: "突发性能实例 t5",
            description: "20%性能基线，性价比之王！个人开发者建站必备",
            price: 626.4,
          },
          {
            name: "共享型实例s6",
            description:
              "100%性能基线，性能强劲，超高性价比，广泛适用于建站等轻量应用",
            price: 840.0,
          },
          {
            name: "轻量应用服务器 2核1G",
            description: "一键上云，可视化面板。适个人建站等轻量场景",
            price: 612.0,
          },
          {
            name: "轻量应用服务器 2核2G",
            description: "一键上云，可视化面板。适个人建站等轻量场景",
            price: 1020.0,
          },
        ],
        advantage: [
          {
            title: "稳定",
            content:
              "单实例可用性达 99.975%，多可用区多实例可用性达 99.995%，云盘可靠性达 99.9999999%，可实现自动宕机迁移、快照备份",
            imgUrl:
              "https://img.alicdn.com/tfs/TB1K6SgzET1gK0jSZFrXXcNCXXa-128-128.png",
          },
          {
            title: "弹性",
            content:
              "支持分钟级别创建1000台实例，多种弹性付费选择更贴合业务现状，同时带来弹性的扩容能力，实例与带宽均可随时升降配，云盘可扩容",
            imgUrl:
              "https://img.alicdn.com/tfs/TB1h_houqL7gK0jSZFBXXXZZpXa-128-128.png",
          },
          {
            title: "高安全",
            content:
              "免费提供 DDoS 防护、木马查杀、防暴力破解等服务，通过多方国际安全认证，ECS云盘支持数据加密功能",
            imgUrl:
              "https://img.alicdn.com/tfs/TB1JhtxuAT2gK0jSZFkXXcIQFXa-128-128.png",
          },
        ],
        application: [
          {
            title: "通用Web应用",
            content:
              "大部分Web应用使用的架构，阿里云推荐C/G/R系列服务器，兼顾高效搭建使用及高性能处理能力",
            imgUrl:
              "https://img.alicdn.com/imgextra/i2/O1CN01FSrA5F1y9XLjVtMhl_!!6000000006536-2-tps-1360-1520.png",
          },
          {
            title: "在线游戏",
            content:
              "高并发、瞬时计算量大的场景，阿里云推荐高主频及GPU服务器实现高计算性能与高图像渲染性能的需求",
            imgUrl:
              "https://img.alicdn.com/imgextra/i3/O1CN01G4Mtt91QTVgkjvX55_!!6000000001977-2-tps-1520-1360.png",
          },
          {
            title: "大数据分析",
            content:
              "对于频繁对存储读取的大数据应用场景，阿里云推荐大数据实例及本地盘实例，主从节点皆有性能优异表现",
            imgUrl:
              "https://img.alicdn.com/imgextra/i1/O1CN01qvouya1QC6dgl7vKB_!!6000000001939-2-tps-1360-1520.png",
          },
          {
            title: "深度学习",
            content:
              "对于持续且大量的人工神经网络计算的深度学习场景，阿里云推荐GPU实例及AMD实例，不但性能表现卓越，同时大量节省成本",
            imgUrl:
              "https://img.alicdn.com/imgextra/i2/O1CN01bYlY2i1JqgPqaKgOB_!!6000000001080-2-tps-1360-1360.png",
          },
        ],
      },
      bannerImg:
        "https://img.alicdn.com/imgextra/i4/O1CN01lt781o1mM6pL1V58A_!!6000000004939-1-tps-3840-740.gif",

      applicationIndex: 0,

      trolleyImgUrl:
        "https://img.alicdn.com/tfs/TB1SZr9khD1gK0jSZFyXXciOVXa-48-48.png",
      trolleyBool: 0,
      price: 469.2,
    };
  },
  mounted() {
    this.scrollToTop();
  },
  components: {
    trolley,
  },
  methods: {
    clickApplicationMenu(val) {
      this.applicationIndex = val;
    },
    trolleyEnter() {
      this.trolleyBool = 1;
    },
    trolleyExit() {
      this.trolleyBool = 0;
    },
    goAnchor(val) {
      document.querySelector(`${val}`).scrollIntoView(true);
    },
    scrollToTop() {
      document.getElementById("product-wrapper").scrollIntoView();
    },
  },
  computed: {
    applicationInfo() {
      return this.description.application[this.applicationIndex];
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
    #description-wrapper {
      margin-top: 40px;
      .description-item {
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
              border-radius:2px;
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
#exit {
  width: 30px;
  height: 30px;
  position: absolute;
  right: 10px;
  top: 5px;
  line-height: 30px;
  text-align: center;
  font-size: 38px;
  z-index: 7;
  color: white;
}
#trolley {
  position: fixed;
  right: 2%;
  bottom: 25%;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-size: 45%;
  background-repeat: no-repeat;
  background-position: 12px 14px;
  box-shadow: 0px 3px 3px rgb(167, 160, 160);
  z-index: 5;
}
#trolley:hover {
  background-color: rgb(206, 221, 70);
  color: white;
  transition: 0.5s;
}
#applicationHead {
  width: 100%;
  height: 40px;
  margin-top: 30px;
}
#parameter {
  margin: 50px auto;
}
.doc span:after {
  content: "→";
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}
.doc:hover span {
  padding-right: 25px;
}
.doc:hover span:after {
  opacity: 1;
  right: 0;
}
.doc span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.myPadding {
  padding-top: 80px;
}
.specification {
  width: 72%;
}
.border {
  border: 1px solid black;
  width: 31%;
  height: auto;
  margin-top: 20px;
}
.para_dtName {
  font-size: 24px;
  margin: 10px;
}
.para_note {
  font-size: 14px;
  margin: 5px 10px;
}
.paraHead {
  text-align: left;
  width: 86%;
  height: 40%;
  border-bottom: 1px solid rgb(196, 212, 211);
  background-size: 300%;
  background-repeat: no-repeat;
  background-position: -290px -20px;
}
.paraFoot {
  text-align: left;
  width: 86%;
  height: 60%;
}

.appBodyNote {
  width: 30%;
  height: auto;
  flex: 0 0 30%;
  margin-top: 80px;
  text-align: left;
}
.appBodyNote div {
  margin-top: 20px;
}
.appBodyImg {
  flex: 0 0 70%;
  margin-top: 10px;
}
.flex {
  display: flex;
  flex-flow: row wrap;
  align-content: flex-start;
  justify-content: flex-start;
}
.doc {
  width: 40%;
  height: 80px;
  line-height: 80px;
  font-size: 20px;
  border: 1px solid black;
  margin-top: 20px;
}
.doc:hover {
  background-color: cornsilk;
}
.apply {
  flex: 0 0 25%;
  line-height: 40px;
}
.appImg {
  width: 100%;
  height: auto;
}

.scene {
  flex: 0 0 30%;
  width: 30%;
  height: 100%;
}
.sceneDetail {
  flex: 0 0 70%;
  margin-left: 10px;
}
.advBox {
  margin-top: 30px;
  flex: 0 0 26%;
}
.advFormat {
  margin-top: 10px;
}
.advImg {
  width: 64px;
  height: 64px;
}
#navigation {
  width: 100%;
  height: 40px;
  box-shadow: 0px 5px 5px rgb(167, 160, 160);
}
.nav {
  flex: 0 0 11%;
  font-size: 16px;
  line-height: 26px;
  padding: 5px;
  text-align: center;
}
.nav:hover {
  transform: scale(1.1);
  border-bottom: 3px solid orange;
}
#head_bottom {
  margin-left: 15%;
  padding-top: 1%;
  width: 30%;
  display: flex;
}

.button span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.button span:after {
  content: "»";
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.button:hover span {
  padding-right: 25px;
}

.button:hover span:after {
  opacity: 1;
  right: 0;
}

#head {
  width: 100%;
  height: 40%;
  text-align: left;
  background-size: 100%;
  background-repeat: no-repeat;
}
#productName {
  padding-top: 2%;
  margin-left: 15%;
  width: 30%;
  font-size: 32px;
}
#introduction {
  padding-top: 1%;
  margin-left: 15%;
  width: 30%;
  font-size: 16px;
}
</style>

