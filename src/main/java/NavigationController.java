import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(NavigationController.class);
    private static final Logger logFile = Logger.getLogger("LoggerFile");
    @ManagedProperty(value = "#{param.pageId}")
    private String pageId;

    private String username;
    private String password;
    private String message;
    private String unsuccessfulLogin;

    private String firstPasswordChanger;
    private String secondPasswordChanger;


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

    public String getFirstPasswordChanger() {
        return firstPasswordChanger;
    }

    public void setFirstPasswordChanger(String firstPasswordChanger) {
        this.firstPasswordChanger = firstPasswordChanger;
    }

    public String getSecondPasswordChanger() {
        return secondPasswordChanger;
    }

    public void setSecondPasswordChanger(String secondPasswordChanger) {
        this.secondPasswordChanger = secondPasswordChanger;
    }

    public String login() {
        User user = new User(this.getUsername(), Cypher.md5Custom(this.getPassword()));
        if (user.getId() != null) {
            unsuccessfulLogin = "";
            message = "You have successfully logged in as " + user.getFirstName() + " " + user.getLastName();
            log.info("Successful login attempt at " +  new Date(System.currentTimeMillis()).toString() + " \n user - " + user.getId() + ". " +  user.getUsername());
            logFile.info("Successful login attempt at " +  new Date(System.currentTimeMillis()).toString() + " \n user - " + user.getId() + ". " +  user.getUsername());
            return "interface";
        } else {
            unsuccessfulLogin = "Wrong username or password";
            log.info("Unsuccessful login attempt at " +  new Date(System.currentTimeMillis()).toString() + " \n user - " + this.getUsername());
            logFile.info("Unuccessful login attempt at " +  new Date(System.currentTimeMillis()).toString() + " \n user - " + this.getUsername());
            return "index";
        }
    }

    public String changePassword() {
        if (this.getFirstPasswordChanger().equals(this.getSecondPasswordChanger())) {
            unsuccessfulLogin = "Password changed";
            log.info("Successful password change attempt at " +  new Date(System.currentTimeMillis()).toString());
            return "index";
        } else {
            unsuccessfulLogin = "Passwords don't match";
            log.info("Unsuccessful password change attempt at " +  new Date(System.currentTimeMillis()).toString());
            return "interface";
        }
    }
} 