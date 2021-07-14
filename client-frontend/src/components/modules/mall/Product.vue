<template>
  <div id="product-wrapper" style="width: 100%; height: 800px">
    <div
      id="trolley"
      :style="{ backgroundImage: 'url(' + trolleyImgUrl + ')' }"
      @click="trolleyEnter()"
    ></div>
    <trolley v-if="trolleyBool"></trolley>
    <div id="exit" v-if="trolleyBool" @click="trolleyExit()">×</div>

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
                    <el-button type="primary">加入购物车</el-button>
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
      details: {
        specification: [
          {
            name: "",
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
        },
      },

      applicationIndex: 0,
      trolleyImgUrl:
        "https://img.alicdn.com/tfs/TB1SZr9khD1gK0jSZFyXXciOVXa-48-48.png",
      trolleyBool: 0,
      price: 469.2,
    };
  },
  mounted() {
    this.scrollToTop();
    this.getProductDetails();
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
#details.info.product_name {
  padding-top: 2%;
  margin-left: 15%;
  width: 30%;
  font-size: 32px;
}
#details.info.introduction {
  padding-top: 1%;
  margin-left: 15%;
  width: 30%;
  font-size: 16px;
}
</style>

