package com.example.scan3d.mappers;

import com.example.scan3d.dtos.request.BaseDTO;
import com.example.scan3d.entities.BaseEntity;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface GenericMapper<E extends BaseEntity, D extends BaseDTO> {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "deleted", source = "deleted")
    D toDTO(E entity);

    E toEntity(D dto);
}
