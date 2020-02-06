/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class TRead extends Token
{
    public TRead()
    {
        super.setText("lire");
    }

    public TRead(int line, int pos)
    {
        super.setText("lire");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TRead(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTRead(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TRead text.");
    }
}
