package com.hospital.hospitalmanagementbackend.organization.staff.service;

import com.hospital.hospitalmanagementbackend.auth.entity.User;
import com.hospital.hospitalmanagementbackend.auth.repository.UserRepository;
import com.hospital.hospitalmanagementbackend.common.enums.DesignationCategory;
import com.hospital.hospitalmanagementbackend.common.enums.StaffStatus;
import com.hospital.hospitalmanagementbackend.common.enums.Status;
import com.hospital.hospitalmanagementbackend.common.exception.DuplicateResourceException;
import com.hospital.hospitalmanagementbackend.common.exception.ResourceNotFoundException;
import com.hospital.hospitalmanagementbackend.organization.department.entity.Department;
import com.hospital.hospitalmanagementbackend.organization.department.repository.DepartmentRepository;
import com.hospital.hospitalmanagementbackend.organization.designation.entity.Designation;
import com.hospital.hospitalmanagementbackend.organization.designation.repository.DesignationRepository;
import com.hospital.hospitalmanagementbackend.organization.doctorprofile.entity.DoctorProfile;
import com.hospital.hospitalmanagementbackend.organization.doctorprofile.repository.DoctorProfileRepository;
import com.hospital.hospitalmanagementbackend.organization.hospital.entity.Hospital;
import com.hospital.hospitalmanagementbackend.organization.hospital.repository.HospitalRepository;
import com.hospital.hospitalmanagementbackend.organization.staff.dto.request.CreateStaffRequest;
import com.hospital.hospitalmanagementbackend.organization.staff.dto.request.UpdateStaffRequest;
import com.hospital.hospitalmanagementbackend.organization.staff.dto.request.UpdateStaffStatusRequest;
import com.hospital.hospitalmanagementbackend.organization.staff.dto.response.StaffDetailsResponse;
import com.hospital.hospitalmanagementbackend.organization.staff.dto.response.StaffListResponse;
import com.hospital.hospitalmanagementbackend.organization.staff.entity.Staff;
import com.hospital.hospitalmanagementbackend.organization.staff.mapper.StaffMapper;
import com.hospital.hospitalmanagementbackend.organization.staff.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StaffService {

    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final DesignationRepository designationRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorProfileRepository doctorProfileRepository;

    private final StaffMapper staffMapper;

    @Transactional
    public StaffDetailsResponse createStaff(CreateStaffRequest request) {

        User user = getUser(request.getUserId());

        validateDuplicateUser(user.getId());

        Hospital hospital = getHospital();

        Department department = getDepartment(request.getDepartmentId());

        Designation designation = getDesignation(request.getDesignationId());

        Staff reportingManager = getReportingManager(
                request.getReportingManagerId()
        );

        String employeeCode = generateEmployeeCode(hospital.getId());

        Staff staff = new Staff();

        staff.setUser(user);
        staff.setHospital(hospital);
        staff.setDepartment(department);
        staff.setDesignation(designation);
        staff.setReportingManager(reportingManager);
        staff.setEmployeeCode(employeeCode);
        staff.setJoiningDate(request.getJoiningDate());
        staff.setEmploymentType(request.getEmploymentType());
        staff.setSalary(request.getSalary());
        staff.setStatus(StaffStatus.ACTIVE);

        Staff savedStaff = staffRepository.save(staff);

        createDoctorProfileIfRequired(savedStaff);

        log.info("Staff created successfully : {}", savedStaff.getId());

        return staffMapper.toDetailsResponse(savedStaff);
    }

    // ======================================================
    // Private Helper Methods
    // ======================================================

    private User getUser(UUID userId) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User",
                                "id",
                                userId
                        ));
    }

    private Hospital getHospital() {

        return hospitalRepository.findFirstByOrderByCreatedAtAsc()
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hospital",
                                "default",
                                "record"
                        ));
    }

    private Department getDepartment(UUID departmentId) {

        return departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department",
                                "id",
                                departmentId
                        ));
    }

    private Designation getDesignation(UUID designationId) {

        return designationRepository.findById(designationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Designation",
                                "id",
                                designationId
                        ));
    }

    private Staff getReportingManager(UUID reportingManagerId) {

        if (reportingManagerId == null) {
            return null;
        }

        return staffRepository.findById(reportingManagerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Staff",
                                "id",
                                reportingManagerId
                        ));
    }

    private void validateDuplicateUser(UUID userId) {

        if (staffRepository.existsByUser_Id(userId)) {

            throw new DuplicateResourceException(
                    "Staff",
                    "user",
                    userId
            );
        }
    }

    private void createDoctorProfileIfRequired(Staff staff) {

        if (staff.getDesignation().getCategory() != DesignationCategory.DOCTOR) {
            return;
        }

        DoctorProfile doctorProfile = new DoctorProfile();

        doctorProfile.setStaff(staff);
        doctorProfile.setLicenseNumber(
                "DOC-" + UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase()
        );
        doctorProfile.setQualification("");
        doctorProfile.setExperienceYears(0);
        doctorProfile.setConsultationFee(BigDecimal.ZERO);
        doctorProfile.setBio("");

        doctorProfileRepository.save(doctorProfile);

        log.info("Doctor profile created for staff : {}", staff.getId());
    }

    private String generateEmployeeCode(UUID hospitalId) {

        long count = staffRepository.countByHospital_Id(hospitalId);

        return String.format("EMP%06d", count + 1);
    }

    @Transactional(readOnly = true)
    public StaffDetailsResponse getStaff(UUID staffId) {

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Staff",
                                "id",
                                staffId
                        ));

        return staffMapper.toDetailsResponse(staff);
    }

    @Transactional(readOnly = true)
    public Page<StaffListResponse> getAllStaff(Pageable pageable) {

        return staffRepository.findAll(pageable)
                .map(staffMapper::toListResponse);
    }


    @Transactional(readOnly = true)
    public Page<StaffListResponse> getStaffByDepartment(
            UUID departmentId,
            Pageable pageable
    ) {

        departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department",
                                "id",
                                departmentId
                        ));

        return staffRepository.findByDepartment_Id(
                        departmentId,
                        pageable
                )
                .map(staffMapper::toListResponse);
    }


    @Transactional
    public StaffDetailsResponse updateStaffStatus(
            UUID staffId,
            UpdateStaffStatusRequest request
    ) {

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Staff",
                                "id",
                                staffId
                        ));

        staff.setStatus(request.getStatus());

        Staff updated = staffRepository.save(staff);

        log.info("Updated staff status : {}", updated.getId());

        return staffMapper.toDetailsResponse(updated);
    }


    @Transactional
    public StaffDetailsResponse updateStaff(
            UUID staffId,
            UpdateStaffRequest request
    ) {

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Staff",
                                "id",
                                staffId
                        ));

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department",
                                "id",
                                request.getDepartmentId()
                        ));

        Designation designation = designationRepository.findById(request.getDesignationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Designation",
                                "id",
                                request.getDesignationId()
                        ));

        Staff reportingManager = null;

        if (request.getReportingManagerId() != null) {

            reportingManager = staffRepository.findById(request.getReportingManagerId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Staff",
                                    "id",
                                    request.getReportingManagerId()
                            ));

            validateReportingManager(reportingManager, staff);
        }

        validateDepartment(department, staff.getHospital());

        validateDesignation(designation);

        staff.setDepartment(department);
        staff.setDesignation(designation);
        staff.setReportingManager(reportingManager);
        staff.setJoiningDate(request.getJoiningDate());
        staff.setEmploymentType(request.getEmploymentType());
        staff.setSalary(request.getSalary());

        Staff updatedStaff = staffRepository.save(staff);

        createDoctorProfileIfRequired(updatedStaff);

        log.info("Staff updated successfully : {}", updatedStaff.getId());

        return staffMapper.toDetailsResponse(updatedStaff);
    }

    private void validateDepartment(
            Department department,
            Hospital hospital
    ) {

        if (!department.getHospital().getId().equals(hospital.getId())) {
            throw new IllegalArgumentException(
                    "Department does not belong to the current hospital."
            );
        }

        if (department.getStatus() != Status.ACTIVE) {
            throw new IllegalArgumentException(
                    "Department is inactive."
            );
        }
    }

    private void validateDesignation(
            Designation designation
    ) {

        if (designation.getStatus() != Status.ACTIVE) {
            throw new IllegalArgumentException(
                    "Designation is inactive."
            );
        }
    }

    private void validateReportingManager(
            Staff manager,
            Staff currentStaff
    ) {

        if (manager.getId().equals(currentStaff.getId())) {
            throw new IllegalArgumentException(
                    "Reporting manager cannot be the same employee."
            );
        }

        if (manager.getStatus() != StaffStatus.ACTIVE) {
            throw new IllegalArgumentException(
                    "Reporting manager is inactive."
            );
        }

        if (!manager.getHospital().getId().equals(currentStaff.getHospital().getId())) {
            throw new IllegalArgumentException(
                    "Reporting manager belongs to another hospital."
            );
        }
    }

}
