
/**
 * class Item - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Item
{
   private int weight;
   private String description;
   private boolean equipped;
   
   public Item(int weight, String description){
       this.weight=weight;
       this.description=description;
       equipped = false;
    }
    
   public void equip(){
       equipped =true;
    }
    
   public void drop(){
       equipped = false;
    }
   public int getWeight(){
       return weight;
   }
    public String getDesc(){
        return description;
   }
   public void setWeight(int weight){
       this.weight=weight;
    }
}
