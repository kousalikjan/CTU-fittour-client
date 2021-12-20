package cz.cvut.fit.tjv.fittour.fittourclient.ui;

import cz.cvut.fit.tjv.fittour.fittourclient.data.RiderClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class RiderWebController
{
    private final RiderClient riderClient;

    public RiderWebController(RiderClient riderClient)
    {
        this.riderClient = riderClient;
    }

    @GetMapping("/riders")
    public String getAllRiders(Model model)
    {
        model.addAttribute("riders", riderClient.fetchAllSnowboards()
                .sort((o1, o2) ->
                        Objects.equals(o1.getId(), o2.getId()) ? 0 :
                                o1.getId() < o2.getId() ? -1 : 1));

        model.addAttribute("formatError", false);
        return "riders"; // vraci jaky HTML soubor z templates se ma vratit
    }


}
