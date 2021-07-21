package example.swing.model;


public class UserInfo {
    private String name;
    private String email;


    public UserInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "Name = " + name + "\n" +
                "Email = " + email;
    }
}