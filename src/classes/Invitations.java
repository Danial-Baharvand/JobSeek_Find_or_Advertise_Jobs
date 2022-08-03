package classes;

/**
 * A collection of invitations
 */
public class Invitations extends MailManager<Invitation>{
    /**
     * get the message for a specific job
     * @param job to get its message
     * @return the message for the job or null if that invitation doesn't have a message
     */
    public String getMessage(Job job){
        Invitation inv = this.stream().filter(i -> i.getJob().equals(job)).findFirst().orElse(null);
        if (inv != null){
            return inv.message;
        }
        return "No message!";
    }
}
