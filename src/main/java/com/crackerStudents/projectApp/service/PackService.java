package com.crackerStudents.projectApp.service;

import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.convert.ObjectMapperUtils;
import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.PackRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PackService {

    private final PackRepo packRepo;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public PackService(PackRepo packRepo, ModelMapper modelMapper, UserService userService) {
        this.packRepo = packRepo;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Transactional
    public void addCardAndSave(Card card, UUID packId) {
        Pack pack = packRepo.findById(packId).orElse(null);
        card.setNext_practice_time(new Date());
        card.setConsecutive_correct_answer(0);
        card.setLast_time_easy(new Date());
        pack.addCard(card);
        packRepo.save(pack);
    }

    @Transactional
    public PackDTO getPackDTOByName(String packName) {
        return modelMapper.map(packRepo.findByName(packName), PackDTO.class);
    }

    @Transactional
    public PackDTO getPackByUserAndName(String packName, User user) {
        Set<Pack> packs = user.getPacks();

        for (Pack pack : packs) {
            if (pack.getName().equals(packName)) {
                return modelMapper.map(pack, PackDTO.class);
            }
        }
        return null;
    }

    @Transactional
    public List<PackDTO> getUserPacks(User user) {
        //Преобразуем сет паков в лист дто-паков
        return ObjectMapperUtils.mapAll(user.getPacks(), PackDTO.class);
    }

    @Transactional
    public boolean packExists(String packName, User user) {
        Set<Pack> packs = user.getPacks();
        for (Pack pack : packs) {
            if (pack.getName().equals(packName)) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void createPack(String packName, User user, boolean packPublic) {
        Pack pack = new Pack(packName, user.getId(), packPublic, 0, new Date());
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


    public List<PackDTO> getAllPacksDTO() {
        List<Pack> packs = packRepo.findAll();
        List<PackDTO> packsDTO = new ArrayList<>();
        for (Pack pack : packs) {
            PackDTO packDTO = modelMapper.map(pack, PackDTO.class);
            UUID authorId = packDTO.getAuthorId();
            String name = userService.getUserNameById(authorId);
            packDTO.setAuthorName(name);
            packsDTO.add(packDTO);
        }
        return packsDTO;
    }

    public Page<PackDTO> getAllPackDTO(Pageable pageable) {
        Page<Pack> all = packRepo.findAll(pageable);
        Page<PackDTO> allDTO = all.map(x -> modelMapper.map(x, PackDTO.class));
        return allDTO;
    }

    public Pack getPackById(UUID id) {
        return packRepo.findById(id).orElse(null);
    }

    public PackDTO getPackDTOByID(UUID packId) {
        return modelMapper.map(packRepo.findById(packId).orElse(null), PackDTO.class);
    }

    public Page<PackDTO> getAllByUserPackDTO(Pageable pageable, User user) {
        Page<Pack> all = packRepo.findByAuthorId(pageable, user.getId());
        Page<PackDTO> allDTO = all.map(x -> modelMapper.map(x, PackDTO.class));
        return allDTO;
    }

    public Page<PackDTO> getAllPackDTOByPublic(Pageable pageable) {
        Page<Pack> all = packRepo.findByIsPublicTrue(pageable);
        Page<PackDTO> allDTO = all.map(x -> modelMapper.map(x, PackDTO.class));
        return allDTO;
    }
}
