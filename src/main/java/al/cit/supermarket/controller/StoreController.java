package al.cit.supermarket.controller;

import al.cit.supermarket.component.MySessionAttributes;
import al.cit.supermarket.model.authority.AuthorityName;
import al.cit.supermarket.service.StoreService;
import al.cit.supermarket.service.dto.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/stores")
public class StoreController {

    private StoreService storeService;
    private MySessionAttributes mySessionAttributes;

    @Autowired
    public StoreController(StoreService storeService, MySessionAttributes mySessionAttributes) {
        this.storeService = storeService;
        this.mySessionAttributes = mySessionAttributes;
    }

    @GetMapping
    public String getStores(Model model){

        model.addAttribute("stores", storeService.getStores());
        return null;
    }

    @GetMapping("/{id}")
    public String getStore(@PathVariable("id") int id){


        return "stores/show";
    }

    @GetMapping("/open/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String openStore(
            @PathVariable("id") int id
    ){

        mySessionAttributes.setStore(storeService.getStore(id));

        Set<String> authorities = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (authorities.contains(AuthorityName.ADMIN.toString()))
            return "redirect:/admin/dashboard";

        return "redirect:/admin/dashboard";
    }

    @PostMapping
    public String postStore(@ModelAttribute("newStore") StoreDTO store){

        int id = storeService.createStore(store);

        return String.format("redirect:/stores/%d", id);
    }

    @PutMapping("/{id}")
    public String putStore(
            @PathVariable("id") int id,
            @ModelAttribute("updatedStore") StoreDTO dto
        ) {

        storeService.updateStore(dto);
        return String.format("redirect:/stores/%d", id);
    }

    @DeleteMapping("/{id}")
    public String deleteStore(@PathVariable("id") int id){

        storeService.deleteStore(id);
        return "redirect:/stores";
    }
}
