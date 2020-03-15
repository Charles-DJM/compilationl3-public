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
        SaLDec functionDec = node.getFonctions();
        visit(functionDec);

        return null;
    }

    //TODO
    public C3aOperand visit(SaAppel node) {
        return null;
    }

    // Déclarations

    public C3aOperand visit(SaDecFonc node) {
        TsItemFct function = table.getFct(node.getNom());
        String comment = "entree fonction";
        C3aInstFBegin fbegin = new C3aInstFBegin(function, comment);
        c3a.ajouteInst(fbegin);

        SaInstBloc instBloc = (SaInstBloc) node.getCorps();
        visit(instBloc);

        comment = "";
        C3aInstFEnd fend = new C3aInstFEnd(comment);
        c3a.ajouteInst(fend);

        return null;
    }

    //TODO
    public C3aOperand visit(SaDecTab node) {
        //return new C3aVar(node.tsItem, new C3aConstant(node.getTaille()));
        return null;
    }

    //TODO
    public C3aOperand visit(SaDecVar node) {
        //return new C3aVar(node.tsItem, null);
        return null;
    }

    // Variables

    public C3aOperand visit(SaVarIndicee node) {
        C3aOperand indice = visit(node.getIndice());
        String comment = "";

        if(!(indice.getClass().equals(C3aConstant.class)) || indice.getClass().equals(C3aTemp.class)) {
            C3aTemp temp = c3a.newTemp();

            C3aInstAffect affect = new C3aInstAffect(indice, temp, comment);
            c3a.ajouteInst(affect);
        }

        return new C3aVar(node.tsItem, indice); }

    public C3aOperand visit(SaVarSimple node) {
        return new C3aVar(node.tsItem, null);
    }

    // Expressions

    // r = a1 + a2
    public C3aOperand visit(SaExpAdd node) {
        C3aTemp r = c3a.newTemp();
        C3aOperand a1 = visit(node.getOp1());
        C3aOperand a2 = visit(node.getOp2());
        String comment = "";
        C3aInstAdd add = new C3aInstAdd(a1, a2, r, comment);

        c3a.ajouteInst(add);

        return r;
    }

    // r = a1 && a2
    public C3aOperand visit(SaExpAnd node) {
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aTemp comparison = c3a.newTemp();
        C3aOperand a1 = visit(node.getOp1());
        C3aOperand a2 = visit(node.getOp2());

        c3a.ajouteInst(new C3aInstJumpIfEqual(a1, c3a.False, e1, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(a2, c3a.False, e1, ""));
        c3a.ajouteInst(new C3aInstAffect(comparison, c3a.True, ""));
        c3a.ajouteInst(new C3aInstJump(e2, ""));
        c3a.addLabelToNextInst(e1);
        c3a.ajouteInst(new C3aInstAffect(comparison, c3a.False, ""));
        c3a.addLabelToNextInst(e2);
        return comparison;
    }

    //TODO
    public C3aOperand visit(SaExpAppel node) {
        return c3a.newTemp();
    }

    // r = a1 / a2
    public C3aOperand visit(SaExpDiv node) {
        C3aTemp r = c3a.newTemp();
        C3aOperand a1 = visit(node.getOp1());
        C3aOperand a2 = visit(node.getOp2());
        String comment = "";
        C3aInstDiv div = new C3aInstDiv(a1, a2, r, comment);

        c3a.ajouteInst(div);

        return r;
    }

    // if a1 == a2 goto e1
    public C3aOperand visit(SaExpEqual node) {
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aTemp comparison = c3a.newTemp();
        C3aOperand a1 = visit(node.getOp1());
        C3aOperand a2 = visit(node.getOp2());

        c3a.ajouteInst(new C3aInstJumpIfEqual(a1, a2, e1, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, comparison, ""));
        c3a.ajouteInst(new C3aInstJump(e2, ""));
        c3a.addLabelToNextInst(e1);
        c3a.ajouteInst(new C3aInstAffect(c3a.True, comparison, ""));
        c3a.addLabelToNextInst(e2);
        return comparison;
    }

    // if a1 < a2 goto e1
    public C3aOperand visit(SaExpInf node) {
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aTemp comparison = c3a.newTemp();
        C3aOperand a1 = visit(node.getOp1());
        C3aOperand a2 = visit(node.getOp2());

        c3a.ajouteInst(new C3aInstJumpIfLess(a1, a2, e1, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, comparison, ""));
        c3a.ajouteInst(new C3aInstJump(e2, ""));
        c3a.addLabelToNextInst(e1);
        c3a.ajouteInst(new C3aInstAffect(c3a.True, comparison, ""));
        c3a.addLabelToNextInst(e2);
        return comparison;
    }

    // int DONE
    public C3aOperand visit(SaExpInt node) { return new C3aConstant(node.getVal()); }

    public C3aOperand visit(SaExpLire node) {
        C3aTemp temp = c3a.newTemp();
        c3a.ajouteInst(new C3aInstRead(temp, ""));

        return temp;
    }

    // r = a1 * a2
    public C3aOperand visit(SaExpMult node) {
        C3aTemp r = c3a.newTemp();
        C3aOperand a1 = visit(node.getOp1());
        C3aOperand a2 = visit(node.getOp2());
        String comment = "";
        C3aInstMult mult = new C3aInstMult(a1, a2, r, comment);

        c3a.ajouteInst(mult);

        return r;
    }

    //TODO
    public C3aOperand visit(SaExpNot node) {
        return c3a.newTemp();
    }

    // r = a1 || a2
    public C3aOperand visit(SaExpOr node) {
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aTemp comparison = c3a.newTemp();
        C3aOperand a1 = visit(node.getOp1());
        C3aOperand a2 = visit(node.getOp2());

        c3a.ajouteInst(new C3aInstJumpIfEqual(a1, c3a.True, e1, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(a2, c3a.True, e1, ""));
        c3a.ajouteInst(new C3aInstAffect(comparison, c3a.False, ""));
        c3a.ajouteInst(new C3aInstJump(e2, ""));
        c3a.addLabelToNextInst(e1);
        c3a.ajouteInst(new C3aInstAffect(comparison, c3a.True, ""));
        c3a.addLabelToNextInst(e2);
        return comparison;
    }

    // r = a1 - a2
    public C3aOperand visit(SaExpSub node) {
        C3aTemp r = c3a.newTemp();
        C3aOperand a1 = visit(node.getOp1());
        C3aOperand a2 = visit(node.getOp2());
        String comment = "";
        C3aInstSub sub = new C3aInstSub(a1, a2, r, comment);

        c3a.ajouteInst(sub);

        return r;
    }

    // var TODO
    public C3aOperand visit(SaExpVar node) { return new C3aVar(table.getVar(node.getVar().toString()), null);}

    // Instructions

    // r = a1
    public C3aOperand visit(SaInstAffect node) {
        c3a.ajouteInst(new C3aInstAffect(visit(node.getRhs()), visit((SaAppel) node.getLhs()), null));

        return visit((SaAppel) node.getLhs());
    }

    public C3aOperand visit(SaInstBloc node) {
        visit(node.getVal());
        return null;
    }

    // write a1
    public C3aOperand visit(SaInstEcriture node) {
        //TODO
        System.out.println("Test0" + node.getArg());
        SaExpAdd add = (SaExpAdd) node.getArg();
        C3aOperand a1 = visit(add);

        String comment = "";

        C3aInstWrite write = new C3aInstWrite(a1, comment);

        c3a.ajouteInst(write);
        return null;
    }

    public C3aOperand visit(SaInstRetour node) {
        return null;
    }

    // if E then LI1 else L12
    public C3aOperand visit(SaInstSi node) {
        // Get expression result
        C3aOperand expResult = visit(node.getTest());
        // Create two labels
        C3aLabel faux = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();

        // If expResult = 0 jump to faux
        c3a.ajouteInst(new C3aInstJumpIfEqual(expResult, c3a.False, faux, ""));
        // then
        //visit(node.getAlors());
        // jump to suite
        c3a.ajouteInst(new C3aInstJump(suite, ""));
        // faux:
        c3a.addLabelToNextInst(faux);
        // else
        //visit(node.getSinon());
        // suite:
        c3a.addLabelToNextInst(suite);

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
        C3aOperand expResult = visit(node.getTest());
        // if expResult = 0 jump to suite
        c3a.ajouteInst(new C3aInstJumpIfEqual(expResult, c3a.False, suite, ""));
        // Loop code
        //visit(node.getFaire());
        // jump to test
        c3a.ajouteInst(new C3aInstJump(test, ""));
        // suite:
        c3a.addLabelToNextInst(suite);

        return null;
    }

    // Listes

    public C3aOperand visit(SaLDec node) {
        visit(table.getFct(node.getTete().getNom()).saDecFonc);

        if(node.getQueue() != null) {
            visit(node.getQueue());
        }

        return null;
    }

    public C3aOperand visit(SaLExp node) {
        C3aOperand temp = visit(node.getTete());

        if(node.getQueue() != null) {
            visit(node.getQueue());
        }

        return temp;
    }

    public C3aOperand visit(SaLInst node) {
        //TODO
        visit((SaInstEcriture) node.getTete());

        if(node.getQueue() != null) {
            visit(node.getQueue());
        }

        return null;
    }

    public C3a getC3a() { return c3a; }
}
