
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import java.util.List;
 
import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
 
public class Reports {

    public List<String> LocalFields;
    public List<String> RelationFields;
    public List<Model> RelationModels;

    public List<String> rules;
    public List<String> fields;
    public List<String> TitleTable;
    public String modelName;
    public JScrollPane spaneltable;
    public JTable dtable;
    public Object[] TitleField = new Object[]{};
    public DefaultTableModel tmodel;
   
    public final JPanel FieldOnPanel = new JPanel();
   
    public JFrame MainViewWin;
    public JDialog EditCreateWindow; //new JFrame("Courses taught by this examiner"); 
    public JButton saveCloseBtn = new JButton("Сохранить");
    public JButton closeButton = new JButton("Отмена");
    public JButton delCloseBtn = new JButton("Удалить");
    public int current_row_id;
    public Boolean OnlyShow = false;
    public Connection conn;
    public String query;
    public String uid = "0";
    public List<String> colums= new ArrayList<>();;
    public List<String> data= new ArrayList<>();;
    public String Name ;
    public void fullsize() {
        //  this.jPanel1.setBackground(Color.yellow);
        //  this.jPanel1.setSize(new Dimension(1400, 900));
     //   System.out.println("MainViewWin: " + MainViewWin);
    //    MainViewWin.getWidth();
        if (spaneltable ==null ) return;
          spaneltable.setPreferredSize(new Dimension(MainViewWin.getWidth() - 50, MainViewWin.getHeight() - 50));
        //  dtable.setPreferredSize(MainViewWin.getSize());
    /// dtable.setPreferredSize(new Dimension(MainViewWin.getWidth() - 100, MainViewWin.getHeight() - 100));
        dtable.revalidate();
        spaneltable.revalidate();
    }
/*
    public List<String> getRowByValue(DefaultTableModel model, int j) {

        List<String> colums_ = new ArrayList<>();
        // System.out.println("model.getColumnCount()"+model.getColumnCount());

        if (j < 0) {
            return colums_;
        }
        for (int i = 0; i < model.getColumnCount(); i++) {

            if (model.getValueAt(j, i) != null && !model.getValueAt(j, i).toString().isEmpty()) {
                colums_.add(model.getValueAt(j, i).toString());
            } else {
                colums_.add("");
            }

        }

        return colums_;

    }
*/
    /* 
     public void TableActionListener() {
     this.dtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
     public void valueChanged(ListSelectionEvent event) {
     int rowid ;
     if(tmodel==null || dtable==null) return;
     rowid=dtable.getSelectedRow();
               
     if (rowid == -1)    return;
                          
     CreateFieldOnPanel(rowid, false);
          
     }

     });
     }*/
    public int RowCount;
    public Boolean ShowRowNumber=true;
    public Boolean  GetTitleAndData(String sql) {  
         System.out.println("CreateTible: " + this.Name);
          System.out.println("sql: " + sql);
        Statement stmt;
        ResultSet rs;
        ResultSetMetaData rsmd;
        String name;
        int columnCount = 0;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                        ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
            this.colums.clear();
             if (this.ShowRowNumber)   this.colums.add("№");
            for (int i = 1; i < columnCount + 1; i++) {
                          //name = rsmd.getColumnName(i);
                           name = rsmd.getColumnLabel(i);
                           //getColumnTypeName
                          this.colums.add(name);
                          System.out.println("getColumnName: " + name);
                      }
            this.TitleField = (Object[]) this.colums.toArray();
                //  this.spaneltable.setPreferredSize(new Dimension(600, 350));

            this.tmodel = new DefaultTableModel(TitleField, 0);

          

            //List<String> data = new ArrayList<>();
              RowCount=0;
              int delta=1;
            while (rs.next()) {
                data.clear();   
                int i=0;
               
               // System.out.println("i: " + i);
                for (String field_name : this.colums) {
                   // data.add(rs.getString(field_name));
                    if(field_name.equals("№"))
                    { data.add(Integer.toString(RowCount+1));delta=0;}
                    else data.add(rs.getString(i+delta) );//.getObject(i)
                    //System.out.println("field_name: " + rs.getString(i));
                    i++;
                }
                RowCount++;
            //  tmodel.setRowCount(RowCount-1);
                 //   tmodel.setNumRows(RowCount);
                tmodel.addRow(data.toArray());
            }
            System.out.println("RowCount: " + this.RowCount);
             System.out.println("getRowCount: " + tmodel.getRowCount());
         // ORM orm= new ORM();
         //orm.uid=
          Auth.getInstance().orm.saveLog(0,"Создание отчета ",this.Name);
                // return data.toArray();
        } catch (SQLException ex) {
            System.out.println( ColorCodes.ANSI_RED +"ERROR in SQL: "    + sql);
            ex.printStackTrace();
            return false;
        }
           
        return true;
    }

    
    public void CreateTible(String sql) {
        
        this.GetTitleAndData(sql);
       if( this.tmodel==null)
       {
         this.tmodel = new DefaultTableModel( this.TitleField, 0);  
       }
      
        
        this.dtable = new JTable(tmodel) {
          private static final long serialVersionUID = 1L;
          public boolean isCellEditable(int row, int column) {
                return false;
            } 
        };
        
        // .setMaxRowCount();
        //dtable.setR .setRowCount(RowCount);

        this.dtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.spaneltable = new JScrollPane(dtable);
         
        
        
         fullsize();
        return;

    }

    public Reports() {

        this.conn = ConnectDB.getInstance().conn;
    }
    //public  Object[]  TitleField=  new Object[]{"id","Город","Страна","Регион","Область","Широта","Долгота","Часовой пояс","id Country"};
/*
    public void clearT() {
        int rowCount = this.dtable.getRowCount();
        //Remove rows one by one from the end of the table
        // System.out.println("clearT: getRowCount=" + rowCount);
        if (rowCount > 0) {
            int rowid = this.dtable.getSelectedRow();
            // System.out.println("clearT: rowid=" + rowid);
            if (rowid >= 0) {
                this.dtable.removeRowSelectionInterval(rowid, rowid);
            }
            rowid = this.dtable.getSelectedRow();
            this.tmodel.getDataVector().removeAllElements();
            this.dtable.revalidate();
        }
    }

    public void Fill() {
        clearT();
        //  this.all("").toTableModel( this.tmodel);
        //  tmodel.revalidate();
        MainViewWin.setVisible(true);
        dtable.repaint();
        dtable.setVisible(true);
    }*/

}
