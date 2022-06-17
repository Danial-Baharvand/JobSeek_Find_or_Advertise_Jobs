package classes;

public interface Inbox {
    Applications applicationsManager = new Applications();
    Invitations invitationsManager = new Invitations();
    default Applications applications(){
        return applicationsManager;
    }
    default Invitations invitations(){
        return invitationsManager;
    }
}
