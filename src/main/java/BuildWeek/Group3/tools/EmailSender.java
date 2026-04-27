package BuildWeek.Group3.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    private final String domainName;
    private final String apiKey;

    public EmailSender(@Value("${mailgun.domainName}") String domainName, @Value("${mailgun.apiKey}") String apiKey) {
        this.domainName = domainName;
        this.apiKey = apiKey;
    }

//    public void sendRegistrationEmail(Utente recipient) {
//        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
//                .basicAuth("api", this.apiKey)
//                .queryString("from", "")
//                .queryString("to", recipient.getEmail())
//                .queryString("subject", "Benvenuto sulla nostra piattaforma!")
//                .queryString("text", "Ciao " + recipient.getNome() + ", la tua registrazione è andata a buon fine!")
//                .asJson();
//
//        System.out.println(response.getBody());
//    }
}