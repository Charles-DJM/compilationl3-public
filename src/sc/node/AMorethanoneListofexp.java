/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AMorethanoneListofexp extends PListofexp
{
    private PExp _exp_;
    private PListofexpbis _listofexpbis_;

    public AMorethanoneListofexp()
    {
        // Constructor
    }

    public AMorethanoneListofexp(
        @SuppressWarnings("hiding") PExp _exp_,
        @SuppressWarnings("hiding") PListofexpbis _listofexpbis_)
    {
        // Constructor
        setExp(_exp_);

        setListofexpbis(_listofexpbis_);

    }

    @Override
    public Object clone()
    {
        return new AMorethanoneListofexp(
            cloneNode(this._exp_),
            cloneNode(this._listofexpbis_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMorethanoneListofexp(this);
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

    public PListofexpbis getListofexpbis()
    {
        return this._listofexpbis_;
    }

    public void setListofexpbis(PListofexpbis node)
    {
        if(this._listofexpbis_ != null)
        {
            this._listofexpbis_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listofexpbis_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._exp_)
            + toString(this._listofexpbis_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._exp_ == child)
        {
            this._exp_ = null;
            return;
        }

        if(this._listofexpbis_ == child)
        {
            this._listofexpbis_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._exp_ == oldChild)
        {
            setExp((PExp) newChild);
            return;
        }

        if(this._listofexpbis_ == oldChild)
        {
            setListofexpbis((PListofexpbis) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}