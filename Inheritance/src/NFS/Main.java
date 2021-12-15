package NFS;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter and fuel and horsepower of " +
                "the vehicle");

        String[] input = scanner.nextLine().split(" ");
        double fuel = Double.parseDouble(input[0]);
        int horsepower = Integer.parseInt(input[1]);

        SportCar sportCar = new SportCar(fuel, horsepower);

        System.out.println("Would like to drive your Sports Car? ");

        String line = scanner.nextLine();

        if (line.equals("Yes") || line.equals("yes")) {
            System.out.println("How far do you want to go? ");
            int distance = Integer.parseInt(scanner.nextLine());

            sportCar.drive(distance);

        } else if (line.equals("No") || line.equals("no")) {
            System.out.println("Ok User");

        } else {
            Exception exception = new Exception("Invalid answer");
            throw exception;
        }

    }
}
