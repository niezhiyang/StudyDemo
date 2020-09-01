package com.nzy.gameover.sticker;

import java.util.List;

public class Element {
    public static final int IMAGE = 1;
    public static final int TEXT = 2;
    private int type;
    private String id;
    private String url;
    private String text;
    private int duration;
    private float width;
    private float height;
    private String background;
    private int fontSize;
    private String textColor;
    private int maxLen;
    private List<ElementNewAnimation> new_anim;
    private List<ElementAnimation> anim;
    private String folder;
    public boolean useCircle = false;
    public int vWidth = 720;
    public int vHeight = 1280;

    public Element() {
    }

    public String getBackgroundPath() {
        return this.folder + "/" + this.background;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getBackground() {
        return this.background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getTextColor() {
        return this.textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public int getMaxLen() {
        return this.maxLen;
    }

    public void setMaxLen(int maxLen) {
        this.maxLen = maxLen;
    }

    public String getFolder() {
        return this.folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public List<ElementAnimation> getAnim() {
        return this.anim;
    }

    public void setAnim(List<ElementAnimation> anim) {
        this.anim = anim;
    }

    public List<ElementNewAnimation> getNew_anim() {
        return this.new_anim;
    }

    public Element setNew_anim(List<ElementNewAnimation> new_anim) {
        this.new_anim = new_anim;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Element element = (Element)o;
            if (Float.compare(element.width, this.width) != 0) {
                return false;
            } else if (Float.compare(element.height, this.height) != 0) {
                return false;
            } else if (this.fontSize != element.fontSize) {
                return false;
            } else if (this.maxLen != element.maxLen) {
                return false;
            } else if (this.vWidth != element.vWidth) {
                return false;
            } else if (this.vHeight != element.vHeight) {
                return false;
            } else {
                label92: {
                    if (this.text != null) {
                        if (this.text.equals(element.text)) {
                            break label92;
                        }
                    } else if (element.text == null) {
                        break label92;
                    }

                    return false;
                }

                if (this.background != null) {
                    if (!this.background.equals(element.background)) {
                        return false;
                    }
                } else if (element.background != null) {
                    return false;
                }

                if (this.textColor != null) {
                    if (!this.textColor.equals(element.textColor)) {
                        return false;
                    }
                } else if (element.textColor != null) {
                    return false;
                }

                label71: {
                    if (this.new_anim != null) {
                        if (this.new_anim.equals(element.new_anim)) {
                            break label71;
                        }
                    } else if (element.new_anim == null) {
                        break label71;
                    }

                    return false;
                }

                if (this.anim != null) {
                    if (this.anim.equals(element.anim)) {
                        return this.folder != null ? this.folder.equals(element.folder) : element.folder == null;
                    }
                } else if (element.anim == null) {
                    return this.folder != null ? this.folder.equals(element.folder) : element.folder == null;
                }

                return false;
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.text != null ? this.text.hashCode() : 0;
        result = 31 * result + (this.width != 0.0F ? Float.floatToIntBits(this.width) : 0);
        result = 31 * result + (this.height != 0.0F ? Float.floatToIntBits(this.height) : 0);
        result = 31 * result + (this.background != null ? this.background.hashCode() : 0);
        result = 31 * result + this.fontSize;
        result = 31 * result + (this.textColor != null ? this.textColor.hashCode() : 0);
        result = 31 * result + this.maxLen;
        result = 31 * result + (this.new_anim != null ? this.new_anim.hashCode() : 0);
        result = 31 * result + (this.anim != null ? this.anim.hashCode() : 0);
        result = 31 * result + (this.folder != null ? this.folder.hashCode() : 0);
        result = 31 * result + this.vWidth;
        result = 31 * result + this.vHeight;
        return result;
    }
}
