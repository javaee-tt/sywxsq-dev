//定义ng-app模块
var mymodule=angular.module("myapp",["pagination"]);

//service层
mymodule.service("uploadService",function ($http) {

    this.upload=function (imgTitle,imgDescription,sort) {
        //基于html5中的对象获取(追加)上传文件 通过FormData构造函数创建一个空对象
        var formData = new FormData();
        //参数一：后端接收文件的参数名称 参数二：获取文件，其中file代表<input type="file" id="file" />中的id
        formData.append("file",file.files[0]);
        //图片标题 (如果没值需要初始化一个值,不然后台会爆undefined)
        formData.append("imgTitle",(imgTitle==undefined?"":imgTitle));
        //图片详情 (如果没值需要初始化一个值,不然后台会爆undefined)
        formData.append("imgDescription",(imgDescription==undefined?"":imgDescription));
        //图片排序 (如果没值需要初始化一个值,不然后台会爆undefined)
        formData.append("sort",(sort==undefined?"":sort));
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
        //调用service方法,并且代入所需参数
        uploadService.upload($scope.entity.imgTitle,$scope.entity.imgDescription,$scope.entity.sort).success(function(response){
            if(response.success){//上传到linux成功
                $scope.entity={};//新增成功后,清空数据
                alert(response.message); //把返回来的图片地址赋值给广告图片地址
                $scope.reloadList();//重新加载分页查询
            }else {
                alert(response.message);//弹窗提示
            }}
            ).error(function (response) {//错误异常
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

    //html回显的图片 ==> 销毁预览的图片
    $scope.destroyImage=function () {
        console.log("执行了这个方法前:"+file.files[0]);
        var files = document.getElementById("file");
        files.outerHTML=file.outerHTML;
        console.log("执行了这个方法后:"+file.files[0]);
        //获取img标签信息
        var img = document.getElementById("imgName");
        //赋地址值
        img.src ="";
        //显示图片
        img.style.display="none";
    }

});

//html页面回显预览图片
function previewImage(file) {
    /*
    * file：file控件
    */
    var tip = "格式有误,只支持上传jpg/png/gif的格式文件!"; // 设定弹窗提示信息
    var filters = {
        "jpeg" : "/9j/4",
        "gif" : "R0lGOD",
        "png" : "iVBORw"
    }

    if (window.FileReader) { // html5方案
        for (var i=0, f; f = file.files[i]; i++) {
            var fr = new FileReader();
            fr.onload = function(e) {
                var src = e.target.result;
                if (!validateImg(src)) {
                    alert(tip)
                } else {
                    showPrvImg(src); //展示图片
                }
            }
            fr.readAsDataURL(f);
        }
    } else { // 降级处理
        if ( !/\.jpg$|\.png$|\.gif$/i.test(file.value) ) {
            alert(tip);
        } else {
            showPrvImg(file.value); //展示图片
        }
    }

    //校验图片格式
    function validateImg(data) {
        var pos = data.indexOf(",") + 1;
        for (var e in filters) {
            if (data.indexOf(filters[e]) === pos) {
                return e;
            }
        }
        return null;
    }

    //html页面回显预览图片
    function showPrvImg(src) {
        //获取img标签信息
        var img = document.getElementById("imgName");
        //赋地址值
        img.src = src;
        //显示图片
        img.style.display="block";
    }
}
