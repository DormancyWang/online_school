package com.guo.eduservice.controller;

import com.guo.commutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EduLoginController {
    //login
    @PostMapping("login")
    public R login(String username,String password) {
        return R.ok().data("token", "admin");
    }

    //info
    @GetMapping("info")
    public R info() {
        return R.ok().data("role", "[admin]").data("name", "guozhaoadmin")
                .data("avatar", "https://guo-edu.oss-cn-beijing.aliyuncs.com/admin.gif");
    }
}


