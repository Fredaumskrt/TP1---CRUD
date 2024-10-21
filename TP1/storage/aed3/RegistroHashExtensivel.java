/*
REGISTRO HASH EXTENSÍVEL

Esta interface apresenta os métodos que os objetos
a serem incluídos na tabela hash extensível devem 
conter.

Implementado pelo Prof. Marcos Kutova
v1.1 - 2021
*/
package storage.aed3;


import java.io.IOException;

public interface RegistroHashExtensivel<T> {

    // Chave numérica para ser usada na HashExtensível
    public int hashCode();

    // Tamanho fixo do registro
    public short size();

    // Converte o objeto em um array de bytes para armazenamento
    public byte[] toByteArray() throws IOException;

    // Constrói o objeto a partir de um array de bytes lido do arquivo
    public void fromByteArray(byte[] ba) throws IOException;
}
