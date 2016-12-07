package com.yabank;

import java.util.*;

public class MoneySackFiller {

    private MoneySack availNominals = new MoneySack();
    private MoneySack resultSack = new MoneySack();
    private int requestedSackSize;


    public MoneySackFiller(Map<Integer, Integer> moneyDb, int requestAmount)
    {
        requestedSackSize = requestAmount;

        // Упорядочение всех купюр в наличии по убыванию
        Integer[] orderedMoneyNominals = moneyDb.keySet().toArray(new Integer[moneyDb.size()]);
        Arrays.sort(orderedMoneyNominals, Collections.reverseOrder());

        for(Integer moneyNominal : orderedMoneyNominals)
        {
            Integer amountOfBanknots = moneyDb.get(moneyNominal);
            for(int i=0; i< amountOfBanknots; i++)
                availNominals.add(moneyNominal);
        }
    }


    public MoneySack fillSack()
    {
        boolean isMoneyExists =
                availNominals.getNominals() != null &&
                availNominals.getNominals().length>0;

        if(isMoneyExists)
            fillKnapSack();

        return resultSack;
    }


    public void fillKnapSack(){
        boolean isFull = false;
        for (int i = 0; i < availNominals.getNominals().length; i++){
            if (resultSack.getNominal() + availNominals.getNominal(i) <= requestedSackSize){
                resultSack.add(availNominals.getNominal(i));
                availNominals.remove(i);
                break;
            }
            // Если больше нечего добавить в resultSack из availNominals.
            if (i == availNominals.getNominals().length -1) isFull = true;
            // Если уже все добавлено, что было
            if (availNominals.isEmpty()) isFull = true;
        }
        if (!isFull) fillKnapSack();
    }

}
