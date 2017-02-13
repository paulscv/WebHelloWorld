package scv.pauls.webhelloworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import scv.pauls.webhelloworld.model.SelectTestTables;

@Controller
public class AppController {
    @Autowired
    SelectTestTables selectTestTables;

    @RequestMapping("/")
    public String hello() {
        return "index";
    }

    @RequestMapping("/select")
    public String SelectAll(Model model){
        model.addAttribute("select", selectTestTables.getRowsAsString("public.\"Box_types\""));
        return "select";
    }
}
