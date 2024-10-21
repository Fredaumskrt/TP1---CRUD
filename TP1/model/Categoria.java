public class Categoria implements Registro {
    private int id;
    private String nome;

    // Construtores, getters, setters, etc.

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        // Transformar a categoria em array de bytes
    }

    @Override
    public void fromByteArray(byte[] ba) throws IOException {
        // Transformar array de bytes em categoria
    }
}
