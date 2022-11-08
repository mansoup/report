<template>
<div>
    <div class="div_any">
    <div class="left div_any01">
    <div class="div_any_child">
<!--      第一个左上角-->
      <div class="div_any_title"><img src="../../../../static/public/images/title_1.png">各地级市采集数据量</div>
      <p id="main" class="p_chart"></p>
    </div>
<!--      左下角-->
    <div class="div_any_child">
      <div class="div_any_title"><img src="../../../../static/public/images/title_2.png">各市平均费用</div>
      <p id="histogramChart" class="p_chart"></p>
    </div>
    </div>
<!--      中间-->
    <div class="div_any02 left ">
      <div class="div_any_child div_height">
        <div class="div_any_title any_title_width"><img src="../../../../static/public/images/title_0.png">沈阳市地图</div>
        <div id="mapChart"
             style="width:97.5%;height:95%;display: inline-block;padding-left: 1.25%;padding-top:2.2%"></div>
      </div>
    </div>

    <div class="right div_any01">
      <!--      右上角-->
      <div class="div_any_child">
        <div class="div_any_title"><img src="../../../../static/public/images/title_3.png">新增人数(当日)</div>
        <p id="lineChart" class="p_chart"></p>
      </div>
<!--      右下角-->
      <div class="div_any_child">
        <div class="div_any_title"><img src="../../../../static/public/images/title_4.png">数据采集条数(当月)</div>
        <p id="lineChart2" class="p_chart"></p>
      </div>
    </div>
    </div>

  <div id="el-dialog" class="">
    <div class="xc_layer"></div>
    <div class="popBox" id="printView">
      <div class="ttBox"><span class="tt" id="reportTitle">动态改变值</span>
        <img src="../../../../static/public/images/close.png" style="width: 30px;float: right;cursor: pointer;" title="关闭弹窗" class="close"/>
      </div>
      <div class="txtBox" id="el-dialog_body">
        <div style="height:100%;width: 98%;margin-left: 1%;">
          <div class="left div_any01" style="width: 64%;">
            <div class="div_any_child">
              <div class="div_any_title"><div type="text" class="demo-input" id="date1" style="display: inline-block;cursor: pointer;margin-right: 16px;"></div><img src="../../../../static/public/images/title_4.png">工程详细信息</div>
<!--              <p id="lineChart3" class="p_chart"></p>-->
<!--              <el-row :gutter="20">-->
<!--                <el-col :span="6" v-for="(val, key, index) in thisproject" :key="index"><div class="grid-content bg-purple">{{key}}&#45;&#45;{{val}}</div></el-col>-->
<!--              </el-row>-->
              <!--涉及两个表,工程表和投资表,后期在分表之后可以进行单独请求而不是直接用总表-->
              <el-descriptions class="margin-top"  title="   " label-class-name="mybg" :column="3"  border>
                <el-descriptions-item label-class-name="mybg">
                  <template slot="label">
                    <i class="el-icon-user"></i>
                    工程名字
                  </template>
                  {{thisproject.supplyWaterProjectName}}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-mobile-phone"></i>
                    服务电话
                  </template>
                  {{thisproject.servicePhoneNumbers}}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-location-outline"></i>
                    工程地址
                  </template>
                  {{thisproject.location}}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-location-outline"></i>
                    填报年份
                  </template>
                  {{thisproject.writeTime}}
                </el-descriptions-item>
                <el-descriptions-item v-if="thisproject.projectSituation=='已建'">
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    工程状态
                  </template>
                  <el-tag size="small">{{thisproject.projectSituation}}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item v-else-if="thisproject.projectSituation=='在建'">
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    工程状态
                  </template>
                  <el-tag size="small" type="warning">{{thisproject.projectSituation}}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item v-else="thisproject.projectSituation">
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    工程状态
                  </template>
                  <el-tag size="small" type="danger">{{thisproject.projectSituation}}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    地表水源名
                  </template>
                {{thisproject.surfaceWaterName}}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    设计供水量
                  </template>
                  {{thisproject.designSupplyScale}}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    年实际供水量
                  </template>
                 {{thisproject.actualSupplyWaterAnnual}}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    管网漏损率
                  </template>
                  {{thisproject.pipeLackRate}}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    供水保证率(%)
                  </template>
                  {{thisproject.supplyWaterRate}}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    工程总投资(万)
                  </template>
                  {{thisproject.totalInvest}}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    国家投资(万)
                  </template>
                  {{thisproject.stateInvest}}
                </el-descriptions-item>
                <el-descriptions-item v-if="thisproject.startSupplyWaterTime">
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    开始供水时间
                  </template>
                  {{thisproject.startSupplyWaterTime.substring(0,10)}}
                </el-descriptions-item>
                <el-descriptions-item v-else>
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    开始供水时间
                  </template>
                  {{thisproject.startSupplyWaterTime}}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    是否竣工
                  </template>
                  {{thisproject.isFinishProject}}
                </el-descriptions-item>
              </el-descriptions>


            </div>
            <div class="div_any_child">
              <div class="div_any_title"><div type="text" class="demo-input" id="date2" style="display: inline-block;cursor: pointer;margin-right: 16px;"></div><img src="../../../../static/public/images/title_4.png">负责人信息</div>
