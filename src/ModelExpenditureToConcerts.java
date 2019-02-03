
import java.util.Arrays;



public class ModelExpenditureToConcerts extends Model {
//public String entity="users";
public ModelExpenditureToConcerts()
        {   
            super("expenditure_to_concerts");
            this.modelName="Расходы на концерт "; 
            this.LocalFields =Arrays.asList( "id_type" ,"id_concerts" ,"id_contragent"    );
            this.RelationFields =Arrays.asList( "name" , "name" ,"name"    );
            this.RelationModels =Arrays.asList( new ModelTypeExpenditure(), new ModelConcerts()  , new ModelContragent()    );  
           
           
            this.fields = Arrays.asList( "id_concerts","id_concerts" ,"id_type" ,"id_type" ,"id_contragent"  ,"id_contragent" ,"total_price"  ,"total_price" ,"make_price"  ,"make_price"     );  
            this.rules  = Arrays.asList("notempty", "notempty", "notempty", "notempty","notempty", "notempty", "notempty", "notempty", "notempty", "notempty"  )  ;
            this.TitleTable = Arrays.asList("id","Концерт" ,"Тип", "Контрагент" ,"Сумма", "Оплачено" , "Примечание" ,"Создано", "Изменено");
            this.SetTitleTable();
        }

}