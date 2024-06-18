package web.PP_3_1_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.PP_3_1_2.dao.UserDao;
import web.PP_3_1_2.models.User;


import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao personDao) {
        this.userDao = personDao;
    }

    public List<User> peopleCount() {
        return userDao.index();
    }

    public User userShow(Integer id) {
        return userDao.show(id);
    }

    @Transactional
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Transactional
    public void updateUser(Integer id, User updatedUser) {
        userDao.update(id, updatedUser);
    }

    @Transactional
    public void deleteUser(Integer id) {
        userDao.delete(id);
    }
}
