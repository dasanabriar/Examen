package com.lmnd.callcenter.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Test;

import com.lmnd.callcenter.model.Call;
import com.lmnd.callcenter.model.Director;
import com.lmnd.callcenter.model.Employee;
import com.lmnd.callcenter.model.Operator;
import com.lmnd.callcenter.model.Supervisor;
import com.lmnd.callcenter.services.DispatcherImpl;
import com.lmnd.callcenter.services.IDispatcher;
import com.lmnd.callcenter.util.Constants;

/**
 * Clase test de DispachertImpl
 * 
 * @author David Sanabria
 * @version 02/03/2018
 */
public class DispatcherImplTest {

	static PriorityBlockingQueue<Employee> employees;
	/**
	 * Test prueba de 50 llamadas 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@Test
	public void dispatchCallTest1() throws InterruptedException, ExecutionException {
		createEmployees();
		int callNumber = 0, 
		    countCall = 0;
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < callNumber; i++) {
			Call call = new Call();
			call.setIdCall(i);
			IDispatcher dispatcher = new DispatcherImpl(employees, call);
			Future<Employee> result = executor.submit(dispatcher);
			assert result.get() != null;
			countCall++;
		}
		assert countCall == callNumber;

	}
	/**
	 * Test prueba de llamadas sin empleados
	 */
	@Test(expected = Exception.class)
	public void dispatchCallTest2() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			Call call = new Call();
			call.setIdCall(i);
			IDispatcher dispatcher = new DispatcherImpl(null, call);
			executor.submit(dispatcher);
		}

	}

	/**
	 * 
	 * Metodo encargado de simular empleados 2 operadores, 1 supervisor y 1 director
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
}
