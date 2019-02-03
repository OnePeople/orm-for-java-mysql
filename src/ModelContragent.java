
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
public class ModelContragent extends Model {
//public String entity="users";

    public ModelContragent() {
            super("contragent");
            this.modelName = "Контрагент";
            
            this.LocalFields =Arrays.asList( "id_city"      );
            this.RelationFields =Arrays.asList( "name"      );
            this.RelationModels =Arrays.asList( new ModelCity()      );
            
            this.fields = Arrays.asList( "name","id_city" ,"adress","phone" );  
            this.rules  = Arrays.asList("notempty","notempty","notempty","notempty" )  ;
            this.TitleTable = Arrays.asList("id","Название", "Город","Адресс","Телефон","Примечание","Создан" ,"Изменен"  );
            this.SetTitleTable();

    }

}
