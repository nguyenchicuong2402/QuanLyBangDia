package org.buffalocoder.quanlybangdia.views.custom;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.View;
import java.awt.*;
import java.util.Arrays;

public class CustomTabbedPanelUI extends BasicTabbedPaneUI {
    // chiều dài tab
    private int width = 200;

    // chiểu rộng tab
    private int height = 50;

    // màu khi tab được chọn
    private Color colorSelected = Color.BLUE;

    // màu khi tab không được chọn
    private Color colorDeselected = Color.CYAN;

    // màu text khi tab được chọn
    private Color colorTextSelected = Color.WHITE;

    // màu text khi tab không được chọn
    private Color colorTextDeselected = Color.WHITE;
    private int[] margin = {0, 0};

    private int inclTab = 0;
    private int anchoCarpetas = 18;
    private Polygon shape;

    public static ComponentUI createUI(JComponent c) {
        return new CustomTabbedPanelUI();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width > 0 ? width : this.width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height > 0 ? height : this.height;
    }

    public Color getColorSelected() {
        return colorSelected;
    }

    public void setColorSelected(Color colorSelected) {
        this.colorSelected = colorSelected;
    }

    public Color getColorDeselected() {
        return colorDeselected;
    }

    public void setColorDeselected(Color colorDeselected) {
        this.colorDeselected = colorDeselected;
    }

    public Color getColorTextSelected() {
        return colorTextSelected;
    }

    public void setColorTextSelected(Color colorTextSelected) {
        this.colorTextSelected = colorTextSelected;
    }

    public Color getColorTextDeselected() {
        return colorTextDeselected;
    }

    public void setColorTextDeselected(Color colorTextDeselected) {
        this.colorTextDeselected = colorTextDeselected;
    }

    public void setMargin(int margin_x, int margin_y) {
        this.margin[0] = margin_x;
        this.margin[1] = margin_y;
    }

    @Override
    protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
        if (runCount > 1) {
            int lines[] = new int[runCount];
            for (int i = 0; i < runCount; i++) {
                lines[i] = rects[tabRuns[i]].y + (tabPlacement == TOP ? maxTabHeight : 0);
            }
            Arrays.sort(lines);

            if (tabPlacement == TOP) {
                int fila = runCount;
                for (int i = 0; i < lines.length - 1; i++, fila--) {
                    Polygon carp = new Polygon();
                    carp.addPoint(0, lines[i]);
                    carp.addPoint(tabPane.getWidth() - 2 * fila - 2, lines[i]);
                    carp.addPoint(tabPane.getWidth() - 2 * fila, lines[i] + 3);
                    if (i < lines.length - 2) {
                        carp.addPoint(tabPane.getWidth() - 2 * fila, lines[i + 1]);
                        carp.addPoint(0, lines[i + 1]);
                    } else {
                        carp.addPoint(tabPane.getWidth() - 2 * fila, lines[i] + rects[selectedIndex].height);
                        carp.addPoint(0, lines[i] + rects[selectedIndex].height);
                    }
                    carp.addPoint(0, lines[i]);
                    g.fillPolygon(carp);
                    g.setColor(darkShadow.darker());
                    g.drawPolygon(carp);
                }
            } else {
                int fila = 0;
                for (int i = 0; i < lines.length - 1; i++, fila++) {
                    Polygon carp = new Polygon();
                    carp.addPoint(0, lines[i]);
                    carp.addPoint(tabPane.getWidth() - 2 * fila - 1, lines[i]);
                    carp.addPoint(tabPane.getWidth() - 2 * fila - 1, lines[i + 1] - 3);
                    carp.addPoint(tabPane.getWidth() - 2 * fila - 3, lines[i + 1]);
                    carp.addPoint(0, lines[i + 1]);
                    carp.addPoint(0, lines[i]);
                    g.fillPolygon(carp);
                    g.setColor(darkShadow.darker());
                    g.drawPolygon(carp);
                }
            }
        }
        super.paintTabArea(g, tabPlacement, selectedIndex);
    }

    /**
     * Tuỳ biến background tab
     *
     * @param g
     * @param tabPlacement
     * @param tabIndex
     * @param x
     * @param y
     * @param w
     * @param h
     * @param isSelected
     */
    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {

        Graphics2D g2D = (Graphics2D) g;
        int xp[] = null; // Para la forma
        int yp[] = null;

        // vẽ tab thông qua các point TOP, BOTTOM, LEFT, RIGHT
        switch (tabPlacement) {
            case LEFT:
                xp = new int[]{x - 2, x - 2, x + w, x + w, x};
                yp = new int[]{y, y + h, y + h, y, y};
                break;
            case RIGHT:
                xp = new int[]{x, x, x + w, x + w, x};
                yp = new int[]{y, y + h, y + h, y, y};
                break;
            case BOTTOM:
                xp = new int[]{x, x, x, x + w, x + w, x + w, x + w, x};
                yp = new int[]{y, y + h, y + h, y + h, y + h, y + h, y, y};
                break;
            case TOP:
            default:
                xp = new int[]{x, x, x, x + w, x + w, x + w, x + w, x};
                yp = new int[]{y + h, y, y, y, y, y, y + h, y + h};
                break;
        }
        shape = new Polygon(xp, yp, xp.length);

        // màu khi tab được chọn
        if (isSelected) {
            g2D.setColor(colorSelected);
        } else {
            // màu khi tab enable
            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
                g2D.setColor(colorDeselected);
            } else {
                g2D.setColor(Color.DARK_GRAY);
            }
        }
        g2D.fill(shape);

        if (runCount > 1) {
            g2D.fill(shape);
        }
        g2D.fill(shape);
    }

    /**
     * Cấu hình text và textFont
     *
     * @param g
     * @param tabPlacement
     * @param font
     * @param metrics
     * @param tabIndex
     * @param title
     * @param textRect
     * @param isSelected
     */
    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        // nếu muốn cho label nằm ở giữa thì dùng dòng này
        //super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);

        g.setFont(font);
        View v = getTextViewForTab(tabIndex);

        if (v != null) {
            v.paint(g, textRect);
        } else {
            // plain text
            int mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);
            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {

                if (isSelected)
                    g.setColor(colorTextSelected);      // màu chữ khi tab được chọn
                else
                    g.setColor(colorTextDeselected);            // màu chữ khi tab không được chọn

                BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, margin[0] + metrics.getDescent(), margin[1] + textRect.y + metrics.getAscent());
            } else { // tab disabled
                g.setColor(Color.BLACK);
                BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x, textRect.y + metrics.getAscent());
                g.setColor(tabPane.getBackgroundAt(tabIndex).darker());
                BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x - 1, textRect.y + metrics.getAscent() - 1);
            }
        }
    }


    /**
     * Tính độ dài của tab
     *
     * @param tabPlacement
     * @param tabIndex
     * @param metrics
     * @return
     */
    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        return width + super.calculateTabWidth(tabPlacement, tabIndex, metrics);
    }


    /**
     * Tính độ rộng của tab
     *
     * @param tabPlacement
     * @param tabIndex
     * @param fontHeight
     * @return
     */
    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        if (tabPlacement == LEFT || tabPlacement == RIGHT) {
            return super.calculateTabHeight(tabPlacement, tabIndex, height);
        } else {
            return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight);
        }
    }


    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
    }


    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
        if (tabPane.hasFocus() && isSelected) {
            g.setColor(UIManager.getColor("ScrollBar.thumbShadow"));
            g.drawPolygon(shape);
        }
    }
}
