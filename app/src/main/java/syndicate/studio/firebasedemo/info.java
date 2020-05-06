package syndicate.studio.firebasedemo;
/**
 * Created by Ashutosh on 06-05-2020.
 */
public class info {
    private String email;
    private String name;
    public  info(){

    }
    public info(String email, String name) {
        this.email = email;
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
}

