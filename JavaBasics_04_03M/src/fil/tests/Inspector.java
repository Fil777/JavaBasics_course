package fil.tests;

public class Inspector implements MailService {

    @Override
    public Sendable processMail(Sendable mail) {
        if(mail instanceof MailPackage) {
            if(isStolenMail(mail)) {
                throw new StolenPackageException();
            } else if(isIllegalMail(mail)){
                throw new IllegalPackageException();
            }
        }
        return mail;
    }

    private boolean isStolenMail(Sendable mail) {
        String content = ((MailPackage)mail).getContent().getContent();
        return content.contains("stones");
    }

    private boolean isIllegalMail(Sendable mail) {
        String content = ((MailPackage)mail).getContent().getContent();
        return content.contains(Constants.WEAPONS) || content.contains(Constants.BANNED_SUBSTANCE);
    }

}
