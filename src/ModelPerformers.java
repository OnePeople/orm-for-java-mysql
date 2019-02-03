
import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home
 */
public class ModelPerformers extends Model {
//public String entity="users";
public ModelPerformers()
        {   
            super("performers");
            this.modelName = "Исполнители";
            this.LocalFields =Arrays.asList( "id_city"      );
            this.RelationFields =Arrays.asList( "name"      );
            this.RelationModels =Arrays.asList( new ModelCity()      );
             
            this.fields = Arrays.asList( "name","id_city" ,"adress" ,"phone" ,"id_city"      );  
            this.rules  = Arrays.asList("notempty", "notempty", "notempty", "notempty", "notempty"  )  ;
            this.TitleTable = Arrays.asList("id","Название" ,"Город", "Адрес" ,"Телефон","Примечание","Создано", "Изменено" );
            this.SetTitleTable();    
        }
  
}
