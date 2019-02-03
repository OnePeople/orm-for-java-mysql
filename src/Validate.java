
import java.util.List;

// import javax.swing.JOptionPane;


public class Validate {
	
    public static boolean CheckFieldValue(String column,String value,List<String> rules,List<String> fields ) {
		ModalWindow MW= new ModalWindow();
 	 	// System.out.println("CheckFieldValue: column= "+column+
                   //      " value="+value+" rules="+rules.toString()+" fields="+fields.toString());
            //  System.out.println("CheckFieldValue:  rules.length= "+rules.size()+" fields.length="+fields.size()); 
                if(rules==null ||fields==null )return true;
 	if (rules.size()  !=fields.size() )	MW.errorBox( "Ошибка шаблона проверки","Ошибка в данных");
                  int i = -1;
                  
		//for(  i < fields.length;  )
                for (String field : fields)
                {i++;
                    System.out.println("CheckFieldValue: field= "+field+
                            " rules.get(i)= "+rules.get(i)+" rules.get(i)= "+fields.get(i)+" i="+i );
		//  System.out.println("CheckFieldValue:cicle column= "+rules.get(i)+" column="+column);
                 if( ! (field).equals(column))  continue;
                   //  System.out.println("CheckFieldValue:make rules= "+rules.get(i)+" column="+column+" field="+field);
                
                     System.out.println("CheckFieldValue:make column="+column+" rules= "+rules.get(i)+" i="+i+" field="+field +" value="+value );
                switch(rules.get(i ))
		{

		case "notempty": 
		if ( value== null ||  value.length() == 0)
		  {
			MW.errorBox( " Неверные данные для "+field+". Поле не может быть пустым","Ошибка в данных");
		  return false;
		  }; break;
		case "float": 
		if ( value== null ||  value.length() == 0 || (!value.matches("[-+]?[0-9]*\\.?[0-9]+")))
		  {
			MW.errorBox( "Неверные данные для "+field+". Данные должны быть float","Ошибка в данных");
		  return false;
		  }; break;  
		
                 case "date": 
		if ( value== null ||  value.length() == 0 || (value.matches("^(((0[1-9]|[12]\\d|3[01])\\.(0[13578]|1[02])\\.((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\.(0[13456789]|1[012])\\.((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\.02\\.((19|[2-9]\\d)\\d{2}))|(29\\.02\\.((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$")))
		  {
			MW.errorBox( "Неверные данные для "+field+". Данные должны быть в формате 31.12.1990","Ошибка в данных");
		  return false;
		  }; break;   
                  
                  case "email":  
		if ( value== null ||  value.length() == 0 ||(!value.matches("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$")))
		  {
			MW.errorBox( "Неверные данные для "+field+".  Не является email  "
		                         +value.toString(),"Ошибка в данных");
		  return false;
		  }; break;     
  		case "onlydigit":  
		if ( value== null ||  value.length() == 0 ||(!value.matches("[0-9]")))
		  {
			MW.errorBox( "Неверные данные для "+field+".   должны быть только цифры  "
		                         +value.toString(),"Ошибка в данных");
		  return false;
		  }; break;                     
		case "plusint":  
		if ( value== null ||  value.length() == 0 ||(!value.matches("[-+]?[0-9]")))
		  {
			MW.errorBox( "Неверные данные для "+field+". Данные должны быть int>0  "
		                         +value.toString(),"Ошибка в данных");
		  return false;
		  }; break;  
		case "notzero": 
		if ( value== null ||  value.length() == 0|| value=="0")
		  {
			MW.errorBox(  "Неверные данные для "+field+". Поле не может быть пустым","Ошибка в данных");
		  return false;
		  }; break;	
		  
		case "max180":  
	 		if ( !( Float.parseFloat(value)<=180))
			  {
	 			MW.errorBox( " Неверные данные для "+field+
					  ". Данные должны быть "+Float.parseFloat(value)+"<=180","Ошибка в данных");
			  return false;
			  }; break;   
		case "min_180": 
	 		if ( !( Float.parseFloat(value)>=-180))
			  {
	 			MW.errorBox( " Неверные данные для "+field
					             +". Данные должны быть "+Float.parseFloat(value)+">=-180","Ошибка в данных");
			  return false;
			  }; break; 		  
		case "max12":  
	 		if ( !( Float.parseFloat(value)<=12))
				  {
	 			MW.errorBox( " Неверные данные для "+field+
						  ". Данные должны быть "+Float.parseFloat(value)+"<=12","Ошибка в данных");
				  return false;
				  }; break;   
		case "min_12": 
				
	 		if ( !( Float.parseFloat(value)>=-12))
				  {
				  MW.errorBox( " Неверные данные для "+field
						             +". Данные должны быть "+Float.parseFloat(value)+">=-12","Ошибка в данных");
				  return false;
				  }; break;   
		case "any": {}  
		}

	  
	 
	  
	  //  
	}
		
		return true;
		
	}
    
    
	public static boolean Check(String[] someList,String[] rules,String[] fields ) {
		ModalWindow MW= new ModalWindow();
 	System.out.println("Validate Check"+ someList.length+" "+rules.length+" "+fields.length+" ");
		
 	if (someList.length!=rules.length ||
 			someList.length!=fields.length||
 			fields.length!=rules.length  )	
 		MW.errorBox( "Ошибка шаблона проверки","Ошибка в данных");

		for(int i = 0;  i < someList.length; i++ ) {
		//	Controller.infoBox("fields="+ fields[i]+",someList="+someList[i]+",rules="+rules[i],"Ошибка в данных");
		switch(rules[i])
		{

		case "notempty": 
		if ( someList[i]== null ||  someList[i].length() == 0)
		  {
			MW.errorBox( " Неверные данные для "+fields[i]+". Поле не может быть пустым","Ошибка в данных");
		  return false;
		  }; break;
		case "float": 
		if ( someList[i]== null ||  someList[i].length() == 0 || (!someList[i].matches("[-+]?[0-9]*\\.?[0-9]+")))
		  {
			MW.errorBox( "Неверные данные для "+fields[i]+". Данные должны быть float","Ошибка в данных");
		  return false;
		  }; break;  

 		case "email":  
		if ( someList[i]== null ||  someList[i].length() == 0 ||(!someList[i].matches("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$")))
		  {
			MW.errorBox( "Неверные данные для "+fields[i]+". Данные должны быть int>0  "
		                         +someList[i].toString(),"Ошибка в данных");
		  return false;
		  }; break;                   
                    
		case "plusint":  
		if ( someList[i]== null ||  someList[i].length() == 0 ||(!someList[i].matches("[-+]?[0-9]")))
		  {
			MW.errorBox( "Неверные данные для "+fields[i]+". Данные должны быть int>0  "
		                         +someList[i].toString(),"Ошибка в данных");
		  return false;
		  }; break;  
		case "notzero": 
		if ( someList[i]== null ||  someList[i].length() == 0|| someList[i]=="0")
		  {
			MW.errorBox(  "Неверные данные для "+fields[i]+". Поле не может быть пустым","Ошибка в данных");
		  return false;
		  }; break;	
		  
		case "max180":  
	 		if ( !( Float.parseFloat(someList[i])<=180))
			  {
	 			MW.errorBox( " Неверные данные для "+fields[i]+
					  ". Данные должны быть "+Float.parseFloat(someList[i])+"<=180","Ошибка в данных");
			  return false;
			  }; break;   
		case "min_180": 
	 		if ( !( Float.parseFloat(someList[i])>=-180))
			  {
	 			MW.errorBox( " Неверные данные для "+fields[i]
					             +". Данные должны быть "+Float.parseFloat(someList[i])+">=-180","Ошибка в данных");
			  return false;
			  }; break; 		  
		case "max12":  
	 		if ( !( Float.parseFloat(someList[i])<=12))
			{
	 		     MW.errorBox( " Неверные данные для "+fields[i]+
						  ". Данные должны быть "+Float.parseFloat(someList[i])+"<=12","Ошибка в данных");
				  return false;
				  }; break;   
		case "min_12": 
				
	 		if ( !( Float.parseFloat(someList[i])>=-12))
				  {
				  MW.infoBox( " Неверные данные для "+fields[i]
						             +". Данные должны быть "+Float.parseFloat(someList[i])+">=-12","Ошибка в данных");
				  return false;
				  }; break;   
		case "any": {}  
		}

	  
	  
	  
	  //  
	}
		
		return true;
		
	}
}
