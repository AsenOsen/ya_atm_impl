package com.yabank;

import java.util.Scanner;

public class Main {

    static private ATM someATM = new ATM();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean isContinueWorking = true;

        while(isContinueWorking)
        {
            String cmd = input.nextLine();
            isContinueWorking = SendCommandToATM(cmd);
        }
    }


    private static boolean SendCommandToATM(String cmd)
    {
        cmd = cmd.toLowerCase();

        if(cmd.indexOf("put") != -1)
            someATM.Put(cmd);
        else if(cmd.indexOf("get") != -1)
            someATM.Get(cmd);
        else if(cmd.indexOf("dump") != -1)
            someATM.Dump();
        else if(cmd.indexOf("state") != -1)
            someATM.State();
        else if(cmd.indexOf("quit") != -1) {
            someATM.Quit();
            // работа с банкоматом завершена
            return false;
        }

        return true;
    }
}
