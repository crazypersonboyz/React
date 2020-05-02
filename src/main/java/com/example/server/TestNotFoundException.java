package com.example.server;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,
        customFaultCode = "{" + TestNotFoundException.NAMESPACE_URI + "}custom_fault",
        faultStringOrReason = "@faultString")
public class TestNotFoundException extends Exception {

    public static final String NAMESPACE_URI = "http://example.com/exception";

    public TestNotFoundException(String message) {
        super(message);
    }
}
