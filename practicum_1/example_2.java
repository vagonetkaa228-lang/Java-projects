package practicum_1;
//Вариант 7
import java.util.Scanner;

public class example_2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите значение X");

        double x = scanner.nextDouble();

        if (0 >= x) {
            System.out.println("X должен быть больше 0");
            return;

        }
        // y = x**5 + ln3(x)**3 + x**(3/2)
        double x_1 = Math.pow(x,5);
        double x_2 = Math.pow(Math.log(x), 3);
        double  x_3=  Math.sqrt(Math.pow(x, 3));

         double y = x_1 + x_2 + x_3;

        System.out.println(y);


    }
}
