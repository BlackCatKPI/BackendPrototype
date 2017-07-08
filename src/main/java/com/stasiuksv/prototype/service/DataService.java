package com.stasiuksv.prototype.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import com.stasiuksv.prototype.controllers.ExchangeObject;
import com.stasiuksv.prototype.model.StoredObject;



public interface  DataService <T extends ExchangeObject, U extends StoredObject>
{
	public HttpStatus create(T exhangeObject);
	public HttpStatus update(Long id, T exhangeObject);
	public HttpStatus deleteByID(Long id);
	public U getByID(Long id);
	public List<U> listAll();
}
