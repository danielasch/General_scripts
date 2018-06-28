package pln.ReferenceAlign;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class Ontology {
	
	private String path;
	private OWLOntologyManager m;
	private IRI iri;
	private OWLOntology ontology;
	private String name;
		
	public Ontology(String path) {
		setPath(path);
		load();
	}
		
	private void load() {
		try {
			m = OWLManager.createOWLOntologyManager();
			File file = new File(path);
			ontology = m.loadOntologyFromOntologyDocument(file);
			IRI iri = m.getOntologyDocumentIRI(ontology);
			setIRI(iri);
			setName();
			}catch(OWLOntologyCreationException e) {
				System.out.println("Impossível criar a ontologia: " + e.getCause());
			}catch(Exception e) {
				System.out.println("Exceção inesperada: " + e.getCause());
			}
	}
	
	private void setName() {
		int i = path.lastIndexOf("\\");
		if(i < 0) {
			i = path.lastIndexOf("/"); 
		}
		String name = path.substring(i+1);
		this.name = name;
	}
		
	private void setIRI(IRI iri) {
		this.iri = iri;
	}
		
	private void setPath(String path) {
		this.path = path;
	}
	
	public String getName() {
		return this.name;
	}
	
	public IRI getIRI() {
		return this.iri;
	}
	
	public OWLOntology getOntology() {
		return this.ontology;
	}
	
	public String getPath() {
		return this.path;
	}
	
}
