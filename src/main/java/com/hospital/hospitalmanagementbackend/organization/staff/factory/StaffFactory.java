package com.hospital.hospitalmanagementbackend.organization.staff.factory;

import com.hospital.hospitalmanagementbackend.auth.entity.User;
import com.hospital.hospitalmanagementbackend.common.enums.StaffStatus;
import com.hospital.hospitalmanagementbackend.organization.department.entity.Department;
import com.hospital.hospitalmanagementbackend.organization.designation.entity.Designation;
import com.hospital.hospitalmanagementbackend.organization.hospital.entity.Hospital;
import com.hospital.hospitalmanagementbackend.organization.staff.dto.request.CreateStaffRequest;
import com.hospital.hospitalmanagementbackend.organization.staff.entity.Staff;
import org.springframework.stereotype.Component;

@Component
public class StaffFactory {

    public Staff create(
            CreateStaffRequest request,
            User user,
            Hospital hospital,
            Department department,
            Designation designation,
            Staff reportingManager,
            String employeeCode
    ) {

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

        return staff;
    }
}
