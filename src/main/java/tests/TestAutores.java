package tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.makigas.hibernate.modelo.Autor;
import es.makigas.hibernate.modelo.Libro;

public class TestAutores {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

	public static void main(String[] args) {

		crearDatos();
		imprimirDatos();		

	}
	
	static void crearDatos() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Autor autor1 = new Autor(1L, "Pablo Pérez", "Español");
		Autor autor2 = new Autor(2L, "Elena Gómez", "México");
		Autor autor3 = new Autor(3L, "Miguel López", "Chile");
		em.persist(autor1);
		em.persist(autor2);
		em.persist(autor3);
		
		em.persist(new Libro(2L, "Cómo vestirse con estilo", autor3)); 
		em.persist(new Libro(1L, "Programar en Java es fácil", autor2)); 
		em.persist(new Libro(3L, "Cómo cocinar sin quemar la cocina", autor1));
		em.persist(new Libro(4L, "Programar en Cobol es divertido", autor2));
		em.persist(new Libro(5L, "Programar en Cobol no es divertido", autor2));
		
		em.getTransaction().commit();
		em.close();		
	}
	
	static void imprimirDatos() {
		EntityManager em = emf.createEntityManager();
		
		Autor autor2 = em.find(Autor.class, 2L);
		List<Libro> libros = autor2.getLibros();
		
		for (Libro libro : libros) {
			System.out.println("* " + libro.toString());	
		}
		
		System.out.println(autor2);
		
		em.close();
	}

}
