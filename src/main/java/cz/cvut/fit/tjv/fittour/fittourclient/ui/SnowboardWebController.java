package cz.cvut.fit.tjv.fittour.fittourclient.ui;

import cz.cvut.fit.tjv.fittour.fittourclient.data.SnowboardClient;
import cz.cvut.fit.tjv.fittour.fittourclient.model.SnowboardDto;
import cz.cvut.fit.tjv.fittour.fittourclient.model.SnowboardModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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

    @GetMapping("/snowboards/add")
    public String addSnowboardGet(Model model)
    {
        model.addAttribute("snowboardModel", new SnowboardModel());
        return "addSnowboard";
    }

    @PostMapping("/snowboards/add")
    public String addSnowboardSubmit(Model model, @ModelAttribute @Validated SnowboardModel snowboardModel, BindingResult result)
    {
        if (result.hasErrors()) {
            model.addAttribute("snowboardModel", new SnowboardModel(true, snowboardModel));
            return "addSnowboard";
        }
        model.addAttribute("snowboardModel", snowboardClient.create(snowboardModel)
                .onErrorReturn(WebClientResponseException.BadRequest.class, new SnowboardModel(true, snowboardModel)));
        return "addSnowboard";
    }

}
