package com.vargax.springipc.Ip;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@TypeDef(name = "json", typeClass = JsonType.class)
class Ip {
    @Id
    private String ip;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private JsonNode payload;

    @UpdateTimestamp
    private LocalDateTime modified;

    public Ip(String ip, JsonNode payload) {
        this.ip = ip;
        this.payload = payload;
    }
}
