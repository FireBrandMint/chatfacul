package com.collegeapp.chatbot;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class JadeAgent extends Agent
{
    JadeManager owner;

    @Override
    protected void setup() {
        owner = JadeManager.Singleton;

        addBehaviour(new TickerBehaviour(this, 90) {
            @Override
            protected void onTick() {
                ((JadeAgent)myAgent).onTick();
            }
        });
    }

    public static void onTick()
    {
        JadeManager owner = JadeManager.Singleton;
        String cmsg = owner.popClientMessage();
        while (cmsg != null)
        {
            if(cmsg.equals("oi"))
                owner.notifyBotMessage("Olá! :]");
            else if(cmsg.contains("google"))
                owner.notifyBotMessage("Não tenho nenhuma função relacionada ao Google.");
            else if(cmsg.contains("versão"))
                owner.notifyBotMessage("A versão deste aplicativo é 0.0.1 Indev.");
            else if(cmsg.equals("olá"))
                owner.notifyBotMessage("Oi! :]");


            cmsg = owner.popClientMessage();
        }
    }

    private static void loopBackup()
    {
        while(true)
        {
            onTick();
            try {
                Thread.sleep(90);
            }
            catch (Exception e)
            {

            }
        }
    }

    public static void backupPlan()
    {
        Thread thread = new Thread(JadeAgent::loopBackup);
        thread.start();
    }
}
