/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AIntVardec extends PVardec
{
    private TInt _int_;
    private TId _id_;

    public AIntVardec()
    {
        // Constructor
    }

    public AIntVardec(
        @SuppressWarnings("hiding") TInt _int_,
        @SuppressWarnings("hiding") TId _id_)
    {
        // Constructor
        setInt(_int_);

        setId(_id_);

    }

    @Override
    public Object clone()
    {
        return new AIntVardec(
            cloneNode(this._int_),
            cloneNode(this._id_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIntVardec(this);
    }

    public TInt getInt()
    {
        return this._int_;
    }

    public void setInt(TInt node)
    {
        if(this._int_ != null)
        {
            this._int_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._int_ = node;
    }

    public TId getId()
    {
        return this._id_;
    }

    public void setId(TId node)
    {
        if(this._id_ != null)
        {
            this._id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._id_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._int_)
            + toString(this._id_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._int_ == child)
        {
            this._int_ = null;
            return;
        }

        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._int_ == oldChild)
        {
            setInt((TInt) newChild);
            return;
        }

        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
