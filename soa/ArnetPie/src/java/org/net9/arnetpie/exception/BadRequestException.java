/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.exception;

/**
 * HTTP Status 400
 * @author LeeThree
 */
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super("HTTP 400: Bad Request: " + message);
    }
}
