package club.ragdollhouse.pojo;

/**
 * accessToken对象
 */
public class AccessToken {

    //accessToken本体
    private String accessToken;
    //accessToken生效时间
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

}
