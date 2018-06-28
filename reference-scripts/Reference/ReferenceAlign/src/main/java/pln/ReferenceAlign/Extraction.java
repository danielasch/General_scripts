package pln.ReferenceAlign;

import java.util.LinkedList;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;

public class Extraction {
	
	private OWLOntology ontology;
	private LinkedList<Concept> list;
	
	public Extraction(OWLOntology ontology, int op) {
		setOntology(ontology);
		if(op == 1) extractDolce();
		else extract();
	}
	
	private void extractDolce() {
		list = new LinkedList<Concept>();
		for(OWLClass cls: ontology.getClassesInSignature()) {
				if(!cls.getIRI().toString().startsWith("http://www.loa-cnr.it/ontologies/WordNet/OWN#")) {
				Concept concept = new Concept();
				concept.setCls(cls);
				concept.setIri(cls.getIRI());
				concept.setName(cls.getIRI().getFragment());
				list.add(concept);
			}
		}
	}

	private void extract() {
		list = new LinkedList<Concept>();
		for(OWLClass cls: ontology.getClassesInSignature()) {
			Concept concept = new Concept();
			concept.setCls(cls);
			concept.setIri(cls.getIRI());
			concept.setName(cls.getIRI().getFragment());
			list.add(concept);
		}
	}
	
	public Concept searchConcept(String concept) {
		for(Concept k : list) {
			if(k.getName().toLowerCase().equals(concept.toLowerCase())) {
				return k;
			}
		}
		return null;
	}

	public OWLOntology getOntology() {
		return ontology;
	}

	private void setOntology(OWLOntology ontology) {
		this.ontology = ontology;
	}
	
	public LinkedList<Concept> getConcepts(){
		return list;
	}
	
}
