import java.util.Random;

public class HomeWork {
    public static void main(String[] args) {
        Random rnd = new Random();
        float numberFloat;
        int numberInt;

        // Деление с остатком:
        Operations.divisionWithRemainder(rnd.nextInt(100), rnd.nextInt(100));
        System.out.println();

        // Сумма цифр двузначного числа
        numberInt = rnd.nextInt(100);
        if (numberInt < 10) {
            numberInt = 10;
        }
        Operations.sumOfNumbers(numberInt);
        System.out.println();

        // Окруление дробного числа до ближайшего целого
        numberFloat = rnd.nextFloat() + rnd.nextInt(20);
        Operations.roundNumber(numberFloat);
        System.out.println();

        // Сумма цифр трехзначного числа
        numberInt = rnd.nextInt(1000);
        if (numberInt < 100) {
            numberInt = 100;
        }
        Operations.sumOfNumbers(numberInt);
    }
}
