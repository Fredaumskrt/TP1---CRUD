public class ArquivoCategorias extends Arquivo<Categoria> {
    private ArvoreBMais<ParNomeId> indiceNome; // Índice indireto de nomes

    public ArquivoCategorias() throws Exception {
        super(Categoria.class.getConstructor(), "categorias.db");
        indiceNome = new ArvoreBMais<>(ParNomeId.class.getConstructor(), "indiceNome.db");
    }

    @Override
    public int create(Categoria categoria) throws Exception {
        int id = super.create(categoria);
        categoria.setID(id);
        // Criar um par (nomeCategoria, idCategoria) para a árvore B+
        indiceNome.create(new ParNomeId(categoria.getNome(), id));
        return id;
    }

    @Override
    public boolean delete(int id) throws Exception {
        Categoria categoria = super.read(id);
        if (categoria == null) return false;
        indiceNome.delete(categoria.getNome());  // Remover do índice de nome
        return super.delete(id);
    }

    // Métodos de busca por nome e outras operações...
}
