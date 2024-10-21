package model;
import java.io.*;
import java.util.Date;


public class Tarefa implements registro {
    private int id;
    private String nome;
    private Date dataCriacao;
    private Date dataConclusao;
    private String status;
    private int prioridade;
    private int idCategoria; // Chave estrangeira para a categoria

    // Construtor
    public Tarefa(int id, String nome, Date dataCriacao, Date dataConclusao, String status, int prioridade, int idCategoria) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.status = status;
        this.prioridade = prioridade;
        this.idCategoria = idCategoria; // Inicializa a chave estrangeira
    }

    // Getters e Setters
    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    // Serialização para byte array
    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(nome);
        dos.writeLong(dataCriacao.getTime());
        dos.writeLong(dataConclusao.getTime());
        dos.writeUTF(status);
        dos.writeInt(prioridade);
        dos.writeInt(idCategoria); // Serializa o idCategoria
        dos.flush();
        return baos.toByteArray();
    }

    // Deserialização de byte array
    @Override
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        id = dis.readInt();
        nome = dis.readUTF();
        dataCriacao = new Date(dis.readLong());
        dataConclusao = new Date(dis.readLong());
        status = dis.readUTF();
        prioridade = dis.readInt();
        idCategoria = dis.readInt(); // Deserializa o idCategoria
    }
}
