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
        // move the elements from temp to arr (left to right).
        for (int i = arr.length - k; i < arr.length; i++) {
            arr[i] = temp[i - (arr.length - k)];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        System.out.println(Arrays.toString(arr));
        RotateLeftInPlace(arr, 7);
        System.out.println(Arrays.toString(arr));
        assert arr[0] == 2;
        assert arr[1] == 3;
        assert arr[2] == 4;
        assert arr[3] == 0;
        assert arr[4] == 1;

        int catches = 0;
        try {
            RotateLeftInPlace(arr, -1);
        } catch(IllegalArgumentException e) {
            assert e.getMessage().equals(NON_NEGATIVE_K_ERROR_MSG);
            catches++;
        }
        try {
            RotateLeftInPlace(null, 7);
        } catch(IllegalArgumentException e) {
            assert e.getMessage().equals(EMPTY_ARRAY_ERROR_MSG);
            catches++;
        }

        assert catches == 2;
    }
}
