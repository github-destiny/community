(function(){
    var myChart = echarts.init(document.querySelector(".bar .chart"));
    var option = {
        color : ['#2f89cf'],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        // 修改图表大小
        grid: {
          left: '0%',
          top: '10px',
          right: '0%',
          bottom: '4%',
          containLabel: true
        },
        // 修改x坐标轴
        xAxis: [
          {
            type: 'category',
            data: [ "旅游行业","教育培训", "游戏行业", "医疗行业", "电商行业", "社交行业", "金融行业" ],
            axisTick: {
              alignWithLabel: true
            },
            // axisLabel
            axisLabel : {
                color: ' rgba(255,255,255,.6)',
                fontSize: 8
            },
            axisLine: {
                show: false
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            // axisLabel
            axisLabel : {
                color: ' rgba(255,255,255,.6)',
                fontSize: '12'
            },
            axisLine: {
                show: true,
                lineStyle : {
                    color: 'rgba(255,255,255,.1)'
                }
            },
            splitLine : {
                lineStyle : {
                    color: 'rgba(255,255,255,.1)'
                }
            }
          }
        ],
        series: [
          {
            name: 'Direct',
            type: 'bar',
            barWidth: '35%',
            data: [200, 300, 300, 900, 1500, 1200, 600],
            itemStyle : {
                barBorderRadius: 5
            }
          }
        ]
      };
      myChart.setOption(option);
      // 图标自适应
      window.addEventListener('resize', function(){
          myChart.resize();
      })
})();
// 柱状图2
(function(){
    // 声明颜色数组
    var myColor = ["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"];
    var myChart = echarts.init(document.querySelector('.bar1 .chart'));
    var option = {
        grid: {
            top: "10%",
            left: "22%",
            bottom: "10%",

        },
        xAxis: {
          show: false,
        },
        yAxis:[
            {
                type: 'category',
                inverse: true,
                data: ["HTML5", "CSS3", "JAVASCRIPT", "VUE", "NODE"],
                axisLine: {
                    show: false,
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    color: '#fff'
                }
            },
            {
                show: true,
                inverse: true,
                data:[702, 350, 610, 793, 664],
                // 不显示y轴的线
                axisLine: {
                    show: false
                },
                // 不显示刻度
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        fontSize: 12,
                        color: "#fff"
                    }
                }
            }
        ],
        series: [
            {
                name: '条',
                type: 'bar',
                data: [70, 34, 60, 78, 69],
                // 设置层级
                yAxisIndex: 0,
                itemStyle: {
                    barBorderRadius: 20,
                    color: function(params){
                        return myColor[params.dataIndex]
                    }
                },
                // 柱子之间的距离
                barCategoryGap: 50,
                //柱子的宽度
                barWidth: 10,
                // 显示在柱状图上的信息
                label: {
                    show: true,
                    position: 'inside',
                    formatter: '{c}%'
                }
            },
            {
                name: '框',
                type: 'bar',
                yAxisIndex: 1,
                data: [100, 100, 100, 100, 100],
                barCategoryGap: 50,
                barWidth: 15,
                itemStyle: {
                    color: "none",
                    borderColor: "#00c1de",
                    borderWidth: 3,
                    barBorderRadius: 15
                },
            }
        ]
    };
    myChart.setOption(option);
    window.addEventListener('resize', function(){
        myChart.resize();
    })
})();
// 折线图左侧
(function(){
    var yearData = [
        {
            year: '2020',  // 年份
            data: [  // 两个数组是因为有两条线
                [24, 40, 101, 134, 90, 230, 210, 230, 120, 230, 210, 120],
                [40, 64, 191, 324, 290, 330, 310, 213, 180, 200, 180, 79]
            ]
        },
        {
            year: '2021',  // 年份
            data: [  // 两个数组是因为有两条线
                [123, 175, 112, 197, 121, 67, 98, 21, 43, 64, 76, 38],
                [143, 131, 165, 123, 178, 21, 82, 64, 43, 60, 19, 34]
            ]
        }
    ];
    var myChart = echarts.init(document.querySelector('.line .chart'));
    var option = {
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['新增粉丝', '新增游客'],
          textStyle: {
              color: '#4c9bfd'
          },
          right: '10%'
        },
        grid: {
            top: '20%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            show: true,// 显示边框
            borderColor: '#012f4a',// 边框颜色
            containLabel: true // 包含刻度文字在内
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
          axisTick: {
            show: false // 去除刻度线
          },
          axisLabel: {
            color: '#4c9bfd' // 文本颜色
          },
          axisLine: {
            show: false // 去除轴线
          },
          boundaryGap: false  // 去除轴内间距
        },
        yAxis: {
          type: 'value',
          axisTick: {
            show: false  // 去除刻度
          },
          axisLabel: {
            color: '#4c9bfd' // 文字颜色
          },
          splitLine: {
            lineStyle: {
              color: '#012f4a' // 分割线颜色
            }
          }
        },
        color: ['#00f2f1', '#ed3f35'],
        series: [
          {
            name: '新增粉丝',
            type: 'line',
            stack: 'Total',
            smooth: true,
            data:  [24, 40, 101, 134, 90, 230, 210, 230, 120, 230, 210, 120],
          },
          {
            name: '新增游客',
            type: 'line',
            stack: 'Total',
            smooth: true,
            data: [40, 64, 191, 324, 290, 330, 310, 213, 180, 200, 180, 79],
          },
        ]
      };
      myChart.setOption(option);
      window.addEventListener('resize', function(){
          myChart.resize();
      })
      // 点击按钮切换数据
      $('.line h2').on('click', 'a', function(){
          var obj = yearData[$(this).index()];
          option.series[0].data = obj.data[0];
          option.series[1].data = obj.data[1];
          // 重新渲染
          myChart.setOption(option);
      })
})();
// 折线图2
(function(){
    var myChart = echarts.init(document.querySelector('.line1 .chart'));
    var option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },
        legend: {
          top: '0%',
          textStyle: {
            color: "rgba(255,255,255,.5)",
            fontSize: "12"
          }
        },
        grid: {
            left: "10",
            top: "30",
            right: "10",
            bottom: "10",
            containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: [ "01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","26","28","29","30"],
            axisLabel: {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: 12
                }
            },
            axisLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.2)"
                }
            },
          }
        ],
        yAxis: [
          {
            type: 'value',
            axisTick: { show: false },
            axisLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)"
                }
            },
            axisLabel: {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: 12
                }
            },
            // 修改分割线的颜色
            splitLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)"
                }
            }
          }
        ],
        series: [
          {
            name: 'Email',
            type: 'line',
            areaStyle: {},
            smooth: true,
            emphasis: {
              focus: 'series'
            },
            lineStyle: {
                color: '#0184d5',
            },
            // 设置拐点 小圆点
            symbol: "circle",
            // 拐点大小
            symbolSize: 8,
            // 设置拐点颜色以及边框
            itemStyle: {
                color: "#0184d5",
                borderColor: "rgba(221, 220, 107, .1)",
                borderWidth: 12
            },
            // 填充区域
            areaStyle: {
                // 渐变色，只需要复制即可
                color: new echarts.graphic.LinearGradient(
                    0,
                    0,
                    0,
                    1,
                    [
                        {
                            offset: 0,
                            color: "rgba(1, 132, 213, 0.4)"   // 渐变色的起始颜色
                        },
                        {
                            offset: 0.8,
                            color: "rgba(1, 132, 213, 0.1)"   // 渐变线的结束颜色
                        }
                    ],
                    false
                ),
                shadowColor: "rgba(0, 0, 0, 0.1)"
            },
            // 设置拐点 小圆点
            symbol: "circle",
            // 拐点大小
            symbolSize: 8,
            // 设置拐点颜色以及边框
            itemStyle: {
                color: "#0184d5",
                borderColor: "rgba(221, 220, 107, .1)",
                borderWidth: 12
            },
            // 开始不显示拐点， 鼠标经过显示
            showSymbol: false,
            data: [ 30, 40, 30, 40,30, 40, 30,60,20, 40, 30, 40, 30, 40,30, 40, 30,60,20, 40, 30, 40, 30, 40,30, 40, 20,60,50, 40],
          },
          {
            name: 'Union Ads',
            type: 'line',
            areaStyle: {},
            smooth: true,
            emphasis: {
              focus: 'series'
            },
            data: [ 130, 10, 20, 40,30, 40, 80,60,20, 40, 90, 40,20, 140,30, 40, 130,20,20, 40, 80, 70, 30, 40,30, 120, 20,99,50, 20],
            lineStyle: {
                normal: {
                  color: "#00d887",
                  width: 2
                }
               },
               areaStyle: {
                normal: {
                  color: new echarts.graphic.LinearGradient(
                    0,
                    0,
                    0,
                    1,
                    [
                      {
                        offset: 0,
                        color: "rgba(0, 216, 135, 0.4)"
                      },
                      {
                        offset: 0.8,
                        color: "rgba(0, 216, 135, 0.1)"
                      }
                    ],
                    false
                  ),
                  shadowColor: "rgba(0, 0, 0, 0.1)"
                }
              },
              // 设置拐点 小圆点
              symbol: "circle",
              // 拐点大小
              symbolSize: 5,
              // 设置拐点颜色以及边框
               itemStyle: {
                  color: "#00d887",
                  borderColor: "rgba(221, 220, 107, .1)",
                  borderWidth: 12
              },
              // 开始不显示拐点， 鼠标经过显示
              showSymbol: false,
          },
        ]
      };
      myChart.setOption(option);
      window.addEventListener('resize', function(){
          myChart.resize();
      })
})();
// 饼图左侧
(function(){
    var myChart = echarts.init(document.querySelector('.pie .chart'));
    var option = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          bottom: '0%',
          itemWidth: 10,
          itemHeight: 10,
          textStyle: {
              color: 'rgba(255,255,255,.5)',
              fontSize: 12
          },
          data: ["0-17岁", "18-45岁", "46-69岁", "70岁及以上"],
        },
        color: [
            "#065aab",
            "#066eab",
            "#0682ab",
            "#0696ab",
            "#06a0ab",
        ],
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: ['40%', '60%'],
            center: ['50%', '50%'],
            avoidLabelOverlap: false,
            labelLine: {
              show: false
            },
            label: {
                show: false,
                position: 'center'
            },
            data: [

            ]
          }
        ]
      };
    $.get('/resident/grouping/age', function (data) {
        // for (let i = 0; i < data.length; i++) {
        //     var obj = {};
        //     obj.name = data[i].name;
        //     obj.value = data[i].value;
        //     console.log(option);
        //     option.series[0].data.push(obj);
        // }
        option.series[0].data = data;
        myChart.setOption(option);
        console.log(option.series[0].data);
    })
    console.log("数据获取完毕", option.series[0].data);
      myChart.setOption(option);
      window.addEventListener('resize', function(){
        myChart.resize();
    })
})();
// 南丁格尔玫瑰图
(function(){
    var myChart = echarts.init(document.querySelector('.pie1 .chart'));
    var option = {
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          bottom: '0%',
          itemHeight: 10,
          itemWidth: 10,
          textStyle: {
              fontSize: 8,
              color: 'rgba(255,255,255,.5)'
          }
        },
        color: ['#006cff', '#60cda0', '#ed8884', '#ff9f7f', '#0096ff', '#9fe6b8', '#32c5e9', '#1d9dff'],
        series: [
          {
            name: '地区分布',
            type: 'pie',
            radius: ['10%', '70%'],
            center: ['50%', '50%'],
            roseType: 'radius',
            itemStyle: {
              borderRadius: 5
            },
            data: [
                { value: 20, name: '云南' },
                { value: 26, name: '北京' },
                { value: 24, name: '山东' },
                { value: 25, name: '河北' },
                { value: 20, name: '江苏' },
                { value: 25, name: '浙江' },
                { value: 30, name: '四川' },
                { value: 42, name: '湖北' }
            ],
            label: {
                fontSize: 10
            },
            labelLine: {
                length: 2
            }
          }
        ]
      };
      myChart.setOption(option);
      window.addEventListener('resize', function(){
        myChart.resize();
    })
})();