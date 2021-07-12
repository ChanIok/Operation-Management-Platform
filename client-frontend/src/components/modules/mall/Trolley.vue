<template>
  <div id="trolleyDetail">
    <div id="trolleyHead">
      <img
        src="https://img.alicdn.com/tfs/TB1SZr9khD1gK0jSZFyXXciOVXa-48-48.png"
        alt=""
        id="IMG"
      />
      <span> 购物车 |</span>
    </div>
    <div id="trolleyBody">
      <div class="produces">
        <div class="producesName">
          {{ producesName }}
        </div>
        <div class="producesSum">
          <div class="Sum">
            <div class="sumButton" @click="sum && sumSub()">-</div>
            <div>{{ sum }}</div>
            <div class="sumButton" @click="sumAdd()">+</div>
          </div>
          <div class="money">
            <h3>&emsp; 共 &emsp; ￥{{ money }}</h3>
          </div>
        </div>
      </div>
    </div>
    <div id="trolleyFoot">
      <div class="totalMoney">
        <h2>&ensp;合计：￥{{ totalMoney }}</h2>
      </div>
      <div class="pay"><button>支付</button></div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      producesName: "云服务器 ECS",
      sum: 0,
      money: 0,
      totalMoney: 0,
    };
  },
  mounted() {},
  methods: {
    applyClick(val) {
      this.isActive = val.currentTarget.id;
    },
    sumAdd() {
      this.sum++;
      this.money = this.sum * 99;
      this.totalMoney = this.money;
    },
    sumSub() {
      this.sum--;
      this.money = this.sum * 99;
      this.totalMoney = this.money;
    },
  },
};
</script>

<style  lang="scss" scoped>
@keyframes appear {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
#trolleyDetail {
  animation:  appear 1.5s;
  position: fixed;
  right: 0;
  top: 0;
  width: 350px;
  height: 100vh;
  background-color: white;
  z-index: 6;
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
.pay {
  line-height: 110px;
  width: 150px;
  height: 100%;
  flex: 0 0 35%;
}
.pay button {
  font-size: 16px;
  border-radius: 15px;
  transition-duration: 0.5s;
  background-color: #4caf50;
  color: white;
  border: 1px solid #4caf50;
  width: 100px;
  height: 50px;
}
.pay button:hover {
  background-color: #5ad65e;
  color: black;
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