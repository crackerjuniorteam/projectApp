package com.crackerStudents.projectApp.mappers;

import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.dto.PackDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PackMapper {
    PackDTO packToPackDto (Pack pack);

    List<PackDTO> packsToPacksDto(List<Pack> packs);

    Pack packDtoToPack(PackDTO pack);

    List<Pack> packsDtoToPacks(List<PackDTO> packs);
}
