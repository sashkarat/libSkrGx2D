package org.skr.gx2d.node.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by rat on 09.01.15.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NodeAddMethodAnnotation {
    String name();
}
