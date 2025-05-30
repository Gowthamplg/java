import java.rmi.Naming;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Calculator calc = (Calculator) Naming.lookup("rmi://localhost:5000/CalculatorService");

            System.out.println("Add: 5 + 3 = " + calc.add(5, 3));
            System.out.println("Subtract: 5 - 3 = " + calc.subtract(5, 3));
            System.out.println("Multiply: 5 * 3 = " + calc.multiply(5, 3));
            System.out.println("Divide: 5 / 3 = " + calc.divide(5, 3));

        } catch (Exception e) {
            System.out.println("Client exception: " + e);
        }
    }
}
