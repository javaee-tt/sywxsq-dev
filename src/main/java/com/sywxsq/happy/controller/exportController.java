package com.sywxsq.happy.controller;

import com.sywxsq.happy.pojo.Friend;
import com.sywxsq.happy.pojo.PageResult;
import com.sywxsq.happy.pojo.SywxsqResult;
import com.sywxsq.happy.service.FriendService;
import com.sywxsq.happy.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 导入和导出
 * @author luokangtao
 * @create 2019-02-23 17:24
 */
@RestController
@RequestMapping("/exportController")
public class exportController {

    @Autowired
    FriendService friendService;

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response){
        //从数据库获取需要导出前100条的数据
        PageResult friend = friendService.findAllFriend(1, 100);//调用的是分页查询,可以自定义查询数据库数据
        List<Friend> rows = friend.getRows();//获取到数据库数据
        //导出操作
        //参数1: 需要导出的数据  参数2:标题  参数3:表名 参数4:映射的实体类  参数5:文件名  参数5:resposne
        ExcelUtils.exportExcel(rows,"我的同学录","这是表名",Friend.class,"文件名.xls",response);
    }

    @RequestMapping("/importExcel")
    public SywxsqResult importExcel(MultipartFile file){
        //解析excel
        List<Friend> friendList = ExcelUtils.importExcel(file, 1, 1, Friend.class);
        //循环遍历 插入到数据
        for (Friend friend:friendList) {
            friend.setCreateTime(new Date());
            friend.setCreateBy("测试人");
            friend.setUserId("测试人");
            boolean b = friendService.addFriend(friend);
            if(b==false){
                return new SywxsqResult(false,"导入["+friend.getFriendName()+"]失败");
            }
        }
        //返回结果集
        return new SywxsqResult(true,"导入数据一共["+friendList.size()+"]成功");
    }
}
