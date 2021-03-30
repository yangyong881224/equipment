package com.equipment.web.controller.door;

import com.equipment.system.domain.EquipmentBanner;
import com.equipment.system.service.IEquipmentBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @UserBannerController:
 * @author: Yayo
 * @date: 2021/3/30 2:01
 */
@Controller
@RequestMapping("/user/banner")
public class UserBannerController {

    @Autowired
    private IEquipmentBannerService bannerService;

    @GetMapping("/list")
    @ResponseBody
    public List<EquipmentBanner> list(){
        EquipmentBanner banner = new EquipmentBanner();
        banner.setStatus("0");
        return bannerService.selectEquipmentBannerList(banner);
    }
}
