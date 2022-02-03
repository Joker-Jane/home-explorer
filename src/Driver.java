import home_explorer.Explorer;
import home_explorer.Serializer;

import java.util.Scanner;

public class Driver {
    private static final Scanner scanner = new Scanner(System.in);
    private static Explorer explorer = new Explorer();

    private static void input(){
        System.out.print("$ ");
        String command = scanner.nextLine();
        explorer.commandParser(command);
    }

    private static void serialize(){
        String str = Serializer.objectToString(explorer);
        System.out.println(str);
        explorer = (Explorer) Serializer.stringToObject(str);
    }

    public static void main(String[] args) {
        //serialize();

        while(true){
            input();
        }
    }
}
