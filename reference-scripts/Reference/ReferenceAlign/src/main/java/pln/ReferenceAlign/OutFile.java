package pln.ReferenceAlign;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.semanticweb.owlapi.model.IRI;

public class OutFile {
	
	BufferedWriter writer;
	private File file;
	private String domainName;
	private String topName;
	
	public OutFile(String domainName, String topName) throws IOException {
		this.domainName = domainName; this.topName = topName;
		setFile();
		writer = new BufferedWriter (new FileWriter(file));
		setHeading();
	}
	
	public void setFile() {
		File file = new File("reference_align_" + domainName + "_"+ topName + ".rdf");
		this.file = file;
	}
	
	public void setHeading() throws IOException {
		writer.write(
			"<?xml version='1.0' encoding='utf-8' standalone='no'?>" + "\n" +
			"\t" + "<rdf:RDF xmlns='http://knowledgeweb.semanticweb.org/heterogeneity/alignment#'" + "\n" +
			"\t" + "\t" + "xmlns:rdf='http://www.w3.org/1999/02/22-rdf-syntax-ns#'" + "\n" +
			"\t" + "\t" + "xmlns:xsd='http://www.w3.org/2001/XMLSchema#'" + "\n" +
			"\t" + "\t" +"xmlns:align='http://knowledgeweb.semanticweb.org/heterogeneity/alignment#'>" + "\n" + 
			" " + "\n" );
		writer.write(
			"\t" + "\t" + "<Alignment>" + "\n" +
			"\t" + "\t" + "\t" + "<xml>yes</xml>" + "\n" +
			"\t" + "\t" + "\t" + "<level>0</level>"+ "\n" +
			"\t" + "\t" + "\t" + "<type>11</type>" + "\n" +
			" " + "\n");
	}
	
	public void setTop(String topName, IRI topIri) throws IOException {
		writer.write(
			"\t" + "\t" + "\t" + "<onto1>" + "\n" +
			"\t" + "\t" + "\t" + "\t" + "<Ontology rdf:about="+"'"+topIri.toString()+"'"+">" + "\n" +
			"\t" + "\t" + "\t" + "\t" + "\t" + "<location>file:/"+topName+"</location>" + "\n" + 
			"\t" + "\t" + "\t" + "\t" + "</Ontology>" + "\n" +
			"\t" + "\t" + "\t" + "</onto1>" + "\n" + 
			" " + "\n" );
		writer.flush();
	}
	
	public void setDomain(String domainName, IRI domainIri) throws IOException {
		writer.write(
			"\t" + "\t" + "\t" + "<onto2>" + "\n" +
			"\t" + "\t" + "\t" + "\t" + "<Ontology rdf:about="+"'"+domainIri.toString()+"'"+">" + "\n" +
			"\t" + "\t" + "\t" + "\t" + "\t" + "<location>file:/"+domainName+"</location>" + "\n" +
			"\t" + "\t" + "\t" + "\t" + "</Ontology>" + "\n" +
			"\t" + "\t" + "\t" + "</onto2>" + "\n" + 
			" " + "\n" );
		writer.flush();
	}
	
	public void map(IRI topConceptIri, IRI domainConceptIri) throws IOException {
		writer.write(
			"\t" + "\t" + "\t" + "<map>" + "\n" + 
			"\t" + "\t" + "\t" + "\t" + "<Cell>" + "\n" + 
			"\t" + "\t" + "\t" + "\t" + "\t" + "<entity1 rdf:resource="+"'"+topConceptIri+"'"+"/>" + "\n" +
			"\t" + "\t" + "\t" + "\t" + "\t" + "<entity2 rdf:resource="+"'"+domainConceptIri+"'"+"/>" + "\n" +
			"\t" + "\t" + "\t" + "\t" + "\t" + "<relation>&lt;</relation>" + "\n" + 
			"\t" + "\t" + "\t" + "\t" + "\t" + "<measure rdf:datatype='xds:float'>1.0</measure>" + "\n" + 
			"\t" + "\t" + "\t" + "\t" + "</Cell>" +  "\n" + 
			"\t" + "\t" + "\t" + "</map>" + "\n" + 
			" " + "\n");
		writer.flush();
	}
	
	public void closeArch() throws IOException {
		writer.write("\t" + "\t" + "</Alignment>" + "\n" + "");
		writer.write("\t" + "</rdf:RDF>" + "\n" + "");
		writer.close();
	}
	
	
}
