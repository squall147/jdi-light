package io.github.com.sections;

import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.bootstrap.elements.common.Popover;
import com.epam.jdi.light.ui.bootstrap.elements.common.Text;
import com.epam.jdi.light.ui.bootstrap.elements.complex.MediaObject;

public class PopoverRight extends Section {

    @UI("#popover-right")
    public Popover popover;
    public MediaObject container;
    public Text body;

}