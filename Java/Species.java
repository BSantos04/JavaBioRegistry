// Create a class for all Species-related functions and variables
public class Species{
    // Create the enums with fixed values for the GenChar parameters
    public enum Category{mammal, bird, reptile, amphibian, fish, insect, arachnid, crustacean, mollusk, annelid, echinoderm, plant, fungus, algae, protist, bacterium, archaeon, virus, unknown}
    public enum ConservationStatus{extinct, endangered, vulnerable, safe, unknown}

    // Create the variables for the parameters
    private String scientificName;
    private String commonName; 
    private ConservationStatus conservationStatus;
    private Category category;
    private String recentSightings;
    private Habitat habitat;
    private GenChar genChar;

    // Create a class object for the Species
    public Species(Builder builder){
        this.scientificName=builder.scientificName;
        this.commonName=builder.commonName;
        this.conservationStatus=builder.conservationStatus;
        this.category=builder.category;
        this.recentSightings=builder.recentSightings;
        this.habitat=builder.habitat;
        this.genChar=builder.genChar;
    }

    // Create a private constructor to create instances of Species
    // If the values are null, empty values or none of the required if it's the case, it will return a deafult value
    public static class Builder{
        private String scientificName=" ";
        private String commonName=""; 
        private ConservationStatus conservationStatus=ConservationStatus.unknown;
        private Category category=Category.unknown;
        private String recentSightings="No information about any recent sightings";
        private Habitat habitat;
        private GenChar genChar;

        public Builder scientificName(String sName){
            if(sName.isEmpty()) this.scientificName=" ";
            else this.scientificName=sName;
            return this;
        }

        public Builder commonName(String cName){
            if(cName.isEmpty()) this.commonName="";
            else this.commonName=cName;
            return this;
        }

        public Builder conservationStatus(ConservationStatus cs){
            if(cs==null) this.conservationStatus=ConservationStatus.unknown;
            else this.conservationStatus=cs;
            return this;
        }

        public Builder category(Category cat){
            if(cat==null) this.category=Category.unknown;
            else this.category=cat;
            return this;
        }

        public Builder recentSightings(String rs){
            if(rs.isEmpty()) this.recentSightings="No information about any recent sightings";
            else this.recentSightings=rs;
            return this;
        }

        public Builder habitat(Habitat h){
            this.habitat=h;
            return this;
        }

        public Builder genChar(GenChar gc){
            this.genChar=gc;
            return this;
        }

        // Return a new Species object with the values
        public Species build(){
            return new Species(this);
        }
    }

    // Create getters to return the values and setters to set values or change them with others
    public String getCommonName(){
        return commonName;
    }
    public void setCommonName(String name){
        if(name.isEmpty()) this.commonName="";
        else this.commonName=name;
    }

    public String getScientificName(){
        return scientificName;
    }
    public void setScientificName(String name){
        if(name.isEmpty()) this.scientificName=" ";
        else this.scientificName=name;
    }

    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category){
        if(category!=null) this.category=category;
        else this.category=Category.unknown;
    }

    public GenChar getGenChar(){
        return genChar;
    }
    public void setGenChar(GenChar genc){
        this.genChar=genc;
    }

    public ConservationStatus getConservationStatus(){
        return conservationStatus;
    }
    public void setConservationStatus(ConservationStatus cStatus){
        if(cStatus!=null) this.conservationStatus=cStatus;
        else this.conservationStatus=ConservationStatus.unknown;
    }

    public String getRecentSightings(){
        return recentSightings;
    }
    public void setRecentSightings(String rSights){
        if(rSights.isEmpty()) this.recentSightings="No information about any recent sightings";
        else this.recentSightings=rSights;
    }

    public Habitat getHabitat(){
        return habitat;
    }
    public void setHabitat(Habitat h){
        this.habitat=h;
    }

    // Create parsers to convert strings into enum values
    // If the strings are empty or null, the same will be parsed as an 'unknown' value
    public static Category parseCategory(String s){
        if(s==null || s.isEmpty()) return Category.unknown;
        for(Category c: Category.values()){
            if(c.name().equalsIgnoreCase(s.trim())) return c;
        }
        return Category.unknown;
    }
    public static ConservationStatus parseConservationStatus(String s){
        if(s==null || s.isEmpty()) return ConservationStatus.unknown;
        for(ConservationStatus cs: ConservationStatus.values()){
            if(cs.name().equalsIgnoreCase(s.trim())) return cs;
        }
        return ConservationStatus.unknown;
    }

    // Display all the information of a certain species
    public void display(){
        System.out.println();
        System.out.println("Scientific Name: "+getScientificName());
        System.out.println("Common Name: "+getCommonName());
        System.out.println("Category: "+getCategory());
        System.out.println("Locomotion: "+getGenChar().getLocomotion());
        System.out.println("Diet: "+getGenChar().getDiet());
        System.out.println("Body Cover: "+getGenChar().getBodyCover());
        System.out.println("Type of reproduction: "+getGenChar().getReproduction());
        System.out.println("Conservation Status: "+getConservationStatus());
        System.out.println("Habitat Name: "+getHabitat().getHabitatName());
        System.out.println("Habitat location: "+getHabitat().getLocation());
        System.out.println("Habitat description: "+getHabitat().getHabitatDescription());
        System.out.println("Recent Sightings information:\n"+getRecentSightings());
        System.out.println();
    }
}
