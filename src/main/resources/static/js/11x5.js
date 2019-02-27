//app模版  pagination是分页插件
var mymodule=angular.module("myapp",["pagination"]);

//controller层  $interval用法就是每间隔多少秒执行一次函数中的代码
mymodule.controller("11x5Controller",function ($scope,$http,$interval) {

    $scope.entity={};//初始化

    //分页查询配置 (这个对象配置原本就是已经配置好的,不是我们写的)一进页面就查询第一页.
    $scope.paginationConf={
        currentPage:1,//当前页码
        totalItems:10,//总记录条数
        itemsPerPage:10,//每页记录数
        perPageOptions:[10,20,30,40,50],//分页选项,下拉选择一页多少条记录
        onChange:function(){//更改页面时触发事件
            $scope.reloadList();//数据变更就重新加载分页查询
        }}

    //分页查询全部11选5
    $scope.findAllImages=function (pageNumber,pageSize) {
        $http.post("../ElevenToFiveController/findAllElevenToFive?pageNumber="+pageNumber+"&"+"pageSize="+pageSize).success(function (response) {
            if(response.success){
                //总记录条数--设置分页配置里面的参数$scope.paginationConf
                $scope.paginationConf.totalItems=response.pageResult.total;
                $scope.list=response.pageResult.rows;

                //开奖号码:
                var result=$scope.list[0].numberResult;
                //JSON格式转换成数组
                var parse = JSON.parse(result);
                //获取值
                $scope.parseOne = parse[0];
                $scope.parsetwo = parse[1];
                $scope.parseThree = parse[2];
                $scope.parseFour = parse[3];
                $scope.parseFive = parse[4];

                //计算出下一轮开奖时间
                var startTime=new Date($scope.list[0].startTime).getTime();//下一轮开始时间毫秒值
                var nowTime = new Date().getTime();//获取当前时间毫秒值

                //剩余时间 (开奖时间-当前时间)/1000 等于秒值
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
                        $scope.findAllImages($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
                    }
                    },1000);
            }else {
                response.message;
            }
        }).error(function (response) { //错误异常
            alert(response.message);//弹窗提示
        })}

    // 如果数据变更就重新加载  分页查询方法(请求/响应)
    $scope.reloadList=function(){
        ////分页查询方法(请求/响应)参数1:当前页码参数2:当前页有多少条数据
        $scope.findAllImages($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage)
    }

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
    }

    //初始化默认值
    $scope.toFiveValue={};
    $scope.values=[];
    //设置出奖值
    $scope.setElEleventToFiveValue=function () {
        var b = $scope.checkValues($scope.toFiveValue.parseOne);
        var b1 = $scope.checkValues($scope.toFiveValue.parsetwo);
        var b2 = $scope.checkValues($scope.toFiveValue.parseThree);
        var b3 = $scope.checkValues($scope.toFiveValue.parseFour);
        var b4 = $scope.checkValues($scope.toFiveValue.parseFive);
        if(b==false||b1==false||b2==false||b3==false||b4==false){
            return false;
        }
        $scope.values.push(
            $scope.toFiveValue.parseOne,
            $scope.toFiveValue.parsetwo,
            $scope.toFiveValue.parseThree,
            $scope.toFiveValue.parseFour,
            $scope.toFiveValue.parseFive);

        $http.post("../ElevenToFiveController/setElEleventToFiveValue?values="+$scope.values).success(function (response) {
            if (response.success){
                alert(response.message);
                $scope.values=[];//清空数组
            }else {
                alert(response.message);
                $scope.values=[];//清空数组

            }}).error(function (response) {
            alert(response.message);
            $scope.values=[];//清空数组
        })}

    //校验方法
    $scope.checkValues=function(values){
        if(values==null){
            alert("不能传空值!");
            return false;
        }
        if(values>12 || values<1){
            alert("不能大于12或者小于1!");
            return false;
        }
        return true;
    }

})