package paymentDetails;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDBUtill {
    
    private static final String INSERT_PAYMENT_SQL = "INSERT INTO payment (cardHolderName, emailAddress, amount, cardNumber, expiryDate, cvv) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PAYMENT_BY_ID_SQL = "SELECT * FROM payment WHERE id=?";
    private static final String SELECT_ALL_PAYMENTS_SQL = "SELECT * FROM payment";
    private static final String UPDATE_PAYMENT_SQL = "UPDATE payment SET cardHolderName=?, emailAddress=?, amount=?, cardNumber=?, expiryDate=?, cvv=? WHERE id=?";
    private static final String DELETE_PAYMENT_SQL = "DELETE FROM payment WHERE id=?";
    
    public void insertPayment(Payment payment) throws SQLException {
        try (Connection conn = DbConnect.getconnection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_PAYMENT_SQL)) {
            preparedStatement.setString(1, payment.getCardHolderName());
            preparedStatement.setString(2, payment.getEmailAddress());
            preparedStatement.setDouble(3, payment.getAmount());
            preparedStatement.setLong(4, payment.getCardNumber());
            preparedStatement.setString(5, payment.getExpiryDate());
            preparedStatement.setInt(6, payment.getCvv());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean updatePayment(Payment payment) throws SQLException {
        boolean rowUpdated;
        try (Connection conn = DbConnect.getconnection();
             PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_PAYMENT_SQL)) {
            preparedStatement.setString(1, payment.getCardHolderName());
            preparedStatement.setString(2, payment.getEmailAddress());
            preparedStatement.setDouble(3, payment.getAmount());
            preparedStatement.setLong(4, payment.getCardNumber());
            preparedStatement.setString(5, payment.getExpiryDate());
            preparedStatement.setInt(6, payment.getCvv());
            preparedStatement.setInt(7, payment.getId());
            
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    public Payment selectPayment(int id) {
        Payment payment = null;
        try (Connection conn = DbConnect.getconnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_PAYMENT_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String cardHolderName = rs.getString("cardHolderName");
                String emailAddress = rs.getString("emailAddress");
                double amount = rs.getDouble("amount");
                long cardNumber = rs.getLong("cardNumber");
                String expiryDate = rs.getString("expiryDate");
                int cvv = rs.getInt("cvv");
                
                payment = new Payment(id, cardHolderName, emailAddress, amount, cardNumber, expiryDate, cvv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }
    
    public List<Payment> selectAllPayments() {
        List<Payment> payments = new ArrayList<>();
        try (Connection conn = DbConnect.getconnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_PAYMENTS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String cardHolderName = rs.getString("cardHolderName");
                String emailAddress = rs.getString("emailAddress");
                double amount = rs.getDouble("amount");
                long cardNumber = rs.getLong("cardNumber");
                String expiryDate = rs.getString("expiryDate");
                int cvv = rs.getInt("cvv");
                
                payments.add(new Payment(id, cardHolderName, emailAddress, amount, cardNumber, expiryDate, cvv));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
    
    public boolean deletePayment(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection conn = DbConnect.getconnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_PAYMENT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}

