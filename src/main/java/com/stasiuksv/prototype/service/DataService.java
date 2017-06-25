package com.stasiuksv.prototype.service;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stasiuksv.prototype.model.StoredObject;


public interface  DataService <T extends StoredObject>
{
	public T convertFromJSON(String jsonString) throws JsonParseException, JsonMappingException, IOException;
	public String convertToJSON(Object objectToJSON) throws JsonProcessingException;
	public HttpStatus create(String jsonString);
	public HttpStatus update(Long id, String jsonString);
	public HttpStatus deleteByID(Long id);
	public String getByID(Long id);
	public String listAll();
}
