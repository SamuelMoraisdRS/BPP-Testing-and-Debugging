package ufrn.bpp;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pedido {

    private static final Logger LOGGER = Logger.getLogger(Pedido.class.getName());

    private Integer id;
    private String cliente;
    private HashMap<Integer, Produto> produtos = new HashMap<>();
    private Double valorTotal = 0d;
    private Function<Double, Double> aplicarDesconto = (valor) -> valor > 100 ?
                                                                  valor * 0.9 : valor;

    public Pedido() {
    }

    public Pedido(Integer id, String nome, List<Produto> produtos) {
        this.id = id;
        setNomeDoCliente(nome);
        String log = String.format("Pedido criado: %s\nNome do Cliente: nome\nProdutos:", nome);
        adicionarListaDeProdutos(produtos);
        for (Produto produto : produtos) {
            calcularTotal(produto);
        }
        LOGGER.info(String.format("Pedido Criado: %s", this));
    }

    public Pedido(String nome, List<Produto> produtos, Function<Double, Double> aplicarDesconto) {
        setNomeDoCliente(nome);
        adicionarListaDeProdutos(produtos);
        setAplicarDesconto(aplicarDesconto);
        for (Produto produto : produtos) {
            calcularTotal(produto);
        }
        LOGGER.info(String.format("Pedido Criado: %s", this));
    }

    public Integer getId() {
        return this.id;
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
        } catch (Exception e) {
            LOGGER.warning("Exceção lançada" + e);
        }
    }

    public void adicionarProduto(Produto produto) {
        if (produtos.containsKey(produto.getId())) {
            LOGGER.warning("Produto já presente no catálogo");
            throw new IllegalArgumentException("O ID inserido já existe no pedido");
        }
        produtos.put(produto.getId(), produto);
        LOGGER.info("Adicionando produto:\n" + produto);
        calcularTotal(produto);
    }

    private void calcularTotal(Produto produto) {
        String mensagemDeLog = String.format("Aplicando Desconto:\nValor normal do pedido: %.2f", this.getValorTotal() + produto.getPreco());
        this.valorTotal = aplicarDesconto.apply(valorTotal + produto.getPreco());
        mensagemDeLog += "\nValor com Desconto: " + this.getValorTotal();
        LOGGER.info(mensagemDeLog);
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
        return String.format("ID - %d\nCliente - %s\nProduto(s):\n %s\nValor Total: %.2f",
                id, cliente, produtos.toString(), valorTotal);
    }
}
