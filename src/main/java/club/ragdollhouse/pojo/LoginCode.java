package club.ragdollhouse.pojo;

public class LoginCode {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionval() {
        return sessionval;
    }

    public void setSessionval(String sessionval) {
        this.sessionval = sessionval;
    }

    private String username;
    private String password;
    private String code;
    private String sessionval;
}
