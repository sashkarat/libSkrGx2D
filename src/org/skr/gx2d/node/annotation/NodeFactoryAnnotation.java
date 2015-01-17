package org.skr.gx2d.node.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by rat on 17.01.15.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NodeFactoryAnnotation {
    Class               factory();
    String              createMethodName();
    NodeDataAccessorAnnotation parameter();
}