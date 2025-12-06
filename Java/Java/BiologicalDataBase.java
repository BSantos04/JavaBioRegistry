import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// Create a class object that will serve as the "database" itself
public class BiologicalDataBase { 
    private ArrayList<Species> database;

    // Create a new ArrayList of the type Species to store the data
    public BiologicalDataBase(){
        database= new ArrayList<Species>();
    }

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
    
    // Export the current data of the database into a CSV file
    public void exportCSV(){
        File folder = new File("data");
        folder.mkdirs();
        File csvFile = new File(folder+"/SpeciesJavaBioDB.csv");
        try(FileWriter writer = new FileWriter(csvFile)){
            writer.write("SpeciesID,ScientificName,CommonName,Category,ConservationStatus,RecentSightings,HabitatID,HabitatName,HabitatLocation,HabitatDescription,Locomotion,Diet,BodyCover,Reproduction\n");

            for(Species s: database){
                writer.write(
                escape(s.getSpeciesID()) + "," +
                escape(s.getScientificName()) + "," + 
                escape(s.getCommonName()) + "," + 
                escape(s.getCategory().name()) + "," + 
                escape(s.getConservationStatus().name()) + "," + 
                escape(s.getRecentSightings()) + "," +
                escape(s.getHabitat().getHabitatID()) + "," +
                escape(s.getHabitat().getHabitatName().name()) + "," + 
                escape(s.getHabitat().getLocation()) + "," +
                escape(s.getHabitat().getHabitatDescription()) + "," +
                escape(s.getGenChar().getLocomotion().name()) + "," + 
                escape(s.getGenChar().getDiet().name()) + "," + 
                escape(s.getGenChar().getBodyCover().name()) + "," + 
                escape(s.getGenChar().getReproduction().name()) + "\n");
            }
        }
        catch(IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }

    // Helper function to put quotation marks on values with commas
    public String escape(String s){
        if(s.contains(",") || s.contains("\"") || s.contains("\n")) {
            s = s.replace("\"", "\"\"");
            return "\"" + s + "\"";
        }
        return s;
    }


    public void loadCSV(){
        File csvFile = new File("data/SpeciesJavaBioDB.csv");
        if(!csvFile.exists()){
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
                String line = br.readLine();
                while((line = br.readLine())!=null){
                    String[] p = line.split(",", -1);
                    if(p.length<13) continue;

                    String sID = p[0];
                    String scientificName = p[1];
                    String commonName = p[2];
                    Species.Category category = Species.parseCategory(p[3]);
                    Species.ConservationStatus conservationStatus = Species.parseConservationStatus(p[4]);
                    String recentSightings = p[5];
                    String habitatID = p[6];
                    Habitat.HabitatName habitatName = Habitat.parseHabitatName(p[7]);
                    String habitatLocation = p[8];
                    String habitatDescription = p[9];
                    GenChar.Locomotion locomotion = GenChar.parseLocomotion(p[10]);
                    GenChar.Diet diet = GenChar.parseDiet(p[11]);
                    GenChar.BodyCover bodyCover = GenChar.parseBodyCover(p[12]);
                    GenChar.Reproduction reproduction = GenChar.parseReproduction(p[13]);

                    Habitat habitat = new Habitat.Builder()
                            .habitatID(habitatID)
                            .habitatName(habitatName)
                            .location(habitatLocation)
                            .description(habitatDescription)
                            .build();
                    GenChar genChar = new GenChar.Builder()
                            .locomotion(locomotion)
                            .diet(diet)
                            .bodyCover(bodyCover)
                            .reproduction(reproduction)
                            .build();
                    Species species = new Species.Builder()
                            .speciesID(sID)
                            .scientificName(scientificName)
                            .commonName(commonName)
                            .category(category)
                            .conservationStatus(conservationStatus)
                            .recentSightings(recentSightings)
                            .habitat(habitat)
                            .genChar(genChar)
                            .build();
                    
                    database.add(species);
                }
            } catch(IOException e){
                System.out.println("Error loading data: "+e.getMessage());
            }
        }
}