package com.epam.jdi.bdd;

import static com.epam.jdi.light.common.Exceptions.exception;
import static com.epam.jdi.light.elements.composite.WebPage.ELEMENTS;
import static com.epam.jdi.light.elements.composite.WebPage.getCurrentPage;
import static com.epam.jdi.tools.LinqUtils.first;
import static com.epam.jdi.tools.ReflectionUtils.isClass;

import java.util.List;

import com.epam.jdi.light.elements.base.BaseUIElement;
import com.epam.jdi.light.elements.base.DriverBase;
import com.epam.jdi.light.elements.base.JDIBase;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.tools.LinqUtils;
import com.epam.jdi.tools.pairs.Pair;

/**
 * Created by Dmitry_Lebedev1 on 1/13/2016.
 */
public final class Utils {

    private Utils() {
    }

    public static <T> T getUI(String name, Class<T> type) {
        try {
            return (T) getUI(name);
        } catch (Exception ex) {
            throw exception("Can't convert element to %s", type.getSimpleName());
        }
    }

    public static BaseUIElement getUI(String name) {
        if (ELEMENTS.has(name)) {
            List<Object> elements = ELEMENTS.get(name);
            if (elements.size() == 1)
                return (BaseUIElement) elements.get(0);
            BaseUIElement element = (BaseUIElement) LinqUtils.first(elements,
                    el -> {
                        WebPage page = ((BaseUIElement) el).getPage();
                        return page != null && page.getName().equals(getCurrentPage());
                    });
            if (element != null)
                return element;
        }
        throw exception("Can't find %s element", name);
    }

    public static BaseUIElement getUI(String name, String section) {
        if (ELEMENTS.has(name)) {
            List<Object> els = ELEMENTS.get(name);
            Object result = first(els, el -> isClass(el.getClass(), JDIBase.class) && ((JDIBase) el).hasParent(section));
            if (result == null)
                throw exception("Can't find %s element at %s", name, section);
            return (BaseUIElement) result;
        }
        throw exception("Can't find %s element", name);
    }
    
    public static Form getForm(String name) {
    	if (ELEMENTS.has(name)) {
        	List<Object> elements = ELEMENTS.get(name);
            return (Form) elements.get(0);
        }
        throw exception("Can't find %s element", name);
    }
    
    public static int[] getIntArrayFromIntegerList(List<Integer> inputList) {
        return inputList.stream().mapToInt(i -> i).toArray();
    }
}
