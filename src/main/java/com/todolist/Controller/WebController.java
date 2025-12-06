package com.todolist.Controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class WebController {
    @GetMapping("/")
    public String index() {
        return "index";  // chỉ load giao diện
    }

    @GetMapping("/create")
    public String createPage() {
        return "create";
    }

    @GetMapping("/edit")
    public String editPage() {
        return "edit";
    }

    @GetMapping("/detail")
    public String todoDetailPage() {
        return "detail";
    }
}
