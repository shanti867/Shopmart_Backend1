package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Entity.Setting;
import com.example.Shopmart_Backend1.Repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {
    @Autowired
    private SettingRepository repository;

    public Setting save(Setting setting){
        return repository.save(setting);
    }
    public List<Setting> getAll(){
        return repository.findAll();
    }
    public Setting update(Long id, Setting setting){
        Setting oldSetting = repository.findById(id).orElseThrow();
        oldSetting.setSiteName(setting.getSiteName());
        oldSetting.setAddress(setting.getAddress());
        oldSetting.setMap1(setting.getMap1());
        oldSetting.setMap2(setting.getMap2());
        oldSetting.setEmail(setting.getEmail());
        oldSetting.setPhone(setting.getPhone());
        oldSetting.setWhatsapp(setting.getWhatsapp());
        oldSetting.setFacebook(setting.getFacebook());
        oldSetting.setTwitter(setting.getTwitter());
        oldSetting.setInstagram(setting.getInstagram());
        oldSetting.setLinkedin(setting.getLinkedin());
        oldSetting.setYoutube(setting.getYoutube());
        oldSetting.setPrivacyPolicy(setting.getPrivacyPolicy());
        oldSetting.setTermsAndConditions(setting.getTermsAndConditions());
        oldSetting.setRefundPolicy(setting.getRefundPolicy());

        return repository.save(oldSetting);
    }
}