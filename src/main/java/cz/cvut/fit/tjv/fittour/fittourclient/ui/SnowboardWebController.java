package cz.cvut.fit.tjv.fittour.fittourclient.ui;

import cz.cvut.fit.tjv.fittour.fittourclient.data.SnowboardClient;
import cz.cvut.fit.tjv.fittour.fittourclient.model.SnowboardDto;
import cz.cvut.fit.tjv.fittour.fittourclient.model.SnowboardModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return "snowboardsAdd";
    }

    @PostMapping("/snowboards/add")
    public String addSnowboardSubmit(Model model, @ModelAttribute @Validated SnowboardModel snowboardModel, BindingResult result)
    {
        if (result.hasErrors()) {
            model.addAttribute("snowboardModel", new SnowboardModel(true, snowboardModel));
            model.addAttribute("success", false);
            return "snowboardsAdd";
        }
        model.addAttribute("snowboardModel", snowboardClient.create(snowboardModel));
        model.addAttribute("success", true);
        return "snowboardsAdd";
    }

    @GetMapping("/snowboards/edit")
    public String editSnowboard(@RequestParam Integer id, Model model)
    {
        model.addAttribute("snowboardDto", snowboardClient.readById(id));
        model.addAttribute("success", false);
        return "snowboardsEdit";
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
        return "snowboardsEdit";
    }

    @GetMapping("/snowboards/delete")
    public String deleteSnowboard(@RequestParam Integer id, Model model)
    {
        model.addAttribute("", snowboardClient.delete(id));
        model.addAttribute("snowboards", snowboardClient.fetchAllSnowboards().sort((o1, o2) ->
                Objects.equals(o1.getId(), o2.getId()) ? 0 :
                        o1.getId() < o2.getId() ? -1 : 1));
        model.addAttribute("formatError", false);
        return "redirect:/snowboards";
    }

    @GetMapping("/riders/snowboard")
    public String getSnowboard(@RequestParam Integer id, Model model)
    {
        System.out.println("here");
        model.addAttribute("snowboard", snowboardClient.readById(id));
        return "ridersOneSnowboard";
    }


}
