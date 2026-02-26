import java.util.Scanner;

public class Arreglos {
    public int [] data;
    public int n ;

    public Arreglos( int n) {
        this.n = n;
        this.data = new int[n]; //cear el arreglo
    }

    public void getArreglos (String name) {
        System.out.println(name + " = [ ");
        for (int i = 0; i < n ; i ++) {
            System.out.println(data[i]);
            if (i < n - 1){
                System.out.println(" , ");
            }                      
        }
        System.out.println(" [ ");
    }

        public v. setArreglos (int i = 0; i < n ; i ++){
        System.out.println(" v[ " + i + " ]: ");
           v.data[i] = sc.nextInt();

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        System.out.println("Tamaño n : " );
        int n = sc.nextInt();

        Arreglos v = new Arreglos (n);

        sc.close();
    }
}
    
    

