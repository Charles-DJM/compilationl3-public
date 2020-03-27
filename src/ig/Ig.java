package ig;

import fg.*;
import nasm.*;
import util.graph.*;
import util.intset.*;

import java.awt.*;
import java.util.*;
import java.io.*;

public class Ig {
    public Graph graph; // Le graphe d'interférence
    public FgSolution fgs; // Les ensembles in et out
    public int regNb; // Le nombre de registres fictifs
    public Nasm nasm; // Le pré-nasm
    public Node int2Node[]; // Accéder à un sommet à partir de son identifiant
	public ColorGraph colorGraph; // Le graphe colorié
	public int couleur[];
    
    public Ig(FgSolution fgs){
		this.fgs = fgs;
		this.graph = new Graph();
		this.nasm = fgs.nasm;
		this.regNb = this.nasm.getTempCounter();
		this.int2Node = new Node[regNb];
		this.construction();
		couleur = getPrecoloredTemporaries();

		colorGraph = new ColorGraph(graph, regNb, couleur);
		couleur = colorGraph.couleur;
		allocateRegisters();
    }

    // Crée le graphe d’interférence à partir du graphe d’analyse
    public void construction() {
    	// Création des sommets du graph, égal au nombre de registres fictifs
    	for(int i = 0; i < regNb; i++) {
    		int2Node[i] = graph.newNode();
		}

    	// Pour chaque sommet du graph d'analyse, c'est-à-dire pour chaque instruction nasm
		for(NasmInst s : nasm.listeInst) {
			// Récupérer in(s)
			IntSet inS = fgs.in.get(s);
			// Récupérer out(s)
			IntSet outS = fgs.out.get(s);

			// Pour chaque registre
			for(int i = 0; i < regNb; i++) {
				// Si le registre i est dans in(s)
				if(inS.isMember(i)) {
					// Parcourir les registres restants
					for(int j = i + 1; j < regNb; j++) {
						// Si le registre j est également dans in(s)
						if(inS.isMember(j)) {
							// On créer un arc entre i et j et un arc entre j et i
							// Puisque les arcs n'ont pas de directions
							// addEdge vérifie et évite les duplicats
							graph.addNOEdge(int2Node[i], int2Node[j]);
						}
					}
				}

				// Si le registre i est dans out(s)
				if(outS.isMember(i)) {
					// Parcourir les registres restants
					for(int j = i + 1; j < regNb; j++) {
						// Si le registre j est également dans out(s)
						if(outS.isMember(j)) {
							// On créer un arc entre i et j et un arc entre j et i
							// Puisque les arcs n'ont pas de directions
							// addEdge vérifie et évite les duplicats
							graph.addEdge(int2Node[i], int2Node[j]);
							graph.addEdge(int2Node[j], int2Node[i]);
						}
					}
				}
			}
		}
    }

    public int[] getPrecoloredTemporaries() {
    	int[] couleur = new int[regNb];

    	// Pour chaque instruction nasm
		for(NasmInst nasmInst : nasm.listeInst) {
			// Si le registre destination existe et qu'il est un registre général
			if(nasmInst.destination != null && nasmInst.destination.isGeneralRegister()) {
				// On récupère le registre
				NasmRegister register = ((NasmRegister) nasmInst.destination);
				// S'il est précoloré
				if(register.color != Nasm.REG_UNK) {
					couleur[register.val] = register.color;
				}
			}

			// Si le registre source existe et qu'il est un registre général
			if(nasmInst.source != null && nasmInst.source.isGeneralRegister()) {
				// On récupère le registre
				NasmRegister register = ((NasmRegister) nasmInst.source);
				// S'il est précoloré
				if(register.color != Nasm.REG_UNK) {
					couleur[register.val] = register.color;
				}
			}
		}

    	return couleur;
    }


    public void allocateRegisters(){
		// Pour chaque instruction nasm
		for(NasmInst nasmInst : nasm.listeInst) {
			// Si le registre destination existe et qu'il est un registre général
			if(nasmInst.destination != null && nasmInst.destination.isGeneralRegister()) {
				// On récupère le registre
				NasmRegister register = ((NasmRegister) nasmInst.destination);
				// S'il n'est pas coloré
				if(register.color == Nasm.REG_UNK) {
					// On le colorie
					register.colorRegister(couleur[register.val]);
				}
			}

			// Si le registre source existe et qu'il est un registre général
			if(nasmInst.source != null && nasmInst.source.isGeneralRegister()) {
				// On récupère le registre
				NasmRegister register = ((NasmRegister) nasmInst.source);
				// S'il n'est pas coloré
				if(register.color == Nasm.REG_UNK) {
					// On le colorie
					register.colorRegister(couleur[register.val]);
				}
			}
		}
    }


    public void affiche(String baseFileName){
	String fileName;
	PrintStream out = System.out;
	
	if (baseFileName != null){
	    try {
		baseFileName = baseFileName;
		fileName = baseFileName + ".ig";
		out = new PrintStream(fileName);
	    }
	    
	    catch (IOException e) {
		System.err.println("Error: " + e.getMessage());
	    }
	}
	
	for(int i = 0; i < regNb; i++){
	    Node n = this.int2Node[i];
	    out.print(n + " : ( ");
	    for(NodeList q=n.succ(); q!=null; q=q.tail) {
		out.print(q.head.toString());
		out.print(" ");
	    }
	    out.println(")");
	}
    }
}
	    
    

    
    
