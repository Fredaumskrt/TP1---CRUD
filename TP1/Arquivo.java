import java.io.*;
import java.lang.reflect.Constructor;
import aed3.HashExtensivel;
import aed3.ParIDPosicao;


public class Arquivo<T extends registro> {
    private RandomAccessFile arquivo;
    private Constructor<T> construtor;
    private String nomeArquivo;
    private int ultimoID;
    private HashExtensivel<ParIDPosicao> indice;  // Usando ParIDPosicao para indexar posicao no arquivo

    public Arquivo(Constructor<T> construtor, String nomeArquivo) throws Exception {
        this.construtor = construtor;
        this.nomeArquivo = nomeArquivo;
        this.arquivo = new RandomAccessFile(nomeArquivo, "rw");
        // Ajusta o constructor e os caminhos de acordo com a implementacao de ParIDPosicao
        this.indice = new HashExtensivel<>(ParIDPosicao.class.getConstructor(long.class, long.class), 10, "indice.db", "cestos.db");
        if (arquivo.length() == 0) {
            arquivo.writeInt(0); // Armazena o ultimo ID 
            ultimoID = 0;
        } else {
            arquivo.seek(0);
            ultimoID = arquivo.readInt();
        }
    }

    public int create(T objeto) throws Exception {
        objeto.setID(++ultimoID);
        byte[] data = objeto.toByteArray();
        long posicao = arquivo.length();
        arquivo.seek(posicao);
        arquivo.writeByte(0); 
        arquivo.writeShort(data.length);
        arquivo.write(data);
        arquivo.seek(0);
        arquivo.writeInt(ultimoID);
        // Cria nova entrada de indice para o novo registro
        indice.create(new ParIDPosicao(ultimoID, posicao));
        return objeto.getID();
    }

    public T read(int id) throws Exception {
        ParIDPosicao found = indice.read(id);
        if (found == null || found.getPosicao() == -1) {
            return null; // Registro nao encontrado 
        }
        long posicao = found.getPosicao();
        arquivo.seek(posicao);
        if (arquivo.readByte() == 1) { // Verifica se o registro esta marcado como deletado
            return null;
        }
        int tamanho = arquivo.readShort();
        byte[] data = new byte[tamanho];
        arquivo.readFully(data);
        T objeto = construtor.newInstance();
        objeto.fromByteArray(data);
        return objeto;
    }

    public boolean update(T objeto) throws Exception {
        ParIDPosicao found = indice.read(objeto.getID());
        if (found == null || found.getPosicao() == -1) {
            return false; // Registro nao encontrado no indice
        }
        long posicao = found.getPosicao();
        arquivo.seek(posicao);
        arquivo.readByte(); 
        int tamanho = arquivo.readShort();
        byte[] dataAntigo = new byte[tamanho];
        arquivo.readFully(dataAntigo);
        byte[] dataNovo = objeto.toByteArray();
        if (dataNovo.length > tamanho) {
            
            delete(objeto.getID());
            create(objeto);
            return true;
        } else {
            // Atualiza o registro no lugar
            arquivo.seek(posicao + 3); // Volta para o inicio do registro 
            arquivo.write(dataNovo);
            return true;
        }
    }

    public boolean delete(int id) throws Exception {
        ParIDPosicao found = indice.read(id);
        if (found == null || found.getPosicao() == -1) {
            return false; // Registro nao encontrado no Indice
        }
        long posicao = found.getPosicao();
        arquivo.seek(posicao);
        arquivo.writeByte(1); // Marca o registro como deletado
        return true;
    }
}
