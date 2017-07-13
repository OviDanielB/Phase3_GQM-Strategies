package it.uniroma2.isssr.gqm3.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.uniroma2.isssr.gqm3.model.rest.DTOMeasureTask;
import it.uniroma2.isssr.integrazione.BusException;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

public interface MeasureTaskService {

	public ResponseEntity createMeasureTask(
            DTOMeasureTask dtoMeasureTask) throws JsonProcessingException, BusException, UnsupportedEncodingException;

}
