package ru.sapronov.jm_pp_2_3_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sapronov.jm_pp_2_3_1.dao.UserDAO;
import ru.sapronov.jm_pp_2_3_1.model.User;
import ru.sapronov.jm_pp_2_3_1.service.UserService;

/**
 * @author Ivan Sapronov
 */
@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model){
        //Получим всех пользователей из DAO и передадим на отображение в Представление
        model.addAttribute("users", userService.index());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        //Получим одного пользователя по его id из DAO и передадим на отображение в Представление
        model.addAttribute("user", userService.show(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(Model model){

        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.show(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("user") User user){
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userService.delete(id);
        return "redirect:/users";
    }
}
