import java.util.Arrays;

 
public class ModelCity extends Model {
 
public ModelCity()
        {  
            super("city");
            this.modelName="Город";
            this.LocalFields =Arrays.asList( "id_country"      );
            this.RelationFields =Arrays.asList( "name"      );
            this.RelationModels =Arrays.asList( new ModelCountry()      );
            
            this.fields = Arrays.asList( "name","id_country"  );  
            this.rules  = Arrays.asList("notempty","notempty" )  ;
            this.TitleTable = Arrays.asList("id","Название","Страна", "Создан","Изменен" );
            this.SetTitleTable();
        } 
  
}
