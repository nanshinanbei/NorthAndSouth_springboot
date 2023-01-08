package autumndoubleli.northandsouth.service;
import autumndoubleli.northandsouth.data.UserDao;
import autumndoubleli.northandsouth.entity.UserInfo;
import autumndoubleli.northandsouth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired

    private UserDao userDao;

    public User getUser(String email, String password) {
        User user = userDao.checkPassword(email, password);
        if (user == null)
            throw new RuntimeException();
        return user;
    }

    public UserInfo getUserInfoByEmail(String email) {
        UserInfo AllUserInfo = userDao.getUserInfoByEmail(email);
        return AllUserInfo;
    }

    public User editPassword(String email,String password) {
        User newuser=userDao.editPassword(email,password);
        return newuser;
    }
}


