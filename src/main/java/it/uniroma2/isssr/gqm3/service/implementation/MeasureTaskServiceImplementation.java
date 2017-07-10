package it.uniroma2.isssr.gqm3.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.service.MeasureTaskService;
import it.uniroma2.isssr.gqm3.model.MeasureTask;
import it.uniroma2.isssr.gqm3.model.rest.DTOMeasureTask;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseMeasureTask;
import it.uniroma2.isssr.gqm3.repository.MeasureTaskRepository;
import it.uniroma2.isssr.integrazione.BusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service("MeasureTaskService")
public class MeasureTaskServiceImplementation implements MeasureTaskService {

    @Autowired
    MeasureTaskRepository measureTaskRepository;

    @Autowired
    HostSettings hostSettings;

    @Autowired
    BusService2Phase4Implementation busService2Phase4Implementation;

    @Override
    public ResponseEntity createMeasureTask(DTOMeasureTask
                                                                            dtoMeasureTask) throws JsonProcessingException, BusException, UnsupportedEncodingException {
        MeasureTask measureTask = new MeasureTask();

        if (dtoMeasureTask == null) {
            //return BAD_REQUEST
        }
        measureTask.setMeans(dtoMeasureTask.getMeans());
        measureTask.setTaskId(dtoMeasureTask.getTaskId());
        measureTask.setMetric(dtoMeasureTask.getMetric());
        measureTask.setResponsible(dtoMeasureTask.getResponsible());
        measureTask.setValidationIdList(dtoMeasureTask.getValidationIdList());
        measureTaskRepository.save(measureTask);

        DTOResponseMeasureTask dtoResponseMeasureTask = new DTOResponseMeasureTask();
        dtoResponseMeasureTask.set_id(measureTask.get_id());
        dtoResponseMeasureTask.setMetric(measureTask.getMetric());
        dtoResponseMeasureTask.setResponsible(measureTask.getResponsible());
        dtoResponseMeasureTask.setTaskId(measureTask.getTaskId());
        dtoResponseMeasureTask.setMeans(measureTask.getMeans());
        dtoResponseMeasureTask.setValidationIdList(measureTask.getValidationIdList());

        /* save on bus and return ResponseEntiry */
        return busService2Phase4Implementation.saveMeasureTask(measureTask);

    }


}
