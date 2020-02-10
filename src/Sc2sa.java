import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;

public class Sc2sa extends DepthFirstAdapter
{
    private SaNode returnValue;

    // caseANomsousexpressionNomexpression

    // program = optvardeclist foncdeclist
    public void caseAOptvardeclistProgram(AOptvardeclistProgram node) {
        node.getOptvardeclist().apply(this);
        SaLDec optvardeclist = (SaLDec) this.returnValue;

        node.getFoncdeclist().apply(this);
        SaLDec foncdeclist = (SaLDec) this.returnValue;

        this.returnValue = new SaProg(optvardeclist, foncdeclist);
    }


    // program = foncdeclist
    public void caseAFoncdeclistProgram(AFoncdeclistProgram node) {
        node.getFoncdeclist().apply(this);
        SaLDec foncdeclist = (SaLDec) this.returnValue;

        this.returnValue = new SaProg(null, foncdeclist);
    }


    // optvardeclist = vardeclist semicolon
    public void caseAOptvardeclist(AOptvardeclist node) {
        node.getVardeclist().apply(this);
    }

    // foncdeclist = foncdec foncdeclist
    public void caseAOneormoreFoncdeclist(AOneormoreFoncdeclist node) {
        node.getFoncdec().apply(this);
        SaDecFonc foncdec = (SaDecFonc) this.returnValue;

        node.getFoncdeclist().apply(this);
        SaLDec foncdeclist = (SaLDec) this.returnValue;

        this.returnValue = new SaLDec(foncdec, foncdeclist);
    }

    // foncdeclist =
    public void caseALastFoncdeclist(ALastFoncdeclist node) {
        this.returnValue = null;
    }

    // foncdec = id paramlist optvardeclist instrbloc
    public void caseAWithvardecFoncdec(AWithvardecFoncdec node) {
        String id = node.getId().getText();

        node.getParamlist().apply(this);
        SaLDec paramlist = (SaLDec) this.returnValue;

        node.getOptvardeclist().apply(this);
        SaLDec optvardeclist = (SaLDec) this.returnValue;

        node.getInstrbloc().apply(this);
        SaInst instrbloc = (SaInst) this.returnValue;

        this.returnValue = new SaDecFonc(id, paramlist, optvardeclist, instrbloc);
    }

    // foncdec = id paramlist instrbloc
    public void caseANovardecFoncdec(ANovardecFoncdec node) {
        String id = node.getId().getText();

        node.getParamlist().apply(this);
        SaLDec paramlist = (SaLDec) this.returnValue;

        node.getInstrbloc().apply(this);
        SaInst instrbloc = (SaInst) this.returnValue;

        this.returnValue = new SaDecFonc(id, paramlist, null, instrbloc);
    }

    // paramlist = l_par vardeclist r_par
    public void caseAWithparamParamlist(AWithparamParamlist node) {
        node.getVardeclist().apply(this);
    }

    // paramlist = l_par r_par
    public void caseAWithoutparamParamlist(AWithoutparamParamlist node) {
        this.returnValue = null;
    }

    // vardeclist = vardec vardeclistbis
    public void caseAMorethanoneVardeclist(AMorethanoneVardeclist node) {
        node.getVardec().apply(this);
        SaDec vardec = (SaDec) this.returnValue;

        node.getVardeclistbis().apply(this);
        SaLDec vardeclistbis = (SaLDec) this.returnValue;

        this.returnValue = new SaLDec(vardec, vardeclistbis);
    }

    // vardeclist = vardec
    public void caseAOneVardeclist(AOneVardeclist node) {
        node.getVardec().apply(this);
        SaDec vardec = (SaDec) this.returnValue;

        this.returnValue = new SaLDec(vardec, null);
    }

    // vardeclistbis = comma vardec vardeclistbis
    public void caseAMoreVardeclistbis(AMoreVardeclistbis node) {
        node.getVardec().apply(this);
        SaDec vardec = (SaDec) this.returnValue;

        node.getVardeclistbis().apply(this);
        SaLDec vardeclistbis = (SaLDec) this.returnValue;

        this.returnValue = new SaLDec(vardec, vardeclistbis);
    }

    // vardeclistbis = comma vardec
    public void caseALastVardeclistbis(ALastVardeclistbis node) {
        node.getVardec().apply(this);
        SaDec vardec = (SaDec) this.returnValue;

        this.returnValue = new SaLDec(vardec, null);
    }

    // vardec = int id
    public void caseAIntVardec(AIntVardec node) {
        String id = node.getId().getText();

        this.returnValue = new SaDecVar(id);
    }

    // vardec = int id l_brac number r_brac
    public void caseAInttableVardec(AInttableVardec node) {
        String id = node.getId().getText();

        int number = Integer.parseInt(node.getNumber().getText());

        this.returnValue = new SaDecTab(id, number);
    }

    // var = id
    public void caseAVarVar(AVarVar node) {
        String id = node.getId().getText();

        this.returnValue = new SaVarSimple(id);
    }

