package it.uniroma2.isssr.gqm3.rest;

import it.uniroma2.isssr.gqm3.model.Metric;
import it.uniroma2.isssr.gqm3.model.rest.DTOMetric;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseMetric;
import it.uniroma2.isssr.gqm3.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class MetricMockupController {

    @Autowired
    MetricRepository metricRepository;

    @RequestMapping(value = "/createMetric", method = RequestMethod.POST)
    public ResponseEntity<DTOResponseMetric> createMetric(@RequestBody DTOMetric metricDto) {

        Metric metric = new Metric();
        metric.setOrdered(metricDto.getOrdered());
        metric.setName(metricDto.getName());
        metric.setVersion(metricDto.getVersion());
        metric.setReleaseNote(metricDto.getReleaseNote());
        metric.setUnit(metricDto.getUnit());
        metric.setScaleType(metricDto.getScaleType());
        metric.setId(metricDto.getId());
        metric.setDescription(metricDto.getDescription());

        /* save on local mongodb */
        metricRepository.save(metric);

        DTOResponseMetric dtoResponse = new DTOResponseMetric();
        dtoResponse.setName(metric.getName());
        dtoResponse.setDescription(metric.getDescription());
        dtoResponse.setId(metric.getId());

        ResponseEntity<DTOResponseMetric> responseEntity = new ResponseEntity<>(dtoResponse,
                HttpStatus.OK);

        return responseEntity;
    }
}
