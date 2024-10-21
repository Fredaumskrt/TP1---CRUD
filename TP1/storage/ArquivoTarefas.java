import java.io.*;
import java.lang.reflect.Constructor;
import aed3.HashExtensivel;
import aed3.ParIDPosicao;
import aed3.ArvoreBMais;
import aed3.ParIdCategoriaIdTarefa;

public class ArquivoTarefas<T extends registro> extends Arquivo<T> {
    private ArvoreBMais<ParIdCategoriaIdTarefa> indiceCategoria;  // Índice para relacionamento 1:N (categoria -> tarefas)

    public ArquivoTarefas(Constructor<T> construtor, String nomeArquivo) throws Exception {
        super(construtor, nomeArquivo);
        // Inicializa a Árvore B+ para controlar a associação entre categorias e tarefas
        this.indiceCategoria = new ArvoreBMais<>(ParIdCategoriaIdTarefa.class.getConstructor(int.class, int.class), "indiceCategoria.db");
    }

    // Sobrescrever o método de criação para inserir no índice de categorias
    @Override
    public int create(T tarefa) throws Exception {
        int id = super.create(tarefa); // Chama o método create da classe pai
        tarefa.setID(id);
        // Adiciona no índice de categorias (idCategoria -> idTarefa)
        if (tarefa instanceof Tarefa) {
            int idCategoria = ((Tarefa) tarefa).getIdCategoria();
            indiceCategoria.create(new ParIdCategoriaIdTarefa(idCategoria, id));
        }
        return id;
    }

    // Sobrescrever o método de exclusão para remover do índice de categorias
    @Override
    public boolean delete(int id) throws Exception {
        T tarefa = super.read(id);
        if (tarefa == null) return false; // Se a tarefa não existir, retorna false

        // Remove do índice de categorias (idCategoria -> idTarefa)
        if (tarefa instanceof Tarefa) {
            int idCategoria = ((Tarefa) tarefa).getIdCategoria();
            indiceCategoria.delete(new ParIdCategoriaIdTarefa(idCategoria, id));
        }
        return super.delete(id); // Chama o método delete da classe pai
    }

    // Método para buscar todas as tarefas de uma categoria específica
    public void buscarTarefasPorCategoria(int idCategoria) throws Exception {
        // Recupera todas as tarefas associadas à categoria
        for (ParIdCategoriaIdTarefa par : indiceCategoria.lista(idCategoria)) {
            T tarefa = super.read(par.getIdTarefa());
            if (tarefa != null) {
                // Exibe ou processa a tarefa (dependendo da lógica de controle/visão)
                System.out.println(tarefa);
            }
        }
    }
}
