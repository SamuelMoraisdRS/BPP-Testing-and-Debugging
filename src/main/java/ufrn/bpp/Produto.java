package ufrn.bpp;

public class Produto {
    private final Integer id;
    private String nome;
    private Double preco;

    public Produto(Integer id, String nome, Double preco) {
        this.nome = nome;
        this.id = id;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public Double getPreco() {
        return preco;
    }

}
