package pln.ReferenceAlign;

import java.util.StringTokenizer;

public class Matching {
	
	private String topConcept;
	private String domainConcept;
	
	public Matching(String topConcept, String domainConcept) {
		this.topConcept = topConcept;
		this.domainConcept = domainConcept;
		setDomainConcept();
	}
	
	public String[] getTopConcepts(){
		int i = 0;
		
		String [] topNames = new String[3];
		
		StringTokenizer st = new StringTokenizer(topConcept, ", ");
		
		while(st.hasMoreTokens()) {
			topNames[i] = st.nextToken();
			i++;
		}
		
		return topNames;
	}
	
	public void setDomainConcept() {
		int index = domainConcept.indexOf("#");
		domainConcept = domainConcept.substring(index+1);
	}
	
	public String getDomainConcept() {
		return domainConcept;
	}

}
