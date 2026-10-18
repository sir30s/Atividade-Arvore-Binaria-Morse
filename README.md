# Árvore Binária de Código Morse

Implementação em Java de uma árvore binária em que cada nó representa um símbolo do código Morse. O caminho da raiz até um nó descreve a sequência de pontos e traços do caractere armazenado nele.

## Convenção da árvore

- A raiz é um nó vazio.
- Um ponto (.) leva ao filho da esquerda.
- Um traço (-) leva ao filho da direita.
- A profundidade de um nó é o tamanho do seu código Morse. Por exemplo, E (.) fica no nível 1 e S (...) no nível 3.
- Nós intermediários, que só fazem parte do caminho de outras letras, não guardam letra. Eles são marcados com o valor ' ' e aparecem como @ na exibição.

## Estrutura do projeto

- No: guarda o código (sinal), o caractere (valor) e as referências aos filhos esquerda e direita.
- ArvoreBinaria: contém a raiz e as operações de inicializar, inserir, buscar e exibir.
- Main: executa os testes de uso.

Métodos de ArvoreBinaria:

- inicializar(): marca a raiz com o sinal reservado @ (é uma limitação do projeto. Depois elaboro mais sobre).
- inserir(String sinal, char valor): percorre o código símbolo a símbolo, cria os nós que ainda não existem no caminho e grava o caractere no último nó.
- buscarSinal(String sinal): recebe um ou mais códigos Morse separados por espaço e devolve os caracteres correspondentes. Por exemplo, "... --- ..." retorna SOS.
- buscarLetra(char letra): recebe um caractere e devolve o seu código Morse. Usa o método privado recursivo buscarNo.
- imprimir(): exibe a árvore de forma hierárquica. Usa o método privado recursivo imprimirNo.

## Exemplo de uso

Trecho do main:

```java
ArvoreBinaria arvore = new ArvoreBinaria();
arvore.inicializar();

arvore.inserir("---", 'O');
arvore.inserir("...", 'S');

System.out.println(arvore.buscarSinal("..."));
System.out.println(arvore.buscarSinal("---"));
System.out.println(arvore.buscarSinal("..."));
System.out.println(arvore.buscarSinal(".."));
System.out.println(arvore.buscarSinal("... --- ..."));
System.out.println(arvore.buscarLetra('S'));
System.out.println(arvore.buscarLetra('F'));

arvore.imprimir();
```

Saída:

```
S
O
S
NÃO ENCONTRADO!

SOS
...
NÃO ENCONTRADO!

@(.@(.@(.S)) -@(-@(-O)))
```

buscarSinal("..") avisa que não encontrou: o caminho existe, mas o nó .. é intermediário e não guarda letra. A linha em branco vem do println do resultado vazio.

buscarLetra('F') avisa que não encontrou porque o F ainda não foi inserido nesse ponto do teste. A linha em branco tem a mesma origem.

Em seguida o main insere as demais letras do alfabeto e os dígitos de 0 a 9, e exibe a árvore completa.

Como ler a exibição:

- O parêntese que se abre depois de um nó contém os filhos desse nó, e pertence a ele.
- Cada filho é mostra o ramo de onde veio: . (esquerda) ou - (direita).
- Um nó sem filhos é impresso sozinho, sem parênteses.
- @ representa a raiz e os nós intermediários, que não guardam letra. Escolhi o "@" porque sim.

## Decisões de projeto

**1. O sinal @ é reservado para a raiz**
O sinal @ não representa nenhum código Morse. Ele é reservado para a raiz da árvore e é usado apenas pelo método inicializar.
Por isso o aviso de sinal inválido, emitido por inserir quando encontra um símbolo diferente de ponto e traço, não é exibido para @. O professor disse em sala que seria possível adicionar valores
"aleatórios" ou "inexistentes" na tabela de código morse, então é uma limitação conhecida deixar de lado o "@".

**2. As operações sobre o código Morse são feitas com a classe Scanner**
Conforme a orientação do professor, apenas length() e a classe Scanner são permitidos.
Por isso, métodos de String como charAt, split, substring, equals e contains não foram usados (apanhei). Para percorrer um código Morse símbolo a símbolo, o programa cria um Scanner sobre a própria String e:

