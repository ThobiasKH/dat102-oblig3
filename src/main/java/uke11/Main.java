package uke11;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int totalElements = 100000;
        final int searchElements = 10000;
        
        HashSet<Integer> hashSet = new HashSet<>();
        Integer[] table = new Integer[totalElements];

        Random rand = new Random();
        int tall = rand.nextInt(1000000); 

        for (int i = 0; i < totalElements; i++) {
            hashSet.add(tall);
            table[i] = tall;
            tall = (tall + 45713) % 1000000; 
        }

        Arrays.sort(table);

        Integer[] searchNumbers = new Integer[searchElements];
        for (int i = 0; i < searchElements; i++) {
            searchNumbers[i] = rand.nextInt(1000000);
        }

        long startTime = System.nanoTime();
        int hashSetFound = 0;
        for (int i = 0; i < searchElements; i++) {
            if (hashSet.contains(searchNumbers[i])) {
                hashSetFound++;
            }
        }
        long hashSetTime = System.nanoTime() - startTime;
        System.out.println("HashSet søketid: " + hashSetTime + " ns");
        System.out.println("Funne tal i HashSet: " + hashSetFound);

        startTime = System.nanoTime();
        int tableFound = 0;
        for (int i = 0; i < searchElements; i++) {
            if (binarySearch(table, searchNumbers[i])) {
                tableFound++;
            }
        }
        long tableTime = System.nanoTime() - startTime;
        System.out.println("Binærsøk søketid: " + tableTime + " ns");
        System.out.println("Funne tal i tabellen: " + tableFound);
    }

    public static boolean binarySearch(Integer[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == key) {
                return true;
            }
            if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
