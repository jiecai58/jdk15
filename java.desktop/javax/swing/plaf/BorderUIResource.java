/*
 * Copyright (c) 1997, 2015, Oracle and/or its affiliates. All rights reserved.
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

package javax.swing.plaf;

import java.awt.Component;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

import java.beans.ConstructorProperties;
import javax.swing.border.*;
import javax.swing.Icon;
import javax.swing.plaf.UIResource;


/**
 * A Border wrapper class which implements UIResource.  UI
 * classes which set border properties should use this class
 * to wrap any borders specified as defaults.
 *
 * This class delegates all method invocations to the
 * Border "delegate" object specified at construction.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans
 * has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @see javax.swing.plaf.UIResource
 * @author Amy Fowler
 *
 */
@SuppressWarnings("serial") // Same-version serialization only
public class BorderUIResource implements Border, UIResource, Serializable
{
    static Border etched;
    static Border loweredBevel;
    static Border raisedBevel;
    static Border blackLine;

    /**
     * Returns a etched border UI resource.
     * @return a etched border UI resource
     */
    public static Border getEtchedBorderUIResource() {
        if (etched == null) {
            etched = new EtchedBorderUIResource();
        }
        return etched;
    }

    /**
     * Returns a lowered bevel border UI resource.
     * @return a lowered bevel border UI resource
     */
    public static Border getLoweredBevelBorderUIResource() {
        if (loweredBevel == null) {
            loweredBevel = new BevelBorderUIResource(BevelBorder.LOWERED);
        }
        return loweredBevel;
    }

    /**
     * Returns a raised bevel border UI resource.
     * @return a raised bevel border UI resource
     */
    public static Border getRaisedBevelBorderUIResource() {
        if (raisedBevel == null) {
            raisedBevel = new BevelBorderUIResource(BevelBorder.RAISED);
        }
        return raisedBevel;
    }

    /**
     * Returns a black line border UI resource.
     * @return a black line border UI resource
     */
    public static Border getBlackLineBorderUIResource() {
        if (blackLine == null) {
            blackLine = new LineBorderUIResource(Color.black);
        }
        return blackLine;
    }

    private Border delegate;

