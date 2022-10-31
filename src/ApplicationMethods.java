import util.Input;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApplicationMethods {
    // =================================================== Variables =======================================================
    static Input input = new Input();
    static final Path p = Paths.get("src","text", "text.txt");
    static String toBeAdded;
    public static ArrayList<String> tempArrayList = new ArrayList<>();
    public static ArrayList<Contact> contactsList = new ArrayList<>();

    // ==================================================== Methods ========================================================
    public void promptUser() {
        Scanner scanner = new Scanner(System.in);
        String userChoice = "whatever";
        while (!userChoice.equals("6")) {
            System.out.println("What would you like to do");
            System.out.println("1 - View contacts.");
            System.out.println("2 - Add a new contact");
            System.out.println("3 - Search a contact by name.");
            System.out.println("4 - Delete an existing contact.");
            System.out.println("5 - Saving");
            System.out.println("6 - Exit.");
            System.out.println("Enter an option (1, 2, 3, 4, 5 or 6)");
            userChoice = scanner.next();

            switch (userChoice) {
                case "1" -> viewContacts();
                case "2" -> addToContactsList();
                case "3" -> searchContact();
                case "4" -> deleteContact();
                case "5" -> {
                    saveLines(tempArrayList);
                    saved();
                }
                case "6" -> goodbye();
                default -> {
                    wrongInput();
                    promptUser();
                }
            } // End Switch
        } // End While
    } // End promptUser();

    // ============ CRUD ===================
    public static void viewContacts (){

        List<String> lines = new ArrayList<>();

        try{
            lines = Files.readAllLines(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String contacts : lines){
            String [] arr = contacts.split(":");
            String str1 = arr[0];
            String strNumber = arr[1];
            System.out.printf("""
                     Name       | Phone number |
                ---------------------------
                
                %-16s| %-15s%n
                    
                ---------------------------
                --------End of List--------
                ---------------------------
                    """,str1,strNumber);        } // End enhanced for-loop
    } // End viewContacts();
    public static void addToContactsList(){
        // this block makes an instance of a Contact
        //  String usrInput = input.getString("Contact Nickname");
        String name = input.getString("Contact name");
        String number = input.getString("Contact Number");
        number = formatPhoneNum(number);
        name = name.trim();
        Contact newContact = new Contact(name,number);


        // this is my array List of Contacts adding the contact that was just crated
        contactsList.add(newContact);

        // looping through arrayList of Contact to access properties previously set and combining them as a single string
    }// End of addToContactsList
    public static void searchContact (){
        List<String> lines = new ArrayList<>();
        String userSearch = input.getString("Enter name to search:...");
        try{
            lines = Files.readAllLines(p);
            for(String contacts : lines){
                if(contacts.contains(userSearch))
                    System.out.println("here you go: "+contacts);
            } // End enhanced for-loop
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // End searchContact();
    public static void deleteContact(){
        String toBeDeleted = input.getString("What contact would you like to delete?");
        List<String> updatedNames = new ArrayList<>();
        for(String name: readLines()){
            if(!name.contains(toBeDeleted)){
                updatedNames.add(name);
            }
        }
        // updatedNames is written using the writeLines function
        writeLines(updatedNames);
    }// End of deleteContact
    public static void writeLines (List<String>lines){
        try {
            Files.write(p, lines);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }// End of writeLines

    private static void saveLines(List<String> lines) {
        for(Contact contact: contactsList){
            // name and number stored in toBeAdded variable
            toBeAdded = contact.getName()+":"+contact.getNumber();
            // toBeAdded added into tempArrayList
            tempArrayList.add(toBeAdded);
        }
        // single string being added to the intermediate ArrayList of String to be able to write to the text file
        try {
            Files.write(p, tempArrayList, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } // End SaveLines

    private static List<String> readLines() {
        List<String> names;
        try {
            names = Files.readAllLines(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return names;
    } // End of readLines

    public static String formatPhoneNum(String aNum) {
        String areaCode = null;
        String firstThree = null;
        String lastFour = null;
        if (aNum.length() == 10) {
            areaCode = "(" + aNum.substring(0, 3) + ") ";
            firstThree = aNum.substring(3, 6) + "-";
            lastFour = aNum.substring(6);
        } else if (aNum.length() == 7) {
            areaCode = " ";
            firstThree = aNum.substring(0,3) + "-";
            lastFour = aNum.substring(3);

        }
        return new StringBuilder().append(areaCode).append(firstThree).append(lastFour).toString();
    }// End of formatPhoneNum


    // ============ user interface messages =================
    public static void goodbye(){
        System.out.println("""
                            ===========================
                            ===       Goodbye       ===
                            ===========================
                            """);
    }
    public static void wrongInput(){
        System.out.println("""
                            ===========================
                            ===Wrong input you Dummy===
                            ===========================
                            """);
    }
    public static void saved(){
        System.out.println("""
                            ===========================
                            ===you saved your inputs===
                            ===========================
                            """);
    }


} // ApplicationMethods Class End