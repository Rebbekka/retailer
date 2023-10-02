package com.impl;

import com.model.db.Transaction;
import com.model.response.RewardsPointsPerMonth;
import com.model.response.RewardsPointsResponse;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class RewardsPoints {

    List<Transaction> transactions = new ArrayList<>();
    int thisMonth = 0;
    String thisMonthName = "";

    public RewardsPoints() {
        //get data
        init();
    }

    private void init() {
        //create data here - in real life this would be in a database and we would query the data only for the ID sent in
        //initialize list of transactions for all customers for last 3 months
        //this month
        Calendar cal = Calendar.getInstance();
        thisMonth = cal.get(Calendar.MONTH);

        Transaction transaction = new Transaction("1", 50.00, cal.getTime());
        transactions.add(transaction);
        //answer should be 50

        transaction = new Transaction("2", 120.00, cal.getTime());
        transactions.add(transaction);
        //answer should be 90 per instructions (2 x 20) + (1 x 50)

        transaction = new Transaction("4", 80.00, cal.getTime());
        transactions.add(transaction);
        //answer should be 80

        //set up monthly transactions for ID 4
        transaction = new Transaction("4", 50.00, cal.getTime());
        transactions.add(transaction);
        //this month total will be 50 + 80 from above = 130

        //one month ago
        //just set practice data to 1st day of the month - real data would be actual dates
        cal.set(cal.get(Calendar.YEAR), thisMonth-1, 1);

        transaction = new Transaction("3", 230.00, cal.getTime());
        transactions.add(transaction);
        //answer should be (2 x 130) + (1 x 50) = 310

        transaction = new Transaction("4", 120.00, cal.getTime());
        transactions.add(transaction);
        //this month's total will be 90

        //two months ago
        int month = cal.get(Calendar.MONTH) - 1;
        cal.set(cal.get(Calendar.YEAR), month, 1);

        transaction = new Transaction("4", 230.00, cal.getTime());
        transactions.add(transaction);
        //this month's total will be 310
        //grand total for ID 4 = 530
    }

    public RewardsPointsResponse getRewardsPoints(String reportID) {
        int rewardsPointsThisMonth = 0;
        int rewardsPointsTotal = 0;
        //get rewards points for last three months
        List<RewardsPointsPerMonth> rewardsPoints = new ArrayList<>();

        for (int j = thisMonth; j >= thisMonth-2; j--) {
            rewardsPointsThisMonth = 0;
            //create an entry on the report for each month, even if there are no transactions for the customer
            Calendar cal = Calendar.getInstance();
            cal.set(cal.get(Calendar.YEAR), j, 1);
            thisMonthName = new SimpleDateFormat("MMM").format(cal.getTime());

            for (int i = 0; i < transactions.size(); i++) {
                String ID = transactions.get(i).getID();
                Double dollarsSpent = transactions.get(i).getDollarsSpent();
                Date transactionDate = transactions.get(i).getTransactionDate();

                //get transaction month for this transaction
                Calendar transactionCal = Calendar.getInstance();
                transactionCal.setTime(transactionDate);
                int month = transactionCal.get(Calendar.MONTH);

                if (ID.equals(reportID)) {
                    //do the rewards point math per month and also find the total
                    if (month == j) {
                        rewardsPointsThisMonth += getPointsPerTransaction(dollarsSpent);
                    }
                }
            }
            RewardsPointsPerMonth rewardsPointsPerMonth = new RewardsPointsPerMonth(rewardsPointsThisMonth, thisMonthName);
            rewardsPoints.add(rewardsPointsPerMonth);
            rewardsPointsTotal += rewardsPointsThisMonth;

        }
        //create response for the last three months and the grand total
        RewardsPointsResponse rewardsPointsResponse = new RewardsPointsResponse();
        rewardsPointsResponse.setTotalPoints(rewardsPointsTotal);
        rewardsPointsResponse.setRewardsPointsPerMonth(rewardsPoints);
        return rewardsPointsResponse;
    }

    private int getPointsPerTransaction(Double dollarsSpent) {
        int rewardsCalc = 0;
        if (dollarsSpent < 100 && dollarsSpent >= 50) {
            rewardsCalc = dollarsSpent.intValue();
        } else if (dollarsSpent >= 100) {
            Double amountOver100 = dollarsSpent - 100.00;
            rewardsCalc = (int) (2 * amountOver100) + (50);
        }
        return rewardsCalc;
    }
}
