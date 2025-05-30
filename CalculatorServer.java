import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            // Start RMI registry on port 5000
            LocateRegistry.createRegistry(5000);

            // Create remote object
            Calculator calc = new CalculatorImpl();

            // Bind object to RMI registry with the name "CalculatorService"
            Naming.rebind("rmi://localhost:5000/CalculatorService", calc);

            System.out.println("Calculator Server is ready.");
        } catch (Exception e) {
            System.out.println("Server failed: " + e);
        }
    }
}
