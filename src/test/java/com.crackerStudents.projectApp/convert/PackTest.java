package com.crackerStudents.projectApp.convert;

import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.domain.Pack;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @Author Krylov Sergey
 */
public class PackTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenDtoConvertToEntity(){
        PackDTO packDTO = new PackDTO();
        packDTO.setName("1");
        packDTO.setAuthorId(new UUID(1,1));
        packDTO.setPublic(true);
        packDTO.setLikes(10);
        packDTO.setCreated(new Date());

        Pack pack = modelMapper.map(packDTO, Pack.class);
        assertEquals(pack.getName(),packDTO.getName());
        assertEquals(pack.getAuthorId(),packDTO.getAuthorId());
        assertEquals(pack.getCreated(),packDTO.getCreated());
    }

    @Test
    public void whenEntityConvertToDto(){
        Pack pack = new Pack();
        pack.setName("1");
        pack.setAuthorId(new UUID(1,1));
        pack.setPublic(true);
        pack.setLikes(10);
        pack.setCreated(new Date());

        PackDTO packDTO = modelMapper.map(pack, PackDTO.class);
        assertEquals(pack.getName(),packDTO.getName());
        assertEquals(pack.getAuthorId(),packDTO.getAuthorId());
        assertEquals(pack.getCreated(),packDTO.getCreated());
    }
}
