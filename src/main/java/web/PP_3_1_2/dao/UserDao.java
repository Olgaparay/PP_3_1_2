package web.PP_3_1_2.dao;


import web.PP_3_1_2.models.User;

import java.util.List;

public interface UserDao {
    List<User> index();
    User show(Integer id);
    void save(User user);
    void update(Integer id, User updatedUser);
    void delete(Integer id);
}
