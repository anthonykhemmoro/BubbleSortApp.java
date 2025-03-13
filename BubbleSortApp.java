import java.io.*;
import java.util.*;

public class BubbleSortApp {
    
    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101); 
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.write(num);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static int[] readFileToArray(String filename) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; 
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the number of elements: ");
            int length = scanner.nextInt();

            int[] array = createRandomArray(length);
            System.out.println("Unsorted array: " + Arrays.toString(array));

            writeArrayToFile(array, "array.txt");
            System.out.println("Array written to file.");

            int[] readArray = readFileToArray("array.txt");
            System.out.println("Array read from file: " + Arrays.toString(readArray));

            bubbleSort(readArray);
            System.out.println("Sorted array: " + Arrays.toString(readArray));
            
            writeArrayToFile(readArray, "sorted_array.txt");
            System.out.println("Sorted array written to file.");
        }
    }
}