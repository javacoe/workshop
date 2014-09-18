package org.springhibernate.base.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springhibernate.base.model.Adres;


public interface DBServiceInterface<E>{

	public List<E> listItems();
	
	public void saveOrUpdateItem(E item);
	
	public E get(int id);
	
	public void delete(int id);	
}
