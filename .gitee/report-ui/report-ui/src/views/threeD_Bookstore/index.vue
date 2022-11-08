<template>
  <div >
  <body id="world" >
<!--  <el-button @click="load">hahah</el-button>-->

  </body>
<!--    <script src="js/three.min.js"></script>-->
<!--    <script src="js/OrbitControls.js"></script>-->
<!--    <script src="js/Tween.js"></script>-->
<!--    <script src="js/3dBookcase.js"></script>-->

  </div>
</template>

<script>

// import * as THREE from "@/views/threeD_Bookstore/js/three.min.js"
// import * as THREE from 'three'
// import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js'

// import { OrbitControls } from "three/examples/jsm/controls/OrbitControls";
// import {TWEEN} from "@/views/threeD_Bookstore/js/Tween.js"
// import {TWEEN} from "@tweenjs/tween.js"
// import {onLoad} from "@/views/threeD_Bookstore/js/3dBookcase.js"
import {onLoad} from "@/api/3dBookcase"

export default {
  name: "bookstore",
  mounted(){
    onLoad();
  },
  methods:{
    load(){
      // onLoad();
    }
  }

}
</script>

<style scoped>
.body{
  margin: 0;
  padding:0;
  box-sizing: border-box;
  overflow: hidden;
  background: #3c763d;
}
canvas{

}
</style>
