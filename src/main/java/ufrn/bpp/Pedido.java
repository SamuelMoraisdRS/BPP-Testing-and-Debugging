package ufrn.bpp;


import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class Pedido {
    private String cliente;
    private HashMap<Integer, Produto> produtos = new HashMap<>();
    private Double valorTotal = 0d;
    private Function<Double, Double> aplicarDesconto = (valor) -> valor > 100 ? valor * 0.9 : valor;

    public Pedido() {}

    public Pedido(String nome, List<Produto> produtos) {
        setNomeDoCliente(nome);
        adicionarListaDeProdutos(produtos);
        for (var produto : produtos) {
            calcularTotal(produto);
        };
    }

    public Pedido(String nome, List<Produto> produtos, Function<Double, Double> aplicarDesconto) {
        setNomeDoCliente(nome);
        adicionarListaDeProdutos(produtos);
        setAplicarDesconto(aplicarDesconto);
        for (var produto : produtos) {
            calcularTotal(produto);
        };
    }

    public void setAplicarDesconto(Function<Double, Double> aplicarDesconto) {
        this.aplicarDesconto = aplicarDesconto;
    }

    private void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public void setNomeDoCliente(String nome) {
        this.cliente = nome;
    }

    public void adicionarListaDeProdutos(HashMap<Integer, Produto> produtos) {
        this.produtos = produtos;
    }

    public void adicionarListaDeProdutos(List<Produto> produtos) {
        try {
            for (Produto produto : produtos) {
                this.produtos.put(produto.getId(), produto);
            }
        } catch (NullPointerException e) {
            // TODO: Precisa ser inserido num logger.
            System.err.println("Lista Nula inserida.");
        }
    }

    public void adicionarProduto(Produto produto) {
        if (produtos.containsKey(produto.getId())) {
            throw new IllegalArgumentException("O ID inserido já existe no pedido");
        }

        // TODO talvez seja melhor utilizar uma HashTable
        if (produto == null) {
            return;
        }

        produtos.put(produto.getId(), produto);
        calcularTotal(produto);
    }

    private void calcularTotal(Produto produto) {
        this.valorTotal = aplicarDesconto.apply(valorTotal + produto.getPreco());
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public HashMap<Integer, Produto> getProdutos() {
        return produtos;
    }

    public String getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return String.format("Cliente - %s\nProduto(s):\n %s\nValor Total: %.2f",
                cliente, produtos.toString(), valorTotal);
    }
}
