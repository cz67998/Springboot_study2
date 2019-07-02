package com.example.demo.openfire;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Localpart;

import java.net.InetAddress;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/8/27
 * Time:10:25
 **/
public class MyXMPPTCPConnection {
    private static XMPPTCPConnection connection;
    private static MyXMPPTCPConnection myXMPPTCPConnection;
    //private static XMPPTCPConnection connection = null;
    private static final String Server_Name = "@ipet.local";
    //private static final String Server_Name = "@ipet.local";
    private static final String Server_IP = "115.28.179.114";
    private static final int Server_port = 5222;
    private static ChatManager chatManager;
    private static String temp_msg;
    private static int i;
    private static String to;
    private static String openid;
    //private static XMPPTCPConnectionConfiguration.Builder config = null;

    public MyXMPPTCPConnection() {

    }

    public synchronized  static MyXMPPTCPConnection getInstance() {
        if (myXMPPTCPConnection == null) {
            myXMPPTCPConnection = new MyXMPPTCPConnection();
            System.out.println("OpenfireService getInstance()");
        }
        return myXMPPTCPConnection;
    }

    public XMPPTCPConnection getConnection() {
        if (connection == null) {
            openConnection();
        }
        return connection;
    }

    public void openConnection() {
        try {
            XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration.builder();
            // 设置安全模式
            config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
            // 连接标示
            config.setSendPresence(true);
            config.setDebuggerEnabled(true);
            //设置开启压缩，可以节省流量
            config.setCompressionEnabled(true);
            //config.setPort(5222);
            //config.setHost("ipet.local");
            //config.setUsernameAndPassword("admin", "kemao2014");
            config.setHostAddress(InetAddress.getByName(Server_IP));
            config.setXmppDomain(Server_Name);
            config.setPort(Server_port);
            connection = new XMPPTCPConnection(config.build());
            connection.connect();
            //connection.login();
            System.out.println("连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        try {
            if (getConnection() == null) {
                System.out.println("======" + "未连接");
                return false;
            }
            if (!getConnection().isAuthenticated() && getConnection().isConnected()) {
                System.out.println("======" + "开始连接");
                getConnection().login(username, password);
                //更改在线状态
                Presence presence = new Presence(Presence.Type.available);
                presence.setMode(Presence.Mode.available);
                getConnection().sendStanza(presence);
                System.out.println("======" + presence + "测试连接");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 此方法用来向设备发送消息
     *
     * @param from
     * @param mac
     * @param msg
     */
    public String sendMsg(String from, String mac, String msg, String open) {
        String currentMessage = null;
        temp_msg = null;
        try {
            if (getConnection() == null) {
                System.out.println("账号未登陆");
                //login("")
            } else {
                System.out.println("用户mac外" + to + "  " + mac);
                to = mac;
                openid = open;
                chatManager = ChatManager.getInstanceFor(getConnection());
                i = 0;
                EntityBareJid jid = JidCreate.entityBareFrom(to + Server_Name);
                Chat chat = chatManager.chatWith(jid);
                Message message = new Message(jid, Message.Type.chat);
                message.setBody(msg);
                System.out.println("向" + jid + "发送：" + message);
                chat.send(message);
//                Object object = new Object();
//                synchronized (object) {
//                    object.wait(200);
//                }

                chatManager.addIncomingListener(new IncomingChatMessageListener() {
                    @Override
                    public void newIncomingMessage(EntityBareJid entityBareJid, Message message, Chat chat) {
                        temp_msg = message.getBody();
                    }
                });
                //若是监听到消息就退出while(true),超过8秒也退出
                long t1 = System.currentTimeMillis();
                while (true) {
                    long t2 = System.currentTimeMillis();
                    if (t2 - t1 > 8000) {
                        break;
                    } else if (temp_msg != null) {
                        if ("LostSensor".equals(temp_msg) || "AeratorAutoOpened".equals(temp_msg) || "AeratorAutoClosed".equals(temp_msg) || "OxygenIsTooHigh".equals(temp_msg) || "Timer1OpenAerator".equals(temp_msg) || "Timer1CloseAerator".equals(temp_msg) || "Timer2OpenAerator".equals(temp_msg) || "Timer2CloseAerator".equals(temp_msg) || "Timer3OpenAerator".equals(temp_msg) || "Timer3CloseAerator".equals(temp_msg) || "Timer4OpenPump".equals(temp_msg) || "Timer4ClosePump".equals(temp_msg) || "Timer5OpenPump".equals(temp_msg) || "Timer5ClosePump".equals(temp_msg) || "Timer6OpenPump".equals(temp_msg) || "Timer6ClosePump".equals(temp_msg)) {
                            temp_msg = null;
                            continue;
                        } else {
                            currentMessage = temp_msg;
                            System.out.println("currentMessage" + currentMessage);
                            break;
                        }
                    }
                }

//                Mac_user macUser = macService.findByMacAndOpenid(to, openid);
//                String currentMessage = macUser.getCurrentmessage();
//                String state = macUser.getState();
//                System.out.println(currentMessage + "currentMessage");
//                if ("CtlSuccess".equals(state) || currentMessage.contains(";")) {
//                    return true;
//                } else {
//                    return false;
//                }
                return currentMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentMessage;
    }

    public boolean addChatManager(IncomingChatMessageListener incomingChatMessageListener) {
        ChatManager chatManager = ChatManager.getInstanceFor(getConnection());
        chatManager.addIncomingListener(incomingChatMessageListener);
        return true;
    }

    /**
     * 注册，
     *
     * @param
     */
    public String register(String account, String password) {
        if (getConnection() == null) {
            System.out.println("======" + "未连接");
            return "0";
        }
        try {
            AccountManager accountManager =AccountManager.getInstance(connection);
            accountManager.sensitiveOperationOverInsecureConnection(true);
            accountManager.createAccount(Localpart.from(account),password);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "1";
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection = null;
                myXMPPTCPConnection = null;
            }
        }
    }
}
