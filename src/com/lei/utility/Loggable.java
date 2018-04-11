package com.lei.utility;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Custom annotation used for logging.
 * Annotated any method as @Loggable on any method to log its execution trail.
 * @author Vinay.Kumar1
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //can use in method only.
public @interface Loggable {

}
