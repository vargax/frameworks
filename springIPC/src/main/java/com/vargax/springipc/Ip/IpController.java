package com.vargax.springipc.Ip;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IpAddressValidator.class)
@interface IpAddress {
    String message() default "invalid IPv4 or IPv6 address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

@RestController
@CommonsLog
@Validated
class IpController {
    private final IpService ipService;
    private final ObjectMapper mapper = new ObjectMapper();

    IpController(IpService ipService) {
        this.ipService = ipService;
    }

    @GetMapping("/")
    List<Ip> getAllIps() {
        return ipService.getCachedIps();
    }

    @GetMapping("/{ipAddr}")
    JsonNode getIp(@PathVariable @IpAddress String ipAddr) {
        log.info(String.format("%s requested...", ipAddr));
        return ipService.getIpPayload(ipAddr);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<JsonNode> handleConstraintViolationException(ConstraintViolationException e) {
        log.info(String.format("Bad request: %s", e.getMessage()));
        Map<String, String> map = new HashMap<>();
        for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
            map.put(cv.getInvalidValue().toString(), cv.getMessage());
        }

        return new ResponseEntity<>(mapper.convertValue(map, JsonNode.class), HttpStatus.BAD_REQUEST);
    }

}

class IpAddressValidator implements ConstraintValidator<IpAddress, String> {
    InetAddressValidator validator = InetAddressValidator.getInstance();

    @Override
    public boolean isValid(String ipAddr, ConstraintValidatorContext context) {
        return validator.isValid(ipAddr);
    }
}