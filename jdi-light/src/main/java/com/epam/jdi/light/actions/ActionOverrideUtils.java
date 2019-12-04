package com.epam.jdi.light.actions;

import com.epam.jdi.light.elements.base.DriverBase;
import com.epam.jdi.light.elements.base.JDIBase;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.func.JFunc1;
import com.epam.jdi.tools.map.MapArray;
import com.epam.jdi.tools.pairs.Pair;
import org.aspectj.lang.ProceedingJoinPoint;

public class ActionOverrideUtils {
    private static MapArray<JFunc1<ProceedingJoinPoint, Boolean>, JFunc1<JDIBase, Object>> OVERRIDE_ACTIONS_LIST =
            new MapArray<>();

    private ActionOverrideUtils() {
    }

    public static void overrideFunction(JFunc1<ProceedingJoinPoint, Boolean> condition, JFunc1<JDIBase, Object> func) {
        OVERRIDE_ACTIONS_LIST.add(condition, func);
    }

    public static void overrideFunction(String actionName, JFunc1<JDIBase, Object> func) {
        OVERRIDE_ACTIONS_LIST.add(jp -> jp.getSignature().getName().equals(actionName), func);
    }

    public static void overrideFunction(String typeName, String actionName, JFunc1<JDIBase, Object> func) {
        OVERRIDE_ACTIONS_LIST.add(jp -> getJpTypeName(jp).equals(typeName)
                && jp.getSignature().getName().equals(actionName), func);
    }

    private static String getJpTypeName(ProceedingJoinPoint jp) {
        return ((DriverBase) jp.getThis()).typeName;
    }

    public static void overrideAction(JFunc1<ProceedingJoinPoint, Boolean> condition, JAction1<JDIBase> action) {
        overrideFunction(condition, jdi -> {
            action.execute(jdi);
            return null;
        });
    }

    public static void overrideAction(String actionName, JAction1<JDIBase> action) {
        overrideFunction(actionName, jdi -> {
            action.execute(jdi);
            return null;
        });
    }

    public static void overrideAction(String className, String actionName, JAction1<JDIBase> action) {
        overrideFunction(className, actionName, jdi -> {
            action.execute(jdi);
            return null;
        });
    }

    public static JFunc1<JDIBase, Object> getOverrideAction(ProceedingJoinPoint jp) {
        if (OVERRIDE_ACTIONS_LIST.isEmpty()) return null;
        for (Pair<JFunc1<ProceedingJoinPoint, Boolean>, JFunc1<JDIBase, Object>> override : OVERRIDE_ACTIONS_LIST)
            if (override.key.execute(jp))
                return override.value;
        return null;
    }
}
