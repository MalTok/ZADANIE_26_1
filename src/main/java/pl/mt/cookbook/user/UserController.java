package pl.mt.cookbook.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/add")
    public String add(User user) {
        userRepository.save(user);
        return "redirect:/user/show-all";
    }

    @GetMapping("/show-all")
    public String showAll(Model model) {
        List<User> userList = (List<User>) userRepository.findAll();
        if (userList.isEmpty()) {
            model.addAttribute("message", "Brak użytkowników w bazie.");
        }
        model.addAttribute("users", userList);
        return "users";
    }
}
