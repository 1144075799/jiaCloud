package com.jiacloud.api.controller;

import com.jiacloud.api.domain.Item;
import com.jiacloud.api.service.impl.CampusActivityServiceImpl;
import com.jiacloud.api.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemServiceImpl itemService;
    @Autowired
    private CampusActivityServiceImpl campusActivityService;

    /**发布项目**/
    @CrossOrigin
    @RequestMapping(value = "/appendActivity",method = RequestMethod.POST)
    public Item appendActivity(@RequestBody Item item){
        /**打印从前端收取的数据(测试用)**/
//        System.out.println(item.getName());
//        System.out.println(item.getSite());
//        System.out.println(item.getAlias());    //打印别名
        /**获取前端传递的值**/
        String name=item.getName();
        String site=item.getSite();
        String title=item.getTitle();
        String time=item.getTime();
        String participants=item.getParticipants();
        String particulars=item.getParticulars();
        String sponsor=item.getSponsor();
        String deadline=item.getDeadline();
        String alias=item.getAlias();

        /**判断数据库钟十分已存在这个活动**/
        String checkTitle=null;
        try {
            checkTitle=itemService.findItem(name).getTitle();
        } catch (Exception e) {
            System.out.println("没有存在相同的项目");
        }
        if(checkTitle!=null){
            /**返回请求号**/
            item.setCode(401);
            return item;
        }

        /**插入数据库**/
        itemService.addItem(name,site,title,time,participants,particulars,sponsor,deadline,alias);

        /**创建校园活动表**/
        campusActivityService.setActivity(alias);

        /**返回请求号**/
        item.setCode(200);

        return item;
    }

    @CrossOrigin
    @RequestMapping(value = "/getActivity",method = RequestMethod.GET)
    public List<Item> getActivity(){
        List<Item> item=itemService.findAllItem();
        return item;
    }
}
