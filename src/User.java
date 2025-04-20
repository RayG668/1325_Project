public class User implements Comparable<User> {
    private String fullName;
    private String requestedSkill;
    private boolean wasPinged;

    public User(String fullName, String requestedSkill) {
        this.fullName = fullName;
        this.requestedSkill = requestedSkill;
        this.wasPinged = false;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRequestedSkill() {
        return requestedSkill;
    }

    public void setRequestedSkill(String requestedSkill) {
        this.requestedSkill = requestedSkill;
    }

    public boolean wasPinged() {
        return wasPinged;
    }

    public void setPinged(boolean status) {
        this.wasPinged = status;
    }

    public String getLastName() {
        String[] parts = fullName.trim().split(" ");
        return parts.length > 1 ? parts[parts.length - 1] : fullName;
    }

    public void displayInfo() {
        System.out.println(fullName + " needs help with: " + requestedSkill);
    }

    public int compareTo(User other) {
        return this.getLastName().compareToIgnoreCase(other.getLastName());
    }
}
