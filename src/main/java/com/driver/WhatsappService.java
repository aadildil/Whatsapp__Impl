package com.driver;

import java.util.List;

public class WhatsappService {
    WhatsappRepository whatsappRepository=new WhatsappRepository();

    public boolean createUser(String name, String mobile) {
        User user=new User(name,mobile);
        return whatsappRepository.createUser(user);

    }

    public Group createGroup(List<User> users) {

        if(users.size()<3)
            return whatsappRepository.createPersonalChat(users);
        else
            return whatsappRepository.createGroup(users);

    }

    public int createMessage(String content) {
        return whatsappRepository.createMessage(content);
    }

    public boolean checkGroupExist(Group group) {
        return whatsappRepository.checkGroupExist(group);
    }

    public boolean userInTheGroup(User sender,Group group) {
        return whatsappRepository.isUserInTheGroup(sender,group);
    }

    public int sendMessage(Message message, User sender, Group group) {
        return whatsappRepository.sendMessage(message,sender,group);
    }

    public boolean isAdmin(Group group, User user) {
        User admin=whatsappRepository.getAdmin(group);
        if(admin.equals(user))
            return true;
        return false;
    }



    public void changeAdmin(User user, Group group) {
        whatsappRepository.changeAdmin(user,group);
    }
}
