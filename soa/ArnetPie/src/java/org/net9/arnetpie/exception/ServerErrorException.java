/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.exception;

/**
 *
 * @author LeeThree
 */
public class ServerErrorException extends RuntimeException {

    public ServerErrorException() {
        super("HTTP 500: Internal Server Errors");
    }
}
