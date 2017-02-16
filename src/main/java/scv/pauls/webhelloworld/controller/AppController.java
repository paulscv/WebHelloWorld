package scv.pauls.webhelloworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
    public String SelectAll(Model model) {
        model.addAttribute("select", selectTestTables.getRowsAsString("public.\"Box_status\""));
        return "select";
    }

    @RequestMapping(value = {"hello/{name}"}, method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(name));
        return modelAndView;
    }
}



