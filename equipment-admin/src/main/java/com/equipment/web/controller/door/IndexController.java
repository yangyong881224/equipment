package com.equipment.web.controller.door;

import com.equipment.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @IndexController:
 * @author: Yayo
 * @date: 2021/2/21 14:40
 */
@Controller
public class IndexController extends BaseController {

    // 系统首页
    @GetMapping("/main/index")
    public String index(ModelMap mmap)
    {
        
        return "/door/index";
    }
}
