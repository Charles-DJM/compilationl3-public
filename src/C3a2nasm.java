import c3a.*;
import nasm.*;
import ts.Ts;

public class C3a2nasm implements C3aVisitor <NasmOperand> {
    private Nasm nasm;
    private Ts table;
    // Afin de savoir dans quelle fonction nous sommes
    private Ts function;
    // Base pointer : calcul adresses de variables locales et de paramètres
    private NasmRegister ebp = new NasmRegister(Nasm.REG_EBP);
    // Stack pointer : Adresse relative du sommet de la pile
    private NasmRegister esp = new NasmRegister(Nasm.REG_ESP);

    public C3a2nasm(C3a c3a, Ts table) {
        nasm = new Nasm(table);
        this.table = table;
        ebp.colorRegister(Nasm.REG_EBP);
        esp.colorRegister(Nasm.REG_ESP);

        // On initialize le compteur de registre au nombre de temporaires existante dans le code c3a
        // Afin de pouvoir utiliser les mêmes identifiants pour les temporaires déjà existantes
        nasm.setTempCounter(c3a.getTempCounter());

        // Accumulator : Pour le stockage de la valeur de retour
        NasmRegister eax = new NasmRegister(Nasm.REG_EAX);
        eax.colorRegister(Nasm.REG_EAX);
        // Base Register : pointeur de donnée
        NasmRegister ebx = new NasmRegister(Nasm.REG_EBX);
        ebx.colorRegister(Nasm.REG_EBX);

        // Initialisation du programme
        nasm.ajouteInst(new NasmCall(null, new NasmLabel("main"), ""));
        nasm.ajouteInst(new NasmMov(null, ebx, new NasmConstant(0), " valeur de retour du programme"));
        nasm.ajouteInst(new NasmMov(null, eax, new NasmConstant(1), ""));
        nasm.ajouteInst(new NasmInt(null, ""));

        // Parcours du code trois adresses et transformation en code pré-machine
        for(C3aInst inst : c3a.listeInst) {
            inst.accept(this);
        }
    }

    // Opérandes

    // Constante
    @Override
    public NasmOperand visit(C3aConstant op) {
        return new NasmConstant(op.val);
    }

    // Temporaire
    @Override
    public NasmOperand visit(C3aTemp op) {
        return new NasmRegister(op.num);
    }

    // Label
    @Override
    public NasmOperand visit(C3aLabel op) {
        return new NasmLabel(op.toString());
    }

    // Fonction
    @Override
    public NasmOperand visit(C3aFunction op) {
        return new NasmLabel(op.toString());
    }

    // Variable
    @Override
    public NasmOperand visit(C3aVar op) {
        // La base
        NasmOperand base;
        // La direction dans laquelle nous allons potentiellement chercher la variable
        char direction;
        // Le décalage nécessaire pour trouver la variable
        NasmConstant decalage;

        // Si la variable est globale
        if(op.item.portee == table) {
            // On associe la variable à une étiquette
            base = new NasmLabel(op.item.identif);
            // Si la variable globale n'est pas un tableau, on a déjà son addresse
            if(op.item.getTaille() == 1) {
                return new NasmAddress(base);
            }
        }
        // Sinon, la variable est locale
        else {
            // On mettra la variable dans la pile, sans étiquette
            base = ebp;
        }

        // Si c'est une variable locale
        if(!(op.item.portee == table || op.item.isParam)) {
            // Alors elle est positionnée dans les adresses inférieures à ebp
            direction = '-';
        }
        // Sinon ce sera dans les adresses supérieures
        else {
            direction = '+';
        }

        // Si la variable est un tableau
        if(op.item.getTaille() > 1) {
            // On récupère l'indexe
            decalage = new NasmConstant(((C3aConstant) op.index).val);
        }
        else {
            // Si c'est un paramètre
            if(op.item.isParam) {
                // arg_i -> [ebp + 8 + 4 * nb_args - 4 * i]
                decalage = new NasmConstant(2 + op.item.portee.nbArg() - op.item.getAdresse());
            }
            // Sinon c'est une variable locale
            else {
                // local_i -> [ebp - 4 * i]
                decalage = new NasmConstant(1 + op.item.getAdresse());
            }
        }

        // On retourne l'addresse
        return new NasmAddress(base, direction, decalage);
    }

