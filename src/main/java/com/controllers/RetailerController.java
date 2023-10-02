package com.controllers;

import com.model.response.RewardsPointsResponse;
import com.service.RewardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
@RequestMapping(value = "/retailer")
public class RetailerController {

  @RequestMapping(
    value = "/status",
    method = RequestMethod.GET,
    produces = {"text/html"}
    )
  @ResponseBody
  public ResponseEntity<String> getStatus() {
    return ResponseEntity.ok("OK!");
  }

  /**
   *
   *  run the API below like this e.g.  http://localhost:8080/retailer/report/4
   *      I have initialized data for customer IDs 1 through 4
   *      ID 4 has transactions over the last 3 months to calculate as the test requested
   *      the report is returned in JSON format
   */
  @RequestMapping(
          value = "/report/{id}",
          method = RequestMethod.GET,
          produces = {"application/json"}
  )
  @ResponseBody
  public ResponseEntity<RewardsPointsResponse> getPoints(@PathVariable String id) {
    System.out.println("getting report for customer ID: " + id);
    RewardsService rewardsService = new RewardsService();
    RewardsPointsResponse rewardsPoints = rewardsService.getRewardsPoints(id);
    return ResponseEntity.ok(rewardsPoints);
  }
}
