package nl.iisg.ids05;

import java.util.ArrayList;
import java.util.List;

import nl.iisg.idscontext.ContextElement;

 
public class Indiv {

	int Id_I;
	
	ArrayList<String> types  = new ArrayList<String>();
	ArrayList<String> values = new ArrayList<String>();	
	
	List<ContextElement> contexts = new ArrayList<ContextElement>();  // Contexts of Indiv
	List<Indiv>          indivs   = new ArrayList<Indiv>();           // Related Indivs of Indiv
	
	public int getId_I() {
		return Id_I;
	}
	public void setId_I(int id_I) {
		Id_I = id_I;
	}
	public ArrayList<String> getTypes() {
		return types;
	}
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	public ArrayList<String> getValues() {
		return values;
	}
	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
	public List<ContextElement> getContexts() {
		return contexts;
	}
	public void setContexts(List<ContextElement> contexts) {
		this.contexts = contexts;
	}
	public List<Indiv> getIndivs() {
		return indivs;
	}
	public void setIndivs(List<Indiv> indivs) {
		this.indivs = indivs;
	}

	
	
	
}
