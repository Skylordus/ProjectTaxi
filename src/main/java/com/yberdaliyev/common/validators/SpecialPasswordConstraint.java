package com.yberdaliyev.common.validators;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by Yerlan on 19.03.2017.
 */
@Constraint(validatedBy = SpecialPasswordValidator.class)
public @interface SpecialPasswordConstraint {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
