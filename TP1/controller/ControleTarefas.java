public class ControleTarefas {
    private ArquivoTarefas arquivoTarefas;
    private VisaoTarefas visaoTarefas;

    public ControleTarefas() throws Exception {
        arquivoTarefas = new ArquivoTarefas();
        visaoTarefas = new VisaoTarefas();
    }

    public void incluirTarefa() throws Exception {
        Tarefa tarefa = visaoTarefas.leTarefa();
        arquivoTarefas.create(tarefa);
    }

    public void excluirTarefa() throws Exception {
        int id = visaoTarefas.leIdTarefa();
        if (!arquivoTarefas.delete(id)) {
            visaoTarefas.mostraErro("Tarefa não encontrada.");
        }
    }

    // Outros métodos de controle...
}
