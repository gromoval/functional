// create NotificationStrategy interface and Notifier class here
@FunctionalInterface
interface NotificationStrategy {
    void notifyCustomer(User1 user);
}

class Notifier {
    private NotificationStrategy notificationStrategy;

    public Notifier(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    public void setNotificationStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    public void notify(User1 user) {
        notificationStrategy.notifyCustomer(user);
    }
}

class Application1 {

    private EmailService emailService;
    private SMSService smsService;

    public Application1(EmailService emailService, SMSService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void run(User1 user) {
        // write your code here
        var notifier = new Notifier(emailService::sendEmail);
        notifier.notify(user);
        notifier.setNotificationStrategy(smsService::sendSMS);
        notifier.notify(user);
    }
}

class User1 {
    private final String email;
    private final String phoneNumber;

    User1(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

interface SMSService {
    void sendSMS(User1 user);
}

interface EmailService {
    void sendEmail(User1 user);
}

class Exec1 {
    public static void main(String[] args) {
        //implementation unknown
    }
}

