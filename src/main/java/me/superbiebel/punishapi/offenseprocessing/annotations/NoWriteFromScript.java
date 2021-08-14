package me.superbiebel.punishapi.offenseprocessing.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Nothing will happen if a script modifies a field with this annotation, the value inside that field will probably be overwritten. A field with this annotation must be regarded as a read only field from the POV of the script even though the final keyword inst there.
 */

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface NoWriteFromScript {
}
