/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AAssinstr extends PAssinstr
{
    private PVar _var_;
    private TEquals _equals_;
    private PExp _exp_;
    private TSemicolon _semicolon_;

    public AAssinstr()
    {
        // Constructor
    }

    public AAssinstr(
        @SuppressWarnings("hiding") PVar _var_,
        @SuppressWarnings("hiding") TEquals _equals_,
        @SuppressWarnings("hiding") PExp _exp_,
        @SuppressWarnings("hiding") TSemicolon _semicolon_)
    {
        // Constructor
        setVar(_var_);

        setEquals(_equals_);

        setExp(_exp_);

        setSemicolon(_semicolon_);

    }

    @Override
    public Object clone()
    {
        return new AAssinstr(
            cloneNode(this._var_),
            cloneNode(this._equals_),
            cloneNode(this._exp_),
            cloneNode(this._semicolon_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAssinstr(this);
    }

    public PVar getVar()
    {
        return this._var_;
    }

    public void setVar(PVar node)
    {
        if(this._var_ != null)
        {
            this._var_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._var_ = node;
    }

    public TEquals getEquals()
    {
        return this._equals_;
    }

    public void setEquals(TEquals node)
    {
        if(this._equals_ != null)
        {
            this._equals_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._equals_ = node;
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
            + toString(this._var_)
            + toString(this._equals_)
            + toString(this._exp_)
            + toString(this._semicolon_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._var_ == child)
        {
            this._var_ = null;
            return;
        }

        if(this._equals_ == child)
        {
            this._equals_ = null;
            return;
        }

        if(this._exp_ == child)
        {
            this._exp_ = null;
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
        if(this._var_ == oldChild)
        {
            setVar((PVar) newChild);
            return;
        }

        if(this._equals_ == oldChild)
        {
            setEquals((TEquals) newChild);
            return;
        }

        if(this._exp_ == oldChild)
        {
            setExp((PExp) newChild);
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
