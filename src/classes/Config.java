package classes;

import java.util.ArrayList;
import java.util.Arrays;

public class Config {
    final static String DT_USERS = "src/dataTables/Users.csv";
    final static String DT_JOBSEEKERS = "src/dataTables/jobSeekers.csv";
    final static String DT_RESUMES = "src/dataTables/resumes.csv";
    final static String DT_RECRUITERS = "src/dataTables/recruiters.csv";
    final static String DT_ADMINS = "src/dataTables/admins.csv";
    final static String DT_CATEGORIES = "src/dataTables/categories.csv";
    final static String DT_JOBS = "src/dataTables/jobs.csv";
    final static String DT_RECRUITER_JOBS = "src/dataTables/recruiterJobs.csv";
    final static String DT_SKILLS = "src/dataTables/skills.csv";
    final static String DT_JOB_APPLICATIONS = "src/dataTables/jobApplications.csv";
    final static int EMAIL = 0;
    final static int NAME = 1;
    final static int PASSWORD = 2;
    final static int ORGANISATION = 3;
    final static int JOB_TITLE = 0;
    final static int JOB_RECRUITER = 7;
    final static int RECRUITER_DESC = 4;
    final static ArrayList<String> STATES =
            new ArrayList<>(Arrays.asList("QLD", "NSW", "VIC", "ACT", "WA", "NT", "SA", "TAS"));
    final static ArrayList<String> JOB_TYPES =
            new ArrayList<>(Arrays.asList("Full-Time", "Part-Time", "Casual", "Intern"));
}
