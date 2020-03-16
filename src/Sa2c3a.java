import c3a.*;
import sa.SaDepthFirstVisitor;
import sa.*;
import ts.Ts;
import ts.TsItemFct;

public class Sa2c3a extends SaDepthFirstVisitor <C3aOperand> {
    private C3a c3a;
    private Ts table;

    public Sa2c3a(SaNode root, Ts table) {
        c3a = new C3a();
        this.table = table;
        root.accept(this);
    }

    public C3aOperand visit(SaProg node) {
        node.getFonctions().accept(this);

        return null;
    }

    public C3aOperand visit(SaAppel node) {
        return appel(node, null);
    }

    // r = call function
    public C3aOperand appel(SaAppel node, C3aOperand result) {
        C3aFunction function = new C3aFunction(node.tsItem);
        if(node.getArguments() != null) {
            node.getArguments().accept(this);
        }
        c3a.ajouteInst(new C3aInstCall(function, result, ""));

        return function;
    }

    // Déclarations

    public C3aOperand visit(SaDecFonc node) {
        TsItemFct function = table.getFct(node.getNom());
        c3a.ajouteInst(new C3aInstFBegin(function, "entree fonction"));

        node.getCorps().accept(this);

        c3a.ajouteInst(new C3aInstFEnd(""));

        return null;
    }

    // Pas de codes trois adresses pour les déclarations de variables
    public C3aOperand visit(SaDecTab node) {
        return null;
    }

    // Pas de codes trois adresses pour les déclarations de variables
    public C3aOperand visit(SaDecVar node) {
        return null;
    }

    // Variables

    // var[E1]
    public C3aOperand visit(SaVarIndicee node) {
        C3aOperand indice = node.getIndice().accept(this);
        C3aVar var;

        // If it's not a temporary or constant
        if(!(indice.getClass().equals(C3aConstant.class) || indice.getClass().equals(C3aTemp.class))) {
            C3aTemp temp = c3a.newTemp();

            c3a.ajouteInst(new C3aInstAffect(indice, temp, ""));

            var = new C3aVar(node.tsItem, temp);
        }

        else {
            var = new C3aVar(node.tsItem, indice);
        }

        return var;
    }

    // var
    public C3aOperand visit(SaVarSimple node) {
        return new C3aVar(node.tsItem, null);
    }

    // Expressions

    // int
    public C3aOperand visit(SaExpInt node) {
        return new C3aConstant(node.getVal());
    }

    // var
    public C3aOperand visit(SaExpVar node) {
        return node.getVar().accept(this);
    }

    // r = a1 + a2
    public C3aOperand visit(SaExpAdd node) {
        C3aOperand a1 = node.getOp1().accept(this);
        C3aOperand a2 = node.getOp2().accept(this);
        C3aTemp r = c3a.newTemp();

        c3a.ajouteInst(new C3aInstAdd(a1, a2, r, ""));

        return r;
    }

    // r = a1 - a2
    public C3aOperand visit(SaExpSub node) {
        C3aOperand a1 = node.getOp1().accept(this);
        C3aOperand a2 = node.getOp2().accept(this);
        C3aTemp r = c3a.newTemp();

        c3a.ajouteInst(new C3aInstSub(a1, a2, r, ""));

        return r;
    }

    // r = a1 * a2
    public C3aOperand visit(SaExpMult node) {
        C3aOperand a1 = node.getOp1().accept(this);
        C3aOperand a2 = node.getOp2().accept(this);
        C3aTemp r = c3a.newTemp();

        c3a.ajouteInst(new C3aInstMult(a1, a2, r, ""));

        return r;
    }

    // r = a1 / a2
    public C3aOperand visit(SaExpDiv node) {
        C3aOperand a1 = node.getOp1().accept(this);
        C3aOperand a2 = node.getOp2().accept(this);
        C3aTemp r = c3a.newTemp();

        c3a.ajouteInst(new C3aInstDiv(a1, a2, r, ""));

        return r;
    }

    // r = a1 && a2
    public C3aOperand visit(SaExpAnd node) {
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aOperand a1 = node.getOp1().accept(this);
        C3aOperand a2 = node.getOp2().accept(this);
        C3aTemp r = c3a.newTemp();

        c3a.ajouteInst(new C3aInstJumpIfEqual(a1, c3a.False, e1, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(a2, c3a.False, e1, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.True, r, ""));
        c3a.ajouteInst(new C3aInstJump(e2, ""));
        c3a.addLabelToNextInst(e1);
        c3a.ajouteInst(new C3aInstAffect(c3a.False, r, ""));
        c3a.addLabelToNextInst(e2);

        return r;
    }

    // r = a1 || a2
    public C3aOperand visit(SaExpOr node) {
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aOperand a1 = node.getOp1().accept(this);
        C3aOperand a2 = node.getOp2().accept(this);
        C3aTemp r = c3a.newTemp();

        c3a.ajouteInst(new C3aInstJumpIfNotEqual(a1, c3a.False, e1, ""));
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(a2, c3a.False, e1, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, r, ""));
        c3a.ajouteInst(new C3aInstJump(e2, ""));
        c3a.addLabelToNextInst(e1);
        c3a.ajouteInst(new C3aInstAffect(c3a.True, r, ""));
        c3a.addLabelToNextInst(e2);

        return r;
    }

