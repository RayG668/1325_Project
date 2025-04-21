public class User implements Comparable<User> {
    private String fullName;
    private String requestedSkill;
    private boolean wasPinged;
    private String email;
    private String phone;

    public User(String fullName, String requestedSkill, String email, String phone) {
        this.fullName = fullName;
        this.requestedSkill = requestedSkill;
        this.email = email;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLastName() {
        String[] parts = fullName.trim().split(" ");
        return parts.length > 1 ? parts[parts.length - 1] : fullName;
    }

    public void displayInfo() {
        System.out.println("🙋 Name: " + fullName);
        System.out.println("🆘 Needs help with: " + requestedSkill);
        if (!email.isEmpty()) System.out.println("📧 Email: " + email);
        if (!phone.isEmpty()) System.out.println("📱 Phone: " + phone);
        System.out.println("--------------------------------------");
    }

    // Used to identify returning users
    public boolean matches(User other) {
        return this.fullName.equalsIgnoreCase(other.fullName)
                && this.requestedSkill.equalsIgnoreCase(other.requestedSkill);
    }

    @Override
    public int compareTo(User other) {
        return this.getLastName().compareToIgnoreCase(other.getLastName());
    }
}
