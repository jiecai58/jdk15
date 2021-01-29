/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package jdk.javadoc.internal.doclets.formats.html.markup;

import jdk.javadoc.internal.doclets.toolkit.Content;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Content for the {@code <body>} element.
 *
 * The content is a {@code <div>} element that contains a
 * header that is always visible, and main content that
 * can be scrolled if necessary.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class BodyContents extends Content {

    private List<Content> mainContents = new ArrayList<>();
    private Content header = HtmlTree.EMPTY;
    private Content footer = HtmlTree.EMPTY;

    public BodyContents addMainContent(Content content) {
        mainContents.add(content);
        return this;
    }

    public BodyContents setHeader(Content header) {
        this.header = header;
        return this;
    }

    public BodyContents setFooter(Content footer) {
        this.footer = footer;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation always returns {@code false}.
     *
     * @return {@code false}
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean write(Writer out, boolean atNewline) throws IOException {
        return toContent().write(out, atNewline);
    }

    /**
     * Returns the HTML for the contents of the BODY element.
     *
     * @return the HTML
     */
    private Content toContent() {
        HtmlTree flexHeader = HtmlTree.HEADER()
                .setStyle(HtmlStyle.flexHeader)
                .add(header);

        HtmlTree flexContent = HtmlTree.DIV(HtmlStyle.flexContent)
                .add(HtmlTree.MAIN().add(mainContents))
                .add(footer);

        return HtmlTree.DIV(HtmlStyle.flexBox)
                .add(flexHeader)
                .add(flexContent);
    }
}
