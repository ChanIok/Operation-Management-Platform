<template>
  <div id="trolley-wrapper">
    <div
      id="trolley-button"
      :style="{ backgroundImage: 'url(' + trolleyImg + ')' }"
      @click="isTrolleyShow = true"
    ></div>

    <el-drawer
      v-model="isTrolleyShow"
      :direction="direction"
      :with-header="false"
      :size="trolleySize"
      destroy-on-close
    >
      <div class="trolley-header">
        <div>
          <i class="el-icon-shopping-cart-1"></i>
        </div>
        <div class="title">购物车</div>
        <div class="close-button" @click="isTrolleyShow = false">
          <i class="el-icon-s-unfold"></i>
        </div>
      </div>
      <div class="trolley-container">
        <div class="list-wrapper">
          <div class="list-item" v-for="(item, index) in trolley" :key="index">
            <el-card shadow="hover">
              <div class="list-item-container">
                <div class="container-main">
                  <div class="list-item-title">
                    {{ item.product_name }}
                  </div>
                  <div class="list-item-content">
                    <div class="description">
                      {{ item.specification_name }}
                    </div>
                    <div class="price">￥ {{ item.price }}</div>
                  </div>
                </div>
                <div class="num-button">
                  <i class="el-icon-minus" @click="subNum(item, index)"></i>
                  <span class="num-counts"> {{ item.number }}</span>
                  <i class="el-icon-plus" @click="addNum(item)"></i>
                </div>
              </div>
            </el-card>
          </div>
        </div>
      </div>
      <div class="trolley-footer">
        <div class="total-price">
          合计：
          <span> ￥{{ totalPrice }} </span>
        </div>
        <div class="pay"><el-button>支付</el-button></div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import trolleyImg from "../../../assets/images/modules/mall/trolley.png";
import store from "../../../store";
export default {
  data() {
    return {
      isTrolleyShow: false,
      trolleyImg,
      trolleySize: "380px",
      direction: "rtl",
      producesName: "云服务器 ECS",
      sum: 0,
      money: 0,
      totalMoney: 0,
      trolley: [
        {
          product_id: 1,
          specification_id: 7,
          product_name: "云服务器ECS",
          specification_name: "突发性能实例 t6",
          number: 1,
          price: 998,
        },
      ],
    };
  },
  props: ["productInCart"],

  methods: {
    applyClick(val) {
      this.isActive = val.currentTarget.id;
    },
    addNum(item) {
      item.number++;
      this.saveTrolley();
    },
    subNum(item, index) {
      item.number--;
      if (item.number <= 0) {
        this.trolley.splice(index, 1);
      }
      this.saveTrolley();
    },
    setTrolleySize(width) {
      if (width < 480) {
        this.trolleySize = "100%";
      } else {
        this.trolleySize = "380px";
      }
    },
    getTrolley() {
      let temp = JSON.parse(localStorage.getItem("trolley"));
      if (temp !== null) {
        this.trolley = temp.trolley;
      }
    },
    saveTrolley() {
      let trolleyWrapper = {
        trolley: this.trolley,
      };
      localStorage.setItem("trolley", JSON.stringify(trolleyWrapper));
    },
  },
  computed: {
    totalPrice() {
      let sum = 0;
      for (let item of this.trolley) {
        sum += item.number * item.price;
      }
      return sum;
    },
    getWidth() {
      return store.state.width;
    },
  },
  mounted() {
    this.getTrolley();
  },
  watch: {
    getWidth(width) {
      this.setTrolleySize(width);
    },
    productInCart(val) {
      for (let item of this.trolley) {
        if (
          item.product_id === val.product_id &&
          item.specification_id === val.specification_id
        ) {
    
          item.number++;
          this.isTrolleyShow = true;
          this.saveTrolley();
          return;
        }
      }
      let item = {
        product_id: val.product_id,
        specification_id: val.specification_id,
        product_name: val.product_name,
        specification_name: val.specification_name,
        number: 1,
        price: val.price,
      };
      this.trolley.push(item);
      this.isTrolleyShow = true;
      this.saveTrolley();
    },
  },
};
</script>

