/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ARetinstrInstr extends PInstr
{
    private PRetinstr _retinstr_;

    public ARetinstrInstr()
    {
        // Constructor
    }

    public ARetinstrInstr(
        @SuppressWarnings("hiding") PRetinstr _retinstr_)
    {
        // Constructor
        setRetinstr(_retinstr_);

    }

    @Override
    public Object clone()
    {
        return new ARetinstrInstr(
            cloneNode(this._retinstr_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseARetinstrInstr(this);
    }

    public PRetinstr getRetinstr()
    {
        return this._retinstr_;
    }

    public void setRetinstr(PRetinstr node)
    {
        if(this._retinstr_ != null)
        {
            this._retinstr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._retinstr_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._retinstr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._retinstr_ == child)
        {
            this._retinstr_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._retinstr_ == oldChild)
        {
            setRetinstr((PRetinstr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
