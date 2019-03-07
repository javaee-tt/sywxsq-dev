//app模版  pagination是分页插件
var mymodule=angular.module("myapp",["pagination"]);

//controller层  $interval用法就是每间隔多少秒执行一次函数中的代码
mymodule.controller("11x5Controller",function ($scope,$http,$interval) {

    $scope.entity={};//初始化

    //解决分页插件二次触发的问题
    $scope.reload = true;

    //分页查询配置 (这个对象配置原本就是已经配置好的,不是我们写的)一进页面就查询第一页.
    $scope.paginationConf={
        currentPage:1,//当前页码
        totalItems:10,//总记录条数
        itemsPerPage:10,//每页记录数
        perPageOptions:[10,20,30,40,50],//分页选项,下拉选择一页多少条记录
        onChange:function(){//更改页面时触发事件
            if(!$scope.reload) {
                return;
            }
            $scope.reloadList();//数据变更就重新加载分页查询
            $scope.reload = false;
            setTimeout(function() {
                $scope.reload = true;
            }, 200);
        }};

    //分页查询全部11选5
    $scope.findAllImages=function (pageNumber,pageSize) {
        $http.post("../ElevenToFiveController/findAllElevenToFive?pageNumber="+pageNumber+"&"+"pageSize="+pageSize).success(function (response) {
            if(response.success){
                //总记录条数--设置分页配置里面的参数$scope.paginationConf
                $scope.paginationConf.totalItems=response.pageResult.total;
                $scope.list=response.pageResult.rows;

                //页面展示的开奖号码:
                var result=response.elevenToFive.numberResult;
                //JSON格式转换成数组
                var parse = JSON.parse(result);
                //获取值
                $scope.parseOne = parse[0];
                $scope.parsetwo = parse[1];
                $scope.parseThree = parse[2];
                $scope.parseFour = parse[3];
                $scope.parseFive = parse[4];

                //如果下一轮开始时间不等于空
                if(response.elevenToFive.startTime!=null){

                    //计算出下一轮开奖时间
                    var startTime=new Date(response.elevenToFive.startTime).getTime();//下一轮开始时间毫秒值
                    var nowTime = new Date().getTime();//获取当前时间毫秒值


                        //剩余时间 (开奖时间-当前时间)/1000 等于秒值 let是局部变量 不能用$scope定义(解决浏览器跳3秒的问题)
                        let secondes = Math.floor((startTime-nowTime)/1000);//毫秒值转换成秒值
                        //定时器  参数1:执行的函数 参数2:每1秒执行一次

                        var time=$interval(function () {
                            if(secondes>0){ //如果剩余时间大于0
                                //时间递减
                                secondes--;
                                //时间格式化(自定义的方法)
                                $scope.timeString=$scope.convertTimeString(secondes);
                            }else{//结束时间递减
                                $interval.cancel(time);//如果剩余时间不大于0则停止定时器
                                setTimeout(function() {
                                    $scope.reloadList();//数据变更就重新加载分页查询
                                }, 2000);//2秒后重新调用后台代码 setTimeout() 方法用于在指定的毫秒数后调用函数或计算表达式。
                            }},1000);

                }else {
                    //自定义字符串
                    $scope.timeString="开奖时间08:00-23:00"
                }
            }else {
                response.message;
            }
        }).error(function (response) { //错误异常
            alert(response.message);//弹窗提示
        })};

    // 如果数据变更就重新加载  分页查询方法(请求/响应)
    $scope.reloadList=function(){
        ////分页查询方法(请求/响应)参数1:当前页码参数2:当前页有多少条数据
        $scope.findAllImages($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage)
    };

    //抽取时间格式化方法 参数是: 总秒值
    $scope.convertTimeString=function (allseconds) {
        //计算天数
        var days = Math.floor(allseconds/(60*60*24));
        //小时
        var hours =Math.floor( (allseconds-(days*60*60*24))/(60*60) );
        //分钟
        var minutes = Math.floor( (allseconds-(days*60*60*24)-(hours*60*60))/60 );
        //秒
        var seconds = allseconds-(days*60*60*24)-(hours*60*60)-(minutes*60);

        //拼接时间字符串
        var timString="";

        if(days>0){ //如果天数大于0 就显示当前天数
            timString=days+"天:";
        }
        if(hours<10){ //如果小时小于10小时,则十位数显示0
            hours="0"+hours;
        }
        if(minutes<10){ //如果分钟小于10分钟,则十位数显示0
            minutes="0"+minutes;
        }
        if(seconds<10){ //如果秒数小于10秒,则十位数显示0
            seconds="0"+seconds;
        }
        return timString+=hours+":"+minutes+":"+seconds;
    };

    //初始化默认值
    $scope.toFiveValue=[];
    //初始化默认值
    $scope.toFiveIds=[];
    //初始化多选值
    $scope.toFiveList={
        data:[{id:1,text:"1"},{id:2,text:"2"},{id:3,text:"3"},{id:4,text:"4"},{id:5,text:"5"},
        {id:6,text:"6"},{id:7,text:"7"},{id:8,text:"8"},{id:9,text:"9"},{id:10,text:"10"},{id:11,text:"11"}]};

    //设置自定义的出奖值
    $scope.setElEleventToFiveValue=function (){
        let toFiveIdsLength = $scope.toFiveIds;
        //已选择的长度
        let length = toFiveIdsLength.length;
        if(length>5){
            //长度太长
            return alert("您已设置:["+length+"]位数,已超出限制,请删除["+(length-5)+"]位数")
        }
        if(length!=5){
            //长度太短
            return alert("您已设置:["+length+"]位数,请再设置"+(5-length)+"位出奖数值");
        }
            //页面回显数据
            $scope.toFiveValue.parseOne=$scope.toFiveIds[0].id;
            $scope.toFiveValue.parsetwo=$scope.toFiveIds[1].id;
            $scope.toFiveValue.parseThree=$scope.toFiveIds[2].id;
            $scope.toFiveValue.parseFour=$scope.toFiveIds[3].id;
            $scope.toFiveValue.parseFive=$scope.toFiveIds[4].id;

            //把值传给后台
            $http.post("../ElevenToFiveController/setElEleventToFiveValue?" +
                "parseOne="+$scope.toFiveValue.parseOne+
                "&parsetwo="+$scope.toFiveValue.parsetwo+
                "&parseThree="+$scope.toFiveValue.parseThree+
                "&parseFour="+$scope.toFiveValue.parseFour+
                "&parseFive="+$scope.toFiveValue.parseFive
            ).success(function (response) {
                if (response.success){
                    alert(response.message);
                }else {
                    alert(response.message);
                }}).error(function (response) {
                alert(response.message);
            })};


});

