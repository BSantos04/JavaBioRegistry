import java.util.Scanner;
import java.io.*;
import java.util.Random;

// Create the main class to orchestrate the whole process
public class Main implements Serializable{
    public static void main(String[] args){
        // Create a Scanner instance to input values
        Scanner in = new Scanner(System.in);

        // Create a database instance and load the stored data if exists
        BiologicalDataBase database = new BiologicalDataBase();
        database.loadDat();

        // Create a habitat list instance to store the registered habitats and load the stored data if exists
        HabitatList habitatList = new HabitatList();
        habitatList.loadDat();

        // Define boolean variable for the interface loop
        boolean bool=true;

        // Set the material to build the IDs
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random r = new Random();
        String sID;
        String hID;

        System.out.println();
        System.out.println("Oh, hi there.");

        System.out.println();
        System.out.println("What do you wanna do?");

        // Start a while loop for the interface and display all the possible options
        while(bool){
            System.out.println("-Add a species to the database (1);");
            System.out.println("-Add an habitat to the database (2);");
            System.out.println("-Verify if a species exists in the database (3);");
            System.out.println("-Show the number of species from a specific category (4)");
            System.out.println("-Show the registered species (5);");
            System.out.println("-Show the total genetic diversity of the database (6);");
            System.out.println("-Show the detailed informations of a species (7);");
            System.out.println("-Show all the species associated with the same habitat (8);");
            System.out.println("-Show all registered habitats (9);");
            System.out.println("-Remove species (10) ;");
            System.out.println("-Remove habitat (11) ;");
            System.out.println("-Exit (12).");
            System.out.print("Write the number of the activity you want to do: ");
            String choice = in.nextLine().trim();
            switch(choice){
                // If selected '1', it adds a new species to the database
                case "1":
                    System.out.println();
                    System.out.println("If you want to skip the regist of a certain attribute (from a species or habitat), just press 'Enter'.");
                    System.out.println("Disclaimer: The scientific name field is mandatory!");
                    System.out.println();

                    StringBuilder sb = new StringBuilder();
                    StringBuilder hb = new StringBuilder();
                    do{
                        for(int i=0; i<10; i++){
                            char c = alphabet.charAt(r.nextInt(alphabet.length()));
                            sb.append(c);
                        }
                        sID = sb.toString();
                    } while(database.checkSpeciesID(sID));

                    do{

                        for(int i=0; i<10; i++){
                            char c = alphabet.charAt(r.nextInt(alphabet.length()));
                            hb.append(c);
                        }
                        hID = hb.toString();
                    } while(habitatList.checkHabitatID(hID));

                    // The scientific name field is mandatory, so if no value will be added, a while loop will ask for it again until you input a value
                    boolean tf=true;
                    while(tf){
                        System.out.print("\nWrite the scientific name: ");
                        String sName = in.nextLine().trim();
                        if(sName.equals("")){
                            System.out.println("\nThe species wasn`t registered because you must input a valid name.\nTry again.");
                            tf=true;
                        }
                        else{
                            System.out.print("\nWrite the common name: ");
                            String cName = in.nextLine().trim().toLowerCase();
    
                            System.out.print("\nWrite the species category (mammal, bird, reptile, amphibian, fish, insect, arachnid, crustacean, mollusk, annelid, echinoderm, plant, fungus, algae, protist, bacterium, archaeon, virus, unknown): ");
                            String cat = in.nextLine().trim().toLowerCase();
    
                            System.out.print("\nWrite the species type of locomotion (biped, quadruped, flying, aquatic, serpentine, cilia, flagella, pseudopods, na, unknown): ");
                            String locomotion = in.nextLine().trim().toLowerCase();

                            System.out.print("\nWrite the species diet (carnivore, herbivore, omnivore, insectivore, scavenger, fishivore, autotroph, heterotroph, unknown): ");
                            String diet = in.nextLine().trim().toLowerCase();

                            System.out.print("\nWrite the species body cover (fur, feathers, scales, skin, shell, na, unknown): ");
                            String bodyCover = in.nextLine().trim().toLowerCase();

                            System.out.print("\nWrite the species way of reproduction (asexual, sexual, hermaphrodite, parthenogenesis, unknown): ");
                            String reprodcution = in.nextLine().trim().toLowerCase();
    
                            System.out.print("\nWrite the conservation status of the species (extinct, endangered, vulnerable, safe, unknown): ");
                            String cStatus = in.nextLine().trim().toLowerCase();
    
                            System.out.print("\nDo you have any information about any recent sighting?\nIf you have, write here:");
                            String rSights = in.nextLine().trim().toLowerCase();
    
                            System.out.print("\nWrite the habitat name (tundra, grassland, desert, forest, mountain, freshwater, marine, coastal, wetland, rainforest, cave, urban, agricultural, savanna, soil, host, unknown): ");
                            String hName = in.nextLine().trim().toLowerCase();
    
                            System.out.print("\nWrite the habitat location: ");
                            String location = in.nextLine().trim().toLowerCase();
    
                            System.out.print("\nDo you want to add any description to this habitat?\nIf so, write here: ");
                            String description = in.nextLine().trim().toLowerCase();
    
                            Habitat sHabitat = new Habitat.Builder()
                                    .habitatID(sID)
                                    .habitatName(Habitat.parseHabitatName(hName))
                                    .location(location)
                                    .description(description)
                                    .build();
                            GenChar sGenChar = new GenChar.Builder()
                                    .locomotion(GenChar.parseLocomotion(locomotion))
                                    .diet(GenChar.parseDiet(diet))
                                    .bodyCover(GenChar.parseBodyCover(bodyCover))
                                    .reproduction(GenChar.parseReproduction(reprodcution))
                                    .build();
                            Species species = new Species.Builder()
                                    .speciesID(hID).scientificName(sName)
                                    .commonName(cName)
                                    .category(Species.parseCategory(cat))
                                    .conservationStatus(Species.parseConservationStatus(cStatus))
                                    .recentSightings(rSights)
                                    .habitat(sHabitat)
                                    .genChar(sGenChar)
                                    .build();

                            database.insertSpecies(species);
                            habitatList.addHabitat(sHabitat);
                            tf=false;
                            break;
                        }
                    }
                    bool=true;
                    break;

                // If selected '2', it adds a new habitat to the database
                case "2":
                    StringBuilder hB = new StringBuilder();
                    do{
                        for(int i=0; i<10; i++){
                            char c = alphabet.charAt(r.nextInt(alphabet.length()));
                            hB.append(c);
                        }
                        hID = hB.toString();
                    } while(habitatList.checkHabitatID(hID));

                    System.out.println("\nAny habitat skipped will be considered 'unknown'!");
                    System.out.print("\nWrite the habitat name (tundra, grassland, desert, forest, mountain, freshwater, marine, coastal, wetland, rainforest, cave, urban, agricultural, savanna, soil, host, unknown): ");
                    String habName = in.nextLine().trim();

                    System.out.print("\nWrite the habitat location: ");
                    String local = in.nextLine().trim();

                    System.out.print("\nDo you want to add any description to this habitat?\nIf so, write here: ");
                    String des = in.nextLine().trim();

                    Habitat habit = new Habitat.Builder().habitatID(hID).habitatName(Habitat.parseHabitatName(habName)).location(local).description(des).build();

                    habitatList.addHabitat(habit);
                    bool=true;
                    break;

                // If selected '3', it will check if a certain species exists based on it's scientific name
                case "3":
                    System.out.print("\nWrite the ID of the species you want to verify: ");
                    String sId = in.nextLine().toLowerCase().trim();
                    database.verifyByID(sId);
                    System.out.println();
                    bool=true;
                    break;

                // If selected '4', it will display every species from a certain category
                // If the category is not valid, it will search for every species of the 'unknown' category
                case "4":
                    System.out.print("\nWrite the category of the species (if the category specified is non of the permited, it will assume it as 'unknown'): ");
                    String category = in.nextLine().toLowerCase().trim();
                    database.speciesByCategory(category);
                    System.out.println();
                    bool=true;
                    break;

                // If selected '5', it will display every species and their respective info stored in the database
                case "5":
                    System.out.println();
                    System.out.println("-------------------------------------------------------------");
                    database.showAll();
                    System.out.println();
                    bool=true;
                    break;

                // If selected '6', it will say how many different genetic characteristics are on the database
                case "6":
                    System.out.println();
                    database.totalGeneticDiversity();
                    System.out.println();
                    bool=true;
                    break;

                // If selected '7', it will display the detailed info of a certain species
                case "7":
                    System.out.print("\nWrite the ID of the species you want to find: ");
                    String Sid = in.nextLine().toLowerCase().trim();
                    database.obtainDetailedInfo(Sid);
                    System.out.println();
                    bool=true;
                    break;

                // If selected '8', it will display every species from a certain habitat
                case "8":
                    System.out.print("\nWrite the name of the habitat you want to search: ");
                    String habitato = in.nextLine().toLowerCase().trim();
                    database.associatedByHabitat(habitato);
                    System.out.println();
                    bool=true;
                    break;

                // If selected '9', it will display the detailed info of every registered habitat
                case "9":
                    System.out.println();
                    habitatList.showAllHabitats();
                    System.out.println();
                    bool=true;
                    break;

                // If selected '10', it will remove a species with a certain scientific name
                case "10":
                    System.out.print("\nWrite the ID of the species you want to remove: ");
                    String SID = in.nextLine().trim().toLowerCase();
                    if(database.verifyButInBoolean(SID)==true){
                        database.removeSpecies(SID);
                        System.out.println("Species removed successfully.");
                        System.out.println();
                    }
                    else{
                        System.out.println("There were no species found with that name in the database.");
                        System.out.println();
                    }
                    bool=true;
                    break;

                // If selected '11', it will remove a species with a certain scientific name
                case "11":
                    System.out.print("\nWrite down the ID of the habitat you want to remove:");
                    String id = in.nextLine().trim().toLowerCase();
                    if(habitatList.verifyButInBoolean(id)==true){
                        habitatList.removeHabitat(id);
                        System.out.println("Habitat removed successfully.");
                        System.out.println();
                    }
                    else{
                        System.out.println("There were no habitats found with that name in the database.");
                        System.out.println();
                    }
                    bool=true;
                    break;
                
                // If selected '12', it will export the current Species and Habitats data on the database CSV files and exit the program
                case "12":
                    database.saveDat();
                    habitatList.saveDat();
                    bool=false;
                    break;

                // If any other keyword was selected, the interface will ask you again for a vaid option
                default:
                    System.out.println();
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Try again!");
                    System.out.println("-------------------------------------------------------------");
                    bool=true;
            }
        }   
    }
}