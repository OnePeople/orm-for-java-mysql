import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ORM {

    //  public String createTable = "CREATE TABLE `example` (id INT, data VARCHAR(100))";
    private String dr;
    private int curId = 0;
    public String uid="0" ;
    public boolean logSelect = true;
    public Connection conn;
    public String query = "";
    public String fields_values = "";
    public  String modelName="";
    public String entity;
    public List<String> colums = new ArrayList<>();
    public int lastCountRecordUpdate=0;
    public int GetCurId() {
 
        return curId;
    }

    public ORM SetCurId(int i) {
        fields_values = "";
        curId = i;
        return this;
    }

    public List<String> getColums() {
        ResultSet rs = null;

        this.query = "  SHOW COLUMNS FROM " + this.entity;
        System.out.println("run: " + this.query);

        Statement statement;
        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery(this.query);
        } catch (SQLException e) {
              System.out.println( ColorCodes.ANSI_RED +"ERROR in SQL: "    + this.query);
           // e.printStackTrace();
        }

        this.colums.clear();

        try {
            while (rs.next()) {
                this.colums.add(rs.getString("Field"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ORM.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.colums;

    }
   public ORM( ) {
     this.conn= ConnectDB.getInstance().conn;    
     // this.ORM("");
    }
    public ORM(String entity) {
         this.entity = entity;
         this.conn= ConnectDB.getInstance().conn;
        // System.out.println("ORM: " + this.entity);
      
    }
    
    public void SetRelation(String localKey,String OtherKey,String OtherTable)
    {
        
    }
    
    public void SelectRelation(String localKey,String OtherKey,String OtherTable)
    {
        
    }  
    
    
    public int runSqlStatement(String sql) {
        System.out.println("run: " + sql);
        int  rs =0 ;
        try {
            Statement statement = this.conn.createStatement();
           //  statement.setDate(2, dt1);
            rs = statement.executeUpdate(sql) ;/*execute*/
                  //  executeUpdate(sql);

        } catch (SQLException ex) {
            // ex.printStackTrace();
           // System.out.println("error: " + ex.getMessage());
             System.out.println( ColorCodes.ANSI_RED +"ERROR in SQL: "    + sql);
        }
        this.lastCountRecordUpdate=rs;
        return rs;
    }



    protected void finalize() {

        System.out.println("------finalize---------");
       // disconnect();
    }

    public ORM setS(String field, String val) {
        // System.out.println("before setS: " + this.fields_values);
        if (this.fields_values == "") {
            this.fields_values = field + "='" + val + "'";
        } else {
            this.fields_values = this.fields_values + "," + field + "='" + val + "'";
        }
        //System.out.println("after setS: " + this.fields_values);
        return this;
    }

    public ORM setI(String field, String val) {
        // System.out.println("before setI: " + this.fields_values);    
        if (this.fields_values == "") {
            this.fields_values = field + "=" + val;
        } else {
            this.fields_values = this.fields_values + "," + field + "=" + val;
        }
        // System.out.println("after setI: " + this.fields_values);    
        return this;
    }

    public void entity(String entity) {
        this.entity = entity;
        this.fields_values = "";

    }

    public ORM delete(int id) {
      
        if (id == 0)        id=this.curId;
        this.query = "DELETE FROM " + this.entity + "  WHERE id=" + id;
        this.fields_values = "";
        this.runSqlStatement(this.query);
        this.saveLog(id,"Удаление");
        return this;
    }

      public void saveLog(int id_record, String crud   ) {
       
          this.saveLog( id_record,  crud ,"" ) ;
      }
      
     public void saveLog(int id_record, String crud ,String note ) {
       
          if (this.logSelect) {
                this.executeUpdate("INSERT INTO events SET "+
                        "crud='" +crud+"', " +
                        "entity='" +this.modelName+"', " +
                        "id_user='" +this.uid+"', " +
                        "note='" +note+"', " +
                         "id_record='" +id_record+"' "
                   );
               }
      }
    
    
    public ORM update(int id) {
        if (id == 0)  
           id=this.curId;
         
        this.query = "UPDATE  " + this.entity + " SET " + this.fields_values + " WHERE id=" + id;
        this.fields_values = "";

        this.runSqlStatement(this.query);
        this.saveLog(id,"Обновление");
        return this;
    }

    public ORM save() {
        this.query = "INSERT INTO " + this.entity + " SET " + this.fields_values;
        this.fields_values = "";
        this.runSqlStatement(this.query);
        int id=lastInsertId();
        this.saveLog(id,"Добавление");
        return this;
    }

    public ResultSet find(Integer id) {
        
        this.SetCurId( id);
        this.query = "SELECT * FROM `" + this.entity + "`  WHERE id=" + this.curId;
        System.out.println("run: " + this.query);
        Statement statement;
        ResultSet rs = null;

        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery(this.query);
            this.saveLog(id,"Просмотр");
             
        } catch (SQLException e) {
             System.out.println( ColorCodes.ANSI_RED +"ERROR in SQL: "    + this.query);
           // e.printStackTrace();
        }

       // this.fields_values = "";
        return rs;
    }
    public int  lastInsertId;
    private int lastInsertId()
    {
         ResultSet rs = null;
        Statement statement;
        int last=0;
        this.query = " SELECT LAST_INSERT_ID() as last "  ;
        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery(this.query);
            if (rs.next()) {
              last=   rs.getInt("last");
            } else {
              last= 0;
            }

        } catch (SQLException e) {
             System.out.println( ColorCodes.ANSI_RED +"ERROR in SQL: "    + this.query);
            //e.printStackTrace();
        }
        this.lastInsertId = last;
        this.fields_values = "";
        return last;
    }
    
    public int Max(String field) {
        ResultSet rs  ;
        Statement statement;
        this.query = "SELECT MAX(" + field + ")  AS maxx FROM " + this.entity;
        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery(this.query);
            if (rs.next()) {
                return rs.getInt("maxx");
            } else {
                return 0;
            }

        } catch (SQLException e) {
              System.out.println( ColorCodes.ANSI_RED +"ERROR in SQL: "    + this.query);
           // e.printStackTrace();
        }
        this.fields_values = "";
        return 0;

    }

       public ORM executeUpdate(String query) {
          this.query =query;
        int rs = 0;
        try {
            Statement statement = this.conn.createStatement();
           //  statement.setDate(2, dt1);
            rs = statement.executeUpdate(query);
           // this.saveLog(0,"",query);
        } catch (SQLException ex) {
            // ex.printStackTrace();
           // System.out.println("error: " + ex.getMessage());
             System.out.println( ColorCodes.ANSI_RED +"ERROR in SQL: "    + query);
        }
         //this.lastCountRecordUpdate=rs;
            this.fields_values = "";
            return this;

    }
       
    //   to do PreparedStatement
      // to do  CallableStatement      
     public ORM executeQuery(String query) {
             ResultSet rs = null;
        this.query =query;
        System.out.println("run: " + this.query);
        //this.curId = 0;
        Statement statement;
        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery(this.query);
            //this.saveLog(0,"",query);
        } catch (SQLException e) {
             System.out.println( ColorCodes.ANSI_RED +"ERROR in SQL: "    + this.query);
           // e.printStackTrace();
        }
            lastResultSet=rs;
            this.fields_values = "";
            return this;

    }
    
    public ORM where(String where) {

        ResultSet rs = null;
        if (where.equals("") ) {
            this.query = "SELECT * FROM  " + this.entity;
        } else {
            this.query = "SELECT * FROM  " + this.entity + "  WHERE   " + where;
        }

        System.out.println("run: " + this.query);
        this.curId = 0;
        Statement statement;
        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery(this.query);
           // System.out.println("select where: ok");

        /* try {
            System.out.println("lastResultSet22: "+rs.getString(0));
        } catch (SQLException ex) {
           System.out.println("lastResultSet22: none");
        }*/
            
            this.saveLog(0,"Просмотр","записей по условию или сортировке");
          //   System.out.println( ColorCodes.ANSI_RED +"==========ERROR in SQL: "    + this.query+"  "+ uid);
        } catch (SQLException e) {
             System.out.println( ColorCodes.ANSI_RED +"ERROR in SQL: "    + this.query);
           // e.printStackTrace();
        }
            lastResultSet=rs;
        this.fields_values = "";
         
           
        
        
        return this;

    }
    public ORM all() {
      return this.all("");
    }
    public ORM all(String orderBy) {

        ResultSet rs = null;
        if (orderBy.equals("")) {
            this.query = "SELECT * FROM  " + this.entity;
        } else {
            this.query = "SELECT * FROM  " + this.entity + "  ORDER BY   " + orderBy;
        }

        System.out.println("run: " + this.query);
        this.curId = 0;
        Statement statement;
        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery(this.query);
           // System.out.println("select all: ok");
            this.saveLog(0,"просмотр","всех записей");
        } catch (SQLException e) {
              System.out.println( ColorCodes.ANSI_RED +"ERROR in SQL: "    + this.query);
            //e.printStackTrace();
        }

        this.fields_values = "";
         lastResultSet=rs;
        return this;

    }
    
 
    public ResultSet lastResultSet;
      
    public void toTableModel(DefaultTableModel tmodel) {
      
         List<String> data = new ArrayList<>();
        try {
            while (lastResultSet.next()) {
                data.clear();
                for (String field_name : this.colums) {
                    data.add(lastResultSet.getString(field_name));
                }
          tmodel.addRow( data.toArray());
                // return data.toArray();
               
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
          //return data.toArray();
    }  
      
        public Vector toComboModel (String DefaultValue  )
    { 
       return toComboModel ("id","name",DefaultValue );
    }
         public  void  SetDefautValueCombo(Item item)   
       {
         //   System.out.println("SetDefautValueCombo defautValueCombo "  + item  );
          this.defautValueCombo=item;
 ;
       }    
    public   Item GetDefautValueCombo()   
       {
         //   System.out.println("GetDefautValueCombo defautValueCombo "  + defautValueCombo  );
           return this.defautValueCombo;
       }   
   public    Item defautValueCombo  ; 
        
   public Vector toComboModel ( String id_field,String name_field,String DefaultValue)
    { 
      Vector model = new Vector(); 
         Item item ;
        
        try {
            while (lastResultSet.next()) {
                 item = new Item( lastResultSet.getString(id_field),lastResultSet.getString(name_field) ,
                 DefaultValue.equals(lastResultSet.getString(id_field)));
                 model.addElement(item );
                // SetDefautValueCombo(item);
                 if ( item !=null && item.isDefault())
                 {  System.out.println("toComboModel:"+item  + lastResultSet.getString(id_field) );
                   SetDefautValueCombo(item);
                       
                 }
            }
        } catch (SQLException ex) {
          //  Logger.getLogger(ORM.class.getName()).log(Level.SEVERE, null, ex);
        }
 
   return model;
    }
    public String[] toArray (int num_field)
    {
        List rowValues = new ArrayList();
         try {
            while (lastResultSet.next()) {
                rowValues.add(lastResultSet.getString(num_field));   
            }
        } catch (SQLException ex) {
          //  Logger.getLogger(ORM.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println(ORM.class.getName()+": catch SQLException");
        }
  //System.out.println("toArray: "+rowValues.size());
   return(String[]) rowValues.toArray(new String[rowValues.size()]);
    }
}
