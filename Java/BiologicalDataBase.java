import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.io.*;

// Create a class object that will serve as the "database" itself
public class BiologicalDataBase implements Serializable{ 
    // Define class version and ArrayList object to store Species
    private static final long serialVersionUID = 1L;
    private ArrayList<Species> database = new ArrayList<>();

    // Insert a new species into the database
    public void insertSpecies(Species species){
        database.add(species);
        System.out.println("\nSpecies added sucessfully.\n");
    }

    // Check if a certain species is in the database by it's scientific name
    public void verifyByID(String id){
        String ID = id.trim().toLowerCase();
        if(verifyButInBoolean(ID)==true){
            System.out.println("This species is in the database.");
        }
        else{
            System.out.println("Species not found.\n");
        }
    }

    // Get the detailed information of a species based on it's scientific name
    public void obtainDetailedInfo(String id){
        Iterator<Species> it = database.iterator();
        while(it.hasNext()){
            Species i = it.next();
            if(i.getSpeciesID().equals(id)){
                i.display();
                return;
            }
            else{
                System.out.println("Species not found.\n");
            }
        }
    }

    // Display every species present in the database and their respective information
    public void showAll(){
        if(database.size()!=0){
            for(Species i: database){
                i.display();
                System.out.println("-------------------------------------------------------------");
            }
        }
        else{
            System.out.println("The Species database is empty.\n");
        }
    }

    // Get every species from a certain category.
    public void speciesByCategory(String category){
        int total=0;
        for(Species i: database){
            if(i.getCategory().equals(Species.parseCategory(category))){
                total+=1;
            }
        }
        if(category.trim().isEmpty()) System.out.println("There are a total of "+total+" species from the 'unknown' category in the database.");

        else System.out.println("There are a total of "+total+" species from the '"+category+"' category in the database.");
    }

    // Display the genetic variety indide the database 
    // It will be based on the genetic characteristics of the species
    public void totalGeneticDiversity(){
        if(database.isEmpty()) {
            System.out.println("The Species database is empty.");
            return;
        }
        HashSet<GenChar> genSet = new HashSet<GenChar>();
        for(Species i: database){
            GenChar g = i.getGenChar();
            if(g!=null) genSet.add(g);
        }
        System.out.println("We found "+genSet.size()+" different genetic characteristic(s) in the database.");
    }

    // Display every species from a specific habitat
    // It will have in consideration the only the habitat name
    public void associatedByHabitat(String habitatName){
        if(database.size()!=0){
            for(Species i: database){
                if(i.getHabitat().getHabitatName().equals(Habitat.parseHabitatName(habitatName))){
                    System.out.println(i.getScientificName());
                }
            }
        }
        else{
            System.out.println("We couldn`t find any species associated with this habitat.");
        }
    }

    // Return the current number of registered species inside the database
    public int getSize(){
        return database.size();
    }

    // Add a list of species and respective information to the database
    public void addList(ArrayList<Species> speciesList){
        database.addAll(speciesList);
        System.out.println("List added sucessfully!");
    }

    // Return if a certain species is currently in the database
    public boolean verifyButInBoolean(String id){
        return database.stream().filter(o->o.getSpeciesID().equals(id)).findFirst().isPresent();
    }

    // Remove a species from the database 
    // It will search by it's scientific name
    public void removeSpecies(String id){
        Iterator<Species> it = database.iterator();
        while(it.hasNext()){
            Species i = it.next();
            if(i.getSpeciesID().equals(id)){
                it.remove();
                return;
            }
        }
    }

    // Check if a Species ID is present in the database
    public boolean checkSpeciesID(String id){
        return database.stream().filter(o->o.getSpeciesID().equals(id)).findFirst().isPresent();
    }
    
    // Export the current Species data into a .dat file
    public void saveDat(){
        try{
            File folder = new File("data");
            folder.mkdirs();

            FileOutputStream fos = new FileOutputStream("data/SpeciesBioReg.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(database);
            oos.close();
        }catch(Exception e){
            System.out.println("Error saving DAT file: "+e.getMessage());
        }
    }

    // Loads the .dat file containing the Species data form previous usage of the software
    public void loadDat(){
        File f = new File("data/SpeciesBioReg.dat");
        if(!f.exists()) return;

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){
            database = (ArrayList<Species>) ois.readObject();

            ois.close();
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Error loading DAT file: "+e.getMessage());
        }
    }
}