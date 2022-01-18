package com.vargax.springipc.Ip;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@CommonsLog
class IpService {
    private final IpRepository ipRepository;
    private final Duration cacheExp;
    @Value("${IP_API_URL}")
    private String ipApiUrl;

    IpService(IpRepository ipRepository, @Value("${CACHE_EXP}") String cacheExp) {
        this.ipRepository = ipRepository;
        this.cacheExp = Duration.parse(cacheExp);
    }

    JsonNode getIpPayload(String ipAddr) {
        Ip ip = ipRepository.findById(ipAddr).orElse(null);

        if (ip == null) {
            log.info(String.format("%s wasn't found locally...", ipAddr));
            ip = ipRepository.save(new Ip(ipAddr, queryIpApi(ipAddr)));
        }

        Duration age = Duration.between(ip.getModified(), LocalDateTime.now());
        if (age.compareTo(cacheExp) > 0) {
            log.info(String.format("%s record is expired! It was cached %s ago...", ip.getIp(), age));
            ip.setPayload(queryIpApi(ipAddr));
            ipRepository.save(ip);
        }

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
