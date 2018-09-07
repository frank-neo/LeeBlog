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

    private int id;
    private String appname;
    private String email;
    private String taken;
    private String taken_time;
}
