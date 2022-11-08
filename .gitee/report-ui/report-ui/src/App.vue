<template>
  <div id="app">
<!--    <router-view>是内置组件 @soup 2022/1/6 增加注释 -->
    <router-view v-if="isRouterAlive" />
  </div>
</template>

<script>
import "@/assets/iconfont/iconfont.css";
import { initDictToLocalstorage } from "@/api/dict-data";
export default {
  name: "App",
  provide() {
    return {
      reload: this.reload
    };
  },
  data() {
    return {
      isRouterAlive: false
    };
  },
  watch: {
    $route(to, form) {
      if (to.path == "/login") {
        this.queryDictName();
      }
    }
  },
  computed: {},
  created() {
    this.queryDictName();
  },
  methods: {
    queryDictName() {
      // 初始化数据字典到浏览器本地缓存
      initDictToLocalstorage(() => {
        this.isRouterAlive = true;
      });
    },
    reload() {
      this.isRouterAlive = false;
      this.$nextTick(function() {
        this.isRouterAlive = true;
      });
    }
  }
};
</script>
