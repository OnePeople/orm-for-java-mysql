
import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

class ItemRenderer extends BasicComboBoxRenderer
    {
        public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);
 
            if (value != null)
            {
                Item item = (Item)value;
                 if (item!=null)
               setText( item.getDescription() ); 
                // if ( item.isDefault())isSelected=true;
                // else isSelected=false;
            }
 
            if (index == -1)
            {
                Item item = (Item)value;
                if (item!=null)
                setText(  item.getDescription()   );
               
            } 
             
            
 
 
            return this;
        }
    }
 