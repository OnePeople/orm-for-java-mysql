
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
public class ModelConcerts extends Model {
//public String entity="users";
public ModelConcerts()
        {   
            super("concerts");
           this.modelName="Концерт";
            this.LocalFields =Arrays.asList( "id_country"      );
            this.RelationFields =Arrays.asList( "name"      );
            this.RelationModels =Arrays.asList( new ModelCountry()      );
            
            this.fields = Arrays.asList( "name","id_country" ,"date_concert" );// ,"date_concert" 
            this.rules  = Arrays.asList("notempty","notempty","notempty" )  ;//,"date"
            this.TitleTable = Arrays.asList("id","Название","Дата концерта", "Создан","Изменен" );
            this.SetTitleTable();
        }
  
}
