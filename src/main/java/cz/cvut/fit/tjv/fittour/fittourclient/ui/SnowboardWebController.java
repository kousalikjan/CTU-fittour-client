package cz.cvut.fit.tjv.fittour.fittourclient.ui;

import cz.cvut.fit.tjv.fittour.fittourclient.data.SnowboardClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SnowboardWebController
{
    private final SnowboardClient snowboardClient;

    public SnowboardWebController(SnowboardClient snowboardClient)
    {
        this.snowboardClient = snowboardClient;
    }

    @GetMapping("/snowboards")
    public String getAllSnowboards(Model model)
    {
        model.addAttribute("snowboards", snowboardClient.fetchAllSnowboards());
        return "snowboards"; // vraci jaky HTML soubor z templates se ma vratit
    }
}
