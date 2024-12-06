package ufrn.bpp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pedido {
    private String cliente;
    private HashMap<Integer, Produto> produtos;
    private Double valorTotal = 0d;

    public Pedido() {
        this.produtos = new HashMap<>();
    }

    public Pedido(String nome) {
        setNomeDoCliente(nome);
    }

    public void setNomeDoCliente(String nome) {
        this.cliente = nome;
    }

    public void adicionarProduto(Produto produto) {
        if (produtos.containsKey(produto.getId())) {
            throw new IllegalArgumentException("O ID inserido já existe no pedido");
        }
        produtos.put(produto.getId(), produto);
        calcularTotal(produto);
    }

    private void calcularTotal(Produto produto) {
        this.valorTotal += produto.getPreco();
        this.valorTotal = this.valorTotal > 100 ? this.valorTotal * 0.9 : this.valorTotal;
    }

    @Override
    public String toString() {
        return String.format("Cliente - %s\nProduto(s):\n %s\nValor Total: %.2f",
                cliente, produtos.toString(), valorTotal);
    }
}
