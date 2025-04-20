import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class FixItHub {
    private ArrayList<User> users;
    private ArrayList<Volunteer> volunteers;
    private HashMap<String, String> missedRequests; // name → skill needed

    public FixItHub() {
        users = new ArrayList<>();
        volunteers = new ArrayList<>();
        missedRequests = new HashMap<>();
    }

    public void addVolunteer(Volunteer v) {
        volunteers.add(v);
        Collections.sort(volunteers); // Keep list sorted by last name

        // Check if any missed users wanted this skill
        for (User user : users) {
            if (!user.wasPinged() &&
                    user.getRequestedSkill().equalsIgnoreCase(v.getSkillOffered())) {

                System.out.println("🔔 Ping: " + user.getFullName() + ", a volunteer (" + v.getFullName() + ") is now available for " + v.getSkillOffered() + "!");
                user.setPinged(true);
            }
        }
    }

    public void addUser(User u) {
        users.add(u);
        boolean foundMatch = false;

        for (Volunteer v : volunteers) {
            if (v.getSkillOffered().equalsIgnoreCase(u.getRequestedSkill())) {
                foundMatch = true;
                break;
            }
        }

        if (!foundMatch) {
            missedRequests.put(u.getFullName(), u.getRequestedSkill());
        }
    }

    public void showAllVolunteers() {
        if (volunteers.isEmpty()) {
            System.out.println("No volunteers available.");
            return;
        }

        System.out.println("\n--- Volunteers List (Sorted by Last Name) ---");
        for (Volunteer v : volunteers) {
            v.displayInfo();
            System.out.println();
        }
    }

    public void searchVolunteers(String skill) {
        boolean found = false;
        for (Volunteer v : volunteers) {
            if (v.getSkillOffered().equalsIgnoreCase(skill)) {
                v.displayInfo();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No volunteers found with that skill.");
        }
    }

    public void rateVolunteer(String name, int rating) {
        for (Volunteer v : volunteers) {
            if (v.getFullName().equalsIgnoreCase(name)) {
                v.addRating(rating);
                System.out.println("Rated " + name + " with " + rating + " stars.");
                return;
            }
        }
        System.out.println("Volunteer not found.");
    }

    public static void main(String[] args) {
        FixItHub hub = new FixItHub();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n==== Fix-It Hub Menu ====");
            System.out.println("1. Register as a Volunteer");
            System.out.println("2. Request Repair Help");
            System.out.println("3. Search Volunteers by Skill");
            System.out.println("4. View All Volunteers");
            System.out.println("5. Rate a Volunteer");
            System.out.println("6. View all Users");
            System.out.println("7. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Full Name: ");
                    String name = sc.nextLine();
                    System.out.print("Skill Offered: ");
                    String skill = sc.nextLine();
                    System.out.print("Qualifications: ");
                    String qual = sc.nextLine();
                    hub.addVolunteer(new Volunteer(name, skill, qual));
                    System.out.println("Thank you for volunteering!");
                }
                case 2 -> {
                    System.out.print("Your Full Name: ");
                    String name = sc.nextLine();
                    System.out.print("Skill you need help with: ");
                    String skill = sc.nextLine();
                    hub.addUser(new User(name, skill));
                    System.out.println("Your request was recorded.");
                }
                case 3 -> {
                    System.out.print("Enter skill to search: ");
                    String skill = sc.nextLine();
                    hub.searchVolunteers(skill);
                }
                case 4 -> hub.showAllVolunteers();
                case 5 -> {
                    System.out.print("Volunteer Full Name: ");
                    String name = sc.nextLine();
                    System.out.print("Rating (0 to 5): ");
                    int stars = sc.nextInt();
                    sc.nextLine();
                    hub.rateVolunteer(name, stars);
                }
                case 6 -> hub.showAllUsers();
                case 7 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }

    public void showAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users have made requests.");
            return;
        }

        Collections.sort(users); // Uses compareTo() from User.java

        System.out.println("\n--- Users List (Sorted by Last Name) ---");
        for (User u : users) {
            u.displayInfo();
            System.out.println();
        }
    }

}
