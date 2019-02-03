
import java.util.Arrays;


public class ModelTickets extends Model {

public ModelTickets()
        {   super("tickets");
            this.modelName="Билеты";
            this.LocalFields =Arrays.asList( "id_concerts"      );
            this.RelationFields =Arrays.asList( "name"      );
            this.RelationModels =Arrays.asList( new ModelConcerts()      ); 
            
            
            this.fields = Arrays.asList( "id_concerts" , "price" ,"type","count_total","count_total","count_sales" ,"count_sales"     );  
            this.rules  = Arrays.asList("notempty", "notempty", "notempty", "notempty", "notempty", "notempty", "notempty"   )  ;
            this.TitleTable = Arrays.asList("id","Концерт", "Тип билета" ,"Стоимость билета" ,"Всего билетов","Продано билетов" ,"Примечание","Создано", "Изменено" );
            this.SetTitleTable();
        }
  
}
