public class User {
    private String name;
    private String requestedSkill;

    // Constructor
    public User(String name, String requestedSkill) {
        this.name = name;
        this.requestedSkill = requestedSkill;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for requested skill
    public String getRequestedSkill() {
        return requestedSkill;
    }

    // Setter for requested skill
    public void setRequestedSkill(String requestedSkill) {
        this.requestedSkill = requestedSkill;
    }

    // Display user info
    public void displayInfo() {
        System.out.println(name + " needs help with: " + requestedSkill);
    }
}
