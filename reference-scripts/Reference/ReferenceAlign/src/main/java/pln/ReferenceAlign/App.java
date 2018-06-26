package pln.ReferenceAlign;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class App {
	
	public static Ontology sumoOntology;
	public static Ontology dolceOntology;
	public static Extraction SUMO;
	public static Extraction DOLCE;

	public static void main( String[] args ) throws IOException {

    	sumoOntology = new Ontology("resources/SUMO.owl");
    	SUMO = new Extraction(sumoOntology.getOntology(), 0);
		
    	dolceOntology = new Ontology("resources/wnNounsyn_v7.owl");
    	DOLCE = new Extraction(dolceOntology.getOntology(), 1);
		
		File mapping = new File("resources/table_set.txt");

		BufferedReader br = new BufferedReader(new FileReader(mapping));
		String currentLine;
		
		String actualDomain = null;
		DomainSearcher ds = null;
		OutFile of1 = null;
		OutFile of2 = null;
		Extraction domainConcepts = null;
		
		while ((currentLine = br.readLine()) != null) {
			
			System.out.println(" ");
			System.out.println("*****NEW LINE*****");
			
			System.out.println(currentLine);
			
			String [] aux = currentLine.split("] ");

			String domain = aux[0];
			domain = manipulate(domain);
			System.out.println("Domain read: " + domain);

			String dolce = aux[1];
			dolce = manipulate(dolce);
			System.out.println("DOLCE read: " + dolce);
			
			String sumo = aux[2];
			sumo = manipulate(sumo);
			System.out.println("SUMO read: " + sumo);
			
			String domainName = domain.substring(0, domain.indexOf("#"));

			if(actualDomain == null){

                actualDomain = domainName;

                System.out.println("Novo domínio: " + actualDomain);

                ds = new DomainSearcher(domainName);

                ds.extract();

                of1 = new OutFile(domainName, "sumo");

                of2 = new OutFile(domainName, "dolce");

                domainConcepts = ds.getDomainConcepts();
                System.out.println("*******************");
                start(ds, of1, of2, dolce, sumo, domain, domainName, domainConcepts, true);

            }

			else if(!actualDomain.equals(domainName)) {

				of1.closeArch();
				of2.closeArch();

				actualDomain = domainName;
				
				System.out.println("Novo domínio: " + actualDomain);

				ds = new DomainSearcher(domainName);
				
				ds.extract();
				
				of1 = new OutFile(domainName, "sumo");
				
				of2 = new OutFile(domainName, "dolce");
				
				domainConcepts = ds.getDomainConcepts();
				System.out.println("*******************");
				start(ds, of1, of2, dolce, sumo, domain, domainName, domainConcepts, true);
			}

			else {
				System.out.println("*******************");
				start(ds, of1, of2, dolce, sumo, domain, domainName, domainConcepts, false);
			}
			
		}
		
	}
	
	public static void start(DomainSearcher ds, OutFile of1, OutFile of2, String dolce, String sumo, String domain
			, String domainName, Extraction domainConcepts, Boolean needSetOntologies) throws IOException{
		
		Matching m1 = new Matching(sumo, domain);
		
		Matching m2 = new Matching(dolce, domain);
		
		System.out.println("Domain concept :" + m1.getDomainConcept());
		
		Concept matchedDomainConcept = domainConcepts.searchConcept(m1.getDomainConcept());
		
		System.out.println("Matched domain concept: " + matchedDomainConcept.getName());
		
		System.out.println("Top sumo concepts: ");
		
		String [] topSumoConcepts = m1.getTopConcepts();
		
		for(int i = 0; i < topSumoConcepts.length; i++) {
			if(topSumoConcepts[i] == null)break;
			System.out.print("[" + topSumoConcepts[i] +  "] ");
		}
		
		System.out.println(" ");
		
		System.out.println("Top dolce concepts: ");
		
		String [] topDolceConcepts = m2.getTopConcepts();
		
		for(int i = 0; i < topDolceConcepts.length; i++) {
			if(topDolceConcepts[i] == null)break;
			System.out.print("[" + topDolceConcepts[i] +  "] ");
		}
		
		System.out.println(" ");
		
		if(needSetOntologies) {
		
			of1.setTop(sumoOntology.getName(), sumoOntology.getIRI());
		
			of1.setDomain(domainName, ds.getDomainOntology().getIRI());
			
			of2.setTop(dolceOntology.getName(), sumoOntology.getIRI());
			
			of2.setDomain(domainName, ds.getDomainOntology().getIRI());
		}
		
		map(topSumoConcepts, of1 , SUMO, matchedDomainConcept);
		
		map(topDolceConcepts, of2, DOLCE, matchedDomainConcept);
		
	}


	public static String manipulate(String str) {
		if(str.length()==2) str = "null";
		if(str.contains("[")) str = str.substring(1);
		if(str.contains("]")) str = str.substring(0, str.length()-1); 
		return str;
	}
	
	public static void map(String[]topConcepts, OutFile of, Extraction sumo, Concept matchedDomainConcept) throws IOException {
		
		for(int i = 0; i < topConcepts.length; i++) {
			
			if(topConcepts[i] == null) return;
			
			else {
				
				Concept matchedTopConcept = sumo.searchConcept(topConcepts[i]);
				
				if(matchedTopConcept != null) {
					
					of.map(matchedTopConcept.getIri(), matchedDomainConcept.getIri());
				}
			}
		}
	}
}
