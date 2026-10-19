package practicum_1;

import java.util.Scanner;
//Вариант 7
public class example_3 {

    public static void main(String[] args){

        System.out.println("Введите первое число:");
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();

        System.out.println("Введите второе число:");
        double b = scanner.nextDouble();

        System.out.println("Введите третье число:");
        double c = scanner.nextDouble();

        if  ((a <= b) && ( a <= c) ){
            System.out.println(a + " - Наименьшее число");
        }

        else if ((b <= a)  && ( b <= c) ){
            System.out.println(b + " - Наименьшее число");
        }

        else {
            System.out.println(c + " - Наименьшее число");
        }


    }
}
