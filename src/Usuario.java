import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nome;
    private Departamento departamento;
    private boolean isAdmin;

    ArrayList<Usuario> centralUsuarios;

    public Usuario(int id, String nome, Boolean isAdmin, Departamento departamento) {
        centralUsuarios = new ArrayList<>();
        this.id = id;
        this.nome = nome;
        this.isAdmin = isAdmin;
        this.departamento = departamento;
    }

    public void addUsuario(Usuario usuario) {
        centralUsuarios.add(usuario);
    }

    public Usuario getUsuario(String nome) {
        for (Usuario usuario : centralUsuarios) {
            if (usuario.getNome().equals(nome)) {
                return usuario;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public Departamento getDepartamento() {
        return departamento;
    }
}