class Item
{
   private String id;
        private String description;
    private Boolean isDefault;
 
        public Item(String id, String description, Boolean isDefault)
        {
            this.id = id;
            this.description = description;
             this.isDefault = isDefault;
        }
 
        public String getId()
        {
            return id;
        }
 
        public String getDescription()
        {
            return description;
        }
         public Boolean isDefault()
        {
            return isDefault;
        }
        public String toString()
        {
            return description;
        }
}