package practicum_3;

public class example_2 {
    public static void main(String[] args){
        int[] arr = {1,79,3,20,5,1,6,31,20,11};
        System.out.println(count(arr));

    }

    public static int count(int[] arr){
        int count = 0;

        for (int i = 0; i < arr.length; i++){
            boolean count_1 = false;
            for (int j = 0; j < i; j++ ){
                if (arr[i] == arr[j]){
                    count_1 = true;


                }

            }
            if (!count_1){
                count++;
            }

        }
        return count;
    }
}
