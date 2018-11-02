package club.ragdollhouse.controller;

import club.ragdollhouse.pojo.Test;
import club.ragdollhouse.service.Testservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestThemeleaf {
    @Autowired
    Testservice testservice;

    @RequestMapping(value = "/TestThemeleaf",method = RequestMethod.GET)
    public String TestThemeleaf(Model model) {
        List<Test> list = new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            Test test = new Test();
            test.setAge(111111*i+"");
            list.add(test);
        }


        model.addAttribute("testlist",list);
        return "ITNews";
    }

    @RequestMapping(value = "/testIpInfo",method = RequestMethod.GET)
    public String testIpInfo(){
        return "testIpInfo";
    }
}
