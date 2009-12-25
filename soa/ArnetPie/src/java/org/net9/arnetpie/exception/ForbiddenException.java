/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.exception;

/**
 *
 * @author LeeThree
 */
public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super("HTTP 403: Forbidden: " + message);
    }
}
