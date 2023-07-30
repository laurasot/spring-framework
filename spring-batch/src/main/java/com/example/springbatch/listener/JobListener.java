package com.example.springbatch.listener;

import com.example.springbatch.models.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component //esto es un listener
public class JobListener implements JobExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(JobListener.class);

    private final JdbcTemplate jdbcTemplate;

    public JobListener(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override //despues del trabajo
    // se implementa el metodo de la interfaz, recibe un job ya ejecutado?
    // revisa el status, en caso de que fue completado correctamente, lanza un log con mensaje
    // utiliza una query de jdbc
    // (rs, row) -> new Persona(rs.getString(1), rs.getString(2), rs.getString(3)))
    // se utiliza como un parámetro de la función query. Esta parte del código se conoce como una lambda
    //o expresión lambda y se utiliza para mapear cada fila del resultado de la consulta a un objeto Persona.
    //forEach se utiliza para iterar sobre cada objeto Persona en la lista resultante y realizar una operación.
    // En este caso, se utiliza log.info para imprimir un mensaje en el registro de logs, que incluye el objeto Persona.
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.info("Finalizó el job. Verifica los resultados:");

            jdbcTemplate.query("SELECT primer_nombre, segundo_nombre, telefono", (rs,row) ->
                    new Persona(rs.getString(1), rs.getString(2), rs.getString(3)))
                    .forEach(persona -> log.info("Registro < "+persona+" > "));
        }
    }
}
