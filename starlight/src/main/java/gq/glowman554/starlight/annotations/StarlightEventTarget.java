package gq.glowman554.starlight.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import gq.glowman554.starlight.data.EventPriority;

@Retention(RUNTIME)
@Target(METHOD)
public @interface StarlightEventTarget
{
	byte value() default EventPriority.THIRD;
}