    // if a1 == a2 goto e1
    public C3aOperand visit(SaExpEqual node) {
        C3aLabel e1 = c3a.newAutoLabel();
        C3aOperand a1 = node.getOp1().accept(this);
        C3aOperand a2 = node.getOp2().accept(this);
        C3aTemp r = c3a.newTemp();


        c3a.ajouteInst(new C3aInstAffect(c3a.True, r, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(a1, a2, e1, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, r, ""));
        c3a.addLabelToNextInst(e1);

        return r;
    }

    // if a1 < a2 goto e1
    public C3aOperand visit(SaExpInf node) {
        C3aLabel e1 = c3a.newAutoLabel();
        C3aOperand a1 = node.getOp1().accept(this);
        C3aOperand a2 = node.getOp2().accept(this);
        C3aTemp r = c3a.newTemp();

        c3a.ajouteInst(new C3aInstAffect(c3a.True, r, ""));
        c3a.ajouteInst(new C3aInstJumpIfLess(a1, a2, e1, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, r, ""));
        c3a.addLabelToNextInst(e1);

        return r;
    }

    // r = not
    public C3aOperand visit(SaExpNot node) {
        C3aLabel e1 = c3a.newAutoLabel();
        C3aOperand a1 = node.getOp1().accept(this);
        C3aTemp r = c3a.newTemp();

        c3a.ajouteInst(new C3aInstAffect(c3a.True, r, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(a1, c3a.False, e1, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, r, ""));
        c3a.addLabelToNextInst(e1);

        return r;
    }

    public C3aOperand visit(SaExpAppel node) {
        C3aTemp temp = c3a.newTemp();
        appel(node.getVal(), temp);

        return temp;
    }

    // r = lire()
    public C3aOperand visit(SaExpLire node) {
        C3aTemp r = c3a.newTemp();
        c3a.ajouteInst(new C3aInstRead(r, ""));

        return r;
    }

    // Instructions

    public C3aOperand visit(SaInstBloc node) {
        node.getVal().accept(this);

        return null;
    }

    // r = a1
    public C3aOperand visit(SaInstAffect node) {
        c3a.ajouteInst(new C3aInstAffect(node.getRhs().accept(this), node.getLhs().accept(this), ""));

        return null;
    }

    // write a1
    public C3aOperand visit(SaInstEcriture node) {
        c3a.ajouteInst(new C3aInstWrite(node.getArg().accept(this), ""));

        return null;
    }

    // ret a1
    public C3aOperand visit(SaInstRetour node) {
        C3aOperand a1 = node.getVal().accept(this);
        c3a.ajouteInst(new C3aInstReturn(a1, ""));

        return a1;
    }

    // if E then LI1 else L12
    public C3aOperand visit(SaInstSi node) {
        // Get expression result
        C3aOperand expResult = node.getTest().accept(this);
        // Create two labels
        C3aLabel faux = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();

        // If expResult = 0 jump to faux
        c3a.ajouteInst(new C3aInstJumpIfEqual(expResult, c3a.False, faux, ""));
        // then
        node.getAlors().accept(this);
        // If there's an else
        if(node.getSinon() != null) {
            // jump to suite
            c3a.ajouteInst(new C3aInstJump(suite, ""));
        }
        // faux:
        c3a.addLabelToNextInst(faux);
        // If there's an else
        if(node.getSinon() != null) {
            // else
            node.getSinon().accept(this);
            // suite:
            c3a.addLabelToNextInst(suite);
        }

        return null;
    }

    // while E do LI
    public C3aOperand visit(SaInstTantQue node) {
        // Create two labels
        C3aLabel test = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();

        // test:
        c3a.addLabelToNextInst(test);
        // Get expression result
        C3aOperand expResult = node.getTest().accept(this);
        // if expResult = 0 jump to suite
        c3a.ajouteInst(new C3aInstJumpIfEqual(expResult, c3a.False, suite, ""));
        // Loop code
        node.getFaire().accept(this);
        // jump to test
        c3a.ajouteInst(new C3aInstJump(test, ""));
        // suite:
        c3a.addLabelToNextInst(suite);

        return null;
    }

    // Listes

    // Inutile puisqu'il n'y a pas de codes trois adresses pour les déclarations de variables
    public C3aOperand visit(SaLDec node) {
        node.getTete().accept(this);

        if(node.getQueue() != null) {
            node.getQueue().accept(this);
        }

        return null;
    }

    // Utilisé pour la liste des paramètres lors d'un appel de fonction
    public C3aOperand visit(SaLExp node) {
        c3a.ajouteInst(new C3aInstParam(node.getTete().accept(this), ""));

        if(node.getQueue() != null) {
            node.getQueue().accept(this);
        }

        return null;
    }

    // Liste d'instructions
    public C3aOperand visit(SaLInst node) {
        node.getTete().accept(this);

        if(node.getQueue() != null) {
            node.getQueue().accept(this);
        }
        return null;
    }

    public C3a getC3a() { return c3a; }
}
