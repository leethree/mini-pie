/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.net9.arnetpie.exception;

/**
 *
 * @author LeeThree
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("HTTP 401: Unauthorized");
    }

}
