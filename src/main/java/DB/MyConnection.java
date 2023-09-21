package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
  public   static Connection connection=null;
  public static Connection getConnection(){
      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/email_authentication?useSSL=false","root","paradox61");

      } catch (ClassNotFoundException | SQLException e) {
          throw new RuntimeException(e);
      }
      System.out.println("Connection done");
      return connection;
    }
    public static void closeConnection(){
      if(connection!=null){
          try{
              connection.close();
          }
          catch (SQLException ex){
              throw new RuntimeException(ex);
          }
      }

    }













}
