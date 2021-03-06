/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.analysis;

import java.util.*;
import sc.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getEOF().apply(this);
        node.getPProgram().apply(this);
        outStart(node);
    }

    public void inAOptvardeclistProgram(AOptvardeclistProgram node)
    {
        defaultIn(node);
    }

    public void outAOptvardeclistProgram(AOptvardeclistProgram node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOptvardeclistProgram(AOptvardeclistProgram node)
    {
        inAOptvardeclistProgram(node);
        if(node.getFoncdeclist() != null)
        {
            node.getFoncdeclist().apply(this);
        }
        if(node.getOptvardeclist() != null)
        {
            node.getOptvardeclist().apply(this);
        }
        outAOptvardeclistProgram(node);
    }

    public void inAFoncdeclistProgram(AFoncdeclistProgram node)
    {
        defaultIn(node);
    }

    public void outAFoncdeclistProgram(AFoncdeclistProgram node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFoncdeclistProgram(AFoncdeclistProgram node)
    {
        inAFoncdeclistProgram(node);
        if(node.getFoncdeclist() != null)
        {
            node.getFoncdeclist().apply(this);
        }
        outAFoncdeclistProgram(node);
    }

    public void inAOptvardeclist(AOptvardeclist node)
    {
        defaultIn(node);
    }

    public void outAOptvardeclist(AOptvardeclist node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOptvardeclist(AOptvardeclist node)
    {
        inAOptvardeclist(node);
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getVardeclist() != null)
        {
            node.getVardeclist().apply(this);
        }
        outAOptvardeclist(node);
    }

    public void inAOneormoreFoncdeclist(AOneormoreFoncdeclist node)
    {
        defaultIn(node);
    }

    public void outAOneormoreFoncdeclist(AOneormoreFoncdeclist node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOneormoreFoncdeclist(AOneormoreFoncdeclist node)
    {
        inAOneormoreFoncdeclist(node);
        if(node.getFoncdeclist() != null)
        {
            node.getFoncdeclist().apply(this);
        }
        if(node.getFoncdec() != null)
        {
            node.getFoncdec().apply(this);
        }
        outAOneormoreFoncdeclist(node);
    }

    public void inALastFoncdeclist(ALastFoncdeclist node)
    {
        defaultIn(node);
    }

    public void outALastFoncdeclist(ALastFoncdeclist node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALastFoncdeclist(ALastFoncdeclist node)
    {
        inALastFoncdeclist(node);
        outALastFoncdeclist(node);
    }

    public void inAWithvardecFoncdec(AWithvardecFoncdec node)
    {
        defaultIn(node);
    }

    public void outAWithvardecFoncdec(AWithvardecFoncdec node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWithvardecFoncdec(AWithvardecFoncdec node)
    {
        inAWithvardecFoncdec(node);
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        if(node.getOptvardeclist() != null)
        {
            node.getOptvardeclist().apply(this);
        }
        if(node.getParamlist() != null)
        {
            node.getParamlist().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAWithvardecFoncdec(node);
    }

    public void inANovardecFoncdec(ANovardecFoncdec node)
    {
        defaultIn(node);
    }

    public void outANovardecFoncdec(ANovardecFoncdec node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANovardecFoncdec(ANovardecFoncdec node)
    {
        inANovardecFoncdec(node);
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        if(node.getParamlist() != null)
        {
            node.getParamlist().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outANovardecFoncdec(node);
    }

    public void inAWithparamParamlist(AWithparamParamlist node)
    {
        defaultIn(node);
    }

    public void outAWithparamParamlist(AWithparamParamlist node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWithparamParamlist(AWithparamParamlist node)
    {
        inAWithparamParamlist(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getVardeclist() != null)
        {
            node.getVardeclist().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        outAWithparamParamlist(node);
    }

    public void inAWithoutparamParamlist(AWithoutparamParamlist node)
    {
        defaultIn(node);
    }

    public void outAWithoutparamParamlist(AWithoutparamParamlist node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWithoutparamParamlist(AWithoutparamParamlist node)
    {
        inAWithoutparamParamlist(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        outAWithoutparamParamlist(node);
    }

    public void inAMorethanoneVardeclist(AMorethanoneVardeclist node)
    {
        defaultIn(node);
    }

    public void outAMorethanoneVardeclist(AMorethanoneVardeclist node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMorethanoneVardeclist(AMorethanoneVardeclist node)
    {
        inAMorethanoneVardeclist(node);
        if(node.getVardeclistbis() != null)
        {
            node.getVardeclistbis().apply(this);
        }
        if(node.getVardec() != null)
        {
            node.getVardec().apply(this);
        }
        outAMorethanoneVardeclist(node);
    }

    public void inAOneVardeclist(AOneVardeclist node)
    {
        defaultIn(node);
    }

    public void outAOneVardeclist(AOneVardeclist node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOneVardeclist(AOneVardeclist node)
    {
        inAOneVardeclist(node);
        if(node.getVardec() != null)
        {
            node.getVardec().apply(this);
        }
        outAOneVardeclist(node);
    }

    public void inAMoreVardeclistbis(AMoreVardeclistbis node)
    {
        defaultIn(node);
    }

    public void outAMoreVardeclistbis(AMoreVardeclistbis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMoreVardeclistbis(AMoreVardeclistbis node)
    {
        inAMoreVardeclistbis(node);
        if(node.getVardeclistbis() != null)
        {
            node.getVardeclistbis().apply(this);
        }
        if(node.getVardec() != null)
        {
            node.getVardec().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        outAMoreVardeclistbis(node);
    }

    public void inALastVardeclistbis(ALastVardeclistbis node)
    {
        defaultIn(node);
    }

    public void outALastVardeclistbis(ALastVardeclistbis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALastVardeclistbis(ALastVardeclistbis node)
    {
        inALastVardeclistbis(node);
        if(node.getVardec() != null)
        {
            node.getVardec().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        outALastVardeclistbis(node);
    }

    public void inAIntVardec(AIntVardec node)
    {
        defaultIn(node);
    }

    public void outAIntVardec(AIntVardec node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIntVardec(AIntVardec node)
    {
        inAIntVardec(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getInt() != null)
        {
            node.getInt().apply(this);
        }
        outAIntVardec(node);
    }

    public void inAInttableVardec(AInttableVardec node)
    {
        defaultIn(node);
    }

    public void outAInttableVardec(AInttableVardec node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInttableVardec(AInttableVardec node)
    {
        inAInttableVardec(node);
        if(node.getRBrac() != null)
        {
            node.getRBrac().apply(this);
        }
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        if(node.getLBrac() != null)
        {
            node.getLBrac().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getInt() != null)
        {
            node.getInt().apply(this);
        }
        outAInttableVardec(node);
    }

    public void inAVarVar(AVarVar node)
    {
        defaultIn(node);
    }

    public void outAVarVar(AVarVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarVar(AVarVar node)
    {
        inAVarVar(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAVarVar(node);
    }

    public void inATableVar(ATableVar node)
    {
        defaultIn(node);
    }

    public void outATableVar(ATableVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATableVar(ATableVar node)
    {
        inATableVar(node);
        if(node.getRBrac() != null)
        {
            node.getRBrac().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getLBrac() != null)
        {
            node.getLBrac().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outATableVar(node);
    }

    public void inAOrExp(AOrExp node)
    {
        defaultIn(node);
    }

    public void outAOrExp(AOrExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOrExp(AOrExp node)
    {
        inAOrExp(node);
        if(node.getExp1() != null)
        {
            node.getExp1().apply(this);
        }
        if(node.getOr() != null)
        {
            node.getOr().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        outAOrExp(node);
    }

    public void inAExp1Exp(AExp1Exp node)
    {
        defaultIn(node);
    }

    public void outAExp1Exp(AExp1Exp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp1Exp(AExp1Exp node)
    {
        inAExp1Exp(node);
        if(node.getExp1() != null)
        {
            node.getExp1().apply(this);
        }
        outAExp1Exp(node);
    }

    public void inAAndExp1(AAndExp1 node)
    {
        defaultIn(node);
    }

    public void outAAndExp1(AAndExp1 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAndExp1(AAndExp1 node)
    {
        inAAndExp1(node);
        if(node.getExp2() != null)
        {
            node.getExp2().apply(this);
        }
        if(node.getAnd() != null)
        {
            node.getAnd().apply(this);
        }
        if(node.getExp1() != null)
        {
            node.getExp1().apply(this);
        }
        outAAndExp1(node);
    }

    public void inAExp2Exp1(AExp2Exp1 node)
    {
        defaultIn(node);
    }

    public void outAExp2Exp1(AExp2Exp1 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp2Exp1(AExp2Exp1 node)
    {
        inAExp2Exp1(node);
        if(node.getExp2() != null)
        {
            node.getExp2().apply(this);
        }
        outAExp2Exp1(node);
    }

    public void inAEqualsExp2(AEqualsExp2 node)
    {
        defaultIn(node);
    }

    public void outAEqualsExp2(AEqualsExp2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEqualsExp2(AEqualsExp2 node)
    {
        inAEqualsExp2(node);
        if(node.getExp3() != null)
        {
            node.getExp3().apply(this);
        }
        if(node.getEquals() != null)
        {
            node.getEquals().apply(this);
        }
        if(node.getExp2() != null)
        {
            node.getExp2().apply(this);
        }
        outAEqualsExp2(node);
    }

    public void inAInfExp2(AInfExp2 node)
    {
        defaultIn(node);
    }

    public void outAInfExp2(AInfExp2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInfExp2(AInfExp2 node)
    {
        inAInfExp2(node);
        if(node.getExp3() != null)
        {
            node.getExp3().apply(this);
        }
        if(node.getInf() != null)
        {
            node.getInf().apply(this);
        }
        if(node.getExp2() != null)
        {
            node.getExp2().apply(this);
        }
        outAInfExp2(node);
    }

    public void inAExp3Exp2(AExp3Exp2 node)
    {
        defaultIn(node);
    }

    public void outAExp3Exp2(AExp3Exp2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp3Exp2(AExp3Exp2 node)
    {
        inAExp3Exp2(node);
        if(node.getExp3() != null)
        {
            node.getExp3().apply(this);
        }
        outAExp3Exp2(node);
    }

    public void inAPlusExp3(APlusExp3 node)
    {
        defaultIn(node);
    }

    public void outAPlusExp3(APlusExp3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPlusExp3(APlusExp3 node)
    {
        inAPlusExp3(node);
        if(node.getExp4() != null)
        {
            node.getExp4().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getExp3() != null)
        {
            node.getExp3().apply(this);
        }
        outAPlusExp3(node);
    }

    public void inAMinusExp3(AMinusExp3 node)
    {
        defaultIn(node);
    }

    public void outAMinusExp3(AMinusExp3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMinusExp3(AMinusExp3 node)
    {
        inAMinusExp3(node);
        if(node.getExp4() != null)
        {
            node.getExp4().apply(this);
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getExp3() != null)
        {
            node.getExp3().apply(this);
        }
        outAMinusExp3(node);
    }

    public void inAExp4Exp3(AExp4Exp3 node)
    {
        defaultIn(node);
    }

    public void outAExp4Exp3(AExp4Exp3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp4Exp3(AExp4Exp3 node)
    {
        inAExp4Exp3(node);
        if(node.getExp4() != null)
        {
            node.getExp4().apply(this);
        }
        outAExp4Exp3(node);
    }

    public void inAMultExp4(AMultExp4 node)
    {
        defaultIn(node);
    }

    public void outAMultExp4(AMultExp4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultExp4(AMultExp4 node)
    {
        inAMultExp4(node);
        if(node.getExp5() != null)
        {
            node.getExp5().apply(this);
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getExp4() != null)
        {
            node.getExp4().apply(this);
        }
        outAMultExp4(node);
    }

    public void inADivExp4(ADivExp4 node)
    {
        defaultIn(node);
    }

    public void outADivExp4(ADivExp4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADivExp4(ADivExp4 node)
    {
        inADivExp4(node);
        if(node.getExp5() != null)
        {
            node.getExp5().apply(this);
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getExp4() != null)
        {
            node.getExp4().apply(this);
        }
        outADivExp4(node);
    }

    public void inAExp5Exp4(AExp5Exp4 node)
    {
        defaultIn(node);
    }

    public void outAExp5Exp4(AExp5Exp4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp5Exp4(AExp5Exp4 node)
    {
        inAExp5Exp4(node);
        if(node.getExp5() != null)
        {
            node.getExp5().apply(this);
        }
        outAExp5Exp4(node);
    }

    public void inANotExp5(ANotExp5 node)
    {
        defaultIn(node);
    }

    public void outANotExp5(ANotExp5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANotExp5(ANotExp5 node)
    {
        inANotExp5(node);
        if(node.getExp5() != null)
        {
            node.getExp5().apply(this);
        }
        if(node.getNot() != null)
        {
            node.getNot().apply(this);
        }
        outANotExp5(node);
    }

    public void inAParenthesisExp5(AParenthesisExp5 node)
    {
        defaultIn(node);
    }

    public void outAParenthesisExp5(AParenthesisExp5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParenthesisExp5(AParenthesisExp5 node)
    {
        inAParenthesisExp5(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        outAParenthesisExp5(node);
    }

    public void inANumberExp5(ANumberExp5 node)
    {
        defaultIn(node);
    }

    public void outANumberExp5(ANumberExp5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANumberExp5(ANumberExp5 node)
    {
        inANumberExp5(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        outANumberExp5(node);
    }

    public void inACallExp5(ACallExp5 node)
    {
        defaultIn(node);
    }

    public void outACallExp5(ACallExp5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACallExp5(ACallExp5 node)
    {
        inACallExp5(node);
        if(node.getCall() != null)
        {
            node.getCall().apply(this);
        }
        outACallExp5(node);
    }

    public void inAVarExp5(AVarExp5 node)
    {
        defaultIn(node);
    }

    public void outAVarExp5(AVarExp5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarExp5(AVarExp5 node)
    {
        inAVarExp5(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        outAVarExp5(node);
    }

    public void inAReadExp5(AReadExp5 node)
    {
        defaultIn(node);
    }

    public void outAReadExp5(AReadExp5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAReadExp5(AReadExp5 node)
    {
        inAReadExp5(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getRead() != null)
        {
            node.getRead().apply(this);
        }
        outAReadExp5(node);
    }

    public void inAMorethanoneListofexp(AMorethanoneListofexp node)
    {
        defaultIn(node);
    }

    public void outAMorethanoneListofexp(AMorethanoneListofexp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMorethanoneListofexp(AMorethanoneListofexp node)
    {
        inAMorethanoneListofexp(node);
        if(node.getListofexpbis() != null)
        {
            node.getListofexpbis().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        outAMorethanoneListofexp(node);
    }

    public void inANoneListofexp(ANoneListofexp node)
    {
        defaultIn(node);
    }

    public void outANoneListofexp(ANoneListofexp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANoneListofexp(ANoneListofexp node)
    {
        inANoneListofexp(node);
        outANoneListofexp(node);
    }

    public void inAMoreListofexpbis(AMoreListofexpbis node)
    {
        defaultIn(node);
    }

    public void outAMoreListofexpbis(AMoreListofexpbis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMoreListofexpbis(AMoreListofexpbis node)
    {
        inAMoreListofexpbis(node);
        if(node.getListofexpbis() != null)
        {
            node.getListofexpbis().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        outAMoreListofexpbis(node);
    }

    public void inALastListofexpbis(ALastListofexpbis node)
    {
        defaultIn(node);
    }

    public void outALastListofexpbis(ALastListofexpbis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALastListofexpbis(ALastListofexpbis node)
    {
        inALastListofexpbis(node);
        outALastListofexpbis(node);
    }

    public void inAAssinstrInstr(AAssinstrInstr node)
    {
        defaultIn(node);
    }

    public void outAAssinstrInstr(AAssinstrInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssinstrInstr(AAssinstrInstr node)
    {
        inAAssinstrInstr(node);
        if(node.getAssinstr() != null)
        {
            node.getAssinstr().apply(this);
        }
        outAAssinstrInstr(node);
    }

    public void inAIfinstrInstr(AIfinstrInstr node)
    {
        defaultIn(node);
    }

    public void outAIfinstrInstr(AIfinstrInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfinstrInstr(AIfinstrInstr node)
    {
        inAIfinstrInstr(node);
        if(node.getIfinstr() != null)
        {
            node.getIfinstr().apply(this);
        }
        outAIfinstrInstr(node);
    }

    public void inAWhileinstrInstr(AWhileinstrInstr node)
    {
        defaultIn(node);
    }

    public void outAWhileinstrInstr(AWhileinstrInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWhileinstrInstr(AWhileinstrInstr node)
    {
        inAWhileinstrInstr(node);
        if(node.getWhileinstr() != null)
        {
            node.getWhileinstr().apply(this);
        }
        outAWhileinstrInstr(node);
    }

    public void inACallinstrInstr(ACallinstrInstr node)
    {
        defaultIn(node);
    }

    public void outACallinstrInstr(ACallinstrInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACallinstrInstr(ACallinstrInstr node)
    {
        inACallinstrInstr(node);
        if(node.getCallinstr() != null)
        {
            node.getCallinstr().apply(this);
        }
        outACallinstrInstr(node);
    }

    public void inARetinstrInstr(ARetinstrInstr node)
    {
        defaultIn(node);
    }

    public void outARetinstrInstr(ARetinstrInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARetinstrInstr(ARetinstrInstr node)
    {
        inARetinstrInstr(node);
        if(node.getRetinstr() != null)
        {
            node.getRetinstr().apply(this);
        }
        outARetinstrInstr(node);
    }

    public void inAWriteinstrInstr(AWriteinstrInstr node)
    {
        defaultIn(node);
    }

    public void outAWriteinstrInstr(AWriteinstrInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWriteinstrInstr(AWriteinstrInstr node)
    {
        inAWriteinstrInstr(node);
        if(node.getWriteinstr() != null)
        {
            node.getWriteinstr().apply(this);
        }
        outAWriteinstrInstr(node);
    }

    public void inAEmptyinstrInstr(AEmptyinstrInstr node)
    {
        defaultIn(node);
    }

    public void outAEmptyinstrInstr(AEmptyinstrInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEmptyinstrInstr(AEmptyinstrInstr node)
    {
        inAEmptyinstrInstr(node);
        if(node.getEmptyinstr() != null)
        {
            node.getEmptyinstr().apply(this);
        }
        outAEmptyinstrInstr(node);
    }

    public void inAInstrblocInstr(AInstrblocInstr node)
    {
        defaultIn(node);
    }

    public void outAInstrblocInstr(AInstrblocInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrblocInstr(AInstrblocInstr node)
    {
        inAInstrblocInstr(node);
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        outAInstrblocInstr(node);
    }

    public void inAAssinstr(AAssinstr node)
    {
        defaultIn(node);
    }

    public void outAAssinstr(AAssinstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssinstr(AAssinstr node)
    {
        inAAssinstr(node);
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getEquals() != null)
        {
            node.getEquals().apply(this);
        }
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        outAAssinstr(node);
    }

    public void inAIfthenIfinstr(AIfthenIfinstr node)
    {
        defaultIn(node);
    }

    public void outAIfthenIfinstr(AIfthenIfinstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfthenIfinstr(AIfthenIfinstr node)
    {
        inAIfthenIfinstr(node);
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        if(node.getThen() != null)
        {
            node.getThen().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        outAIfthenIfinstr(node);
    }

    public void inAIfthenelseIfinstr(AIfthenelseIfinstr node)
    {
        defaultIn(node);
    }

    public void outAIfthenelseIfinstr(AIfthenelseIfinstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfthenelseIfinstr(AIfthenelseIfinstr node)
    {
        inAIfthenelseIfinstr(node);
        if(node.getElseinstr() != null)
        {
            node.getElseinstr().apply(this);
        }
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        if(node.getThen() != null)
        {
            node.getThen().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        outAIfthenelseIfinstr(node);
    }

    public void inAElseinstr(AElseinstr node)
    {
        defaultIn(node);
    }

    public void outAElseinstr(AElseinstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAElseinstr(AElseinstr node)
    {
        inAElseinstr(node);
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        if(node.getElse() != null)
        {
            node.getElse().apply(this);
        }
        outAElseinstr(node);
    }

    public void inAWhileinstr(AWhileinstr node)
    {
        defaultIn(node);
    }

    public void outAWhileinstr(AWhileinstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWhileinstr(AWhileinstr node)
    {
        inAWhileinstr(node);
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        if(node.getDo() != null)
        {
            node.getDo().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getWhile() != null)
        {
            node.getWhile().apply(this);
        }
        outAWhileinstr(node);
    }

    public void inACallinstr(ACallinstr node)
    {
        defaultIn(node);
    }

    public void outACallinstr(ACallinstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACallinstr(ACallinstr node)
    {
        inACallinstr(node);
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getCall() != null)
        {
            node.getCall().apply(this);
        }
        outACallinstr(node);
    }

    public void inACall(ACall node)
    {
        defaultIn(node);
    }

    public void outACall(ACall node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACall(ACall node)
    {
        inACall(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getListofexp() != null)
        {
            node.getListofexp().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outACall(node);
    }

    public void inARetinstr(ARetinstr node)
    {
        defaultIn(node);
    }

    public void outARetinstr(ARetinstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARetinstr(ARetinstr node)
    {
        inARetinstr(node);
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getReturn() != null)
        {
            node.getReturn().apply(this);
        }
        outARetinstr(node);
    }

    public void inAWriteinstr(AWriteinstr node)
    {
        defaultIn(node);
    }

    public void outAWriteinstr(AWriteinstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWriteinstr(AWriteinstr node)
    {
        inAWriteinstr(node);
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getWrite() != null)
        {
            node.getWrite().apply(this);
        }
        outAWriteinstr(node);
    }

    public void inAEmptyinstr(AEmptyinstr node)
    {
        defaultIn(node);
    }

    public void outAEmptyinstr(AEmptyinstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEmptyinstr(AEmptyinstr node)
    {
        inAEmptyinstr(node);
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        outAEmptyinstr(node);
    }

    public void inAInstrbloc(AInstrbloc node)
    {
        defaultIn(node);
    }

    public void outAInstrbloc(AInstrbloc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrbloc(AInstrbloc node)
    {
        inAInstrbloc(node);
        if(node.getRCurbrac() != null)
        {
            node.getRCurbrac().apply(this);
        }
        if(node.getInstrblocbis() != null)
        {
            node.getInstrblocbis().apply(this);
        }
        if(node.getLCurbrac() != null)
        {
            node.getLCurbrac().apply(this);
        }
        outAInstrbloc(node);
    }

    public void inAInstrInstrblocbis(AInstrInstrblocbis node)
    {
        defaultIn(node);
    }

    public void outAInstrInstrblocbis(AInstrInstrblocbis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrInstrblocbis(AInstrInstrblocbis node)
    {
        inAInstrInstrblocbis(node);
        if(node.getInstrblocbis() != null)
        {
            node.getInstrblocbis().apply(this);
        }
        if(node.getInstr() != null)
        {
            node.getInstr().apply(this);
        }
        outAInstrInstrblocbis(node);
    }

    public void inANoinstrInstrblocbis(ANoinstrInstrblocbis node)
    {
        defaultIn(node);
    }

    public void outANoinstrInstrblocbis(ANoinstrInstrblocbis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANoinstrInstrblocbis(ANoinstrInstrblocbis node)
    {
        inANoinstrInstrblocbis(node);
        outANoinstrInstrblocbis(node);
    }
}
