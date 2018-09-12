package club.ragdollhouse.pojo;

/**
 * 登录记住状态对象
 */
public class LoginAccessToken {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    public String getTaken_time() {
        return taken_time;
    }

    public void setTaken_time(String taken_time) {
        this.taken_time = taken_time;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeefftime() {
        return codeefftime;
    }

    public void setCodeefftime(String codeefftime) {
        this.codeefftime = codeefftime;
    }

    private int id;//主键id
    private String appname;//昵称
    private String email;//邮箱，也就是登录用户，和github相似
    private String taken;//登录状态token
    private String taken_time;//登录状态失效日期
    private String  code;//注册验证码
    private String codeefftime;//注册验证码失效日期
}
