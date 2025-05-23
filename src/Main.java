import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            convertJpgToPng("durian.jpg");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void createFile(){
        Path path = Paths.get("person.excel");
        try{
            Files.createFile(path);
            System.out.println("File Created");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void createDirectory(){
        Path path = Paths.get("my-folder");
        try{
            Files.createDirectory(path);
            System.out.println("Directory Created");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void renameFile(){
        Path path = Paths.get("person.csv");
        Path targetPath = Paths.get("person.excel");
        try{
            Files.move(path, targetPath);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void moveFile(){
        Path path = Paths.get("person.excel");
        Path targetPath = Paths.get("D:\\person.excel");
        try{
            Files.move(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createJavaFile(){
        Path path = Paths.get("C:\\Users\\User\\Desktop\\Main.java");
        String javaCode = """
                public class Main{
                    public static void main(String[] args) {
                        System.out.println("Hello World");
                    }
                }""";
        try{
            Files.createFile(path);
            OutputStream fileOutputStream = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            fileOutputStream.write(javaCode.getBytes(StandardCharsets.UTF_8));
            System.out.println("File Created");
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void convertJpgToPng(String fileName){
        try(FileInputStream fileInputStream = new FileInputStream(fileName)){
            String newFileName = fileName.substring(0, fileName.lastIndexOf('.')) + ".png";
            try(FileOutputStream fileOutputStream = new FileOutputStream(newFileName)){
                int read = fileInputStream.read();
                while (read != -1){
                    fileOutputStream.write(read);
                    read = fileInputStream.read();
                }
                System.out.println("Successfully converted JPG to PNG");
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void writeToFile(){
        try(FileOutputStream fileOutputStream = new FileOutputStream("person.txt",true)){
            System.out.print("[+] Write anything to file: ");
            String data = new Scanner(System.in).nextLine() + "\n";
            fileOutputStream.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void javaWithDirectory(){
        Path directoryPath = Paths.get("C:\\Users\\User\\Desktop\\code");
        Path filePath = Paths.get("C:\\Users\\User\\Desktop\\code\\Main.java");

        String importToCode = """
                public class Main{
                    public static void main(String[] args){
                        System.out.println("Hello World");
                    }
                }""";

        try{
//            Delete the existing directory and file
            if(Files.deleteIfExists(filePath)){
                myLog("Deleted existing file");
            }
            if (Files.deleteIfExists(directoryPath)){
                myLog("Deleted existing directory");
            }

//            Create a new directory and file
            Files.createDirectory(directoryPath);
            myLog("Directory created");
            Files.createFile(filePath);
            myLog("File Created");

            OutputStream fileStream = Files.newOutputStream(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            myLog("Connected to file");
            fileStream.write(importToCode.getBytes(StandardCharsets.UTF_8));
            myLog("Code imported to file");
            fileStream.close();
            myLog("File Closed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void workWithFileClass(){
        File file = new File("person.txt");
        try{
            if(file.createNewFile()){
                myLog("File created");
            }
            System.out.print("[+] Renaming file: [ ");
            for (int i = 0; i <30; i++) {
                Thread.sleep(100);
                System.out.print("\b=>");
            }
            System.out.println("\b]");
            if(file.renameTo(new File("people.txt"))){
                myLog("Renamed successfully");
            }
        }catch (Exception e){
            myLog(e.getMessage());
        }
    }

    public static void myLog(String message){
        System.out.println(Date.from(Instant.now()) + "\tMESSAGE: " + message);
    }

    public static void readFromFile(){
        try (FileInputStream fileInputStream = new FileInputStream("person.txt")){
//            for (byte b : fileInputStream.readAllBytes()){
//                System.out.print((char) b);
//            }
            System.out.println(fileInputStream.transferTo(System.out));
            int x = fileInputStream.read();
            while (x != -1){
                System.out.print((char) x);
                x = fileInputStream.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
