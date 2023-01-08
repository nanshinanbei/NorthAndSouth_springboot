package autumndoubleli.northandsouth.mapper;
import org.springframework.jdbc.core.RowMapper;
import autumndoubleli.northandsouth.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserMapper implements RowMapper<User>
{
    public User mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        User student = new User();
        student.setId(rs.getInt("id"));
        student.setEmail(rs.getString("email"));
        student.setPassword(rs.getString("password"));
        return student;
    }
}