import c3a.*;
import nasm.*;
import ts.Ts;

public class C3a2nasm implements C3aVisitor {
    private Nasm nasm;
    private C3a c3a;
    private Ts table;

    public C3a2nasm(C3a c3a, Ts table) {
        nasm = new Nasm(table);
        this.c3a = c3a;
        this.table = table;
    }

    // Opérandes

    // Constante
    @Override
    public NasmOperand visit(C3aConstant op) {
        return null;
    }

    // Variable
    @Override
    public NasmOperand visit(C3aVar op) {
        return null;
    }

    // Temporaire
    @Override
    public NasmOperand visit(C3aTemp op) {
        return null;
    }

    // Label
    @Override
    public NasmOperand visit(C3aLabel op) {
        return null;
    }

    // Fonction
    @Override
    public NasmOperand visit(C3aFunction op) {
        return null;
    }

    // Instructions

    // Addition
    @Override
    public NasmOperand visit(C3aInstAdd inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);
        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmAdd(null, dest, oper2, ""));
        return null;
    }

    // Soustraction
    @Override
    public NasmOperand visit(C3aInstSub inst) {
        return null;
    }

    // Multiplication
    @Override
    public NasmOperand visit(C3aInstMult inst) {
        return null;
    }

    // Division
    @Override
    public NasmOperand visit(C3aInstDiv inst) {
        return null;
    }

    // Début de fonction
    @Override
    public NasmOperand visit(C3aInstFBegin inst) {
        return null;
    }

    // Fin de fonction
    @Override
    public NasmOperand visit(C3aInstFEnd inst) {
        return null;
    }

    // Paramètres de fonction
    @Override
    public NasmOperand visit(C3aInstParam inst) {
        return null;
    }

    // Saut sans condition
    @Override
    public NasmOperand visit(C3aInstJump inst) {
        return null;
    }

    // Saut sous condition d'égalité
    @Override
    public NasmOperand visit(C3aInstJumpIfEqual inst) {
        return null;
    }

    // Saut sous condition d'inferiorité
    @Override
    public NasmOperand visit(C3aInstJumpIfLess inst) {
        return null;
    }

    // Saut sous condition d'inégalité
    @Override
    public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
        return null;
    }

    // Affectation
    @Override
    public NasmOperand visit(C3aInstAffect inst) {
        return null;
    }

    // Appel
    @Override
    public NasmOperand visit(C3aInstCall inst) {
        return null;
    }

    // Lire
    @Override
    public NasmOperand visit(C3aInstRead inst) {
        return null;
    }

    // Ecrire
    @Override
    public NasmOperand visit(C3aInstWrite inst) {
        return null;
    }

    // Retour
    @Override
    public NasmOperand visit(C3aInstReturn inst) {
        return null;
    }

    // Instruction
    @Override
    public NasmOperand visit(C3aInst inst) {
        return null;
    }

    public Nasm getNasm() {
        return nasm;
    }
}
