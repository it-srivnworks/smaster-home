package com.srivn.works.smaster.smasterhome.utils;

import java.io.IOException;

@FunctionalInterface
public interface RetryImplementation {
       Boolean run() throws IOException;
}
