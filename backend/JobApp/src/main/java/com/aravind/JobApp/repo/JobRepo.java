package com.aravind.JobApp.repo;

import com.aravind.JobApp.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {

    List<JobPost> findByPostProfileContainingOrPostDescContaining(String postProfile, String postDesc);
}

//public List<JobPost> getAllJobs() {
//    return jobs;
//}
//
//public void addJob(JobPost jobPost) {
//    jobs.add(jobPost);
//    System.out.println(jobs);
//}
//
//public JobPost getJob(int postId) {
//    for (JobPost jobPost: jobs) {
//        if(jobPost.getPostId() == postId) {
//            return jobPost;
//        }
//    }
//    return null;
//}
//
//public void updateJob(JobPost jobPost) {
//    for (JobPost jobPost1: jobs) {
//        if(jobPost.getPostId() == jobPost1.getPostId()) {
//            jobPost1.setPostProfile(jobPost.getPostProfile());
//            jobPost1.setPostDesc(jobPost.getPostDesc());
//            jobPost1.setReqExperience(jobPost.getReqExperience());
//            jobPost1.setPostTechStack(jobPost.getPostTechStack());
//        }
//    }
//}
//
//public void deleteJob(int postId) {
//    jobs.removeIf(jobPost -> jobPost.getPostId() == postId);
//}
