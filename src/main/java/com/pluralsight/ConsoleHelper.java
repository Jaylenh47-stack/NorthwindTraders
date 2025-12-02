package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class ConsoleHelper {

    private static Scanner scanner = new Scanner(System.in);

    public static int promptForInt(String prompt) {
        int input = 0;
        boolean isInvalid = false;

        do {
            try {
                System.out.print(prompt );
                input = scanner.nextInt();
                scanner.nextLine();
                isInvalid = true;
            } catch (Exception ex) {
                scanner.nextLine();
                System.out.println("Invalid entry, please enter a whole number");
            }

        }
        while (!isInvalid);

        return input;
    }

    public static String promptForString(String prompt) {

        System.out.println(prompt + ": ");
        return scanner.nextLine();

    }

    public static float promptForFloat(String prompt){
        float input = 0f;
        boolean isInvalid = false;

        do {
            try {
                System.out.println(prompt + ": ");
                input = scanner.nextFloat();
                scanner.nextLine();
                isInvalid = true;
            } catch (Exception ex) {
                scanner.nextLine();
                System.out.println("Invalid entry, please enter a number");
            }

        }
        while (!isInvalid);

        return input;
    }

    public static double promptForDouble(String prompt) {
        double input = 0;
        boolean isInvalid = false;

        do {
            try {
                System.out.print(prompt + ": ");
                input = scanner.nextDouble();
                scanner.nextLine();
                isInvalid = true;
            } catch (Exception ex) {
                scanner.nextLine();
                System.out.println("Invalid entry, please enter a number");
            }

        }
        while (!isInvalid);

        return input;
    }

    public static LocalDate promptForDate(String prompt){
        LocalDate input = LocalDate.parse("2000-01-01");
        boolean isInvalid = false;

        do {
            try {
                System.out.println(prompt + ": ");
                 input = LocalDate.parse(scanner.nextLine());
                isInvalid = true;
            } catch (Exception ex) {

                System.out.println("Invalid entry, please enter a date (YYYY-MM-DD");
            }
        }while(!isInvalid);

        return input;
    }

    public static LocalTime promptForTime(String prompt){
        LocalTime input = LocalTime.parse("00:00:00");
        boolean isInvalid = false;

        do {
            try {
                System.out.println(prompt + ": ");
                input = LocalTime.parse(scanner.nextLine());

                isInvalid = true;
            } catch (Exception ex) {

                System.out.println("Invalid entry, please enter a date (YYYY-MM-DD");
            }
        }while(!isInvalid);

        return input;
    }

    public static boolean promptForYesNo(String prompt){

        System.out.print(prompt + " (Yes/No):");
        return scanner.nextLine().toLowerCase().contains("y");
    }

    public static void displayList(List<?> values) {
        int totalNumber = values.size();
        int numberPerRow = 5;
        int indexOfNext = 0;

        while (indexOfNext < totalNumber) {

            for (int i = 1; i <= numberPerRow; i++) {
                if (indexOfNext >= totalNumber) {
                    System.out.println();
                    return;
                }
                System.out.printf("%15s", values.get(indexOfNext++).toString());
            }
            System.out.println();
        }
    }

}