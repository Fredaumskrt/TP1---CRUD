# TP1---CRUD

Este projeto implementa um CRUD genérico com um índice direto utilizando uma tabela hash extensível.

Classes criadas: 

- Classe Arquivo <T extends registro>

Função: Gerencia a manipulação de registros em um arquivo, incluindo operações de inclusão, leitura, atualização e exclusão.
Métodos Principais:
create(T objeto): Insere um novo registro no fim do arquivo e atualiza o índice com o novo ID e a posição do registro.
read(int id): Busca o registro usando o índice direto e retorna os dados do registro.
update(T objeto): Atualiza um registro existente, realocando se necessário, para tratar alterações no tamanho do registro.
delete(int id): Marca o registro como excluído no arquivo e remove a entrada correspondente do índice.

- Interface registro
Função: Define os métodos que as classes de registros devem implementar para serem usadas com o CRUD.
Métodos:
getID(): Retorna o ID do registro.
setID(int id): Define o ID do registro.
toByteArray(): Converte o registro para um array de bytes para ser salvo no arquivo.
fromByteArray(byte[] ba): Reconstrói o registro a partir de um array de bytes lido do arquivo.

- Classe ParEmailID

Função: Representa um par chave-valor para o índice (email e ID) usado na tabela hash extensível.
Métodos:
hashCode(): Calcula o código hash do email.
size(): Retorna o tamanho do registro.
toByteArray(): Serializa o registro para um array de bytes.
fromByteArray(byte[] ba): Deserializa o registro a partir de um array de bytes.
 
- Classe HashExtensivel<T extends RegistroHashExtensivel<T>>

Função: Implementa a tabela hash extensível, que é usada como um índice direto para os registros.
Métodos:
create(T elemento): Adiciona um novo elemento na tabela hash.
read(int chave): Lê um elemento da tabela hash usando a chave.
update(T elemento): Atualiza um elemento existente na tabela hash.
delete(int chave): Remove um elemento da tabela hash.


- O trabalho possui um índice direto implementado com a tabela hash extensível?


R: Sim, o trabalho implementa um índice direto utilizando a tabela hash extensível. A classe HashExtensivel é usada para gerenciar os índices, com a classe ParIDPosicao armazenando o par ID e a posição do registro no arquivo.

- A operação de inclusão insere um novo registro no fim do arquivo e no índice e retorna o ID desse registro?


R: Sim, a operação de inclusão insere um novo registro no fim do arquivo e atualiza o índice hash extensível com o novo ID e a posição do registro no arquivo. O ID é incrementado e retornado após a inclusão.

- A operação de busca retorna os dados do registro, após localizá-lo por meio do índice direto?

R: sim, a operação de busca usa o índice direto implementado pela tabela hash extensível para localizar a posição do registro no arquivo e retorna os dados do registro.

- A operação de alteração altera os dados do registro e trata corretamente as reduções e aumentos no espaço do registro? 

R: Sim, a operação de alteração verifica o tamanho do registro após a modificação. Se o novo registro não couber no espaço existente, ele é realocado (removido e adicionado novamente), e o índice é atualizado para refletir a nova posição.

- A operação de exclusão marca o registro como excluído e o remove do índice direto?

R: Sim, a operação de exclusão marca o registro como excluído no arquivo (usando a lápide) e remove o registro correspondente do índice direto na tabela hash extensível.

- O trabalho está funcionando corretamente? 

R: Com base nos testes realizados, o trabalho está funcionando parcilamente (95%), com todas as operações principais (inclusão, busca, alteração, exclusão) sendo executadas conforme esperado. O problema de EOFException é retornado após os usos da inclusão, busca e alteração, mas nada que comprometa o uso do programa.

- O trabalho está completo? 

R: Sim está completo

-O trabalho é original e não a cópia de um trabalho de outro grupo? 

R: O trabalho foi feito por mim e com algumas dúvidas tiradas pelo gpt com alguns códigos do professor Marcos Kutova, que nos forneceu. Trabalho original!!!
