package main;


import domaine.Trader;
import domaine.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExerciceGroupingBy {
    enum TransactionsLevel {
        VERY_HI, HI, LO, ME;
    }

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        ExerciceGroupingBy main = new ExerciceGroupingBy(transactions);
        main.run();
    }

    /**
     * La liste de base de toutes les transactions.
     */
    private List<Transaction> transactions;
    // Pour tester le vide
    private List<Transaction> pasDeTransaction = new ArrayList<>();
    /**
     * Crée un objet comprenant toutes les transactions afin de faciliter leur usage pour chaque point de l'énoncé
     *
     * @param transactions la liste des transactions
     */
    public ExerciceGroupingBy(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void run() {
        this.groupBy1();
        this.groupBy2();
        this.groupBy3();
    }

    private void groupBy1() {
        System.out.println("GroupBy1");
        Map<Trader, List<Transaction>> grouped = transactions
                .stream()
                .collect(Collectors.groupingBy(Transaction::getTrader));

        grouped.forEach((trader, transactions) -> System.out.println(trader + " : " + transactions));
    }

    private void groupBy2() {
        System.out.println("GroupBy2");
        Map<Trader, Long> group = transactions
                .stream()
                .collect(Collectors.groupingBy(Transaction::getTrader, Collectors.counting()));
        group.forEach((trader, count) -> System.out.println(trader + " : " + count));
    }


    private void groupBy3() {
        System.out.println("GroupBy3");
        Map<TransactionsLevel, List<Transaction>> map = transactions
                .stream()
                .collect(Collectors.groupingBy(transaction -> {
                    if (transaction.getValue() >= 1000) return TransactionsLevel.VERY_HI;
                    else if (transaction.getValue() >= 800) return TransactionsLevel.HI;
                    else if (transaction.getValue() >= 600) return TransactionsLevel.ME;
                    else return TransactionsLevel.LO;
                }));

        map.forEach((trader, transactions) -> System.out.println(trader + " : " + transactions));
    }
}
