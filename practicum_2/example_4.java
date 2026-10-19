package practicum_2;

public class example_4 {

    public static void printInterval(double a, double b){
        double max;
        double min;
        if(a > b){

            max = a;
            min = b;
        }
        else{

            max = b;
            min = a;
        }
        // целое c: округляет в
        int c = (int) Math.ceil(max);

        // целое d: округляет в меньшую сторону
        int d = (int) Math.floor(min);


        System.out.println(min + " " + max);


        System.out.println(c + " " + d);


        System.out.println(c + " >= " + max + " > " + min + " >= " + d);


        

    }
    public static void main(String[] args){
        printInterval(-3.5, -5.6);


    }
}
