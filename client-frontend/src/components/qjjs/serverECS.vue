<template>
  <div id="Box" style="width: 100%; height: 800px">
    <div
      id="trolley"
      :style="{ backgroundImage: 'url(' + trolleyImgUrl + ')' }"
      @click="trolleyEnter()"
    ></div>
    <trolley v-if="trolleyBool"></trolley>
    <div id="exit" v-if="trolleyBool" @click="trolleyExit()">×</div>

    <div id="head" :style="{ backgroundImage: 'url(' + ImgUrl + ')' }">
      <div id="productName">
        <b>{{ productName }}</b>
      </div>
      <div id="introduction">
        {{ introduction }}
      </div>
      <div id="head_bottom">
        <button class="button"><span>立即购买</span></button>
      </div>
    </div>

    <div id="navigation" class="flex">
      <div class="nav" @click="jump('#par')">产品规格</div>
      <div class="nav" @click="jump('#adv')">产品优势</div>
      <div class="nav" @click="jump('#apply')">应用场景</div>
      <div class="nav" @click="jump('#docu')">文档</div>
    </div>
    <div class="body">
      <div id="parameter">
        <h1 id="par">产品规格</h1>
        <div class="flex">
          <div class="border">
            <div
              class="paraHead"
              :style="{ backgroundImage: 'url(' + ImgUrl + ')' }"
            >
              <div class="para_dtName">{{ detailname }}</div>
              <div class="para_note">{{ note }}</div>
            </div>
            <div class="paraFoot">
              <div class="price">
                <span>￥{{ price }}</span
                >/年
              </div>
              <button class="button"><span>加入购物车</span></button>
            </div>
          </div>
          <div class="border">
            <div
              class="paraHead"
              :style="{ backgroundImage: 'url(' + ImgUrl + ')' }"
            >
              <div class="para_dtName">{{ detailname }}</div>
              <div class="para_note">{{ note }}</div>
            </div>
            <div class="paraFoot">
              <div class="price">
                <span>￥{{ price }}</span
                >/年
              </div>
              <button class="button"><span>加入购物车</span></button>
            </div>
          </div>
          <div class="border">
            <div
              class="paraHead"
              :style="{ backgroundImage: 'url(' + ImgUrl + ')' }"
            >
              <div class="para_dtName">{{ detailname }}</div>
              <div class="para_note">{{ note }}</div>
            </div>
            <div class="paraFoot">
              <div class="price">
                <span>￥{{ price }}</span
                >/年
              </div>
              <button class="button"><span>加入购物车</span></button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="body myPadding">
      <h1 id="adv">产品优势</h1>
      <div class="flex">
        <div class="advBox">
          <div>
            <img :src="advantage_ImgUrl" alt="..." class="advImg" />
          </div>
          <h3 class="advFormat">稳定</h3>
          <div class="advFormat">{{ advantage_note }}</div>
        </div>
        <div class="advBox">
          <div>
            <img :src="advantage_ImgUrl" alt="..." class="advImg" />
          </div>
          <h3 class="advFormat">稳定</h3>
          <div class="advFormat">{{ advantage_note }}</div>
        </div>
        <div class="advBox">
          <div>
            <img :src="advantage_ImgUrl" alt="..." class="advImg" />
          </div>
          <h3 class="advFormat">稳定</h3>
          <div class="advFormat">{{ advantage_note }}</div>
        </div>
      </div>
    </div>
    <div class="body myPadding">
      <h1 id="apply">应用场景</h1>
      <div class="flex">
        <div id="applicationHead" class="flex">
          <div
            class="apply"
            :class="{ active: isActive == 1 }"
            id="1"
            @click="applyClick($event)"
          >
            Web应用
          </div>
          <div
            class="apply"
            :class="{ active: isActive == 2 }"
            id="2"
            @click="applyClick($event)"
          >
            Web应用
          </div>
          <div
            class="apply"
            :class="{ active: isActive == 3 }"
            id="3"
            @click="applyClick($event)"
          >
            Web应用
          </div>
        </div>
        <div id="applicationBody" class="flex">
          <div class="appBodyImg">
            <img :src="application_ImgUrl" alt="..." class="appImg" />
          </div>
          <div class="appBodyNote">
            <h2>{{ appliName }}</h2>
            <div>{{ appliNote }}</div>
          </div>
        </div>
      </div>
    </div>

    <div class="body myPadding">
      <h2 id="docu">文档与工具</h2>
      <div class="flex">
        <div class="doc"><span>产品帮助</span></div>
        <div class="doc"><span>常见问题</span></div>
      </div>
    </div>
  </div>
</template>

<style>
* {
  margin: 0 auto;
}
h1 {
  margin-top: 50px;
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
.body {
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
.active {
  border-bottom: 5px solid orange;
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
.button {
  display: inline-block;
  border-radius: 4px;
  background-color: #f4511e;
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
.price {
  padding-top: 15px;
}
.price span {
  font-size: 28px;
  color: orange;
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

<script>
import trolley from "../modules/mall/trolley.vue";
export default {
  data() {
    return {
      productName: "云服务器 ECS",
      introduction:
        "云服务器 ECS（Elastic Compute Service）是一种弹性可伸缩的云计算服务，助您降低 IT 成本，提升运维效率，使您更专注于核心业务创新。专业的售前技术支持，协助您选择最合适配置方案，详询：18123-1",
      detailname: "突发性能实例 t6",
      note: "轻量低负载场景，20%CPU性能轻松覆盖",
      ImgUrl:
        "https://img.alicdn.com/imgextra/i4/O1CN01lt781o1mM6pL1V58A_!!6000000004939-1-tps-3840-740.gif",
      advantage_ImgUrl:
        "https://img.alicdn.com/tfs/TB1K6SgzET1gK0jSZFrXXcNCXXa-128-128.png",
      advantage_note:
        "云服务器 ECS通过可信云服务认证,可用性达 99.98%,多可用区多实例可用性达 99.995%，云盘可靠性达 99.9999999%，可实现自动宕机迁移、快照备份",
      isActive: 3,
      application_ImgUrl:
        "https://img.alicdn.com/imgextra/i2/O1CN01FSrA5F1y9XLjVtMhl_!!6000000006536-2-tps-1360-1520.png",
      appliName: "Web应用",
      appliNote:
        "较为通用网站类型，如门户、MIS系统等，使⽤中小型的数据库，无需很强的处理能力，无较大业务并发量。",
      trolleyImgUrl:
        "https://img.alicdn.com/tfs/TB1SZr9khD1gK0jSZFyXXciOVXa-48-48.png",
      trolleyBool: 0,
      price: 469.2,
    };
  },
  mounted() {},
  components: {
    trolley,
  },
  methods: {
    applyClick(val) {
      this.isActive = val.currentTarget.id;
    },
    trolleyEnter() {
      this.trolleyBool = 1;
    },
    trolleyExit() {
      this.trolleyBool = 0;
    },
    jump(val) {
      console.log(val);
      document.querySelector(`${val}`).scrollIntoView(true);
    },
  },
};
</script>