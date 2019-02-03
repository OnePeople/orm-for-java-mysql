
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

 
 
public class ConnectDB   {
     
      private static ConnectDB _instance = null;

   

    public static synchronized ConnectDB getInstance() {
        if (_instance == null)
            _instance = new ConnectDB();
        return _instance;
    } 
    
    private ConnectDB() {
     
      Connect ();
  
    }
    public Connection conn ;
    public void    Connect () {
            System.out.println("------Connection---------");
        Connection connection;
        ModalWindow MW= new ModalWindow();
        Properties prop = new Properties();
          String dr = new File("mydb.cfg").getAbsolutePath();
       // System.out.println("test");
       // System.out.println("file cfg " + dr);
        try {
            prop.load(new FileInputStream(dr));
        } catch (FileNotFoundException e) {
             MW.errorBox( "File "+dr+ " not found","Ошибка загрузки файла конфигурации");
            System.exit(1);
        } catch (IOException e) {
               MW.errorBox( "Ошибка загрузки файла конфигурации","Ошибка ");
            System.exit(1);
        }

       
        String host = prop.getProperty("host") ;
        String username = prop.getProperty("username") ;
        String password = prop.getProperty("password") ;
        String driver = prop.getProperty("driver") ;

      //  System.out.println("host: localhost\root:\password:1111\ndriver: " + driver);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
              MW.errorBox( "В системе не установлен драйвер для работы с данной базой данных ("+driver+")","Ошибка ");
             System.exit(1);
        }
        System.out.println("--------------------------");
        System.out.println("DRIVER: " + driver);
        try {
            connection = DriverManager.getConnection(host, username, password);
            System.out.println("CONNECTION: " + connection);
            this.conn = connection;

        } catch (SQLException e) {
			 
            // e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка подключения к базе данных, или базы данных не существует. Проверьте файл конфигураций mydb.cfg", "Error connect", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
         
    } 
    
      public void disconnect() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("DISCONNETION ");
    }
 
}
