//app模版  pagination是分页插件
var mymodule=angular.module("myapp",["pagination"]);

//controller层
mymodule.controller("11x5Controller",function ($scope,$http) {

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



})