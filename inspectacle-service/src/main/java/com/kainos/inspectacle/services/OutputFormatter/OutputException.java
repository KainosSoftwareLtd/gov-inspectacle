package com.kainos.inspectacle.services.OutputFormatter;

import java.io.IOException;

public class OutputException extends IOException {
    public OutputException(String message, Throwable cause) {
        super(message, cause);
    }
}
