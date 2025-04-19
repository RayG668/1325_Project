public class Volunteer {
    private String name;
    private String skillOffered;

    // Constructor
    public Volunteer(String name, String skillOffered) {
        this.name = name;
        this.skillOffered = skillOffered;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for skill
    public String getSkillOffered() {
        return skillOffered;
    }

    // Display volunteer info
    public void displayInfo() {
        System.out.println(name + " can help with: " + skillOffered);
    }
}
