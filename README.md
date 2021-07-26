<div align="center">

#  云业务综合运营支撑平台

**这是一个让人烦躁躁的项目**

</div>

### 前端：Vue 3 + vite + elementUI

### 后端：Java Spring MVC + MySQL

## 介绍

根据移动集团相关规范并结合某省分公司云移动业务与技术现状及发展需要，拟规划建立起一套在云业务能力上达到全网、灵活、高效、自动化；在技术能力上达到集中、统一、可靠、快速的，用于第三方用户能在线体验、发布、购买的云业务超市以及在线云体验，内部管理人员统一企业工作台办公的综合运营管理平台。

## 构成


```bash
Operation-Management-Platform
├── backend                              //后端
|  ├── lib
|  ├── src
|  └── target
├── client-frontend                     //用于第三方用户体验的前端
|  ├── public
|  └── src
└── manage-frontend                     //用于内部管理人员使用的前端
   ├── public
   └── src
```
## 运行

###  开发环境

前端：Node.js

后端：IntelliJ IDEA 2021.1 + MySQL 5.5.49 + Apache Maven 3.8.1 + Apache Tomcat 9.0.48

###  运行方式

- 前端运行方式，如：

```bash
cd client-frontend
npm i
npm run dev
```

- 设置端口和代理 `vite.config.ts`
- 设置API `src`/`api`/`index.ts`

**默认端口** 

- 后端：`8080`，客户端前端：`8090`，管理端前端：`9000`



