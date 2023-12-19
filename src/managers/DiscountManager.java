package managers;

import entity.Discount;
import tools.InputFromKeyboard;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class DiscountManager {
    public Discount addDiscount(Scanner scanner){
        System.out.println("\n-------- Add new discount --------\n");

        System.out.print("Enter discount name: ");
        String name = scanner.nextLine();

        System.out.print("Enter discount percent: ");
        int discountPercent = InputFromKeyboard.inputFromRange(1, 100, scanner);

        // Define a custom DateTimeFormatter for the input format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.print("Enter discount start date (DD-MM-YYYY): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine(), dateFormatter);

        System.out.print("Enter discount end date (DD-MM-YYYY): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine(), dateFormatter);

        Discount discount = new Discount(name, discountPercent, startDate, endDate);

        return discount;
    }

    public String timeBeforeNextDiscount(List<Discount> discounts){
        Discount nextDiscount = null;
        LocalDateTime dateNow = LocalDateTime.now();

        for(int i = 0; i < discounts.size(); i++){
            if(discounts.get(i).getStart().isBefore(ChronoLocalDate.from(dateNow)) && discounts.get(i).getEnd().isAfter(ChronoLocalDate.from(dateNow))){
                return "Discount is running";
            }
            if(nextDiscount == null && discounts.get(i).getStart().isAfter(ChronoLocalDate.from(dateNow))){
                nextDiscount = discounts.get(i);
                continue;
            }
            if(nextDiscount.getStart().isAfter(discounts.get(i).getStart())){
                nextDiscount = discounts.get(i);
            }
        }
        if(nextDiscount == null){
            return "Discounts wasn't planned yet";
        }
        Duration duration = Duration.between(dateNow, nextDiscount.getStart().atStartOfDay());
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        return String.format("%d дней, %d часов, %d минут, %d секунд", days, hours, minutes, seconds);
    }
}
