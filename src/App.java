import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // App app = new App();
        // System.out.println(app.menu());
        new AppGUI();
        // TODO
    }

    public String menu(){
        Scanner input = new Scanner(System.in);

        System.out.print("Ingresa Algoritmo de Busqueda: ");
        try {
            int choice = input.nextInt();

            input.close();

            switch (choice) {
                case 1:{
                    return new String("Busqueda en Profundidad");
                    // break;
                }
                case 2:{
                    return new String("Busqueda en Amplitud");
                    // break;
                }
                case 3:{
                    return new String("Escalado de Colina");
                    // break;
                }
                case 4:{
                    return new String("Primero el Mejor");
                    // break;
                }
                case 5:{
                    return new String("A*");
                    // break;
                }
                default:
                    return new String("Algoritmo no valido.");
                    // break;
            }
        } catch (Exception e) {
            // e.printStackTrace();
            return new String("Valor ingresado no valido.");
        }
    }
}
