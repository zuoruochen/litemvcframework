package com.zrc.demo;

import com.zrc.annotation.Controller;
import com.zrc.annotation.RequestMapping;
import com.zrc.annotation.RequestParam;
import com.zrc.annotation.ResponseBody;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ControllerDemo {
    private String name = ControllerDemo.class.getName();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @RequestMapping(method = "GET", path = "/json")
    @ResponseBody
    public Map<String, Object> getJsonTest(@RequestParam(name = "key") String input, int value) {
        Map<String, Object> map = new HashMap<>();
        map.put("input", input);
        map.put("value", value);
        return map;
    }
}
