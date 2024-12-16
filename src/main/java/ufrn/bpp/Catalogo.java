package ufrn.bpp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class Catalogo {
    private static final Logger LOGGER = Logger.getLogger(Catalogo.class.getName());

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
            LOGGER.warning("O produto já existe no catálogo.");
        }
        catalogo.put(produto.getId(), produto);
        LOGGER.info("Produto adicionado\n" + produto);
    }
}
