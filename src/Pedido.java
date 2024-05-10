import java.util.Date;
import java.util.List;

public class Pedido {
    private Usuario solicitante;
    private Departamento departamento;
    private Date dataPedido;
    private Date dataConclusao;
    private String status;
    private List<Item> itens;
    private double valorTotal;

    public Pedido(Usuario solicitante, Departamento departamento, Date dataPedido, Date dataConclusao,
            String status, List<Item> itens, double valorTotal) {
        this.solicitante = solicitante;
        this.departamento = departamento;
        this.dataPedido = dataPedido;
        this.dataConclusao = dataConclusao;
        this.status = status;
        this.itens = itens;
        this.valorTotal = valorTotal;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido [solicitante=" + solicitante.getNome() + ", departamento=" + departamento.getNome() + ", dataPedido=" + dataPedido
                + ", dataConclusao=" + dataConclusao + ", status=" + status + ", itens=" + itens + ", valorTotal="
                + valorTotal + "]";
    }

    
}
