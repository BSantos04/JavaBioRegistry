import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

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

    // Export the current data of the database into a CSV file
    public void exportCSV(){
        File folder = new File("JavaBioDBcsv");
        folder.mkdirs();
        File csvFile = new File(uniquify(folder+"/HabitatJavaBioDB.csv"));
        try(FileWriter writer = new FileWriter(csvFile)){
            writer.write("ID,HabitatName,Location,Description\n");

            for(Habitat h: habitatList){
                writer.write(
                    escape(h.getHabitatID()) + "," +
                    escape(h.getHabitatName().name()) + "," + 
                    escape(h.getLocation()) + "," + 
                    escape(h.getHabitatDescription()) + "\n");
            }
            System.out.println("CSV exported succesfully!!!");
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

    // Helper function to put a counter on the CSV file name if a file from this database is currently on the working directory
    public static String uniquify(String path){
        File file = new File(path);
        if(!file.exists()) return path;

        String parent = file.getParent();
        String name = file.getName();
        String extension = "";

        int dotIndex = name.lastIndexOf(".");
        if(dotIndex>0){
            extension = name.substring(dotIndex);
            name = name.substring(0, dotIndex);
        }

        int counter = 1;
        String newName;
        File newFile;

        do{
            newName=name + "(" + counter + ")" + extension;
            if(parent!=null) newFile = new File(parent, newName);
            else newFile = new File(newName);
            counter++;
            }
            while(newFile.exists());
                return newFile.getPath();     
    }
    
}
