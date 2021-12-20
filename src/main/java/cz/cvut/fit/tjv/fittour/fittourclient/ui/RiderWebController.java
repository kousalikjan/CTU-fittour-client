package cz.cvut.fit.tjv.fittour.fittourclient.ui;

import cz.cvut.fit.tjv.fittour.fittourclient.data.RiderClient;
import cz.cvut.fit.tjv.fittour.fittourclient.data.SnowboardClient;
import cz.cvut.fit.tjv.fittour.fittourclient.model.RiderModel;
import cz.cvut.fit.tjv.fittour.fittourclient.model.SnowboardModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/riders/add")
    public String addRiderGet(Model model)
    {
        model.addAttribute("riderModel", new RiderModel());
        model.addAttribute("success", false);
        return "ridersAdd";
    }

    @PostMapping("/riders/add")
    public String addRiderSubmit(Model model, @ModelAttribute @Validated RiderModel riderModel, BindingResult result)
    {
        if (result.hasErrors()) {
            model.addAttribute("riderModel", new RiderModel(riderModel));
            model.addAttribute("success", false);
            return "ridersAdd";
        }
        model.addAttribute("riderModel", riderClient.create(riderModel));
        model.addAttribute("success", true);
        return "ridersAdd";
    }


}
