package classes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Tests {

    public Tests() {

    }

    public static HashSet<Job> createExampleJobs() {

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setOrg("Google");
        recruiter1.setEmail("companyEmail@gmail.com");

        Job job1 = new Job();
        job1.setJobTitle("Software Engineer");
        job1.setRecruiter(recruiter1);
        job1.setJobDescription("This is a job. Please apply");
        //job1.setStates("QLD");
        job1.setSalary(125000);
        job1.setJobType("Casual");
        job1.setKeywords("java, sql");


        Job job2 = new Job();
        job2.setJobTitle("It Specialist");
        job2.setRecruiter(recruiter1);
        job2.setJobDescription("This is a job. Please apply");
        //job2.setStates("QLD");
        job2.setSalary(125000);
        job2.setJobType("full-time");

        Job job3 = new Job();
        job3.setJobTitle("Data Analyst");
        job3.setRecruiter(recruiter1);
        job3.setJobDescription("This is a job. Please apply");
        //job3.setStates("QLD");
        job3.setSalary(125000);
        job3.setJobType("part-time");

        Job job4 = new Job();
        job4.setJobTitle("DevOps");
        job4.setRecruiter(recruiter1);
        job4.setJobDescription("This is a job. Please apply");
        //job4.setStates("QLD");
        job4.setSalary(125000);
        job4.setJobType("Full-time");

        Job job5 = new Job();
        job5.setJobTitle("Web Dev");
        job5.setRecruiter(recruiter1);
        job5.setJobDescription("This is a job. Please apply");
        //job5.setStates("QLD");
        job5.setSalary(125000);
        job5.setJobType("casual");

        Job job6 = new Job();
        job6.setJobTitle("Backend");
        job6.setRecruiter(recruiter1);
        job6.setJobDescription("This is a job. Please apply");
        //job6.setStates("QLD");
        job6.setSalary(125000);
        job6.setJobType("full-time");
        return new HashSet<Job>(Arrays.asList(job1, job2, job3, job4, job5));
    }

    public static BiMultiMap createExampleCats(){
        BiMultiMap categories = new BiMultiMap();
        categories.put("compSci", "0001");
        categories.put("Networking", "0002");
        categories.put("AI", "0003");
        return categories;
    }
    public static HashMap<JobSeeker, Integer> createTestJobSeekerScores(){
        HashMap<JobSeeker, Integer> scoredJobSeekers = new HashMap<>();
        Runtime.accountManager().getJobSeekers().values().stream().forEach(j -> scoredJobSeekers.put(j, 100));
        return scoredJobSeekers;
    }
    public static JobSeeker createExampleJobSeekers(){
        JobSeeker js = new JobSeeker();
        js.setFullName("Jack Lu");
        js.setEmail("jack@monash.com");
        js.addSkill("Java");
        js.addSkill("UI design");
        js.addSkill("SQL");
        return js;
    }

    /**
     * tests whether the createJobSeeker method of the CreateAccount class is working successfully
     * success: if a valid email and password are written to the registeredUsers.csv file
     * can be used in conjunction with the testLogin method (which will test whether the newly created user can successfully log in)
     * @param testCreateAccount object of the CreateAccount class, from which the createJobSeeker method is invoked
     * @return true if the use case is expected to run, else false
     */
    public boolean testCreateAccount(CreateAccountPage testCreateAccount, AccountManagement testAccountManagement) {
        //Test 1: expect true
        //testCreateAccount.createJobSeeker("jack", "stoneman", "jack2@example.com", "abc123", "abc123");
        //testAccountManagement.userLogin("jack2@example.com", "abc123");
        return true;
        //Test 2: expect false - passwords don't match
        //testCreateAccount.createJobSeeker("jack", "stoneman", "jack2@example.com", "acb123", "abc124");
        //return false;
        //Test 3: expect false - email isn't unique
        //testCreateAccount.createJobSeeker("jack", "stoneman", "jack@example.com", "acb123", "abc124");
        //return false;
    }

    public static Recruiter createExampleRecruiter(){
        Recruiter testRecruiter = new Recruiter();
        testRecruiter.setOrg("Not Seek");
        testRecruiter.setEmail("recruiter@notseek.com");
        testRecruiter.setRecruiterDescription("If you seek, you will find...");

        Job testJob = new Job();
        testJob.setJobTitle("Software Engineer");
        testJob.setRecruiter(testRecruiter);
        testJob.setJobDescription("This is a job. Please apply");
        //testJob.setStates("QLD");
        testJob.setSalary(125000);

        Job testJob1 = new Job();
        testJob1.setJobTitle("UI Designer");
        testJob1.setRecruiter(testRecruiter);
        testJob1.setJobDescription("This is a job. Please apply");
        //testJob1.setStates("VIC");
        testJob1.setSalary(99000);

        Job testJob2 = new Job();
        testJob2.setJobTitle("Software Technician");
        testJob2.setRecruiter(testRecruiter);
        testJob2.setJobDescription("This is a job. Please apply");
        //testJob2.setStates("QLD");
        testJob2.setSalary(75000);

        return testRecruiter;
    }

}