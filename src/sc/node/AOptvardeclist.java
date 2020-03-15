/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AOptvardeclist extends POptvardeclist
{
    private PVardeclist _vardeclist_;
    private TSemicolon _semicolon_;

    public AOptvardeclist()
    {
        // Constructor
    }

    public AOptvardeclist(
        @SuppressWarnings("hiding") PVardeclist _vardeclist_,
        @SuppressWarnings("hiding") TSemicolon _semicolon_)
    {
        // Constructor
        setVardeclist(_vardeclist_);

        setSemicolon(_semicolon_);

    }

    @Override
    public Object clone()
    {
        return new AOptvardeclist(
            cloneNode(this._vardeclist_),
            cloneNode(this._semicolon_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAOptvardeclist(this);
    }

    public PVardeclist getVardeclist()
    {
        return this._vardeclist_;
    }

    public void setVardeclist(PVardeclist node)
    {
        if(this._vardeclist_ != null)
        {
            this._vardeclist_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._vardeclist_ = node;
    }

    public TSemicolon getSemicolon()
    {
        return this._semicolon_;
    }

    public void setSemicolon(TSemicolon node)
    {
        if(this._semicolon_ != null)
        {
            this._semicolon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._semicolon_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._vardeclist_)
            + toString(this._semicolon_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._vardeclist_ == child)
        {
            this._vardeclist_ = null;
            return;
        }

        if(this._semicolon_ == child)
        {
            this._semicolon_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._vardeclist_ == oldChild)
        {
            setVardeclist((PVardeclist) newChild);
            return;
        }

        if(this._semicolon_ == oldChild)
        {
            setSemicolon((TSemicolon) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}