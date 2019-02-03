
import java.util.Arrays;

/**
 *
 * @author Home
 */
public class ModelUser extends Model {
//public String entity="users";

    public ModelUser() {
        super("users");
        this.modelName = "Пользователь";
        this.fields = Arrays.asList("login","name","phone","acess","acess","password" );  
        this.rules  = Arrays.asList("notempty","notempty","notempty","onlydigit","notempty","notempty")  ;
        this.TitleTable = Arrays.asList("id","Логин","Имя","Телефон","Уровень доступа","Пароль","Создано","Изменено");
         
        this.LocalFields =Arrays.asList( "acess"      );
        this.RelationFields =Arrays.asList( "name"      );
        this.RelationModels =Arrays.asList( new ModelAccessLevels()      );
        this.SetTitleTable();
    }
  // public String[]  
    //public String[] 
}
