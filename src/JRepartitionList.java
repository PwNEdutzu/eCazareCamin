import java.util.List;

public class JRepartitionList extends JConnection {
    public static List<BookingDetails> getRepartitionList() {
        List<BookingDetails> repartitionList = JBookingDetails.getBookingList();
        // Sort the list based on medie in descending order
        repartitionList.sort((bd1, bd2) -> {
            double medie1 = Double.parseDouble(bd1.getMedie());
            double medie2 = Double.parseDouble(bd2.getMedie());
            return Double.compare(medie2, medie1);
        });

        // Print the sorted list
        for (BookingDetails booking : repartitionList) {
            System.out.println(booking.getMedie());
        }
        return repartitionList;
    }
}
