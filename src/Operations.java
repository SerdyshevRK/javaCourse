import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Operations {
    // Create an array with even numbers
    static int[] evenArray(){
        int[] retArray = new int[10];
        for (int i = 1; i <= retArray.length; i++) {
            retArray[i - 1] = i * 2;
        }
        return retArray;
    }
    // Create an array with odd numbers
    static int[] oddArray(){
        int[] retArray = new int[50];
        for (int i = 1; i <= retArray.length; i++) {
            retArray[i - 1] = (i * 2) - 1;
        }
        return retArray;
    }
    // Revers array elements
    static void reverseArray(int[] array){
        for (int i = 0; i < array.length / 2; i++) {
            int tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
    }
    // Count the number of even elements in array
    static int calculateEvenNumbers(int[] array){
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0)
                count++;
        }
        return count;
    }
    // Replace all elements with odd index with '0'
    static void replaceElementsInArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            if (i % 2 != 0)
                array[i] = 0;
        }
    }
    // Calculate arithmetic mean of array elements
    static float calculateMean(int[] array){
        float retValue = 0;
        for (int i = 0; i < array.length; i++) {
            retValue += array[i];
        }
        return retValue / array.length;
    }
    // Check if array is strictly increasing sequence
    static boolean checkArray(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] >= array[i + 1])
                return false;
        }
        return true;
    }
    // Create an array with first 20 elements of Fibonacci set
    static int[] fibonacciArray(){
        int[] retArray = new int[20];
        retArray[0] = retArray[1] = 1;
        for (int i = 2; i < retArray.length; i++){
            retArray[i] = retArray[i - 1] + retArray[i - 2];
        }
        return retArray;
    }
    // Find last index of element with max value
    static int findIndex(int[] array){
        int retValue = 0;
        int tmp = array[0];
        for (int i = 1; i < array.length; i++) {
            if (tmp <= array[i]){
                tmp = array[i];
                retValue = i;
            }
        }
        return retValue;
    }
    static int findIntegers(float[] array){
        int retValue = 0;
        for (int i = 0; i < array.length; i++){
            if ((array[i] * 10) % 10 == 0)
                retValue++;
        }
        return retValue;
    }
    // Count the number of same elements
    static String countElements(int[] array){
        int[] count = new int[3];
        int repeat = 0;
        int maxVal = -1, index = -1;
        String retValue = "*ничего не выводит*";

        for (int i = 0; i < array.length; i++) {        // count the number of same values:
            count[array[i] + 1]++;                      // count[0] - for '-1', count[1] - for '0', count[2] - for '1'
        }
        for (int i = 0; i < count.length; i++) {        // search for max value
            if (maxVal < count[i]) {
                maxVal = count[i];
                index = i;
            }
        }
        for (int i = 0; i < count.length; i++) {        // test for repeats
            if (maxVal == count[i]){
                repeat++;
            }
        }
        if (repeat == 1) {                              // if have no repeats (only one 'maxValue')
            if (maxVal > 0) {
                switch (index) {
                    case 0:
                        retValue = "В массиве чаще всего встречается '-1' (" + count[0] + " раз.)";
                        break;
                    case 1:
                        retValue = "В массиве чаще всего встречается '0' (" + count[1] + " раз.)";
                        break;
                    case 2:
                        retValue = "В массиве чаще всего встречается '1' (" + count[2] + " раз.)";
                        break;
                }
            }
        }
        return retValue;
    }
    static String compareHalves(int[] array){
        int leftSum = 0, rightSum = 0;
        if (array.length < 2)
            return "Что-то он не делится...";
        for (int i = 0; i < array.length / 2; i++) {
            leftSum += Math.abs(array[i]);
            rightSum += Math.abs(array[i + (array.length / 2)]);
        }
        if (leftSum == rightSum){
            return "Суммы модулей равны.";
        } else if (leftSum > rightSum){
            return "Сумма левой половины больше.";
        } else {
            return "Сумма правой половины больше";
        }
    }
    // Search for element with max value
    static int maxValueInMultiArray(int[][] array, int rows, int columns){
        int retValue = 0;
        for (int i = 0; i < rows * columns; i++) {
            if(retValue < array[i / columns][i % columns]) {
                retValue = array[i / columns][i % columns];
            }
        }
        return retValue;
    }
    // Multiplication of elements in array rows
    static int multiplicationElements(int[][] array){
        int[] multiplication = new int[array.length];
        int maxMult = 0, retIndex = -1;
        Arrays.fill(multiplication, 1);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                multiplication[i] *= array[i][j];
            }
        }
        for (int i = 0; i < multiplication.length; i++) {
            if (maxMult < Math.abs(multiplication[i])){
                maxMult = Math.abs(multiplication[i]);
                retIndex = i;
            }
        }
        return retIndex;
    }
    // Place element with max value in beginning of array
    static void formatArray(int[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array[i].length; j++) {
                if (array[i][0] < array[i][j]){
                    int tmp = array[i][0];
                    array[i][0] = array[i][j];
                    array[i][j] = tmp;
                }
            }
        }
    }
    // Multiplication table
    static int[][] multTable(){
        int[][] retArray = new int[10][10];
        for (int i = 0; i < retArray.length; i++) {
            for (int j = 0; j < retArray[i].length; j++) {
                if (i == 0 || i == 1 || j == 0 || j == 1)
                    retArray[i][j] = -1;
                if (retArray[i][j] != -1) {
                    retArray[i][j] = i * j;
                    if (i != j)
                        retArray[j][i] = -1;
                }
            }
        }
        return retArray;
    }
    static String encodeString(String string, String key){
        byte[] encodeString = string.getBytes();
        byte[] encodeKey = key.getBytes();
        
        for (int i = 0; i < encodeString.length; i++) {
            encodeString[i] = (byte)(encodeString[i]^encodeKey[i % encodeKey.length]);
        }
        System.out.println(Arrays.toString(encodeString));
        return new String(encodeString);
    }
    static String decodeString(String string, String key){
        byte[] decodeString = string.getBytes(StandardCharsets.UTF_8);
        byte[] decodeKey = key.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < decodeString.length; i++) {
            decodeString[i] = (byte)(decodeString[i]^decodeKey[i % decodeKey.length]);
        }
        return new String(decodeString);
    }
}
