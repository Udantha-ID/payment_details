package paymentDetails;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PaymentServlet")
public class PaymenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PaymentDBUtill paymentDBUtil;

    public PaymenServlet() {
        super();
        this.paymentDBUtil = new PaymentDBUtill();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/payment_new":
                    showNewPaymentForm(request, response);
                    break;
                case "/payment_insert":
                    insertPayment(request, response);
                    break;
                case "/payment_delete":
                    deletePayment(request, response);
                    break;
                case "/payment_edit":
                    showEditPaymentForm(request, response);
                    break;
                case "/payment_update":
                    updatePayment(request, response);
                    break;
                default:
                    listPayments(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewPaymentForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");
        dispatcher.forward(request, response);
    }

    private void insertPayment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String cardHolderName = request.getParameter("card-holder-name");
        String emailAddress = request.getParameter("email-address");
        double amount = Double.parseDouble(request.getParameter("amount"));
        long cardNumber = Long.parseLong(request.getParameter("card-number"));
        String expiryDate = request.getParameter("expiry-date");
        int cvv = Integer.parseInt(request.getParameter("cvv"));

        try {
            Payment newPayment = new Payment(cardHolderName, emailAddress, amount, cardNumber, expiryDate, cvv);
            paymentDBUtil.insertPayment(newPayment);
            request.setAttribute("status", "paymentInserted");
            response.sendRedirect("payment_list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deletePayment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        paymentDBUtil.deletePayment(id);

        HttpSession session = request.getSession();
        session.setAttribute("deleteMessage", "Payment deleted successfully.");

        response.sendRedirect("payment_list?success=true");
    }

    private void showEditPaymentForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Payment existingPayment = paymentDBUtil.selectPayment(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");
        request.setAttribute("paymentInfo", existingPayment);
        dispatcher.forward(request, response);
    }

    private void updatePayment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String cardHolderName = request.getParameter("card-holder-name");
        String emailAddress = request.getParameter("email-address");
        double amount = Double.parseDouble(request.getParameter("amount"));
        long cardNumber = Long.parseLong(request.getParameter("card-number"));
        String expiryDate = request.getParameter("expiry-date");
        int cvv = Integer.parseInt(request.getParameter("cvv"));

        Payment updatedPayment = new Payment(id, cardHolderName, emailAddress, amount, cardNumber, expiryDate, cvv);
        paymentDBUtil.updatePayment(updatedPayment);
        response.sendRedirect("payment_list");
    }

    private void listPayments(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Payment> listPayments = paymentDBUtil.selectAllPayments();
        request.setAttribute("listPayments", listPayments);
        RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
