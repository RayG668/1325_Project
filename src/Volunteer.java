import java.util.ArrayList;

public class Volunteer implements Comparable<Volunteer> {
    private String fullName;
    private String skillOffered;
    private String qualifications;
    private String email;
    private String phone;
    private ArrayList<Integer> ratings;

    public Volunteer(String fullName, String skillOffered, String qualifications, String email, String phone) {
        this.fullName = fullName;
        this.skillOffered = skillOffered;
        this.qualifications = qualifications;
        this.email = email;
        this.phone = phone;
        this.ratings = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public String getSkillOffered() {
        return skillOffered;
    }

    public String getQualifications() {
        return qualifications;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void addRating(int stars) {
        if (stars >= 0 && stars <= 5) {
            ratings.add(stars);
        }
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) return 0.0;
        int total = 0;
        for (int r : ratings) {
            total += r;
        }
        return (double) total / ratings.size();
    }

    public void displayInfo() {
        System.out.println("👤 Name: " + fullName);
        System.out.println("🔧 Skill: " + skillOffered);
        System.out.println("📋 Qualifications: " + qualifications);
        if (!email.isEmpty()) System.out.println("📧 Email: " + email);
        if (!phone.isEmpty()) System.out.println("📱 Phone: " + phone);
        System.out.printf("⭐ Avg. Rating: %.2f (%d ratings)\n", getAverageRating(), ratings.size());
        System.out.println("--------------------------------------");
    }

    public String getLastName() {
        String[] parts = fullName.trim().split(" ");
        return parts.length > 1 ? parts[parts.length - 1] : fullName;
    }

    @Override
    public int compareTo(Volunteer other) {
        return this.getLastName().compareToIgnoreCase(other.getLastName());
    }
}

