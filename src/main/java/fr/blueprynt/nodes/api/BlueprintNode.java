package fr.blueprynt.nodes.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BlueprintNode {
    String id();
    String label();
    String category();
    NodeType nodeType();
    String color() default "#64748b";
    String icon() default "";
    /** Short description shown in the node catalogue (optional). */
    String description() default "";
    String[] requiredHooks() default {};
}
