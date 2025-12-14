package com.project.ecommerce.order_management.service;

import com.amazonaws.util.EC2MetadataUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
public class AwsMetadataService {

    public String getAvailabilityZones() {
        return EC2MetadataUtils.getAvailabilityZone();
    }

    public String getRegion() {
        String az = getAvailabilityZones();
        if (az != null && az.length() > 1) {
            return az.substring(0, az.length() - 1);
        }

        return null;
    }
}
