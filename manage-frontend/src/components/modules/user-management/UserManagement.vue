<template>
  <div id="userList">
    <div id="table-wrapper">
      <el-table
        ref="singleTable"
        :data="tableData"
        highlight-current-row
        @current-change="handleCurrentChange"
        style="width: 100%; height: 600px; font-size: 16px"
        :row-style="{ height: '78px' }"
      >
        <el-table-column property="user_id" label="用户id"> </el-table-column>
        <el-table-column property="username" label="用户名"> </el-table-column>
        <el-table-column property="permission_cn" label="用户权限">
        </el-table-column>
      </el-table>

      <div id="index-wrapper">
        <div class="index">
          <i class="el-icon-caret-left page" @click="page_up()"></i>
          <span class="page">{{ pageIndex }}</span>
          <i class="el-icon-caret-right page" @click="page_down()"></i>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
#userList {
  height: 100%;
  width: 100%;
  #table-wrapper {
    width: 100%;
    height: calc(100% - 65px);
    overflow: auto;
  }
  #index-wrapper {
    flex-grow: 1;
    flex-shrink: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    .index {
      font-size: 36px;
    }
    .page {
      margin: 0 10px 0 10px;
    }
    i {
      cursor: pointer;
      &:hover {
        color: #409eff;
        transition-duration: 0.2s;
        transition-timing-function: ease-in-out;
      }
    }
  }
}
</style>

<script>
import { _getUserList } from "../../../api/user/userList.ts";
export default {
  data() {
    return {
      tableData: [
        {
          user_id: "",
          username: "",
          permission: "",
          permission_cn: "",
        },
      ],
      permissionList: {
        0: "管理员",
        1: "业务员",
        9: "普通用户",
      },
      currentRow: null,
      pageIndex: 1,
    };
  },
  methods: {
    handleCurrentChange(val) {
      this.currentRow = val;
    },
    page_up() {
      if (this.pageIndex > 1) {
        this.pageIndex--;
        this.getUserList();
      }
    },
    page_down() {
      this.pageIndex++;
      this.getUserList();
    },
    getUserList() {
      console.log(this.limit);
      _getUserList(this.limit)
        .then((res) => {
          if (res.code === 0) {
            this.tableData = res.data.datalist;
            console.log(this.datalist);
            this.tableData.filter((item, i) => {
              this.tableData[i].permission_cn =
                this.permissionList[item.permission];
            });
          } else {
            console.log(res.data.datalist);
          }
        })
        .catch((err) => {
          console.log("获取用户列表失败！");
        });
    },
  },
  mounted() {
    this.getUserList();
  },
  computed: {
    limit() {
      let limit = {
        page_num_start: (this.pageIndex - 1) * 10,
        page_num_end: (this.pageIndex - 1) * 10 + 9,
      };
      return limit;
    },
  },
};
</script>
