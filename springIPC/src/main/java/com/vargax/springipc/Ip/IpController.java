package com.vargax.springipc.Ip;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CommonsLog
class IpController {
    private final IpService ipService;

    IpController(IpService ipService) {
        this.ipService = ipService;
    }

    @GetMapping("/")
    List<Ip> getAllIps() {
        return ipService.getCachedIps();
    }

    @GetMapping("/{ipAddr}")
    JsonNode getIp(@PathVariable String ipAddr) {
        log.info(String.format("%s requested...", ipAddr));
        return ipService.getIpPayload(ipAddr);
    }


}
