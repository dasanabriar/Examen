package com.lmnd.callcenter.services;

import java.util.concurrent.Callable;

import com.lmnd.callcenter.model.Employee;
/**
 * Interfaz para despachadores
 * @author David Sanabria
 * @version 01/03/2018
 */
public interface IDispatcher extends Callable<Employee> {
	
	Employee dispatchCall();

}
