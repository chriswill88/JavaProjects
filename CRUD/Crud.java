import java.lang.reflect.Array;
import java.rmi.server.Operation;
import java.util.Map;
import java.util.Scanner;

class Crud {
    public static void create() {
        // POST
        System.out.println("POST");
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

    public static void main(String[] args) {
        Scanner opScan = new Scanner(System.in);
        String operation;        

        do {
          System.out.println("What operation did you want to do? "); 
          operation = opScan.nextLine();
          System.out.println("Using Operation: " + operation);
          
          switch (operation) {
            case "create":
                create();
                break;
            case "read":
                read();
                break;
            case "update":
                update();
                break;
            case "delete":
                delete();
                break;

          }

        } while (!operation.equalsIgnoreCase("quit"));
        opScan.close();
    }
}