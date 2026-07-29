package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Entity.Setting;
import com.example.Shopmart_Backend1.Service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/setting")
@CrossOrigin("*")
public class SettingController {
    @Autowired
    private SettingService service;

    @PostMapping
    public Setting createSetting(@RequestBody Setting setting){
        return service.save(setting);
    }
    @GetMapping
    public List<Setting> getAll(){
        return service.getAll();
    }
    @PutMapping("/{id}")
    public Setting updateSetting(@PathVariable Long id, @RequestBody Setting setting){
        return service.update(id, setting);
    }
}