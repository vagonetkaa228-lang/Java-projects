

package practicum_2;

import java.util.Scanner;

public class example_1 {
    public static void main(String[] args){
        double sum_rd = 0;
        Scanner scanner = new Scanner(System.in);


        int N = scanner.nextInt();

         for(double n =1.0; n < N; n++ ){
             sum_rd+=(1/n);
         }

        System.out.println(sum_rd);

    }
}
