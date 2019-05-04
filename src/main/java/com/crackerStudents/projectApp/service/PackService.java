package com.crackerStudents.projectApp.service;

import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.convert.ObjectMapperUtils;
import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.CardRepo;
import com.crackerStudents.projectApp.repos.PackRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class PackService {
    private final CardRepo cardRepo;
    private final PackRepo packRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public PackService(PackRepo packRepo, ModelMapper modelMapper, CardRepo cardRepo) {
        this.packRepo = packRepo;
        this.modelMapper = modelMapper;
        this.cardRepo = cardRepo;
    }

    @Transactional
    public void addCardAndSave(Card card, Pack pack) {
        cardRepo.save(card);
        pack.addCard(card);
    }

    @Transactional
    public PackDTO getPackDTOByName(String packName) {
        return modelMapper.map(packRepo.findByName(packName), PackDTO.class);
    }

    @Transactional
    public List<PackDTO> getUserPacks(User user) {
        //Преобразуем сет паков в лист дто-паков
        return ObjectMapperUtils.mapAll(user.getPacks(), PackDTO.class);
    }

    @Transactional
    public boolean packExists(String packName, User user) {
        Set<Pack> packs = user.getPacks();
        for(Pack pack: packs) {
            if (pack.getName().equals(packName)) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void createPack(String packName, User user) {
        Pack pack = new Pack(packName,user.getId(),true,0,new Date());
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

    public Pack getPackByName(String packName, User user) {
        // сюда dto не надо засовывать
        for (Pack pack : user.getPacks()) {
            if (pack.getName().contains(packName)) {
                return pack;
            }
        }
        return null;
    }
}
