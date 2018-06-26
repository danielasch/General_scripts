package pln.ReferenceAlign;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;

public class Concept {
	
	private IRI iri;
	private OWLClass cls;
	private String Name;
	
	public Concept() {
		
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	public IRI getIri() {
		return iri;
	}

	public void setIri(IRI iri) {
		this.iri = iri;
	}

	public OWLClass getCls() {
		return cls;
	}

	public void setCls(OWLClass cls) {
		this.cls = cls;
	}
	
}