    /**
     * Creates a UIResource border object which wraps
     * an existing Border instance.
     * @param delegate the border being wrapped
     */
    public BorderUIResource(Border delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException("null border delegate argument");
        }
        this.delegate = delegate;
    }

    public void paintBorder(Component c, Graphics g, int x, int y,
                            int width, int height) {
        delegate.paintBorder(c, g, x, y, width, height);
    }

    public Insets getBorderInsets(Component c)       {
        return delegate.getBorderInsets(c);
    }

    public boolean isBorderOpaque() {
        return delegate.isBorderOpaque();
    }

    /**
     * A compound border UI resource.
     */
    public static class CompoundBorderUIResource extends CompoundBorder implements UIResource {
        /**
         * Constructs a {@code CompoundBorderUIResource}.
         * @param outsideBorder the outside border
         * @param insideBorder the inside border
         */
        @ConstructorProperties({"outsideBorder", "insideBorder"})
        public CompoundBorderUIResource(Border outsideBorder, Border insideBorder) {
            super(outsideBorder, insideBorder);
        }

    }

    /**
     * An empty border UI resource.
     */
    public static class EmptyBorderUIResource extends EmptyBorder implements UIResource {

        /**
         * Constructs an {@code EmptyBorderUIResource}.
         * @param top the top inset of the border
         * @param left the left inset of the border
         * @param bottom the bottom inset of the border
         * @param right the right inset of the border
         */
        public EmptyBorderUIResource(int top, int left, int bottom, int right)   {
            super(top, left, bottom, right);
        }
        /**
         * Constructs an {@code EmptyBorderUIResource}.
         * @param insets the insets of the border
         */
        @ConstructorProperties({"borderInsets"})
        public EmptyBorderUIResource(Insets insets) {
            super(insets);
        }
    }

    /**
     * A line border UI resource.
     */
    public static class LineBorderUIResource extends LineBorder implements UIResource {

        /**
         * Constructs a {@code LineBorderUIResource}.
         * @param color the color for the border
         */
        public LineBorderUIResource(Color color) {
            super(color);
        }

        /**
         * Constructs a {@code LineBorderUIResource}.
         * @param color the color for the border
         * @param thickness the thickness of the border
         */
        @ConstructorProperties({"lineColor", "thickness"})
        public LineBorderUIResource(Color color, int thickness)  {
            super(color, thickness);
        }
    }


    /**
     * A bevel border UI resource.
     */
    public static class BevelBorderUIResource extends BevelBorder implements UIResource {

        /**
         * Constructs a {@code BevelBorderUIResource}.
         * @param bevelType the type of bevel for the border
         */
        public BevelBorderUIResource(int bevelType) {
            super(bevelType);
        }

        /**
         * Constructs a {@code BevelBorderUIResource}.
         * @param bevelType the type of bevel for the border
         * @param highlight the color to use for the bevel highlight
         * @param shadow the color to use for the bevel shadow
         */
        public BevelBorderUIResource(int bevelType, Color highlight, Color shadow) {
            super(bevelType, highlight, shadow);
        }

        /**
         * Constructs a {@code BevelBorderUIResource}.
         * @param bevelType the type of bevel for the border
         * @param highlightOuter the color to use for the bevel outer highlight
         * @param highlightInner the color to use for the bevel inner highlight
         * @param shadowOuter the color to use for the bevel outer shadow
         * @param shadowInner the color to use for the bevel inner shadow
         */
        @ConstructorProperties({"bevelType", "highlightOuterColor", "highlightInnerColor", "shadowOuterColor", "shadowInnerColor"})
        public BevelBorderUIResource(int bevelType,
                                     Color highlightOuter, Color highlightInner,
                                     Color shadowOuter, Color shadowInner) {
            super(bevelType, highlightOuter, highlightInner, shadowOuter, shadowInner);
        }
    }

    /**
     * An etched border UI resource.
     */
    public static class EtchedBorderUIResource extends EtchedBorder implements UIResource {

        /**
         * Constructs an {@code EtchedBorderUIResource}.
         */
        public EtchedBorderUIResource()    {
            super();
        }

        /**
         * Constructs an {@code EtchedBorderUIResource}.
         * @param etchType the type of etch to be drawn by the border
         */
        public EtchedBorderUIResource(int etchType)    {
            super(etchType);
        }

        /**
         * Constructs an {@code EtchedBorderUIResource}.
         * @param highlight the color to use for the etched highlight
         * @param shadow the color to use for the etched shadow
         */
        public EtchedBorderUIResource(Color highlight, Color shadow)    {
            super(highlight, shadow);
        }

        /**
         * Constructs an {@code EtchedBorderUIResource}.
         * @param etchType the type of etch to be drawn by the border
         * @param highlight the color to use for the etched highlight
         * @param shadow the color to use for the etched shadow
         */
        @ConstructorProperties({"etchType", "highlightColor", "shadowColor"})
        public EtchedBorderUIResource(int etchType, Color highlight, Color shadow)    {
            super(etchType, highlight, shadow);
        }
    }

    /**
     * A matte border UI resource.
     */
    public static class MatteBorderUIResource extends MatteBorder implements UIResource {

        /**
         * Constructs a {@code MatteBorderUIResource}.
         * @param top the top inset of the border
         * @param left the left inset of the border
         * @param bottom the bottom inset of the border
         * @param right the right inset of the border
         * @param color the color rendered for the border
         */
        public MatteBorderUIResource(int top, int left, int bottom, int right,
                                     Color color)   {
            super(top, left, bottom, right, color);
        }

        /**
         * Constructs a {@code MatteBorderUIResource}.
         * @param top the top inset of the border
         * @param left the left inset of the border
         * @param bottom the bottom inset of the border
         * @param right the right inset of the border
         * @param tileIcon the icon to be used for tiling the border
         */
        public MatteBorderUIResource(int top, int left, int bottom, int right,
                                     Icon tileIcon)   {
            super(top, left, bottom, right, tileIcon);
        }

        /**
         * Constructs a {@code MatteBorderUIResource}.
         * @param tileIcon the icon to be used for tiling the border
         */
        public MatteBorderUIResource(Icon tileIcon)   {
            super(tileIcon);
        }
    }

    /**
     * A titled border UI resource.
     */
    public static class TitledBorderUIResource extends TitledBorder implements UIResource {

        /**
         * Constructs a {@code TitledBorderUIResource}.
         * @param title the title the border should display
         */
        public TitledBorderUIResource(String title)     {
            super(title);
        }

        /**
         * Constructs a {@code TitledBorderUIResource}.
         * @param border the border
         */
        public TitledBorderUIResource(Border border)       {
            super(border);
        }

        /**
         * Constructs a {@code TitledBorderUIResource}.
         * @param border the border
         * @param title the title the border should display
         */
        public TitledBorderUIResource(Border border, String title) {
            super(border, title);
        }

        /**
         * Constructs a {@code TitledBorderUIResource}.
         * @param border the border
         * @param title the title the border should display
         * @param titleJustification the justification fro the title
         * @param titlePosition the position for the title
         */
        public TitledBorderUIResource(Border border,
                        String title,
                        int titleJustification,
                        int titlePosition)      {
            super(border, title, titleJustification, titlePosition);
        }

        /**
         * Constructs a {@code TitledBorderUIResource}.
         * @param border the border
         * @param title the title the border should display
         * @param titleJustification the justification fro the title
         * @param titlePosition the position for the title
         * @param titleFont the font for rendering the title
         */
        public TitledBorderUIResource(Border border,
                        String title,
                        int titleJustification,
                        int titlePosition,
                        Font titleFont) {
            super(border, title, titleJustification, titlePosition, titleFont);
        }

        /**
         * Constructs a {@code TitledBorderUIResource}.
         * @param border the border
         * @param title the title the border should display
         * @param titleJustification the justification fro the title
         * @param titlePosition the position for the title
         * @param titleFont the font for rendering the title
         * @param titleColor the color of the title
         */
        @ConstructorProperties({"border", "title", "titleJustification", "titlePosition", "titleFont", "titleColor"})
        public TitledBorderUIResource(Border border,
                        String title,
                        int titleJustification,
                        int titlePosition,
                        Font titleFont,
                        Color titleColor)       {
            super(border, title, titleJustification, titlePosition, titleFont, titleColor);
        }
    }

}