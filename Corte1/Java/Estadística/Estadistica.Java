public class EstadisticaDemo {
    static class Estadistica {
        public static int suma(int[] a) {
            int total = 0;
            for (int x : a) total += x; // bucle for mejorado
            return total;
        }
        public static double promedio(int[] a) {
            return (double) suma(a) / a.length;
        }
    }
    public static void main(String[] args) {
        int[] grades = {80, 75, 90, 60, 95};
        System.out.println("Suma: " + Stats.suma(grades));
        System.out.println("Promedio: " + Stats.promedio(grades));
    }
}