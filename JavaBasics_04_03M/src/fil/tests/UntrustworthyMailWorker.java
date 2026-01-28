package fil.tests;

public class UntrustworthyMailWorker implements MailService {
    private final MailService realMailService = new RealMailService();
    private MailService[] mailServices;

    public UntrustworthyMailWorker(MailService[] services){
        mailServices = services;
    }

    public MailService getRealMailService(){
        return realMailService;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        for (MailService ms : mailServices) {
            mail = ms.processMail(mail);
        }
        return realMailService.processMail(mail);
    }
}
