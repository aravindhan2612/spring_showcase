package com.aravind.JobApp;

import com.aravind.JobApp.model.JobPost;
import com.aravind.JobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class JobRestController {

    @Autowired
    private JobService jobService;

    @PostMapping("jobPost")
    public JobPost addJob( @RequestBody JobPost jobPost) {
         jobService.addJob(jobPost);
         return jobService.getJob(jobPost.getPostId());
    }

    @GetMapping(value = "jobPosts", produces = {"application/json"})
    public List<JobPost> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("jobPost/{postId}")
    public JobPost getJob(@PathVariable("postId") int postId) {
       return jobService.getJob(postId);
    }

    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> getByKeyword(@PathVariable("keyword") String keyword) {
        return jobService.getByKeyword(keyword);
    }
    @PutMapping("jobPost")
    public  JobPost updateJob(@RequestBody JobPost jobPost) {
        jobService.updateJob(jobPost);
        return jobService.getJob(jobPost.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public  JobPost deleteJob(@PathVariable int postId) {
        jobService.deleteJob(postId);
        return jobService.getJob(postId);
    }

    @GetMapping("load")
    public String  loadData() {
        jobService.loadData();
        return "success";
    }
}
