<template>
  <div id="presentation">
    <div id="container"></div>
    <div id="banner" :style="{ backgroundImage: 'url(' + banner + ')' }">
      <div id="content">
        <div id="title">服务支持中心</div>
        <div id="second-title">
          为您提供专业的售前咨询服务，和7×24小时的售后支持服务，助您轻松上云
        </div>
      </div>
    </div>
    <div id="container-main">
      <div class="list-wrapper" id="permission">
        <div class="big-title">权限提升</div>
        <div class="list-container">
          <div class="list-item">
            <div class="title">客户</div>
            <div class="content">
              输入合同号或产品序列号，提升软件下载、文档、案例等权限
            </div>
          </div>
          <div class="list-item">
            <div class="title">伙伴工程师</div>
            <div class="content">与伙伴公司关联，提升技术支持相关权限</div>
          </div>
          <div class="list-item">
            <div class="title">产品订阅</div>
            <div class="content">设置产品的订阅内容，可收到内容更新通知</div>
          </div>
        </div>
      </div>
      <div class="list-wrapper" id="contact">
        <div class="big-title">联系支持</div>
        <div class="list-container">
          <div class="list-item">
            <div class="title">智能问答</div>
            <div class="content">
              智能、快捷地解决您的问题，与华为工程师实时在线交流
            </div>
          </div>
          <div class="list-item">
            <div class="title">技术论坛</div>
            <div class="content">产品技术问题咨询和交流</div>
          </div>
          <div class="list-item">
            <div class="title">我的问题单</div>
            <div class="content">在线提交问题单、查看问题处理进展</div>
          </div>
          <div class="list-item">
            <div class="title">变更管理</div>
            <div class="content">合作伙伴发起高风险实施方案评审</div>
          </div>
          <div class="list-item">
            <div class="title">现场维护</div>
            <div class="content">设合作伙伴接受、分发和处理工单</div>
          </div>
          <div class="list-item">
            <div class="title">服务热线</div>
            <div class="content">中国区：400-000-0000 support@yun.com</div>
          </div>
        </div>
      </div>

      <div class="list-wrapper" id="query">
        <div class="big-title">备件、License、维保、DOA查询</div>
        <div class="list-container">
          <div class="list-item">
            <div class="title">备件申请</div>
            <div class="content">合作伙伴在线提交备件申请、查看备件进展</div>
          </div>
          <div class="list-item">
            <div class="title">License申请</div>
            <div class="content">License激活与下载、维护操作以及任务管理</div>
          </div>
          <div class="list-item">
            <div class="title">备件查询</div>
            <div class="content">
              根据备件单号、问题单号、故障件SN查询备件进展
            </div>
          </div>
          <div class="list-item">
            <div class="title">DOA查询</div>
            <div class="content">查询DOA、差错货、货损退换货申请状态</div>
          </div>
          <div class="list-item">
            <div class="title">维保查询与购买</div>
            <div class="content">
              根据产品SN查询维保或保修内容，以及维保购买与咨询
            </div>
          </div>
          <div class="list-item">
            <div class="title">ServiceCare Portal</div>
            <div class="content">
              为您的公司提供设备存量管理、产品文档、技术服务请求等一站式自助服务平台
            </div>
          </div>
        </div>
      </div>
      <div class="list-wrapper" id="introduction">
        <div class="big-title">支持服务介绍</div>
        <div class="list-container">
          <div class="list-item">
            <div class="title">维保服务</div>
            <div class="content">支持助力客户积极预防、快速解决网络问题</div>
          </div>
          <div class="list-item">
            <div class="title">License申请</div>
            <div class="content">
              产品保修内容概览，含服务级别、保修市场、服务指南等
            </div>
          </div>
          <div class="list-item"></div>
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
import banner from "../../../assets/images/modules/presentation/banner-help.jpg";
export default {
  name: "Presentation",
  data() {
    return { banner };
  },
  components: {},
  methods: {},
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
    #contact {
      background-color: rgb(245, 245, 245);
      .title {
        border-bottom: 1px solid rgba(138, 138, 138, 0.8);
      }
    }
    #introduction {
      @extend #contact;
    }
    .list-wrapper {
      padding: 25px 40px 25px 40px;
      @media screen and(min-width:1080px) {
        padding-left: 10%;
        padding-right: 10%;
      }
      .big-title {
        font-size: 34px;
        text-align: center;
        margin-bottom: 15px;
      }
      .list-container {
        display: flex;
        flex-wrap: wrap;
      }
      .list-item {
        // line-height: 28px;
        box-sizing: border-box;
        padding: 10px;
        width: 33%;
        @media screen and(max-width:1080px) {
          width: 100%;
          padding-left: 10%;
          padding-right: 10%;
        }
        @media screen and(max-width:720px) {
          padding-left: 10px;
          padding-right: 10px;
        }
        .title {
          font-size: 18px;
          box-sizing: border-box;
          padding-bottom: 5px;
          cursor: pointer;
          &:hover {
            color: #409eff;
            transition-duration: 0.2s;
            transition-timing-function: ease-in-out;
          }
        }
        .content {
          margin-top: 10px;
          font-size: 15px;
          color: #8d8d8d;
        }
      }
    }
  }
  #footer {
    font-size: 15px;
    margin-top: 20px;
    text-align: center;
    line-height: 30px;
 
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