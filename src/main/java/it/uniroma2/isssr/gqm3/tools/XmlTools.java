package it.uniroma2.isssr.gqm3.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.uniroma2.isssr.gqm3.Exception.ModelXmlNotFoundException;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class XmlTools {

	private static final Logger log = LoggerFactory.getLogger(XmlTools.class);

	/**
	 * Create a String with content imported from a file xml adapted to a new
	 * name of workflow.
	 * 
	 * @param path
	 *            Path of Xml file.
	 * @param defaultProcessIdentifierName
	 *            Default Identifier name of the process to be replaced, that is
	 *            described by Xml file
	 * @param defaultName
	 *            Default name of workflow to be replaced, that is described by
	 *            Xml file
	 * @param processIdentifierName
	 *            New name of Identifier name of the process to replace with in
	 *            imported xml file
	 * @param name
	 *            New name of workflow to replace with in imported xml file
	 * 
	 * @return String with content imported from a file xml adapted to a new
	 *         name of workflow.
	 * @throws IOException
	 */
	public static String workflowModelXmlToString(String path, String defaultProcessIdentifierName, String defaultName,
			String processIdentifierName, String name) throws IOException {
		// read file into a string
		String content;
		File file = new ClassPathResource(path).getFile();
		log.info("loading " + file.getAbsolutePath());
		Scanner scanner = new Scanner(file);
		content = scanner.useDelimiter("\\Z").next();
		scanner.close();
		content = content.replaceAll(defaultProcessIdentifierName, processIdentifierName.replaceAll(" ", "_"));
		content = content.replaceAll(defaultName, name);
		return content;
	}

	public static String modelSourceToXml(String source)
			throws JsonProcessingException, IOException, ModelXmlNotFoundException {
		JsonNode editorNode;
		System.out.println(source);
		editorNode = new ObjectMapper().readTree(source);
		BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
		
		BpmnModel modelData = jsonConverter.convertToBpmnModel(editorNode);
		byte[] bpmnBytes = null;
		bpmnBytes = new BpmnXMLConverter().convertToXML(modelData);
		if (bpmnBytes == null) {
			throw new ModelXmlNotFoundException();
		}
		return new String(bpmnBytes);

	}

}
