import java.util.List;

public class JRepartitionList extends JConnection {
    public static List<BookingDetails> getRepartitionList() {
        List<BookingDetails> repartitionList = JBookingDetails.getBookingList();

        // Sort the list by faraTaxa=true first, then by medie in descending order
        repartitionList.sort((bd1, bd2) -> {
            boolean faraTaxa1 = bd1.getFaraTaxa();
            boolean faraTaxa2 = bd2.getFaraTaxa();

            if (faraTaxa1 && !faraTaxa2) {
                return -1; // bd1 has faraTaxa=true and should be prioritized
            } else if (!faraTaxa1 && faraTaxa2) {
                return 1; // bd2 has faraTaxa=true and should be prioritized
            } else {
                double medie1 = Double.parseDouble(bd1.getMedie());
                double medie2 = Double.parseDouble(bd2.getMedie());

                // Sort by medie in descending order
                return Double.compare(medie2, medie1);
            }
        });

        // Print the sorted list
        for (BookingDetails booking : repartitionList) {
            System.out.println(booking.getMedie());
        }
        return repartitionList;
    }
}
