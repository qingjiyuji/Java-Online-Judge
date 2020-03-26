import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int[] pow(int[] nums) {
        List<Integer> l = new ArrayList<Integer>();
        for (int num : nums) {
            int temp = num * num;
            if (!l.contains(temp)) {
                l.add(temp);
            }
        }
        int[] result = new int[l.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = l.get(i);
        }
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
        	nums[i] = s.nextInt();
        }
        s.close();
        int[] result = pow(nums);
        String str = String.valueOf(result[0]);
        for (int i = 1; i < result.length; i++) {
            str += " " + result[i];
        }
        System.out.print(str);
    }
}

