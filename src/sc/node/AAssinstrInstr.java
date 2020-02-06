/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AAssinstrInstr extends PInstr
{
    private PAssinstr _assinstr_;

    public AAssinstrInstr()
    {
        // Constructor
    }

    public AAssinstrInstr(
        @SuppressWarnings("hiding") PAssinstr _assinstr_)
    {
        // Constructor
        setAssinstr(_assinstr_);

    }

    @Override
    public Object clone()
    {
        return new AAssinstrInstr(
            cloneNode(this._assinstr_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAssinstrInstr(this);
    }

    public PAssinstr getAssinstr()
    {
        return this._assinstr_;
    }

    public void setAssinstr(PAssinstr node)
    {
        if(this._assinstr_ != null)
        {
            this._assinstr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._assinstr_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._assinstr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._assinstr_ == child)
        {
            this._assinstr_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._assinstr_ == oldChild)
        {
            setAssinstr((PAssinstr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
