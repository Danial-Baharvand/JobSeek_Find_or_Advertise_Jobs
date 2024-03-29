package classes;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Config file and options for the program
 */
public class Config {
    final static String DT_JOBSEEKERS = "src/dataTables/jobSeekers.csv";
    final static String DT_RESUMES = "src/resumes";
    final static String DT_RECRUITERS = "src/dataTables/recruiters.csv";
    final static String DT_ADMINS = "src/dataTables/admins.csv";
    final static String DT_CATEGORIES = "src/dataTables/categories.csv";
    final static String DT_JOBS = "src/dataTables/jobs.csv";
    final static String SEPARATOR_1 = "%%>1<%%";
    final static String SEPARATOR_2 = "%%>2<%%";
    final static String SEPARATOR_3 = "%%>3<%%";
    final static String SEPARATOR_4 = "%%>4<%%";
    final static int EMAIL = 0;
    final static int NAME = 1;
    final static int PASSWORD = 2;
    final static int IS_ACTIVE = 3;
    final static int RESUME = 4;
    final static int SKILLS = 5;
    final static int APPLICATIONS = 6;
    final static int INVITATIONS = 7;
    final static int ORGANISATION = 4;
    final static int RECRUITER_JOBS = 5;
    final static int JOB_TITLE = 0;
    final static int JOB_STATES = 1;
    final static int JOB_SALARY = 2;
    final static int JOB_TYPE = 3;
    final static int JOB_KEYWORDS = 4;
    final static int JOB_DESCRIPTION = 5;
    final static int JOB_RECRUITER = 8;
    final static int JOB_PUBLISHED = 9;
    final static int JOB_CATEGORIES = 10;
    final static int MAIL_JOBSEEKER = 0;
    final static int MAIL_JOB = 1; // unused intentionally
    final static int MAIL_MESSAGE = 2;
    final static int CATEGORY_NAME = 0;
    final static int CATEGORY_KEYWORDS = 1;


    final static ArrayList<String> STATES =
            new ArrayList<>(Arrays.asList("QLD", "NSW", "VIC", "ACT", "WA", "NT", "SA", "TAS"));
    final static ArrayList<String> JOB_TYPES =
            new ArrayList<>(Arrays.asList("Full-Time", "Part-Time", "Casual", "Intern"));
}
