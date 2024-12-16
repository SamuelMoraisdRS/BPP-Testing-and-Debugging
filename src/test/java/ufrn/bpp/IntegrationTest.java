package ufrn.bpp;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntegrationTest {

    Catalogo catalogo = new Catalogo();

    @Test
    void fluxoCompletoDePedido() {
        // Teste da adição de Produtos
        Produto produto_1 = new Produto(1, "Caneta", 2.50);
        Produto produto_2 = new Produto(2, "Lápis", 1.50);
        Produto produto_3 = new Produto(3, "Borracha", 2.00);
        catalogo.adicionarProduto(produto_1);
        catalogo.adicionarProduto(produto_2);
        catalogo.adicionarProduto(produto_3);
        assertEquals(catalogo.buscarProdutoPorId(1), produto_1);
        assertEquals(catalogo.buscarProdutoPorId(2), produto_2);
        assertEquals(catalogo.buscarProdutoPorId(3), produto_3);
        // Criação de um pedido com os produtos adicionados anteriromente
        List<Produto> produtos = Arrays.asList(produto_1, produto_2, produto_3);
        Pedido pedido = new Pedido(1, "Samuel", produtos);
        // Checagem do valor sem desconto
        assertEquals(pedido.getValorTotal(), 6.0);
        // Checagem do valor com desconto
        Produto produto_4 = new Produto(4, "Cadeira", 100.00);
        pedido.adicionarProduto(produto_4);
        assertEquals(pedido.getValorTotal(), 95.4);
    }
}
