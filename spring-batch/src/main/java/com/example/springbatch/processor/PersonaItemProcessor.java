package com.example.springbatch.processor;

import com.example.springbatch.models.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonaItemProcessor implements ItemProcessor<Persona,Persona> {
    private static final Logger log = LoggerFactory.getLogger(PersonaItemProcessor.class);
    @Override
    public Persona process(Persona item) throws Exception {
        String primerNombre = item.getPrimerNombre().toUpperCase();
        String segundoNombre = item.getSegundoNombre().toUpperCase();
        String telefono = item.getTelefono().toUpperCase();

        Persona persona = new Persona(primerNombre,segundoNombre,telefono);
        log.info("Convirtiendo("+item+") a ("+persona+")");
        return persona;
    }
}
