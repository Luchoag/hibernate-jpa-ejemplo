package tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.GregorianCalendar;

import es.makigas.hibernate.modelo.Empleado;
import es.makigas.hibernate.modelo.Direccion;

public class TestEmpleados {

	//@PersistenceContext(unitName="Persistencia")  Usando JavaEE habría que utilizar esta anotación para hacerlo funcionar.
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	
	public static void main(String[] args) {

		/*
		 * manager.persist  ---> Insertar: inserta los datos y los deja en modo managed, que significa que los cambios que se realizan se actualizan automáticamente
		 * manager.merge    ---> Modificar: sirve para administrar una entidad que no está en modo managed
		 * manager.remove   ---> Eliminar
		 * manager.find     ---> Encontrar por clase, ID, etc.
		 * manager.getTransaction     ---> Operar con múltiples elementos
		 * manager.createQuery		  ---> Crear queries propias
		 * manager.createNameQuery		  ---> Crear consultas con nombre
		 */
		
		//Crear el gestor de persistencia EM
		EntityManager manager = emf.createEntityManager();
		
		manager = emf.createEntityManager();
		
		insertInicial();
		
		Empleado e = manager.find(Empleado.class, 10L);
		manager.getTransaction().begin();
		e.setNombre("David");
		manager.persist(e);
		manager.getTransaction().commit();
		
		imprimirTodo();		
		manager.close();
		
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		e.setNombre("Raúl"); //El cambio no se produce en la base de datos porque la entidad está detached, es decir no administrada luego de haber cerrado el manager
		e = manager.merge(e); //Es necesario este método para que el cambio de la línea anterior se realice.
		manager.remove(e); //Elimina al empleado de la tabla
		manager.getTransaction().commit();
		
		imprimirTodo();		
		manager.close();		
		
	}

	private static void insertInicial() {
		EntityManager manager = emf.createEntityManager();
		Empleado e = new Empleado(10L, "Pérez", "Pepito", LocalDate.of(1979, 6, 6));
		Empleado e2 = new Empleado(25L, "Martínez", "José", LocalDate.of(1979, 6, 6));
		
		e.setDireccion(new Direccion(15L, "Calle Falsa 123", "Springfield", "Springfield", "EEUU"));
		
		manager.getTransaction().begin(); //Comienzan las transacciones
		manager.persist(e);
		manager.persist(e2);
		
		e.setApellido("López");
		
		manager.getTransaction().commit(); //Se guardan las transacciones hechas desde el begin()
		
		manager.close();
	}
	
	@SuppressWarnings("unchecked")
	private static void imprimirTodo() {
		EntityManager manager = emf.createEntityManager();
		//Devuelve todos los empleados del sistema. Igual a SELECT * FROM EMPLEADO.
		List <Empleado> empleados = (List<Empleado>)manager.createQuery("FROM Empleado").getResultList();
		System.out.println("Es esta base de datos hay " + empleados.size() + " empleados.");
		
		for (Empleado emp : empleados) {
			System.out.println(emp.toString());
		}
		manager.close();
	}

}
