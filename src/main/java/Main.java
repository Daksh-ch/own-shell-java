import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    private static String getPath(String parameter){
        String[] pathList = System.getenv("PATH").split(File.pathSeparator);

        for(String path : pathList ){
            Path fullPath = Path.of(path, parameter);
            if(Files.isRegularFile(fullPath) && Files.isExecutable(fullPath)){
                return fullPath.toString();
            }
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);

        
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
                    String path = getPath(rem);
                    if(path != null){
                        System.out.println(rem + " is " + path);
                    }
                    else{
                        System.out.println(rem + ": not found");
                    }
                }
            }
            else if(fw.equals("echo")){
                System.out.println(rem);
            }
            else if(getPath(fw) != null && !fw.equals("pwd")){
                String[] arguments = command.split(" ");
                ProcessBuilder pb = new ProcessBuilder(arguments);
                pb.redirectErrorStream(true);

                Process process = pb.start();

                try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
                process.waitFor();

            }

            else if(fw.equals("pwd")){
                System.out.println(System.getProperty("user.dir"));
            }
            else System.out.println(fw + ": command not found");
            sc.close();
        }
    }
}