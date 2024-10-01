import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   public Main() {
   }

   public static void main(String[] var0) {

      Scanner var1 = new Scanner(System.in);
      System.out.println("Ingrese el path al archivo de reglas: ");

      String reglasPath = var1.next();
      System.out.println("Ingrese el path del archivo de hechos: ");
      String hechosPath = var1.next();
      MotorDeInferencia motor = new MotorDeInferencia(reglasPath, hechosPath);
      System.out.println("Ingrese la opcion que desea:\n1. Inferencia hacia adelante.\n2. Inferencia hacia atras.\nIngrese la opcion: ");
      int var2 = var1.nextInt();
      switch(var2) {
         case 1:
            System.out.println("Estos fueron los hechos inferidos.");
            for (Hecho h: motor.inferenciaAdelante().getHechos()) {
               System.out.println(h);
            }
            break;

         case 2:
            System.out.println("Ingrese la meta.");
            var1.nextLine();
            String hecho = var1.nextLine();
            String[] tokens = hecho.split(" ");
            Hecho h = new Hecho();
            h.setId(tokens[tokens.length-1]);

            boolean result = motor.inferenciaAtras(h);
            if (tokens.length == 2) h.setNOT(true);
            if ((h.getNOT() != result)) {
               System.out.println("SI Se pudo alcanzar a la meta.");
            } else {
               System.out.println("NO se pudo alcanzar a la meta.");
            }
            break;

         default:
            System.out.println("Esta es una opcion invalida.");
            break;
      }
   }
}
