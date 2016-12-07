package com.yabank;

import java.util.HashMap;
import java.util.Map;

public class ATM
{

    private Map<Integer, Integer> moneyDb = new HashMap();


    public ATM()
    {
        // Изначально банкомат инициализируется нулевым количеством
        // всех валидных номиналов купюр
        for(Integer moneyVal : new int[]{1, 3, 5, 10, 25, 50, 100, 500, 1000, 5000})
            moneyDb.put(moneyVal, 0);
    }

    public void Put(String command)
    {
        // Данный метод будет работать только с командой из 3 параметров
        String[] params = command.split("\\s+");
        if(params.length != 3)
            return;

        if(params[0].equalsIgnoreCase("put"))
        {
            int userMoneyVal, userMoneyAmo;

            try{
                userMoneyVal = Integer.parseInt(params[1]);
                userMoneyAmo = Integer.parseInt(params[2]);
            }catch (NumberFormatException e)
            {
                return;
            }

            // Если введенный номинал принимается банкоматом
            if(moneyDb.containsKey(userMoneyVal))
            {
                int newMoneyAmo = moneyDb.get(userMoneyVal) + userMoneyAmo;
                moneyDb.put(userMoneyVal, newMoneyAmo);

                State();
            }
        }
    }


    public void Get(String command)
    {
        // Данный метод будет работать только с командой из 3 параметров
        String[] params = command.split("\\s+");
        if(params.length != 2)
            return;

        if(params[0].equalsIgnoreCase("get"))
        {
            int userRequestedAmount;

            try{
                userRequestedAmount = Integer.parseInt(params[1]);
            }catch (NumberFormatException e)
            {
                return;
            }

            // наполнитель userRequestedAmount купюрами в наличии
            // (имплементация задачи о рюкзаке)
            MoneySack fullSack =
                    new MoneySackFiller(moneyDb, userRequestedAmount).fillSack();

            Map<Integer, Integer> given = new HashMap();
            int totalGivenAmount = 0;

            for (int nominal : fullSack.getNominals())
            {
                moneyDb.put(nominal, moneyDb.get(nominal)-1);
                given.put(nominal, given.containsKey(nominal) ? given.get(nominal)+1 : 1);
                totalGivenAmount += nominal;
            }

            for(Map.Entry<Integer, Integer> givenRecord : given.entrySet())
                System.out.print(givenRecord.getKey()+"="+givenRecord.getValue()+", ");
            System.out.println(" всего "+totalGivenAmount);

            // если не удалось собрать нужную сумму из имеющихся купюр
            if(totalGivenAmount != userRequestedAmount)
                System.out.println("без "+(userRequestedAmount-totalGivenAmount));
        }
    }


    public void Dump()
    {
        for(Map.Entry<Integer, Integer> moneyRecord : moneyDb.entrySet())
        {
            int moneyVal = moneyRecord.getKey();
            int moneyAmo = moneyRecord.getValue();
            System.out.println(moneyVal + " " + moneyAmo);
        }
    }


    public void State()
    {
        int totalMoneySumInATM = 0;
        for(Map.Entry<Integer, Integer> moneyRecord : moneyDb.entrySet())
        {
            int moneyVal = moneyRecord.getKey();
            int moneyAmo = moneyRecord.getValue();
            totalMoneySumInATM += moneyVal * moneyAmo;
        }

        System.out.println("всего "+totalMoneySumInATM);
    }


    public void Quit()
    {
        // этот метод мог бы содержать логику по удалению используемых ресурсов
        // например, обнуление базы номиналов купюр:
        moneyDb.clear();
    }
}