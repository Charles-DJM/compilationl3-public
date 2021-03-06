/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ACallExp5 extends PExp5
{
    private PCall _call_;

    public ACallExp5()
    {
        // Constructor
    }

    public ACallExp5(
        @SuppressWarnings("hiding") PCall _call_)
    {
        // Constructor
        setCall(_call_);

    }

    @Override
    public Object clone()
    {
        return new ACallExp5(
            cloneNode(this._call_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACallExp5(this);
    }

    public PCall getCall()
    {
        return this._call_;
    }

    public void setCall(PCall node)
    {
        if(this._call_ != null)
        {
            this._call_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._call_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._call_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._call_ == child)
        {
            this._call_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._call_ == oldChild)
        {
            setCall((PCall) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
