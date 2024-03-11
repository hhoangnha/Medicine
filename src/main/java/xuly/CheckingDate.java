/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xuly;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Thai Vinh
 */
public class CheckingDate {

    public String getCurrentDate() {
        // Get current date
        LocalDate currentDate = LocalDate.now();

        // Format date as SQL Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
        //ok
    }

    public boolean isValidDate(String inputDate) {
        try {
            // Get current date
            LocalDate currentDate = LocalDate.now();

            // Convert inputDate to LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateToCheck = LocalDate.parse(inputDate, formatter);

            // Check if the inputDate is not after the current date and is not equal to the current date
            return !dateToCheck.isAfter(currentDate) && !dateToCheck.isEqual(currentDate);
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        CheckingDate test = new CheckingDate();

        // Test the method
        String inputDate = "2024031012"; // Replace this with your input date
        if (test.isValidDate(inputDate)) {
            System.out.println("The input date is valid and on or before the current date.");
        } else {
            System.out.println("The input date is either invalid or after the current date.");
        }
    }

}
