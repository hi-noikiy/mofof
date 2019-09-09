package com.mofof.service;

import com.mofof.entity.fund.Agreement;
import com.mofof.repository.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author HanWeiFeng 2017年7月1日
 */
@Service
@Transactional
public class AgreementService {
    @Autowired
    AgreementRepository agreementRepository;

    public Agreement save(Agreement agreement) {
        return agreementRepository.save(agreement);
    }

    public List<Agreement> findAllAgreements(Long fundId) {
        return agreementRepository.findAllByFundIdOrderByIdDesc(fundId);
    }

    public Agreement findById(Long id) {
        return agreementRepository.findOne(id);
    }

    public void delete(Agreement agreement){agreementRepository.delete(agreement);}
}
