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
import java.util.List;
import java.util.Set;

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


}
