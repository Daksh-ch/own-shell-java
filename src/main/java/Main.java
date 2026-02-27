import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
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


            


            
            if(fw.equals("exit")) System.exit(0);
            else if(fw.equals("type")){
                if(rem.equals("type") || (rem.equals("echo")) || (rem.equals("exit"))){
                System.out.println(rem + " is a shell builtin");
                }
                else{
                    Boolean found = false;
                    for(String dir : dirs){
                        String path = dir + File.separator + rem;
                        Path fullPath = Path.of(path);
                        if(Files.exists(fullPath)){
                            if(Files.isExecutable(fullPath)){
                                found = true;
                                System.out.println(rem + " is " + fullPath);
                                break;
                            }
                        }
                    }
                    if(found == false) System.out.println(rem + ": not found");  
                }
            }
            else if(fw.equals("echo")){
                System.out.println(rem);
            }
            else if(!fw.equals("")){
                String[] input = command.split(" ");
                ProcessBuilder pb = new ProcessBuilder(input);

                pb.redirectErrorStream(true);
                Process process = pb.start();

                //read the output of the process and also show any error messages
                Scanner processScanner = new Scanner(process.getInputStream());
                while(processScanner.hasNextLine()){
                    System.out.println(processScanner.nextLine());  
            
                }
            
            }
            else System.out.println(fw + ": command not found");
        
        }
    }

}