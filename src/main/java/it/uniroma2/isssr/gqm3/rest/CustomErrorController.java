package it.uniroma2.isssr.gqm3.rest;

import it.uniroma2.isssr.gqm3.model.rest.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * Questa classe consenge di gestire un errore e di customizzarlo per
 * allineamento all'altro gruppo.
 *
 * @author Fabio Alberto Coira
 *         <p>
 *         <p>
 *         Based on the helpful answer at http://stackoverflow.com/q/25356781/56285,
 *         with error details in response body added.
 *         <p>
 *         Si ringrazia "Joni Karppinen" per questa porzione di codice di cui è autore
 * @link https://gist.github.com/jonikarppinen/662c38fb57a23de61c8b
 */


@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    DTO error(HttpServletRequest request, HttpServletResponse response) {
        // Appropriate HTTP response code (e.g. 404 or 500) is automatically set by Spring. 
        // Here we just define response body.
        DTO error = new DTO();
        error.setErrorCode(response.getStatus());
        error.setMessage((String) getErrorAttributes(request, true).get("message"));
        return error;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }


    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

}