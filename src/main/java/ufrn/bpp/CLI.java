package ufrn.bpp;


import java.util.*;
import java.util.function.Function;

import static java.lang.Integer.parseInt;

public class CLI {

    enum Estado {
        MENU,
        LISTAR_PRODUTOS,
        BUSCAR_PRODUTOS,
        ADICIONAR_PRODUTOS,
        REALIZAR_PEDIDO,
        LISTAR_PEDIDOS,
        ENCERRAR
    }

    private final String STRING_MENU = "CATALOGO:\n1 - Ver todos os produtos  2 - Buscar Produto  3 - Adicionar Produto   4 - Realizar Pedido  5 - Listar Pedidos  6 - Encerrar";

    public Estado estadoAtual = Estado.MENU;

    public final LinkedList<Pedido> pedidos = new LinkedList<>();

    public CLI() {
    }

    public boolean aplicacaoEstaExecutando = true;

    public static void processarPedido(List<Integer> idsDeProdutos, String nomeDoCliente, Catalogo catalogo,
                                       Pedido pedido) {
        pedido.setNomeDoCliente(nomeDoCliente);
        for (Integer id : idsDeProdutos) {
            pedido.adicionarProduto(catalogo.buscarProdutoPorId(id));
        }
    }

    public void adicionarPedidoALista(Pedido pedido) {
        pedidos.push(pedido);
    }

    public void listarPedidos() {
        System.out.println("Pedidos");
        for (Pedido pedido : pedidos) {
            System.out.printf("==\n%s\n==\n", pedido.toString());
        }
    }

    public static void main(String[] args) {
        CLI cli = new CLI();
        Catalogo catalogo = new Catalogo();

        // TODO: Vamos substituir por mocking
        Produto p1 = new Produto(1, "Caneta Azul", 2.50);
        Produto p2 = new Produto(2, "Caneta Preta", 3.50);
        Produto p3 = new Produto(3, "Caneta Vermelha", 2.00);

        catalogo.adicionarProduto(p1);
        catalogo.adicionarProduto(p2);
        catalogo.adicionarProduto(p3);

        try (Scanner scanner = new Scanner(System.in)) {
            while (cli.aplicacaoEstaExecutando) {
                switch (cli.estadoAtual) {
                    case MENU -> {
                        System.out.println(cli.STRING_MENU);
                        cli.estadoAtual = Estado.values()[parseInt(scanner.nextLine())];
                    }
                    case ADICIONAR_PRODUTOS -> {
                        System.out.println("Insira o ID do produto.");
                        Integer id = parseInt(scanner.nextLine());
                        System.out.println("Insira o nome do produto.");
                        String nome = scanner.nextLine();
                        System.out.println("Insira o preço do produto.");
                        Double preco = Double.parseDouble(scanner.nextLine());
                        catalogo.adicionarProduto(new Produto(id, nome, preco));
                        cli.estadoAtual = Estado.LISTAR_PRODUTOS;
                    }
                    case LISTAR_PRODUTOS -> {
                        System.out.println("Catálogo:");
                        for (var produto : catalogo.listarProdutos()) {
                            System.out.println("**");
                            System.out.println(produto.toString());
                            System.out.println("**");
                        }
                        cli.estadoAtual = Estado.MENU;
                    }
                    case REALIZAR_PEDIDO -> {
                        System.out.println("Insira o nome do cliente:");
                        String nome = scanner.nextLine();
                        System.out.println("Insira os IDs dos itens que deseja pedir:");
                        String stringDeIds = scanner.nextLine();
                        // Transforma a String recebida na stdin em lista de ids inteiros, para busca no catálogo
                        List<Produto> listaDeProdutos = Arrays.stream(stringDeIds.split("[ ,]+")).
                                map(Integer::parseInt).map(catalogo::buscarProdutoPorId).
                                toList();
                        Pedido novoPedido = new Pedido(nome, listaDeProdutos);
                        cli.adicionarPedidoALista(novoPedido);
                        cli.estadoAtual = Estado.LISTAR_PEDIDOS;
                    }
                    case LISTAR_PEDIDOS -> {
                        cli.listarPedidos();
                        cli.estadoAtual = Estado.MENU;
                    }
                    case ENCERRAR -> {
                        System.out.println("**ENCERRANDO APLICAÇÃO**");
                        System.exit(0);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}