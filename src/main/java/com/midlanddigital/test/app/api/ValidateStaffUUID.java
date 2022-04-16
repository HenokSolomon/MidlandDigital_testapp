package com.midlanddigital.test.app.api;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  annotated controller methods requires http request header with a valid staffUUID parameter
 *  staffUUID: validUUID value
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidateStaffUUID {
}