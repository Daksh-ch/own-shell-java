import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        
        while(true){
            System.out.print("$ ");
            String command = sc.nextLine();

            String fw = command.indexOf(" ") == -1 ? command : command.substring(command.indexOf(0,command.indexOf(' ')));
            String rem = command.indexOf(" ") == -1 ? "" : command.substring(' ' + 1);

            if(fw.equals("exit")) break;
            else if(fw.equals("type")){
                if(rem.equals("type") || (rem.equals("echo")) || (rem.equals("exit"))){
                    System.out.println(rem + " is a shell builtin");
                }
                else System.out.println(rem + " not found");  
            }
            else if(fw.equals("echo ")){
                System.out.println(rem);
            }
            else{
                System.out.println(fw + ": command not found");
            }
        }

        sc.close();
    }


}