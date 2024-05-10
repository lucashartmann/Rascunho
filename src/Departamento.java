public class Departamento {

    private String nome;
    private double valorMaximo;

    public Departamento(String nome, double valorMaximo) {
        this.nome = nome;
        this.valorMaximo = valorMaximo;
    }

    public String getNome() {
        return nome;
    }

    public double getValorMaximo() {
        return valorMaximo;
    }
}