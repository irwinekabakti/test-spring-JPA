package com.example.demo_test_spring_JPA.Metadata;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MetadataService {

    private MetadataRepository metadataRepository;

    public MetadataService(MetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    public Iterable<Metadata> getMetadata() {
        return metadataRepository.findAll();
    }

    public Metadata createMetadata(Metadata metadata) {
        return metadataRepository.save(metadata);
    }

    public Metadata updateMetadata(Integer id, Metadata metadata) {
        Optional<Metadata> metadataToUpdate = metadataRepository.findByProductId(id);
        if (metadataToUpdate.isEmpty()) {
            return null;
        }
        Metadata updatedMetadata = metadataToUpdate.get();
        if (updatedMetadata.getIncrement() != null) {
            updatedMetadata.setIncrement(metadata.getIncrement());
        }
        if (updatedMetadata.getUnit() != null && updatedMetadata.getUnit() != "") {
            updatedMetadata.setUnit(metadata.getUnit());
        }
        if (updatedMetadata.getWeight() != null) {
            updatedMetadata.setWeight(metadata.getWeight());
        }
        if (updatedMetadata.getCalorie() != null) {
            updatedMetadata.setCalorie(metadata.getCalorie());
        }
        if (updatedMetadata.getProteins() != null) {
            updatedMetadata.setProteins(metadata.getProteins());
        }
        if (updatedMetadata.getFats() != null) {
            updatedMetadata.setFats(metadata.getFats());
        }
        if (updatedMetadata.getCarbs() != null) {
            updatedMetadata.setCarbs(metadata.getCarbs());
        }
        return metadataRepository.save(updatedMetadata);
    }
}