package ufrn.bpp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Catalogo {
    private HashMap<Integer, Produto> catalogo;

    public Produto buscarProdutoPorId(Integer id) {
        return catalogo.get(id);
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<Produto>(catalogo.values()) ;
    }

    public void adicionarProduto(Produto produto) {
        if (catalogo.containsKey(produto.getId())) {
            throw new IllegalArgumentException("O ID inserido já existe no catálogo");
        }
        catalogo.put(produto.getId(), produto);
    }
}
