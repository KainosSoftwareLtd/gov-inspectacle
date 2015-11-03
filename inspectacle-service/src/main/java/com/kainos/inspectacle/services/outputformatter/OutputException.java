package com.kainos.inspectacle.services.outputformatter;

import java.io.IOException;

public class OutputException extends IOException {
    public OutputException(String message, Throwable cause) {
        super(message, cause);
    }
}
