package com.crackerStudents.projectApp.service;


import com.crackerStudents.projectApp.DTO.UserDTO;
import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.Role;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.repos.PackRepo;
import com.crackerStudents.projectApp.repos.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;


@Service
public class UserService implements UserDetailsService {
    @Value("${pathNameUpload}")
    private String uploadPath;

    private UserRepo userRepo;
    private PackRepo packRepo;
    private MailSender mailSender;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepo userRepo, @Lazy PackRepo packRepo, MailSender mailSender, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.packRepo = packRepo;
        this.mailSender = mailSender;
        this.modelMapper = modelMapper;
    }

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        sendMessage(user);

        return true;
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Memory. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        userRepo.save(user);

        return true;
    }


    public void updateProfile(String username, String password, String email, String firstName, String lastName, MultipartFile file, User user) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            user.setAvatar(resultFilename);
        }

        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) || (userEmail != null && !userEmail.equals(email));
        if (isEmailChanged) {
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        if (!StringUtils.isEmpty(firstName)) {
            user.setFirstName(firstName);
        }
        if (!StringUtils.isEmpty(lastName)) {
            user.setLastName(lastName);
        }
        userRepo.save(user);
        if (isEmailChanged) {
            sendMessage(user);
        }
    }

    public UserDTO getUserDTOByName(String username) {
        return modelMapper.map(userRepo.findByUsername(username), UserDTO.class);
    }

    /**
     * Тут мы добавляем подписчика к user.
     *
     * @param currentUser
     * @param user
     */
    @Transactional
    public void subscribe(User currentUser, User user) {
        user.getSubscribers().add(currentUser);
        userRepo.save(user);
    }

    /**
     * Тут мы удаляем подписчика у пользователя user.
     *
     * @param currentUser - это пользователь @AuthenticationPrincipal
     * @param user        - это на чьем мы профиле сейчас
     */
    @Transactional
    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().remove(currentUser);
        System.out.println("getSubscribers:");
        for (User el : user.getSubscribers()) {
            System.out.println(el.getUsername());
        }
        userRepo.save(user);
    }

    public String getUserNameById(UUID authorId) {
        return userRepo.findById(authorId).get().getUsername();
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Transactional
    public void addPackDeepClone(User user, UUID idPack) {
        Pack packForeign = packRepo.findById(idPack).get();
        Pack myPack = new Pack();
        myPack.setAuthorId(user.getId());
        myPack.setCreated(new Date());
        myPack.setLikes(0);
        myPack.setName(packForeign.getName());
        myPack.setPublic(false);
        Set<Card> packForeignCards = packForeign.getCards();
        for (Card el : packForeignCards) {
            Card card = new Card(el.getQuestion(), el.getAnswer(), user);
            myPack.addCard(card);
        }
        user.addPack(myPack);
        packRepo.save(myPack);
        userRepo.save(user);
    }
}
