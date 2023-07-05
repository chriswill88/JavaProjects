package com.mycompany.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {
    public static void save(String key, String value, File file) {

    }

    public static void create(Scanner opScanner) {
        // POST
        System.out.println("What is your Key? ");
        String key = opScanner.nextLine();
        System.out.println("What is your Value? ");
        String value = opScanner.nextLine();


        System.out.println("POST" + key + value);
    }

    public static void read() {
        // GET
        System.out.println("GET");
    }

    public static void update() {
        // PUT
        System.out.println("UPDATE");
    }

    public static void delete() {
        // DELETE
        System.out.println("DELETE");
    } 

    public static File createFile() {
        try {
            File myFile = new File("fileDB.txt");
            System.out.println("Preparing... ");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
            return myFile;
        } catch (IOException e) {
            System.out.println("An error has occured");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner opScan = new Scanner(System.in);
        String operation;

        // Initalize file or database
        createFile();


        // Initialize input and output streams
        // FileReader input = new FileReader(myFile);
        // FileWriter output = new FileWriter(myFile);

        // get JSON object from file
        try {
            String lines = Files.readString(Paths.get("fileDB.txt"));
            System.out.println("Read From file: " + lines);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        do {
            System.out.println("What operation did you want to do? ");
            operation = opScan.nextLine();
            System.out.println("Using Operation: " + operation);
            
            switch (operation) {
                case "create":
                    create(opScan);
                    break;
                case "read":
                    // read(output);
                    break;
                case "update":
                    // update(input, output);
                    break;
                case "delete":
                    // delete(input, output);
                    break;
            }
        } while (!operation.equalsIgnoreCase("quit"));

        // save updated object to file
        // input.close();
        // output.close();
        opScan.close();
    }
}