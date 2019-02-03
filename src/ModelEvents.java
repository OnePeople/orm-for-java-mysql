
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
public class ModelEvents extends Model {
//public String entity="users";

    public ModelEvents() {
            super("events");
            this.modelName = "Событие";
            this.LocalFields =Arrays.asList( "id_user"      );
            this.RelationFields =Arrays.asList( "name"      );
            this.RelationModels =Arrays.asList( new ModelUser()      );
            this.OnlyShow=true;
            this.fields = Arrays.asList( "name","id_record" ,"crud" ,"entity" ,"id_user" ,"id_user"    );  
            this.rules  = Arrays.asList("notempty", "notempty", "notempty", "notempty", "notempty", "notempty" )  ;
            this.TitleTable = Arrays.asList("id","Номер записи" ,"Таблица", "Примечание" ,"Пользователь","Создано", "Изменено" );
            this.SetTitleTable();    
    }

    public void Add(String eventName, Integer Uid) {
        this.setS("name", eventName).setI("id_user", Uid.toString()).save();
    }
}
