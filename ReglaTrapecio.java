import java.util.Scanner;

public class ReglaTrapecio {

    public static double f(double x) {
        return Math.exp(Math.pow(x, 4));
    }

    public static double reglaTrapecio(double a, double b, int n) {
        double h = (b - a) / n;
        System.out.println("\nh = " + h);

        double suma = 0;
        System.out.println("\n--- Cálculo de los puntos ---");
        for (int k = 1; k < n; k++) {
            double xk = a + k * h;
            double fx = f(xk);
            suma += fx;
            System.out.printf("k=%d  xk=%.6f   f(xk)=%.6e%n", k, xk, fx);
        }

        double resultado = h * ((f(a) + f(b)) / 2 + suma);

        System.out.println("\nAplicando la fórmula:");
        System.out.println("∫ f(x) dx ≈ h * [ (f(a)+f(b))/2 + sum f(xk) ]");

        return resultado;
    }

    public static double errorAbsoluto(double aproximado, double exacto) {
        return Math.abs(aproximado - exacto);
    }

    public static long medirTiempo(Runnable metodo) {
        long inicio = System.nanoTime();
        metodo.run();
        long fin = System.nanoTime();
        return (fin - inicio) / 1_000_000;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Regla del Trapecio ===");

        System.out.print("Ingrese a: ");
        double a = sc.nextDouble();

        System.out.print("Ingrese b: ");
        double b = sc.nextDouble();

        System.out.print("Ingrese número de intervalos n: ");
        int n = sc.nextInt();

        System.out.print("Valor exacto (0 si no lo conoce): ");
        double exacto = sc.nextDouble();

        long tiempo = medirTiempo(() -> {
            double aprox = reglaTrapecio(a, b, n);
            System.out.printf("%nAproximación numérica: %.10e%n", aprox);

            if (exacto != 0) {
                double ea = errorAbsoluto(aprox, exacto);
                double er = ea / Math.abs(exacto);

                System.out.println("Error absoluto: " + ea);
                System.out.println("Error relativo: " + er);
            }
        });

        System.out.println("\nTiempo de ejecución: " + tiempo + " ms");
        System.out.println("Iteraciones realizadas: " + (n - 1));
    }
}
