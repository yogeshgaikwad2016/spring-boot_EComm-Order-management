package com.project.ecommerce.order_management.controller;

import com.project.ecommerce.order_management.service.AwsMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/aws")
public class MetadataController {

    @Autowired
    AwsMetadataService awsMetadataService;

    @GetMapping("/metadata")
    public Map<String, String> getMappings() {
        Map<String, String> response = new HashMap<>();
        response.put("Region", awsMetadataService.getRegion());
        response.put("AZ", awsMetadataService.getAvailabilityZones());

        return response;
    }
}
