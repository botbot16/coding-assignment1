import java.util.Arrays;

/**
 * Provides functions to rotate arrays.
 */
public class RotateArray {

    private static final String NON_NEGATIVE_K_ERROR_MSG = "k must be non-negative.";
    private static final String EMPTY_ARRAY_ERROR_MSG = "array must non-empty.";

    /**
     * Rotates the input array k positions to the left, in a cyclic manner.
     * @param arr the array to be rotated.
     * @param k the amount of positions to be rotated.
     * @throws IllegalArgumentException if k < 0 or arr == null.
     */
    public static void RotateLeftInPlace(int[] arr, int k) {
        if (k < 0) {
            throw new IllegalArgumentException(NON_NEGATIVE_K_ERROR_MSG);
        }
        if (arr == null) {
            throw new IllegalArgumentException(EMPTY_ARRAY_ERROR_MSG);
        }
        if (arr.length == 0 || k == 0) {
            // array of length 0 can't be rotated.
            // rotating an array by 0 leaves it in place.
            return;
        }

        // rotating an array by arr.length leaves it in place.
        k = k % arr.length;

        int[] temp = new int[k];
        // save the first k elements, and move k elements to the left.
        for (int i = 0; i < k; i++) {
            temp[i] = arr[i];
            arr[i] = arr[i + k];
        }
        // move the rest of the elements of arr to the left.
        for (int i = k; i < arr.length - k; i++) {
            arr[i] = arr[i + k];
        }
        // move the elements from temp to the end of arr.
        for (int i = arr.length - k; i < arr.length; i++) {
            arr[i] = temp[i - (arr.length - k)];
        }
    }

    public static void main(String[] args) {
        int[] original_arr = new int[7];
        for (int i = 0; i < original_arr.length; i++) {
            original_arr[i] = i + 1;
        }
        int[] arr1 = original_arr.clone();
        System.out.println(Arrays.toString(arr1));
        RotateLeftInPlace(arr1, 2);
        System.out.println(Arrays.toString(arr1));
        assert arr1[0] == 3;
        assert arr1[1] == 4;
        assert arr1[2] == 5;
        assert arr1[3] == 6;
        assert arr1[4] == 7;
        assert arr1[5] == 1;
        assert arr1[6] == 2;

        int[] arr2 = original_arr.clone();
        System.out.println(Arrays.toString(arr2));
        RotateLeftInPlace(arr2, 8);
        System.out.println(Arrays.toString(arr2));
        assert arr2[0] == 2;
        assert arr2[1] == 3;
        assert arr2[2] == 4;
        assert arr2[3] == 5;
        assert arr2[4] == 6;
        assert arr2[5] == 7;
        assert arr2[6] == 1;

        int catches = 0;
        try {
            RotateLeftInPlace(original_arr, -1);
        } catch(IllegalArgumentException e) {
            assert e.getMessage().equals(NON_NEGATIVE_K_ERROR_MSG);
            catches++;
        }
        try {
            RotateLeftInPlace(null, 2);
        } catch(IllegalArgumentException e) {
            assert e.getMessage().equals(EMPTY_ARRAY_ERROR_MSG);
            catches++;
        }

        assert catches == 2;
    }
}
