package com.crackerStudents.projectApp.convert;

import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.domain.Pack;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

/**
 * @Author Krylov Sergey
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class PackConvert {

    private final ModelMapper modelMapper;

    @Autowired
    public PackConvert(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private PackDTO convertToDto(Pack pack) {
        PackDTO packDTO = modelMapper.map(pack, PackDTO.class);
        return packDTO;
    }


    private Pack convertToEntity(PackDTO packDTO) throws ParseException {
        Pack pack = modelMapper.map(packDTO, Pack.class);
        return pack;
    }
}
