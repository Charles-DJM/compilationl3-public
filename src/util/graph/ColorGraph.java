package util.graph;

import util.graph.*;
import util.intset.*;
import java.util.*;
import java.io.*;

public class ColorGraph {
    public  Graph          G; // le graphe d'interférence
    public  int            R; // Le nombre de sommets
    public  int            K; // Le nombre de couleurs
    private Stack<Integer> pile;
    public  IntSet         enleves; // Les sommets enlevés
    public  IntSet         deborde; // Les sommets qui débordent
    public  int[]          couleur; // Le tableau couleurs
    public  Node[]         int2Node;
    static  int            NOCOLOR = -1;

    public ColorGraph(Graph G, int K, int[] phi){
        this.G       = G;
        this.K       = K;
        pile         = new Stack<Integer>();
        R            = G.nodeCount();
        couleur      = new int[R];
        enleves      = new IntSet(R);
        deborde      = new IntSet(R);
        int2Node     = G.nodeArray();
        for(int v=0; v < R; v++){
            int preColor = phi[v];
            if(preColor >= 0 && preColor < K)
            couleur[v] = phi[v];
            else
            couleur[v] = NOCOLOR;
        }

        // On exécute l'algorithme de simplification
        simplification();
        // On exécute l'algorithme de sélection
        selection();
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* associe une couleur à tous les sommets se trouvant dans la pile */
    /*-------------------------------------------------------------------------------------------------------------*/
    
    public void selection() {
        // while taille(pille != 0) do
        while(pile.size() != 0) {
            // s <- depile
            int s = pile.pop();
            enleves.remove(s);

            // Si l'élément n'a pas de couleur
            if(couleur[s] == NOCOLOR) {
                // On récupère les couleurs de ses voisins
                IntSet neighborColor = couleursVoisins(s);

                // S'il reste une couleur disponible
                if (neighborColor.getNbElements() != K) {
                    // color[s] <- choisisCouleur(C)
                    couleur[s] = choisisCouleur(neighborColor);
                }
            }
        }
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /* récupère les couleurs des voisins de s */
    /*-------------------------------------------------------------------------------------------------------------*/
    
    public IntSet couleursVoisins(int s) {
        // On initialize l'ensemble des couleurs des voisins avec un ensemble vide
        // Il peut y avoir au maximum K couleurs
        IntSet neighborColor = new IntSet(K);
        // On récupère le sommet lié à l'entier s
        Node node = int2Node[s];
        // On récupère la liste de prédecesseurs
        NodeList nodeList = node.pred();

        // Tant qu'il existe des prédecesseurs, c'est-à-dire des voisins de s non visités
        while(nodeList != null) {
            // Si le voisin n'a pas été enlevé
            if(!(enleves.isMember(nodeList.head.mykey))) {
                // On ajoute la couleur du voisin
                neighborColor.add(couleur[nodeList.head.mykey]);
                nodeList = nodeList.tail;
            }
        }

        return neighborColor;
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /* recherche une couleur absente de colorSet */
    /*-------------------------------------------------------------------------------------------------------------*/
    
    public int choisisCouleur(IntSet colorSet) {
        // On parcours toutes les couleurs
        for(int i = 0; i < K; i++) {
            // On regarde si la couleur est dans colorSet
            if(!(colorSet.isMember(i))) return i;
        }

        return -1;
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /* calcule le nombre de voisins du sommet s */
    /*-------------------------------------------------------------------------------------------------------------*/
    
    public int nbVoisins(int s) {
        // On initialize le nombre de voisins à 0
        int nb = 0;
        // On récupère le sommet lié à l'entier s
        Node node = int2Node[s];
        // On récupère la liste de prédecesseurs
        NodeList nodeList = node.pred();

        // Tant qu'il existe des prédecesseurs, c'est-à-dire des voisins de s non visités
        while(nodeList != null) {
            // Si le voisin n'a pas été enlevé
            if(!(enleves.isMember(nodeList.head.mykey))) {
                nb++;
            }
            nodeList = nodeList.tail;
        }

        return nb;
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* simplifie le graphe d'interférence g                                                                        */
    /* la simplification consiste à enlever du graphe les temporaires qui ont moins de k voisins                   */
    /* et à les mettre dans une pile                                                                               */
    /* à la fin du processus, le graphe peut ne pas être vide, il s'agit des temporaires qui ont au moins k voisin */
    /*-------------------------------------------------------------------------------------------------------------*/

    public int simplification() {
        // modif <- vrai
        boolean modif = true;

        // while taille(pile) != R et modif = vrai do
        while(pile.size() != R && modif) {
            // modif <- faux
            modif = false;
            // Pour chaque sommet
            for(int s = 0; s < R; s++) {
                // S'il n'est pas déjà dans la pile
                if(!(pile.contains(s))) {
                    // Si le nombre de voisins est plus petit que le nombre de couleurs
                    if (nbVoisins(s) < K) {
                        // empile(s)
                        pile.push(s);
                        // On ajoute le sommet aux sommets enlevés
                        enleves.add(s);
                        // modif <- vrai
                        modif = true;
                    }
                    // Sinon, on a besoin d'un débordement
                    else {
                        debordement();
                    }
                }
            }
        }

        return 1;
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/
    
    public void debordement() {
        // while taille(pile) != R do
        while(pile.size() != R) {
            // On parcours la pile
            int s = 0;
            for(; s < R; s++) {
                // On prend un sommet qui n'est pas déjà dans la pile
                if(!pile.contains(s)) break;
            }

            // empile(s)
            pile.push(s);
            // On ajoute le sommet aux sommets enlevés
            enleves.add(s);
            // On ajoute le sommet aux sommets débordés
            deborde.add(s);
            simplification();
        }
    }


    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/

    public void coloration()
    {
	this.simplification();
	this.debordement();
	this.selection();
    }

    void affiche()
    {
	System.out.println("vertex\tcolor");
	for(int i = 0; i < R; i++){
	    System.out.println(i + "\t" + couleur[i]);
	}
    }
    
    

}
