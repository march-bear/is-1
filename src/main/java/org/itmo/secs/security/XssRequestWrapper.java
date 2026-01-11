package org.itmo.secs.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class XssRequestWrapper extends HttpServletRequestWrapper {

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    private static String sanitize(String input) {
        String safeInput = Jsoup.clean(input, Safelist.relaxed());
        return safeInput;
    }

    @Override
    public String getHeader(String name) {
        return sanitize(super.getHeader(name));
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        var headers = super.getHeaders(name);
        var safeHeaders = new ArrayList<String>();
        headers.asIterator().forEachRemaining(
            (el) -> safeHeaders.add(sanitize(el))
        );
        return Collections.enumeration(safeHeaders);
    }

    @Override
    public String getParameter(String name) {
        return sanitize(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        var values = super.getParameterValues(name);
        for (int i = 0; i < values.length; ++i) {
            values[i] = sanitize(values[i]);
        }
        return values;
    }
}
