package fg;
import util.graph.*;
import nasm.*;
import util.intset.*;
import java.io.*;
import java.util.*;

public class FgSolution {
    int iterNum;
    public Nasm nasm;
    Fg fg;
    public Map< NasmInst, IntSet> use;
    public Map< NasmInst, IntSet> def;
    public Map< NasmInst, IntSet> in;
    public Map< NasmInst, IntSet> out;
    
    public FgSolution(Nasm nasm, Fg fg){
		this.nasm = nasm;
		this.fg = fg;
		this.use = new HashMap< NasmInst, IntSet>();
		this.def = new HashMap< NasmInst, IntSet>();
		this.in =  new HashMap< NasmInst, IntSet>();
		this.out = new HashMap< NasmInst, IntSet>();

		// Pour chaque instruction nasm, on initialise les ensembles use et def
		for(NasmInst nasmInst : nasm.listeInst) {
			createUseDefSet(nasmInst);
		}

		iterNum = -1;
		createInOutSet();
    }

    private void createUseDefSet(NasmInst nasmInst) {
		// On initialize les IntSet
		IntSet defSet = new IntSet(10);
		IntSet useSet = new IntSet(10);

		// Si l'instruction possède un registre source
		if(nasmInst.source != null) {
			// Si le registre source est utilisé et que c'est un registre général
			if (nasmInst.srcUse && nasmInst.source.isGeneralRegister()) {
				NasmRegister source = (NasmRegister) nasmInst.source;
				// Si le registre n'est pas EAX, EBX, ECX ou EDX, on additionne 4 à son numéro de registre
				if(source.color == Nasm.REG_UNK) {
					useSet.add(source.val + 4);
				}
				else {
					useSet.add(source.val);
				}
			}

			// Si le registre source est une adresse
			if (nasmInst.source.getClass().equals(NasmAddress.class)) {
				NasmAddress address = (NasmAddress) nasmInst.source;

				// Si la base est un registre général
				if (address.base != null && address.base.isGeneralRegister()) {
					// Alors il est utilisé
					NasmRegister source = (NasmRegister) address.base;
					// Si le registre n'est pas EAX, EBX, ECX ou EDX, on additionne 4 à son numéro de registre
					if(source.color == Nasm.REG_UNK) {
						useSet.add(source.val + 4);
					}
					else {
						useSet.add(source.val);
					}
				}

				// Si l'offset est un registre général
				if (address.offset != null && address.offset.isGeneralRegister()) {
					// Alors il est utilisé
					NasmRegister source = (NasmRegister) address.offset;
					// Si le registre n'est pas EAX, EBX, ECX ou EDX, on additionne 4 à son numéro de registre
					if(source.color == Nasm.REG_UNK) {
						useSet.add(source.val + 4);
					}
					else {
						useSet.add(source.val);
					}
				}
			}
		}

		// Si l'instruction possède un registre destination
		if(nasmInst.destination != null) {
			// Si le registre destination est utilisé
			if (nasmInst.destUse) {
				// Si le registre destination est un registre général
				if (nasmInst.destination.isGeneralRegister()) {
					NasmRegister destination = (NasmRegister) nasmInst.destination;
					// Si le registre n'est pas EAX, EBX, ECX ou EDX, on additionne 4 à son numéro de registre
					if(destination.color == Nasm.REG_UNK) {
						useSet.add(destination.val + 4);
					}
					else {
						useSet.add(destination.val);
					}
				}
			}

			// Si le registre destination est défini
			if (nasmInst.destDef) {
				// Si le registre destination est un registre général
				if (nasmInst.destination.isGeneralRegister()) {
					NasmRegister destination = (NasmRegister) nasmInst.destination;
					// Si le registre n'est pas EAX, EBX, ECX ou EDX, on additionne 4 à son numéro de registre
					if(destination.color == Nasm.REG_UNK) {
						defSet.add(destination.val + 4);
					}
					else {
						defSet.add(destination.val);
					}
				}
			}
		}

		// On ajoute les IntSet à la hashmap
		use.put(nasmInst, useSet);
		def.put(nasmInst, defSet);
	}

	private void createInOutSet() {
    	// On intialize les ensembles in2 et out2 qui correspondent aux ensembles in' et ou' de l'algorithme
		Map< NasmInst, IntSet> in2 = new HashMap< NasmInst, IntSet>();
		Map< NasmInst, IntSet> out2 = new HashMap< NasmInst, IntSet>();

		// Pour chaque instruction nasm, c'est-à-dire chaque sommet dans le graph
		for(NasmInst nasmInst : nasm.listeInst) {
			// On initialize les ensemble in, in2, out et out2 avec des ensembles vide
			in.put(nasmInst, new IntSet(10));
			in2.put(nasmInst, new IntSet(10));
			out.put(nasmInst, new IntSet(10));
			out2.put(nasmInst, new IntSet(10));
		}

		// La variable test utilisée pour la condition du while
		boolean test;

		// Faire
		do {
			// Nouvelle itération (si c'est la première itération, on passe de -1 à 0)
			iterNum++;

			// Pour chaque instruction, sommet
			for(NasmInst s : nasm.listeInst) {
				// in2(s) = in(s)
				in2.replace(s, in.get(s));
				// out2(s) = out(s)
				out2.replace(s, out.get(s));

				// in(s) = use(s) U (out(s) − def(s))
				IntSet sIn = use.get(s).copy();
				IntSet sOut = out.get(s).copy();
				sOut.minus(def.get(s));
				sIn.union(sOut);
				in.replace(s, sIn);

				// Pour chaque prédécesseur n
				// out(n) = out(n) U in(s)
				// On récupère le noeud dans le graph correspondant à notre instruction
				Node sNode = fg.inst2Node.get(s);
				// On récupère ses prédécesseurs
				NodeList nodeList = sNode.pred();
				// On parcours ses prédécesseurs jusqu'à les avoir tous vu
				while(nodeList != null) {
					// On récupère la tête de la liste des prédécesseurs
					Node predecessorNode = nodeList.head;
					// On récupère l'instruction liée à predecessorNode
					NasmInst predecessorInst = fg.node2Inst.get(predecessorNode);

					// out(predecessor) = out(predecessor) U in(s)
					out.replace(predecessorInst, out.get(predecessorInst).union(in.get(s)));

					// On continue de parcourir les successeurs
					nodeList = nodeList.tail;
				}
			}

			// Vérification de la condition du while
			// On part du principe que le test est vrai
			test = true;
			// Si, pour tout s, in2(s) != in(s) et out2(s) != out(s), alors on recommence
			for(NasmInst s : nasm.listeInst) {
				if(!(in.get(s).equal(in2.get(s)))) test = false;
				if(!(out.get(s).equal(out2.get(s)))) test = false;
				if(!test) break;
			}
		}
		// Tant que le test n'est pas vrai
		while(!test);

	}
    
    public void affiche(String baseFileName){
		String fileName;
		PrintStream out = System.out;

		if (baseFileName != null){
			try {
			baseFileName = baseFileName;
			fileName = baseFileName + ".fgs";
			out = new PrintStream(fileName);
			}

			catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			}
		}

		out.println("iter num = " + iterNum);
		for(NasmInst nasmInst : this.nasm.listeInst){
			out.println("use = "+ this.use.get(nasmInst) + " def = "+ this.def.get(nasmInst) + "\tin = " + this.in.get(nasmInst) + "\t \tout = " + this.out.get(nasmInst) + "\t \t" + nasmInst);
		}
    }
}

    