<!--              <p id="lineChart4" class="p_chart"></p>-->
              <el-descriptions class="margin-top"  title="   " label-class-name="mybg" :column="3"  border>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    负责人住址
                  </template>
                  {{thisproject.address}}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    负责人职位
                  </template>
                  {{thisproject.position}}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    负责人名字
                  </template>
                  {{thisproject.supervisorName}}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-tickets"></i>
                    负责人号码
                  </template>
                  {{thisproject.servicePhoneNumbers}}
<!--                  {{thisproject.imgUrl}}-->
                </el-descriptions-item>
              </el-descriptions>
            </div>
          </div>
          <div class="left div_any01"  style="width: 32%;">
            <div class="div_any_child">
              <div class="div_any_title">
                <div type="text" class="demo-input" id="date3"
                     style="display: inline-block;cursor: pointer;margin-right: 16px;"></div>
                <img src="../../../../static/public/images/title_7.png">
                工程投入信息
              </div>
              <p id="pieChart2" class="p_chart"></p>
            </div>
            <div class="div_any_child">
              <div class="div_any_title">
                <div type="text" class="demo-input" id="date4"
                     style="display: inline-block;cursor: pointer;margin-right: 16px;">
                </div>
                <img src="../../../../static/public/images/title_20.png">水源信息
              </div>
              <el-image :src="thisproject.imgUrl" style="width: 448px; height: 288px"></el-image>
              <p>{{thisproject.imgUrl}}</p>


              <p id="pieChart3" class="p_chart"></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</div>

</template>
<script>
//添加百度地图 https://www.cxyzjd.com/article/weixin_43893437/95765573

