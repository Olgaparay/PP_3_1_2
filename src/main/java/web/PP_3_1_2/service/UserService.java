package web.PP_3_1_2.service;


import web.PP_3_1_2.models.User;

import java.util.List;

public interface UserService {
    List<User> peopleCount();
    public User userShow(Integer id);
    void saveUser(User user);
    void updateUser(Integer id, User updatedUser);
    void deleteUser(Integer id);
}
