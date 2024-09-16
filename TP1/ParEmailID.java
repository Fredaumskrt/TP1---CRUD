import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParEmailID implements aed3.RegistroHashExtensivel<ParEmailID> {

    private String email;
    private int id;
    private static final short TAMANHO = 44;

    public ParEmailID() {
        this("", -1);
    }

    public ParEmailID(String e, int i) {
        this.email = e;
        this.id = i;
        if (e.getBytes().length + 4 > TAMANHO) {
            throw new IllegalArgumentException("Número de caracteres do email maior que o permitido. Os dados serão cortados.");
        }
    }

    @Override
    public int hashCode() {
        return Math.abs(this.email.hashCode());
    }

    public short size() {
        return TAMANHO;
    }

    @Override
    public String toString() {
        return this.email + ";" + this.id;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(email);
        dos.writeInt(id);
        byte[] data = baos.toByteArray();
        
        if (data.length > TAMANHO) {
            throw new IOException("Dados excedem o tamanho máximo permitido.");
        }
        
        byte[] result = new byte[TAMANHO];
        System.arraycopy(data, 0, result, 0, data.length);
        
        return result;
    }

    @Override
    public void fromByteArray(byte[] ba) throws IOException {
        if (ba.length < 2) {
            throw new IOException("Byte array é muito curto para ler os dados.");
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        
        try {
            this.email = dis.readUTF();
            this.id = dis.readInt();
        } catch (IOException e) {
            throw new IOException("Erro ao ler os dados do byte array.", e);
        }
    }

    public static int hash(String email) {
        return Math.abs(email.hashCode());
    }
}
