/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ACallinstrInstr extends PInstr
{
    private PCallinstr _callinstr_;

    public ACallinstrInstr()
    {
        // Constructor
    }

    public ACallinstrInstr(
        @SuppressWarnings("hiding") PCallinstr _callinstr_)
    {
        // Constructor
        setCallinstr(_callinstr_);

    }

    @Override
    public Object clone()
    {
        return new ACallinstrInstr(
            cloneNode(this._callinstr_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACallinstrInstr(this);
    }

    public PCallinstr getCallinstr()
    {
        return this._callinstr_;
    }

    public void setCallinstr(PCallinstr node)
    {
        if(this._callinstr_ != null)
        {
            this._callinstr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._callinstr_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._callinstr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._callinstr_ == child)
        {
            this._callinstr_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._callinstr_ == oldChild)
        {
            setCallinstr((PCallinstr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
