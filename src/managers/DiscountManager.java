package managers;

import entity.Discount;
import tools.InputFromKeyboard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class DiscountManager {
    
    PersistToDatabase persistToDatabase = new PersistToDatabase();
    
    public Discount addDiscount(Scanner scanner) throws Exception {
        System.out.println("\n-------- Add new discount --------\n");

        System.out.print("Enter discount name: ");
        String name = scanner.nextLine();

        System.out.print("Enter discount percent: ");
        int discountPercent = InputFromKeyboard.inputFromRange(1, 100, scanner);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        System.out.print("Enter start date (DD-MM-YYYY): ");
        LocalDateTime startDate = LocalDateTime.parse(scanner.nextLine() + " 00:00", formatter);

        System.out.print("Enter end date (DD-MM-YYYY): ");
        LocalDateTime endDate = LocalDateTime.parse(scanner.nextLine() + " 23:59", formatter);

        if(startDate.isAfter(endDate)){
            throw new Exception("Start date cannot be after end date");
        }
        Discount discount = new Discount(name, discountPercent, startDate, endDate);
        
        persistToDatabase.saveDiscount(discount);
        
        return discount;
    }
    public String nextDiscount(List<Discount> discountList){
        Discount nextDiscount = null;
        LocalDateTime now = LocalDateTime.now();
        for(int i = 0; i < discountList.size(); i++){
            Discount discount = discountList.get(i);
            if(discount.getStart().isBefore(now) && discount.getEnd().isAfter(now)){
                long days = ChronoUnit.DAYS.between(now, discount.getEnd());
                long hours = ChronoUnit.HOURS.between(now, discount.getEnd()) - days*24;
                long minutes = ChronoUnit.MINUTES.between(now, discount.getEnd()) - days*24*60 - hours*60;
                long seconds = ChronoUnit.SECONDS.between(now, discount.getEnd()) - days*24*60*60 - hours*60*60 - minutes*60;
                return String.format("Discount is running. \"%s\" (%d%%) - before end: %d days %d hours %d minutes %d seconds", discount.getName(), discount.getDiscount(), days, hours, minutes, seconds);
            }
            if(nextDiscount == null){
                if(discount.getStart().isAfter(now)){
                    nextDiscount = discount;
                }
                continue;
            }
            if(nextDiscount.getStart().isAfter(discount.getStart()) && discount.getStart().isAfter(now)){
                nextDiscount = discount;
            }
        }
        if(nextDiscount == null){
            return "Discounts wasn't planned yet";
        }
        long days = ChronoUnit.DAYS.between(now, nextDiscount.getStart());
        long hours = ChronoUnit.HOURS.between(now, nextDiscount.getStart()) - days*24;
        long minutes = ChronoUnit.MINUTES.between(now, nextDiscount.getStart()) - days*24*60 - hours*60;
        long seconds = ChronoUnit.SECONDS.between(now, nextDiscount.getStart()) - days*24*60*60 - hours*60*60 - minutes*60;
        return String.format("%d days %d hours %d minutes %d seconds before \"%s\"(%d%%) discount", days, hours, minutes, seconds, nextDiscount.getName(), nextDiscount.getDiscount());
    }
}
