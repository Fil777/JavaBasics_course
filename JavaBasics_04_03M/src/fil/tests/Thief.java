package fil.tests;

public class Thief implements MailService {
    private final int minPrice;
    private int stolenValue = 0;

    public Thief(int minPrice){
        this.minPrice = minPrice;
    }

    public int getStolenValue(){
        return stolenValue;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if(mail instanceof MailPackage) {
            Package pack = ((MailPackage)mail).getContent();
            if(pack.getPrice() >= minPrice){
                stolenValue += pack.getPrice();
                mail = new MailPackage(mail.getFrom(), mail.getTo(), stolenPackage(pack));
            }
        }
        return mail;
    }

    private Package stolenPackage(Package pack) {
        return new Package("stones instead of " + pack.getContent(), 0);
    }
}
