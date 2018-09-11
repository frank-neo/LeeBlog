package club.ragdollhouse.pojo;

/**
 * 注册校验对象
 */
public class RegisterCode {

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

    public String getSessionval() {
        return sessionval;
    }

    public void setSessionval(String sessionval) {
        this.sessionval = sessionval;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String code;
    private String sessionval;
    private String nickname;
    private String password;
}
