package com.midlanddigital.test.app.api;

import com.midlanddigital.test.app.domain.exceptions.ServiceException;
import com.midlanddigital.test.app.domain.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
@Slf4j
public class StaffUUIdHttpHeaderInterceptor implements AsyncHandlerInterceptor {

    public static final String UUID_PATTERN = "^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$";
    private final StaffService staffService;

    public StaffUUIdHttpHeaderInterceptor(StaffService staffService) {
        this.staffService = staffService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)) { return true; }

        final ValidateStaffUUID validateStaffUUID = ((HandlerMethod) handler).getMethod().getAnnotation((ValidateStaffUUID.class));

        if (validateStaffUUID == null) {
            return true;
        }

        /* validate if the header exists */
        var staffUUID = request.getHeader("staffUUID");
        if (staffUUID == null) {
            throw new ServiceException("Staff UUID header is required", ServiceException.INVALID_STAFF_UUID_HEADER);
        }

        /* validated if the header value is a valid UUID string */
        if (!staffUUID.matches(UUID_PATTERN)) {
            throw new ServiceException("Invalid staff UUID", ServiceException.INVALID_STAFF_UUID_HEADER);
        }

        try {
            /* check if a staff with the give UUID exists , the method throws exception if staff with that UUID doesn't exist */
            staffService.validateStaff(UUID.fromString(staffUUID));
        } catch (ServiceException e) {
            throw new ServiceException("Please make sure to set Http header staffUUID parameter with a valid staff uuid " + e.getMessage()
                    , ServiceException.INVALID_STAFF_UUID_HEADER);

        }


        return true;
    }

}