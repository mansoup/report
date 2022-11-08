<template>
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">
      <!-- or name="fade" -->
      <!-- <router-view :key="key"></router-view> -->
<!--   @soup 2022/3/17   keep-alive是一个抽象组件：它自身不会渲染一个DOM元素，也不会出现在父组件链中；使用keep-alive包裹动态组件时，会缓存不活动的组件实例，而不是销毁它们 include定义缓存白名单-->
      <keep-alive :include="cacheViews">
        <router-view></router-view>
      </keep-alive>
    </transition>
  </section>
</template>

<script>
import {mapGetters} from "vuex"
export default {
  name: 'AppMain',
  computed: {
    ...mapGetters(['cacheViews']),
    key() {
      return this.$route.name !== undefined ? this.$route.name + +new Date() : this.$route + +new Date()
    }
  }
}
</script>

<style scoped>
.app-main {
  /*50 = navbar  */
  height: calc(100vh - 50px);
  position: relative;
  overflow: auto;
  background: #f5f8fa;
}
</style>