import request from '@/utils/request'
import echarts from  'echarts'
import 'echarts/extension/bmap/bmap.js'
// import "static/public/styles/common.css"
//import {test} from "@/api/test";
export default {

  data() {
    return {
      opinionData:[],
      thisproject:[],
      projectInfo:[],//工程的各类信息
      projectLocationInfo:[],
      dta:[ [
        [12, 88, 200, '辽宁大学', 1990],
        [10, 60, 150, '东北大学', 1990],
        [8, 98, 185, '大连理工大学', 1990],
        [25, 80, 500, '沈阳大学', 1990],
        [30, 50, 800, '沈阳师范大学', 1990],
        [10, 40, 80, '沈阳航空大学', 1990],
      ],
      ]
      //   [{
      //   longitude:'',
      //   latitude:'',
      //   supplyWaterProjectName:'',
      // }],//工程的经纬度和工程名称
    };
  },
  mounted() {
    this.test(), this.baidumap(), this.initmapProject()
      this.getProjectLocation()
      // if($("#el-dialog").removeClass('hide')){
      //   this.pies();
      // }

     this.$nextTick(function() {
        this.drawLine('main')
      })

  },
  created() {
    this.getProjectLocation()

  },
  methods: {
    pies(){
      var pieChart2 = echarts.init(document.getElementById('pieChart2'));
      pieChart2.setOption({
        color:["#32cd32","#ff7f50","#87cefa","#FD6C88","#4b5cc4","#faff72"],
        tooltip : {
          trigger: 'item',
          formatter: "{a}<br/>{b}<br/>{c}万元"
        },
        calculable : true,
        legend: {
          top: 'bottom',
          textStyle:{
              color:'white',
          },
        },
        series : [
          {
            name:'工程',
            type:'pie',
            radius : [20, 100],
            center : ['-10%', '50%'],
            roseType : 'area',
            x: '50%',
            sort : 'ascending',
            label: {
              normal: {
                show: false,
              },
            },
            labelLine: {
              normal: {
                show: false
              }
            },

            data:[
              {value:55, name:'人力费用'},
              {value:15, name:'耗材费'},
              {value:5, name:'检测费'},
              {value:35, name:'管理费'},
              {value:125, name:'材料费'},
            ]
          }
        ]
      })

      //pieChart3

    },
    drawLine(id) {
      this.charts = echarts.init(document.getElementById(id))
      this.charts.setOption({
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['近七日收益']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },

        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ["1","2","3","4","5"]

        },
        yAxis: {
          type: 'value'
        },

        series: [{
          name: '近七日收益',
          type: 'line',
          stack: '总量',
          data: this.opinionData
        }]
      })
    },
    baidumap(){
      var mapChart = echarts.init(document.getElementById('mapChart'));
      mapChart.setOption({
        title:{
          text:"辽宁省水利工程区位图",
          left:'center',
        },
        bmap: {
          center: [123.38,41.88],
          zoom: 7,//控制百度地图缩放大小
          roam: true,//是否开启鼠标缩放和平移漫游
        },
        tooltip : {
          trigger: 'item',
          formatter:function(params, ticket, callback){
            return params.value[2]
          }
        },
        series: [{
          type: 'scatter',
          coordinateSystem: 'bmap',
          data: this.projectLocationInfo,
          //   [
          //   //地图红点显示提示
          //   [ 123.45648433384034,41.79885212450066,  '沈阳市'] ,
          //   [ 123.40575736988242,41.83545867322391, '辽宁大学'] ,
          //   [ 123.33404906144175,41.854686065935105, '丁香湖'],
          //   [ 123.6788915377512,41.94473619680818, '秀湖'],
          //   [ 123.59054921520654,41.84239421806109, '东陵'],
          //   // [118, 24, '厦门第六医院'],
          // ]
        }]
      });

      $("#el-dialog").addClass("hide");
      $(".close").click(function(event) {
        $("#el-dialog").addClass("hide");
      });
      mapChart.on('click', (params)=>{
        $("#el-dialog").removeClass('hide');
        this.pies();
        $("#reportTitle").html(params.value[2]);
        // console.log("params.value"+params.value);
        //遍历工程对象数组，找到点击的工程数组
        // console.log("this.projectInfo"+this.projectInfo[0].longitude);
        for (let i = 0; i <this.projectInfo.length ; i++) {
          console.log("this.thisproject.longitude"+this.projectInfo[i].longitude+"params.value[0]"+params.value[0]+"this.projectInfo[i].latitude"+this.projectInfo[i].latitude+"params.value[1]"+params.value[1]);
          if(params.value[0]==this.projectInfo[i].longitude&&params.value[1]==this.projectInfo[i].latitude&&params.value[2]==this.projectInfo[i].supplyWaterProjectName){
            //找到该工程了
            this.thisproject=this.projectInfo[i];

            // console.log("this.thisproject.totalInvest"+new Array.form(this.thisproject));
          }
        }
        let a= Object.values(this.thisproject);
        console.log("this.thisproject.totalInvest"+a);

      });

      //百度地图得配置
      var bmap = mapChart.getModel().getComponent('bmap').getBMap()
      bmap.addControl(new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_SATELLITE_MAP ]}));
      bmap.setMapStyle({style:'day'})
    },

    test(){
         /**
          @Author soup
          @Date 2022/03/25
          @Description 这里通过axios直接访问数据，是个好例子，思考跳过专门写 js代码进行访问
          **/
         // request({
         //   url: '/mapreport/queryAllUser',
         //   method: 'GET',
         // }).then((response) => {
         //   // console.log("response"+response.data[0].age)
         //   //我把user表删除了，所以出现前端异常提示
         //   for (let i = 0; i <response.data.length ; i++) {
         //     this.opinionData[i]=parseInt(response.data[i].age);
         //     // console.log("this.opinionData[i]"+this.opinionData)
         //   }
         //   this.drawLine('main');//更新数据，不然不会显示
         //   console.log("this.opinionData"+this.opinionData)
         //   if (response.code != '200') {
         //     return
         //   }
         // })
    },
    getProjectLocation(){
      /**
       @Author soup
       @Date 2022/03/25
       @Description 这里通过axios直接访问数据，是个好例子，思考跳过专门写 js代码进行访问
       **/
      request({
        url: '/waterProject/queryAllProject',
        method: 'GET',
      }).then((response) => {
        // console.log("response"+response.data[0].supplyWaterProjectName)
        this.projectInfo= response.data;
        // console.log("this.projectInfo.supplyWaterProjectName"+this.projectInfo[0].supplyWaterProjectName);
        // this.projectLocationInfo = response.data;
        // console.log("this.projectLocationInfo"+new Array(this.projectLocationInfo));
        console.log("未赋值前this.projectLocationInfo"+" "+this.projectLocationInfo);
        const arr= [];
        for (let i = 0; i <response.data.length ; i++) {
          const {longitude,latitude,supplyWaterProjectName,id} = response.data[i]
          arr.push([longitude,latitude,supplyWaterProjectName,id])
        }
        this.projectLocationInfo = arr;
        // console.log("id"+this.projectLocationInfo[0][3]);
        this.baidumap()//更新数据，不然不会显示
        // console.log("this.projectLocationInfo"+" "+this.projectLocationInfo);
        // console.log("this.opinionData"+this.opinionData)
        if (response.code != '200') {
          return
        }
      })
    },
    initmapProject(){
      var histogramChart = echarts.init(document.getElementById('histogramChart'));
      histogramChart.setOption({
        title: {
          subtext: '',
          left: 'center'
        },
        tooltip: {
          formatter: "{a}<br/>{b}<br/>{c}万元",
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '平均投资',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 55, name: '沈阳市' },
              { value: 35, name: '丹东' },
              { value: 50, name: '大连' },
              { value: 30, name: '锦州' },
              { value: 20, name: '葫芦岛' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      })


      var lineChart2= echarts.init(document.getElementById('lineChart2'));
      lineChart2.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '',
            type: 'bar',
            barWidth: '60%',
            data: [10, 52, 200, 334, 390, 330, 220]
          }
        ]
      })


      var lineChart= echarts.init(document.getElementById('lineChart'));
      lineChart.setOption({
        backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [
          {
            offset: 0,
            color: '#f7f8fa'
          },
          {
            offset: 1,
            color: '#cdd0d5'
          }
        ]),
        title: {
          text: '工程造价比与满意度关系',
          left: '5%',
          top: '3%'
        },
        legend: {
          right: '10%',
          top: '3%',
          data: ['1990', '2015']
        },
        grid: {
          left: '8%',
          top: '10%'
        },
        xAxis: {
          splitLine: {
            lineStyle: {
              type: 'dashed'
            }
          }
        },
        yAxis: {
          splitLine: {
            lineStyle: {
              type: 'dashed'
            }
          },
          scale: true
        },
        series: [
          {
            name: '1990',
            data: this.dta[0],
            type: 'scatter',
            symbolSize: function (data) {
              // return Math.sqrt(data[2]) / 5e2;
              return Math.sqrt(data[2]);
            },
            emphasis: {
              focus: 'series',
              label: {
                show: true,
                formatter: function (param) {
                  return param.data[3];
                },
                position: 'top'
              }
            },
            itemStyle: {
              shadowBlur: 10,
              shadowColor: 'rgba(120, 36, 50, 0.5)',
              shadowOffsetY: 5,
              color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [
                {
                  offset: 0,
                  color: 'rgb(251, 118, 123)'
                },
              ])
            }
          },
        ]
      })
    }
  },



};
</script>

