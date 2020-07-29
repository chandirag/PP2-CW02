package sample;

import com.mongodb.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.mongodb.client.model.Projections.include;

public class MyGymManager extends Application implements GymManager  {
    private static boolean addingMember;
    private static boolean deletingMember;


    public static void startUpMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        MyGymManager myGymManager = new MyGymManager();
        System.out.println("------------------------------------");
        System.out.println("Please select an option below to continue:");
        System.out.println(
                "1 : Add new Member\n" +
                "2 : Delete existing Member\n" +
                "3 : Print list of existing members\n" +
                "4 : Sort list of existing members by name in ascending order\n" +
                "5 : Write/Save in a file list of members with relevant details\n" +
                "6 : Open GUI\n" +
                "7 : Exit program\n"
        );
        System.out.print("Enter number of selected option: ");
        int userSelectedOption = scanner.nextInt();
        switch (userSelectedOption) {
            case 1:
                int count = Database.getCount();
                if (count < 100) {
                    addingMember = true;
                    while (addingMember) {
                        try {
                            myGymManager.addNewMember();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Age should be above 60 for members of type: Over60");
                        }
                    }
                } else {
                    System.out.println("Maximum number of 100 members reached. Delete existing members to add" +
                            " new ones to the database.");
                }
                break;
            case 2:
                deletingMember = true;
                while (deletingMember) {
                    Scanner deleteIDScanner = new Scanner(System.in);
                    try {
                        System.out.print("Enter the Membership ID of record that needs to be deleted: ");
                        int idOfMemberToBeDeleted = deleteIDScanner.nextInt();
                        myGymManager.deleteExistingMember(idOfMemberToBeDeleted);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Enter a valid Membership ID.\n");
                    }
                }
                break;
            case 3:
                myGymManager.printExistingMembers();
                break;
            case 4:
                myGymManager.sort();
                break;
            case 5:
                myGymManager.saveToFile();
                break;
            case 6:
                myGymManager.openGUI();
                System.out.println("Exiting program...");
                System.exit(0);
                break;
            case 7:
                System.out.println("Exiting program...");
                System.exit(0);
            default:
                System.out.println("Invalid input");
        }
    }

    @Override
    public void addNewMember() {
        int count = Database.getCount();
        if (count >= 100) {
            System.out.println("Member limit reached.");
            addingMember = false;
        } else {
            Scanner scanner = new Scanner(System.in);
            Scanner idScanner = new Scanner(System.in);
            Scanner nameScanner = new Scanner(System.in);
            Scanner schoolScanner = new Scanner(System.in);
            Scanner ageScanner = new Scanner(System.in);
            Scanner dateScanner = new Scanner(System.in);
            Scanner heightScanner = new Scanner(System.in);
            Scanner weightScanner = new Scanner(System.in);
            System.out.println("-------------------------------------");
            System.out.println(
                    "1 : Default Member\n" +
                            "2 : Student Member\n" +
                            "3 : Senior Member\n"  +
                            "4 : Exit Menu\n"
            );
            System.out.print("What type of user do you want to create? ");
            int userType = scanner.nextInt();

            if (userType == 1) {
                System.out.print("Enter membership number: ");
                int membershipID = idScanner.nextInt();
                DBObject object1 = Database.findDocumentById(membershipID);
                if (object1 == null) {
                    System.out.print("Enter name: ");
                    String userName = nameScanner.nextLine();
                    System.out.print("Enter date joined: ");
                    String membershipDate = dateScanner.nextLine();
                    System.out.print("Enter height: ");
                    double height = heightScanner.nextDouble();
                    System.out.print("Enter weight: ");
                    double weight = weightScanner.nextDouble();
                    Database.createDefaultMember(membershipID, userName, membershipDate, height, weight);
                    System.out.println("Member added to database successfully.");
                } else {
                    System.out.println("A member with the membership no " + membershipID + " already exists.");
                }
            } else if (userType == 2) {
                System.out.print("Enter membership number: ");
                int membershipID = idScanner.nextInt();
                DBObject object1 = Database.findDocumentById(membershipID);
                if (object1 == null) {
                    System.out.print("Enter name: ");
                    String userName = nameScanner.nextLine();
                    System.out.print("Enter date joined: ");
                    String membershipDate = dateScanner.nextLine();
                    System.out.print("Enter height: ");
                    double height = heightScanner.nextDouble();
                    System.out.print("Enter weight: ");
                    double weight = weightScanner.nextDouble();
                    System.out.print("Enter school: ");
                    String school = schoolScanner.nextLine();
                    Database.createStudentMember(membershipID, userName,membershipDate, height, weight, school);
                    System.out.println("Member added to database successfully.");
                } else {
                    System.out.println("A member with the membership no " + membershipID + " already exists.");
                }
            } else if (userType == 3) {
                int age;
                System.out.print("Enter membership number: ");
                int membershipID = idScanner.nextInt();
                DBObject object1 = Database.findDocumentById(membershipID);
                if (object1 == null) {
                    System.out.print("Enter name: ");
                    String userName = nameScanner.nextLine();
                    System.out.print("Enter date joined: ");
                    String membershipDate = dateScanner.nextLine();
                    System.out.print("Enter height: ");
                    double height = heightScanner.nextDouble();
                    System.out.print("Enter weight: ");
                    double weight = weightScanner.nextDouble();
                    while (true) {
                        try {
                            System.out.print("Enter age: ");
                            age = ageScanner.nextInt();
                            Database.createOver60Member(membershipID, userName, membershipDate, height, weight, age);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Age should be above 60.\n");
                        }
                    }
                    System.out.println("Member added to database successfully.");
                } else {
                    System.out.println("A member with the membership no " + membershipID + " already exists.");
                }
            } else if (userType == 4) {
                addingMember = false;
            } else {
                System.out.println("Invalid input.");
            }
        }

    }

    @Override
    public void deleteExistingMember(int id) {
        DB db = Database.connectToDatabase();
        DBCollection collection = db.getCollection("users");
        DBCursor cursor = collection.find(new BasicDBObject("_id", id));
        DBObject document = Database.findDocumentById(id);
        if (document == null) {
            System.out.println("A member with membership number " + id + " does not exist in the database.");
        } else {
            String deletedMemberType = (String) cursor.one().get("member-type");
            String deletedMemberID = (String) cursor.one().get("_id").toString();
            collection.remove(document);
            int count = Database.getCount();
            System.out.println("Member " + deletedMemberID + " was deleted.\n" +
                    "Deleted MemberType: " + deletedMemberType + "\n" +
                    "Free spaces left in the system: " + (5 - count));
            deletingMember = false;
        }
    }

    @Override
    public void printExistingMembers() {
        // Print all existing members sorted by Membership No.
        Database.sortAndPrintExistingMembers("_id", 1);
    }

    @Override
    public void sort() {
        // Print all existing members sorted by Name
        Database.sortAndPrintExistingMembers("name", 1);
    }

    @Override
    public void saveToFile() throws IOException {
        Database.saveToFile("MemberData.txt", "_id", -1);
        System.out.println("Data saved to file.");
    }

    @Override
    public void openGUI() {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       GUI.display();
    }

}
