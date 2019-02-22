var mymodule=angular.module("myapp",["pagination"]);

//controller层
mymodule.controller("FriendController",function ($scope,$http) {

    $scope.entity={};

    //新增通讯录
    $scope.addFriend=function () {
        $http.post("../FriendController/addFriend",$scope.entity).success(function (response) {
            if(response.success){//如果为true
                $scope.entity={};//上传成功后清空数据
                alert(response.message);//弹窗提示成功
            }else {
                alert(response.message);//弹窗提示失败
            }}).error(function (response) {//如果出现错误
            alert(response.message);//弹窗提示错误原因
        })}

    //分页查询配置 (这个对象配置原本就是已经配置好的,不是我们写的)一进页面就查询第一页.
    $scope.paginationConf={
        currentPage:1,//当前页码
        totalItems:10,//总记录条数
        itemsPerPage:10,//每页记录数
        perPageOptions:[10,20,30,40,50],//分页选项,下拉选择一页多少条记录
        onChange:function(){//更改页面时触发事件
            $scope.reloadList();//数据变更就重新加载分页查询
        }}

        //查询所有通讯录
    $scope.findAllFriend=function(pageNumber,pageSize){
        $http.post("../FriendController/findAllFriend?pageNumber="+pageNumber+"&"+"pageSize="+pageSize).success(function (response){
            if(response.success){
                //总记录条数--设置分页配置里面的参数$scope.paginationConf
                $scope.paginationConf.totalItems=response.pageResult.total;
                $scope.list=response.pageResult.rows;
            }}).error(function (response) {
            alert(response.message);
        })}

    // 如果数据变更就重新加载  分页查询方法(请求/响应)
    $scope.reloadList=function(){
        ////分页查询方法(请求/响应)参数1:当前页码参数2:当前页有多少条数据
        $scope.findAllFriend($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage)
    }

    //定义一个关系组
    $scope.relationList=["同学","老师","朋友","知己","兄弟","亲属"];

    //查询当前用户的分类
    $scope.selectUserClassifyList=function () {
        $http.get("../ClassifyController/selectUserClassifyList").success(function (response) {
            if(response.success){
                $scope.ClassifyList=response.classifyList;
            }else{
                alert(response.message);//弹窗提示失败
            }}).error(function (response) {//如果出现错误
            alert(response.message);//弹窗提示错误原因
        })}

    //定义一个空的分类名称
    $scope.calssify={};


    //新增分类
    $scope.addClassify=function () {
        $http.post("../ClassifyController/addClassify",$scope.calssify).success(function (response) {
            if(response.success){
                alert(response.message);
                $scope.calssify={};//清空
                $scope.selectUserClassifyList();//重新查询分类
            }else {
                alert(response.message);//弹窗提示失败
            }}).error(function (response) {//如果出现错误
                alert(response.message);//弹窗提示错误原因
            })}
    
    //删除分类
    $scope.deleteClassify=function (id) {
        $http.post("../ClassifyController/deleteClassify?id="+id).success(function (response) {
            if(response.success){
                alert(response.message);
                $scope.selectUserClassifyList();//重新查询分类
            }else {
                alert(response.message);//弹窗提示失败
            }}).error(function (response) {//如果出现错误
            alert(response.message);//弹窗提示错误原因
        })}
    
    
})