<!--/**-->
<!--@Author soup-->
<!--@Date 2022/04/09-->
<!--@Description  增加style-->
<!--**/-->
<style scoped lang="scss">
@import "../../../../static/public/styles/common.css";

.page-container {
  height: 100%;
  height: 100%;
  position: relative;
}
.div_any_child{
  width: 100%;
  height: 330px;
  box-shadow: -10px 0px 15px white inset, /*å·¦è¾¹é˜´å½±*/
  0px -10px 15px #white inset, /*ä¸Šè¾¹é˜´å½±*/
  10px 0px 15px #white inset, /*å³è¾¹é˜´å½±*/
  0px 10px 15px #white inset;
  border: 1px solid white;
  box-sizing: border-box;
  position: relative;
  margin-top: 25px;
}
.div_any_title{
  background-color: #034c6a;
  border-radius: 18px;
  position: absolute;
  height: 35px;
  width: 60%;
  top:-15px;
  color: #ffffff;
  font-weight: bold;
  font-size: 16px;
  left: 20%;
  line-height: 35px;
  text-align: center;
}
.p_chart{
  height: 288px;

  padding: 5px 10px;
  margin-top: 15px;
}
.mybg{
  background: #034c6a;
}
//.hide{
//  display: none !important;
//}
</style>
