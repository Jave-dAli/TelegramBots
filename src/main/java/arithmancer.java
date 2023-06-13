import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigInteger;

public class arithmancer extends TelegramLongPollingBot {
    public static BigInteger factorial(BigInteger n) {
        return factorialHelper(n, BigInteger.ONE);
    }
    private static BigInteger factorialHelper(BigInteger n, BigInteger result) {
        if (n.compareTo(BigInteger.ZERO) <= 0) {
            return result;
        }
        return factorialHelper(n.subtract(BigInteger.ONE), result.multiply(n));
    }

    @Override
    public void onUpdateReceived(Update update) {
//        System.out.println(update.getMessage().getText());
//        System.out.println(update.getMessage().getFrom().getFirstName());

        String command = update.getMessage().getText();
        SendMessage message = new SendMessage();
        System.out.println(update.getMessage().getFrom().getFirstName()+" "+update.getMessage().getFrom().getLastName());
        if(command.equals("/start")) {
            System.out.println(update.getMessage().getFrom().getFirstName()+" "+update.getMessage().getFrom().getLastName());
            message.setText("Hello There :)");
        }
        try{
            BigInteger number=BigInteger.valueOf(Long.parseLong(update.getMessage().getText()));
            message.setText(
                    String.valueOf(
                            factorial(number)
                    )
            );
        }catch (NumberFormatException e){
            message.setText("Please enter number to get factorial of.");
        }

        message.setChatId(update.getMessage().getChatId());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String getBotToken(){
        return "5864518722:AAGDXQKF0XmOUFv7X4tCtwEFd4OHE0qT3_s";
    }
    @Override
    public String getBotUsername() {
        return "arithmancerbot";
    }
}