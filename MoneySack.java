package com.yabank;

class MoneySack {
    private int[] nominals = new int[0];

    public void add(int nominal){
        if (nominals == null){
            nominals = new int[1];
            nominals[0] = nominal;
        }else{
            int[] newNominals = new int[nominals.length + 1];
            for (int i = 0; i < nominals.length; i++){
                newNominals[i] = nominals[i];
            }
            newNominals[newNominals.length - 1] = nominal;
            nominals = newNominals;
        }
    }

    public void remove(int n){
        if (nominals == null) return;
        int[] newNominals = new int[nominals.length - 1];
        for (int i = 0;i < nominals.length;i++){
            if (i < n) newNominals[i] = nominals[i];
            if (i > n) newNominals[i-1] = nominals[i];
        }
        nominals = newNominals;
    }

    public int getNominal(){
        int sackWeight = 0;
        if (nominals != null){
            for (int i = 0; i < nominals.length; i++){
                sackWeight += nominals[i];
            }
        }
        return sackWeight;
    }

    public int getNominal(int n){
        int w = -1;
        if (n < nominals.length){
            w = nominals[n];
        }
        return w;
    }

    public int[] getNominals(){
        return nominals;
    }

    public boolean isEmpty(){
        return (getNominal() == 0);
    }
}
