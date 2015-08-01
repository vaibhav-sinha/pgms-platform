package com.pgms.manager.controller;

import com.pgms.manager.security.UserDetail;
import com.pgms.service.api.*;
import com.pgms.shared.json.JsonConverter;
import com.pgms.shared.model.*;
import com.pgms.shared.pojo.*;
import com.pgms.shared.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user-1 on 11/7/15.
 */
@Controller
@RequestMapping("/cmo")
@PreAuthorize("hasRole('ROLE_CMO')")
public class CmoController {

    @Autowired
    LocationService locationService;
    @Autowired
    ComplaintService complaintService;
    @Autowired
    ComplaintStatusService complaintStatusService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    OfficerService officerService;
    @Autowired
    UpdateService updateService;
    @Autowired
    ReviewStatusService reviewStatusService;
    @Autowired
    Mapper mapper;
    @Autowired
    JsonConverter jsonConverter;

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(@AuthenticationPrincipal UserDetail userDetail) throws IOException {
        OfficerVO officerVO = mapper.map(userDetail, OfficerVO.class);
        ModelAndView modelAndView = new ModelAndView("officer_dashboard");
        modelAndView.addObject("user", officerVO);
        modelAndView.addObject("userJson", jsonConverter.toJson(officerVO));
        return modelAndView;
    }

    @RequestMapping("/partials/inbox")
    public String partialInbox() {
        return "partials/inbox";
    }

    @RequestMapping("/partials/complaint")
    public String partialComplaint() {
        return "partials/complaint";
    }

    @RequestMapping("/partials/timeline")
    public String partialTimeline(){
        return "partials/timeline";
    }

    @RequestMapping("/partials/timeline-element")
    public String partialTimelineElement(){
        return "partials/timeline-element";
    }
    @RequestMapping("/partials/stats")
    public String partialStats() {
        return "partials/stats";
    }

    @RequestMapping("/partials/stats-table")
    public String partialStatsTable() {
        return "partials/stats-table";
    }

    @RequestMapping("/locationStats")
    @ResponseBody
    public PgmsResponse<Info<Long>> locationStats() {
        Info<Long> statistics = new Info<>();
        List<String> keys;
        List<Info<Long>.InfoEntry> statisticsEntryList = new ArrayList<>();
        List<Location> locationList = locationService.getAllActiveLocation();
        List<ComplaintStatus> complaintStatusList = complaintStatusService.getAllActiveComplaintStatus();

        keys = complaintStatusList.stream().map(ComplaintStatus::getName).collect(Collectors.toList());
        statistics.setName("LocationStats");
        statistics.setKeys(keys);

        for(Location location : locationList) {
            Info<Long>.InfoEntry statisticsEntry = statistics.new InfoEntry();
            statisticsEntry.setName(location.getName());
            statisticsEntry.setValues(new HashMap<>());
            for(ComplaintStatus complaintStatus : complaintStatusList) {
                PgmsComplaintFilter pgmsComplaintFilter = new PgmsComplaintFilter();
                pgmsComplaintFilter.setLocationId(location.getId());
                pgmsComplaintFilter.setComplaintStatusId(complaintStatus.getId());
                Long count = complaintService.getComplaintsCountForFilter(pgmsComplaintFilter);
                statisticsEntry.getValues().put(complaintStatus.getName(), count);
            }
            statisticsEntryList.add(statisticsEntry);
        }

        statistics.setData(statisticsEntryList);
        PgmsResponse<Info<Long>> pgmsResponse = new PgmsResponse<>();
        pgmsResponse.setSuccess(true);
        pgmsResponse.setData(statistics);

        return pgmsResponse;
    }

