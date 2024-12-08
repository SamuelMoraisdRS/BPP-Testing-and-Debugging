package ufrn.bpp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Catalogo {
    private final HashMap<Integer, Produto> catalogo = new HashMap<>();

    public Catalogo() {}
    public Produto buscarProdutoPorId(Integer id) {
        return catalogo.get(id);
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(catalogo.values()) ;
    }

    public void adicionarProduto(Produto produto) {
        if (catalogo.containsKey(produto.getId())) {
            throw new IllegalArgumentException("O ID inserido já existe no catálogo");
        }
        catalogo.put(produto.getId(), produto);
    }
}
