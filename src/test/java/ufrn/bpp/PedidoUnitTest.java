package ufrn.bpp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PedidoUnitTest {

    Pedido pedido = new Pedido();

    @Mock
    static Produto produto_1 = mock(Produto.class);
    @Mock
    static Produto produto_2 = mock(Produto.class);
    @Mock
    static Produto produto_3 = mock(Produto.class);
    @Mock
    static Produto produto_4 = mock(Produto.class);

    // BeforeEach é usado para evitar efeitos colaterais entre testes
    @BeforeEach
    void setup() {
        when(produto_1.getId()).thenReturn(1);
        when(produto_2.getId()).thenReturn(2);
        when(produto_3.getId()).thenReturn(3);
        when(produto_4.getId()).thenReturn(4);

        when(produto_1.getPreco()).thenReturn(10.0);
        when(produto_2.getPreco()).thenReturn(20.0);
        when(produto_3.getPreco()).thenReturn(30.0);
        when(produto_4.getPreco()).thenReturn(100.0);

        pedido.adicionarProduto(produto_1);
        pedido.adicionarProduto(produto_2);
        pedido.adicionarProduto(produto_3);
    }

    @Test
    void calculoDoValorSemDesconto() {
        assertEquals(pedido.getValorTotal(), 60.0);
    }
    @Test
    void calculoDoValorComDesconto() {
        pedido.adicionarProduto(produto_4);
        assertEquals(pedido.getValorTotal(), 144.0);
    }
}