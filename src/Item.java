public class Item {
    private String descricao;
    private double valorUnitario;
    private int quantidade;

    public Item(String descricao, double valorUnitario, int quantidade) {
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
    }

    public double calcularTotal() {
        return valorUnitario * quantidade;
    }

    @Override
    public String toString() {
        return "Descrição: " + descricao + ", Valor Unitário: " + valorUnitario + ", Quantidade: " + quantidade + ", Total: "
                + calcularTotal();
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }



}