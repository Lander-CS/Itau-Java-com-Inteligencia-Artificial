package exercicio8;

/**
 * INTERFACE FUNCIONAL (BASE PARA EXPRESSÕES LAMBDA)
 * 
 * A anotação @FunctionalInterface informa ao compilador Java que esta interface
 * possui EXATAMENTE UM único método abstrato.
 * Por ter apenas um método, ela pode ser implementada diretamente por uma Expressão Lambda (->)!
 */
@FunctionalInterface
public interface ServicoNotificacao {
    // Único método abstrato:
    void enviar(String mensagem, String destinatario);
}
