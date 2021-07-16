<template>
  <div id="mall">
    <div id="banner" :style="{ backgroundImage: 'url(' + banner + ')' }">
      <div id="content">
        <div id="title">体验中心</div>
        <div id="second-title">0元体验，畅快上云</div>
      </div>
    </div>
    <div class="big-title">为您推荐</div>
    <div id="container-main">
      <div id="trial-list-wrapper">
        <div
          class="trial-list-group"
          v-for="(item, index) in trials"
          :key="index"
        >
          <div class="anchor" :id="`item${index + 1}`"></div>
          <div class="title-type">{{ item.product_type }}</div>
          <div
            class="trial-list-item"
            v-for="(pItem, pIndex) in item.list"
            :key="pIndex"
            @click="clickTrial(pItem.product_id)"
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
import { _getTrials } from "../../../api/trial/trial";
import banner from "../../../assets/images/modules/mall/banner1.jpg";
export default {
  data() {
    return {
      banner,
      trials: [],
    };
  },

  methods: {
    clickTrial(index) {
      this.$router.push(`/trial/${index}`);
    },
    getTrials() {
      _getTrials()
        .then((res) => {
          if (res.code === 0) {
            console.log(res.data.message);
            this.trials = res.data.products;
          } else {
            ElMessage.error({
              offset: 60,
              message: res.data.message,
              type: "error",
            });
          }
        })
        .catch((err) => {
          ElMessage.error({
            offset: 60,
            message: "获取产品列表失败！",
            type: "error",
          });
        });
    },
  },
  mounted() {
    this.getTrials();
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
          .trial-descript {
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
          &:hover .trial-descript {
            padding-left: 28px;
          }
        }
      }
    }
    #trial-list-wrapper {
      flex-grow: 1;
      .trial-list-group {
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
        .trial-list-item {
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
 