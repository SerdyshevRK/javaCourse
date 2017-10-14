import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[3];

        // Odd or even number
        System.out.println(Operations.evenOddNumber(generateIntNumber(rnd, 200)));
        pause();
        System.out.println();

        // Number next to 10.
        System.out.println(Operations.tenComparer(generateFloatNumber(rnd), generateFloatNumber(rnd)));
        pause();
        System.out.println();

        // Find roots of the equation
        System.out.println(Operations.findRoots(generateRealNumber(rnd), generateRealNumber(rnd), generateRealNumber(rnd)));
        pause();
        System.out.println();

        // Compare number to interval
        System.out.println(Operations.intervalCompare(generateInterval(rnd, 5, 156)));
        pause();
        System.out.println();

        // The greatest digit
        System.out.println(Operations.greatestDigit(generateInterval(rnd, 100, 1000)));
        pause();
        System.out.println();

        // Sorting array of numbers
        System.out.println("Сортировка массива чисел:");
        int i = 0;
        while (i < array.length){
            System.out.println("Введите " + (i + 1) + " коэффициент (целое число):");
            if (!scanner.hasNextInt()){
                System.out.println("Вы ввели не целое число.");
                scanner.next();
                continue;
            }
            array[i] = scanner.nextInt();
            i++;
        }
        System.out.print("Числа в переменных: ");
        for (i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        Operations.sorting(array);
        System.out.println("\nВозрастающая последовательность: " + array[0] + " " + array[1] + " " + array[2]);
        pause();
        System.out.println();

        // Seconds to hours
        System.out.println(Operations.secondsToHours(generateIntNumber(rnd, 28801)));
        pause();

        // First sequence: 1000, 1003, 1006, 1009, ...
        System.out.println("Последовательность: 1000, 1003, 1006, 1009, ...");
        firstSequence();
        System.out.println();
        pause();

        // Second sequence: 1, 3, 5, 7, 9, 11, ...
        System.out.println("Последовательность: 1, 3, 5, 7, 9, 11, ...");
        secondSequence();
        System.out.println();
        pause();

        // Third sequence: 90, 85, 80, 75, 70, 65, 60, ...
        System.out.println("Последовательность: 90, 85, 80, 75, 70, 65, 60, ...");
        thirdSequence();
        System.out.println();
        pause();

        // Fourth sequence: 2, 4, 8, 16, 32, ...
        System.out.println("Последовательность: 2, 4, 8, 16, 32, ...");
        fourthSequence();
        System.out.println();
        pause();

        // Factorial
        System.out.println("Расчет факториала:");
        System.out.println("Введите натуральное число:");
        System.out.println(Operations.factorial(inputTest(scanner, "Введите натуральное число:")));
        System.out.println();
        pause();

        // Positive dividers
        System.out.println("Поиск делителей числа:");
        System.out.println("Введите натуральное число:");
        dividers(inputTest(scanner, "Введите натуральное число:"));
        System.out.println();
        pause();

        // Prime or composite number test
        System.out.println("Проверка числа на простоту:");
        System.out.println("Введите натуральное число:");
        primeNumberTest(inputTest(scanner, "Введите натуральное число"));
        System.out.println();
        pause();

        // Fibonacci series
        fibonacci();
        System.out.println();
        pause();

        // Sum of digits
        System.out.println("Сумма цифр числа:");
        System.out.println("Введите натуральное число:");
        int number = inputTest(scanner, "Введите натуральное число:");
        System.out.println("Сумма цифр числа " + number + ":");
        System.out.println(Operations.sumOfDigit(number));
        System.out.println();
        pause();

        // Lucky tickets
        System.out.println("Количество счастливых билетов из интервала (000001 - 999999):");
        System.out.println(Operations.luckyTickets());
        System.out.println();
        pause();

        // Wrong labels
        System.out.println("Количество ошибочных табличек:");
        System.out.println(Operations.wrongLabels());
        System.out.println();
        pause();

        // Symmetry face
        System.out.println("Симметричная комбинвция цифр на цифровых часах за сутки случается " + Operations.symmetryFace() + " раз.");
        System.out.println();
        pause();

        // Nmber of excluded machines
        System.out.println("Из прораммы учений придется исключить " + Operations.excludedMachines() + " единиц техники.");
    }
    static int generateIntNumber(Random rnd, int bound){
        if (bound == -1){
            return rnd.nextInt();
        }
        return rnd.nextInt(bound);
    }
    static int generateInterval(Random rnd, int minValue, int maxValue){
        return generateIntNumber(rnd,(maxValue - minValue)) + minValue;
    }
    static float generateFloatNumber(Random rnd){
        return generateIntNumber(rnd, 50) + rnd.nextFloat();
    }
    static float generateRealNumber(Random rnd){
        int sign;                       // sign of number '> 0' or '< 0'

        if(generateIntNumber(rnd, 11) > 5){
            sign = -1;
        } else {
            sign = 1;
        }
        return generateFloatNumber(rnd) * sign;
    }
    static void pause(){
        System.out.println("Press 'Enter' to continue...");
        try {
            System.in.read();
        } catch (Exception e){}
    }
    static void firstSequence(){            // 1000, 1003, 1006, 1009, ...
        int count = 0;
        for (int i = 1000; i < 10000; i += 3){
            System.out.print(i + " ");
            count++;
            if (count == 30) {
                System.out.println();
                count = 0;
            }
        }
    }
    static void secondSequence(){           // 1, 3, 5, 7, 9, 11, ...
        int count = 0;
        int result = 1;

        for (int i = 0; i < 55; i++){
            System.out.print(result + " ");
            count++;
            if (count == 20){
                System.out.println();
                count = 0;
            }
            result += 2;
        }
    }
    static void thirdSequence(){             // 90, 85, 80, 75, 70, 65, 60, ...
        int count = 0;
        for (int i = 90; i >= 0; i -= 5){
            System.out.print(i + " ");
            count++;
            if (count == 20){
                System.out.println();
                count = 0;
            }
        }
    }
    static void fourthSequence(){             // 2, 4, 8, 16, 32, ...
        int count = 0;
        int result = 2;
        for (int i = 0; i < 20; i++){
            System.out.print(result + " ");
            count++;
            if (count == 20){
                System.out.println();
                count = 0;
            }
            result *= 2;
        }
    }
    static int inputTest(Scanner sc, String message){
        while (!sc.hasNextInt()){
            System.out.println(message);
            sc.next();
        }
        return sc.nextInt();
    }
    static void dividers(int number){
        int divResult;
        System.out.println("Делители числа " + number + ":");
        for (int i = 1; i < Math.sqrt(number); i++){
            if ((number % i) != 0){
                continue;
            }
            System.out.print(i + " ");
            divResult = number / i;
            if (i != divResult)
                System.out.print(divResult + " ");
        }
    }
    static void primeNumberTest(int number){
        for (int i = 2; i < Math.sqrt(number); i++){
            if ((number % i) == 0){
                System.out.println("Число " + number + " - составное.");
                return;
            }
        }
        System.out.println("Число " + number + " - простое.");
    }
    static void fibonacci(){
        int[] array = new int[11];
        array[0] = array[1] = 1;
        for (int i = 2; i < array.length; i++){
            array[i] = array[i - 2] + array[i - 1];
        }
        System.out.println("Числа Фибоначчи:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
