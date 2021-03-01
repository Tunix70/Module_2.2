import java.sql.*;

public class PreparedStatement {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:8889/proselyte_tutorials";

    /**
     * User and Password
     */
    static final String USER = "root";
    static final String PASSWORD = "Loading182";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        java.sql.PreparedStatement preparedStatement = null;

        System.out.println("Registering JDBC driver...");

        Class.forName(JDBC_DRIVER);

        System.out.println("Creating database connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing preparestatement...");

        try{
            String sql = "SELECT * FROM developers";
            preparedStatement = connection.prepareStatement(sql);
            System.out.println("Initial developers table content:");
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialty = resultSet.getString("specialty");
                int salary = resultSet.getInt("salary");

                System.out.println("id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Specialty: " + specialty);
                System.out.println("Salary: " + salary);
                System.out.println("\n============================\n");
            }
            sql = "UPDATE developers SET salary= ? WHERE specialty= ?";
            System.out.println("Creating statement...");
            System.out.println("Executing SQL query...");

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 80000);
            preparedStatement.setString(2, "java");

            System.out.println("Rows impacted: " + preparedStatement.executeUpdate());

            System.out.println("Final developers table content:");
            sql = "SELECT * FROM developers";
            resultSet = preparedStatement.executeQuery(sql);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialty = resultSet.getString("specialty");
                int salary = resultSet.getInt("salary");

                System.out.println("id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Specialty: " + specialty);
                System.out.println("Salary: " + salary);
                System.out.println("\n============================\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        System.out.println("Thank You.");
    }
}



