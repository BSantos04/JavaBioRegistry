import java.io.Serializable;

// Create a class for all Habitat-related functions and variables
public class Habitat implements Serializable{
    // Create an enum to fix the accepted values for the habitat name
    public enum HabitatName{tundra, grassland, desert, forest, mountain, freshwater, marine, coastal, wetland, rainforest, cave, urban, agricultural, savanna, soil, host, unknown}

    // Create the variables for the parameters
    private String habitatID;
    private HabitatName habitatName; 
    private String location;
    private String description;

    // Create a new object for the Habitat
    public Habitat(Builder builder){
        this.habitatID=builder.habitatID;
        this.habitatName=builder.habitatName;
        this.description=builder.description;
        this.location=builder.location;
    }

    // Create a private constructor to create instances of Habitat
    // If the values are null, empty values or none of the required if it's the case, it will return a deafult value
    public static class Builder{
        private String habitatID = " ";
        private HabitatName habitatName = HabitatName.unknown;
        private String location = "unknown";
        private String description = "no description found.";

        public Builder habitatID(String id){
            if(id==null | id.isEmpty()) this.habitatID=id;
            else this.habitatID=id;
            return this;
        }

        public Builder habitatName(HabitatName hn){
            if(hn!=null) this.habitatName=hn;
            else this.habitatName=HabitatName.unknown;
            return this;
        }

        public Builder location(String l){
            if(l==null || l.isEmpty()) this.location="unknown";
            else this.location=l;
            return this;
        }

        public Builder description(String d){
            if(d==null || d.isEmpty()) this.description="no description found.";
            else this.description=d;
            return this;
        }

        // Return a new Habitat object with the values
        public Habitat build(){
            return new Habitat(this);
        }

    }

    // Create getters to return the values and setters to set values or change them with others
    public String getHabitatID(){
        return habitatID;
    }
    public void setHabitatId(String id){
        if(id.isEmpty()) this.habitatID=" ";
        else this.habitatID=id;
    }

    public HabitatName getHabitatName(){
        return habitatName;
    }
    public void setHabitatName(HabitatName name){
        if(name!=null) this.habitatName=name;
        else this.habitatName=HabitatName.unknown;
    }

    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        if(location.isEmpty()) this.location="unknown";
        else this.location=location;
    }

    public String getHabitatDescription(){
        return description;
    }
    public void setHabitatDescription(String description){
        if(description.isEmpty()) this.description="unknown";
        else this.description=description;
    }

    // Create a parser to convert habitat name string into an enum value
    // If the string is empty or null, the same will be parsed as an 'unknown' value
    public static HabitatName parseHabitatName(String s){
        if(s==null || s.isEmpty()) return HabitatName.unknown;
        for(HabitatName hn: HabitatName.values()){
            if(hn.name().equalsIgnoreCase(s.trim())) return hn;
        }
        return HabitatName.unknown;
    }

    // Display the habitat info
    public void display(){
        System.out.println();
        System.out.println("ID: "+getHabitatID());
        System.out.println("Habitat Name: "+getHabitatName());
        System.out.println("Location: "+getLocation());
        System.out.println("Description: "+getHabitatDescription());
        System.out.println();
    }

    // Override equals() and hashCode() methods in order to adapt hashSets to remove duplicated Habitat objects
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Habitat)) return false;

        Habitat other = (Habitat) o;

        return this.habitatName==other.habitatName &&
               this.location.equalsIgnoreCase(other.location) &&
               this.description.equalsIgnoreCase(other.description); 
    }
    @Override
    public int hashCode() {
        int result = habitatName.hashCode();
        result = 31 * result + location.toLowerCase().hashCode();
        result = 31 * result + description.toLowerCase().hashCode();

        return result;
    }
}
