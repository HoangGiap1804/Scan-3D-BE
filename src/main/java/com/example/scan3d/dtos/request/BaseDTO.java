package com.example.scan3d.dtos.request;

import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Data
public class BaseDTO {
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean deleted;
}
