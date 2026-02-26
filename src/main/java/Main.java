import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String command = "";
        do{
            System.out.print("$ ");

            Scanner sc = new Scanner(System.in);
            command = sc.nextLine();
        
            System.out.println(command + ": command not found");
        }
        while(!command.equals("exit"));
        

    }

}