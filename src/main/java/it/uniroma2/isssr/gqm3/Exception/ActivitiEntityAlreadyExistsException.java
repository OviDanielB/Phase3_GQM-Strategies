package it.uniroma2.isssr.gqm3.Exception;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

@SuppressWarnings("serial")
public class ActivitiEntityAlreadyExistsException extends Exception {

	private static final String dataFieldName = "data";
	
	private static <T> String buildMessage(String modelName, Class<T> T){
		
		Field stringListField = null;
		try {
			stringListField = T.getDeclaredField(dataFieldName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
        ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
		return "A "+ stringListClass.getSimpleName() +" named \"" + modelName + "\" already exists.";
	}
	
	
	public ActivitiEntityAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}
	
	public ActivitiEntityAlreadyExistsException(String name, Class<?> entityListClass) {
		super(buildMessage(name, entityListClass));
	}

	public ActivitiEntityAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public ActivitiEntityAlreadyExistsException(String name, Class<?> entityListClass, Throwable cause) {
		super(buildMessage(name, entityListClass), cause);
	}

	public ActivitiEntityAlreadyExistsException(String name, Class<?> entityListClass, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(buildMessage(name, entityListClass), cause, enableSuppression, writableStackTrace);
	}

}
