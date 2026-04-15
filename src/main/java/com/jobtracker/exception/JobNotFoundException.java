package com.jobtracker.exception;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(Long jobId){
        super("Job not found with id: %s" + jobId );
    }
}
