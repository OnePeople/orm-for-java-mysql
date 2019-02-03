import javax.swing.JOptionPane;


public class ModalWindow {
	public   void infoBox(String Message, String titleBar)
	{
	    JOptionPane.showMessageDialog(null, Message,  titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
public   void errorBox(String Message, String titleBar)
	{
	    JOptionPane.showMessageDialog(null, Message,  titleBar, JOptionPane.ERROR_MESSAGE);
	}

public   int YesNoBox(String Message, String titleBar)
	{
	   int res= JOptionPane.showConfirmDialog(null, Message,   titleBar, JOptionPane.YES_NO_OPTION);
	   System.out.println("YesNoBox: res"+ res);
	    return res;
	}

}
