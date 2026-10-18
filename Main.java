import java.util.Scanner;

class No {
    String sinal;
    char valor;
    No esquerda;
    No direita;

    public No(String sinal){
        this.sinal = sinal;
        this.esquerda = null;
        this.direita = null;
    }

    public No(String sinal, char valor) {
        this.sinal = sinal;
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }
}

class ArvoreBinaria {
    No raiz = new No(" ");

    public void inicializar() {
        inserir("@", ' ');
    }


    public void inserir(String sinal, char valor){
        No atual = raiz;
        Scanner sinalAtual = new Scanner(sinal);
        sinalAtual.useDelimiter("");

        while (sinalAtual.hasNext()){

            if (sinalAtual.hasNext("\\.")){
                sinalAtual.next();
                if(atual.esquerda == null) {
                    atual.esquerda = new No(" ", ' ');
                }
                atual = atual.esquerda;

            } else if (sinalAtual.hasNext("-")){
                sinalAtual.next();
                if(atual.direita == null) {
                    atual.direita = new No(" ", ' ');
                }
                atual = atual.direita;
            }

            else {
                if(sinal != "@") {
                    System.out.println("INSIRA CORRETAMENTE OS VALORES!");
                }
                sinalAtual.next();
            }
        }
        sinalAtual.close();

        atual.valor = valor;
        atual.sinal = sinal;

    }

    public String buscarLetra(char letra){
        if(letra == ' '){
            System.out.println("NÃO ENCONTRADO!");
            return "";
        }
        No atual = buscarNo(raiz, letra);

        if(atual == null){
            System.out.println("NÃO ENCONTRADO!");
            return "";
        }
        return atual.sinal;
    }
    private No buscarNo(No atual, char letra){
        if(atual == null){
            return null;
        }

        if(atual.valor == letra){
            return atual;
        }

        No achou = buscarNo(atual.esquerda, letra);
        if(achou != null){
            return achou;
        }

        return buscarNo(atual.direita, letra);
    }

    public String buscarSinal(String sinal) {

        Scanner sinalSeparado = new Scanner(sinal);
        sinalSeparado.useDelimiter(" ");

        String resultado = "";

        while (sinalSeparado.hasNext()) {
            String codigoAtual = sinalSeparado.next();
            Scanner sinalAtual = new Scanner(codigoAtual);
            sinalAtual.useDelimiter("");

            No atual = raiz;
            boolean falhou = false;

            while (sinalAtual.hasNext()) {

                if (sinalAtual.hasNext("\\.")) {
                    sinalAtual.next();

                    if(atual.esquerda == null){
                        falhou = true;
                    } else {
                        atual = atual.esquerda;
                    }
                }

                else if (sinalAtual.hasNext("-")) {

                    sinalAtual.next();

                    if(atual.direita == null){
                        falhou = true;
                    }else{
                        atual = atual.direita;
                    }
                }
                else {
                    return "OCORRÊNCIA DE SINAL INVÁLIDO";
                }
            }
            sinalAtual.close();

            if(!falhou && atual.valor != ' ') {
                resultado += atual.valor;
            }else{
                System.out.println("NÃO ENCONTRADO!");
            }

        }
        sinalSeparado.close();
        return resultado;
    }

    public void imprimir(){
        imprimirNo(raiz);
        System.out.println();
    }

    private void imprimirNo(No atual){
        if(atual.valor == ' '){
            System.out.print("@");
        } else {
            System.out.print(atual.valor);
        }

        if(atual.esquerda != null || atual.direita != null){
            System.out.print("(");

            if(atual.esquerda != null){
                System.out.print(".");
                imprimirNo(atual.esquerda);
            }
            if(atual.esquerda != null && atual.direita != null){
                System.out.print(" ");
            }
            if(atual.direita != null){
                System.out.print("-");
                imprimirNo(atual.direita);
            }

            System.out.print(")");
        }
    }
}

class Main{
    public static void main(String[] args){
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

        arvore.inserir(".-", 'A');
        arvore.inserir("-...", 'B');
        arvore.inserir("-.-.", 'C');
        arvore.inserir("-..", 'D');
        arvore.inserir(".", 'E');
        arvore.inserir("..-.", 'F');
        arvore.inserir("--.", 'G');
        arvore.inserir("....", 'H');
        arvore.inserir("..", 'I');
        arvore.inserir(".---", 'J');
        arvore.inserir("-.-", 'K');
        arvore.inserir(".-..", 'L');
        arvore.inserir("--", 'M');
        arvore.inserir("-.", 'N');
        arvore.inserir(".--.", 'P');
        arvore.inserir("--.-", 'Q');
        arvore.inserir(".-.", 'R');
        arvore.inserir("-", 'T');
        arvore.inserir("..-", 'U');
        arvore.inserir("...-", 'V');
        arvore.inserir(".--", 'W');
        arvore.inserir("-..-", 'X');
        arvore.inserir("-.--", 'Y');
        arvore.inserir("--..", 'Z');

        arvore.imprimir();
    }
}

// REFERÊNCIAS:
// https://www-w3schools-com.translate.goog/java/ref_scanner_next.asp?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt&_x_tr_pto=tc
// https://www-w3schools-com.translate.goog/java/ref_scanner_hasnext.asp?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt&_x_tr_pto=tc
// https://stackoverflow.com/questions/34728988/how-to-compare-string-in-java-using-scanner-object
// https://stackoverflow.com/questions/8964279/coding-a-basic-pretty-printer-for-trees-in-java
// https://medium.com/devmap/using-binary-trees-and-java-to-decode-and-encode-this-19th-century-messaging-protocol-7a26d7049a11