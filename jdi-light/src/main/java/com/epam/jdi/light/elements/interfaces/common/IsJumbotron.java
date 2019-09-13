package com.epam.jdi.light.elements.interfaces.common;

import com.epam.jdi.light.elements.interfaces.base.HasClick;
import com.epam.jdi.light.elements.interfaces.base.HasValue;

public interface IsJumbotron extends HasClick, IsText, HasValue {
    default String getValue() { return getText(); }
}