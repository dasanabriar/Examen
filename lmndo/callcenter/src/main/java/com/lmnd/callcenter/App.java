package com.lmnd.callcenter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import com.lmnd.callcenter.model.Call;
import com.lmnd.callcenter.model.Director;
import com.lmnd.callcenter.model.Employee;
import com.lmnd.callcenter.model.Operator;
import com.lmnd.callcenter.model.Supervisor;
import com.lmnd.callcenter.services.DispatcherImpl;
import com.lmnd.callcenter.services.IDispatcher;
import com.lmnd.callcenter.util.Constants;

/**
 * Programa para manejar la concurrecia de llamadas
 * 
 * @author David Sanabria
 * @version 01/03/2018
 */
public class App

{

	public static PriorityBlockingQueue<Employee> employees;
	
	/**
	 * Metodo main
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			createEmployees();
			callProcess();
		} catch (InterruptedException e) {
			
		}
	}
	
	/**
	 * 
	 *  Metodo encargado de crear empleados 
	 *    
	 */
	public static void createEmployees() {
		employees = new PriorityBlockingQueue<>();
		int iterator = 0;
		for (; iterator < 2; iterator++) {
			employees.put(new Operator(iterator, Constants.PRIORITY_OPERATOR));
		}

		for (; iterator < 3; iterator++) {
			employees.put(new Supervisor(iterator, Constants.PRIORITY_SUPERVISOR));
		}

		for (; iterator < 4; iterator++) {
			employees.put(new Director(iterator, Constants.PRIORITY_DIRECTOR));
		}
	}
	/**
	 * Metodo encargado de simular las llamdas y ejecutar el despachador
	 * @throws InterruptedException
	 */
	public static void callProcess() throws InterruptedException {

		ExecutorService executor = Executors.newFixedThreadPool(10);

		for (int i = 0; i < 10; i++) {
			Call call = new Call();
			call.setIdCall(i);
			IDispatcher dispatcher = new DispatcherImpl(employees, call);
			executor.submit(dispatcher);
			Thread.sleep(1000);
		}

	}
}
