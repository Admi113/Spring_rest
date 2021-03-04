package com.dmitriynikanorov.spring.rest.exception_handling;

import javax.print.DocFlavor;

public class NoSuchEmployeeException extends RuntimeException {

    public NoSuchEmployeeException(String message) {
        super(message);
    }
}
