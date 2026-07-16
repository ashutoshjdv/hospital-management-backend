package com.hospital.hospitalmanagementbackend.organization.staff.mapper;

import com.hospital.hospitalmanagementbackend.auth.entity.Profile;
import com.hospital.hospitalmanagementbackend.organization.staff.dto.response.StaffDetailsResponse;
import com.hospital.hospitalmanagementbackend.organization.staff.dto.response.StaffListResponse;
import com.hospital.hospitalmanagementbackend.organization.staff.entity.Staff;
import org.springframework.stereotype.Component;

@Component
public class StaffMapper {

    public StaffDetailsResponse toDetailsResponse(Staff staff) {

        return StaffDetailsResponse.builder()
                .id(staff.getId())
                .userId(staff.getUser().getId())
                .employeeCode(staff.getEmployeeCode())
                .employeeName(getEmployeeName(staff))
                .email(staff.getUser().getEmail())
                .departmentId(staff.getDepartment().getId())
                .departmentName(staff.getDepartment().getName())
                .designationId(staff.getDesignation().getId())
                .designationName(staff.getDesignation().getName())
                .reportingManagerId(
                        staff.getReportingManager() != null
                                ? staff.getReportingManager().getId()
                                : null
                )
                .reportingManagerName(
                        staff.getReportingManager() != null
                                ? getEmployeeName(staff.getReportingManager())
                                : null
                )
                .joiningDate(staff.getJoiningDate())
                .employmentType(staff.getEmploymentType())
                .salary(staff.getSalary())
                .status(staff.getStatus())
                .createdAt(staff.getCreatedAt())
                .updatedAt(staff.getUpdatedAt())
                .build();
    }

    public StaffListResponse toListResponse(Staff staff) {

        return StaffListResponse.builder()
                .id(staff.getId())
                .employeeCode(staff.getEmployeeCode())
                .employeeName(getEmployeeName(staff))
                .designation(staff.getDesignation().getName())
                .department(staff.getDepartment().getName())
                .status(staff.getStatus())
                .build();
    }

    private String getEmployeeName(Staff staff) {

        if (staff == null || staff.getUser() == null) {
            return "";
        }

        Profile profile = staff.getUser().getProfile();

        if (profile == null) {
            return "";
        }

        String firstName = profile.getFirstName() == null
                ? ""
                : profile.getFirstName();

        String lastName = profile.getLastName() == null
                ? ""
                : profile.getLastName();

        return (firstName + " " + lastName).trim();
    }
}