<style  lang="scss" scoped>
#trolley-wrapper {
  position: relative;
  #trolley-button {
    position: fixed;
    right: 5%;
    bottom: 15%;
    width: 64px;
    height: 64px;
    border-radius: 50%;
    background-size: 40%;
    background-repeat: no-repeat;
    background-position: 50% 50%;
    box-shadow: 0px 3px 3px rgb(167, 160, 160);
    z-index: 5;
    background-color: #fff;
    cursor: pointer;
    &:hover {
      background-color: #bbddffb9;
      color: white;
      transition: 0.25s;
    }
  }

  .trolley-header {
    display: flex;
    font-size: 32px;
    padding: 30px;
    background-color: rgb(250, 250, 250);
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
    position: relative;
    i {
      font-size: 42px;
    }
    .close-button {
      position: absolute;
      right: 40px;
      cursor: pointer;
      @media screen and (min-width: 480px) {
        display: none;
      }
      &:hover {
        color: #409eff;
        transition-duration: 0.2s;
        transition-timing-function: ease-in-out;
      }
    }
  }
  .trolley-container {
    height: calc(100vh - 105px);
  }
  .list-wrapper {
    overflow: auto;
    height: 100%;
    overflow: auto;
    padding-bottom: 85px;
    box-sizing: border-box;
    .list-item {
      padding: 10px;
      box-sizing: border-box;
      .list-item-container {
        display: flex;
        .container-main {
          flex-basis: 70%;
          .list-item-title {
            font-size: 22px;
            margin-bottom: 5px;
          }
          .list-item-content {
            font-size: 16px;
            .description {
              color: #808080;
              margin-bottom: 5px;
            }
            .price {
              font-size: 18px;
            }
          }
        }

        .num-button {
          display: flex;
          justify-content: center;
          align-items: center;
          flex-basis: 30%;
          i {
            cursor: pointer;
            font-size: 24px;
            &:hover {
              color: #409eff;
              transition-duration: 0.2s;
              transition-timing-function: ease-in-out;
            }
          }
          .num-counts {
            font-size: 26px;
            margin-left: 10px;
            margin-right: 10px;
          }
        }
      }
    }
  }
  .trolley-footer {
    position: absolute;
    display: flex;
    justify-content: space-between;
    align-items: center;
    bottom: 0;
    display: flex;
    font-size: 20px;
    width: 100%;
    padding: 20px 20px 20px 20px;
    background-color: rgb(250, 250, 250);
    box-shadow: 0 -2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
    .total-price span {
      color: #ff6a00;
      font-size: 22px;
    }
  }
}

#trolleyHead {
  background-color: rgb(71, 70, 70);
  color: white;
  height: 40px;
  text-align: left;
  display: inline block;
}
#trolleyBody {
  height: 80%;
  display: inline block;
  text-align: left;
}
#trolleyFoot {
  height: 20%;
  display: flex;
  text-align: left;
}
#IMG {
  width: auto;
  height: 70%;
  margin-top: 5px;
  margin-left: 7px;
}
.totalMoney {
  line-height: 110px;
  width: 150px;
  height: 100%;
  flex: 0 0 65%;
}

.sumButton {
  border-radius: 50%;
  width: 15px;
  height: 15px;
  text-align: center;
  line-height: 15px;
  border: 1px solid black;
}
.produces {
  width: 90%;
  height: 100px;
  margin-top: 15px;
  display: flex;
  border: 1px solid black;
}
.Sum {
  width: 100%;
  height: 32%;
  margin-top: 18px;
  display: flex;
}
.money {
  width: 100%;
  height: 50%;
  line-height: 50px;
}
.producesName {
  width: 50%;
  height: 100%;
  text-align: center;
  line-height: 100px;
}
.producesSum {
  width: 50%;
  height: 100%;
}
</style>