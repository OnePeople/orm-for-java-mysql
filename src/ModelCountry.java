
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home
 */
public class ModelCountry extends Model {
//public String entity="users";
public ModelCountry()
        {   super("country");
            this.modelName="Страна";
            
            
             this.fields = Arrays.asList( "name"  );  
            this.rules  = Arrays.asList("notempty"  )  ;
            this.TitleTable = Arrays.asList("id","Название" ,"Создан" ,"Изменен"  );
            this.SetTitleTable();         
        
        }
  
}
