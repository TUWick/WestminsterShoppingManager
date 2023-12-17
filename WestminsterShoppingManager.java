import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

public class WestminsterShoppingManager implements ShoppingManager {

    // public static void main(String[] args) {
    //     javacw = new WestminsterShoppingManager();
    //     javacw.drive();
    // }

    // Scanner inputChoiceSc = new Scanner(System.in);
    // private static final int COUNT_DOCTOR = 10;


    // public ArrayList<Doctor> docList = new ArrayList<>();
    // public ArrayList<Patient> patientsList = new ArrayList<>();
    // public ArrayList<Consultation> consList = new ArrayList<>();


    // private static WestminsterSkinConsultationManager javacw;



    // public static WestminsterSkinConsultationManager javacw() {
    //    return javacw;
    // }

    // public void drive(){
    //     loadDataFromFile();

    //     while (true){
    //         displayConsoleMenu();

    //         String userchoice = inputChoiceSc.next();

    //         switch (userchoice) {
    //             case "0":
    //                 System.out.println("Thank you for using the WESTMINSTER SKIN CONSULTATION CENTRE System!!!");
    //                 break;

    //             case "1":
    //                 addDoctor();
    //                 break;

    //             case "2":
    //                 deleteDoctor();
    //                 break;

    //             case "3":
    //                 viewDoctors();
    //                 break;

    //             case "4":
    //                 saveDataToFile();
    //                 break;

    //             case "5":
    //                 startGUI();
    //                 break;


    //             default:
    //                 System.out.println("!!WRONG INPUT!! Please enter a valid input");
    //                 break;
    //         }

    //     }
    // }

    // public static void displayConsoleMenu(){
    //     System.out.println("\n----- Welcome to the Westminster Skin Consultation CONSOLE MENU -----");
    //     System.out.println("1 - Add a new doctor to the system ");
    //     System.out.println("2 - Delete a doctor from the system ");
    //     System.out.println("3 - Print the list of all the doctors ");
    //     System.out.println("4 - Save data in a file ");
    //     System.out.println("5 - !!! Open the Consultation Manger GUI !!!");

    //     System.out.println("Enter you option (press 0 to exit):- ");
    // }


    // public boolean isText(String name) {
    //     return name.matches("[a-zA-Z]+");
    // }

    // public boolean isNumber(String number){
    //     try{
    //         Integer.parseInt(number);
    //         return true;
    //     }
    //     catch(NumberFormatException ex){
    //         return false;
    //     }
    // }


    @Override
    public void add() {
        try {

            System.out.println("\n _______Adding a new doctor to the system________ ");

            int i = 0;
            String name = "";
            String surname = "";
            String specialisation = "";
            String medicalLicenseNumber = "";
            LocalDate dateOfBirth;

            while (true) {

                System.out.print("Name of the Doctor:- ");
                name = inputChoiceSc.next();

                if (isText(name)) {
                    break;
                } else {
                    System.out.println("Please enter your name!!!");
                }
            }

            while (true){

                System.out.print("Surname of the Doctor:- ");
                surname = inputChoiceSc.next();

                if (isText(surname)){
                    break;
                }
                else{
                    System.out.println("Please enter your surname!!!");
                }
            }

            while(true){
                try{

                    System.out.print("Date of birth of the Doctor (Year-Month-Day):- ");
                    dateOfBirth = LocalDate.parse(inputChoiceSc.next());

                    break;

                }catch (DateTimeParseException exp){
                    System.out.println("Please Enter a valid data ");
                }
            }




            System.out.print("Mobile number of the Doctor:- ");
            String mobileNumber = inputChoiceSc.next();


            while(true){
                System.out.print("Medical license number of the Doctor:- ");
                medicalLicenseNumber = inputChoiceSc.next();
                if(isNumber(medicalLicenseNumber)){

                    break;
                }
                else {
                    System.out.println("Please Enter Valid Data for Medical License Number");
                }
            }



            while(true){
                System.out.print("Specialisation of the Doctor:- ");
                specialisation = inputChoiceSc.next();
                if(isText(specialisation)){

                    break;
                }
                else{
                    System.out.println("Please Enter Valid Data Type Specialisation");
                }
            }






            Doctor newDoctor = new Doctor(name, surname, dateOfBirth, mobileNumber, Integer.parseInt(medicalLicenseNumber), specialisation);


            if (docList.size() <= COUNT_DOCTOR) {
                docList.add(newDoctor);
                System.out.println("---Doctor has been added successfully into the system---");
            } else {
                System.out.println("!!System Limit Exceded!! system only can allocated 10 doctors ");
            }
        }
        catch (NumberFormatException cw){
            System.out.println("Invalid Input");
        }
        catch (Exception j){
            System.out.println("Error inputting data please try again!!!!");
        }
    }


    @Override
    public void delete() {
        boolean foundFlag = false;
        System.out.println("\n--- Delete doctor ---");
        System.out.print("Medical license number of doctor to be deleted: ");

        try {
            int medicalLicenseNumber = inputChoiceSc.nextInt();

            for (Doctor d : docList) {
                if (d.getMedicalLicenseNumber() == medicalLicenseNumber) {
                    foundFlag = true;
                    Doctor currentDoctor = d;
                    docList.remove(currentDoctor);
                    System.out.println(
                            "Doctor with medical license number " + medicalLicenseNumber + " deleted successfully");
                    System.out.println(currentDoctor);
                    System.out.println("No. of doctors in centre: " + docList.size());
                    break;
                }
            }

            if (!foundFlag) {
                System.out.println("Doctor with medical license number " + medicalLicenseNumber + " not found");
            }
        }catch (Exception e){
            inputChoiceSc.next();
            System.out.println("!!!Invalid Input!!!");
        }
    }

    @Override
    public void print() {

        System.out.println("\n--- List of doctors in consultation centre ---");

        if (docList.isEmpty()) {
            System.out.println("No doctors");
        } else {
            System.out.println();
            docList.sort((d1, d2) -> d1.getSurname().compareTo(d2.getSurname()));
            for (Doctor doctor : docList) {
                System.out.println(doctor);
            }
        }
    }

    @Override
    public void saveDataToFile() {
        File doctorsFile = new File("doctors");

        try (FileOutputStream fileOutput = new FileOutputStream(doctorsFile)) {
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            for (Doctor doctor : docList) {
                objectOutput.writeObject(doctor);
            }

            objectOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("All data saved to file: doctors");
        System.out.println();

    }

    @Override
    public void loadDataFromFile() {

        System.out.println("Trying to load data from file: doctors");

        File doctorData = new File("doctors");
        try {
            FileInputStream fileInput = new FileInputStream(doctorData);

            try (ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
                while (true) {
                    Doctor doctor = (Doctor) objectInput.readObject();
                    if (doctor != null) {
                        docList.add(doctor);
                    } else {
                        break;
                    }
                }

            } catch (EOFException e) {
                System.out.println("Doctor data loaded from file");
            }

            catch (Exception e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            System.out.println("No data file found");
        }
    }

    // public JFrame startGUI() {
    //     SkinConsultationManagerGUI gui = new SkinConsultationManagerGUI();
    //     return gui.start();
    // }


}