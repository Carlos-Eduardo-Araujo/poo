class ItemBiblioteca {
    private String titulo;
    private int anoPublicacao;
    private boolean disponivel;

    public ItemBiblioteca(String titulo, int anoPublicacao) {
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true;
    }

    public String detalhes() {
        return "Título: " + titulo + ", Ano: " + anoPublicacao + ", Disponível: " + (disponivel ? "Sim" : "Não");
    }

    public boolean emprestar() {
        if (disponivel) {
            disponivel = false;
            return true;
        }
        return false;
    }

    public void devolver() {
        disponivel = true;
    }
}

class Livro extends ItemBiblioteca {
    private String autor;
    private int numeroPaginas;
    private String isbn;

    public Livro(String titulo, int anoPublicacao, String autor, int numeroPaginas, String isbn) {
        super(titulo, anoPublicacao);
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
    }

    @Override
    public String detalhes() {
        return "[Livro] Título: " + titulo + ", Autor: " + autor + ", Ano: " + anoPublicacao + ", Páginas: " + numeroPaginas + ", ISBN: " + isbn + ", Disponível: " + (disponivel ? "Sim" : "Não");
    }
}

class Revista extends ItemBiblioteca {
    private int numero;
    private String periodicidade;
    private String editora;

    public Revista(String titulo, int anoPublicacao, int numero, String periodicidade, String editora) {
        super(titulo, anoPublicacao);
        this.numero = numero;
        this.periodicidade = periodicidade;
        this.editora = editora;
    }

    @Override
    public String detalhes() {
        return "[Revista] Título: " + titulo + ", Número: " + numero + ", Ano: " + anoPublicacao + ", Periodicidade: " + periodicidade + ", Editora: " + editora + ", Disponível: " + (disponivel ? "Sim" : "Não");
    }
}

class Usuario {
    private String nome;
    private int idUsuario;
    private List<ItemBiblioteca> itensEmprestados;

    public Usuario(String nome, int idUsuario) {
        this.nome = nome;
        this.idUsuario = idUsuario;
        this.itensEmprestados = new ArrayList<>();
    }

    public void emprestarItem(ItemBiblioteca item) {
        if (item.emprestar()) {
            itensEmprestados.add(item);
            System.out.println(nome + " emprestou: " + item.detalhes());
        } else {
            System.out.println(item.detalhes() + " não está disponível para empréstimo.");
        }
    }

    public void devolverItem(ItemBiblioteca item) {
        if (itensEmprestados.contains(item)) {
            item.devolver();
            itensEmprestados.remove(item);
            System.out.println(nome + " devolveu: " + item.detalhes());
        } else {
            System.out.println(nome + " não tem " + item.detalhes() + " emprestado.");
        }
    }

    public void listarEmprestimos() {
        if (itensEmprestados.isEmpty()) {
            System.out.println(nome + " não tem itens emprestados.");
        } else {
            System.out.println("Itens emprestados por " + nome + ":");
            for (ItemBiblioteca item : itensEmprestados) {
                System.out.println(item.detalhes());
            }
        }
    }
}

public class Biblioteca {
    public static void main(String[] args) {
        Livro livro1 = new Livro("Java para Iniciantes", 2020, "João Silva", 300, "123456789");
        Revista revista1 = new Revista("Ciência Hoje", 2023, 45, "Mensal", "Editora ABC");
        
        Usuario usuario1 = new Usuario("Carlos", 101);
        usuario1.emprestarItem(livro1);
        usuario1.emprestarItem(revista1);
        usuario1.listarEmprestimos();
        
        usuario1.devolverItem(livro1);
        usuario1.listarEmprestimos();
    }
}