//angular.js整合select2
mymodule.directive('select2', function () {
    return {
        restrict: 'A',
        scope: {
            config: '=',
            ngModel: '=',
            select2Model: '='
        },
        link: function (scope, element, attrs) {
            // 初始化
            var tagName = element[0].tagName,
                config = {
                    allowClear: true,
                    multiple: !!attrs.multiple,
                    placeholder: attrs.placeholder || ' '   // 修复不出现删除按钮的情况
                };

            // 生成select
            if(tagName === 'SELECT') {
                // 初始化
                var $element = $(element);
                delete config.multiple;

                angular.extend(config, scope.config);
                $element
                    .prepend('<option value=""></option>')
                    .val('')
                    .select2(config);

                // model - view
                scope.$watch('ngModel', function (newVal) {
                    setTimeout(function () {
                        $element.find('[value^="?"]').remove();    // 清除错误的数据
                        $element.select2('val', newVal);
                    },0);
                }, true);
                return false;
            };

            // 处理input
            if(tagName === 'INPUT') {
                // 初始化
                var $element = $(element);

                // 获取内置配置
                if(attrs.query) {
                    scope.config = select2Query[attrs.query]();
                }

                // 动态生成select2
                scope.$watch('config', function () {
                    angular.extend(config, scope.config);
                    $element.select2('destroy').select2(config);
                }, true);

                // view - model
                $element.on('change', function () {
                    scope.$apply(function () {
                        scope.select2Model = $element.select2('data');
                    });
                });

                // model - view
                scope.$watch('select2Model', function (newVal) {
                    $element.select2('data', newVal);
                }, true);

                // model - view
                scope.$watch('ngModel', function (newVal) {
                    // 跳过ajax方式以及多选情况
                    if(config.ajax || config.multiple) { return false }

                    $element.select2('val', newVal);
                }, true);
            }}};
});