    // var = id l_brac exp r_brac
    public void caseATableVar(ATableVar node) {
        String id = node.getId().getText();

        node.getExp().apply(this);
        SaExp exp = (SaExp) this.returnValue;

        this.returnValue = new SaVarIndicee(id, exp);
    }

    // exp = exp or exp1
    public void caseAOrExp(AOrExp node) {
        node.getExp().apply(this);
        SaExp op1 = (SaExp) this.returnValue;

        node.getExp1().apply(this);
        SaExp op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpOr(op1, op2);
    }

    // exp = exp1
    public void caseAExp1Exp(AExp1Exp node) {
        node.getExp1().apply(this);
    }

    // exp1 = exp1 and exp2
    public void caseAAndExp1(AAndExp1 node) {
        node.getExp1().apply(this);
        SaExp op1 = (SaExp) this.returnValue;

        node.getExp2().apply(this);
        SaExp op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpAnd(op1, op2);
    }

    // exp1 = exp2
    public void caseAExp2Exp1(AExp2Exp1 node) {
        node.getExp2().apply(this);
    }

    // exp2 = exp2 equals exp3
    public void caseAEqualsExp2(AEqualsExp2 node) {
        node.getExp2().apply(this);
        SaExp op1 = (SaExp) this.returnValue;

        node.getExp3().apply(this);
        SaExp op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpEqual(op1, op2);
    }

    // exp2 = exp2 inf exp3
    public void caseAInfExp2(AInfExp2 node) {
        node.getExp2().apply(this);
        SaExp op1 = (SaExp) this.returnValue;

        node.getExp3().apply(this);
        SaExp op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpInf(op1, op2);
    }

    // exp2 = exp3
    public void caseAExp3Exp2(AExp3Exp2 node) {
        node.getExp3().apply(this);
    }

    // exp3 = exp3 plus exp4
    public void caseAPlusExp3(APlusExp3 node) {
        node.getExp3().apply(this);
        SaExp op1 = (SaExp) this.returnValue;

        node.getExp4().apply(this);
        SaExp op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpAdd(op1, op2);
    }

    // exp3 = exp3 minus exp4
    public void caseAMinusExp3(AMinusExp3 node) {
        node.getExp3().apply(this);
        SaExp op1 = (SaExp) this.returnValue;

        node.getExp4().apply(this);
        SaExp op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpSub(op1, op2);
    }

    // exp3 = exp4
    public void caseAExp4Exp3(AExp4Exp3 node) {
        node.getExp4().apply(this);
    }

    // exp4 = exp4 mult exp5
    public void caseAMultExp4(AMultExp4 node) {
        node.getExp4().apply(this);
        SaExp op1 = (SaExp) this.returnValue;

        node.getExp5().apply(this);
        SaExp op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpMult(op1, op2);
    }

    // exp4 = exp4 div exp5
    public void caseADivExp4(ADivExp4 node) {
        node.getExp4().apply(this);
        SaExp op1 = (SaExp) this.returnValue;

        node.getExp5().apply(this);
        SaExp op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpDiv(op1, op2);
    }

    // exp4 = exp5
    public void caseAExp5Exp4(AExp5Exp4 node) {
        node.getExp5().apply(this);
    }

    // exp5 = not exp5
    public void caseANotExp5(ANotExp5 node) {
        node.getExp5().apply(this);
        SaExp op1 = (SaExp) this.returnValue;

        this.returnValue = new SaExpNot(op1);
    }

    // exp5 = l_par exp r_par
    public void caseAParenthesisExp5(AParenthesisExp5 node) {
        node.getExp().apply(this);
    }

    // exp5 = number
    public void caseANumberExp5(ANumberExp5 node) {
        int number = Integer.parseInt(node.getNumber().getText());

        this.returnValue = new SaExpInt(number);
    }

    // exp5 = call
    public void caseACallExp5(ACallExp5 node) {
        node.getCall().apply(this);
        SaAppel op1 = (SaAppel) this.returnValue;

        this.returnValue = new SaExpAppel(op1);
    }

    // exp5 = var
    public void caseAVarExp5(AVarExp5 node) {
        node.getVar().apply(this);
        SaVar op1 = (SaVar) this.returnValue;

        this.returnValue = new SaExpVar(op1);
    }

    // exp5 = read l_par r_par
    public void caseAReadExp5(AReadExp5 node) {
        this.returnValue = new SaExpLire();
    }

    // listofexp = exp lifeofexpbis
    public void caseAMorethanoneListofexp(AMorethanoneListofexp node) {
        node.getExp().apply(this);
        SaExp exp = (SaExp) this.returnValue;

        node.getListofexpbis().apply(this);
        SaLExp lifeofexpbis = (SaLExp) this.returnValue;

        this.returnValue = new SaLExp(exp, lifeofexpbis);
    }

    // listofexp =
    public void caseANoneListofexp(ANoneListofexp node) {
        this.returnValue = null;
    }

    // listofexpbis = comma exp listofexpbis
    public void caseAMoreListofexpbis(AMoreListofexpbis node) {
        node.getExp().apply(this);
        SaExp exp = (SaExp) this.returnValue;

        node.getListofexpbis().apply(this);
        SaLExp listofexpbis = (SaLExp) this.returnValue;

        this.returnValue = new SaLExp(exp, listofexpbis);
    }

