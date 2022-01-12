package com.vargax.springipc.Ip;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@CommonsLog
class IpService {
    private final IpRepository ipRepository;
    @Value("${ipapi.url}")
    private String ipApiUrl;

    IpService(IpRepository ipRepository) {
        this.ipRepository = ipRepository;
    }

    JsonNode getIpPayload(String ipAddr) {
        Ip ip = ipRepository.findById(ipAddr).orElseGet(() -> {
            log.info(String.format("%s wasn't found locally...", ipAddr));
            return ipRepository.save(new Ip(ipAddr, queryIpApi(ipAddr)));
        });
        return ip.getPayload();
    }

    List<Ip> getCachedIps() {
        List<Ip> ii = ipRepository.findAll();
        log.info(String.format("%d IPs found in the cache", ii.size()));
        return ii;
    }

    private JsonNode queryIpApi(String ipAddr) {
        log.info(String.format("Querying IP-API for %s", ipAddr));
        String query = String.format("%s%s", ipApiUrl, ipAddr);
        return new RestTemplate().getForObject(query, JsonNode.class);
    }

}
