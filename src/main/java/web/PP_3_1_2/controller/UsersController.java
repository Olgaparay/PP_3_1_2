package web.PP_3_1_2.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.PP_3_1_2.models.User;
import web.PP_3_1_2.service.UserService;

@Controller
@RequestMapping("/")
public class UsersController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        logger.info("Вход в метод index");
        model.addAttribute("users", userService.peopleCount());
        logger.info("Выход из метода index");
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        logger.info("Вход в метод show с ID {}", id);
        User user = userService.userShow(id);
        if (user == null) {
            logger.warn("Пользователь с ID {} не найден", id);
            return "redirect:/";
        }
        model.addAttribute("user", user);
        logger.info("Выход из метода show с ID {}", id);
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        logger.info("Вход в метод newUser");
        logger.info("Выход из метода newUser");
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        logger.info("Вход в метод create");
        if (bindingResult.hasErrors()) {
            logger.warn("Ошибки при создании пользователя");
            return "new";
        }
        userService.saveUser(user);
        logger.info("Пользователь {} успешно создан", user.getId());
        logger.info("Выход из метода create");
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        logger.info("Вход в метод edit с ID {}", id);
        User user = userService.userShow(id);
        if (user == null) {
            logger.warn("Пользователь с ID {} не найден для редактирования", id);
            return "redirect:/";
        }
        model.addAttribute("user", user);
        logger.info("Выход из метода edit с ID {}", id);
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable("id") Integer id) {
        logger.info("Вход в метод update с ID {}", id);
        if (bindingResult.hasErrors()) {
            logger.warn("Ошибки при обновлении пользователя с ID {}", id);
            return "edit";
        }
        if (userService.userShow(id) == null) {
            logger.warn("Пользователь с ID {} не найден для обновления", id);
            return "redirect:/";
        }
        userService.updateUser(id, user);
        logger.info("Пользователь {} успешно обновлен", id);
        logger.info("Выход из метода update с ID {}", id);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        logger.info("Вход в метод delete с ID {}", id);
        if (userService.userShow(id) == null) {
            logger.warn("Пользователь с ID {} не найден для удаления", id);
            return "redirect:/";
        }
        userService.deleteUser(id);
        logger.info("Пользователь {} успешно удален", id);
        logger.info("Выход из метода delete с ID {}", id);
        return "redirect:/";
    }
}
