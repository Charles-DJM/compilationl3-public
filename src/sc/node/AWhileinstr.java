/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AWhileinstr extends PWhileinstr
{
    private TWhile _while_;
    private PExp _exp_;
    private TDo _do_;
    private PInstrbloc _instrbloc_;

    public AWhileinstr()
    {
        // Constructor
    }

    public AWhileinstr(
        @SuppressWarnings("hiding") TWhile _while_,
        @SuppressWarnings("hiding") PExp _exp_,
        @SuppressWarnings("hiding") TDo _do_,
        @SuppressWarnings("hiding") PInstrbloc _instrbloc_)
    {
        // Constructor
        setWhile(_while_);

        setExp(_exp_);

        setDo(_do_);

        setInstrbloc(_instrbloc_);

    }

    @Override
    public Object clone()
    {
        return new AWhileinstr(
            cloneNode(this._while_),
            cloneNode(this._exp_),
            cloneNode(this._do_),
            cloneNode(this._instrbloc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAWhileinstr(this);
    }

    public TWhile getWhile()
    {
        return this._while_;
    }

    public void setWhile(TWhile node)
    {
        if(this._while_ != null)
        {
            this._while_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._while_ = node;
    }

    public PExp getExp()
    {
        return this._exp_;
    }

    public void setExp(PExp node)
    {
        if(this._exp_ != null)
        {
            this._exp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exp_ = node;
    }

    public TDo getDo()
    {
        return this._do_;
    }

    public void setDo(TDo node)
    {
        if(this._do_ != null)
        {
            this._do_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._do_ = node;
    }

    public PInstrbloc getInstrbloc()
    {
        return this._instrbloc_;
    }

    public void setInstrbloc(PInstrbloc node)
    {
        if(this._instrbloc_ != null)
        {
            this._instrbloc_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._instrbloc_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._while_)
            + toString(this._exp_)
            + toString(this._do_)
            + toString(this._instrbloc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._while_ == child)
        {
            this._while_ = null;
            return;
        }

        if(this._exp_ == child)
        {
            this._exp_ = null;
            return;
        }

        if(this._do_ == child)
        {
            this._do_ = null;
            return;
        }

        if(this._instrbloc_ == child)
        {
            this._instrbloc_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._while_ == oldChild)
        {
            setWhile((TWhile) newChild);
            return;
        }

        if(this._exp_ == oldChild)
        {
            setExp((PExp) newChild);
            return;
        }

        if(this._do_ == oldChild)
        {
            setDo((TDo) newChild);
            return;
        }

        if(this._instrbloc_ == oldChild)
        {
            setInstrbloc((PInstrbloc) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
