package junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CircleTest {
    // In a test, you can create a Circle object with a given radius,
    // call area() method on it, and compare the return value with the expected value.

    private double inputRadius, expectedArea, expectedPerimeter;

    public CircleTest(double inputRadius, double expectedArea, double expectedPerimeter) {
        this.inputRadius = inputRadius;
        this.expectedArea = expectedArea;
        this.expectedPerimeter = expectedPerimeter;
    }

   /* @Test
    public void testPerimeters() {
        System.out.println("HERE, perimeter");
        assertEquals(expectedPerimeter, new Circle(inputRadius).perimeter(), 0.0001);}
*/
     @Test
      public void testArea() {
        System.out.println("HERE, area");
        assertEquals(expectedArea, new Circle(inputRadius).area(), 0.0001);
    }

    @Parameterized.Parameters //(name = "area({0}) =  {1}, perimeter({0}) = {2}}")
    public static Iterable<Object[]> loadTestData() {

        List<Object[]> inputExpectedList = new ArrayList<>();
        inputExpectedList.add(new Object[]{1, Math.PI, 2 * Math.PI});
        inputExpectedList.add(new Object[]{2, 4 * Math.PI, 4 * Math.PI});
        return inputExpectedList;
    }
}
