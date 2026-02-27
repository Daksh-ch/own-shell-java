import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);

        String allPath = System.getenv("PATH");
        String[] dirs = allPath.split(File.pathSeparator);

        
        while(true){
            System.out.print("$ ");
            String command = sc.nextLine();

            String fw = command.indexOf(" ") == -1 ? command : command.substring(0,command.indexOf(' '));
            String rem = command.indexOf(" ") == -1 ? "" : command.substring(command.indexOf(' ') + 1);

            switch (fw) {
                case "exit":
                    break;
                case "type":
                    if(rem.equals("type") || (rem.equals("echo")) || (rem.equals("exit"))){
                    System.out.println(rem + " is a shell builtin");
                    }
                    else{
                        for(String dir : dirs){
                            String fullPath = dir + File.separator + rem;
                            if(Files.exists(fullPath)){
                                if(Files.isExecutable(fullPath)){
                                    System.out.println(rem + " is " + fullPath);
                                }
                            }
                            else System.out.println(rem + ": not found");
                        }
                    }
                    // else System.out.println(rem + " not found");
                    break;
                case "echo":
                    System.out.println(rem);
                    break;
                default:
                    System.out.println(fw + ": command not found");
            }
            
        }
        
    }


}