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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Objects;

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
        model.addAttribute("snowboards", snowboardClient.fetchAllSnowboards()
                .sort((o1, o2) ->
                Objects.equals(o1.getId(), o2.getId()) ? 0 :
                        o1.getId() < o2.getId() ? -1 : 1));

        model.addAttribute("formatError", false);
        return "snowboards"; // vraci jaky HTML soubor z templates se ma vratit
    }

    @GetMapping("/snowboards/add")
    public String addSnowboardGet(Model model)
    {
        model.addAttribute("snowboardModel", new SnowboardModel());
        model.addAttribute("success", false);
        return "addSnowboard";
    }

    @PostMapping("/snowboards/add")
    public String addSnowboardSubmit(Model model, @ModelAttribute @Validated SnowboardModel snowboardModel, BindingResult result)
    {
        if (result.hasErrors()) {
            model.addAttribute("snowboardModel", new SnowboardModel(true, snowboardModel));
            model.addAttribute("success", false);
            return "addSnowboard";
        }
        model.addAttribute("snowboardModel", snowboardClient.create(snowboardModel));
        model.addAttribute("success", true);
        return "addSnowboard";
    }

    @GetMapping("/snowboards/edit")
    public String editSnowboard(@RequestParam Integer id, Model model)
    {
        model.addAttribute("snowboardDto", snowboardClient.readById(id));
        model.addAttribute("success", false);
        return "editSnowboard";
    }

    @PostMapping("/snowboards/edit")
    public String editSnowboardSubmit(Model model, @ModelAttribute @Validated SnowboardDto snowboardDto, BindingResult result)
    {
        if (result.hasErrors()) {
            model.addAttribute("snowboards", snowboardClient.fetchAllSnowboards().sort((o1, o2) ->
                    Objects.equals(o1.getId(), o2.getId()) ? 0 :
                            o1.getId() < o2.getId() ? -1 : 1));
            model.addAttribute("formatError", true);
            return "snowboards";
        }
        model.addAttribute("snowboardDto", snowboardClient.update(snowboardDto));
        model.addAttribute("success", true);
        return "editSnowboard";
    }

    @GetMapping("/snowboards/delete")
    public String deleteSnowboard(@RequestParam Integer id, Model model)
    {
        System.out.println("delete" + id);
        model.addAttribute("", snowboardClient.delete(id));
        model.addAttribute("snowboards", snowboardClient.fetchAllSnowboards().sort((o1, o2) ->
                Objects.equals(o1.getId(), o2.getId()) ? 0 :
                        o1.getId() < o2.getId() ? -1 : 1));
        model.addAttribute("formatError", false);
        return "redirect:/snowboards";
    }

}
