package com.sparta.engineering72.Input;

import com.sparta.engineering72.Log.Logger;
import com.sparta.engineering72.Utility.Sleeper;
import com.sparta.engineering72.View.Display;
import com.sparta.engineering72.View.Printer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputCollector {

    public static int getReportChoice() {
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        Display.displayReportChoice();
        do {
            try {
                input = scanner.nextInt();
                if (input < 1 || input > 3) {
                    throw new InputMismatchException("Invalid input");
                }
                return input;
            } catch (InputMismatchException ime) {
                Logger.logError(ime, "Invalid input");
                Display.displayError();
                Sleeper.sleep(100);
            }
        } while (true);
    }

    public static int getUserInput() {
        int numberOfYears = -1;
        int numberOfMonths = -1;
        int totalMonths;
        do {
            if (numberOfYears < 0) {
                numberOfYears = getTime("Years");
                continue;
            }
            if (numberOfMonths < 0 || isaBoolean(numberOfYears, numberOfMonths)) {
                numberOfMonths = getTime("Months");
                continue;
            }
            totalMonths = numberOfMonths+numberOfYears*12;
            return totalMonths;

        } while (true);
    }

    private static boolean isaBoolean(int numberOfYears, int numberOfMonths) { //FIXME name
        return numberOfYears == 0 && numberOfMonths <= 0;
    }

    private static int getTime(String timeScale) {
        Scanner input = new Scanner(System.in);
        int numberOfMonths;
        Display.displayEnterNumberOf(timeScale);
        try {
            numberOfMonths = input.nextInt();
            if (numberOfMonths < 0) {
                throw new InputMismatchException("Invalid input");
            }
            return numberOfMonths;
        } catch (InputMismatchException ime) {
            Logger.logError(ime, "Invalid input");
            Display.displayError();
            Sleeper.sleep(100);
        }
        return -1;
    }
}