    @RequestMapping("/departmentStats")
    @ResponseBody
    public PgmsResponse<Info<Long>> departmentStats() {
        Info<Long> statistics = new Info<>();
        List<String> keys;
        List<Info<Long>.InfoEntry> statisticsEntryList = new ArrayList<>();
        List<Department> departmentList = departmentService.getAllActiveDepartments();
        List<ComplaintStatus> complaintStatusList = complaintStatusService.getAllActiveComplaintStatus();

        keys = complaintStatusList.stream().map(ComplaintStatus::getName).collect(Collectors.toList());
        statistics.setName("DepartmentStats");
        statistics.setKeys(keys);

        for(Department department : departmentList) {
            Info<Long>.InfoEntry statisticsEntry = statistics.new InfoEntry();
            statisticsEntry.setName(department.getName());
            statisticsEntry.setValues(new HashMap<>());
            for(ComplaintStatus complaintStatus : complaintStatusList) {
                PgmsComplaintFilter pgmsComplaintFilter = new PgmsComplaintFilter();
                pgmsComplaintFilter.setDepartmentId(department.getId());
                pgmsComplaintFilter.setComplaintStatusId(complaintStatus.getId());
                Long count = complaintService.getComplaintsCountForFilter(pgmsComplaintFilter);
                statisticsEntry.getValues().put(complaintStatus.getName(), count);
            }
            statisticsEntryList.add(statisticsEntry);
        }

        statistics.setData(statisticsEntryList);
        PgmsResponse<Info<Long>> pgmsResponse = new PgmsResponse<>();
        pgmsResponse.setSuccess(true);
        pgmsResponse.setData(statistics);

        return pgmsResponse;
    }

    @RequestMapping("/officerStats")
    @ResponseBody
    public PgmsResponse<Info<String>> officerStats() {
        Info<String> info = new Info<>();
        List<String> keys = new ArrayList<>();
        List<Info<String>.InfoEntry> infoEntryList = new ArrayList<>();

        info.setName("OfficerInfo");
        info.setKeys(keys);
        info.setData(infoEntryList);

        //keys.add("Name");
        keys.add("Department");
        keys.add("Open Complaints");
        keys.add("Actions taken");
        keys.add("Last Login Time");

        List<Officer> officerList = officerService.getAllActiveOfficer();

        for(Officer officer : officerList) {
            PgmsUpdateFilter pgmsUpdateFilter = new PgmsUpdateFilter();
            pgmsUpdateFilter.setOfficerId(officer.getId());

            PgmsComplaintFilter pgmsComplaintFilter = new PgmsComplaintFilter();
            pgmsComplaintFilter.setDepartmentId(officer.getDepartment().getId());
            pgmsComplaintFilter.setComplaintStatusId(complaintStatusService.getClosedComplaintStatus().getId());

            Info<String>.InfoEntry infoEntry = info.new InfoEntry();
            infoEntry.setValues(new HashMap<>());
            infoEntry.setName(officer.getName());

            //infoEntry.getValues().put("Name", officer.getName());
            infoEntry.getValues().put("Department", officer.getDepartment().getName());
            infoEntry.getValues().put("Open Complaints", complaintService.getComplaintsCountForFilter(pgmsComplaintFilter).toString());
            infoEntry.getValues().put("Actions taken", updateService.getUpdatesCountForFilter(pgmsUpdateFilter).toString());
            infoEntry.getValues().put("Last Login Time", officer.getLastSignedIn() != null ? officer.getLastSignedIn().toString() : "Never signed in");

            infoEntryList.add(infoEntry);
        }

        PgmsResponse<Info<String>> pgmsResponse = new PgmsResponse<>();
        pgmsResponse.setSuccess(true);
        pgmsResponse.setData(info);
        return pgmsResponse;
    }

    @RequestMapping("/statusList")
    @ResponseBody
    public PgmsResponse<List<ReviewStatus>> statusList() {
        PgmsResponse<List<ReviewStatus>> pgmsResponse = new PgmsResponse<>();
        pgmsResponse.setData(reviewStatusService.getAllActiveReviewStatus());
        pgmsResponse.setSuccess(true);
        return pgmsResponse;
    }
}
