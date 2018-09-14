package club.ragdollhouse.pojo;

/**
 * UserInf表pojo
 */
public class UserInf {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMd5pwd() {
        return md5pwd;
    }

    public void setMd5pwd(String md5pwd) {
        this.md5pwd = md5pwd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCodeefftime() {
        return codeefftime;
    }

    public void setCodeefftime(String codeefftime) {
        this.codeefftime = codeefftime;
    }

    public String getPermit_flag() {
        return permit_flag;
    }

    public void setPermit_flag(String permit_flag) {
        this.permit_flag = permit_flag;
    }

    private String md5pwd;
    private String password;
    private String email;
    private String statu;
    private String code;
    private String appname;
    private String sex;
    private String codeefftime;
    private String permit_flag;
}
