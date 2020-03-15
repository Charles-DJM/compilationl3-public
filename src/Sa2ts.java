import sa.*;
import ts.Ts;
import ts.TsItemVar;

public class Sa2ts extends SaDepthFirstVisitor <Void> {

    // foncName est le nom de la fonction courrante. Si foncName est null, alors on se situe dans le contexte global. Sinon, on se situe
    // dans un contexte local.

    // On peut accéder à la table de symbole locale d'une fonction à partir de son nom (foncName).

    // Lorsqu'une déclaration de variable concerne les paramètres d'une fonction, la valeur de foncParam est true. Sinon, elle est false.

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
        // If the context is global
        if (foncName == null){
            // If the variable was not yet declared, we declare it
            if(table.getVar(node.getNom()) == null) {
                node.tsItem = table.addVar(node.getNom(), 1);
            }
            // Otherwise, the variable was already declared in this scope
            else {
                System.out.println("/!\\ ERROR : The variable is already declared in this scope.");
                System.exit(0);
            }
        }

        else {
        // Otherwise, the context is local
            // If it's a fonction parameter
            if (foncParam){
                // If the variable was not yet declared, we declare it
                if(table.getTableLocale(foncName).getVar(node.getNom()) == null) {
                    node.tsItem = table.getTableLocale(foncName).addParam(node.getNom());
                }
                // Otherwise, the variable was already declared in this scope
                else {
                    System.out.println("/!\\ ERROR : The variable is already declared in this scope.");
                    System.exit(0);
                }

            }
            // Otherwise, it's a local variable
            else {
                // If the variable was not yet declared, we declare it
                if (table.getTableLocale(foncName).getVar(node.getNom()) == null) {
                    node.tsItem = table.getTableLocale(foncName).addVar(node.getNom(), 1);
                }
                // Otherwise, the variable was already declared in this scope
                else {
                    System.out.println("/!\\ ERROR : The variable is already declared in this scope.");
                    System.exit(0);
                }
            }
        }

        return null;
    }

    public Void visit(SaDecTab node){
        // If the context is global
        if (foncName == null){
            // If the variable was not yet declared, we declare it
            if(table.getVar(node.getNom()) == null) {
                node.tsItem = table.addVar(node.getNom(), node.getTaille());
            }
            // Otherwise, the variable was already declared in this scope
            else {
                System.out.println("/!\\ ERROR : The variable is already declared in this scope.");
                System.exit(0);
            }
        }

        else {
            // Otherwise, the context is local
            System.out.println("/!\\ ERROR : An array must be declared in a global context.");
            System.exit(0);
        }

        return null;
    }

    public Void visit(SaDecFonc node){
        Ts localTable = new Ts();

        // If the function is declared locally
        if(foncName != null) {
            System.out.println("/!\\ ERROR : The function is being declared locally.");
            System.exit(0);
        }

        // If the function hasn't been declared yet
        if(table.getFct(node.getNom()) == null) {
            foncName = node.getNom();

            // If the function has parameters
            if (node.getParametres() != null){
                node.tsItem = table.addFct(foncName, node.getParametres().length(), localTable, node);
                foncParam = true;
                node.getParametres().accept(this);
                foncParam = false;
            }

            // Otherwise
            else {
                node.tsItem = table.addFct(foncName, 0, localTable, node);
            }

            // If the function has local variables
            if (node.getVariable() != null){
                node.getVariable().accept(this);
            }
        }

        // Otherwise, it has been declared
        else {
            System.out.println("/!\\ ERROR : The function is already declared.");
            System.exit(0);
        }

        node.getCorps().accept(this);

        // Reset foncName
        foncName = null;

        return null;
    }


    public Void visit(SaVarSimple node){
        // If the context is local
        if(foncName != null) {
            // If the variable is neither global nor local
            if (table.getTableLocale(foncName).getVar(node.getNom()) == null && table.getVar(node.getNom()) == null) {
                System.out.println("/!\\ ERROR : The variable has not been declared.");
                System.exit(0);
            }

            // Otherwise, the variable exists
            else {
                // If the variable was defined locally
                TsItemVar variable = table.getTableLocale(foncName).getVar(node.getNom());
                if (variable != null) {
                    node.tsItem = variable;
                }
                // Otherwise, the variable was defined globally
                else {
                    node.tsItem = table.getVar(node.getNom());
                }
            }
        }

        // If the context is global
        else {
            // If the variable does not exist
            if (table.getVar(node.getNom()) == null) {
                System.out.println("/!\\ ERROR : The variable is not declared.");
                System.exit(0);
            }

            // Otherwise, the variable exists
            else {
                node.tsItem = table.getVar(node.getNom());
            }
        }

        return null;
    }

    public Void visit(SaVarIndicee node){
        // The context doesn't matter has tables are global
        // If the variable has not been declared
        if (table.getVar(node.getNom()) == null) {
            System.out.println("/!\\ ERROR : The variable has not been declared.");
            System.exit(0);
        }

        // Otherwise, the variable exists
        else {
            // The variable is defined globally
            TsItemVar variable = table.getVar(node.getNom());
            if (variable != null) {
                // If the variable is a table
                if(variable.getTaille() > 1) {
                    node.tsItem = variable;
                }

                // Otherwise, it's not a table
                else {
                    System.out.println("/!\\ ERROR : The variable is not a table.");
                    System.exit(0);
                }
            }
        }

        return null;
    }

    public Void visit(SaAppel node) {

        // Check if there's a function called "main" with no arguments
        if(table.getFct("main") == null || table.getFct("main").nbArgs != 0) {
            System.out.println("/!\\ ERROR : The 'main' function does not exist or it has arguments.");
            System.exit(0);
        }

        // If we're calling a function that has not already been declared
        if(table.getFct(node.getNom()) == null) {
            System.out.println("/!\\ ERROR : The function has not been declared.");
            System.exit(0);
        }

        // If we're calling a function that has arguments
        if (node.getArguments() != null){
            // If the number of arguments is different between the call and the function
            if (table.getFct(node.getNom()).getNbArgs() != node.getArguments().length()){
                System.out.println("/!\\ ERROR : The number of arguments in the call does not equal the number of arguments required by the function.");
                System.exit(0);
            }

            // Otherwise
            else {
                node.getArguments().accept(this);
            }
        }

        // Otherwise, if we haven't exited the method yet, the function call is correct and the function has no arguments
        node.tsItem = table.getFct(node.getNom());

        return null;
    }

    public Ts getTableGlobale() { return table; }

}
