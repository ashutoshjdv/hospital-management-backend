package com.hospital.hospitalmanagementbackend.organization.designation.service;

import com.hospital.hospitalmanagementbackend.common.enums.Status;
import com.hospital.hospitalmanagementbackend.common.exception.DuplicateResourceException;
import com.hospital.hospitalmanagementbackend.common.exception.ResourceNotFoundException;
import com.hospital.hospitalmanagementbackend.organization.designation.dto.request.CreateDesignationRequest;
import com.hospital.hospitalmanagementbackend.organization.designation.dto.request.UpdateDesignationRequest;
import com.hospital.hospitalmanagementbackend.organization.designation.dto.request.UpdateDesignationStatusRequest;
import com.hospital.hospitalmanagementbackend.organization.designation.dto.response.DesignationDetailsResponse;
import com.hospital.hospitalmanagementbackend.organization.designation.dto.response.DesignationListResponse;
import com.hospital.hospitalmanagementbackend.organization.designation.entity.Designation;
import com.hospital.hospitalmanagementbackend.organization.designation.repository.DesignationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DesignationService {

    // =====================================================
    // Dependencies
    // =====================================================

    private final DesignationRepository designationRepository;

    // =====================================================
    // Public Methods
    // =====================================================

    @Transactional
    public DesignationDetailsResponse createDesignation(CreateDesignationRequest request) {

        log.info("Creating designation : {}", request.getName());

        validateDuplicateName(request.getName());

        Designation designation = new Designation();

        designation.setName(request.getName().trim());
        designation.setDescription(request.getDescription());
        designation.setLevel(request.getLevel());
        designation.setCategory(request.getCategory());

        Designation savedDesignation = designationRepository.save(designation);

        log.info("Designation created successfully with id {}", savedDesignation.getId());

        return mapToDetailsResponse(savedDesignation);
    }

    @Transactional(readOnly = true)
    public DesignationDetailsResponse getDesignationById(UUID id) {

        log.info("Fetching designation : {}", id);

        Designation designation = getDesignationEntity(id);

        return mapToDetailsResponse(designation);
    }

    @Transactional(readOnly = true)
    public Page<DesignationListResponse> getAllDesignations(Pageable pageable) {

        log.info("Fetching designation list");

        return designationRepository
                .findAll(pageable)
                .map(this::mapToListResponse);
    }

    @Transactional
    public DesignationDetailsResponse updateDesignation(
            UUID id,
            UpdateDesignationRequest request
    ) {

        log.info("Updating designation : {}", id);

        Designation designation = getDesignationEntity(id);

        if (designationRepository.existsByNameIgnoreCaseAndIdNot(
                request.getName(),
                id
        )) {

            throw new DuplicateResourceException(
                    "Designation",
                    "name",
                    request.getName()
            );
        }

        designation.setName(request.getName().trim());
        designation.setDescription(request.getDescription());
        designation.setLevel(request.getLevel());
        designation.setCategory(request.getCategory());

        Designation updatedDesignation = designationRepository.save(designation);

        log.info("Designation updated successfully : {}", id);

        return mapToDetailsResponse(updatedDesignation);
    }

    @Transactional
    public DesignationDetailsResponse changeDesignationStatus(
            UUID id,
            UpdateDesignationStatusRequest request
    ) {

        log.info("Updating designation status : {}", id);

        Designation designation = getDesignationEntity(id);

        designation.setStatus(request.getStatus());

        Designation updatedDesignation = designationRepository.save(designation);

        return mapToDetailsResponse(updatedDesignation);
    }

    // =====================================================
    // Private Helper Methods
    // =====================================================

    private Designation getDesignationEntity(UUID id) {

        return designationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Designation",
                                "id",
                                id
                        ));
    }

    private void validateDuplicateName(String name) {

        if (designationRepository.existsByNameIgnoreCase(name)) {

            throw new DuplicateResourceException(
                    "Designation",
                    "name",
                    name
            );
        }
    }

    // =====================================================
    // Mapping Methods
    // =====================================================

    private DesignationDetailsResponse mapToDetailsResponse(
            Designation designation
    ) {

        return DesignationDetailsResponse.builder()
                .id(designation.getId())
                .name(designation.getName())
                .description(designation.getDescription())
                .level(designation.getLevel())
                .category(designation.getCategory())
                .status(designation.getStatus())
                .createdAt(designation.getCreatedAt())
                .build();
    }

    private DesignationListResponse mapToListResponse(
            Designation designation
    ) {

        return DesignationListResponse.builder()
                .id(designation.getId())
                .name(designation.getName())
                .level(designation.getLevel())
                .category(designation.getCategory())
                .status(designation.getStatus())
                .build();
    }

}
