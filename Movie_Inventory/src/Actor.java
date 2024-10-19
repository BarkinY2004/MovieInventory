public class Actor {
    private String name;
    private String gender;
    private String nationality;
    // Constructor with parameters
    public Actor(String name, String gender, String nationality) {
        this.name = name;
        this.gender = gender;
        this.nationality = nationality;
    }
    // Constructor without parameters
    public Actor() {
        this.name = "";
        this.gender = "";
        this.nationality = "";
    }
    // Copy constructor
    public Actor(Actor other) {
        this.name = other.name;
        this.gender = other.gender;
        this.nationality = other.nationality;
    }
    //here are the getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    // this is the toString method
    @Override
    public String toString() {
        return "(" + name + ", " + gender + ", " + nationality + ")";
    }
}
