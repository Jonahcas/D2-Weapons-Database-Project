import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyJDBC {
    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weapons_schema", "root", "TrustNo.1");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from weapons");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("Name"));
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
