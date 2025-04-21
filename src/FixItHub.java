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

        System.out.println("\uD83D\uDD27 Welcome to Fix-It Hub!");

        while (running) {
            System.out.println("======================================");
            System.out.println("🛠️  Fix-It Hub Main Menu");
            System.out.println("======================================");
            System.out.println("1️⃣  Register as a Volunteer");
            System.out.println("2️⃣  Request Repair Help");
            System.out.println("3️⃣  Volunteer Menu");
            System.out.println("4️⃣  View all Users");
            System.out.println("5️⃣  Search and Edit Your Request");
            System.out.println("6️⃣  Exit Program");
            System.out.print("Choose an option (1–6): ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear newline

            switch (choice) {
                case 1 -> {
                    System.out.println("\uD83D\uDCDD Volunteer Registration");
                    System.out.print("Full Name: ");
                    String name = sc.nextLine();
                    System.out.print("Skill Offered: ");
                    String skill = sc.nextLine();
                    System.out.print("Qualifications: ");
                    String qual = sc.nextLine();
                    hub.addVolunteer(new Volunteer(name, skill, qual));
                    System.out.println("✅ Thank you, " + name + "! You're now a Fix-It volunteer.");
                }
                case 2 -> {
                    System.out.println("\uD83C\uDD98 Repair Help Request");
                    System.out.print("Your Full Name: ");
                    String name = sc.nextLine();
                    System.out.print("What skill you need help with?: ");
                    String skill = sc.nextLine();
                    hub.addUser(new User(name, skill));
                    System.out.println("✅ Request received! We’ll notify you if someone becomes available.");
                }
                case 3 -> hub.showVolunteerMenu(sc);
                case 4 -> hub.showAllUsers();
                case 5 -> hub.editUserRequest(sc);
                case 6 -> {
                    running = false;
                    System.out.println("\uD83D\uDC4B Thanks for using Fix-It Hub!");
                }
                default -> System.out.println("⚠\uFE0F Invalid option. Please choose 1-8");
            }
            System.out.println();
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

    public User serachUserByName(String name) {
        for (User u : users) {
            if (u.getFullName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        return null;
    }

    public void editUserRequest(Scanner sc) {
        System.out.print("Enter your full name:" );
        String name = sc.nextLine();

        User user = serachUserByName(name);
        if (user != null) {
            System.out.println("Current skill needed: " + user.getRequestedSkill());
            System.out.print("Enter new skill you need help with: ");
            String newSkill = sc.nextLine();
            user.setRequestedSkill(newSkill);
            user.setPinged(false);
            System.out.println("Your request has been updated");
        } else {
            System.out.println("User not found.");
        }
    }

    public void showVolunteerMenu(Scanner sc) {
        boolean inVolunteerMenu = true;

        while (inVolunteerMenu) {
            System.out.println("\n===== \uD83E\uDDF0 Volunteer Menu =====");
            System.out.println("1. \uD83D\uDD0D Search Volunteers by Skill");
            System.out.println("2. \uD83D\uDCCB View All Volunteers");
            System.out.println("3. ⭐ Rate a Volunteer");
            System.out.println("4. \uD83D\uDD19 Return to Main Menu");
            System.out.println("Choose an option (1-4): ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter skill to search: ");
                    String skill = sc.nextLine();
                    searchVolunteers(skill);
                }
                case 2 -> showAllVolunteers();
                case 3 -> {
                    if (volunteers.isEmpty()){
                        System.out.println("⚠\uFE0F No volunteers available to rate.");
                        return;
                    }

                    System.out.print("Volunteer Full Name: ");
                    String name = sc.nextLine();
                    Volunteer found = null;

                     for (Volunteer v : volunteers) {
                        if (v.getFullName().equalsIgnoreCase(name)) {
                            found = v;
                            break;
                        }
                    }

                     if (found == null) {
                         System.out.println("⚠\uFE0F Volunteer not found. Please check the name and try again.");

                         System.out.print(" Available vounteers: ");
                         for (Volunteer v : volunteers) {
                             System.out.println("- " + v.getFullName());
                         }

                         return;
                     }

                     System.out.println("Rating (0 to 5): ");
                     int stars = sc.nextInt();
                     sc.nextLine();
                     found.addRating(stars);
                     System.out.println("✅ You rated " + name + " with " + stars + " stars.");
                }
                case 4 -> {

                    inVolunteerMenu = false;
                    System.out.println("Returning to Main Menu.");
                }
                default -> System.out.println("⚠\uFE0F Invalid option. Please choose 1-4");
            }
        }
    }

}