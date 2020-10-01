package com.fluxinfotech.pharmacymanagementsystem.user;

import com.fluxinfotech.pharmacymanagementsystem.company.Company;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO saveOrUpdate(UserDTO payload) {
        UserDTO userDTO = null;
        try {
            User user = new User();
            if (payload.getId() != null) {
                user = getEntityById(payload.getId());
            }
            modelMapper.map(payload, user);

            User savedUser = userRepository.save(user);
            userDTO = modelMapper.map(savedUser, UserDTO.class);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return userDTO;
    }

    private User getEntityById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    public UserDTO getById(Long id) {

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return modelMapper.map(userOptional.get(), UserDTO.class);
        }
        return null;
    }

    public Page<UserDTO> getAllUser(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        Page<UserDTO> userDTOS = users
                .map(user -> modelMapper.map(user, UserDTO.class));
        return userDTOS;
    }

    @Transactional
    public Boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }
}
