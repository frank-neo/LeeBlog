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

    private String username;//用户邮箱
    private String code;//激活码
    private String sessionval;//
    private String nickname;//昵称
    private String password;//密码
    private String sex;//性别
    private String md5pwd;//md5加密密码
    private String codeefftime;//注册码生效时间
}
