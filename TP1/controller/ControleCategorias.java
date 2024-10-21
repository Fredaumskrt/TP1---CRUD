public class ControleCategorias {
    private ArquivoCategorias arquivoCategorias;
    private VisaoCategorias visaoCategorias;

    public ControleCategorias() throws Exception {
        arquivoCategorias = new ArquivoCategorias();
        visaoCategorias = new VisaoCategorias();
    }

    public void incluirCategoria() throws Exception {
        Categoria categoria = visaoCategorias.leCategoria();
        arquivoCategorias.create(categoria);
    }

    public void excluirCategoria() throws Exception {
        int id = visaoCategorias.leIdCategoria();
        if (!arquivoCategorias.delete(id)) {
            visaoCategorias.mostraErro("Categoria não encontrada.");
        }
    }

    // Outros métodos de controle...
}
