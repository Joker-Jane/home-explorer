import home_explorer.Explorer;
import home_explorer.Serializer;
import home_explorer.User;

import java.util.Scanner;

public class Driver {
    private static final Scanner scanner = new Scanner(System.in);
    private static User user = new User();

    private static void input(){
        System.out.print("> ");
        String command = scanner.nextLine();
        user.command(command);
    }

    public static void main(String[] args) {
        while(true){
            input();
        }
    }
}
