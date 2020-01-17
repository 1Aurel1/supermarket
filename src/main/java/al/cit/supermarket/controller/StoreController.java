package al.cit.supermarket.controller;

import al.cit.supermarket.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/{id}")
    public String getStore(
            @PathVariable("id") int id,
            Model theModel
        ){


        return null;
    }

    @PostMapping
    public String postStore(){

        return "redirect:/";
    }

}
