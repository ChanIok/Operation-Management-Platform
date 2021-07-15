<template>
  <div id="personal-data">
    <div id="header">
      <div id="title">个人中心</div>
    </div>
    <div id="container">
      <el-affix target="#container" :offset="80" id="side-menu-wrapper">
        <div id="side-menu">
          <div class="menu-item" @click="goAnchor('account')">
            <i class="el-icon-user-solid"></i>
            <div class="menu-item-descript">我的帐户</div>
          </div>
          <div class="menu-item" @click="goAnchor('products')">
            <i class="el-icon-s-goods"></i>
            <div class="menu-item-descript">我的产品</div>
          </div>
          <div class="menu-item" @click="goAnchor('experiences')">
            <i class="el-icon-s-flag"></i>
            <div class="menu-item-descript">我的体验</div>
          </div>
        </div>
      </el-affix>

      <div id="list-wrapper">
        <div class="list-item">
          <div class="anchor" id="account"></div>
          <div class="item-header">
            <div class="title">我的帐户</div>
            <el-button @click="enterEdit" id="edit-button">编辑</el-button>
          </div>
          <el-divider></el-divider>
          <el-descriptions title="" :column="columnSize" border>
            <el-descriptions-item label="用户名">
              <span>{{ info.username }}</span>
            </el-descriptions-item>
            <el-descriptions-item label="联系电话">
              <span>{{ info.phone_num }}</span>
            </el-descriptions-item>
            <el-descriptions-item label="邮箱">
              <span>{{ info.email }}</span>
            </el-descriptions-item>
          </el-descriptions>

          <div class="account-item-footer">
            <div class="balance-wrapper">
              <span class="balance-descript"> 余额： ￥ </span>
              <span class="balance-number"> {{ balance }}</span>
            </div>
            <el-button type="primary" @click="isTopUP = true">充值</el-button>
          </div>
        </div>

        <div class="list-item card-show" id="products-wrapper">
          <div class="anchor" id="products"></div>
          <div class="item-header">
            <div class="title">我的产品</div>
          </div>
          <el-divider></el-divider>

          <div class="product-list-wrapper">
            <div
              class="product-list-item"
              v-for="(pItem, pIndex) in products"
              :key="pIndex"
            >
              <el-card class="box-card" shadow="hover">
                <template #header>
                  <div class="card-header">
                    <span>{{ pItem.product_name }}</span>
                  </div>
                </template>
                <div class="card-content">
                  <div class="content-descript">{{ pItem.description }}</div>
                  <div class="content-time">
                    <span>到期时间：</span>{{ pItem.authority_time }}
                  </div>
                </div>
              </el-card>
            </div>
          </div>
        </div>
        <div class="list-item card-show">
          <div class="anchor" id="experiences"></div>
          <div class="item-header">
            <div class="title">我的体验</div>
          </div>
          <el-divider></el-divider>

          <div class="product-list-wrapper">
            <div
              class="product-list-item"
              v-for="(eItem, eIndex) in products"
              :key="eIndex"
            >
              <el-card class="box-card" shadow="hover">
                <template #header>
                  <div class="card-header">
                    <span>{{ eItem.product_name }}</span>
                  </div>
                </template>
                <div class="card-content">
                  <div class="content-descript">{{ eItem.description }}</div>
                  <div class="content-time">
                    <span>到期时间：</span>{{ eItem.authority_time }}
                  </div>
                </div>
              </el-card>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div id="dialog-wrapper">
    <el-dialog
      title="修改信息"
      v-model="infoEdit"
      :width="dialogWidth"
      :fullscreen="dialogFull"
    >
      <el-form
        :model="info"
        :label-position="labelPosition"
        :rules="rules"
        ref="info"
      >
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="info.username" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
          <el-input v-model="info.email"></el-input>
        </el-form-item>
        <el-form-item
          label="手机"
          :label-width="formLabelWidth"
          prop="phone_num"
        >
          <el-input v-model="info.phone_num"></el-input>
        </el-form-item>
      </el-form>

      <div id="dialog-footer">
        <div>
          <el-button @click="cancelEdit">取 消</el-button>
        </div>
        <div>
          <el-button type="primary" @click="editForm('info')">确 定</el-button>
        </div>
      </div>
    </el-dialog>

    <el-dialog
      title="充值"
      v-model="isTopUP"
      :width="dialogWidth"
      :fullscreen="dialogFull"
    >
      <div id="topUpWrapper">
        <div
          class="imgWrapper"
          :style="{ backgroundImage: 'url(' + alipay + ')' }"
        ></div>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="isTopUP = false">取 消</el-button>
          <el-button type="primary" @click="isTopUP = false">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { ElMessage } from "element-plus";
