package com.example.demo0.controllers;
import com.example.demo0.models.*;
import com.example.demo0.reposytories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class MainController {

    @Autowired
    private AddresRep addresRep;
    @Autowired
    private PersonRep personRep;

    @Autowired
    private CocaRep cocaRep;
    @Autowired
    private ShopRep shopRep;

    @GetMapping("/")
    public String greeting( String name, Model model) {
        model.addAttribute("name", " это k4LЬКUЛЯt00RRRRRR");
        return "Home";
    }

    @PostMapping("/res")
    public String PostResult(@RequestParam(value = "variable1", defaultValue = "0") int a, @RequestParam(value = "variable2", defaultValue = "0") int b, @RequestParam(value = "operations", defaultValue = "+") String operation,  Model model) {
        int c = switch (operation) {
            case ("+") -> a + b;
            case ("-") -> a - b;
            case ("*") -> a * b;
            case ("/") -> a / b;
            default -> 0;
        };
        model.addAttribute("answer", c);
        return "Result";
    }

    @GetMapping("/res")
    public String GetResult(@RequestParam(value = "variable1", defaultValue = "0") int a, @RequestParam(value = "variable2", defaultValue = "0") int b, @RequestParam(value = "operations", defaultValue = "+") String operation,  Model model) {
        int c = switch (operation) {
            case ("+") -> a + b;
            case ("-") -> a - b;
            case ("*") -> a * b;
            case ("/") -> a / b;
            default -> 0;
        };
        model.addAttribute("answer", c);
        return "Result";
    }

    @GetMapping("/person")
    public String Main(Model model){
        Iterable<Addres> addres = addresRep.findAll();
        model.addAttribute("addres", addres);
        return "person";
    }

    @PostMapping("/person/add")
    public String blogPostAdd(@RequestParam String name, @RequestParam String city, Model model)
    {
        Addres addres = addresRep.findByCity(city);
        Person person = new Person(name, addres);
        personRep.save(person);
        return "redirect:/person";
    }

    @GetMapping("/coca")
    private String Main2(Model model){
        Iterable<Coca> coca = cocaRep.findAll();
        model.addAttribute("coca", coca);
        Iterable<Shop> shop = shopRep.findAll();
        model.addAttribute("shop", shop);

        return "coca";
    }

    @PostMapping("/coca/add")
    public String blogPostAdd2(@RequestParam String coca, @RequestParam String shop, Model model)
    {
        Coca coca2 = cocaRep.findByName(coca);
        Shop shop2 = shopRep.findByName(shop);

        coca2.getShops().add(shop2);
        shop2.getCocas().add(coca2);

        cocaRep.save(coca2);
        shopRep.save(shop2);

        return "redirect:/coca";
    }

    @Autowired
    public PlanetRep planetRep;
    @Autowired
    public HumanRep humanRep;

    @GetMapping("/human")
    public String Main3(Model model){
        Iterable<Planet> planets = planetRep.findAll();
        model.addAttribute("planet",planets);
        return "human";
    }

    @PostMapping("/human/add")
    public String blogPostAdd4(@RequestParam String name, @RequestParam String nameplanet, Model model)
    {
        Planet planet = planetRep.findByNameplanet(nameplanet);
        Human human = new Human(name, planet);
        humanRep.save(human);
        return "redirect:/human";
    }
}