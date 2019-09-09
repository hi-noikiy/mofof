package com.mofof.service;

import com.mofof.repository.ProjectOwershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by hzh on 17/7/10.
 */
@Service
@Transactional
public class ProjectOwershipService {
    @Autowired
    ProjectOwershipRepository projectOwershipRepository;
}
