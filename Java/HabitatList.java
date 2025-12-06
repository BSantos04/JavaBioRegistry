import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

// Create a class object to store all registered habitats
public class HabitatList implements Serializable{
    // Define class version and ArrayList object to store Habitats
    private static final long serialVersionUID = 1L;
    private HashSet<Habitat> habitatList = new HashSet<>();

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
            System.out.println("The Habitats database is empty!");
        }
    }

    // Return if a certain habitat is currently in the database
    public boolean verifyButInBoolean(String id){
        return habitatList.stream().filter(o->o.getHabitatID().equals(id)).findFirst().isPresent();
    }

    // Remove an habitat from the database
    public void removeHabitat(String id){
        Iterator<Habitat> it = habitatList.iterator();
        while(it.hasNext()){
            Habitat i = it.next();
            if(i.getHabitatID().equals(id)){
                it.remove();
                return;
            }
        }
    }

    // Check if an Habitat ID is present in the database
    public boolean checkHabitatID(String id){
        return habitatList.stream().filter(o->o.getHabitatID().equals(id)).findFirst().isPresent();
    }

    // Export the current data of the database into a .dat file
    public void saveDat(){
        try{
            File folder = new File("data");
            folder.mkdirs();

            FileOutputStream fos = new FileOutputStream("data/HabitatBioReg.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(habitatList);
            oos.close();
        }catch(Exception e){
            System.out.println("Error saving DAT file: "+e.getMessage());
        }
    }

    // Loads the .dat file containing the Species data form previous usage of the software
    public void loadDat(){
        File f = new File("data/HabitatBioReg.dat");
        if(!f.exists()){
            return;
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){

            habitatList = (HashSet<Habitat>) ois.readObject();

            ois.close();
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Error loading DAT file: "+e.getMessage());
        }
    }
}
