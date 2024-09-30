import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
   public Main() {
   }

   public static void main(String[] var0) {
      BaseDeHechos var1 = new BaseDeHechos();
      ArrayList var2 = new ArrayList();

      try {
         System.out.println("Leyendo hechos desde el archivo 'hechos.txt'...");
         List var3 = Files.readAllLines(Paths.get("hechos1.txt"));
         String[] var4 = ((String)var3.get(0)).split(",");
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            String var7 = var4[var6];
            var1.agregarHecho(var7.trim());
            System.out.println(var7.trim());
            System.out.println(" hhh ");
         }

         System.out.println("Leyendo reglas desde el archivo 'reglas.txt'...");
         List var14 = Files.readAllLines(Paths.get("reglas1.txt"));
         Iterator var16 = var14.iterator();

         while(var16.hasNext()) {
            String var18 = (String)var16.next();
            String[] var20 = var18.split("=>");
            List var8 = Arrays.asList(var20[0].trim().split(","));
            String var9 = var20[1].trim();
            var2.add(new Regla(var8, var9));
            PrintStream var10001 = System.out;
            Objects.requireNonNull(var10001);
            var8.forEach(var10001::println);
            System.out.println("->premisa");
            System.out.println(var9);
            System.out.println("->conclusion");
         }
      } catch (IOException var12) {
         System.err.println("Error al leer los archivos: " + var12.getMessage());
         return;
      }

      Scanner var13 = new Scanner(System.in);

      try {
         System.out.println("Ingrese la meta que desea probar:");
         String var15 = var13.nextLine().trim();
         MotorDeInferencia var17 = new MotorDeInferencia(var2, var1);
         System.out.println("\nComenzando inferencia hacia atr\u00e1s...");
         boolean var19 = var17.inferenciaAtras(var15);
         if (var19) {
            System.out.println("\nSe ha alcanzado la meta: " + var15);
         } else {
            System.out.println("\nNo se pudo alcanzar la meta: " + var15);
         }
      } catch (Throwable var11) {
         try {
            var13.close();
         } catch (Throwable var10) {
            var11.addSuppressed(var10);
         }

         throw var11;
      }

      var13.close();
   }
}
