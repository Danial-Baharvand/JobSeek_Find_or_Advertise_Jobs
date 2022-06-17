package classes;

public class Invitations extends MailManager<Invitation>{
    public String getMessage(Job job){
        Invitation inv = this.stream().filter(i -> i.getJob().equals(job)).findFirst().orElse(null);
        if (inv != null){
            return inv.message;
        }
        return "No message!";
    }
}
