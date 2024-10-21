package storage.aed3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParEmailID implements RegistroHashExtensivel<ParEmailID> {

    private String email;
    private int id;
    private short TAMANHO = 44;

    public ParEmailID() {
        this("", -1);
    }

    public ParEmailID(String e, int i) {
        try {
            this.email = e;
            this.id = i;
            if (e.getBytes().length + 4 > TAMANHO) {
                throw new Exception("Número de caracteres do email maior que o permitido. Os dados serão cortados.");
            }
        } catch (Exception ec) {
            ec.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        return Math.abs(this.email.hashCode());
    }

    @Override
    public short size() {
        return this.TAMANHO;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(email);
        dos.writeInt(id);
        byte[] bs = baos.toByteArray();
        byte[] bs2 = new byte[TAMANHO];
        for (int i = 0; i < TAMANHO; i++)
            bs2[i] = ' ';
        for (int i = 0; i < bs.length && i < TAMANHO; i++)
            bs2[i] = bs[i];
        return bs2;
    }

    @Override
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.email = dis.readUTF();
        this.id = dis.readInt();
    }

    public static int hash(String email) {
        return Math.abs(email.hashCode());
    }

    @Override
    public String toString() {
        return this.email + ";" + this.id;
    }
}
