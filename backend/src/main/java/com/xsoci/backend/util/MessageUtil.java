package com.xsoci.backend.util;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageUtil {
    private final MessageSource messageSource;

    public String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(key, null, locale);
    }
    
    public String getMessage(String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(key, args, locale);
    }

    public String success(String field) {
        return getMessage(
            "server.200",
            new Object[]{field}
        );
    }

    public String invalid(String field) {
        return getMessage(
            "validation.invalid",
            new Object[]{field}
        );
    }
}