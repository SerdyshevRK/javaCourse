import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] workArray;
        int[] tmpArray;
        float[] firstArray;
        float[] secondArray;
        float[] thirdArray;
        int[][] multiWorkArray;
        Random rnd = new Random();
        Scanner scanner = new Scanner(System.in);

        // Output: array with even numbers
        System.out.println("Массив с четными числами от 2 до 20:");
        System.out.println(Arrays.toString(Operations.evenArray()));      // in a row
        for (int i = 0; i < Operations.evenArray().length; i++) {         // in a column
            System.out.println(Operations.evenArray()[i]);
        }
        System.out.println();

        // Output: array with odd numbers
        System.out.println("Массив с нечетными числами от 1 до 99:");
        workArray = Operations.oddArray();
        System.out.println(Arrays.toString(workArray));       // direct order
        Operations.reverseArray(workArray);
        System.out.println(Arrays.toString(workArray));       // reverse order
        System.out.println();

        // Count the number of even elements in array
        System.out.println("Подсчет количества четных элементов в массиве:");
        workArray = generateRandomArray(rnd,15, 0, 9);  // filling an array
        System.out.println(Arrays.toString(workArray));
        System.out.println("Четных элементов: " + Operations.calculateEvenNumbers(workArray));
        System.out.println();

        // Replace elements with odd index
        System.out.println("Замена элементов массива с нечетным индексом:");
        workArray = generateRandomArray(rnd,8, 1, 10);  // filling an array
        System.out.println("Исходный массив: " + Arrays.toString(workArray));        // original array
        Operations.replaceElementsInArray(workArray);
        System.out.println("Измененный массив: " + Arrays.toString(workArray));      // modified array
        System.out.println();

        // Calculate and compare arithmetic mean of elements in two arrays
        System.out.println("Сравниваем среднее арифметическое элементов из двух массивов:");
        workArray = generateRandomArray(rnd, 5, 0, 5);
        tmpArray = generateRandomArray(rnd, 5, 0, 5);
        System.out.println("Первый массив: " + Arrays.toString(workArray));
        System.out.println("Второй массив: " + Arrays.toString(tmpArray));
        if (Operations.calculateMean(workArray) == Operations.calculateMean(tmpArray)){
            System.out.println("Среднее арифметическое элементов одинаковы для обоих массивов.");
        } else {
            String result;
            if (Operations.calculateMean(workArray) > Operations.calculateMean(tmpArray)){
                result = "первого";
            } else {
                result = "второго";
            }
            System.out.println("Среднее арифметическое " + result + " массива оказалось больше");
        }
        System.out.println(Operations.calculateMean(workArray) + ", " + Operations.calculateMean(tmpArray));
        System.out.println();

        // Check if array is strictly increasing sequence
        System.out.println("Проверка массива на строгое возрастание:");
        workArray = generateRandomArray(rnd, 4, 10, 99);
        System.out.println(Arrays.toString(workArray));
        if (Operations.checkArray(workArray)){
            System.out.println("Массив строго возрастающая последовательность.");
        } else {
            System.out.println("Массив не является строго возрастающей последовательностью.");
        }
        System.out.println();

        // Array with first 20 elements of Fibonacci set
        System.out.println("Последовательность Фибоначчи:");
        System.out.println(Arrays.toString(Operations.fibonacciArray()));
        System.out.println();

        // Find last index of element with max value in array
        System.out.println("Определение последнего вхождения елемента массива с максимальным значением:");
        workArray = generateRandomArray(rnd, 12, -15, 15);
        System.out.println(Arrays.toString(workArray));
        System.out.println("Индекс последнео вхождения: " + Operations.findIndex(workArray));
        System.out.println();

        // Three arrays: find integers in third array
        System.out.println("Поиск целых чисел в массиве:");
        firstArray = generateFloatRandomArray(rnd, 10, 1, 9);
        secondArray = generateFloatRandomArray(rnd, 10, 1, 9);
        thirdArray = new float[firstArray.length];
        for (int i = 0; i < firstArray.length; i++) {
            thirdArray[i] = firstArray[i] / secondArray[i];
        }
        System.out.println(Arrays.toString(firstArray));
        System.out.println(Arrays.toString(secondArray));
        System.out.println(Arrays.toString(thirdArray));
        System.out.println("Количество целых чисел: " + Operations.findIntegers(thirdArray));
        System.out.println();

        // Count the number of elements with same values
        workArray = generateRandomArray(rnd, 11, -1, 1);
        System.out.println(Arrays.toString(workArray));
        System.out.println(Operations.countElements(workArray));

        // Compare two halves of array
        int arrayLength = consoleIntegerInput(scanner, 0, "Введите целое, положительное число:");
        workArray = generateRandomArray(rnd, arrayLength, -5, 5);
        System.out.println(Arrays.toString(workArray));
        System.out.println(Operations.compareHalves(workArray));
        System.out.println();

        // Generate an array with an equal number of negative and positive values
        System.out.println("Генерируется массив с равным количеством отрицательных и положительных значений:");
        System.out.println(Arrays.toString(generateWhateverItIs(rnd)));

        // Create array from another array
        System.out.println("Создание нового массива из четных элементов исходного:");
        arrayLength = consoleIntegerInput(scanner, 3, "Введите целое число больше трех:");
        workArray = generateRandomArray(rnd, arrayLength, 0, arrayLength);
        System.out.println("Исходный массив:");
        System.out.println(Arrays.toString(workArray));
        System.out.println("Новый массив:");
        System.out.println(Arrays.toString(generateArrayFromArray(workArray)));
        System.out.println();

        // Multidimensional arrays

        // Create array 8x5
        System.out.println("Многомерный массив рамерностью (8х5):");
        multiWorkArray = generateMultidemetionalArray(rnd, 8, 5, 10, 99);
        for (int i = 0; i < 8; i++) {
            System.out.println(Arrays.toString(multiWorkArray[i]));
        }
        System.out.println();

        // Create array 5x8
        System.out.println("Многомерный массив рамерностью (5х8):");
        multiWorkArray = generateMultidemetionalArray(rnd, 5, 8, -99, 99);
        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(multiWorkArray[i]));
        }
        // Search for element with max value
        System.out.println("Наибольшее значение элемента в массиве: " + Operations.maxValueInMultiArray(multiWorkArray, 5, 8));
        System.out.println();

        // Create array 7x4
        System.out.println("Многомерный массив рамерностью (7x4):");
        multiWorkArray = generateMultidemetionalArray(rnd, 7, 4, -5, 5);
        for (int i = 0; i < 7; i++) {
            System.out.println(Arrays.toString(multiWorkArray[i]));
        }
        System.out.println("Индекс строки с наибольшем по модулю произведением элементов: " + Operations.multiplicationElements(multiWorkArray));
        System.out.println();

        // Create array 6x7
        multiWorkArray = generateMultidemetionalArray(rnd, 6, 7, 0, 9);
        for (int i = 0; i < 6; i++) {
            System.out.println(Arrays.toString(multiWorkArray[i]));
        }
        System.out.println();
        System.out.println("Преобразованный массив (элемент с наибольшим значением впереди):");
        Operations.formatArray(multiWorkArray);
        for (int i = 0; i < 6; i++) {
            System.out.println(Arrays.toString(multiWorkArray[i]));
        }
        System.out.println();

        // Multiplication tasks selection
        System.out.println("Примеры из таблицы умножения:");
        multiWorkArray = Operations.multTable();
        int[] validValues = new int[countNotEmpty(multiWorkArray)];
        int pointer = 0;
        for (int i = 22; i < 100; i++) {
            if (multiWorkArray[i / 10][i % 10] != -1){
                validValues[pointer] = multiWorkArray[i / 10][i % 10];
                pointer++;
            }
        }
        int randomCase;
        for (int i = 0; i < 15; i++) {
            randomCase = validValues[rnd.nextInt(validValues.length)];       // take random valid value
            for (int j = 22; j < 100; j++) {
                if (randomCase == multiWorkArray[j / 10][j % 10]){
                    System.out.println("пример #" + (i + 1) + ": " + (j / 10) + " * " + (j % 10) + " (= " + randomCase + ")");
                    multiWorkArray[j / 10][j % 10] = -1;                     // exclude repeating
                    break;
                }
            }
        }
        System.out.println();

        // Encode/decode string
        System.out.println("Кодирование строки:");
        String str = Operations.encodeString("Some string", "abc");
        System.out.println(str);
        System.out.println(Arrays.toString(str.getBytes()));
        String decStr = Operations.decodeString(str, "abc");
        System.out.println(Arrays.toString(decStr.getBytes()));
        System.out.println(decStr);
    }
    static int consoleIntegerInput(Scanner scanner, int bottomLine, String message){
        System.out.println(message);
        int arrayLength;
        while (true){
            if (scanner.hasNextInt()) {
                arrayLength = scanner.nextInt();
                if (arrayLength <= bottomLine) {
                    System.out.println(message);
                    continue;
                }
                break;
            } else {
                System.out.println(message);      // if input is not an integer number
                scanner.next();
            }
        }
        return arrayLength;
    }
    static int[] generateRandomArray(Random rnd, int arrayLength, int minValue, int maxValue){
        int[] retArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            if (minValue < 0){
                retArray[i] = rnd.nextInt(maxValue + Math.abs(minValue) + 1) + minValue;
            } else if (minValue == 0) {
                retArray[i] = rnd.nextInt(maxValue + 1);
            } else {
                retArray[i] = rnd.nextInt(maxValue - minValue + 1) + minValue;
            }
        }
        return retArray;
    }
    // Create 'float' array with integer values
    static float[] generateFloatRandomArray(Random rnd, int arrayLength, int minValue, int maxValue){
        float[] retArray = new float[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            if (minValue < 0){
                retArray[i] = rnd.nextInt(maxValue + Math.abs(minValue) + 1) + minValue;
            } else if (minValue == 0) {
                retArray[i] = rnd.nextInt(maxValue + 1);
            } else {
                retArray[i] = rnd.nextInt(maxValue - minValue + 1) + minValue;
            }
        }
        return retArray;
    }
    static int[] generateArrayFromArray(int[] array){
        int[] retArray = new int[Operations.calculateEvenNumbers(array)];
        int retArrayI = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0){
                retArray[retArrayI] = array[i];
                retArrayI++;
            }
        }
        return retArray;
    }
    static int[] generateWhateverItIs(Random rnd){
        int[] mainArray = new int[12];
        int[] negativeArray = generateRandomArray(rnd, 6, -10, -1);
        int[] positiveArray = generateRandomArray(rnd, 6, 1, 10);
        int negI = 0, posI = 0, randomCase;

        for (int i = 0; i < mainArray.length; i++) {
            randomCase = rnd.nextInt(4);             // 0, 1 - for negative; 2, 3 - for positive
            if (randomCase < 2){
                if (negI != negativeArray.length){
                    mainArray[i] = negativeArray[negI];
                    negI++;
                } else {                                    // if negativeArray reached the end take elements from positiveArray
                    mainArray[i] = positiveArray[posI];
                    posI++;
                }
            } else {
                if (posI != positiveArray.length){
                    mainArray[i] = positiveArray[posI];
                    posI++;
                } else {                                    // if positiveArray reached the end take elements from negativeArray
                    mainArray[i] = negativeArray[negI];
                    negI++;
                }
            }
        }
        return mainArray;
    }
    static int[][] generateMultidemetionalArray(Random rnd, int rows, int columns, int minValue, int maxValue){
        int[][] retArray = new int[rows][];
        for (int i = 0; i < rows; i++) {
            retArray[i] = generateRandomArray(rnd, columns, minValue, maxValue);
        }
        return retArray;
    }
    static int countNotEmpty(int[][] array){
        int count = 0;
        for (int i = 22; i < 100; i++) {
            if (array[i / 10][i % 10] != -1)
                count++;
        }
        return count;
    }
}
