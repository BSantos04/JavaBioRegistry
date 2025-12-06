// Create a class object for the Genetic Characteristics
public class GenChar {
    // Create the enums with fixed values for the GenChar parameters
    public enum Locomotion {biped, quadruped, flying, aquatic, serpentine, cilia, flagella, pseudopods, na, unknown}
    public enum Diet {carnivore, herbivore, omnivore, insectivore, scavenger, fishivore, autotroph, heterotroph, unknown}
    public enum BodyCover {fur, feathers, scales, skin, shell, na, unknown}
    public enum Reproduction {asexual, sexual, hermaphrodite, parthenogenesis, unknown}

    // Create the variables for the parameters
    private Locomotion locomotion;
    private Diet diet;
    private BodyCover bodyCover;
    private Reproduction reproduction;

    // Create a new object for the genetic characteristics
    public GenChar(Builder builder){
        this.locomotion=builder.locomotion;
        this.diet=builder.diet;
        this.bodyCover=builder.bodyCover;
        this.reproduction=builder.reproduction;
    }  

    // Create a private constructor to create instances of GenChar
    // If the values are null or none of the required, it will display as 'unknown' by default 
    public static class Builder {
        private Locomotion locomotion = Locomotion.unknown;
        private Diet diet = Diet.unknown;
        private BodyCover bodyCover = BodyCover.unknown;
        private Reproduction reproduction = Reproduction.unknown;

        public Builder locomotion(Locomotion l){
            if(l!=null) this.locomotion=l;
            else this.locomotion=Locomotion.unknown;
            return this;
        }

        public Builder diet(Diet d){
            if(d!=null) this.diet=d;
            else this.diet=Diet.unknown;
            return this;
        }

        public Builder bodyCover(BodyCover bc){
            if(bc!=null) this.bodyCover=bc;
            else this.bodyCover=BodyCover.unknown;
            return this;
        }

        public Builder reproduction(Reproduction r){
            if(r!=null) this.reproduction=r;
            else this.reproduction=Reproduction.unknown;
            return this;
        }

        // Return a new GenChar object with the values
        public GenChar build(){
            return new GenChar(this);
        }
    }

    // Create getters to return the values and setters to set values or change them with others
    public Locomotion getLocomotion(){
        return locomotion;
    }
    public void setLocomotion(Locomotion locomotion){
        if(locomotion!=null) this.locomotion=locomotion;
        else this.locomotion=Locomotion.unknown;
    }

    public Diet getDiet(){
        return diet;
    }
    public void setDiet(Diet diet){
        if(diet!=null) this.diet=diet;
        else this.diet=Diet.unknown;
    }

    public BodyCover getBodyCover(){
        return bodyCover;
    }
    public void setBodyCover(BodyCover bodyCover){
        if(bodyCover!=null) this.bodyCover=bodyCover;
        else this.bodyCover=BodyCover.unknown;
    }

    public Reproduction getReproduction(){
        return reproduction;
    }
    public void setReproduction(Reproduction reproduction){
        if(reproduction!=null) this.reproduction=reproduction;
        else this.reproduction=Reproduction.unknown;   
    }

    // Create parsers to convert strings into enum values
    // If the strings are empty or null, the same will be parsed as an 'unknown' value
    public static Locomotion parseLocomotion(String s){
        if(s==null || s.isEmpty()) return Locomotion.unknown;
        for(Locomotion l: Locomotion.values()){
            if(l.name().equalsIgnoreCase(s.trim())) return l;
        }
        return Locomotion.unknown;
    }
    public static Diet parseDiet(String s){
        if(s==null || s.isEmpty()) return Diet.unknown;
        for(Diet d: Diet.values()){
            if(d.name().equalsIgnoreCase(s.trim())) return d;
        }
        return Diet.unknown;
    }
    public static BodyCover parseBodyCover(String s){
        if(s==null || s.isEmpty()) return BodyCover.unknown;
        for(BodyCover bc: BodyCover.values()){
            if(bc.name().equalsIgnoreCase(s.trim())) return bc;
        }
        return BodyCover.unknown;
    }
    public static Reproduction parseReproduction(String s){
        if(s==null || s.isEmpty()) return Reproduction.unknown;
        for(Reproduction r: Reproduction.values()){
            if(r.name().equalsIgnoreCase(s.trim())) return r;
        }
        return Reproduction.unknown;
    }

    // Override equals() and hashCode() methods in order to adapt hashSets to remove duplicated Habitat objects
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof GenChar)) return false;

        GenChar other = (GenChar) o;

        return this.locomotion==other.locomotion &&
               this.diet==other.diet &&
               this.bodyCover==other.bodyCover &&
               this.reproduction==other.reproduction;
    }
    @Override
    public int hashCode() {
        int result = locomotion.hashCode();
        result = 31 * result + diet.hashCode();
        result = 31 * result + bodyCover.hashCode();
        result = 31 * result + reproduction.hashCode();

        return result;
    }
}