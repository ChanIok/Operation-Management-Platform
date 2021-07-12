<template>
  <div id="market">
    <div class="bigHeadline">弹性计算</div>
    <div class="smallHeadline"><b class="Headline_b">| </b> 云服务器</div>
    <div id="container">
      <div class="product">
        <div class="productName">
          <router-link to="/serverECS"><b>{{ productName }}</b></router-link>
        </div>
        <div class="introduction">
          {{ introduction }}
        </div>
        <div class="purchase">
          <button>加入购物车</button>
        </div>
      </div>
      
      <div class="product" >
        <div class="productName">
          <a href=""><b>{{ productName }}</b></a>
        </div>
        <div class="introduction">
          {{ introduction }}
        </div>
        <div class="purchase">
          <button>加入购物车</button>
        </div>
      </div>
    </div>

    <div class="smallHeadline"><b class="Headline_b">| </b> 高性能计算HPC</div>

  </div>
 
</template>

<style>
* {
  margin: 0 auto;
}
#market {
  width: 100%;
  height: 100%;
}

.bigHeadline {
  width: 100%;
  height: 30px;
  line-height: 30px;
  font-size: 30px;
  text-align: left;
  margin-left: 15px;
  margin-bottom: 12px;
}

.smallHeadline {
  width: 100%;
  height: 30px;
  line-height: 30px;
  font-size: 18px;
  text-align: left;
  margin-left: 15px;
  margin-bottom: 22px;
  color: rgb(105, 192, 47);
}

.Headline_b {
  font-size: 30px;
}

#container {
  display: flex;
  flex-flow: row wrap;
  align-content: flex-start;
  justify-content: flex-start;
  
}
.product {
  width: 50%;
  height: 250px;
  flex: 0 0 20%;
  padding: 0 30px;
}

.productName {
  font-size: 26px;
  font-family: Arial, Helvetica, sans-serif;
  margin-bottom: 10px;
}

.introduction {
  font-size: 12px;
  margin: 0 15px;
  text-align: left;
  line-height: 20px;
}
</style>

<script>
export default {
  data() {
    return {
      productName: "云服务器 ECS",
      introduction:
        "云服务器 ECS是一种弹性可伸缩的计算服务，助您降低 IT 成本，提升运维效率，使您更专注于核心业务创新。",
    };
  },
  mounted() {},
  methods: {},
};
</script>