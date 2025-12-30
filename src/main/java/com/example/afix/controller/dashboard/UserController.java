package com.example.afix.controller.dashboard;

import com.example.afix.exception.UserAlreadyExistsException;
import com.example.afix.model.Authority;
import com.example.afix.model.User;
import com.example.afix.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService theEmployeeService){
        userService = theEmployeeService;
    }


    @GetMapping("")
    public String users(Model model) {
        List<User> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);
        model.addAttribute("pageTitle", "Gebruikers");
        return "dashboard/users/list";
    }

    @GetMapping("/add")
    public String addEmployee(Model model){
        User theUser = new User();
        model.addAttribute("user", theUser);
        model.addAttribute("pageTitle", "Gebruikers toevoegen");

        return "dashboard/users/add";
    }

    @GetMapping("/edit/{username}")
    public String editUser(@PathVariable String username, Model model) {
        User existingUser = userService.findByUsername(username);
        model.addAttribute("pageTitle", "Gebruiker aanpassen");
        if (existingUser == null) {
            return "redirect:/users";
        }

        model.addAttribute("user", existingUser);
        return "dashboard/users/edit";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user,
                           @RequestParam("role") String role) {
        if (userService.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("User already exists!");
        }

        Authority auth = new Authority();
        auth.setAuthority(role);
        auth.setUser(user);
        user.setAuthority(auth);

        userService.save(user);

        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user,
                             @RequestParam("role") String role) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser == null) {
            throw new RuntimeException("User not found: " + user.getUsername());
        }

        existingUser.setEnabled(user.getEnabled());

        Authority authority = existingUser.getAuthority();
        authority.setAuthority(role);
        existingUser.setAuthority(authority);

        userService.save(existingUser);

        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable String id){
        User theEmployee = userService.findByUsername(id);
        if(theEmployee == null){
            return "redirect:/users";
        }

        userService.deleteByUsername(id);

        return "redirect:/users";
    }
}
