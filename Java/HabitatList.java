import java.util.HashSet;

// Create a class object to store all registered habitats
public class HabitatList { 
    private HashSet<Habitat> habitatList;

    // Create the function to store the habitats inside a list
    public HabitatList(){
        habitatList=new HashSet<Habitat>();
    }

    // Add a new habitat to the list
    public void addHabitat(Habitat habitat){
        if(habitatList.add(habitat)) System.out.println("\nHabitat added sucessfully.");
        else System.out.println("\nThis habitat is already in the database.");
    }

    // Display all the habitats registered in the database
    public void showAllHabitats(){
        if(!habitatList.isEmpty()){
            for(Habitat i: habitatList){
                System.out.println("-------------------------------------------------------------");
                i.display();
            }
        }
        else{
            System.out.println("The database is empty!");
        }
    }
    
}
