//app模版  pagination是分页插件
var mymodule = angular.module("myapp", []);

//controller层
mymodule.controller("registryController", function ($scope, $http) {

    //初始化对象
    $scope.entity = {};

    //发送邮件验证码
    $scope.sendEmail = function () {
        $http.post("../SendEmailController/sendEmail?recipientsEmail=" + $scope.entity.recipientsEmail).success(function (response) {
            if (response.success) {
                alert(response.message);
            } else {
                alert(response.message);
            }}).error(function (response) {
            alert(response.message);
        })};

        //校验验证码
    $scope.checkEmailCode = function () {
        $http.post("../SendEmailController/checkEmailCode?recipientsEmail=" + $scope.entity.recipientsEmail + "&YZMcode=" + $scope.entity.YZMcode).success(function (response) {
            if (response.success) {
                alert(response.message);
                $scope.entity = {}; //成功后对象清空
            } else {
                alert(response.message);
            }
            }).error(function (response) {
            alert(response.message);
        })};


})