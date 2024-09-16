package aed3;  

import java.io.*;

public class ParIDPosicao implements RegistroHashExtensivel<ParIDPosicao> {
    private long id;
    private long posicao;

    public ParIDPosicao(long id, long posicao) {
        this.id = id;
        this.posicao = posicao;
    }

    @Override
    public int hashCode() {
        return (int)(id ^ (id >>> 32));  // Hash simples baseado no ID
    }

    @Override
    public short size() {
        return 16; // 8 bytes para ID + 8 bytes para posicao
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeLong(id);
        dos.writeLong(posicao);
        dos.flush();
        return baos.toByteArray();
    }

    @Override
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readLong();
        this.posicao = dis.readLong();
    }

    // Getters and Setters
    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public long getPosicao() {
        return posicao;
    }

    public void setPosicao(long posicao) {
        this.posicao = posicao;
    }
}
