package cz.cvut.fit.tjv.fittour.fittourclient.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController
{
    @GetMapping("/")
    public String getHome()
    {
        return "home";
    }
}
