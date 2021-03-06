/**
 *     Copyright (C) 2015  the original author or authors.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License,
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package io.dohko.jdbi.binders;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.dohko.jdbi.binders.AnnotationBinderFactory.FieldBinder;

import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
@BindingAnnotation(AnnotationBinderFactory.class)
public @interface BindBean
{
    /**
     * Returns a type to bind the parameters of a SQL expression.
     * 
     * @returns the type to bind the parameters of a SQL expression.
     */
    Class<? extends Binder<BindBean, ?>> binder() default FieldBinder.class;

    /**
     * Each parameter may follow the pattern: <param name>:<property name>, where <em>param name</em> is the parameter name and <em>property name</em>
     * is the object's property name.
     * 
     * For example:
     * 
     * <p>
     * select a, b, c from table where a = :bc
     * </p>
     * 
     * <p>
     * Foo getFoo(@BindBean (params={"bc:id"} User user)
     * 
     * In this case, the object must have an attribute called id.
     * 
     * @returns the parameters found in a bean.
     */
    String[] params() default {};

    /**
     * Returns the suffix to a binder.
     * 
     * @returns the suffix of this binder.
     */
    String suffix() default "";
}
