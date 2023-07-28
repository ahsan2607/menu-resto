//@author Muhammad Ahsan Raziq Sulthany
package Class;
import java.sql.*;
import javax.swing.*;

public class conn {
    Connection conn = null;
    public static Connection connDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dbrestoranahsanujikom","root","");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
