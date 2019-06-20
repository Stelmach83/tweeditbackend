package dev.stelmach.tweeditapi.validation;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class MatchValidator implements ConstraintValidator<EnableMatchConstraint, Object> {

    @Override
    public void initialize(final EnableMatchConstraint constraint) {
    }

    @Override
    public boolean isValid(final Object o, final ConstraintValidatorContext context) {
        boolean result = true;
        try {
            String mainField, secondField, message;
            Object firstObj, secondObj;

            final Class<?> clazz = o.getClass();
            final Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(Match.class)) {
                    mainField = field.getName();
                    secondField = field.getAnnotation(Match.class).field();
                    message = field.getAnnotation(Match.class).message();

                    if (message == null || "".equals(message))
                        message = "Fields " + mainField + " and " + secondField + " must match!";

                    firstObj = BeanUtils.getProperty(o, mainField);
                    secondObj = BeanUtils.getProperty(o, secondField);

                    result = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
                    if (!result) {
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate(message).addPropertyNode(mainField).addConstraintViolation();
                        break;
                    }
                }
            }
        } catch (final Exception e) {
        }
        return result;
    }
}