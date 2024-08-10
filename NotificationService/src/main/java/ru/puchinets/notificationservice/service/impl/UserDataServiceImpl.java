package ru.puchinets.notificationservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.puchinets.notificationservice.enums.NotificationProvider;
import ru.puchinets.notificationservice.model.command.UserUpdate;
import ru.puchinets.notificationservice.model.entity.UserData;
import ru.puchinets.notificationservice.repository.UserDataRepository;
import ru.puchinets.notificationservice.service.UserDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.puchinets.notificationservice.Constants.USER_UPDATE_COMMAND_TYPE_EX;
import static ru.puchinets.notificationservice.enums.NotificationProvider.*;
import static ru.puchinets.notificationservice.enums.UserUpdateCommandType.*;

@Service
@RequiredArgsConstructor
public class UserDataServiceImpl implements UserDataService {

    private final UserDataRepository repository;

    @Override
    public List<UserData> create(UserUpdate userUpdate) {
        if (userUpdate.getType() != CREATE) throw new IllegalArgumentException(USER_UPDATE_COMMAND_TYPE_EX);
        List<UserData> dataList = new ArrayList<>();
        if (userUpdate.getEmail() != null) {
            dataList.add(UserData
                    .builder()
                    .userId(userUpdate.getUserId())
                    .data(userUpdate.getEmail())
                    .provider(MAIL)
                    .build());
        }
        if (userUpdate.getPhone() != null) {
            dataList.add(UserData
                    .builder()
                    .userId(userUpdate.getUserId())
                    .data(userUpdate.getPhone())
                    .provider(SMS)
                    .build());
        }
        dataList = repository.saveAll(dataList);
        return dataList;
    }

    @Override
    public UserData save(UserData data) {
        Optional<UserData> fromDb = repository.findByUserIdAndProvider(data.getUserId(), data.getProvider());
        if (fromDb.isEmpty()) return repository.save(data);
        else {
            UserData userData = fromDb.get();
            userData.setData(data.getData());
            return repository.save(userData);
        }

    }

    @Override
    public boolean delete(UserUpdate userUpdate) {
        if (userUpdate.getType() != DELETE) throw new IllegalArgumentException(USER_UPDATE_COMMAND_TYPE_EX);
        if (userUpdate.getUserId() == null) return false;
        repository.deleteAllByUserId(userUpdate.getUserId());
        return true;
    }

    @Override
    public List<UserData> update(UserUpdate userUpdate) {
        List<UserData> result = new ArrayList<>();
        if (userUpdate.getType() != UPDATE) throw new IllegalArgumentException(USER_UPDATE_COMMAND_TYPE_EX);
        Optional<UserData> mailFromDb = repository.findByUserIdAndProvider(userUpdate.getUserId(), MAIL);
        Optional<UserData> smsFromDb = repository.findByUserIdAndProvider(userUpdate.getUserId(), SMS);
        if (userUpdate.getPhone() != null) {
            UserData data = smsFromDb
                    .map(userData -> {
                        userData.setData(userUpdate.getPhone());
                        return userData;
                    })
                    .orElse(new UserData(null, userUpdate.getUserId(), userUpdate.getPhone(), SMS));
            result.add(save(data));
        }
        if (userUpdate.getEmail() != null) {
            UserData data = mailFromDb
                    .map(userData -> {
                        userData.setData(userUpdate.getEmail());
                        return userData;
                    })
                    .orElse(new UserData(null, userUpdate.getUserId(), userUpdate.getEmail(), MAIL));
            result.add(save(data));
        }
        return result;
    }

    @Override
    public List<UserData> getByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public Optional<UserData> findByEmail(String email) {
        return repository.findByProviderAndData(MAIL, email);
    }

    @Override
    public void deleteUserData(String data, NotificationProvider provider) {
        repository.deleteUserDataByDataAndProvider(data, provider);
    }
}
