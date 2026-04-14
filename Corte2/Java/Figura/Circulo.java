class Circulo extends Figura {
    private double radio ;
    public Circulo ( double radio ) {
        super (" Circulo ") ;
        this . radio = radio ;
    }
    @Override
    public double calcularArea () {
        return Math . PI * Math . pow ( radio , 2) ;
    }
}