import java.util.ArrayList;

public class Volunteer implements Comparable<Volunteer> {
    private String fullName;
    private String skillOffered;
    private String qualifications;
    private ArrayList<Integer> ratings;

    public Volunteer(String fullName, String skillOffered, String qualifications) {
        this.fullName = fullName;
        this.skillOffered = skillOffered;
        this.qualifications = qualifications;
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
        System.out.println("Name: " + fullName);
        System.out.println("Skill: " + skillOffered);
        System.out.println("Qualifications: " + qualifications);
        System.out.printf("Avg. Rating: %.2f (%d ratings)\n", getAverageRating(), ratings.size());
    }

    // Sort alphabetically by last name
    @Override
    public int compareTo(Volunteer other) {
        String thisLastName = this.getLastName();
        String otherLastName = other.getLastName();
        return thisLastName.compareToIgnoreCase(otherLastName);
    }

    public String getLastName() {
        String[] parts = fullName.trim().split(" ");
        return parts.length > 1 ? parts[parts.length - 1] : fullName;
    }
}

