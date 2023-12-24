package com.misol.kakaobank.exception

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass


class EnumValidator : ConstraintValidator<ValidEnum, Any> {
    private lateinit var enumValues: Array<out Any>

    override fun initialize(annotation: ValidEnum) {
        enumValues = annotation.enumClass.java.enumConstants
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
        if (value == null) return true

        return enumValues.any { it == value }
    }
}

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [EnumValidator::class])
annotation class ValidEnum(
        val enumClass: KClass<out Enum<*>>,
        val message: String = "유효하지 않은 enum 값입니다.",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)