package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below-mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private static int customGroupCount=0;
    private static int messageId=0;

    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }

    public boolean createUser(User user) {
        if(userMobile.contains(user.getMobile()))
            return false;
        userMobile.add(user.getMobile());
        return true;
    }

    public Group createPersonalChat(List<User> users) {
        User admin=users.get(0);
        String name=users.get(1).getName();
        Group group=new Group(name,users.size());

        groupUserMap.put(group,users);

        adminMap.put(group,admin);

        groupMessageMap.put(group,new ArrayList<>());

        return group;
    }

    public Group createGroup(List<User> users) {
        User admin=users.get(0);
        customGroupCount++;
        String name="Group "+customGroupCount;
        Group group=new Group(name,users.size());

        groupUserMap.put(group,users);

        adminMap.put(group,admin);

        groupMessageMap.put(group,new ArrayList<>());

        return group;
    }

    public int createMessage(String content) {
        messageId++;
        Message message=new Message(messageId,content);
        return messageId;

    }

    public boolean checkGroupExist(Group group) {
        if(groupUserMap.containsKey(group))
            return true;
        else
            return false;
    }

    public boolean isUserInTheGroup(User user, Group group) {
        List<User> users=groupUserMap.get(group);
        if(users!=null&&users.contains(user))
            return true;
        return false;

    }

    public int sendMessage(Message message, User sender, Group group) {
        List<Message> messageList=groupMessageMap.get(group);
        messageList.add(message);
        groupMessageMap.put(group,messageList);
        senderMap.put(message,sender);
        return messageList.size();

    }

    public User getAdmin(Group group) {
        return adminMap.get(group);
    }

    public void changeAdmin(User user, Group group) {
        adminMap.put(group,user);

    }
}
