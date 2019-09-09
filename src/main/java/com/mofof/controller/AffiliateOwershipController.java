package com.mofof.controller;

import com.mofof.service.AffiliateOwershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hzh on 17/7/10.
 */
@RestController
@RequestMapping(path = "/affiliate-owership")
public class AffiliateOwershipController {
    @Autowired
    AffiliateOwershipService affiliateOwershipService;
}
