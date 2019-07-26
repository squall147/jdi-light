package com.epam.jdi.light.elements.base;

import com.epam.jdi.light.asserts.generic.HasAssert;
import com.epam.jdi.light.asserts.generic.UIAssert;
import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.interfaces.IBaseElement;

public abstract class UIBaseElement<A extends UIAssert>
        implements IBaseElement, HasAssert<A> {
    protected UIElement uiElement;
    public JDIBase base() { return core().base(); }
    public UIElement core() {
        if (uiElement == null)
            uiElement = new UIElement();
        return uiElement;
    }

    protected void setElement(UIElement uiElement) { this.uiElement = uiElement; }

    public void hover() { core().hover(); }
    public boolean isEnabled() {
        return core().isEnabled();
    }
    public boolean isDisabled() {
        return !isEnabled();
    }
    public boolean isDisplayed() {
        return core().isDisplayed();
    }
    public boolean isHidden() {
        return !isDisplayed();
    }
    public void highlight(String color) {
        core().highlight(color);
    }
    public void highlight() {
        core().highlight();
    }
    public void show() {
        core().show();
    }

    public A is() {
        return (A) new UIAssert().set(this);
    }
}