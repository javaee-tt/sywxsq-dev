var mymodule=angular.module("myapp",[]);

//controller层
mymodule.controller("FriendController",function ($scope,$http) {

    $scope.entity={};

    //新增同学/同事/朋友
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

        //查询所有同学/同事/朋友
    $scope.findAllFriend=function(){
        $http.post("../FriendController/findAllFriend").success(function (resposne){
            if(resposne.success){
                $scope.list=resposne.friendList;
            }}).error(function (response) {
            alert(response.message);
        })}

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


})