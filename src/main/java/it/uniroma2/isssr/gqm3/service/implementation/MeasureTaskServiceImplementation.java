package it.uniroma2.isssr.gqm3.service.implementation;

import it.uniroma2.isssr.gqm3.service.MeasureTaskService;
import it.uniroma2.isssr.gqm3.model.MeasureTask;
import it.uniroma2.isssr.gqm3.model.rest.DTOMeasureTask;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseMeasureTask;
import it.uniroma2.isssr.gqm3.repository.MeasureTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("MeasureTaskService")
public class MeasureTaskServiceImplementation implements MeasureTaskService {

    @Autowired
    MeasureTaskRepository measureTaskRepository;

    @Override
    public ResponseEntity<DTOResponseMeasureTask> createMeasureTask(DTOMeasureTask
                                                                            dtoMeasureTask) {
        // TODO Auto-generated method stub
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

        DTOResponseMeasureTask dtoResponseMeasureTask =
                new DTOResponseMeasureTask();

        dtoResponseMeasureTask.set_id(measureTask.get_id());
        dtoResponseMeasureTask.setMetric(measureTask.getMetric());
        dtoResponseMeasureTask.setResponsible(measureTask.getResponsible());
        dtoResponseMeasureTask.setTaskId(measureTask.getTaskId());
        dtoResponseMeasureTask.setMeans(measureTask.getMeans());
        dtoResponseMeasureTask.setValidationIdList(measureTask.getValidationIdList());

        ResponseEntity<DTOResponseMeasureTask> responseEntity =
                new ResponseEntity<DTOResponseMeasureTask>
                        (dtoResponseMeasureTask, HttpStatus.CREATED);
        return responseEntity;
    }


}
