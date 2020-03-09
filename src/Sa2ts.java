import sa.*;
import ts.Ts;

public class Sa2ts extends SaDepthFirstVisitor <Void> {

    /* foncName est le nom de la fonction courrante. Si foncName est null, alors on se situe dans le contexte global. Sinon, on se situe
    dans un contexte local.

       On peut accéder à la table de symbole location d'une fonction à partir de son nom (foncName).

       Lorsqu'une déclaration de variable concerne les paramètres d'une fonction, la valeur de foncParam est true. Sinon, elle est false. */

    Ts table;
    String foncName;
    boolean foncParam;

    public Sa2ts(SaNode saRoot) {
        table = new Ts();
        foncName = null;
        foncParam = false;
        saRoot.accept(this);
    }

    public Void visit(SaDecVar node){
        if (foncName == null){
        // Global context
            if (table.getVar(node.getNom()) == null){
                table.addVar(node.getNom(), node.tsItem == null ? 1 : node.tsItem.getTaille());
            }
            else {
                System.out.println("/!\\ ERROR : Variable not declared.");
                System.exit(0);
            }
        }

        else {
        // Local context or function parameter
            if (foncParam){
                if(table.getTableLocale(foncName).getVar(node.getNom()) == null){
                    table.getTableLocale(foncName).addParam(node.getNom());
                }
                else {
                    System.out.println("/!\\ ERROR : Parameter not declared.");
                    System.exit(0);
                }
            }
            else {
                if(table.getTableLocale(foncName).getVar(node.getNom()) == null){
                    table.getTableLocale(foncName).addVar(node.getNom(), node.tsItem == null ? 1 : node.tsItem.getTaille());
                }
                else {
                    System.out.println("/!\\ ERROR : Variable not declared");
                    System.exit(0);
                }
            }
        }
        return null;
    }

    public Void visit(SaDecTab node){
        if (foncName == null){
            if (table.getVar(node.getNom()) == null) table.addVar(node.getNom(), node.getTaille());
            else {
                System.out.println("/!\\ ERROR : Variable not declared.");
                System.exit(0);
            }
        }
        else {
            System.out.println("/!\\ ERROR : Array must be declared in a global context.");
            System.exit(0);
        }
        return null;
    }

    public Void visit(SaDecFonc node){
        Ts newTs = new Ts();

        if (foncName != null){
            System.out.println("/!\\ ERROR : Function declared in another function.");
            System.exit(0);
        }

        if (table.getFct(node.getNom()) != null){
            System.out.println("/!\\ ERROR : Function not declared.");
            System.exit(0);
        }
        else
            table.addFct(node.getNom(), node.getParametres() == null ? 0 : node.getParametres().length(), newTs, node);

        foncName = node.getNom();

        if (node.getParametres() != null){
            foncParam = true;
            node.getParametres().accept(this);
        }

        if (node.getVariable() != null){
            node.getVariable().accept(this);
        }

        foncParam = false;
        node.getCorps().accept(this);

        foncName = null;

        return null;
    }


    public Void visit(SaVarSimple node){
        if (foncName != null && table.getTableLocale(foncName).getVar(node.getNom()) == null && table.getVar(node.getNom()) == null) {
            System.out.println("/!\\ ERROR : Variable not declared.");
            System.exit(0);
        }
        if (table.getVar(node.getNom()) == null && foncName == null){
            System.out.println("/!\\ ERROR : Variable not declared.");
            System.exit(0);
        }
        return null;
    }

    public Void visit(SaVarIndicee node){
        if (table.getVar(node.getNom()).getTaille() == 1){
            System.out.println("/!\\ ERROR : Indexed variable.");
            System.exit(0);
        }
        return null;
    }

    public Void visit(SaAppel node) {
        if (table.getFct(node.getNom()) == null){
            System.out.println("/!\\ ERROR : Function not declared.");
            System.exit(0);
        }

        if (node.getArguments() != null){

            if (table.getFct(node.getNom()).getNbArgs() < node.getArguments().length()){
                System.out.println("/!\\ ERROR : Too many arguments.");
                System.exit(0);
            }
            if (table.getFct(node.getNom()).getNbArgs() > node.getArguments().length()){
                System.out.println("/!\\ ERROR : Not enough arguments.");
                System.exit(0);
            }
        }
        return null;
    }

    public Ts getTableGlobale() { return table; }

}
