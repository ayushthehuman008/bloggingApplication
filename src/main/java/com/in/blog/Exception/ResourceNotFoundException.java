package com.in.blog.Exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	String resourceName;
	String fieldName;
	long fieldValue;
	
	public ResourceNotFoundException() {
		super();
	}
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public long getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}

	@Override
	public String toString() {
		return "ResourceNotFoundException [resourceName=" + resourceName + ", fieldName=" + fieldName + ", fieldValue="
				+ fieldValue + "]";
	}

	
}
