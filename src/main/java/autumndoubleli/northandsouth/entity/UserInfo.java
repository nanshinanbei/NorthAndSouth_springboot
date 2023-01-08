package autumndoubleli.northandsouth.entity;
public class UserInfo
{
    public Integer id;
    public String username;
    public String telephone;
    public String email;
    public String school;
    public String password;
    public String name;

    public UserInfo(int id,
                    String username,
                    String telephone,
                    String email,
                    String school,
                    String password,
                    String name) {
        this.id = id;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.school = school;
        this.password = password;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPhone() {
        return telephone;
    }
    public void setPhone(String phone) {
        this.telephone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

