
package com.info.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TerryJ on 2020/03/04.
 *
 */
@RestController
public class CommonController {

    @RequestMapping(value = "/test")
    public String test(){

        return "test";
    }


}
