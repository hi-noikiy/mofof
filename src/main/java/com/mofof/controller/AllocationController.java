package com.mofof.controller;

import com.mofof.dto.AllocationInfo;
import com.mofof.entity.relation.Allocation;
import com.mofof.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by hzh on 17/6/17.
 */
@RestController
@RequestMapping(path = "/allocation")
public class AllocationController {
    @Autowired
    AllocationService allocationService;

    @GetMapping(path = "/allocationinfo")
    public AllocationInfo allocationInfo(Long investRelationId) {
        return allocationService.census(investRelationId);
    }

    @GetMapping(path = "/platformallocationinfo")
    public AllocationInfo platformAllocationInfo(Long platformId) {
        return allocationService.platformCensus(platformId);
    }

    @GetMapping(path = "/allocations")
    public List<Allocation> allocations(Long investRelationId, int allocationType, @RequestParam("startDateForSearch")
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDateForSearch, @RequestParam("endDateForSearch")
                                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDateForSearch) {
        return allocationService.findAllocations(investRelationId, allocationType, startDateForSearch, endDateForSearch);
    }

    @GetMapping(path = "/platformallocations")
    public List<Allocation> platformAllocations(Long platformId, int allocationType, @RequestParam("startDateForSearch")
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDateForSearch, @RequestParam("endDateForSearch")
                                                @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDateForSearch) {
        return allocationService.findPlatformAllocations(platformId, allocationType, startDateForSearch, endDateForSearch);
    }

    @PostMapping(path = "/save")
    public Allocation save(@RequestBody Allocation allocation) {
        return allocationService.save(allocation);
    }
}
