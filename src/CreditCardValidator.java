import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CreditCardValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Card Validation System");

        System.out.print("Enter card number: ");
        long cardNumber = scanner.nextLong();
        String cardType = getCardType(cardNumber);
        // Validate the card number
        if (isValidCard(cardNumber)) {
            System.out.println("Card is valid. and of Type: "+ cardType);
        } else {
            System.out.println("Card is invalid and of Type: "+ cardType);
        }

        scanner.close();

    }



    // Determine the card type based on the starting digits
    public static String getCardType(long number) {
        String numStr = String.valueOf(number);

        if (numStr.startsWith("4")) {
            return "Visa";
        } else if (numStr.startsWith("5")) {
            return "MasterCard";
        } else if (numStr.startsWith("6")) {
            return "Discover Cards";
        } else if (numStr.startsWith("37")) {
            return "American Express";
        } else{
            return "Unknown";
        }
    }

    // Validate the card number using Luhn's algorithm
    public static boolean isValidCard(long number) {
        String numStr = String.valueOf(number);
        int sumEven = 0;
        int sumOdd = 0;
        int length = numStr.length();

        // Process each digit from right to left
         // Luhn's algorithm: double every second digit from the right
         // If doubling results in a number greater than 9, subtract 9 from it
        for (int i = length - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(numStr.charAt(i));

            // If position from the right is even (1-based indexing)
            if ((length - i) % 2 == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = digit - 9; // Same as adding its digits
                }
                sumEven += digit;
            } else {
                sumOdd += digit;
            }
        }
        // Calculate the total sum of even and odd positioned digits
        int totalSum = sumEven + sumOdd;
        return totalSum % 10 == 0;
    }
}