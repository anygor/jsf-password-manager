import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;

@ManagedBean(name = "user")
@SessionScoped
public class User {
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private String role;
    private String position;
    private Long id;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User(String username, String password) {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = getConnection();
        String stm = "SELECT * FROM user WHERE username = ? AND password = ?";
        try {
            pst = con.prepareStatement(stm);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString(2) + " " + rs.getString(3));
                this.setId(rs.getLong(1));
                this.setFirstName(rs.getString(2));
                this.setLastName(rs.getString(3));
                this.setPosition(rs.getString(4));
                this.setUsername(rs.getString(5));
                this.setPassword(rs.getString(6));
                this.setRole(rs.getString(7));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User() {

    }

    public Connection getConnection() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/user_management";
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection completed.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        finally {
        }
        return con;
    }
}