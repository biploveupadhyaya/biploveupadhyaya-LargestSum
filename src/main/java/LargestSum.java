
import java.util.List;
import java.util.*;
public class LargestSum {
    /**
     * Get the largest possible sum that can be obtained from a pair of values in the list. A number can't be added
     * to itself, unless there are duplicates.
     *
     * @param nums a list of ints.
     * @return the largest possible sum of separate numbers from nums.
     */
    public int bigSum(List<Integer> nums){
        if (nums == null || nums.size() < 2) {
            throw new IllegalArgumentException("List must contain at least two elements.");
        }

        // Map to store the count of each number
        Map<Integer, Integer> countMap = new HashMap<>();

        // Count occurrences of each number
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        // Determine the two largest distinct numbers
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int number = entry.getKey();
            int count = entry.getValue();

            if (count > 1) {
                // If there are duplicates, consider it for max1
                max1 = Math.max(max1, number);
            }
            if (number > max1) {
                max2 = max1; // Update max2 to previous max1
                max1 = number; // Update max1 to the new largest number
            } else if (number > max2) {
                max2 = number; // Update max2 if number is larger than current max2
            }
        }

        // Calculate the maximum sum
        int maxSum = (max1 != Integer.MIN_VALUE ? max1 : 0) + (max2 != Integer.MIN_VALUE ? max2 : 0);
        if (max1 == max2) {
            // If both are the same, use count to add them
            maxSum = max1 * 2; // If max1 == max2, and there's at least one duplicate
        }

        return maxSum;
    }
}