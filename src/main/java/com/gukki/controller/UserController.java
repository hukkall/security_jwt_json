package com.gukki.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author DvDxD
 * @since 2020-06-27
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @PreAuthorize("hasRole('admin')")
    @RequestMapping("/admin")
    public String test() {
        return "Success";
    }

    @PreAuthorize("hasPermission('/user/user','view')")
    @RequestMapping("/user")
    public String user(){
        return "User Success";
    }
}

