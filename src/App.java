import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class App {

    Sistema sistema = new Sistema();;
    Usuario usuarioAtual;
    Scanner scanner = new Scanner(System.in);

    public void executar() {
        inicializar();
        selecionarUsuarioAtual();
        menu();
    }

    private void inicializar() {
        Departamento Financeiro = new Departamento("Financeiro", 1000.0);
        Departamento RH = new Departamento("RH", 1500.0);
        Departamento Engenharia = new Departamento("Engenharia", 2000.0);
        Departamento Manutencao = new Departamento("Manutenção", 1200.0);
        Departamento TI = new Departamento("TI", 1800.0);
        sistema.adicionarDepartamento(TI);
        sistema.adicionarDepartamento(RH);
        sistema.adicionarDepartamento(Financeiro);
        sistema.adicionarDepartamento(Engenharia);
        sistema.adicionarDepartamento(Manutencao);
        Usuario Alice = new Usuario(1, "Alice", false, Financeiro);
        Usuario Bob = new Usuario(2, "Bob", false, TI);
        Usuario Carol = new Usuario(3, "Carol", true, Manutencao);
        sistema.adicionarUsuario(Alice);
        sistema.adicionarUsuario(Bob);
        sistema.adicionarUsuario(Carol);
    }

    private void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n----- Menu -----");
            System.out.println("1 -- Registrar um novo pedido de aquisição");
            System.out.println("2 -- Avaliar um pedido que esteja aberto para aprová-lo ou rejeitá-lo");
            System.out.println("3 -- Listar todos os pedidos entre duas datas");
            System.out.println("4 -- Buscar pedidos por funcionário solicitante");
            System.out.println("5 -- Buscar pedidos pela descrição de um item");
            System.out.println("6 -- Visualizar os detalhes de um pedido para aprová-lo ou rejeitá-lo");
            System.out.println("7 -- Estatísticas Gerais");
            System.out.println("8 -- Trocar o usuário atual");
            System.out.println("9 -- Criar um usuário");
            System.out.println("10 -- Criar um departamento");
            System.out.println("0 -- Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registrarNovoPedido();
                    break;
                case 2:
                    avaliarPedido();
                    break;
                case 3:
                    listarPedidosEntreDatas();
                    break;
                case 4:
                    buscarPedidosPorFuncionario();
                    break;
                case 5:
                    buscarPedidosPelaDescricaoItem();
                    break;
                case 6:
                    visualizarDetalhesPedido();
                    break;
                case 7:
                    exibirEstatisticasGerais();
                    break;
                case 8:
                    selecionarUsuarioAtual();
                    break;
                case 9:
                    criarNovoUsuario();
                    break;
                case 10:
                    criarNovoDepartamento();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (opcao != 0);
        scanner.close();
    }

    public void criarNovoDepartamento() {
        System.out.println("Criar Novo Departamento:");
        System.out.print("Nome do Departamento: ");
        String nome = scanner.nextLine();
        if (sistema.encontrarDepartamentoPorNome(nome) != null) {
            System.out.println("Departamento já existe.");
            return;
        }
        System.out.print("Valor Máximo Permitido por Pedido: ");
        double valorMaximo = scanner.nextDouble();
        Departamento novoDepartamento = new Departamento(nome, valorMaximo);
        sistema.adicionarDepartamento(novoDepartamento);
        System.out.println("Departamento criado com sucesso!");
    }

    public void criarNovoUsuario() {
        System.out.println("Criar Novo Usuário:");
        System.out.print("Identificador: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Tipo (funcionário ou administrador): ");
        String tipoString = scanner.nextLine();
        boolean isAdmin = tipoString.equalsIgnoreCase("administrador");
        System.out.print("Departamento: ");
        String nomeDepartamento = scanner.nextLine();
        Departamento departamento = sistema.encontrarDepartamentoPorNome(nomeDepartamento);
        if (departamento == null) {
            System.out.println("Departamento não encontrado.");
            return;
        }
        Usuario novoUsuario = new Usuario(id, nome, isAdmin, departamento);
        sistema.adicionarUsuario(novoUsuario);
        System.out.println("Usuário criado com sucesso!");
    }

    private void selecionarUsuarioAtual() {
        System.out.println("Usuários Disponíveis:");
        List<Usuario> usuarios = sistema.getUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println("Id: " + usuario.getId() + ", " + usuario.getNome());
        }
        System.out.print("Escolha o usuário digitando o Id: ");
        int userId = scanner.nextInt();
        Usuario novoUsuario = sistema.encontrarUsuarioPorId(userId);
        if (novoUsuario == null) {
            System.out.println("Usuário não encontrado.");
            System.exit(userId);
        } else {
            usuarioAtual = novoUsuario;
            System.out.println("Usuário atual alterado para: " + usuarioAtual.getNome());
        }
    }

    public Date converterStringParaData(String dataString) {
        Date data = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            data = formato.parse(dataString);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data.");
        }
        return data;
    }

    private void registrarNovoPedido() {
        Departamento departamento = usuarioAtual.getDepartamento();
        double total = 0;
        System.out.print("Data do pedido (formato dd/MM/yyyy): ");
        String dataString = scanner.next();
        Date dataPedido = converterStringParaData(dataString);
        if (dataPedido == null) {
            System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            return;
        }
        System.out.print("Data de conclusão do pedido (formato dd/MM/yyyy): ");
        String dataConclusaoString = scanner.next();
        Date dataConclusao = converterStringParaData(dataConclusaoString);
        if (dataConclusao == null) {
            System.out.println("Data de conclusão inválida. Use o formato dd/MM/yyyy.");
            return;
        }
        List<Item> itens = new ArrayList<>();
        while (true) {
            System.out.println("\nAdicionar Item ao Pedido:");
            System.out.print("Descrição do item (ou 'fim' para terminar): ");
            String descricao = scanner.next();
            if (descricao.equalsIgnoreCase("fim")) {
                break;
            }
            double valorUnitario;
            while (true) {
                System.out.print("Valor unitário: ");
                try {
                    valorUnitario = scanner.nextDouble();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Valor unitário inválido. Insira um número válido.");
                    scanner.next();
                }
            }
            System.out.print("Quantidade: ");
            int quantidade;
            while (true) {
                try {
                    quantidade = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Quantidade inválida. Insira um número inteiro válido.");
                    scanner.next();
                }
            }
            itens.add(new Item(descricao, valorUnitario, quantidade));
        }
        for (Item item : itens) {
            total += item.getValorUnitario() * item.getQuantidade();
        }
        System.out.print("Status do pedido (aberto, aprovado, reprovado): ");
        String statusPedido = scanner.next();
        if (!statusPedido.equalsIgnoreCase("aberto") && !statusPedido.equalsIgnoreCase("aprovado")
                && !statusPedido.equalsIgnoreCase("reprovado")) {
            System.out.println("Status do pedido inválido. Use 'aberto', 'aprovado' ou 'reprovado'.");
            return;
        }
        Pedido pedido = new Pedido(usuarioAtual, departamento, dataPedido, dataConclusao, statusPedido, itens, total);
        sistema.adicionarPedido(pedido);
    }

    private void avaliarPedido() {
        Pedido pedidoParaAvaliar = sistema.encontrarPedidosPorSolicitante(usuarioAtual);
        if (pedidoParaAvaliar == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }
        System.out.println("Detalhes do Pedido:");
        System.out.println(pedidoParaAvaliar);
        System.out.print("Deseja aprovar (a) ou rejeitar (r) este pedido? ");
        String decisao = scanner.next();
        if (decisao.equalsIgnoreCase("a")) {
            pedidoParaAvaliar.setStatus("Aprovado");
            System.out.println("Pedido aprovado com sucesso!");
        } else if (decisao.equalsIgnoreCase("r")) {
            pedidoParaAvaliar.setStatus("Rejeitado");
            System.out.println("Pedido rejeitado com sucesso!");
        } else {
            System.out.println("Opção inválida. Operação cancelada.");
        }
    }

    private void listarPedidosEntreDatas() {
        if (usuarioAtual.getIsAdmin() == false) {
            System.out.println("Você não é administrador!");
        } else {
            System.out.print("Digite a data inicial (formato dd/MM/yyyy): ");
            String dataString = scanner.next();
            Date dataInicial = converterStringParaData(dataString);
            if (dataInicial == null) {
                System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
                return;
            }
            System.out.print("Digite a data final (formato dd/MM/yyyy): ");
            String dataConclusaoString = scanner.next();
            Date dataFinal = converterStringParaData(dataConclusaoString);
            if (dataFinal == null) {
                System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
                return;
            }
            sistema.listarPedidosEntreDatas(dataInicial, dataFinal);
        }
    }

    private void buscarPedidosPorFuncionario() {
        if (usuarioAtual.getIsAdmin() == false) {
            System.out.println("Você não é administrador!");
        } else {
            sistema.buscarPedidosPorFuncionario(usuarioAtual);
        }
    }

    private void buscarPedidosPelaDescricaoItem() {
        if (usuarioAtual.getIsAdmin() == false) {
            System.out.println("Você não é administrador!");
        } else {
            System.out.println("Digite uma descrição:");
            String descricao = scanner.next();
            sistema.buscarPedidosPelaDescricaoItem(descricao);
        }
    }

    private void visualizarDetalhesPedido() {
        if (usuarioAtual.getIsAdmin() == false) {
            System.out.println("Você não é administrador!");
        } else {
            sistema.visualizarDetalhesPedido(usuarioAtual);
        }
    }

    private void exibirEstatisticasGerais() {
        if (usuarioAtual.getIsAdmin() == false) {
            System.out.println("Você não é administrador!");
        } else {
            sistema.exibirEstatisticasGerais();
        }
    }
}
