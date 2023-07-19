package com.mycompany.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

public class App {
    public static void save(JSONObject object, File myFile) {
        try {
            PrintWriter writer = new PrintWriter(myFile, "UTF-8");
            writer.println(object.toString());
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println( "Saved :)" );
    }

    public static void create(JSONObject mainObj, Scanner opScanner, File myFile) {
        // POST
        System.out.println("What is your Key? ");
        String key = opScanner.nextLine();

        if (mainObj.has(key)) {
            System.out.println(key + " is in DB, can not create value that already exists");
            return;
        }
        System.out.println("What is your Value? ");
        String value = opScanner.nextLine();

        mainObj.put(key, value);

        // Stringify
        System.out.println("POST: " + mainObj);
        save(mainObj, myFile);
    }

    public static void readAll(JSONObject mainObj) {
        // GET
        System.out.println("GET: " + mainObj);
    }

    public static void get(JSONObject mainObj, Scanner opScanner) {
        // GET
        System.out.println("What is your Key? ");
        String key = opScanner.nextLine();
        if (!mainObj.has(key)) {
            System.out.println(key + " is not in DB, can not get value");
            return;
        }
        System.out.println("GET: " + mainObj.get(key));
    }

    public static void update(JSONObject mainObj, Scanner opScanner, File myFile) {
        // PUT
        System.out.println("What is your Key? ");
        String key = opScanner.nextLine();

        if (!mainObj.has(key)) {
            System.out.println(key + " is not in DB, can not update value");
            return;
        }

        System.out.println("What is your Value? ");
        String value = opScanner.nextLine();

        System.out.println("UPDATE: " + key + ": " + mainObj.get(key) + " -> " + value );
        mainObj.put(key, value);
        System.out.println("UPDATE: " + key + ": " + mainObj.get(key) );

        save(mainObj, myFile);
    }

    public static void delete(JSONObject mainObj, Scanner opScanner, File myFile) {
        // DELETE
        System.out.println("What is your Key? ");
        String key = opScanner.nextLine();

        System.out.println( "DELETE: " + key + ": " + mainObj.get(key) );
        mainObj.remove(key);
        System.out.println( "OBJECT: " + mainObj);
        
        save(mainObj, myFile);
    } 

    public static void clearAll(JSONObject mainObj, File myFile) {
        // DELETE
        Iterator<String> keys = mainObj.keys();
        List<String> keyList = new ArrayList<String>();

        while (keys.hasNext()) {
            keyList.add(keys.next());
        }
        System.out.println("Keylist: " + keyList);

        while (keyList.size() > 0) {
            mainObj.remove(keyList.get(0));
            keyList.remove(0);
        }

        System.out.println( "All Clean! " + mainObj);
  
        save(mainObj, myFile);
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
        JSONObject mainObject = null;

        // Initalize file or database
        File myFile = createFile();

        // get JSON object from file
        try {
            String lines = Files.readString(Paths.get("fileDB.txt"));
            mainObject = new JSONObject(lines);
            System.out.println("loaded json object: " + mainObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.JSONException e) {
            System.out.println("Empty File: " + mainObject);
            mainObject = new JSONObject();
            save(mainObject, myFile);
        }

        do {
            System.out.println("What operation did you want to do? (Hint 'man')");
            operation = opScan.nextLine();
            System.out.println("Using Operation: " + operation);

            switch (operation) {
                case "create":
                    create(mainObject, opScan, myFile);
                    break;
                case "readAll":
                    readAll(mainObject);
                    break;
                case "update":
                    update(mainObject, opScan, myFile);
                    break;
                case "get":
                    get(mainObject, opScan);
                    break;
                case "delete":
                    delete(mainObject, opScan, myFile);
                    break;
                case "clear":
                    clearAll(mainObject, myFile);
                    break;
                case "quit":
                    break;
                case "man":
                    System.out.println(" Operations: ");
                    System.out.println(" create - push a key value to DB ");
                    System.out.println(" readAll - get all keys and values from DB ");
                    System.out.println(" get - get one value from a key ");
                    System.out.println(" delete - delete one value from a key ");
                    System.out.println(" clear - clears the whole DB ");
                    System.out.println(" update - update a key value in the DB ");
                    System.out.println(" quit - quit ");
                    break;
                default:
                    System.out.println("Invalid Operation - Try again.");
            }
            System.out.println("---------------------------------");
        } while (!operation.equalsIgnoreCase("quit"));
        opScan.close();
        System.out.println("Good Night");
    }
}