import java.util.ArrayList;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;

public class FixItHub {
    private ArrayList<User> users;
    private ArrayList<Volunteer> volunteers;

    public FixItHub() {
        users = new ArrayList<>();
        volunteers = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addVolunteer(Volunteer volunteer) {
        volunteers.add(volunteer);
    }

    public void matchRequests() {
        System.out.println("\nMatching users with volunteers...\n");
        for (User user : users) {
            boolean foundMatch = false;
            for (Volunteer volunteer : volunteers) {
                if (user.getRequestedSkill().equalsIgnoreCase(volunteer.getSkillOffered())) {
                    System.out.println(user.getName() + " matched with " + volunteer.getName()
                            + " for help with " + user.getRequestedSkill());
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                System.out.println(user.getName() + " has no volunteer available for: " + user.getRequestedSkill());
            }
        }
    }

    public static void main(String[] args) {
        FixItHub hub = new FixItHub();
        Scanner scanner = new Scanner(System.in);

        // Get volunteers
        System.out.print("How many volunteers? ");
        int volunteerCount = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        for (int i = 0; i < volunteerCount; i++) {
            System.out.println("Volunteer " + (i + 1) + " info:");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Skill Offered: ");
            String skill = scanner.nextLine();
            hub.addVolunteer(new Volunteer(name, skill));
        }

        // Get user repair requests
        System.out.print("\nHow many users need help? ");
        int userCount = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        for (int i = 0; i < userCount; i++) {
            System.out.println("User " + (i + 1) + " info:");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Skill Needed: ");
            String skill = scanner.nextLine();
            hub.addUser(new User(name, skill));
        }

        // Match users to volunteers
        hub.matchRequests();

        scanner.close(); // always good to close Scanner when done
    }
}