import {
  _getUserInfo,
  _updateUserInfo,
  _getUserBalance,
} from "../../../api/user/user";
import alipay from "../../../assets/images/modules/top-up/alipay.jpg";

import store from "../../../store";
export default {
  name: "PersonalData",
  data() {
    let validatePhone = (rule, value, callback) => {
      if (!/^\d+$/.test(value)) {
        callback(new Error("请输入数字值"));
      } else {
        if (value.length != 11) {
          callback(new Error("手机必须为11位"));
        } else {
          callback();
        }
      }
    };
    let validateEmail = (rule, value, callback) => {
      let myreg =
        /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      if (!myreg.test(value)) {
        callback(new Error("邮箱格式不对"));
      } else {
        callback();
      }
    };
    return {
      alipay,
      isTopUP: false,
      infoEdit: false,
      columnSize: 1,
      dialogWidth: "60%",
      dialogFull: false,
      labelPosition: "right",
      formLabelWidth: "80px",
      balance: 0,
      info: {
        username: "",
        email: "",
        phone_num: "",
      },
      products: [
        {
          product_name: "云服务器ECS",
          description: "突发性能实例 t6",
          authority_time: "2022-07-30",
        },
        {
          product_name: "轻量应用服务器",
          description: "轻量应用服务器 2核1G",
          authority_time: "2022-02-10",
        },
        {
          product_name: "弹性裸金属服务器",
          description: "AMD 通用型弹性裸金属服务器 ebmg6a",
          authority_time: "2023-01-09",
        },
        {
          product_name: "云服务器ECS",
          description: "突发性能实例 t6",
          authority_time: "2023-01-31",
        },
        {
          product_name: "云安全中心",
          description: "高级版",
          authority_time: "2027-11-29",
        },
        {
          product_name: "云原生关系型数据库PolarDB",
          description: "PolarDB MySQL版",
          authority_time: "2021-09-09",
        },
      ],
      rules: {
        phone_num: [{ validator: validatePhone, trigger: "blur" }],
        email: [{ validator: validateEmail, trigger: "blur" }],
      },
      infoBackup: {},
    };
  },
  components: {},
  methods: {
    editForm(info) {
      this.$refs[info].validate((valid) => {
        if (valid) {
          let userInfoDto = {
            email: this.info.email,
            phone_num: this.info.phone_num,
          };

          _updateUserInfo(userInfoDto)
            .then((res) => {
              if (res.code === 0) {
                ElMessage.success({
                  offset:60,
                  message: res.data.message,
                  type: "success",
                });
                this.infoEdit = false;
              } else {
                ElMessage.error("更新个人信息失败！");
              }
            })

            .catch((err) => {
              ElMessage.error("更新个人信息失败！");
            });
        } else {
          console.log("error submit!!");
        }
      });
    },
    goAnchor(index) {
      let anchor = document.getElementById(index);
      if (anchor !== null) anchor.scrollIntoView();
    },
    getUserInfo() {
      if (localStorage.getItem("token") === null) {
        this.$router.push({
          path: "/login",
          query: { return: this.$route.path },
        });
        return;
      }
      _getUserInfo()
        .then((res) => {
          if (res.code === 0) {
            console.log(res.data.message);
            this.info = res.data.userInfo;
          } else {
            throw err;
          }
        })
        .then(() => {
          _getUserBalance().then((res) => {
            if (res.code === 0) {
              console.log(res.data.message);
              this.balance = res.data.balance;
            } else {
              throw err;
            }
          });
        })
        .catch((err) => {
          ElMessage.error("获取个人信息失败！");
        });
    },

    setDialogSize(width) {
      if (width < 720) {
        this.labelPosition = "top";
        this.dialogFull = true;
      } else {
        this.labelPosition = "right";
        this.dialogFull = false;
      }
    },
    enterEdit() {
      this.infoEdit = true;
      this.infoBackup = JSON.parse(JSON.stringify(this.info));
    },
    cancelEdit() {
      this.infoEdit = false;
      this.info = this.infoBackup;
    },
  },
  mounted() {
    this.getUserInfo();
  },
  computed: {
    getWidth() {
      return store.state.width;
    },
  },
  watch: {
    getWidth(width) {
      this.setDialogSize(width);
    },
  },
};
</script>
<style lang="scss">
#topUpWrapper {
  .imgWrapper {
    background-position: 50% 50%;
    height: 50vh;
    background-size: contain;
    background-repeat: no-repeat;
  }
}
</style>
<style  lang="scss" scoped>
#personal-data {
  height: 100%;
  box-sizing: border-box;
  #header {
    line-height: 48px;
    font-size: 36px;
    font-weight: bold;
    display: flex;
    justify-content: space-between;
    padding: 0 15% 40px 15%;
    @media screen and(max-width:1080px) {
      padding: 10px 20px 40px 20px;
    }
    @media screen and(max-width:720px) {
      padding: 10px 0 40px 10px;
    }
  }

  padding: 40px;
  @media screen and (max-width: 720px) {
    padding: 20px;
  }
  @media screen and (max-width: 360px) {
    padding: 10px;
  }
  #container {
    padding: 0px 15% 40px 15%;
    box-sizing: border-box;
    display: flex;
    justify-content: space-between;
    @media screen and(max-width:1080px) {
      padding: 0 20px 40px 20px;
    }
    @media screen and(max-width:720px) {
      padding: 0 0 40px 0;
    }
    #side-menu-wrapper {
      width: 300px;
      height: 360px;
      flex-shrink: 0;
      @media screen and(max-width:1080px) {
        width: 200px;
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
          .menu-item-descript {
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
          &:hover .menu-item-descript {
            padding-left: 28px;
          }
        }
      }
    }

    #list-wrapper {
      flex-grow: 1;
      .list-item {
        padding: 10px;
        margin-bottom: 20px;
        .anchor {
          position: relative;
          top: -20px;
        }
        .item-header {
          display: flex;
          height: 60px;
          align-items: center;
          .title {
            width: 100%;
            height: 30px;
            line-height: 30px;
            font-size: 26px;
          }
        }
      }
    }
    .account-item-footer {
      width: 100%;
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 80px;
      .balance-number {
        font-size: 24px;
        font-weight: 500;
      }
      .el-button {
        flex-basis: 30%;
      }
    }
    .card-show {
      .product-list-wrapper {
        display: flex;
        flex-wrap: wrap;
        .product-list-item {
          cursor: pointer;
          flex-basis: 50%;
          padding: 10px;
          box-sizing: border-box;
          @media screen and(max-width:1080px) {
            flex-basis: 100%;
          }
          .box-card {
            .card-header {
              font-weight: bold;
            }
          }
          .card-content {
            color: #555555;
            .content-descript {
              margin-bottom: 10px;
            }
            .content-time {
              span {
                font-size: 14px;
              }
            }
          }
        }
      }
    }
  }
}

#dialog-wrapper {
  #dialog-footer {
    display: flex;
    justify-content: flex-end;
    div {
      margin-left: 10px;
    }
  }
}
</style>