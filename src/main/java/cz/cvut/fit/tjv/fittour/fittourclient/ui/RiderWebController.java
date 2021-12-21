package cz.cvut.fit.tjv.fittour.fittourclient.ui;

import cz.cvut.fit.tjv.fittour.fittourclient.data.RiderClient;
import cz.cvut.fit.tjv.fittour.fittourclient.data.SnowboardClient;
import cz.cvut.fit.tjv.fittour.fittourclient.model.IntegerModel;
import cz.cvut.fit.tjv.fittour.fittourclient.model.RiderModel;
import cz.cvut.fit.tjv.fittour.fittourclient.model.SnowboardModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

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

    @GetMapping("/riders/updatesnowboard")
    public String updateSnowboardGet(@RequestParam Integer id, @ModelAttribute  IntegerModel integerModel, Model model)
    {
        model.addAttribute("error", false);
        return "ridersUpdateSnowboard";
    }


    @PostMapping("/riders/updatesnowboard")
    public String updateSnowboardGet(@RequestParam Integer id, @ModelAttribute @Validated IntegerModel integerModel, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            model.addAttribute("error", true);
            model.addAttribute("success", false);
            return "ridersUpdateSnowboard";
        }
        model.addAttribute("", riderClient.updateSnowboard(id, integerModel.getIdx())
                .onErrorResume(WebClientResponseException.BadRequest.class, e -> Mono.just(new RiderModel())));
        model.addAttribute("error", false);
        model.addAttribute("success", true);

        return "ridersUpdateSnowboard";
    }



}
