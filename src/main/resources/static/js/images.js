
var mymodule=angular.module("myapp",["pagination"]);

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
        })};

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
        })};

    //图片上传
    $scope.uploadImages=function () {
        $http.post("../ImagesController/addImages",$scope.entity).success(function (response) {
            if(response.success){//上传成功
                $scope.entity={};//新增成功后,清空数据
                alert(response.message);//弹窗提示
                $scope.reloadList();//重新加载分页查询
            }else {
                alert(response.message);//弹窗提示
            }}
            ).error(function (response) { //错误异常
            alert(response.message);//弹窗提示
        })};

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

    //查询全部图片
    $scope.findAllImages=function (pageNumber,pageSize) {
        $http.post("../ImagesController/findAllImages?pageNumber="+pageNumber+"&"+"pageSize="+pageSize).success(function (response) {
            if(response.success){
                //总记录条数--设置分页配置里面的参数$scope.paginationConf
                $scope.paginationConf.totalItems=response.pageResult.total;
                $scope.list=response.pageResult.rows;
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

     //根据id删除图片和删除linux保存的图片
    $scope.deleteImages=function (id,imgUrl) {
        if(confirm("你确认删除?")){
            $http.post("../ImagesController/deleteImages?id="+id+"&imgUrl="+imgUrl).success(function (response) {
                if(response.success){
                    alert(response.message);
                    $scope.reloadList();//删除成功后,重新刷新页面
                }else {
                    alert(response.message);
                }}).error(function (response) {
                alert(response.message);
            })}};


    //图片放大效果
    $scope.imagesBig=function ($index) {
        $scope.imagesBigPlus=$scope.list[$index].imgUrl;
    }
});
