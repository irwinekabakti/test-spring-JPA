package com.example.demo_test_spring_JPA.Metadata;

import org.springframework.stereotype.Controller;

@Controller
public class MetadataController {
    private final MetadataService metadataService;
    public MetadataController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    public Metadata createMetadata(Metadata metadata) {
        return metadataService.createMetadata(metadata);
    }
}
