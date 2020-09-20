package tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.GregorianCalendar;

import es.makigas.hibernate.modelo.Empleado;

public class TestEmpleados {

	//@PersistenceContext(unitName="Persistencia")  Usando JavaEE habría que utilizar esta anotación para hacerlo funcionar.
	
	private static EntityManager manager; //--> es una interfaz
	
	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {

		/*
		 * manager.persist  ---> Insertar
		 * manager.merge    ---> Modificar
		 * manager.remove   ---> Eliminar
		 * manager.find     ---> Insertar
		 * manager.getTransaction     ---> Operar con múltiples elementos
		 * manager.createQuery		  ---> Crear queries propias
		 * manager.createNameQuery		  ---> Crear consultas con nombre
		 */
		
		//Crear el gesto de persistencia EM
		emf = Persistence.createEntityManagerFactory("Persistencia");
		manager = emf.createEntityManager();
		
		
		Empleado e = new Empleado(10L, "Pérez", "Pepito", new GregorianCalendar(1979, 6, 6).getTime());
		Empleado e2 = new Empleado(25L, "Martínez", "José", new GregorianCalendar(1984, 10, 10).getTime());
		
		manager.getTransaction().begin(); //Comienzan las transacciones
		manager.persist(e);
		manager.persist(e2);
		manager.getTransaction().commit(); //Se guardan las transacciones hechas desde el begin()
		
		imprimirTodo();
			

	}
	
	@SuppressWarnings("unchecked")
	private static void imprimirTodo() {
		//Devuelve todos los empleados del sistema. Igual a SELECT * FROM EMPLEADO.
		List <Empleado> empleados = (List<Empleado>)manager.createQuery("FROM Empleado").getResultList();
		System.out.println("Es esta base de datos hay " + empleados.size() + " empleados.");
		
		for (Empleado emp : empleados) {
			System.out.println(emp.toString());
		}
	}

}
