package com.example.shoppingList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ShoppingController {

    private final List<ShoppingItem> items = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @GetMapping("/home")
    public String showHomepage() {
       // model.addAttribute("items", items);
        return "shopping-home-page";
    }
    
    // Display the shopping list
    @GetMapping("/")
    public String showList(Model model) {

        model.addAttribute("items", items);

        return "index";
    }
    @GetMapping("/shopping")
    public String showShoppingPage() {
        return "shoppingPage";
    }
    
    @GetMapping("/checkout")
    public String showCheckout(Model model) {
        model.addAttribute("items", items);
        return "checkout";
    }
    

    // Add a new item
    @PostMapping("/items")
    public String addItem(@RequestParam String name) {

        if (name != null && !name.trim().isEmpty()) {
            items.add(new ShoppingItem(nextId.getAndIncrement(), name.trim(), 0));
        }

        return "redirect:/";
    }

    // Mark an item as bought or unbought
    @PostMapping("/items/{id}/toggle")
    public String toggleItem(@PathVariable long id) {

        for (ShoppingItem item : items) {
            if (item.getId() == id) {
                item.setBought(!item.isBought());
                break;
            }
        }

        return "redirect:/";
    }

    // Delete an item
    @PostMapping("/items/{id}/delete")
    public String deleteItem(@PathVariable long id) {

        items.removeIf(item -> item.getId() == id);

        return "redirect:/";
    }
}