import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{param.pageId}")
    private String pageId;

    private String username;
    private String password;
    private String message;
    private String unsuccessfulLogin;


    public String moveToPage1() {
        return "page1";
    }

    public String moveToPage2() {
        return "page2";
    }

    public String moveToHomePage() {
        return "home";
    }

    public String processPage1() {
        return "page";
    }

    public String processPage2() {
        return "page";
    }

    public String showPage() {
        if(pageId == null) {
            return "home";
        }

        if(pageId.equals("1")) {
            return "page1";
        }else if(pageId.equals("2")) {
            return "page2";
        }else {
            return "home";
        }
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getUnsuccessfulLogin() {
        return unsuccessfulLogin;
    }

    public void setUnsuccessfulLogin(String unsuccessfulLogin) {
        this.unsuccessfulLogin = unsuccessfulLogin;
    }

    public String login() {
        User user = new User(this.getUsername(), Cypher.md5Custom(this.getPassword()));
        if (user.getId() != null) {
            unsuccessfulLogin = "";
            message = "You have successfully logged in as " + user.getFirstName() + " " + user.getLastName();
            return "interface";
        } else {
            unsuccessfulLogin = "Wrong username or password";
            return "index";
        }
    }
} 