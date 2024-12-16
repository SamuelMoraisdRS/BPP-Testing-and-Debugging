package ufrn.bpp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CatalogoUnitTest {
    Catalogo catalogo = new Catalogo();

    @Mock
    static Produto produto_1 = mock(Produto.class);
    @Mock
    static Produto produto_2 = mock(Produto.class);
    @Mock
    static Produto produto_3 = mock(Produto.class);

    // BeforeEach é usado para evitar efeitos colaterais entre testes
    @BeforeEach
    void setup() {
        when(produto_1.getId()).thenReturn(1);
        when(produto_2.getId()).thenReturn(2);
        when(produto_3.getId()).thenReturn(3);

        when(produto_1.toString()).thenReturn("ID - 1\nNome: Produto 1\nPreço: 1.20");
        when(produto_2.toString()).thenReturn("ID - 2\nNome: Produto 2\nPreço: 2.20");
        when(produto_3.toString()).thenReturn("ID - 3\nNome: Produto 3\nPreço: 3.20");

        catalogo.adicionarProduto(produto_1);
        catalogo.adicionarProduto(produto_2);
        catalogo.adicionarProduto(produto_3);
    }

    @Test
    void buscaDeProdutoPorId() {
        assertEquals(catalogo.buscarProdutoPorId(produto_1.getId()), produto_1);
        assertEquals(catalogo.buscarProdutoPorId(produto_2.getId()), produto_2);
        assertEquals(catalogo.buscarProdutoPorId(produto_3.getId()), produto_3);
    }

    @Test
    void listagemDeProdutos() {
        List<Produto> listaObtida = catalogo.listarProdutos();
        List<Produto> listaEsperada = Arrays.asList(produto_1, produto_2, produto_3);
        for (int idx = 0; idx < 3; idx++) {
            assertEquals(listaEsperada.get(idx), listaObtida.get(idx));
        }
    }
}
