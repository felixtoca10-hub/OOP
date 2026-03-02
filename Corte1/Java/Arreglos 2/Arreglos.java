import java.util.Scanner;

public class Arreglos {

    public int [] data;
    public int n ;

    public Arreglos( int n) {
        this.n = n;
        this.data = new int[n]; //cear el arreglo
    }

    public void getvector (String name) {
        System.out.println(name + " = [ ");
        for (int i = 0; i < n ; i ++) {
            System.out.println(data[i]);
            if (i < n - 1){
                System.out.println(" , ");
            }                      
        }
        System.out.println(" [ ");
    }

        public void setvector (String name){

             Scanner sc = new Scanner(System.in);
        
        for (int i = 0; i < n ; i++) 
        {
            System.out.print("v[" + i + "]: ");
            data[i] = sc.nextInt();
        }
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        System.out.println("Tamaño n : " );
        int n = sc.nextInt();

        Arreglos v = new Arreglos (n);
        v.setvector("myVector");
        v.getvector("myVector");

        sc.close();
    }
}