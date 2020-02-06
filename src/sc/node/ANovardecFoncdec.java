/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ANovardecFoncdec extends PFoncdec
{
    private TId _id_;
    private PParamlist _paramlist_;
    private PInstrbloc _instrbloc_;

    public ANovardecFoncdec()
    {
        // Constructor
    }

    public ANovardecFoncdec(
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") PParamlist _paramlist_,
        @SuppressWarnings("hiding") PInstrbloc _instrbloc_)
    {
        // Constructor
        setId(_id_);

        setParamlist(_paramlist_);

        setInstrbloc(_instrbloc_);

    }

    @Override
    public Object clone()
    {
        return new ANovardecFoncdec(
            cloneNode(this._id_),
            cloneNode(this._paramlist_),
            cloneNode(this._instrbloc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANovardecFoncdec(this);
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

    public PParamlist getParamlist()
    {
        return this._paramlist_;
    }

    public void setParamlist(PParamlist node)
    {
        if(this._paramlist_ != null)
        {
            this._paramlist_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._paramlist_ = node;
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
            + toString(this._id_)
            + toString(this._paramlist_)
            + toString(this._instrbloc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._paramlist_ == child)
        {
            this._paramlist_ = null;
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
        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(this._paramlist_ == oldChild)
        {
            setParamlist((PParamlist) newChild);
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
