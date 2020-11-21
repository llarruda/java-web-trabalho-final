/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Junior
 */
public class EditarClienteException extends Exception{

    public EditarClienteException() {
    }

    public EditarClienteException(String string) {
        super(string);
    }

    public EditarClienteException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