    // Instructions

    // Addition
    @Override
    public NasmOperand visit(C3aInstAdd inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);

        // On copie la première opérange dans la destination
        nasm.ajouteInst(new NasmMov(label, dest, op1, ""));
        // On additionne la destination avec la deuxième opérande
        nasm.ajouteInst(new NasmAdd(null, dest, op2, ""));

        return null;
    }

    // Soustraction
    @Override
    public NasmOperand visit(C3aInstSub inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);

        // On copie la première opérange dans la destination
        nasm.ajouteInst(new NasmMov(label, dest, op1, ""));
        // On soustrait la destination avec la deuxième opérande
        nasm.ajouteInst(new NasmSub(null, dest, op2, ""));

        return null;
    }

    // Multiplication
    @Override
    public NasmOperand visit(C3aInstMult inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);

        // On copie la première opérange dans la destination
        nasm.ajouteInst(new NasmMov(label, dest, op1, ""));
        // On multiplie la destination avec la deuxième opérande
        nasm.ajouteInst(new NasmMul(null, dest, op2, ""));

        return null;
    }

    // Division
    @Override
    public NasmOperand visit(C3aInstDiv inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);
        // Acumulator : Pour les opérations arithmétiques
        NasmRegister eax = new NasmRegister(Nasm.REG_EAX);
        eax.colorRegister(Nasm.REG_EAX);

        // La première opérande doit être mise dans eax
        nasm.ajouteInst(new NasmMov(label, eax, op1, ""));

        // Si op2 est une constante
        if(op2.getClass().equals(NasmConstant.class)) {
            // Alors on la met dans un nouveau registre
            NasmRegister reg = nasm.newRegister();
            nasm.ajouteInst(new NasmMov(null, reg, op2, ""));
            // On divise eax par ebx
            nasm.ajouteInst(new NasmDiv(null, reg, ""));
        }
        // Sinon, pas besoin d'un nouveau registre
        else {
            // On divise directement par op2
            nasm.ajouteInst(new NasmDiv(null, op2, ""));
        }

        // On déplace eax là où on souhaite avoir le résultat
        nasm.ajouteInst(new NasmMov(null, dest, eax, ""));

        return null;
    }

    // Début de fonction
    @Override
    public NasmOperand visit(C3aInstFBegin inst) {
        function = inst.val.getTable();

        NasmOperand label = new NasmLabel(inst.val.identif);
        // Sauvegarde dans la pile l'ancienne valeur de ebp
        nasm.ajouteInst(new NasmPush(label, ebp, "sauvegarde la valeur de ebp"));
        // Nouvelle valeur de ebp
        nasm.ajouteInst(new NasmMov(null, ebp, esp, "nouvelle valeur de ebp"));
        // Allocation de mémoire dans la pile pour les variables locales
        nasm.ajouteInst(new NasmSub(null, esp, new NasmConstant(4 * function.nbVar()), "allocation des variables locales"));

        return null;
    }

    // Fin de fonction
    @Override
    public NasmOperand visit(C3aInstFEnd inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        // Désallocation de la mémoire occupé par les variables locales
        nasm.ajouteInst(new NasmAdd(label, esp, new NasmConstant(4 * function.nbVar()), "désallocation des variables locales"));
        // On restaure l'ancienne valeur de ebp
        nasm.ajouteInst(new NasmPop(null, ebp, "restaure la valeur de ebp"));
        // On recupère la valeur empilé
        nasm.ajouteInst(new NasmRet(null, ""));

        return null;
    }

    // Retour
    @Override
    public NasmOperand visit(C3aInstReturn inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        // ebp + 4 * 2
        NasmAddress address = new NasmAddress(ebp, '+', new NasmConstant(2));

        // On déplace la valeur de retour à l'adresse dédiée
        nasm.ajouteInst(new NasmMov(label, address, inst.op1.accept(this), "ecriture de la valeur de retour"));

        return null;
    }

    // Paramètres de fonction
    @Override
    public NasmOperand visit(C3aInstParam inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        // On push dans la pile le paramètre
        nasm.ajouteInst(new NasmPush(label, inst.op1.accept(this), "Param"));

        return null;
    }

    // Saut sans condition
    @Override
    public NasmOperand visit(C3aInstJump inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        // On saute
        nasm.ajouteInst(new NasmJmp(label, inst.result.accept(this), "Jump"));

        return null;
    }

    // Saut sous condition d'égalité
    @Override
    public NasmOperand visit(C3aInstJumpIfEqual inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        // On compare les deux opérandes
        nasm.ajouteInst(new NasmCmp(label, inst.op1.accept(this), inst.op2.accept(this), "JumpIfEqual 1"));
        // On saute si c'est égal
        nasm.ajouteInst(new NasmJe(null, inst.result.accept(this), "JumpIfEqual 2"));

        return null;
    }

    // Saut sous condition d'inégalité
    @Override
    public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        // On compare les deux opérandes
        nasm.ajouteInst(new NasmCmp(label, inst.op1.accept(this), inst.op2.accept(this), "jumpIfNotEqual 1"));
        // On saute si c'est pas égal
        nasm.ajouteInst(new NasmJne(null, inst.result.accept(this), "jumpIfNotEqual 2"));

        return null;
    }

    // Saut sous condition d'inferiorité
    @Override
    public NasmOperand visit(C3aInstJumpIfLess inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        // On compare les deux opérandes
        nasm.ajouteInst(new NasmCmp(label, inst.op1.accept(this), inst.op2.accept(this), "JumpIfLess 1"));
        // On saute si c'est inférieur
        nasm.ajouteInst(new NasmJl(null, inst.result.accept(this), "JumpIfLess 2"));

        return null;
    }

    // Affectation
    @Override
    public NasmOperand visit(C3aInstAffect inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        // On déplace la partie droite de l'affectation dans la partie gauche
        nasm.ajouteInst(new NasmMov(label, inst.result.accept(this), inst.op1.accept(this), "Affect"));

        return null;
    }

    // Appel
    @Override
    public NasmOperand visit(C3aInstCall inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        // Allocation de quatres octets dans la pile pour stocker la valeur de retour
        nasm.ajouteInst(new NasmSub(label, esp, new NasmConstant(4), "allocation mémoire pour la valeur de retour"));
        // Appel de fonction
        nasm.ajouteInst(new NasmCall(null, inst.op1.accept(this), ""));
        // Récupération de la valeur de retour
        nasm.ajouteInst(new NasmPop(null, inst.result.accept(this), "récupération de la valeur de retour"));
        // Si on avait des paramètres
        if(table.getFct(inst.op1.toString()).nbArgs > 0) {
            // Désallocation de l'espace occupé dans la pile par les paramètres
            nasm.ajouteInst(new NasmAdd(null, esp, new NasmConstant(function.nbArg() * 4), "désallocation des arguments"));
        }

        return null;
    }

    // Lire
    @Override
    public NasmOperand visit(C3aInstRead inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand dest = inst.result.accept(this);
        // Acumulator : Pour les opérations arithmétiques
        NasmRegister eax = new NasmRegister(Nasm.REG_EAX);
        eax.colorRegister(Nasm.REG_EAX);

        // On met dans eax l'addresse où se situeront les lignes écrites
        nasm.ajouteInst(new NasmMov(label, eax, new NasmConstant(2), ""));
        // On appelle readline qui lit une ligne à l'adresse pointée par eax
        nasm.ajouteInst(new NasmCall(null, new NasmLabel("readline"), ""));
        // On appelle atoi qui met dans eax l'entier obtenu de la chaîne pointée par eax
        nasm.ajouteInst(new NasmCall(null, new NasmLabel("atoi"), ""));
        // On déplace eax là où on souhaite avoir le résultat
        nasm.ajouteInst(new NasmMov(null, dest, eax, ""));

        return null;
    }

    // Ecrire
    @Override
    public NasmOperand visit(C3aInstWrite inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        // Acumulator : Pour les opérations arithmétiques
        NasmRegister eax = new NasmRegister(Nasm.REG_EAX);
        eax.colorRegister(Nasm.REG_EAX);

        // On déplace ce qu'on cherche à écrire dans eax
        nasm.ajouteInst(new NasmMov(label, eax, inst.op1.accept(this), "Write 1"));
        // On appelle iprintLF qui affiche le contenu dans eax
        nasm.ajouteInst(new NasmCall(null, new NasmLabel("iprintLF"), "Write 2"));

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
