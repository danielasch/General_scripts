package pln.ReferenceAlign;

import java.io.File;

public class DomainSearcher {
	
	private Extraction domainConcepts;
	
	private String domainName;
	
	private Ontology domainOntology;
	
	public DomainSearcher(String domainName) {
		
		this.domainName = domainName;
	}
	
	public File searchDomain() {
		
    	File file = new File("resources/" + domainName + ".owl");
    	
    	return file;
    	
    }

	public void extract() {
		
		domainOntology = new Ontology(searchDomain().getPath());
		
		domainConcepts = new Extraction(domainOntology.getOntology(), 0);
	
	}
	
	public String getDomainName() {
		return domainName;
	}
	
	public Ontology getDomainOntology() {
		return domainOntology;
	}
	
	public Extraction getDomainConcepts() {
		/*
		for(Concept k : domainConcepts.getConcepts()) {
			System.out.println(k.getName());
		}
		*/
		return domainConcepts;
	}
	

}
