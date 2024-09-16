# TP1---CRUD

Este projeto implementa um CRUD genérico com um índice direto utilizando uma tabela hash extensível.

- O trabalho possui um índice direto implementado com a tabela hash extensível?

 R: Sim, o trabalho implementa um índice direto utilizando a tabela hash extensível. A classe HashExtensivel é usada para gerenciar os índices, com a classe ParIDPosicao armazenando o par ID e a posição do registro no arquivo.

- A operação de inclusão insere um novo registro no fim do arquivo e no índice e retorna o ID desse registro? R: Sim, a operação de inclusão insere um novo registro no fim do arquivo e atualiza o índice hash extensível com o novo ID e a posição do registro no arquivo. O ID é incrementado e retornado após a inclusão.

- A operação de busca retorna os dados do registro, após localizá-lo por meio do índice direto? R: sim, a operação de busca usa o índice direto implementado pela tabela hash extensível para localizar a posição do registro no arquivo e retorna os dados do registro.

- A operação de alteração altera os dados do registro e trata corretamente as reduções e aumentos no espaço do registro? R: Sim, a operação de alteração verifica o tamanho do registro após a modificação. Se o novo registro não couber no espaço existente, ele é realocado (removido e adicionado novamente), e o índice é atualizado para refletir a nova posição.

- A operação de exclusão marca o registro como excluído e o remove do índice direto? R: Sim, a operação de exclusão marca o registro como excluído no arquivo (usando a lápide) e remove o registro correspondente do índice direto na tabela hash extensível.

- O trabalho está funcionando corretamente? R: Com base nos testes realizados, o trabalho está funcionando parcilamente (95%), com todas as operações principais (inclusão, busca, alteração, exclusão) sendo executadas conforme esperado. O problema de EOFException é retornado após os usos da inclusão, busca e alteração, mas nada que comprometa o uso do programa.

- O trabalho está completo? R: Sim está completo

-O trabalho é original e não a cópia de um trabalho de outro grupo? R: O trabalho foi feito por mim e com algumas dúvidas tiradas pelo gpt com alguns códigos do professor Marcos Kutova, que nos forneceu. Trabalho original!!!
