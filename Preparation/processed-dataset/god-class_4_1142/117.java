void reportSchemaWarning(String key, Object[] args, Element ele, Exception exception) {
    if (element2Locator(ele, xl)) {
        fErrorReporter.reportError(xl, XSMessageFormatter.SCHEMA_DOMAIN, key, args, XMLErrorReporter.SEVERITY_WARNING, exception);
    } else {
        fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, key, args, XMLErrorReporter.SEVERITY_WARNING, exception);
    }
}
