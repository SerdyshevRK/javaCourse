public class Operations {

    public static void divisionWithRemainder(int q, int w){
        int result, remainder;

        result = q / w;
        remainder = q % w;

        System.out.println("Деление с остатком:");
        System.out.println(q + " / " + w + " = " + result + " и " + remainder + " в остатке.");
    }

    public static void sumOfNumbers(int number){
        int result,tmp;

        result = 0;
        tmp = number;
        while (number > 0){
            result += number % 10;
            number /= 10;
        }
        System.out.println("Сумма цифр числа " + tmp + " равна " + result);
    }

    public static void roundNumber(float number){
        int result = Math.round(number);
        System.out.println("Число " + number + " округлилось до " + result);
    }
}
