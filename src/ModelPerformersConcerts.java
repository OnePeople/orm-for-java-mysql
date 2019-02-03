
import java.util.Arrays;



public class ModelPerformersConcerts extends Model {
//public String entity="users";
public ModelPerformersConcerts()
        {   
            super("performers_to_concerts");
           this.modelName="Участники концертов";
            this.LocalFields =Arrays.asList( "id_performers","id_concerts"     );
            this.RelationFields =Arrays.asList( "name"  ,"name"      );
            this.RelationModels =Arrays.asList( new ModelPerformers(),  new ModelConcerts()    );
            
            
            this.fields = Arrays.asList( "id_performers","id_concerts" ,"id_performers" ,"id_concerts" ,"price"  ,"price"     );  
            this.rules  = Arrays.asList("notempty", "notempty", "notempty", "notempty", "notempty", "notempty"  )  ;
            this.TitleTable = Arrays.asList("id","Участник" ,"Концерт", "Гонорар" ,"Создано", "Изменено" );
            this.SetTitleTable();
        }

}