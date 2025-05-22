public class Main {
    public static void main(String[] args) {

        try(FileOutputStream fileOutputStream = new FileOutputStream("person.txt",true)){
            System.out.print("[+] Write anything to file: ");
            String data = new Scanner(System.in).nextLine() + "\n";
            fileOutputStream.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileInputStream fileInputStream = new FileInputStream("person.txt")){
            for (byte b : fileInputStream.readAllBytes()){
                System.out.print((char) b);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