    // listofexpbis =
    public void caseALastListofexpbis(ALastListofexpbis node) {
        this.returnValue = null;
    }

    // instr = assinstr
    public void caseAAssinstrInstr(AAssinstrInstr node) {
        node.getAssinstr().apply(this);
    }

    // instr = ifinstr
    public void caseAIfinstrInstr(AIfinstrInstr node) {
        node.getIfinstr().apply(this);
    }

    // instr = whileinstr
    public void caseAWhileinstrInstr(AWhileinstrInstr node) {
        node.getWhileinstr().apply(this);
    }

    // instr = callinstr
    public void caseACallinstrInstr(ACallinstrInstr node) {
        node.getCallinstr().apply(this);
    }

    // instr = retinstr
    public void caseARetinstrInstr(ARetinstrInstr node) {
        node.getRetinstr().apply(this);
    }

    // instr = writeinstr
    public void caseAWriteinstrInstr(AWriteinstrInstr node) {
        node.getWriteinstr().apply(this);
    }

    // instr = emptyinstr
    public void caseAEmptyinstrInstr(AEmptyinstrInstr node) {
        node.getEmptyinstr().apply(this);
    }

    // instr = instrbloc
    public void caseAInstrblocInstr(AInstrblocInstr node) {
        node.getInstrbloc().apply(this);
    }

    // assinstr = var equals exp semicolon
    public void caseAAssinstr(AAssinstr node) {
        node.getVar().apply(this);
        SaVar var = (SaVar) this.returnValue;

        node.getExp().apply(this);
        SaExp exp = (SaExp) this.returnValue;

        this.returnValue = new SaInstAffect(var, exp);
    }

    // ifinstr = if exp then instr
    public void caseAIfthenIfinstr(AIfthenIfinstr node) {
        node.getExp().apply(this);
        SaExp exp = (SaExp) this.returnValue;

        node.getInstrbloc().apply(this);
        SaInst instr = (SaInst) this.returnValue;

        this.returnValue = new SaInstSi(exp, instr, null);
    }

    // ifinstr = if exp then instr elseinstr
    public void caseAIfthenelseIfinstr(AIfthenelseIfinstr node) {
        node.getExp().apply(this);
        SaExp exp = (SaExp) this.returnValue;

        node.getInstrbloc().apply(this);
        SaInst instr = (SaInst) this.returnValue;

        node.getElseinstr().apply(this);
        SaInst elseinstr = (SaInst) this.returnValue;

        this.returnValue = new SaInstSi(exp, instr, elseinstr);
    }

    // elseinstr = else instr
    public void caseAElseinstr(AElseinstr node) {
        node.getInstrbloc().apply(this);
    }

    // whileinstr = while exp do instr
    public void caseAWhileinstr(AWhileinstr node) {
        node.getExp().apply(this);
        SaExp exp = (SaExp) this.returnValue;

        node.getInstrbloc().apply(this);
        SaInst instr = (SaInst) this.returnValue;

        this.returnValue = new SaInstTantQue(exp, instr);
    }

    // callinstr = call semicolon
    public void caseACallinstr(ACallinstr node) {
        node.getCall().apply(this);
    }

    // call = id l_par listofexp r_par
    public void caseACall(ACall node) {
        String id = node.getId().getText();

        node.getListofexp().apply(this);
        SaLExp listofexp = (SaLExp) this.returnValue;

        this.returnValue = new SaAppel(id, listofexp);
    }

    // retinstr = return exp semicolon
    public void caseARetinstr(ARetinstr node) {
        node.getExp().apply(this);
        SaExp exp = (SaExp) this.returnValue;

        this.returnValue = new SaInstRetour(exp);
    }

    // writeinstr = write l_par exp r_par semicolon
    public void caseAWriteinstr(AWriteinstr node) {
        node.getExp().apply(this);
        SaExp exp = (SaExp) this.returnValue;

        this.returnValue = new SaInstEcriture(exp);
    }

    // emptyinstr = semicolon
    public void caseAEmptyinstr(AEmptyinstr node) {
        this.returnValue = null;
    }

    // instrbloc = l_curbrac instrblocbis r_curbrac
    public void caseAInstrbloc(AInstrbloc node) {
        node.getInstrblocbis().apply(this);
        SaLInst instrblocbis = (SaLInst) this.returnValue;

        this.returnValue = new SaInstBloc(instrblocbis);
    }

    // instrblocbis = instr instrblocbis
    public void caseAInstrInstrblocbis(AInstrInstrblocbis node) {
        node.getInstr().apply(this);
        SaInst instr = (SaInst) this.returnValue;

        node.getInstrblocbis().apply(this);
        SaLInst instrblocbis = (SaLInst) this.returnValue;

        this.returnValue = new SaLInst(instr, instrblocbis);
    }

    // instrblocbis =
    public void caseANoinstrInstrblocbis(ANoinstrInstrblocbis node) {
        this.returnValue = null;
    }

    // Get root
    public SaNode getRoot() {
         return returnValue;
     }
}