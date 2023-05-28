package app.skillcaptain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/web/users")
public class UserWebController {

    @Autowired
    private UserRepository userRepository;

    // Other endpoints...

    @GetMapping("/details")
    public String showUserDetails(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "data";
    }

    @GetMapping("/{id}/edit")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            return "edit-user";
        } else {
            // Handle user not found
            return "redirect:/web/users/details";
        }
    }

    @PostMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id, @ModelAttribute("user") User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Update the user's name and email
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            userRepository.save(user);
            return "redirect:/web/users/details";
        } else {
            // Handle user not found
            return "redirect:/web/users/details";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
            return "redirect:/web/users/details";
        } else {
            // Handle user not found
            return "redirect:/web/users/details";
        }
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/web/users/details";
    }
}
