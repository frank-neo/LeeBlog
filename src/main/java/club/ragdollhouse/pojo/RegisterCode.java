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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMd5pwd() {
        return md5pwd;
    }

    public void setMd5pwd(String md5pwd) {
        this.md5pwd = md5pwd;
    }


    public String getCodeefftime() {
        return codeefftime;
    }

    public void setCodeefftime(String codeefftime) {
        this.codeefftime = codeefftime;
    }

    private String username;
    private String code;
    private String sessionval;
    private String nickname;
    private String password;
    private String sex;
    private String md5pwd;
    private String codeefftime;
}
