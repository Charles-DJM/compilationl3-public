/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AInstrbloc extends PInstrbloc
{
    private TLCurbrac _lCurbrac_;
    private PInstrblocbis _instrblocbis_;
    private TRCurbrac _rCurbrac_;

    public AInstrbloc()
    {
        // Constructor
    }

    public AInstrbloc(
        @SuppressWarnings("hiding") TLCurbrac _lCurbrac_,
        @SuppressWarnings("hiding") PInstrblocbis _instrblocbis_,
        @SuppressWarnings("hiding") TRCurbrac _rCurbrac_)
    {
        // Constructor
        setLCurbrac(_lCurbrac_);

        setInstrblocbis(_instrblocbis_);

        setRCurbrac(_rCurbrac_);

    }

    @Override
    public Object clone()
    {
        return new AInstrbloc(
            cloneNode(this._lCurbrac_),
            cloneNode(this._instrblocbis_),
            cloneNode(this._rCurbrac_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInstrbloc(this);
    }

    public TLCurbrac getLCurbrac()
    {
        return this._lCurbrac_;
    }

    public void setLCurbrac(TLCurbrac node)
    {
        if(this._lCurbrac_ != null)
        {
            this._lCurbrac_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lCurbrac_ = node;
    }

    public PInstrblocbis getInstrblocbis()
    {
        return this._instrblocbis_;
    }

    public void setInstrblocbis(PInstrblocbis node)
    {
        if(this._instrblocbis_ != null)
        {
            this._instrblocbis_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._instrblocbis_ = node;
    }

    public TRCurbrac getRCurbrac()
    {
        return this._rCurbrac_;
    }

    public void setRCurbrac(TRCurbrac node)
    {
        if(this._rCurbrac_ != null)
        {
            this._rCurbrac_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rCurbrac_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._lCurbrac_)
            + toString(this._instrblocbis_)
            + toString(this._rCurbrac_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._lCurbrac_ == child)
        {
            this._lCurbrac_ = null;
            return;
        }

        if(this._instrblocbis_ == child)
        {
            this._instrblocbis_ = null;
            return;
        }

        if(this._rCurbrac_ == child)
        {
            this._rCurbrac_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._lCurbrac_ == oldChild)
        {
            setLCurbrac((TLCurbrac) newChild);
            return;
        }

        if(this._instrblocbis_ == oldChild)
        {
            setInstrblocbis((PInstrblocbis) newChild);
            return;
        }

        if(this._rCurbrac_ == oldChild)
        {
            setRCurbrac((TRCurbrac) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
