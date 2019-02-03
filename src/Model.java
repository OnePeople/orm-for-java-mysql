
import com.toedter.calendar.JDateChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Model extends ORM {
    /*
    "notempty"
    "any"
    "float"
    "max180"
    "min_180"
    "max180"
    "min_180"
    "min_12"
    "max12"
    "plusint"
    "email"
    "date"
    
    */
    //public String[]  rules  ;
    //public String[]  fields  ; 
    public List<String> LocalFields ;
    public List<String> RelationFields ;
    public List<Model> RelationModels ;

     
    public List<String> rules  ;
    public List<String> fields  ;
    public List<String> TitleTable ;
    //public  String modelName;
    public JScrollPane spaneltable;
    public JTable dtable;
    public Object[] TitleField = new Object[]{};
    public DefaultTableModel tmodel;
    private ModalWindow MW = new ModalWindow();

    public final JPanel FieldOnPanel = new JPanel();
  //public final JScrollPane FieldOnScrollPanel = new JScrollPane(FieldOnPanel);

    public List<JLabel> labels = new ArrayList<JLabel>();
    public List<JTextField> inputs = new ArrayList<JTextField>();

     
    public JFrame MainViewWin;
    public JDialog EditCreateWindow; //new JFrame("Courses taught by this examiner"); 
    public JButton saveCloseBtn = new JButton("Сохранить");
    public JButton closeButton = new JButton("Отмена");
     public JButton delCloseBtn = new JButton("Удалить");
    public int current_row_id;
    
    /*
    public   void setSelectedValue(JComboBox<Item> comboBox, int value)
{
    Item item;
    for (int i = 0; i < comboBox.getItemCount(); i++)
    {
        item = (Item)comboBox.getItemAt(i);
        if (item.getId() == value)
        {
            comboBox.setSelectedIndex(i);
            break;
        }
    }
}*/
    
    public void fullsize()
    {
       //  this.jPanel1.setBackground(Color.yellow);
      //  this.jPanel1.setSize(new Dimension(1400, 900));
         spaneltable.setPreferredSize( new Dimension( MainViewWin.getWidth()-50, MainViewWin.getHeight()-50));
        // dtable.setPreferredSize(MainViewWin.getSize());
   //   dtable.setPreferredSize(  new Dimension( MainViewWin.getWidth()-100, MainViewWin.getHeight()-100));
         dtable.revalidate();
         spaneltable.revalidate();  
    }
   public Boolean OnlyShow=false; 
    public void CreateFieldOnPanel(int row_id, boolean isNew) {
        int number = 0;
         this.FieldOnPanel.removeAll();
        //  this.TitleField=(Object[])this.colums.toArray();
        JPanel InputPanel = new JPanel(/*new FlowLayout(FlowLayout.RIGHT)*/);
        JPanel LabelPanel = new JPanel(/*new FlowLayout(FlowLayout.LEFT)*/);
         List<String> ValuesList;
        InputPanel.setLayout(new BoxLayout(InputPanel, BoxLayout.Y_AXIS));
        LabelPanel.setLayout(new BoxLayout(LabelPanel, BoxLayout.Y_AXIS));
        
       SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        //  FieldOnPanel.setLayout(null);
        if (isNew) ValuesList=null; 
        else  ValuesList= getRowByValue(this.tmodel,row_id);
        int index=0;
       
        labels.clear();
        inputs.clear();
        Boolean UpdateTitle=true;
        if (this.TitleTable ==null || this.TitleTable.size()!=this.TitleField.length) UpdateTitle=false;
        current_row_id=-1;   
        for (String item : this.colums) { 
            // number = labels.size() + 1;
            JLabel label;
           if (UpdateTitle)
             label = new JLabel(this.TitleTable.get(index));
           else 
             label = new JLabel(item);
           // ValuesList.get(index);
           // System.out.println("CreateFieldOnPanel: index="+ index); 
            // if(isNew) 
                 
          final   JTextField input;
         
            String fieldtext="";
            if (isNew ||  (item.equals("password") ))    fieldtext="" ;
            else fieldtext=ValuesList.get(index) ;
            
            input = new JTextField(fieldtext );
            labels.add(label);
            inputs.add(input);
            if(item.equals("id") || item.equals("date_add")  || item.equals("date_edit")) 
                input.setEditable(false);
            if(this.OnlyShow)   input.setEditable(false);
           if(!isNew && item.equals("id")  ) 
                  current_row_id= Integer.parseInt(  input.getText() ) ;
           
               //  label.setAlignmentX(JLabel.LEFT_ALIGNMENT);
            //  input.setAlignmentX(JTextField.RIGHT_ALIGNMENT);

            label.setPreferredSize(new Dimension(270, 20));
            input.setPreferredSize(new Dimension(270, 25));
              //   label.setLocation(100+44,100 ); 
            //  input.setLocation(100+55,10 ); 
            LabelPanel.add(label);
           // InputPanel
            LabelPanel.add(input);
          
           if (item.equals("date_concert") ) 
           {     Date date;
               com.toedter.calendar.JDateChooser   jDateChoose= new com.toedter.calendar.JDateChooser( );   
                try {
                   date =    formatter.parse(fieldtext ) ;
                   jDateChoose.setDate(date);
                } catch (ParseException ex) {
                  //  Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                }
                
          
          jDateChoose.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            JDateChooser chooser = (JDateChooser)evt.getSource();
                      input.setText(formatter.format(chooser.getDate()));
            }
       
             });
              jDateChoose.setPreferredSize(new Dimension(270, 25));
       if(this.OnlyShow)   jDateChoose.setEnabled(false);
               input.setVisible(false);
               LabelPanel.add(jDateChoose );
           }
            int relationindex=-1;
          if (LocalFields!=null) 
              for (String LocalField : this.LocalFields ) {
           
                  relationindex++;
           if (!item.equals(LocalField) )   continue ; 
           System.out.println("CreateFieldOnPanel: замена выпадающим списком LocalField="+LocalField+" i="+ relationindex);
             // Model  RelationModel= RelationModels.get(relationindex); /* new ModelCountry()*/;
                   // ResultSet rs=  Mc.all("name");
                  //  String[] Strings =  Mc.toArray(rs, 2);
                     
                    JComboBox petList1 = new JComboBox(RelationModels.get(relationindex).all(RelationFields.get(relationindex) 
                     /*"name"*/).toComboModel(fieldtext ));
                     petList1.setRenderer( new ItemRenderer() );
                 if( RelationModels.get(relationindex).GetDefautValueCombo()!=null)
                    petList1.getModel().setSelectedItem( RelationModels.get(relationindex).GetDefautValueCombo()); 
                      
                       Item item1 = (Item)petList1.getSelectedItem();
              if(item1!=null)   input.setText(item1.getId() );
                
                   petList1.addActionListener( 
                    new ActionListener() {
                        
                         public void actionPerformed(ActionEvent e)
                            {
                                JComboBox comboBox = (JComboBox)e.getSource();
                                Item item = (Item)comboBox.getSelectedItem();
                                // System.out.println( item.getId() + " : " + item.getDescription() );
                                input.setText(item.getId() );
                            }
                    }
                    
                    ); 
                    input.setVisible(false);
                    petList1.setPreferredSize(new Dimension(270, 25));
                    LabelPanel.add(petList1 );
                    if(this.OnlyShow)   petList1.setEditable(false);
           }
           
           
           
           
           
           
           
           
           
           /*
           
           
               if (item.equals("id_country") ) 
                {   
                    ModelCountry Mc= new ModelCountry();
                   // ResultSet rs=  Mc.all("name");
                  //  String[] Strings =  Mc.toArray(rs, 2);
                     
                    JComboBox petList1 = new JComboBox(Mc.all("name").toComboModel(fieldtext ));
                     petList1.setRenderer( new ItemRenderer() );
                 if( Mc.GetDefautValueCombo()!=null)
                    petList1.getModel().setSelectedItem( Mc.GetDefautValueCombo()); 
                      
                       Item item1 = (Item)petList1.getSelectedItem();
                       input.setText(item1.getId() );
                   petList1.addActionListener( 
                    new ActionListener() {
                        
                         public void actionPerformed(ActionEvent e)
                            {
                                JComboBox comboBox = (JComboBox)e.getSource();
                                Item item = (Item)comboBox.getSelectedItem();
                                // System.out.println( item.getId() + " : " + item.getDescription() );
                                input.setText(item.getId() );
                            }
                    }
                    
                    ); 
                    input.setVisible(false);
                    petList1.setPreferredSize(new Dimension(270, 25));
                    LabelPanel.add(petList1 );
                }    
                            
                            
                 if (item.equals("id_city") ) 
                {   
                    ModelCity Mc= new ModelCity();
                   // ResultSet rs=  Mc.all("name");
                  //  String[] Strings =  Mc.toArray(rs, 2);
                     
                    JComboBox petList1 = new JComboBox(Mc.all("name").toComboModel(fieldtext ));
                     petList1.setRenderer( new ItemRenderer() );
                 if( Mc.GetDefautValueCombo()!=null)
                    petList1.getModel().setSelectedItem( Mc.GetDefautValueCombo()); 
                      
                       Item item1 = (Item)petList1.getSelectedItem();
                       input.setText(item1.getId() );
                   petList1.addActionListener( 
                    new ActionListener() {
                        
                         public void actionPerformed(ActionEvent e)
                            {
                                JComboBox comboBox = (JComboBox)e.getSource();
                                Item item = (Item)comboBox.getSelectedItem();
                                // System.out.println( item.getId() + " : " + item.getDescription() );
                                input.setText(item.getId() );
                            }
                    }
                    
                    ); 
                    input.setVisible(false);
                    petList1.setPreferredSize(new Dimension(270, 25));
                    LabelPanel.add(petList1 );
                }            
           
           
               if (item.equals("id_concerts") ) 
                {   
                    ModelConcerts Mc= new ModelConcerts();
                   // ResultSet rs=  Mc.all("name");
                  //  String[] Strings =  Mc.toArray(rs, 2);
                     
                    JComboBox petList1 = new JComboBox(Mc.all("name").toComboModel(fieldtext ));
                     petList1.setRenderer( new ItemRenderer() );
                 if( Mc.GetDefautValueCombo()!=null)
                    petList1.getModel().setSelectedItem( Mc.GetDefautValueCombo()); 
                      
                       Item item1 = (Item)petList1.getSelectedItem();
                       input.setText(item1.getId() );
                   petList1.addActionListener( 
                    new ActionListener() {
                        
                         public void actionPerformed(ActionEvent e)
                            {
                                JComboBox comboBox = (JComboBox)e.getSource();
                                Item item = (Item)comboBox.getSelectedItem();
                                // System.out.println( item.getId() + " : " + item.getDescription() );
                                input.setText(item.getId() );
                            }
                    }
                    
                    ); 
                    input.setVisible(false);
                    petList1.setPreferredSize(new Dimension(270, 25));
                    LabelPanel.add(petList1 );
                }           
           
               if (item.equals("id_performers") ) 
                {   
                    ModelPerformers Mp= new ModelPerformers();
                 //   ResultSet rs=  Mp.all("name");
                  //  String[] Strings =  Mp.toArray(rs, 2);
                    JComboBox petList2 = new JComboBox( Mp.all("name").toComboModel(fieldtext ));
                    petList2.setRenderer( new ItemRenderer() );
                     if( Mp.GetDefautValueCombo()!=null)
                     petList2.getModel().setSelectedItem( Mp.GetDefautValueCombo()); 
                  
                    Item item2 = (Item)petList2.getSelectedItem();
                     input.setText(item2.getId() );
                    petList2.addActionListener( 
                    new ActionListener() {
                        
                         public void actionPerformed(ActionEvent e)
                            {
                                JComboBox comboBox = (JComboBox)e.getSource();
                                Item item = (Item)comboBox.getSelectedItem();
                                input.setText(item.getId() );
                              //  System.out.println( item.getId() + " : " + item.getDescription() );
                            }
                    }
                    
                    );
                    
                    //  petList2.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
                    input.setVisible(false);
                    petList2.setPreferredSize(new Dimension(270, 25));
                    LabelPanel.add(petList2 );
                } 
           
           
           
           
           */
           
           
           
           LabelPanel.setPreferredSize( new Dimension(300,450));
                   //set ( new Dimension(200, 25));
           
        //   LabelPanel.setBackground(new Color( index*5,index*5 ,index*5 ));
           LabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
           FieldOnPanel.add(LabelPanel);
            index++;
        }
        // index=0;
          // for (String field_name :  getRowByValue(tmodel,id)) {index++;
           //                       System.out.println("CreateFieldOnPanel: value="+ field_name+" index "+index); 
           //                   }
        
        
       
        // LabelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
      //  FieldOnPanel.add(InputPanel);
 
        // FieldOnPanel.add(jDateChooser1)
        ;
        
      //  LabelPanel.setSize(200, 400);
       // InputPanel.setSize(200, 400);
       // FieldOnPanel.setSize(200, 500);
      //FieldOnPanel.setPreferredSize( new Dimension(300,500));
                 
         //  FieldOnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        for(ActionListener act : delCloseBtn.getActionListeners()) {
    delCloseBtn.removeActionListener(act);
}
       for(ActionListener act : saveCloseBtn.getActionListeners()) {
    saveCloseBtn.removeActionListener(act);
}
             
       for(ActionListener act : closeButton.getActionListeners()) {
    closeButton.removeActionListener(act);
}
                    
       
        delCloseBtn.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e)
            { //System.out.println("delCloseBtn: id= "+current_row_id);
              
                 if ( MW.YesNoBox("Вы дейсвительно хотите удалить?","Подтверждение")==0) 
                 { DoDelete(current_row_id) ;
                 EditCreateWindow.setVisible(false);
                 }
            }
        }); 
        
        
        saveCloseBtn.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            { Boolean res;
              if (isNew) res=  DoCreate(); else  res=  DoUpdate(current_row_id) ;
              if(res)  EditCreateWindow.setVisible(false);
            }
        }); 
       
        
          closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                 EditCreateWindow.setVisible(false);
            }
        });       
    if(!this.OnlyShow)     FieldOnPanel.add(saveCloseBtn);
         if (!isNew && !this.OnlyShow) FieldOnPanel.add(delCloseBtn);
        FieldOnPanel.add(closeButton);

        FieldOnPanel.revalidate();
        if (isNew) 
        EditCreateWindow = new JDialog(this.MainViewWin, "Создать "+modelName, true);
        else 
         EditCreateWindow = new JDialog(this.MainViewWin, "Редактировать "+modelName, true);
        
        EditCreateWindow.add(FieldOnPanel);

      //    EditCreateWindow.getContentPane().add(rightPanel,BorderLayout.EAST);
        EditCreateWindow.setSize(370, 550);

        EditCreateWindow.setLocationRelativeTo(null);
        EditCreateWindow.setResizable(false);
        EditCreateWindow.setVisible(true);
        EditCreateWindow.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("jdialog window closed event received");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("jdialog window closing event received");
            }
        });

    }

    public void DeleteFieldOnPanel() {
        int index = 0;
        if (labels.size() > 0) {
            for (String item : this.colums) {
                index = labels.size() - 1;
                JLabel label = labels.remove(index);
                JTextField input = inputs.remove(index);
                FieldOnPanel.remove(label);
                FieldOnPanel.remove(input);
                FieldOnPanel.repaint();
                FieldOnPanel.revalidate();

            }
        }

    }

    
      public List<String> getRowByValue(DefaultTableModel model, int j) {
       
       List<String> colums_ = new ArrayList<>();
      // System.out.println("model.getColumnCount()"+model.getColumnCount());
      
       if (j<0) return colums_;
        for (int i = 0; i< model.getColumnCount(); i++) {
            
            
            if ( model.getValueAt( j,i)!= null && !model.getValueAt( j,i).toString().isEmpty()) {
                      colums_.add(  model.getValueAt( j,i).toString());
                }
            else
            colums_.add("");
            
        }

        return colums_;

    }
    
    
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
    }

    
    public void SetTitleTable()
    { // System.out.println("SetTitleTable:  TitleTable.size()=" + this.TitleTable.size()
       //  +"this.TitleField.length="+this.TitleField.length);
         if (this.TitleTable ==null || this.TitleTable.size()!=this.TitleField.length) return;
         int i=0;
       
         for (String title : this.TitleTable)
                {
                    JTableHeader th = this.dtable.getTableHeader();
                    TableColumnModel tcm = th.getColumnModel();
                    TableColumn tc = tcm.getColumn(i);
                    tc.setHeaderValue( title );
                    th.repaint();
                    i++;
                }
    }
    public Model(String entity) {

        super(entity);
             // load colums names 
        // System.out.println("Model: " + this.entity);

        this.colums = this.getColums();
        this.TitleField = (Object[]) this.colums.toArray();
    //  this.spaneltable.setPreferredSize(new Dimension(600, 350));

        this.tmodel = new DefaultTableModel(TitleField, 0);

        this.dtable = new JTable(tmodel) {

           private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            } /**/
        };
        
        
        this.dtable.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
        this.spaneltable = new JScrollPane(dtable);
        this.uid=Auth.getInstance().uid ;
        this.TableActionListener();
    }
  //public  Object[]  TitleField=  new Object[]{"id","Город","Страна","Регион","Область","Широта","Долгота","Часовой пояс","id Country"};

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
       this.all("").toTableModel( this.tmodel);
     //  tmodel.revalidate();
       MainViewWin.setVisible(true);
       dtable.repaint();
       dtable.setVisible(true);
      }

    public void DoDelete(int id) {

          if (MW.YesNoBox("Вы дейсвительно хотите удалить данные?", "Подтверждение") != 0) 
            return;
          
         //SetCurId(current_row_id ) ;
        
         System.out.println("DoDelete: id= "+id);
         delete(id);
         if(this.lastCountRecordUpdate>0)
	   MW.infoBox("Запись была успешно удалена","Редактирование записи");
        else 
           MW.errorBox("Запись невозможно удалить. Обратитесь к администратару","Редактирование записи");
 
        Fill();
        
    }

    
    
     
    public Boolean DoUpdate(int id) {
        int index=-1;
        String txt="";//current_row_id
        SetCurId( id) ;
      ///  if (id==0)id= this.i
       for (String column : this.colums) {
           index++;
           txt= inputs.get(index).getText();
            if(  ! Validate.CheckFieldValue(column,txt,this.rules,this.fields)) return false; 
            
              if(column.equals("id") ||column.equals("date_add")  )  continue;
           if(column.equals("password") ) 
                this.setS(column, Auth.md5(txt)) ;
               else
                if( column.equals("date_edit"))  
                    setI(column, "NOW()") ;
                    else
                    this.setS(column, txt) ;
            
        }
         update(id);
      
        if(this.lastCountRecordUpdate>0)
	   MW.infoBox("Запись была успешно отредактирована","Редактирование записи");
        else 
             MW.errorBox("Запись невозможно изменить. Обратитесь к администратару","Редактирование записи");
           Fill(); 
// this.Update(this.find(Integer.parseInt(data[0])))	;	
            return true; 
    }
   public Boolean DoCreate()  {  
       int index=-1;
       String txt="";
       this.fields_values = "";
          for (String column : this.colums) { 
              index++;
              // System.out.println("DoCreate" + item+" ="+txt+" this.fields_values="+this.fields_values+" "+(item!="id"));
            txt= inputs.get(index).getText();
           if(  ! Validate.CheckFieldValue(column,txt,this.rules,this.fields)) return false;
           if(column.equals("id") ||column.equals("date_add") || column.equals("date_edit"))  continue;
           if(column.equals("password") ) 
                this.setS(column, Auth.md5(txt)) ;
               else
                this.setS(column, txt) ;
           
        }    
       
       save();
       
        if(this.lastCountRecordUpdate>0)
	   MW.infoBox("Запись была успешно создана","Редактирование записи");
        else 
             MW.errorBox("Запись невозможно создать. Обратитесь к администратару","Редактирование записи");
    Fill(); 
       
       return true; 
    }

  
}
