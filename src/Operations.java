public class Operations {
    public static String evenOddNumber(int number){
        String type = "";
        if((number % 2) != 0){
            type = "не";
        }
        return "Число " + number + " - " + type + " четное.";
    }
    public static String tenComparer(float firstNumber, float secondNumber){
        float tmp;
        if((firstNumber - 10) > (secondNumber - 10)){
            tmp = secondNumber;
        } else {
            tmp = firstNumber;
        }
        return "Из чисел " + firstNumber + " и " + secondNumber + " ближайшее к 10: " + tmp;
    }
    public static String findRoots(float coefA, float coefB, float coefC){
        double discriminant, x1, x2;
        discriminant = Math.pow((double) coefB, 2) -  4 * coefA * coefC;
        if (discriminant < 0){
            return "При коэффициентах квадратного уравнения: a = " + coefA + ", b = " + coefB + ", c = " + coefC +
                    "\nУравнение не имеет вещественных корней.";
        }
        x1 = (-1 * coefB + Math.sqrt(discriminant)) / 2;
        x2 = (-1 * coefB - Math.sqrt(discriminant)) / 2;
        return "При коэффициентах квадратного уравнения: a = " + coefA + ", b = " + coefB + ", c = " + coefC +
                "\nКорни этого уравнения: x1 = " + x1 + ", x2 = " + x2;
    }
    public static String intervalCompare(int number){
        String str = "";
        if ((number <= 25) || (number >= 100)){
            str = " не";
        }
        return "Число " + number + str + " содержится в интервале (25, 100).";
    }
    public static String greatestDigit(int number){
        int digit = 0;
        int tmpNumber = number;
        int tmp;
        while (number > 0){
            tmp = number % 10;
            if(digit < tmp)
                digit = tmp;
            number /= 10;
        }
        return "В числе " + tmpNumber + " наибольшая цифра: " + digit;
    }
    public static void sorting(int[] array){
        for (int i = 0; i < array.length - 1; i++){
            boolean flag = false;
            for (int j = 0; j < array.length - i - 1; j++){
                if (array[j] > array[j + 1]){
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    flag = true;
                }
            }
            if(flag == false)
                break;
        }
    }
    public static String secondsToHours(int seconds){
        String retValue = "";
        int hours = (seconds / 3600);
        switch (hours){
            case 8:
            case 7:
            case 6:
            case 5:
                retValue = seconds + "\nОсталось " + hours + " часов.";
                break;
            case 4:
            case 3:
            case 2:
                retValue = seconds + "\nОсталось " + hours + " часа.";
                break;
            case 1:
                retValue = seconds + "\nОстался " + hours + " час.";
                break;
            case 0:
                retValue = seconds + "\nОсталось менее часа.";
                break;
        }
        return retValue;
    }
    public static double factorial(int number){
        double result = 1;
        if(number < 2)
            return result;
        for (int i = 2; i <= number; i++){
            result *= i;
        }
        return result;
    }
    public static int sumOfDigit(int number){
        int result = 0;
        while (number > 0){
            result += number % 10;
            number /= 10;
        }
        return result;
    }
    public static int luckyTickets(){
        int[] array = new int[28];
        int d1, d2, d3, sum, result = 0;

        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
        for (int i = 0; i <= 999; i++) {            // counting number of ways to get sum
            d1 = i / 100;                           // 001 - sum = 1
            d2 = (i / 10) % 10;                     // 010 - sum = 1
            d3 = i % 10;                            // 100 - sum = 1
            sum = d1 + d2 + d3;                     // total: 3 ways to get this sum
            array[sum]++;
        }
        for (int i = 0; i < array.length; i++) {    // counting number of lucky tickets
            result += array[i] * array[i];
        }
        result--;                                   // '000000' - don't exist, so need to decrement 'result' by one
        return result;
    }
    public static int wrongLabels(){
        int result = 0;
        int[] digits = new int[5];

        for (int i = 1; i <= 50000; i++) {
            digits[0] = i / 10000;
            digits[1] = (i / 1000) % 10;
            digits[2] = (i / 100) % 10;
            digits[3] = (i / 10) % 10;
            digits[4] = i % 10;
            for (int j = 0; j < digits.length; j++) {
                if (digits[j] == 2) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }
    public static int symmetryFace(){
        int d1, d2, result = 0;
        for (int i = 0; i < 24; i++) {
            d1 = i / 10;
            d2 = i % 10;
            if(((d2 * 10) + d1) < 60)
                result++;
        }
        return result;
    }
    public static int excludedMachines(){
        int result = 0;
        for (int i = 1; i < 100000; i++) {
            if(String.valueOf(i).contains("4") || String.valueOf(i).contains("13"))
                result++;
        }
        return result;
    }
}
