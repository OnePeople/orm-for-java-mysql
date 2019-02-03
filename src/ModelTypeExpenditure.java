
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
public class ModelTypeExpenditure extends Model {
//public String entity="users";
public ModelTypeExpenditure()
        {  
            super("type_expenditure");
            this.modelName="Тип расхода ";
            this.fields = Arrays.asList( "name"     );  
            this.rules  = Arrays.asList("notempty"   )  ;
            this.TitleTable = Arrays.asList("id","Название","Создано", "Изменено" );
            this.SetTitleTable();
        }
  
}
