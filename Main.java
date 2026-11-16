package practicum_5;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(3, 4);

        System.out.println(circle.describe());
        System.out.println(rectangle.describe());

        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(5));
        shapes.add(new Rectangle(3, 4));
        shapes.add(new Circle(1));
        shapes.add(new Rectangle(10, 2));


        // Обходим массив через общий тип Shape
        System.out.println("Before sorting:");
        for (Shape s : shapes) {
            System.out.println("  " + s.describe());
        }

        Collections.sort(shapes);   // работает благодаря Comparable<Shape>

        System.out.println("After sorting (by area, ascending):");
        for (Shape s : shapes) {
            System.out.println("  " + s.describe());
        }
    }
}