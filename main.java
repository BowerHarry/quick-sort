import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// This program implements a quick sort algorithm on a list of names.
public class CW2Q2 {
    public static void main(String[] args){
        // Reads the names from a text file at the provided path into a String.
        Scanner scanner = null;
        try {
            // Provide the path of the text file here.
            scanner = new Scanner( new File("C:\\Users\\Harry\\OneDrive - University of Bath\\Programming\\CW4-C&Java\\Files\\Names\\names.txt"));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = scanner.useDelimiter("\\A").next();
        scanner.close();
        // Removes all the double quotes from the String.
        content = content.replace("\"", "");
        // Creates an array containing the names that are to be sorted.
        String[] namesArray = content.split(",");
        // For each name in the array, add it to an ArrayList.
        ArrayList<String> namesList = new ArrayList<>();
        int len = namesArray.length;
        for (int i = 0; i < len; i++) {
            namesList.add(namesArray[i]);
        }
        // Prints the original list of names.
        System.out.println("Before sorting...\n" + namesList);
        // Prints the sorted list of names.
        System.out.println("Sorted names...\n" + quicksort(namesList));
    }

    // This recursive function will return an alphabetically sorted ArrayList of names.
    private static ArrayList<String> quicksort(ArrayList<String> namesList) {
        // Number of names that need sorting.
        int len = namesList.size();
        // Set the pivot to be the last name on the list.
        String pivot = namesList.get(len - 1);
        // Will contain all the names that are sorted to be after the pivot.
        ArrayList<String> smallerList = new ArrayList<>();
        // Will contain all the names that are 'equal' to the pivot.
        ArrayList<String> pivotList = new ArrayList<>();
        // Will contain all the names that are sorted to be before the pivot.
        ArrayList<String> greaterList = new ArrayList<>();
        // Will contain the sorted names.
        ArrayList<String> sortedList = new ArrayList<>();
        pivotList.add(pivot);
        // Puts all of the names in the original list into one of the three sublists.
        for (int i = 0; i < namesList.size() - 1; i++) {
            if (compare(pivot, namesList.get(i)) > 0 ) {
                smallerList.add(namesList.get(i));
            }
            else if (compare(pivot, namesList.get(i)) < 0 ) {
                greaterList.add(namesList.get(i));
            }
            else {
                pivotList.add(namesList.get(i));
            }
        }
        // As long as the size of smaller list is greater than 1, perform the same sorting on this list.
        if (smallerList.size() > 1) {
            smallerList = quicksort(smallerList);
        }
        // As long as the size of greater list is greater than 1, perform the same sorting on this list.
        if (greaterList.size() > 1) {
            greaterList = quicksort(greaterList);
        }
        // All the three sublists are now sorted so now we add them all to one list.
        sortedList.addAll(greaterList);
        sortedList.addAll(pivotList);
        sortedList.addAll(smallerList);
        // Return the final sorted list.
        return sortedList;
    }

    // This function will compare two strings and calculate which string is first alphabetically.
    protected static int compare (String string1, String string2) {
        int minLength = min(string1.length(), string2.length());
        // For each character in the string...
        for(int i = 0; i < minLength; i++) {
            int char1 = string1.charAt(i);
            int char2 = string2.charAt(i);
            // If char 1 is first alphabetically, return 1 (meaning string 1 is first alphabetically).
            if (char1 < char2) {
                return 1;
            }
            // If char 2 is first alphabetically, return -1 (meaning string 2 is first alphabetically).
            else if (char1 > char2) {
                return -1;
            }
        }
        // If every character is equal then return 0.
        return 0;
    }

    // This function will return the min of two integers.
    protected static int min (int integer1, int integer2) {
        // Assume int2 is the smallest integer.
        int minInt = integer2;
        // If int1 is smaller than int2 then int1 is the smallest integer.
        if (integer1 < integer2) {
            minInt = integer1;
        }
        return minInt;
    }
}



