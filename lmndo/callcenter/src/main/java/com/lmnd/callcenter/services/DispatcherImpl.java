package com.lmnd.callcenter.services;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

import org.apache.log4j.Logger;


import com.lmnd.callcenter.model.Call;
import com.lmnd.callcenter.model.Employee;
import com.lmnd.callcenter.util.Constants;

/**
 * Case despachadora de llamadas 
 * @author David Sanabria
 *
 */
public class DispatcherImpl implements IDispatcher  {
	
	private static final Logger LOG = Logger.getLogger(IDispatcher.class);
	private PriorityBlockingQueue<Employee> employees;
	private Call call;
	private Employee asignedEmployee = null;
	
	
	/**
	 * Constructor 
	 * @param employees
	 * @param call
	 */
	public DispatcherImpl(PriorityBlockingQueue<Employee> employees, Call call) {
		this.employees = employees;
		this.call = call;
	}
	/**
	 *  Clase despachadora de llamadas
	 */
	public Employee dispatchCall() {
		LOG.info("dispatchCall : inicio proceso despachador llamada " + call.getIdCall()  );
		try {
			asignedEmployee = this.employees.take();
			LOG.info("dispatchCall : se reservo el elmpleado " + asignedEmployee.getIdEmployee()  );		
			callInProcess(asignedEmployee);
			employees.add(asignedEmployee);
		} catch (InterruptedException e) {
			LOG.error("dispatchCall : Error al despachar llamada : " + e.getMessage());
		}
		return asignedEmployee;
	}
	/**
	 * Metodo encargado de simular una llamda en proceso.
	 * @param asignedEmployee
	 * @throws InterruptedException
	 */
	public void callInProcess(Employee asignedEmployee) throws InterruptedException {
		LOG.info("callInProcess :  llamada "+call.getIdCall()+" en curso, asignada al empleado " + asignedEmployee.getIdEmployee() + " " + asignedEmployee.getPosition() );
		int duration = new Random().nextInt(Constants.CALL_DURATION_MAXIMUN - Constants.CALL_DURATION_MINIMUN + 1)+Constants.CALL_DURATION_MINIMUN;
		Thread.sleep(duration);
		LOG.info("callInProcess :  Finalizó la llamada "+ call.getIdCall() + "duración "+duration + " empleado " + asignedEmployee.getPosition());
	}

	@Override
	public Employee call() throws Exception {
		return dispatchCall();
	}

	public Call getCall() {
		return call;
	}

	public void setCall(Call call) {
		this.call = call;
	}

	
	
	
	
}
