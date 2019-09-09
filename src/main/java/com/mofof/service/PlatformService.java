package com.mofof.service;

import com.mofof.entity.platform.Platform;
import com.mofof.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author hzh
 * @date 2019-06-29
 */
@Service
@Transactional
public class PlatformService {
    @Autowired
    PlatformRepository platformRepository;

    public List<Platform> findAll() {
        return  platformRepository.findAllByOrderByIdDesc();
    }

    public Platform findOne(Long id) {
        return platformRepository.findOne(id);
    }
    public void delete(Long id) {
        platformRepository.delete(id);
    }

    public Platform save(Platform platform) {
        return platformRepository.save(platform);
    }
}
