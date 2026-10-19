package practicum_2;

 import java.util.Scanner;

public class example_2 {
    public static void main(String[] args){

        Scanner scanner =  new Scanner(System.in);
        int N = scanner.nextInt();
        double X = scanner.nextDouble();

        double sum_rd = 1;
        double factorial = 1;
        for( int n = 1; n <= N; n++){
            factorial *= n;
            sum_rd+= (Math.pow(X, n)) / factorial;

        }

        System.out.println(sum_rd);



    }

}