- usa useDelimiter(""), que faz cada chamada de next() devolver um único símbolo;
- identifica o símbolo com hasNext("\\.") (ponto) e hasNext("-") (traço), sem consumi-lo. O ponto precisa de escape (\\.) porque, em expressão regular, o ponto casa com qualquer caractere (aprendi na marra);
- consome o símbolo com next() depois de decidir para qual lado descer.

Em buscarSinal, um segundo Scanner, com delimitador de espaço, separa os códigos de uma mensagem.

**3. Uso do laço while**
O percurso usa while (scanner.hasNext()) com next() dentro do laço porque foi a maneira que eu encontrei com base na documentação do W3Schools pra classe Scanner.

**4. Busca por letra**
buscarLetra percorre a árvore recursivamente: confere o nó atual, depois a subárvore esquerda e, por fim, a direita (PRÉ-ORDEM!!!).
Ao encontrar o nó cujo valor é o caractere procurado, devolve o sinal guardado nele.

**5. Exibição da árvore.**
A função imprimir tomou como referência o código discutido no StackOverflow: https://stackoverflow.com/questions/8964279/coding-a-basic-pretty-printer-for-trees-in-java.
A versão final foi adaptada ao formato pedido: cada nó é seguido de um par de parênteses que contém os seus filhos.

O método recursivo imprimirNo imprime o nó e, se ele tiver filhos, abre um parêntese, imprime o filho da esquerda (precedido de .), o filho da direita (precedido de -) e fecha o parêntese.
Um filho só é visitado se existir, o que permite imprimir o rótulo do ramo apenas quando há nó correspondente.

**Tratamento de erros:**

- buscarSinal com caminho inexistente: exibe NÃO ENCONTRADO!
- buscarSinal com caminho existente, mas em nó sem letra: exibe NÃO ENCONTRADO!
- buscarSinal com símbolo diferente de . e -: devolve OCORRÊNCIA DE SINAL INVÁLIDO
- buscarLetra com caractere ausente da árvore ou ' ': exibe NÃO ENCONTRADO! e devolve texto vazio
- inserir com símbolo diferente de . e -: exibe INSIRA CORRETAMENTE OS VALORES!

## Referências

- W3Schools, Scanner.next(): https://www-w3schools-com.translate.goog/java/ref_scanner_next.asp?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt&_x_tr_pto=tc
- W3Schools, Scanner.hasNext(): https://www-w3schools-com.translate.goog/java/ref_scanner_hasnext.asp?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt&_x_tr_pto=tc
- StackOverflow, comparação de strings com Scanner: https://stackoverflow.com/questions/34728988/how-to-compare-string-in-java-using-scanner-object
- StackOverflow, pretty printer de árvores em Java: https://stackoverflow.com/questions/8964279/coding-a-basic-pretty-printer-for-trees-in-java

## Dificuldades encontradas

- Restrições: A proibição de usar as demais funções da API do Java exigiu que eu estudasse a classe Scanner a fundo, em especial useDelimiter,
  hasNext com padrão e next. foi bem "divertido".
  
- Tratamento de erros: Entradas fora do caminho esperado produziram, durante o desenvolvimento, NullPointerException (ao descer por um filho inexistente) e laços infinitos (quando um símbolo inválido não era consumido).
  Foi necessário tratar os casos de caminho inexistente, nó sem letra e símbolo inválido.
  
- Recursividade. Este é um tema em que ainda tenho dificuldade. Foi preciso identificar o caso base e a ordem das chamadas tanto em buscarNo quanto em imprimirNo.
  
- Exibição da árvore. Foi a etapa mais trabalhosa. O formato com parênteses cumpre o pedido de mostrar os filhos dentro do nó pai, mas com a tabela completa a saída ocupa uma única linha longa.
  Como melhoria futura, a exibição poderia usar uma linha por nó, com indentação proporcional à profundidade.

## Limitações conhecidas

- O programa ainda não lê mensagens digitadas pelo usuário. Os testes estão no main, apenas valores char isolados.
- inserir avisa quando recebe um símbolo inválido, mas não interrompe a inserção.
