/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.exception;

/**
 * HTTP Status 404
 * @author LeeThree
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super("HTTP 404: Not Found: " + message);
    }

    public NotFoundException(Throwable cause) {
        super("HTTP 400: Not Found: " + cause.getMessage(), cause);
    }
}
