package ar.edu.unlam.pb2.test;


import static org.junit.Assert.assertTrue;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.drools.core.io.impl.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderFactory;

import ar.edu.unlam.pb2.modelo.Persona;

public class DescuentosTest {

	@Test
	public void descuentoBuenosAires() {
		InternalKnowledgeBase base = crearBase();
		KieSession session = base.newKieSession();
		Persona persona = new Persona();
		persona.setCiudad("Buenos Aires");
		persona.setEdad(19);
		session.insert(persona);
		session.fireAllRules();
		session.dispose();
		
		Assert.assertTrue("El descuento esperado es de 0.25 pero fue de " + persona.getDescuento(), 0.25 == persona.getDescuento());
		
		System.out.println("El descuento para el ciudadano de Buenos Aires es de: " + persona.getDescuento());
	}
	
	
	@Test
	public void dosPersonasTest() {
		InternalKnowledgeBase base = crearBase();
		KieSession session = base.newKieSession();
		Persona personaBuenosAires = new Persona();
		personaBuenosAires.setCiudad("Buenos Aires");
		personaBuenosAires.setEdad(18);
		session.insert(personaBuenosAires);
		Persona personaEntreRios = new Persona();
		personaEntreRios.setCiudad("Entre Rios");
		personaEntreRios.setEdad(70);
		session.insert(personaEntreRios);
		session.fireAllRules();
		session.dispose();
		

		Assert.assertTrue("El descuento esperado es de 0.25, pero fue de " + personaBuenosAires.getDescuento(), 0.25 == personaBuenosAires.getDescuento());
		Assert.assertTrue("El descuento esperado es de 0.50, pero fue de " + personaEntreRios.getDescuento(), 0.50 == personaEntreRios.getDescuento());
		
		System.out.println("El descuento para el ciudadano de Buenos Aires es de: " + personaBuenosAires.getDescuento());
		System.out.println("El descuento para el ciudadano de Entre Rios es de: " + personaEntreRios.getDescuento());
	}
	
	private InternalKnowledgeBase crearBase() {
		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		builder.add(new ClassPathResource ("descuentos.drl"), ResourceType.DRL);
		
		if(builder.hasErrors()) {
			for(KnowledgeBuilderError error : builder.getErrors()) {
				System.out.println(error);
			}
			throw new IllegalArgumentException("No se logro compilar la base de conocimiento");
		}
		
		InternalKnowledgeBase base = KnowledgeBaseFactory.newKnowledgeBase();
		base.addPackages(builder.getKnowledgePackages());
		return base ;
	}
	
}
