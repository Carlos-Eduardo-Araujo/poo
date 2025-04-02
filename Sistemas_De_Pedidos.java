import java.util.ArrayList;
import java.util.List;

abstract class ItemMenu {
    protected String nome;
    protected double preco;
    protected String descricao;

    public ItemMenu(String nome, double preco, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    public abstract String exibirDetalhes();

    public void alterarPreco(double novoPreco) {
        if (novoPreco > 0) {
            this.preco = novoPreco;
        }
    }
}

class PratoPrincipal extends ItemMenu {
    private List<String> ingredientes;
    private int tempoPreparo;
    private String categoria;

    public PratoPrincipal(String nome, double preco, String descricao, List<String> ingredientes, int tempoPreparo, String categoria) {
        super(nome, preco, descricao);
        this.ingredientes = ingredientes;
        this.tempoPreparo = tempoPreparo;
        this.categoria = categoria;
    }

    @Override
    public String exibirDetalhes() {
        return "Prato: " + nome + " (" + categoria + ") - R$ " + preco + "\nIngredientes: " + ingredientes + "\nTempo de preparo: " + tempoPreparo + " min\nDescrição: " + descricao;
    }
}

class Bebida extends ItemMenu {
    private String tamanho;
    private boolean alcoolica;
    private String marca;

    public Bebida(String nome, double preco, String descricao, String tamanho, boolean alcoolica, String marca) {
        super(nome, preco, descricao);
        this.tamanho = tamanho;
        this.alcoolica = alcoolica;
        this.marca = marca;
    }

    @Override
    public String exibirDetalhes() {
        return "Bebida: " + nome + " (" + tamanho + ") - R$ " + preco + "\nMarca: " + marca + "\nAlcoólica: " + (alcoolica ? "Sim" : "Não") + "\nDescrição: " + descricao;
    }
}

class Pedido {
    private int numeroPedido;
    private List<ItemMenu> itens;
    private String data;

    public Pedido(int numeroPedido, String data) {
        this.numeroPedido = numeroPedido;
        this.data = data;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemMenu item) {
        itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemMenu item : itens) {
            total += item.preco;
        }
        return total;
    }

    public void exibirPedido() {
        System.out.println("Pedido #" + numeroPedido + " - Data: " + data);
        for (ItemMenu item : itens) {
            System.out.println(item.exibirDetalhes());
        }
        System.out.println("Total: R$ " + calcularTotal());
    }
}

public class Restaurante {
    public static void main(String[] args) {
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Frango");
        ingredientes.add("Queijo");
        ingredientes.add("Molho especial");

        PratoPrincipal prato1 = new PratoPrincipal("Frango Grelhado", 29.99, "Frango suculento grelhado na brasa", ingredientes, 25, "Carnes");
        Bebida bebida1 = new Bebida("Suco Natural", 8.50, "Suco de laranja fresco", "500ml", false, "Natural Fresh");
        
        Pedido pedido1 = new Pedido(1, "02/04/2025");
        pedido1.adicionarItem(prato1);
        pedido1.adicionarItem(bebida1);
        pedido1.exibirPedido();
    }
}
