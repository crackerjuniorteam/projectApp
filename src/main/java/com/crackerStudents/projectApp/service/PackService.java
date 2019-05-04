package com.crackerStudents.projectApp.service;

import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.convert.ObjectMapperUtils;
import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.PackRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class PackService {

    private final PackRepo packRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public PackService(PackRepo packRepo, ModelMapper modelMapper){
        this.packRepo = packRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void AddCardAndSave(Card card, String packName){
        Pack pack = packRepo.findByName(packName);
        pack.addCard(card);
        packRepo.save(pack);
    }

    @Transactional
    public PackDTO getPackDTOByName(String packName){
        return modelMapper.map(packRepo.findByName(packName),PackDTO.class);
    }

    @Transactional
    public PackDTO getPackByUserAndName(String packName, User user){
        Set<Pack> packs = user.getPacks();

        for (Pack pack : packs) {
           if (pack.getName().equals(packName)){
               return modelMapper.map(pack, PackDTO.class);
           }
        }
        return null;
    }

    @Transactional
    public List<PackDTO> getUserPacks(User user){
        //Преобразуем сет паков в лист дто-паков
        return ObjectMapperUtils.mapAll(user.getPacks(),PackDTO.class);
    }

    @Transactional
    public boolean packExists(PackDTO packDTO){
        return packRepo.existsByName(packDTO.getName());
    }

    @Transactional
    public void createPack(PackDTO packDTO, User user){

        Pack pack = modelMapper.map(packDTO, Pack.class);

        pack.addUser(user);
        user.addPack(pack);
        packRepo.save(pack);
    }

    public PackDTO createPackDTO(String packName, UUID uuid) {
        PackDTO packDTO = new PackDTO();
        packDTO.setName(packName);
        packDTO.setAuthorId(uuid);
        packDTO.setPublic(true);
        packDTO.setLikes(0);
        packDTO.setCreated(new Date());
        packDTO.setUsers(new ArrayList<>());
        return packDTO;
    }


}
