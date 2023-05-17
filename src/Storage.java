public class Storage {
    private static User loggedUser;
    private static StudentDetails studentDetails;

    private static BookingDetails bookingDetails;

    public static void setLoggedUser(User user) {
        loggedUser = user;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void clearLoggedUser() {
        loggedUser = null;
    }

    public static void setStudentDetails(StudentDetails details) {
        studentDetails = details;
    }

    public static StudentDetails getStudentDetails() {
        return studentDetails;
    }
    public static void setBookingDetails(BookingDetails details) {
        bookingDetails = details;
    }

    public static BookingDetails getBookingDetails() {
        return bookingDetails;
    }
}