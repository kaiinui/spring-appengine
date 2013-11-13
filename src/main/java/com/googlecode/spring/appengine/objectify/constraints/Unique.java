/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.spring.appengine.objectify.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * A class-level constraint that checks if a field, or a combination of fields,
 * is unique within the annotated entity. Although this is a class-level constraint,
 * the error(s) will be associated to the field(s).
 * 
 * <p>
 * Example:
 * 
 * <pre class="code">
 * &#064;Entity
 * &#064;Unique(&quot;license&quot;)
 * public class Car {
 * 
 *     &#064;Id private Long id;
 *     private String license;
 *     ..
 * }
 * 
 * &#064;Entity
 * &#064;Unique({ &quot;brand&quot;, &quot;model&quot; })
 * public class Model {
 * 
 *     &#064;Id private Long id;
 *     private String brand;
 *     private String model;
 *     ..
 * }
 * </pre>
 * 
 * <p>Note that this constraint does not prohibit to store entities that violate the unique constraint.
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
@Constraint(validatedBy = { UniqueValidator.class })
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Unique {

    /**
     * @return The field, or a combination of fields, that should be unique within 
     *         the annotated entity. A {@link ConstraintDeclarationException} will 
     *         be thrown upon validation if a field could not be found or the 
     *         entity is not registered in the objectify service.
     */
    String[] value();

    String message() default "{com.googlecode.spring.appengine.objectify.constraints.Unique.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@code @Unique} annotations on the same element.
     */
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.TYPE })
    public @interface List {
        Unique[] value();
    }
}
