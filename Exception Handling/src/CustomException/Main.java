package CustomException;

public class Main {
    public static void main(String[] args) {

        try {
            Student student = new Student(
                    "evgen1", "test@test.com");

        } catch (InvalidPersonNameException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}