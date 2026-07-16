package com.hospital.hospitalmanagementbackend.organization.specialization.service;

import com.hospital.hospitalmanagementbackend.common.enums.Status;
import com.hospital.hospitalmanagementbackend.common.exception.DuplicateResourceException;
import com.hospital.hospitalmanagementbackend.common.exception.ResourceNotFoundException;
import com.hospital.hospitalmanagementbackend.organization.specialization.dto.request.CreateSpecializationRequest;
import com.hospital.hospitalmanagementbackend.organization.specialization.dto.request.UpdateSpecializationRequest;
import com.hospital.hospitalmanagementbackend.organization.specialization.dto.request.UpdateSpecializationStatusRequest;
import com.hospital.hospitalmanagementbackend.organization.specialization.dto.response.SpecializationDetailsResponse;
import com.hospital.hospitalmanagementbackend.organization.specialization.dto.response.SpecializationListResponse;
import com.hospital.hospitalmanagementbackend.organization.specialization.entity.Specialization;
import com.hospital.hospitalmanagementbackend.organization.specialization.repository.SpecializationRepository;
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
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    // =====================================================
    // Create
    // =====================================================

    @Transactional
    public SpecializationDetailsResponse createSpecialization(
            CreateSpecializationRequest request
    ) {

        validateDuplicateName(request.getName());

        Specialization specialization = new Specialization();

        specialization.setName(request.getName().trim());
        specialization.setDescription(request.getDescription());
        specialization.setStatus(Status.ACTIVE);

        Specialization savedSpecialization =
                specializationRepository.save(specialization);

        log.info("Specialization created : {}", savedSpecialization.getId());

        return mapToDetailsResponse(savedSpecialization);
    }

    // =====================================================
    // Get By Id
    // =====================================================

    @Transactional(readOnly = true)
    public SpecializationDetailsResponse getSpecialization(UUID id) {

        return mapToDetailsResponse(getSpecializationEntity(id));
    }

    // =====================================================
    // Get All
    // =====================================================

    @Transactional(readOnly = true)
    public Page<SpecializationListResponse> getSpecializations(
            Pageable pageable
    ) {

        return specializationRepository.findAll(pageable)
                .map(this::mapToListResponse);
    }

    // =====================================================
    // Update
    // =====================================================

    @Transactional
    public SpecializationDetailsResponse updateSpecialization(
            UUID id,
            UpdateSpecializationRequest request
    ) {

        Specialization specialization = getSpecializationEntity(id);

        if (specializationRepository.existsByNameIgnoreCaseAndIdNot(
                request.getName().trim(),
                id
        )) {

            throw new DuplicateResourceException(
                    "Specialization",
                    "name",
                    request.getName()
            );
        }

        specialization.setName(request.getName().trim());
        specialization.setDescription(request.getDescription());

        Specialization updated =
                specializationRepository.save(specialization);

        log.info("Specialization updated : {}", id);

        return mapToDetailsResponse(updated);
    }

    // =====================================================
    // Update Status
    // =====================================================

    @Transactional
    public SpecializationDetailsResponse updateSpecializationStatus(
            UUID id,
            UpdateSpecializationStatusRequest request
    ) {

        Specialization specialization = getSpecializationEntity(id);

        specialization.setStatus(request.getStatus());

        return mapToDetailsResponse(
                specializationRepository.save(specialization)
        );
    }

    // =====================================================
    // Private Methods
    // =====================================================

    private Specialization getSpecializationEntity(UUID id) {

        return specializationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Specialization",
                                "id",
                                id
                        ));
    }

    private void validateDuplicateName(String name) {

        if (specializationRepository.existsByNameIgnoreCase(
                name.trim()
        )) {

            throw new DuplicateResourceException(
                    "Specialization",
                    "name",
                    name
            );
        }
    }

    // =====================================================
    // Mapping
    // =====================================================

    private SpecializationDetailsResponse mapToDetailsResponse(
            Specialization specialization
    ) {

        return SpecializationDetailsResponse.builder()
                .id(specialization.getId())
                .name(specialization.getName())
                .description(specialization.getDescription())
                .status(specialization.getStatus())
                .createdAt(specialization.getCreatedAt())
                .build();
    }

    private SpecializationListResponse mapToListResponse(
            Specialization specialization
    ) {

        return SpecializationListResponse.builder()
                .id(specialization.getId())
                .name(specialization.getName())
                .status(specialization.getStatus())
                .build();
    }
}