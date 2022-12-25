package liftup.user.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

@ServerEndpoint(value = "/websockets/{username}")
@Component
public class socket {
    //Testing purposes for front end use. Elmin and I will work together to make sure this works
    private static Map<Session, String > sessionUsernameMap = new Hashtable< >();
    private static Map < String, Session > usernameSessionMap = new Hashtable < > ();

    private final Logger logger = LoggerFactory.getLogger(socket.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username)
            throws IOException {
        logger.info("Entered into Open");

        sessionUsernameMap.put(session, username);
        usernameSessionMap.put(username, session);

        //show user who joined
        String message = "User:" + username + " has Joined the Chat";
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        // will handle new messages that show
        logger.info(showTime()+ " " +"Entered into Message: Got Message:" + message);
        String username = sessionUsernameMap.get(session);

        if (message.startsWith("@")) // Direct message to a user using the format "@username <message>"
        {
            String destUsername = message.split(" ")[0].substring(1); // remove maybe
            sendMessageToPArticularUser(destUsername,showTime()+ " " + "[DM] " + username + ": " + message);
            sendMessageToPArticularUser(username,showTime()+ " " + "[DM] " + username + ": " + message);
        } else // Message to whole chat
        {
            broadcast(showTime()+ " " + username + ": " + message);
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        //info displayed
        logger.info("Entered into Close");

        String username = sessionUsernameMap.get(session);
        sessionUsernameMap.remove(session);
        usernameSessionMap.remove(username);

        //disconnect user
        String message = username + " disconnected";
        broadcast(showTime()+ " " +message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // error handling
        logger.info("Entered into Error");
    }

    private void sendMessageToPArticularUser(String username, String message) {
        try {
            usernameSessionMap.get(username).getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
        }
    }

    private void broadcast(String message) {
        sessionUsernameMap.forEach((session, username) -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }

        });

    }
    //simple formater to show the time of the day when a message was sent
    public static String showTime(){
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return time;
    }


}//end socket class
