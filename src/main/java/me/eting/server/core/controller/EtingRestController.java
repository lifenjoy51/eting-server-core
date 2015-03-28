package me.eting.server.core.controller;

import me.eting.common.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lifenjoy51 on 2015-03-04.
 */
@Controller
public class EtingRestController {
    
    @RequestMapping("/test")
    @ResponseBody
    public int test(){
        return 1234;
    }
    
}
