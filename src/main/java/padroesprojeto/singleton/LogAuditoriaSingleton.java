package padroesprojeto.singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PADRÃO CRIACIONAL: SINGLETON
 *
 * Centraliza o registro de auditoria de todas as transações bancárias
 * em um único ponto de acesso global.
 */
public class LogAuditoriaSingleton {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final List<String> historico = new ArrayList<>();

    private LogAuditoriaSingleton() {
    }

    private static class InstanceHolder {
        private static final LogAuditoriaSingleton INSTANCIA = new LogAuditoriaSingleton();
    }

    public static LogAuditoriaSingleton getInstancia() {
        return InstanceHolder.INSTANCIA;
    }

    public void registrar(String evento) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        historico.add("[" + timestamp + "] " + evento);
    }

    public List<String> getHistorico() {
        return Collections.unmodifiableList(historico);
    }
}
