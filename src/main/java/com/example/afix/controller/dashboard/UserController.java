package com.example.afix.controller.dashboard;

import com.example.afix.exception.UserAlreadyExistsException;
import com.example.afix.model.Role;
import com.example.afix.model.User;
import com.example.afix.service.role.RoleService;
import com.example.afix.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(
            UserService theEmployeeService,
            RoleService roleService
    ){
        this.userService = theEmployeeService;
        this.roleService = roleService;
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
        var allRoles = roleService.findAll();
        model.addAttribute("user", userService.newUser());
        model.addAttribute("roles", allRoles);
        model.addAttribute("pageTitle", "Gebruikers toevoegen");

        return "dashboard/users/add";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        User existingUser = userService.findById(id);
        if (existingUser == null) {
            return "redirect:/users";
        }

        var allRoles = roleService.findAll();

        model.addAttribute("user", existingUser);
        model.addAttribute("roles", allRoles);
        model.addAttribute("pageTitle", "Gebruiker aanpassen");
        return "dashboard/users/edit";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("User already exists!");
        }

        user.setEnabled(user.getEnabled());

        Set<Role> managedRoles = user.getRoles()
                .stream()
                .map(role -> roleService.findById(role.getId()))
                .collect(Collectors.toSet());
        user.setRoles(managedRoles);

        userService.save(user);

        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        User existingUser = userService.findById(user.getId());
        if (existingUser == null) {
            throw new RuntimeException("User not found: " + user.getUsername());
        }

        existingUser.setEnabled(user.getEnabled());

        Set<Role> managedRoles = user.getRoles()
                .stream()
                .map(role -> roleService.findById(role.getId()))
                .collect(Collectors.toSet());
        existingUser.setRoles(managedRoles);

        userService.save(existingUser);

        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Integer id){
        User theEmployee = userService.findById(id);
        if(theEmployee == null){
            return "redirect:/users";
        }

        userService.deleteById(id);

        return "redirect:/users";
    }
}
