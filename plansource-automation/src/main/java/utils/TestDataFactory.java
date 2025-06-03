package utils;

import pojo.EmployeeData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TestDataFactory {

    public static EmployeeData getDefaultEmployee() {
        EmployeeData emp = new EmployeeData();

        emp.setFirstName("John");
        emp.setLastName("Doe");
        emp.setAddress1("123 Test Lane");
        emp.setAddress2("Apt 4B");
        emp.setCity("San Francisco");
        emp.setStateAbbr("AL");
        emp.setZip("94107");
        emp.setCountry("USA");

        // Dynamic birthdate (fixed)
        emp.setBirthDate("01/15/1990");

        emp.setGender("Male");
        emp.setMaritalStatus("Single");
        emp.setEmploymentLevel("F");
        emp.setLocation("SCA");
        emp.setCurrentSalary("80000");
        emp.setBenefitSalary("85000");
        emp.setPassword("Test@123");

        return emp;
    }

    // Optional helper: Generate a random SSN (used directly in EmployeePage)
    public static String generateRandomSSN() {
        Random rand = new Random();
        return "324-" + (10 + rand.nextInt(90)) + "-" + (1000 + rand.nextInt(9000));
    }

    // Optional helper: Generate today's date minus N days for hire/eligibility
    public static String getFormattedPastDate(int daysAgo) {
        LocalDate date = LocalDate.now().minusDays(daysAgo);
        return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }
}
