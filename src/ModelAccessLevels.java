
import java.util.Arrays;
 
public class ModelAccessLevels extends Model {
 
public ModelAccessLevels()
        {   
            super("access_levels");
            this.modelName="Уровни досмтупа";
            this.fields = Arrays.asList( "name");
            this.rules  = Arrays.asList("notempty" ) ;
            this.TitleTable = Arrays.asList("id","Название","Дата концерта", "Создан","Изменен" );
            this.SetTitleTable();
        }
  
}
