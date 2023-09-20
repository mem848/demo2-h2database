package com.sherwin.demo2.service;

import com.sherwin.demo2.infrastructure.Labor;
import com.sherwin.demo2.domain.entity.LaborEntity;
import com.sherwin.demo2.domain.repository.LaborRepository;
import com.sherwin.demo2.rest.resources.mappers.LaborMapper;
import com.sherwin.demo2.rest.resources.v1.LaborResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LaborService {
    //this class should take Labor object and calculate price for labor
    @Autowired
    private LaborRepository repository;
    @Autowired
    private LaborMapper mapper;

    public LaborEntity setPrice(Labor labor)
    {
        labor.setCost(labor.getLength()* labor.getWidth()*labor.getPricePerSqft());
        LaborEntity entity = mapper.fromLaborToLaborEntity(labor);
        return repository.save(entity);
    }

    public Iterable<LaborEntity> getAllLabor()
    {
        return repository.findAll();
    }

    public Optional<LaborEntity> getLabor(Integer id)
    {
        return repository.findById(id);
    }

    public Optional<LaborEntity> deleteLabor(Integer id)
    {
        Optional<LaborEntity> deletedLabor = repository.findById(id);
        repository.deleteById(id);
        return deletedLabor;
    }

    public LaborResponse mapFromOptional (Optional<LaborEntity> entity)
    {
        LaborResponse response = new LaborResponse();
        entity.ifPresent(res -> res.setLength(entity.get().getLength()));
        return response;

    }
//    public Optional<LaborEntity> updateLabor(Optional<LaborEntity> currentLabor, Labor updateLabor)
//    {
//        //here we update all labor fields
//        //if I use a functional interface, I can only use one abstract method
//        currentLabor.ifPresent(labor -> labor.setLength(updateLabor.getLength()));
//        currentLabor.ifPresent(labor -> labor.setWidth(updateLabor.getWidth()));
//        currentLabor.ifPresent(labor -> labor.setPricePerSqft(updateLabor.getPricePerSqft()));
//        currentLabor.ifPresent(labor
//                -> labor.setCost(updateLabor.getLength()*updateLabor.getWidth()* updateLabor.getPricePerSqft()));
//        currentLabor.ifPresent(labor -> repository.save(labor));
//        return currentLabor;
//    }

    public LaborEntity updateLabor(LaborEntity currentLabor, Labor updateLabor)
    {
        currentLabor.setLength(updateLabor.getLength());
        currentLabor.setWidth(updateLabor.getWidth());
        currentLabor.setPricePerSqft(updateLabor.getPricePerSqft());
        currentLabor.setCost(updateLabor.getLength()*updateLabor.getWidth()*updateLabor.getPricePerSqft());
        return repository.save(currentLabor);
    }
}
