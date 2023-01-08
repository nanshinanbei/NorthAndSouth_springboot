package autumndoubleli.northandsouth.data;
import autumndoubleli.northandsouth.entity.UserInfo;
import autumndoubleli.northandsouth.entity.User;
import autumndoubleli.northandsouth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUserByEmail(String email) {
        String sql = "select * from user where email = ?";
        List<User> userList = jdbcTemplate.query(sql, new Object[]{email}, new UserMapper());
        if (userList.size() == 0) {
            return new User(-1, "invalid", "ok");
        }
        return userList.get(0);
    }

    public User checkPassword(String email, String password) {
        User user = getUserByEmail(email);
        if (user.equals(new User(-1, "invalid", "ok"))) {
            return new User(-1, "invalid", "ok");
        }
        if ((user.getPassword() + "").equals(password)) {
            return user;
        }
        return new User(-1, "ok", "invalid");
    }

    public UserInfo getUserInfoByEmail(String email) {
        String sql = "select * from user where email = ?";
        List<UserInfo> userList = jdbcTemplate.query(sql, new Object[]{email},
                (rs, rowNum) -> new UserInfo(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("telephone"),
                        rs.getString("email"),
                        rs.getString("school"),
                        rs.getString("password"),
                        rs.getString("name")
                ));
        if (userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    public User editPassword(String email, String password) {
        String sql = "update user set password = ? where email = ?";
        jdbcTemplate.update(sql, new Object[]{password, email});
        String sqlQuery = "select * from user where email = ?";
        List<User> userList = jdbcTemplate.query(sqlQuery, new Object[]{email}, new UserMapper());
        if (userList.size() == 0) {
            return new User(-1, "invalid", "ok");
        }
        return userList.get(0);
    }
}
