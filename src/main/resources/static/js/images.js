
var mymodule=angular.module("myapp",[]);

//service层
mymodule.service("uploadService",function ($http) {

    this.upload=function () {
        //基于html5中的对象获取(追加)上传文件
        var formData = new FormData();
        //参数一：后端接收文件的参数名称 参数二：获取文件，其中file代表<input type="file" id="file" />中的id
        formData.append("file",file.files[0]);

        return $http({
            method:"post",
            url : "../uploadController/uploadImages",
            data : formData,
            headers : {'Content-Type' : undefined}, //上传文件必须是这个类型，默认text/plain  作用:相当于设置enctype="multipart/form-data"
            transformRequest : angular.identity  //对整个表单进行二进制序列化
        });}

});

//controller层
mymodule.controller("imagesController",function ($scope,$http,uploadService) {

    $scope.entity={};//初始化

    //图片上传功能
    $scope.uploadFile=function(){
        uploadService.upload().success(function(response){
            if(response.success){//上传到linux成功
                $scope.entity.imgUrl=response.message; //把返回来的图片地址赋值给广告图片地址
            }else {
                alert(response.message);//弹窗提示
            }}
            ).error(function (response) {//错误异常
            alert(response.message);//弹窗提示
        })}

    //图片上传
    $scope.uploadImages=function () {
        $http.post("../ImagesController/addImages",$scope.entity).success(function (response) {
            if(response.success){//上传成功
                $scope.entity={};//新增成功后,清空数据
                alert(response.message);//弹窗提示
            }else {
                alert(response.message);//弹窗提示
            }}
            ).error(function (response) { //错误异常
            alert(response.message);//弹窗提示
        })}

    //查询全部图片
    $scope.findAllImages=function () {
        $http.post("../ImagesController/findAllImages").success(function (response) {
            if(response.success){
                $scope.list=response.images;
            }else {
                response.message;
            }
        }).error(function (response) { //错误异常
                alert(response.message);//弹窗提示
            })}

     //根据id删除图片和删除linux保存的图片
    $scope.deleteImages=function (id,imgUrl) {
        $http.post("../ImagesController/deleteImages?id="+id+"&imgUrl="+imgUrl).success(function (response) {
            if(response.success){
                alert(response.message);
                $scope.findAllImages();//删除成功后,重新刷新页面
            }else {
                alert(response.message);
            }}).error(function (response) {
                alert(response.message);
        })}

})
