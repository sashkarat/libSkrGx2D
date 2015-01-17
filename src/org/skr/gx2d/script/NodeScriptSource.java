package org.skr.gx2d.script;

/**
 * Created by rat on 04.11.14.
 */
public class NodeScriptSource {
    String sourceText = "";

    public NodeScriptSource() {
    }

    public NodeScriptSource(NodeScriptSource cpy) {
        sourceText = cpy.sourceText;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

}
