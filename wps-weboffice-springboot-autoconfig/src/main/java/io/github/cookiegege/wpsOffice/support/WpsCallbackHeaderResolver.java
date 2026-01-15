package io.github.cookiegege.wpsOffice.support;

import io.github.cookiegege.wpsOffice.context.WpsCallbackHeader;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author JoSuper
 * @date 2026/1/14 10:46
 */
public class WpsCallbackHeaderResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == WpsCallbackHeader.class;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {

        WpsCallbackHeader header = new WpsCallbackHeader();
        header.setFileId(webRequest.getHeader("X-Weboffice-File-Id"));
        header.setSaveType(webRequest.getHeader("X-Weboffice-Save-Type"));
        header.setToken(webRequest.getHeader("X-Wps-Weboffice-Token"));

        return header;
    }
}
